package com.kakao.todoapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

	@GetMapping({"/", "/login"})
	public String welcome() {
		return "redirect:hello.html";
	}

	@GetMapping(value = "/main")
	public String main() {
		return "redirect:app-main.html";
	}
}
