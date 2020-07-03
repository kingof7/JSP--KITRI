package com.java.member.command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.command.Command;
import com.java.member.model.MemberDao;
import com.java.member.model.ZipcodeDto;

public class ZipcodeCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		String checkDong = request.getParameter("dong");
		logger.info(logMsg + checkDong);
		
		if(checkDong != null) {
			// DB
			// 내가원하는 select문으로 여러가지 컬럼을 가져와서 배열로 담을 수 있음, 한행을 가져옴(DTO)-ArrayList(여러개반복)
			//MemberDao.geArrayList<E>().zipcodeReader(checkDong);
			ArrayList<ZipcodeDto> zipcodeList = MemberDao.getInstance().zipcodeReader(checkDong);
			logger.info(logMsg + zipcodeList.size());
			request.setAttribute("zipcodeList", zipcodeList);
		}
		
				
		return "/WEB-INF/views/member/zipcode.jsp";
	}

}
