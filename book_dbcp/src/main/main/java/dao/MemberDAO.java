package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import dto.BookDTO;
import dto.ChangeDTO;
import dto.MemberDTO;

public class MemberDAO {

	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;

//	static {
//		// 드라이버 로드
//		try {
//			Class.forName("oracle.jdbc.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}

	public Connection getConnection(){
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
			con = ds.getConnection();
		} catch (Exception e) {			
			e.printStackTrace();
		}
		return con;
	}

	public void close(Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close(Connection con, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// CRUD 메소드

	// R(Read) - 전체조회, 특정(pk) 조회, 제목 조회,로그인
	// 조회 메소드 작성하기
	// 리턴 타입 : List<~~~~DTO> or ~~~DTO ==> sql 구문 보고 결정
	// List<~~~~DTO> : where 절 없는 경우, where 절이 pk 가 아니면
	// ~~~DTO : where 절이 pk 인 경우

	// 전달인자 : () 에 어떻게 작성할 것인가?
	// sql 구문의 ? 보고 결정
	public MemberDTO isLogin(MemberDTO loginDto) {
		
		MemberDTO dto = null;
		
		try {
			
			con = getConnection();
			String sql = "SELECT * FROM membertbl WHERE userid=? AND password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginDto.getUserid());
			pstmt.setString(2, loginDto.getPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new MemberDTO();
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setName(rs.getString("name"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return dto;
	}
	
	public boolean dupId(String userid) {		
		
		try {
			
			con = getConnection();
			String sql = "SELECT * FROM membertbl WHERE userid=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			if (rs.next()) { // 아이디 있음
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt, rs);
		}
		return true; // 중복 아이디 없음
	}
	
	

	// U(Update) - 수정
	// 수정 메소드 작성하기
	// 리턴타입 : int
	public int update(ChangeDTO changeDto) {
		int updateRow = 0;
		try {
			
			con = getConnection();
			String sql="UPDATE MEMBERTBL SET PASSWORD = ? WHERE USERID = ? AND PASSWORD = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, changeDto.getChangePassword());
			pstmt.setString(2, changeDto.getUserid());
			pstmt.setString(3, changeDto.getCurrentPassword());
			
			updateRow = pstmt.executeUpdate();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		return updateRow;
	}
	
	
	
	// D(Delete) - 삭제
	// 삭제 메소드 작성하기
	// 리턴타입 : int
	

	// C(Create) - 삽입(회원가입)
	// 삽입 메소드 작성하기
	// 리턴타입 : int
	public int insert(MemberDTO insertDto) {
		int insertRow = 0;
		try {
			con = getConnection();
			String sql = "INSERT INTO membertbl(userid,name,password) VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, insertDto.getUserid());
			pstmt.setString(2, insertDto.getName());
			pstmt.setString(3, insertDto.getPassword());
			
			insertRow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(con, pstmt);
		}
		return insertRow;
	}
	
}












