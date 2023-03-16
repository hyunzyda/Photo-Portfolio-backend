package com.kosmo.project.domain.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.kosmo.project.dto.User;

@Component
public class JoinDAO {
	   final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
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
	
	public void addInfo(User u) throws SQLException{
		Connection conn=open();
		String sql="insert into user values(?,?,?,?,?)";
		PreparedStatement pstmt=conn.prepareStatement(sql);
		
		try(conn;pstmt){
			pstmt.setString(1, u.getUserid());
			pstmt.setString(2, u.getUserps());
			pstmt.setString(3, u.getUsername());
			pstmt.setString(4, u.getGender());
			pstmt.setString(5, u.getEmail());
			pstmt.executeUpdate();
		}
	}
	
	
}
