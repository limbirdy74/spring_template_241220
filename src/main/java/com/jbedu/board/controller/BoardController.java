package com.jbedu.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbedu.board.dao.BoardDao;
import com.jbedu.board.util.Constant;

@Controller
public class BoardController {
	
	private JdbcTemplate template;  // servlet-context.xml 에 만들어진 것을 주입받아야 함. DI(dependency injection)
	
	@Autowired  // 컨테이터 만들어져 있는 bean(객체)가 자동으로 DI 됨
	public void setTemplate(JdbcTemplate template) { // DI -> 의존성 주입(Dependency Injection)
		this.template = template;
		Constant.template = this.template;
	}
	
	@RequestMapping(value = "/write_form")
	public String write_form() {
		return "write_form";
	}

	@RequestMapping(value = "/writeOk")
	public String writeOk(HttpServletRequest request, Model model) {

		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		// command 에서 해야 하나 그냥 해보자
		
		BoardDao boardDao = new BoardDao();
		boardDao.boardWrite(bname, btitle, bcontent);
		
		return "redirect:boardList";
	}	
	
	@RequestMapping(value = "/boardList")
	public String boardList(HttpServletRequest request, Model model) {
		
		BoardDao boardDao = new BoardDao();
		model.addAttribute("bDtos", boardDao.boardList());
		
		return "boardList";
	}
	
	@RequestMapping(value = "/delete_form")
	public String delete_form() {
		return "delete_form";
	}
	
	@RequestMapping(value = "/deleteOk")
	public String deleteOk(HttpServletRequest request, Model model) {

		String bnum = request.getParameter("bnum");
		
		// command 에서 해야 하나 그냥 해보자
		
		BoardDao boardDao = new BoardDao();
		boardDao.boardDelete(bnum);
		
		return "redirect:boardList";
	}
}
