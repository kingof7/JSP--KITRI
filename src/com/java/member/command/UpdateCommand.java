package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		logger.info(logMsg + id);
		
		MemberDto memberDto = MemberDao.getInstance().updateId(id);
		logger.info(logMsg + memberDto.toString());
		
		request.setAttribute("memberDto", memberDto);
		
		return "/WEB-INF/views/member/update.jsp";
	}

}
