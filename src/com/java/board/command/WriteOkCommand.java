package com.java.board.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
				
		//write.jsp -> Dto에 담아주기 위해
		
		BoardDto boardDto = new BoardDto();
		boardDto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		boardDto.setGroupNumber(Integer.parseInt(request.getParameter("groupNumber")));
		boardDto.setSequenceNumber(Integer.parseInt(request.getParameter("sequenceNumber")));
		boardDto.setSequenceLevel(Integer.parseInt(request.getParameter("sequenceLevel")));
		
		boardDto.setWriter(request.getParameter("writer"));
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setEmail(request.getParameter("email"));
		boardDto.setContent(request.getParameter("content"));
		boardDto.setPassword(request.getParameter("password"));
		boardDto.setWriteDate(new Date());	 //시간
		
		logger.info(logMsg + boardDto);
		
		int check = BoardDao.getInstance().insert(boardDto);
		logger.info(logMsg + "check: " +  check);
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/board/writeOk.jsp";
	}

}
