package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.user.domain.User;
import com.sns.user.mapper.UserMapper;

@Controller
public class TestController {
	@Autowired
	private UserMapper userMapper;
	
	@GetMapping("/test1")
	@ResponseBody
	public String test1() {
		return "<h1>성공<h1>";
	}
	
	
	@GetMapping("/test2")
	@ResponseBody
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("1", 11);
		map.put("2", 22);
		
		return map;
	}
	
	@GetMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	@GetMapping("/test4")
	@ResponseBody
	public List<Map<String, Object>> test4() {
		return userMapper.selectUserList();
	}
	
	
}