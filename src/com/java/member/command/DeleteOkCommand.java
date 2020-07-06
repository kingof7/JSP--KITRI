package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;

public class DeleteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		System.out.println("OK");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		logger.info(logMsg + id + " " + password);
		
		int check = MemberDao.getInstance().delete(id, password);
		
		logger.info(logMsg + check);
		
		return "/WEB-INF/views/member/deleteOk.jsp";
	}

}
