package com.kosmo.project.domain.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kosmo.project.dto.Board;

@Component
public class BoardDAO {
	   final String JDBC_DRIVER = "org.mariadb.jdbc.Driverdddddddddddd";
	   final String DB_IP = "springboot-db.cfv6476o3rzm.ap-northeast-2.rds.amazonaws.com";
	   final String DB_PORT = "3306";
	   final String DB_NAME = "project";
	   final String DB_URL = "jdbc:mariadb://" + DB_IP + ":" + DB_PORT + "/" + DB_NAME;

	   public Connection open() {
	      Connection conn = null;
	      try {
	         Class.forName(JDBC_DRIVER);
	         conn = DriverManager.getConnection(DB_URL, "user", "Kosmo2023*");
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return conn;
	   }
	
	public void addWrite(Board b) throws Exception {
		Connection conn=open();
		
		String sql = "insert into board(title,content) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn;pstmt){
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContent());
			pstmt.executeUpdate();
		}
	}

	public void delBoard(int bid) throws SQLException {
		Connection conn = open();
		
		String sql = "delete from board where bid=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn; pstmt) {
			pstmt.setInt(1, bid);
			//삭제된 뉴스 기사가 없을 경우
			if(pstmt.executeUpdate() == 0) {
				throw new SQLException("DB 삭제 에러");
			}
		}
		
	}

	public List<Board> getAll() throws Exception {
		Connection conn = open();
		List<Board> boardList = new ArrayList<>();
		String sql = "select bid, title, to_char(bdate, 'yyyy-MM-dd hh:mm:ss') as cdate, content from board";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		try(conn; pstmt; rs) {
			while(rs.next()) {
				Board b = new Board();
				b.setBid(rs.getInt("bid"));
				b.setTitle(rs.getString("title"));
				b.setDate(rs.getString("cdate"));
				b.setContent(rs.getString("content"));
				
				boardList.add(b);
			}
			return boardList;			
		}
	}


	public Board getBoard(int bid) throws SQLException {
		Connection conn = open();
		
		Board b = new Board();
		String sql = "select bid, title, to_char(bdate, 'yyyy-MM-dd hh:mm:ss') as cdate, ";
		       sql += " content from board where bid = ? ";  //sql = sql + "    ";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bid);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
		try(conn; pstmt; rs) {

				b.setBid(rs.getInt("bid"));
				b.setTitle(rs.getString("title"));
				b.setDate(rs.getString("cdate"));
				b.setContent(rs.getString("content"));
				pstmt.executeQuery();

			return b;
		}		
	}
}
