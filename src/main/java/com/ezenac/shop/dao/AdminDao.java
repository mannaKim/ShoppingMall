package com.ezenac.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.shop.dto.AdminVO;
import com.ezenac.shop.dto.ProductVO;
import com.ezenac.shop.dto.QnaVO;
import com.ezenac.shop.util.Dbman;
import com.ezenac.shop.util.Paging;

public class AdminDao {
	private AdminDao() {}
	private static AdminDao itc = new AdminDao();
	public static AdminDao getInstance() {return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public AdminVO workerCheck(String workId) {
		AdminVO avo = null;
		String sql = "select * from worker where id=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, workId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				avo = new AdminVO();
				avo.setId(workId);
				avo.setPwd(rs.getString("pwd"));
				avo.setName(rs.getString("name"));
				avo.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
		return avo;
	}

	public ArrayList<ProductVO> listProduct(Paging paging, String key) {
		ArrayList<ProductVO> list = new ArrayList<ProductVO>();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, p.* from "
				+ "((select * from product where name like '%'||?||'%' order by pseq desc) p)"
				+ ") where rn>=?"
				+ ") where rn<=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setInt(2, paging.getStartNum());
			pstmt.setInt(3, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setName(rs.getString("name"));
				//pvo.setKind(rs.getString("kind"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				//pvo.setContent(rs.getString("content"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				pvo.setIndate(rs.getTimestamp("indate"));
				list.add(pvo);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
		return list;
	}

	public int getAllCount(String tableName, String key) {
		int count = 0;
		String sql="select count(*) as cnt from " + tableName
				+ " where name like '%'||?||'%'";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next()) count = rs.getInt("cnt");
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
		return count;
	}

	public ArrayList<QnaVO> listQna(Paging paging) {
		ArrayList<QnaVO> list = new ArrayList<QnaVO>();
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, q.* from "
				+ "((select * from qna order by qseq desc) q)"
				+ ") where rn>=?"
				+ ") where rn<=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, paging.getStartNum());
			pstmt.setInt(2, paging.getEndNum());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QnaVO qvo = new QnaVO();
				qvo.setQseq(rs.getInt("qseq"));
				qvo.setSubject(rs.getString("subject"));
				qvo.setContent(rs.getString("content"));
				qvo.setId(rs.getString("id"));
				qvo.setRep(rs.getString("rep"));
				qvo.setIndate(rs.getTimestamp("indate"));
				list.add(qvo);
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
		return list;
	}

	public void inserProduct(ProductVO pvo) {
		String sql = "insert into product(pseq,kind,name,price1,price2,price3,content,image)"
				+ " values(product_seq.nextVal,?,?,?,?,?,?,?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());
			pstmt.setString(2, pvo.getName());
			pstmt.setInt(3, pvo.getPrice1());
			pstmt.setInt(4, pvo.getPrice2());
			pstmt.setInt(5, pvo.getPrice3());
			pstmt.setString(6, pvo.getContent());
			pstmt.setString(7, pvo.getImage());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
	}

	public void updateProduct(ProductVO pvo) {
		String sql = "update product"
				+ " set kind=?, name=?, price1=?, price2=?, price3=?,"
				+ " content=?, useyn=?, bestyn=?, image=?"
				+ " where pseq=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pvo.getKind());
			pstmt.setString(2, pvo.getName());
			pstmt.setInt(3, pvo.getPrice1());
			pstmt.setInt(4, pvo.getPrice2());
			pstmt.setInt(5, pvo.getPrice3());
			pstmt.setString(6, pvo.getContent());
			pstmt.setString(7, pvo.getUseyn());
			pstmt.setString(8, pvo.getBestyn());
			pstmt.setString(9, pvo.getImage());
			pstmt.setInt(10, pvo.getPseq());
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);}
	}
	
	
}
