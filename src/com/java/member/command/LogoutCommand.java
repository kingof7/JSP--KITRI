package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;

public class LogoutCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
//		if(!session.isNew()) {
//			String id = (String) session.getAttribute("id");
//			String memberLevel = (String) session.getAttribute("memberLevel");
//			logger.info(logMsg + id + " " + memberLevel);
//			
//			session.invalidate();
//		}
		
		return "/WEB-INF/views/member/logout.jsp";
	}
}
