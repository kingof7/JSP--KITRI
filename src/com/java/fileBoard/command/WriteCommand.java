package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;

public class WriteCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		logger.info(logMsg + "WriteCommand");
		
		// ROOT글일 때
		int boardNumber = 0;		// ROOT글이면 0
		int groupNumber = 1;		// 그룹번호
		int sequenceNumber = 0;		// 글 순서
		int sequenceLevel = 0;		// 글 레벨
		
		// 답글인경우, DB 글번호, 그룹번호, 글 순서, 글 레벨을 갖고와야함 from 부모글 // boardNumber = 부모글번호
		if(request.getParameter("boardNumber") != null) {
			boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber = Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber = Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel = Integer.parseInt(request.getParameter("sequenceLevel"));
		}
		
		logger.info(logMsg + "boardNumber: " + boardNumber);
		logger.info(logMsg + "groupNumber: " + groupNumber);
		logger.info(logMsg + "sequenceNumber: " + sequenceNumber);
		logger.info(logMsg + "sequenceLevel: " + sequenceLevel);
		
		request.setAttribute("boardNumber", boardNumber);
		request.setAttribute("groupNumber", groupNumber);
		request.setAttribute("sequenceNumber", sequenceNumber);
		request.setAttribute("sequenceLevel", sequenceLevel);
				
		return "/WEB-INF/views/fileBoard/write.jsp";
	}

}
