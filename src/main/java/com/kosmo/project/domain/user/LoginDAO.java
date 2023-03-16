package com.kosmo.project.domain.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.kosmo.project.dto.User;

@Component
public class LoginDAO {
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
	
	public String getInfo(User u) throws SQLException {
		String name=null;
		ResultSet rs;
		Connection conn=open();
		
		String sql = "select userid,userps,username from user where userid=? and userps=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try(conn;pstmt){
			pstmt.setString(1,u.getUserid());
			pstmt.setString(2,u.getUserps());

			rs = pstmt.executeQuery();
			if(rs.next()) {
				name = rs.getString("username");
			}else
				return name;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
}
