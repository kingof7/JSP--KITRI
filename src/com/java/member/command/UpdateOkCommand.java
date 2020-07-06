package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("utf-8");
		MemberDto memberDto = new MemberDto();		
		
		/*
		 * HttpSession session = request.getSession(); memberDto.setId((String)
		 * session.getAttribute("id"));
		 */
		
		memberDto.setNum(Integer.parseInt(request.getParameter("num")));
		memberDto.setPassword(request.getParameter("password"));
		memberDto.setEmail(request.getParameter("email"));
		
		memberDto.setZipcode(request.getParameter("zipcode"));
		memberDto.setAddress(request.getParameter("address"));
		memberDto.setJob(request.getParameter("job"));
		memberDto.setMailing(request.getParameter("mailing"));
		memberDto.setInterest(request.getParameter("interest"));
		logger.info(logMsg + memberDto.toString());
		
		//싱글톤방식
		int check = MemberDao.getInstance().update(memberDto);
		logger.info(logMsg + check);
		
		request.setAttribute("check", check);
		
		
		return "/WEB-INF/views/member/updateOk.jsp";
	}

}
