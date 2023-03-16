package com.kosmo.project.domain.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosmo.project.dto.User;

@Controller
@RequestMapping("/join")
public class JoinController {
	final JoinDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	int result;
	
	@Autowired
	public JoinController(JoinDAO dao) {
		this.dao=dao;
	}
	
	@PostMapping("/add")
	public String addWrite(@ModelAttribute User user,Model m) {
		
		try {
			dao.addInfo(user);
			
		} catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("error","이미 가입된 아이디명 입니다");
			return "join";
		}
		return "login";
	}

}
