package com.kakao.todoapp.web;

import com.kakao.todoapp.core.service.message.MessageSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	private MessageSourceService messageSourceService;

	@Autowired
	public HelloController(MessageSourceService messageSourceService) {
		this.messageSourceService = messageSourceService;
	}

	@GetMapping({"/", "/login"})
	public String hello() {
		return "redirect:hello.html";
	}

	@GetMapping(value = "/main")
	public String main() {
		return "redirect:app-main.html";
	}
}
