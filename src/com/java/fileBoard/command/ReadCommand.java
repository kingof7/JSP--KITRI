package com.java.fileBoard.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class ReadCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		int boardNumber = Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		logger.info(logMsg + " boardNumber: " + boardNumber + ", " + "pageNumber: " + pageNumber);
		
		BoardDto boardDto = BoardDao.getInstance().read(boardNumber);
		logger.info(logMsg + boardDto);
		
		if(boardDto.getFileSize() != 0) {
			// _의 인덱스 뽑아줌
			int index = boardDto.getFileName().indexOf("_") + 1;
			boardDto.setFileName(boardDto.getFileName().substring(index));
		}
		
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("boardDto", boardDto);
		
		return "/WEB-INF/views/fileBoard/read.jsp";
	}

}
