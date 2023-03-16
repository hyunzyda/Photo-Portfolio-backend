package com.kosmo.project.domain.board;

import java.sql.SQLException;
import java.util.List;

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

import com.kosmo.project.dto.Board;

@Controller
@RequestMapping("/board")
public class BoardController {
	final BoardDAO dao;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
	public BoardController(BoardDAO dao) {
		this.dao=dao;
	}
	
	@PostMapping("/add")
	public String addWrite(@ModelAttribute Board board,Model m) {
		try {
			dao.addWrite(board);	
		} catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("error","게시글이 정상적으로 등록되지 않았습니다");
		}
		return "redirect:/board/list";
	}
	
	@GetMapping("/delete/{bid}")
	public String deleteBoard(@PathVariable int bid,Model m) {
		try {
			dao.delBoard(bid);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("게시글 삭제 과정에서 문제 발생!!");
			m.addAttribute("error","게시글이 정상적으로 삭제되지 않았습니다!!");
		}
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String listBoard(Model m) {
		List<Board> list;
		
		try {
			list=dao.getAll();
			m.addAttribute("boardlist",list);			
		} catch(Exception e) {
			e.printStackTrace();
			m.addAttribute("error","게시목록이 정상적으로 처리되지 않았습니다");
		}
		return "/board/boardList";
	}
	
	@GetMapping("/{bid}")
	public String getBoard(@PathVariable int bid,Model m) {
		Board b;
		try {
			b = dao.getBoard(bid);
			m.addAttribute("board",b);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warn("게시글을 가져오는 과정에서 문제 발생!!");
			m.addAttribute("error","게시글을 정상적으로 가져오지 못했습니다!!");
		}
		return "/board/boardView";
	}
}
