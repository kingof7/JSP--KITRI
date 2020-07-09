package com.java.board.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// update.jsp에서 여기로 넘어옴
		// 한글 인코딩		
		request.setCharacterEncoding("utf-8");
		// pageNumber는 boardDto의 필드가 아니므로 int로 받음
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		BoardDto boardDto = new BoardDto();
		
		//제목, 내용, 글번호만 받아서 update()로 넘겨줌
		boardDto.setSubject(request.getParameter("subject"));
		boardDto.setContent(request.getParameter("content"));
		//boardNumber는 boardDto의 필드이기에 이렇게 셋팅
		boardDto.setBoardNumber(Integer.parseInt(request.getParameter("boardNumber")));
		
		logger.info(logMsg + boardDto + pageNumber);
		
		//업데이트 완료 확인용 check
		int check = BoardDao.getInstance().update(boardDto);
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("check", check);
		request.setAttribute("boardDto", boardDto);
		
		logger.info("pageNumber: " + pageNumber + "check: " + check + "boardDto: " + boardDto.toString());
		
		return "/WEB-INF/views/board/updateOk.jsp";
		
	}

}
