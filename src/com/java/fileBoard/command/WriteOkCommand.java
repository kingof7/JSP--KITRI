package com.java.fileBoard.command;



import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.command.Command;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//request만으로 못받으니까 아래 걸 써줌
		DiskFileItemFactory factory = new DiskFileItemFactory(); //파일보관 객체
		ServletFileUpload upload = new ServletFileUpload(factory);	//요청처리 객체
		
		List<FileItem> list = upload.parseRequest(request);
		Iterator<FileItem> iter = list.iterator();
		
		while(iter.hasNext()) {	// 11개
			FileItem fileItem = iter.next();
			
			if(fileItem.isFormField()) {
				String name = fileItem.getFieldName();
				logger.info(logMsg + name);
			}else {
				String name = fileItem.getFieldName();
				logger.info(logMsg + "binary: " + name + "," + fileItem.getName() + "," + fileItem.getSize());
			}
		}
		
		return "/WEB-INF/views/fileBoard/writeOk.jsp";
	}

}
