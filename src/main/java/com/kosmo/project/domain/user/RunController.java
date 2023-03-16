package com.kosmo.project.domain.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RunController {
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
	
	@GetMapping("/join")
	public String join() {
		return "/join";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "/user/logout";
	}
	
	@GetMapping("/main/main")
	public String main1() {
		return "/main/main";
	}
}
