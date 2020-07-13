package com.java.fileBoard.command;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.java.fileBoard.model.BoardDao;
import com.java.fileBoard.model.BoardDto;
import com.java.command.Command;

public class UpdateOkCommand implements Command {

	@Override
	public String proRequest(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// update.jsp에서 여기로 넘어옴
		// 한글 인코딩
		request.setCharacterEncoding("utf-8");
		// pageNumber는 boardDto의 필드가 아니므로 int로 받음
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		List<FileItem> list = upload.parseRequest(request);

		Iterator<FileItem> iter = list.iterator();
		BoardDto boardDto = new BoardDto();
		Map<String, String> dataMap = new HashMap<>();

		int fileUp = 0; // 파일 수정 or 삭제되었는지 판단
		String fileDel = null; // 파일이 비어있는지 판단

		while (iter.hasNext()) { //한 태그씩 넘어감 (인풋태그~~->파일태그~->끝)
			FileItem fileItem = iter.next();

			if (fileItem.isFormField()) { // 바이너리 문자열 확인 (파일이아닌것)
				String name = fileItem.getFieldName();
				String value = fileItem.getString("utf-8");
				logger.info(logMsg + name + "," + value);
				dataMap.put(name, value);

			} else {
				if (fileItem.getFieldName().equals("file")) { //파일이면
					
					if (fileItem.getName() == null || fileItem.getName().equals(""))
						continue; // 파일 불러오기가 비워져 있으면?  continue; -> 마지막 태그일 경우 while문 빠져나감

					fileDel = fileItem.getFieldName(); // 파일이 비어있지 않았다는 뜻
					logger.info(logMsg + "fileDel-------: " + fileDel); // 파일이 존재한다면 file이 로그에 찍힘
					upload.setFileSizeMax(1024 * 1024 * 10); // 파일 최대 크기 byte * kb * mb * gb

					String fileName = System.currentTimeMillis() + "_" + fileItem.getName();
					// 내 서버 절대경로
					String dir = "C:/JAVA/MVCHomePage/WebContent/pds/";

					File file = new File(dir, fileName);
					BufferedInputStream bis = null;
					BufferedOutputStream bos = null;
					try { // 파일을 서버에 저장 하는 부분 WriteOkCommand를 기반으로 작성.

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
					
					//파일 수정이 일어날 경우 요기 안에서 수정됨
					boardDto.setFileName(fileName);
					boardDto.setFileSize(fileItem.getSize());
					boardDto.setPath(file.getAbsolutePath());
					fileUp = 1; // dao에서 수정글인지 판단하기위한 변수1를 넣어준다.

				}
			}
		}
		
		
		// 사용자가 파일삭제를 눌렀을 경우 이 로직이 실행된다.
		System.out.println("sjflkasjdlkfjlksdjf=====" + fileDel);
		
		// 파일이 삭제된경우		
		if (dataMap.get("fileDelete").equals("1")) {
			boardDto.setFileName(null);
			boardDto.setFileSize(0);
			boardDto.setPath(null);
			fileUp = 1; //파일 삭제시에도 1을 셋팅
		}
		// 변경된 게시글의 제목,내용,비밀번호를 set한다.
		// UPDATE문 실행을 위한 boardNumber값도 set
		// 여기서는 파일 수정된 값 셋팅 안해준다, 왜냐하면 위에서 이미 다했기 때문에, 90~93 lines
		boardDto.setSubject(dataMap.get("subject"));
		boardDto.setContent(dataMap.get("content"));
		boardDto.setPassword(dataMap.get("password"));
		boardDto.setBoardNumber(Integer.parseInt(dataMap.get("boardNumber")));
		logger.info(boardDto.toString());
		BoardDao dao = BoardDao.getInstance();
		int check = dao.update(boardDto, fileUp);
		logger.info(logMsg + "check : " + check);
		
		request.setAttribute("check", check);
		request.setAttribute("boardNumber", dataMap.get("boardNumber"));
		request.setAttribute("pageNumber", dataMap.get("pageNumber"));
		
		return "/WEB-INF/views/fileBoard/updateOk.jsp";
	}

}
