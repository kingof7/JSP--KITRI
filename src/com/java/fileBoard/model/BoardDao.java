package com.java.fileBoard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.java.database.ConnectionProvider;
import com.java.database.JdbcUtil;

public class BoardDao {
	//싱글톤 패턴
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	public int insert(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int value = 0;
		
		writeNumber(boardDto, conn);
		
		String sql = null;
		
		try {
			
			if(boardDto.getFileSize() == 0) {
				sql = "insert into board(board_number, writer, subject, email, content, password, write_date, read_count,"
						+ "group_number, sequence_number, sequence_level) " 
						+ "values(board_board_number_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boardDto.getWriter());
				pstmt.setString(2, boardDto.getSubject());
				pstmt.setString(3, boardDto.getEmail());
				pstmt.setString(4, boardDto.getContent().replace("\r\n", "<br/>"));
				pstmt.setString(5, boardDto.getPassword());
				
				//Date -> time -> Timestamp 변환
				/*
				 * Date date = boardDto.getWriteDate(); long time = date.getTime(); Timestamp ts
				 * = new Timestamp(time); pstmt.setTimestamp(5, ts);
				 */
				
				pstmt.setTimestamp(6, new Timestamp(boardDto.getWriteDate().getTime()));
				pstmt.setInt(7, boardDto.getReadCount());
				pstmt.setInt(8,  boardDto.getGroupNumber());
				pstmt.setInt(9,  boardDto.getSequenceNumber());
				pstmt.setInt(10, boardDto.getSequenceLevel());
				
				value = pstmt.executeUpdate();
			}else {
				sql = "insert into board(board_number, writer, subject, email, content, password, write_date, read_count,"
						+ "group_number, sequence_number, sequence_level, file_name, path, file_size) " 
						+ "values(board_board_number_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boardDto.getWriter());
				pstmt.setString(2, boardDto.getSubject());
				pstmt.setString(3, boardDto.getEmail());
				pstmt.setString(4, boardDto.getContent().replace("\r\n", "<br/>"));
				pstmt.setString(5, boardDto.getPassword());
				
				//Date -> time -> Timestamp 변환
				/*
				 * Date date = boardDto.getWriteDate(); long time = date.getTime(); Timestamp ts
				 * = new Timestamp(time); pstmt.setTimestamp(5, ts);
				 */
				
				pstmt.setTimestamp(6, new Timestamp(boardDto.getWriteDate().getTime()));
				pstmt.setInt(7, boardDto.getReadCount());
				pstmt.setInt(8,  boardDto.getGroupNumber());
				pstmt.setInt(9,  boardDto.getSequenceNumber());
				pstmt.setInt(10, boardDto.getSequenceLevel());
				pstmt.setString(11, boardDto.getFileName());
				pstmt.setString(12, boardDto.getPath());
				pstmt.setLong(13, boardDto.getFileSize());
				
				value = pstmt.executeUpdate();
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return value;		
	}
	
	public void writeNumber(BoardDto boardDto, Connection conn) {
		// 그룹번호, 글순서, 글레벨
		
		int boardNumber = boardDto.getBoardNumber(); // 0	
		int groupNumber = boardDto.getGroupNumber(); // 1
		int sequenceNumber = boardDto.getSequenceNumber(); // 0
		int sequenceLevel = boardDto.getSequenceLevel(); // 0
		String sql = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//루트글
			if(boardNumber == 0) {	// groupNumber, 0, 0 // ROOT : 그룹번호
				sql = "select max(group_number) from board";
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					int max = rs.getInt(1);// "max(group_number)" : 부모 게시물갯수
					boardDto.setGroupNumber(max+1); // 최고 그룹넘버 최대값에 1을 더함
				}
				
			}
			//자식글
			else {	// 답글 : 글순서, 글레벨
				
				// 시퀀스 넘버가 0보다큰(=이전에 작성한글들) 것들 1 씩 모두 증가
				sql = "update board set sequence_number = sequence_number + 1 " + "where group_number=? and sequence_number > ?";
				
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, groupNumber);
				pstmt.setInt(2, sequenceNumber);
				pstmt.executeUpdate();
								
				//본인 것 1증가
				sequenceNumber = sequenceNumber + 1;
				sequenceLevel = sequenceLevel + 1;
				
				boardDto.setSequenceNumber(sequenceNumber);
				boardDto.setSequenceLevel(sequenceLevel);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);			
		}
		
	}
	
	public int getCount() {
		String sql = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		int value = 0;
		
		try {
			sql = "select count(*) from board";
			
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) value = rs.getInt(1); // count(*)
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		return value;
	}
	
	public ArrayList<BoardDto> getBoardList(int startRow, int endRow) {
				
		ArrayList<BoardDto> boardList = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from "
					+ "(select rownum as rnum, a.* from (select * from board order by group_number desc, sequence_number asc) a) b "
					+ "where b.rnum >= ? and b.rnum <= ?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				BoardDto boardDto = new BoardDto();
				
				boardDto.setBoardNumber(rs.getInt("board_number"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setEmail(rs.getString("email"));
				boardDto.setContent(rs.getString("content"));
				
				boardDto.setPassword(rs.getString("password"));
				boardDto.setWriteDate(new Date(rs.getTimestamp("write_date").getTime())); //DB에 sysdate(timestamp) --> 자바의 Date로 바꾸기
				boardDto.setReadCount(rs.getInt("read_count"));
				boardDto.setGroupNumber(rs.getInt("group_number"));
				boardDto.setSequenceNumber(rs.getInt("sequence_number"));
				boardDto.setSequenceLevel(rs.getInt("sequence_level"));
				
				System.out.println(boardDto);
				
				boardList.add(boardDto);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return boardList;
	}
	
	public BoardDto read(int boardNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto boardDto = null;
		
		try {
			
			//조회수 올리는 쿼리
			conn = ConnectionProvider.getConnection();
			
			//오토커밋 취소
			conn.setAutoCommit(false);
			
			String sql = "update board set read_count = read_count + 1 where board_number = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			int value = pstmt.executeUpdate();
			if(value > 0) JdbcUtil.close(pstmt);
			
			
			String sqlSelect = "select * from board where board_number = ?";
			pstmt = conn.prepareStatement(sqlSelect);
			pstmt.setInt(1, boardNumber);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {				
				boardDto = new BoardDto();
				
				boardDto.setBoardNumber(rs.getInt("board_number"));
				boardDto.setWriter(rs.getString("writer"));
				boardDto.setSubject(rs.getString("subject"));
				boardDto.setEmail(rs.getString("email"));
				boardDto.setContent(rs.getString("content"));
				
				boardDto.setPassword(rs.getString("password"));
				boardDto.setWriteDate(new Date(rs.getTimestamp("write_date").getTime())); //DB에 sysdate(timestamp) --> 자바의 Date로 바꾸기
				boardDto.setReadCount(rs.getInt("read_count"));
				boardDto.setGroupNumber(rs.getInt("group_number"));
				boardDto.setSequenceNumber(rs.getInt("sequence_number"));
				boardDto.setSequenceLevel(rs.getInt("sequence_level"));
				
				boardDto.setFileName(rs.getString("file_name"));
				boardDto.setPath(rs.getString("path"));
				boardDto.setFileSize(rs.getLong("file_size"));
			}
			
			conn.commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			JdbcUtil.rollBack(conn);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		return boardDto;		
	}
	
	public BoardDto select(int boardNumber) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardDto boardDto = null;
		
		try {
							
			String sql = "select * from board where board_number = ?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {				
				boardDto = new BoardDto();								
				boardDto.setFileName(rs.getString("file_name"));
				boardDto.setPath(rs.getString("path"));
				boardDto.setFileSize(rs.getLong("file_size"));
			}
		
			
		}catch(Exception e) {
			e.printStackTrace();			
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		return boardDto;		
	}
	
	
	public int delete(int boardNumber, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;				
		int value = 0;
		
		
		try {
			String sql = "delete from board where board_number=? and password=?";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNumber);
			pstmt.setString(2, password);
			
			value=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);			
		}
		
		return value;
	}
	
	@SuppressWarnings("null")
	public int update(BoardDto boardDto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boardDto = null;
		int value = 0;
		try {
			
			conn = ConnectionProvider.getConnection();			
			
			String sqlUpdate = "update board set subject = ?, content = ? where board_number = ?";
			pstmt = conn.prepareStatement(sqlUpdate);
			pstmt.setString(1, boardDto.getSubject());
			pstmt.setString(2, boardDto.getContent());
			pstmt.setInt(3, boardDto.getBoardNumber());
			value = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		
		return value;		
	}	
	
}
