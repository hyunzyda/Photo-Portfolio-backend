package com.kosmo.project.domain.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.kosmo.project.dto.User;

@Controller
@RequestMapping("/dologin")
public class LoginController {
	
	final LoginDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	public LoginController(LoginDAO dao) {
		this.dao=dao;
	}
	
	@PostMapping("/confirm")
	public String confirmInfo(@ModelAttribute User u,Model m, HttpServletRequest request) {
	    String id = request.getParameter("userid");
	    String pass = request.getParameter("userps");
		m.addAttribute("id",id);
		m.addAttribute("pw",pass);
		try {
			String name=dao.getInfo(u);	
			
			if(name!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("sessionid", id);

			}else {
				m.addAttribute("error","아이디와 비밀번호를 확인하세요!");
				return "login";
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "/main/main";
	}
}
