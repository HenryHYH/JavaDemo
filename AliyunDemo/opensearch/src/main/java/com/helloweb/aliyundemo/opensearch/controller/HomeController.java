package com.helloweb.aliyundemo.opensearch.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aliyun.opensearch.sdk.generated.commons.OpenSearchClientException;
import com.aliyun.opensearch.sdk.generated.commons.OpenSearchException;
import com.helloweb.aliyundemo.opensearch.demo.OpenSearchFactory;

@RestController
@RequestMapping(path = "/home")
public class HomeController {

	@Autowired
	private OpenSearchFactory factory;

	@RequestMapping
	public String index(@RequestParam(defaultValue = "搜索") String keyword) {

		String query = "default:'" + keyword + "'";
		String result = factory.search(query);

		System.out.println(result);

		return result;
	}

	@RequestMapping(path = "/suggest")
	public String suggest(@RequestParam(defaultValue = "cs") String query)
			throws OpenSearchException, OpenSearchClientException {

		String result = factory.suggest(query);

		return result;
	}

	@RequestMapping("home/scroll")
	public String scroll() throws OpenSearchException, OpenSearchClientException, InterruptedException {

		factory.scroll();

		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	@RequestMapping("home/add")
	public String add() throws OpenSearchException, OpenSearchClientException {

		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("id", "6");
		fields.put("title", "测试一下标题");
		fields.put("description", "这是测试的描述字段，没有什么内容！！");
		fields.put("author_id", "5");

		String result = factory.addDocument("product", fields);
		System.out.println(result);

		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
	}

	@RequestMapping("home/update")
	public String update() throws OpenSearchException, OpenSearchClientException {
		Map<String, Object> fields = new HashMap<String, Object>();
		fields.put("id", "6");
		fields.put("title", "测试一下标题2");
		fields.put("author_id", "4");

		String result = factory.updateDocument("product", fields);
		System.out.println(result);

		return result;

	}

}
