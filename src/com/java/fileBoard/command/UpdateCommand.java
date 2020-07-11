package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class UpdateCommand implements Command {
	
	//read.jsp에서 upFun()에 의해 여기로 이동
	
	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		// read.jsp에서 boardNumber, pageNumber 데이터 가져옴
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		logger.info(logMsg + " boardNumber: " + boardNumber + ", " + "pageNumber: " + pageNumber);
		
		// 게시글 번호로 BoardDao에서 read함수 실행(게시글 정보 가져오기)
		// 게시글 정보 가져와서 boardDto에 담음
		BoardDto boardDto = BoardDao.getInstance().read(boardNumber);
		logger.info(logMsg + boardDto);
		
		// jsp에서 받아온 데이터를 모두 받아주고, 전부 setAttribute로 넘겨줌
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", boardDto);
		request.setAttribute("boardNumber", boardNumber);
		
		return "/WEB-INF/views/fileBoard/update.jsp";
	}

}
