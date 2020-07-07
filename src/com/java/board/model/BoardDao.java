package com.java.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

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
		
		try {
			String sql = "insert into board " + "values(board_board_number_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionProvider.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, boardDto.getWriter());
			pstmt.setString(2, boardDto.getSubject());
			pstmt.setString(3, boardDto.getEmail());
			pstmt.setString(4, boardDto.getContent());
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
				sql = "update board set sequence_number = sequence_number + 1 " + "where group_number=? and sequence_number > 0";
				
				conn = ConnectionProvider.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, groupNumber);
				pstmt.setInt(2, sequenceNumber);
								
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
				
		ArrayList<BoardDto> boardList = null;
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
				BoardDto board = new BoardDto();
				
				boardList.add(board);
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
}
