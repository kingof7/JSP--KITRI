package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.command.Command;
import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;

public class WriteOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("utf-8");

		// request만으로 못받으니까 아래 걸 써줌
		// DiskFileItemFactory factory = new DiskFileItemFactory(); //파일보관 객체
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory); // 요청처리 객체

		List<FileItem> list = upload.parseRequest(request);
		Iterator<FileItem> iter = list.iterator();

		BoardDto boardDto = new BoardDto();
		HashMap<String, String> dataMap = new HashMap<String, String>();

		while (iter.hasNext()) { // 11개
			FileItem fileItem = iter.next();

			if (fileItem.isFormField()) {
				/*
				 * String name = fileItem.getFieldName(); logger.info(logMsg + name);
				 */

				// WriteCommand.java->write.jsp에서 받는 name들
				// Map으로 할 수도 있음
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

				String name = fileItem.getFieldName(); // 위에 분기 방식을 Map방식으로 간편하게 처리한다.
				String value = fileItem.getString("utf-8");
				logger.info(logMsg + name + "," + value);

				dataMap.put(name, value);

			} else {
//	            String name = fileItem.getFieldName();
//	            logger.info(logMsg + "binary " + name + "," + fileItem.getName() + "," + fileItem.getSize()); // 파일네임 체크
				if (fileItem.getFieldName().equals("file")) {
					// 파일명 getName() , 파일사이즈 getSize(), 파일정보 getInputStream()
					if (fileItem.getName() == null || fileItem.getName().equals(""))
						continue; // 파일 불러오기가 비워져 있으면? continue;

					upload.setFileSizeMax(1024 * 1024 * 10); // 파일 최대 크기 byte * kb * mb * gb
					String fileName = System.currentTimeMillis() + "_" + fileItem.getName();

					// 내 서버 절대경로
					// String dir ="C:/LJH/mvc/workspace/MVCHomePage/WebContent/pds";

					// 실제 톰캣 서버 경로
//	               String dir = request.getServletContext().getRealPath("\\pds\\");
//	               logger.info(logMsg + dir);

					File dir = new File("C:\\pds\\");
					dir.mkdir();
					File file = null;
					if (dir.exists() && dir.isDirectory()) {
						file = new File(dir, fileName);
					}

					BufferedInputStream bis = null;
					BufferedOutputStream bos = null;

					try { // 파일을 서버에 저장 하는 부분
						bis = new BufferedInputStream(fileItem.getInputStream(), 1024);
						bos = new BufferedOutputStream(new FileOutputStream(file), 1024);
						while (true) {
							int data = bis.read();
							if (data == -1)
								break;

							bos.write(data);
						}
						bos.flush();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						if (bis != null)
							bis.close();
					}

					boardDto.setFileName(fileName);
					boardDto.setFileSize(fileItem.getSize());
					boardDto.setPath(file.getAbsolutePath());

				}

				String name = fileItem.getFieldName();
				logger.info(logMsg + "binary: " + name + "," + fileItem.getName() + "," + fileItem.getSize());
			}
		}

		boardDto.setDataMap(dataMap);
		boardDto.setWriteDate(new Date());
		logger.info(logMsg + boardDto.toString());

		int check = BoardDao.getInstance().insert(boardDto);
		logger.info(logMsg + check);

		request.setAttribute("check", check);

		return "/WEB-INF/views/fileBoard/writeOk.jsp";

	}

}
