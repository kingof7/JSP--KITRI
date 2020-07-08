package com.java.board.model;

import java.util.Date;

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
	
	public BoardDto() {
		// TODO Auto-generated constructor stub
	}
	
	public BoardDto(int boardNumber, String writer, String subject, String email, String content, String password,
			Date writeDate, int readCount, int groupNumber, int sequenceNumber, int sequenceLevel) {
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

	@Override
	public String toString() {
		return "BoardDao [boardNumber=" + boardNumber + ", writer=" + writer + ", subject=" + subject + ", email="
				+ email + ", content=" + content + ", password=" + password + ", writeDate=" + writeDate
				+ ", readCount=" + readCount + ", groupNumber=" + groupNumber + ", sequenceNumber=" + sequenceNumber
				+ ", sequenceLevel=" + sequenceLevel + "]";
	}
	
}
