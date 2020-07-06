package com.java.member.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.MemberDto;

public class RegisterOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		MemberDto memberDto = new MemberDto();
		
		
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String jumin1 = request.getParameter("jumin1");
		String jumin2 = request.getParameter("jumin2");
		String email = request.getParameter("email");
		
		String zipcode = request.getParameter("zipcode");
		String address = request.getParameter("address");
		String job = request.getParameter("job");
		String mailing = request.getParameter("mailing");
		String interest = request.getParameter("resultInterest");
		
		logger.info(logMsg + " " + id + " " + password + " " + name + " " + jumin1 + " " + jumin2
				+ " " + email + " " + zipcode + " " + address + " "  + job + " " + mailing + " " + interest);
		memberDto.setId(id);
		memberDto.setPassword(password);
		memberDto.setName(name);
		memberDto.setJumin1(jumin1);
		memberDto.setJumin2(jumin2);
		memberDto.setEmail(email);
		memberDto.setZipcode(zipcode);
		memberDto.setAddress(address);
		memberDto.setJob(job);
		memberDto.setMailing(mailing);
		memberDto.setInterest(interest);
		memberDto.setMemberLevel("BB");
		
		logger.info(logMsg + memberDto.toString());
		
		MemberDao dao = MemberDao.getInstance();
		int check = dao.insert(memberDto);
		logger.info(logMsg + check);
		
		request.setAttribute("check", check);
	
		return "/WEB-INF/views/member/registerOk.jsp";
	}

}
