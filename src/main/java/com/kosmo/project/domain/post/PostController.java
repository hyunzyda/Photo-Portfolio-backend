package com.kosmo.project.domain.post;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kosmo.project.dto.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	final PostDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${imgdir}")
	String fdir;
	
	public PostController(PostDAO dao) {
		this.dao = dao;
	}
	
	// 포스트 추가 요청
	@PostMapping("/add")
	public String addPost(@ModelAttribute Post post, Model m, @RequestParam("file") MultipartFile file) {
		try {
			File dest = new File(fdir + "/" + file.getOriginalFilename());
			
			file.transferTo(dest);
			
			post.setImg("/img/" + dest.getName());
			
			dao.addPost(post);
		} catch (IllegalStateException | IOException | SQLException e) {
			e.printStackTrace();
			logger.info("포스트 추가 오류 발생");
			m.addAttribute("error", "포스트가 정상적으로 등록되지 않았습니다.");
		}
		return "redirect:/post/admin";
	}
	
	// 포스트 삭제 요청
	@GetMapping("/delete/{pid}")
	public String delPost(@PathVariable int pid, Model m) {	
		try {
			dao.delPost(pid);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("포스트 삭제 오류 발생");
			m.addAttribute("error", "포스트가 정상적으로 삭제되지 않았습니다.");
		}
		return "redirect:/post/admin";
	}
	
	// 관리자 요청
	@GetMapping("/admin")
	public String admin(Model m) {
		List<Post> list;
		
		try {
			list = dao.getAll();
			m.addAttribute("postList", list);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("어드민 접근 문제 발생");
			m.addAttribute("error", "어드민으로 접근하지 못했습니다.");
		}
		return "post/admin";
	}
	
	@GetMapping("/list/{pid}")
	public String getNews(@PathVariable int pid, Model m) {
		try {
			Post post = dao.getPost(pid);
			m.addAttribute("post", post);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("포스트 출력 오류 발생");
			m.addAttribute("error", "포스트를 정상적으로 가져오지 못했습니다.");
		}
		return "post/info";
	}
	

	
	@GetMapping("/list")
	public String moveToArea(Model m, @RequestParam(required = false) String loc, HttpServletRequest req) {
		List<Post> list;
		try {
			list = dao.getAll();
			m.addAttribute("loc", loc);
			m.addAttribute("postList", list);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("여행지 출력 과정에서 문제 발생");
			m.addAttribute("error", "여행지 출력이 정상적으로 처리되지 않았습니다.");
		}
		return "post/list";
	}
	
	@GetMapping("/main")
	public String main(Model m, HttpServletRequest req) {
		List<Post> list;
		try {
			list = dao.getAll();
			m.addAttribute("postList", list);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("여행지 출력 과정에서 문제 발생");
			m.addAttribute("error", "여행지 출력이 정상적으로 처리되지 않았습니다.");
		}
		return "main/main2";
	}
}
