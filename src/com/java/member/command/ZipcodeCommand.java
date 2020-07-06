package com.java.member.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.ZipcodeDto;

public class ZipcodeCommand implements Command{
	
	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		
		String checkDong = request.getParameter("dong");
		logger.info(logMsg + checkDong);
		if (checkDong != null) {
			// DB 연결
			MemberDao dao = MemberDao.getInstance();
			List<ZipcodeDto> zipcodeList = dao.zipcodeReader(checkDong);
			logger.info(logMsg + zipcodeList.size());
			
			request.setAttribute("zipcodeList", zipcodeList);
		}
		
		return "/WEB-INF/views/member/zipcode.jsp";
	}
	
}
