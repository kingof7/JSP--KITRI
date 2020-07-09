package com.java.board.command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		BoardDto boardDto = new BoardDto();	
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		
		boardDto.setSubject(request.getParameter("subject"));		
		boardDto.setContent(request.getParameter("content"));		
		boardDto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		
		logger.info(logMsg + boardDto + pageNumber);
		
		int check = BoardDao.getInstance().update(boardDto);
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("check", check);
		request.setAttribute("boardDto", boardDto);
		
		logger.info("pageNumber: " + pageNumber + "check: " + check + "boardDto: " + boardDto.toString());
		
		return "/WEB-INF/views/board/updateOk.jsp";
	}

}
