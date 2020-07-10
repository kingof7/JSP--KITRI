package com.java.fileBoard.model;

import java.util.Date;
import java.util.HashMap;

public class BoardDto {
	
	private int boardNumber; //자동 증가 번호
		
	//사용자에게 입력 받음
	private String writer;
	private String subject;
	private String email;
	private String content;
	private String password;
	
	private Date writeDate;
	private int readCount; // 조회수 
	private int groupNumber;
	private int sequenceNumber;
	private int sequenceLevel;
	
	private String fileName;
	private String path;
	private long fileSize;
	
	private HashMap<String, String> dataMap;
	
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardDto(int boardNumber, String writer, String subject, String email, String content, String password,
			Date writeDate, int readCount, int groupNumber, int sequenceNumber, int sequenceLevel, String fileName,
			String path, int fileSize) {
		super();
		this.boardNumber = boardNumber;
		this.writer = writer;
		this.subject = subject;
		this.email = email;
		this.content = content;
		this.password = password;
		this.writeDate = writeDate;
		this.readCount = readCount;
		this.groupNumber = groupNumber;
		this.sequenceNumber = sequenceNumber;
		this.sequenceLevel = sequenceLevel;
		this.fileName = fileName;
		this.path = path;
		this.fileSize = fileSize;
	}	

	public int getBoardNumber() {
		return boardNumber;
	}

	public void setBoardNumber(int boardNumber) {
		this.boardNumber = boardNumber;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public int getSequenceLevel() {
		return sequenceLevel;
	}

	public void setSequenceLevel(int sequenceLevel) {
		this.sequenceLevel = sequenceLevel;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public HashMap<String, String> getDataMap() {
		return dataMap;
	}

	public void setDataMap(HashMap<String, String> dataMap) {
		this.dataMap = dataMap;
		
		setBoardNumber(Integer.parseInt(dataMap.get("boardNumber")));
;		setGroupNumber(Integer.parseInt(dataMap.get("groupNumber")));
		setSequenceNumber(Integer.parseInt(dataMap.get("sequenceNumber")));
		setSequenceLevel(Integer.parseInt(dataMap.get("sequenceLevel")));	
		
		setWriter(dataMap.get("writer"));
		setEmail(dataMap.get("email"));
		setContent(dataMap.get("content"));
		setPassword(dataMap.get("password"));
		setSubject(dataMap.get("subject"));
		
	}

	@Override
	public String toString() {
		return "BoardDto [boardNumber=" + boardNumber + ", writer=" + writer + ", subject=" + subject + ", email="
				+ email + ", content=" + content + ", password=" + password + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", groupNumber=" + groupNumber + ", sequenceNumber=" + sequenceNumber
				+ ", sequenceLevel=" + sequenceLevel + ", fileName=" + fileName + ", path=" + path + ", fileSize="
				+ fileSize + ", dataMap=" + dataMap + "]";
	}

	

	
	
}
