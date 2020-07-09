package com.java.board.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.board.model.BoardDao;
import com.java.board.model.BoardDto;
import com.java.command.Command;

public class ListCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String pageNumber = request.getParameter("pageNumber");
		if(pageNumber == null) pageNumber = "1";
		
		int currentPage = Integer.parseInt(pageNumber); // 1이 요청됨
		logger.info(logMsg + currentPage);
		
		int boardSize = 10; //페이지당 게시물 수 // [1] 스타트:1 엔드:10 [2] 스타트:11 엔드: 20 ....				
		int startRow = boardSize*(currentPage-1)+1; // 시작번호 1, 11, 21, 31 ...
		int endRow = currentPage*boardSize;	 // 끝번호 10, 20, 30, ...
		
		//BoardDao
		int count = BoardDao.getInstance().getCount(); // 게시글 갯수
		logger.info(logMsg + count);
		
		ArrayList<BoardDto> boardList = null;
		if(count > 0) {
			// startRow, endRow
			boardList = BoardDao.getInstance().getBoardList(startRow, endRow);
			//logger.info(logMsg + boardList.size());
		}
		request.setAttribute("boardList", boardList);
		request.setAttribute("boardSize", boardSize);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("count", count);
		
		logger.info(boardList.toString());
		
		return "/WEB-INF/views/board/list.jsp";
	}

}
