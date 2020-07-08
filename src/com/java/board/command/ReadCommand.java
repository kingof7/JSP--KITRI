package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class ReadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		logger.info(logMsg + " boardNumber: " + boardNumber + ", " + "pageNumber: " + pageNumber);
		
		BoardDto boardDto = BoardDao.getInstance().read(boardNumber);
		logger.info(logMsg + boardDto);
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", boardDto);
		
		return "/WEB-INF/views/board/read.jsp";
	}

}
