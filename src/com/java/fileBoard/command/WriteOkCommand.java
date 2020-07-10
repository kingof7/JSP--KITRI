package com.java.fileBoard.command;



import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.command.Command;
import com.java.fileBoard.model.BoardDto;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		//request만으로 못받으니까 아래 걸 써줌
		DiskFileItemFactory factory = new DiskFileItemFactory(); //파일보관 객체
		ServletFileUpload upload = new ServletFileUpload(factory);	//요청처리 객체
		
		List<FileItem> list = upload.parseRequest(request);
		Iterator<FileItem> iter = list.iterator();
		
		BoardDto boardDto = new BoardDto();
		HashMap<String, String> dataMap = new HashMap<String, String>();
		
		while(iter.hasNext()) {	// 11개
			FileItem fileItem = iter.next();
			
			if(fileItem.isFormField()) {
				/*
				 * String name = fileItem.getFieldName(); logger.info(logMsg + name);
				 */
				
				
				//WriteCommand.java->write.jsp에서 받는 name들
				//Map으로 할 수도 있음
				/*
				 * if(fileItem.getFieldName().equals("boardNumber")) { String boardNumber =
				 * fileItem.getString(); int num = Integer.parseInt(boardNumber);
				 * boardDto.setBoardNumber(Integer.parseInt(fileItem.getString()));
				 * 
				 * }
				 * 
				 * if(fileItem.getFieldName().equals("groupNumber")) {
				 * boardDto.setGroupNumber(Integer.parseInt(fileItem.getString()));
				 * 
				 * }
				 * 
				 * if(fileItem.getFieldName().equals("sequenceNumber")) {
				 * boardDto.setSequenceNumber(Integer.parseInt(fileItem.getString())); }
				 * 
				 * if(fileItem.getFieldName().equals("sequenceLevel")) {
				 * boardDto.setSequenceLevel(Integer.parseInt(fileItem.getString())); }
				 * 
				 * if(fileItem.getFieldName().equals("writer")) {
				 * boardDto.setWriter(fileItem.getString("utf-8")); }
				 * 
				 * if(fileItem.getFieldName().equals("subject")) {
				 * boardDto.setSubject(fileItem.getString("utf-8")); }
				 */
				
				//Map방식
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");
				logger.info(logMsg + name + "," + value);
				
				dataMap.put(name, value);
				
			}else {
				
				if(fileItem.getFieldName().equals("file")) {
					//파일명 fileItem.getName() / 파일사이즈 fileItem.getSize(), getInputStream()
					
					//파일(파일명)안올린 경우
					if(fileItem.getName() == null || fileItem.getName().equals("")) {
						continue; // 다시위로올라가서 제목,이메일등 불러오는 역할
					}
					
					upload.setFileSizeMax(1024*1024*10); //바이트*키로바이트*메가바이트 ex:) 1024*1=1kb // 1024*1024*1=1mb // 1024*1024*1024*1 = 1gb
					
					
					//경로명, File file <- dir, fileItem.getName() 추가 필요 (파일명이 계속 바뀌니까)
					String dir = "C:/LJH/mvc/workspace/MVCHomePage/WebContent/pds";
					
					//파일이 겹치지 않게 (초당 시간을 추가)
					String fileName = System.currentTimeMillis() + "_" + fileItem.getName();
					
					File file = new File(dir, fileName);
					
					//dir, fileName 합쳐주는 거
					String path = file.getAbsolutePath();
					
					logger.info(file.getAbsolutePath());
					logger.info(file.getCanonicalPath());
					
					//InputStream is = null;
					BufferedInputStream bis = null;					
					BufferedOutputStream bos = null;
					
					try {			
						//is = fileItem.getInputStream();
						bis = new BufferedInputStream(fileItem.getInputStream(), 1024);
						bos = new BufferedOutputStream(new FileOutputStream(file), 1024);
						//넘어온게 있으면
						while(true) {
							int data = bis.read();
							if(data == -1) break;
						}
					}catch(IOException e) {
						e.printStackTrace();
					}finally {
						if(bis != null) bis.close();
					}
					
					boardDto.setFileName(fileName);
					boardDto.setFileSize(fileItem.getSize());
					boardDto.setPath(path);
				}
				
				
				String name = fileItem.getFieldName();
				logger.info(logMsg + "binary: " + name + "," + fileItem.getName() + "," + fileItem.getSize());
			}
		}
		
		boardDto.setDataMap(dataMap);
		boardDto.setWriteDate(new Date());
		logger.info(logMsg + boardDto.toString());
		
		return "/WEB-INF/views/fileBoard/writeOk.jsp";
	}

}
