package com.javagyeongmin.MVCboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.javagyeongmin.MVCboard.dto.BDto;

public class BDao {

	DataSource dataSource;

	public BDao() {
		super();
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<BDto> list() { // 리스트가 반환 -> 글들 여러개라 배열로 반환 -> 글 하나하나 dto -> Bdto
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // ResultSet은 셀럭트 하려고 설정하는거임 나머지 두개로는 업데이트 가능 
		// 셀렉트는 결과를 반환해준다 (sql문 기본 4개 : SELECT, INSERT, UPDATE, DELETE )
		
		try {
			conn = dataSource.getConnection();
			String query = "SELECT * FROM MVC_board order by bGroup desc, bStep asc"; // desc : 내림차순 / asc : 오름차순 / order : 정렬
			pstmt = conn.prepareStatement(query); // sql문 실행
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // next() : 다음 데이터 있으면 참 / 없으면 거짓 
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				dtos.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}	
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		return dtos;
		
	}
	
	
	public void write(String bName, String bTitle, String bContent) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0)";
		
		try {
			conn = dataSource.getConnection(); // 연결 만들고
			pstmt = conn.prepareStatement(query); // sql문 실행시키고
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			
			int dbFlag = pstmt.executeUpdate(); // 성공이면 1 반환
			System.out.println(dbFlag);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // try/catch문 한번 더 들어가야됨
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
	public BDto contentView(String strId) {
		
		upHit(strId);
		
		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "SELECT * FROM MVC_board where bId=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strId)); // 문자열인 strId를 int로 변환
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // next() : 다음 데이터 있으면 참 / 없으면 거짓 
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}	
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
		
	}
	
	// 수정
	public void modify(String bId, String bName, String bTitle, String bContent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		String query = "UPDATE mvc_board SET bName=?, bTitle=?, bContent=? where bId=?";
		
		try {
			conn = dataSource.getConnection(); // 연결 만들고
			pstmt = conn.prepareStatement(query); // sql문 실행시키고
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bId));
			
			int dbFlag = pstmt.executeUpdate(); // 성공이면 1 반환
			System.out.println(dbFlag);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // try/catch문 한번 더 들어가야됨
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	// 삭제
	public void delete(String bId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		String query = "delete from mvc_board where bId=?";
		
		try {
			conn = dataSource.getConnection(); // 연결 만들고
			pstmt = conn.prepareStatement(query); // sql문 실행시키고
			
			pstmt.setInt(1, Integer.parseInt(bId));
			
			int dbFlag = pstmt.executeUpdate(); // 성공이면 1 반환
			System.out.println(dbFlag);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // try/catch문 한번 더 들어가야됨
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public BDto reply_view(String strId) {
		
		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "SELECT * FROM MVC_board where bId=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(strId)); // 문자열인 strId를 int로 변환
			rs = pstmt.executeQuery();
			
			while(rs.next()) { // next() : 다음 데이터 있으면 참 / 없으면 거짓 
				int bId = rs.getInt("bId");
				String bName = rs.getString("bName");
				String bTitle = rs.getString("bTitle");
				String bContent = rs.getString("bContent");
				Timestamp bDate = rs.getTimestamp("bDate");
				int bHit = rs.getInt("bHit");
				int bGroup = rs.getInt("bGroup");
				int bStep = rs.getInt("bStep");
				int bIndent = rs.getInt("bIndent");
				
				dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}	
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return dto;
		}
	
	public void reply(String bId, String bName, String bTitle, String bContent, String bGroup, String bStep, String bIndent) {
		
		replyShape(bGroup, bStep);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "INSERT INTO mvc_board(bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) VALUES (mvc_board_seq.nextval, ?, ?, ?, 0, ?, ?, ?)";
		
		try {
			conn = dataSource.getConnection(); // 연결 만들고
			pstmt = conn.prepareStatement(query); // sql문 실행시키고
			
			pstmt.setString(1, bName);
			pstmt.setString(2, bTitle);
			pstmt.setString(3, bContent);
			pstmt.setInt(4, Integer.parseInt(bGroup));
			pstmt.setInt(5, Integer.parseInt(bStep)+1);
			pstmt.setInt(6, Integer.parseInt(bIndent)+1);
			
			
			int dbFlag = pstmt.executeUpdate(); // 성공이면 1 반환
			System.out.println(dbFlag);
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // try/catch문 한번 더 들어가야됨
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void replyShape(String strGroup, String strStep) {
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE mvc_board SET bStep = bStep + 1 WHERE bGroup = ? and bStep > ?";
		
		try {
			conn = dataSource.getConnection(); // 연결 만들고
			pstmt = conn.prepareStatement(query); // sql문 실행시키고
			
			pstmt.setInt(1, Integer.parseInt(strGroup));
			pstmt.setInt(2, Integer.parseInt(strStep));
			
			
			int dbFlag = pstmt.executeUpdate(); // 성공이면 1 반환
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // try/catch문 한번 더 들어가야됨
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void upHit(String strId) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String query = "UPDATE mvc_board SET bHit = bHit + 1 WHERE bId = ?";
		
		try {
			conn = dataSource.getConnection(); // 연결 만들고
			pstmt = conn.prepareStatement(query); // sql문 실행시키고
			
			pstmt.setInt(1, Integer.parseInt(strId));
			
			
			int dbFlag = pstmt.executeUpdate(); // 성공이면 1 반환
			
		}catch(Exception e){
			e.printStackTrace();
		}finally { // try/catch문 한번 더 들어가야됨
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}


