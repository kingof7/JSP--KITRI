package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;

public class LoginOkCommand implements Command{
	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		logger.info(logMsg + id + " " + password);
		
		MemberDao dao = MemberDao.getInstance();
		String memberLevel = dao.loginCheck(id,password);
		logger.info(logMsg + memberLevel);
		
		request.setAttribute("memberLevel", memberLevel);
		request.setAttribute("id", id);
		
		return "/WEB-INF/views/member/loginOk.jsp";
	}
}
