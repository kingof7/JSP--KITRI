package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.command.Command;

public class DeleteOkCommand implements Command{

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		String password = request.getParameter("password");
		
		logger.info(logMsg + boardNumber + "," + pageNumber + "," + password);
		
		int check = BoardDao.getInstance().delete(boardNumber, password);
		logger.info(logMsg + check);
		
		request.setAttribute("check", check);
		request.setAttribute("pageNumber", pageNumber);
		
		return "/WEB-INF/views/board/deleteOk.jsp";
		
	}

}
