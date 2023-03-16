package com.kosmo.project.domain.post;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kosmo.project.dto.Post;

@Component
public class PostDAO {
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
	
	public List<Post> getAll() throws SQLException {
		Connection conn = open();
		
		// 포스트 값 전체 가져오기
		List<Post> postList = new ArrayList<>();
		String sql = "SELECT pid, address, title, content, img, loc FROM post";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		try (conn; ps; rs) {
			while (rs.next()) {
				Post p = new Post();
				p.setPid(rs.getInt("pid"));
				p.setAddress(rs.getString("address"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setImg(rs.getString("img"));
				p.setLoc(rs.getString("loc"));
				
				postList.add(p);
			}
		}
		return postList;
	}
	
	// 포스트 특정 값 가져오기
	public Post getPost(int pid) throws SQLException {
		Connection conn = open();
		
		Post p = new Post();
		String sql = "SELECT pid, address, title, content, img, loc FROM post WHERE pid = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, pid);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		try(conn; ps; rs) {
			System.out.println(rs);
			p.setPid(rs.getInt("pid"));
			p.setAddress(rs.getString("address"));
			p.setTitle(rs.getString("title"));
			p.setContent(rs.getString("content"));
			p.setImg(rs.getString("img"));
			p.setLoc(rs.getString("loc"));
			return p;
		}
	}
	
	// 포스트 특정 값 가져오기
	public List<Post> getList(String address) throws SQLException {
		Connection conn = open();
		
		// 포스트 값 전체 가져오기
		List<Post> postList = new ArrayList<>();
		String sql = "SELECT pid, address, title, content, img, loc FROM post WHERE address = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, address);
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		try (conn; ps; rs) {
			while (rs.next()) {
				Post p = new Post();
				p.setPid(rs.getInt("pid"));
				p.setAddress(rs.getString("address"));
				p.setTitle(rs.getString("title"));
				p.setContent(rs.getString("content"));
				p.setImg(rs.getString("img"));
				p.setLoc(rs.getString("loc"));
				
				postList.add(p);
			}
		}
		return postList;
	}
	
	// 포스트 추가
	public void addPost(Post p) throws SQLException {
		Connection conn = open();
		
		String sql = "INSERT INTO post(address, title, content, img, loc) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try(conn; ps) {
			ps.setString(1, p.getAddress());
			ps.setString(2, p.getTitle());
			ps.setString(3, p.getContent());
			ps.setString(4, p.getImg());
			ps.setString(5, p.getLoc());
			
			ps.executeUpdate();
		}
	}
	
	// 포스트 삭제
	public void delPost(int pid) throws SQLException {
		Connection conn = open();
		
		String sql = "DELETE FROM post WHERE pid=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		try (conn; ps) {
			ps.setInt(1, pid);
			if (ps.executeUpdate() == 0) {
				throw new SQLException("DB 삭제 에러");
			}
		}
	}
}
