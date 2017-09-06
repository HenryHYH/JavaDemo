package com.helloweb.aliyundemo.logservice.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.Consts.CursorMode;
import com.aliyun.openservices.log.common.Histogram;
import com.aliyun.openservices.log.common.LogGroupData;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.Logs.Log;
import com.aliyun.openservices.log.common.Logs.Log.Content;
import com.aliyun.openservices.log.common.Logs.LogGroup;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.request.GetHistogramsRequest;
import com.aliyun.openservices.log.request.GetLogsRequest;
import com.aliyun.openservices.log.request.ListLogStoresRequest;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.aliyun.openservices.log.response.BatchGetLogResponse;
import com.aliyun.openservices.log.response.GetCursorResponse;
import com.aliyun.openservices.log.response.GetHistogramsResponse;
import com.aliyun.openservices.log.response.GetLogsResponse;

@RestController
@RequestMapping("/")
public class HomeController {

	@RequestMapping("")
	public String index() {

		try {
			test();
		} catch (LogException | InterruptedException e) {
			e.printStackTrace();
		}

		return "Hello world!!!";
	}

	public void test() throws LogException, InterruptedException {
		String endpoint = "http://cn-shenzhen.log.aliyuncs.com"; // 选择与上面步骤创建 project 所属区域匹配的Endpoint
		String accessKeyId = "LTAIAEMVSMtDaRcn"; // 使用您的阿里云访问密钥 AccessKeyId
		String accessKeySecret = "u6CgiFiWd6ahL8ux4fRd7tWmiHmDhH"; // 使用您的阿里云访问密钥AccessKeySecret
		String project = "henry-hyh-logservice"; // 上面步骤创建的项目名称
		String logstore = "henry-hyh-logstore"; // 上面步骤创建的日志库名称

		// 构建一个客户端实例
		Client client = new Client(endpoint, accessKeyId, accessKeySecret);
		// 列出当前 project 下的所有日志库名称
		int offset = 0;
		int size = 100;
		String logStoreSubName = "";
		ListLogStoresRequest req1 = new ListLogStoresRequest(project, offset, size, logStoreSubName);
		ArrayList<String> logStores = client.ListLogStores(req1).GetLogStores();
		System.out.println("ListLogs:" + logStores.toString() + "\n");
		// 写入日志
		String topic = "";
		String source = "";
		// 连续发送 10 个数据包，每个数据包有 10 条日志
		for (int i = 0; i < 10; i++) {
			Vector<LogItem> logGroup = new Vector<LogItem>();
			for (int j = 0; j < 10; j++) {
				LogItem logItem = new LogItem((int) (new Date().getTime() / 1000));
				logItem.PushBack("index", String.valueOf(i * 10 + j));
				logGroup.add(logItem);
			}
			PutLogsRequest req2 = new PutLogsRequest(project, logstore, topic, source, logGroup);
			client.PutLogs(req2);
			/*
			 * 发送的时候也可以指定将数据发送至有一个特定的 shard，只要设置 shard 的 hashkey，则数据会写入包含该 hashkey 的 range
			 * 所对应的 shard，具体 API 参考以下接口： public PutLogsResponse PutLogs( String project,
			 * String logStore, String topic, List<LogItem> logItems, String source, String
			 * shardHash // 根据 hashkey 确定写入 shard，hashkey 可以是 MD5(ip) 或 MD5(id) 等 ) throws
			 * LogException;
			 */
		}
		// 把 0 号 shard 中，最近 1 分钟写入的数据都读取出来。
		int shard_id = 0;
		long curTimeInSec = System.currentTimeMillis() / 1000;
		GetCursorResponse cursorRes = client.GetCursor(project, logstore, shard_id, curTimeInSec - 60);
		String beginCursor = cursorRes.GetCursor();
		cursorRes = client.GetCursor(project, logstore, shard_id, CursorMode.END);
		String endCursor = cursorRes.GetCursor();
		String curCursor = beginCursor;
		while (curCursor.equals(endCursor) == false) {
			int loggroup_count = 2; // 每次读取两个 loggroup
			BatchGetLogResponse logDataRes = client.BatchGetLog(project, logstore, shard_id, loggroup_count, curCursor,
					endCursor);
			// 读取LogGroup的List
			List<LogGroupData> logGroups = logDataRes.GetLogGroups();
			for (LogGroupData logGroupData : logGroups) {
				// 直接使用Protocol buffer格式的LogGroup进行
				LogGroup log_group_pb = logGroupData.GetLogGroup();
				System.out.println("Source:" + log_group_pb.getSource());
				System.out.println("Topic:" + log_group_pb.getTopic());
				for (Log log_pb : log_group_pb.getLogsList()) {
					System.out.println("LogTime:" + log_pb.getTime());
					for (Content content : log_pb.getContentsList()) {
						System.out.println(content.getKey() + ":" + content.getValue());
					}
				}
			}
			String next_cursor = logDataRes.GetNextCursor();
			System.out.println("The Next cursor:" + next_cursor);
			curCursor = next_cursor;
		}
		// ！！！重要提示 : 只有打开索引功能，才能调用以下接口 ！！！
		// 等待 1 分钟让日志可查询
		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// 查询日志分布情况
		String query = "index";
		int from = (int) (new Date().getTime() / 1000 - 300);
		int to = (int) (new Date().getTime() / 1000);
		GetHistogramsResponse res3 = null;
		while (true) {
			GetHistogramsRequest req3 = new GetHistogramsRequest(project, logstore, topic, query, from, to);
			res3 = client.GetHistograms(req3);
			if (res3 != null && res3.IsCompleted()) // IsCompleted() 返回
													// true，表示查询结果是准确的，如果返回
													// false，则重复查询
			{
				break;
			}
			Thread.sleep(200);
		}
		System.out.println("Total count of logs is " + res3.GetTotalCount());
		for (Histogram ht : res3.GetHistograms()) {
			System.out.printf("from %d, to %d, count %d.\n", ht.GetFrom(), ht.GetTo(), ht.GetCount());
		}
		// 查询日志数据
		long total_log_lines = res3.GetTotalCount();
		int log_offset = 0;
		int log_line = 10;
		while (log_offset <= total_log_lines) {
			GetLogsResponse res4 = null;
			// 对于每个 log offset,一次读取 10 行 log，如果读取失败，最多重复读取 3 次。
			for (int retry_time = 0; retry_time < 3; retry_time++) {
				GetLogsRequest req4 = new GetLogsRequest(project, logstore, from, to, topic, query, log_offset,
						log_line, false);
				res4 = client.GetLogs(req4);
				if (res4 != null && res4.IsCompleted()) {
					break;
				}
				Thread.sleep(200);
			}
			System.out.println("Read log count:" + String.valueOf(res4.GetCount()));
			log_offset += log_line;
		}
	}
}
