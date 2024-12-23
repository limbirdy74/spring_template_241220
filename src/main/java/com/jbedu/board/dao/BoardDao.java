package com.jbedu.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.jbedu.board.dto.BoardDto;
import com.jbedu.board.util.Constant;

public class BoardDao {
	
	private JdbcTemplate template;
	
	public BoardDao() {
		this.template = Constant.template;
	}
	
	public void boardWrite(final String bname, final String btitle, final String bcontent) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				// TODO Auto-generated method stub
				String sql = "INSERT INTO mvc_board(bname, btitle, bcontent, bhit) VALUES (?, ?, ?, 0)";
				
				PreparedStatement pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, bname);
				pstmt.setString(2, btitle);
				pstmt.setString(3, bcontent);
				
				return pstmt;
			}
		});

	}
	
	// 글목록보기
	public ArrayList<BoardDto> boardList() {
		String sql = "SELECT * FROM mvc_board ORDER BY bnum DESC";
		
		ArrayList<BoardDto> bDtos = (ArrayList<BoardDto>) this.template.query(sql, new BeanPropertyRowMapper(BoardDto.class));
		
		return bDtos;
	}
	
	// 글삭제하기. template 얘들은 final 로 해야 오류 안남
	public void boardDelete(final String bnum) {
		
		String sql = "DELETE FROM mvc_board WHERE bnum = ?";
		
		// u -> 자동완성 다섯번쨰 것. null대신 new 자동완성 두번쨰
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, bnum);
				
			}
		});	
		
	}

}
