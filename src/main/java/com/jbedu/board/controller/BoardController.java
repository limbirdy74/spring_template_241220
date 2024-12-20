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
	
	private JdbcTemplate template;
	
	@Autowired
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
}
