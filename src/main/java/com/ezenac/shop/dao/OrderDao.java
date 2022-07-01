package com.ezenac.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.OrderVO;
import com.ezenac.shop.util.Dbman;

public class OrderDao {
	private OrderDao() {}
	private static OrderDao itc = new OrderDao();
	public static OrderDao getInstance() {return itc;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int insertOrder(ArrayList<CartVO> list, String id) {
		int oseq = 0;
		con = Dbman.getConnection();
		//각 단계별 DB작업이 끝날 때 마다 pstmt.close()로 끊고 다시 시작
		//1. 주문번호(시퀀스자동입력)와 구매자 아이디로 orders테이블에 레코드 추가
		String sql = "insert into orders(oseq,id) values(orders_seq.nextVal,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			//2. orders테이블에 시퀀스로 입력된 가장 마지막(방금추가한)주문 번호 조회
			sql = "select max(oseq) as max_oseq from orders";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) oseq = rs.getInt("max_oseq");
			pstmt.close();
			
			//3. list의 카트 목록들을 orders에서 얻은 max_oseq와 함께 order_detail에 추가
			//반복실행을 이용하여 카트 목록을 하나씩 꺼내서 oseq와 함께 order_detail 테이블에 추가
			//odseq, quantity, result, oseq, pseq 
			sql = "insert into order_detail(odseq,quantity,oseq,pseq)"
					+ " values(order_detail_seq.nextVal,?,?,?)";
			for(CartVO cvo : list) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cvo.getQuantity());
				pstmt.setInt(2,oseq);
				pstmt.setInt(3, cvo.getPseq());
				pstmt.executeUpdate();
				pstmt.close();
			}
			
			//4. order_detail에 추가된 카트내용은 cart테이블에서 처리되었으므로 삭제 또는 result를 2로 변경
			//sql = "delete from cart where cseq=?";
			sql = "update cart set result='2' where cseq=?";
			for(CartVO cvo : list) {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1,cvo.getCseq());
				pstmt.executeUpdate();
				pstmt.close();
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return oseq;
	}

	public ArrayList<OrderVO> listOrderByOseq(int oseq) {
		ArrayList<OrderVO> list = new ArrayList<OrderVO>();
		
		String sql = "select * from order_view where oseq=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oseq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				OrderVO ovo = new OrderVO();
				//d.odseq, o.oseq, o.indate, o.id,
				//m.name as mname, m.zip_num, m.address1, m.address2, m.phone,
				//d.pseq, p.name as pname, p.price2, d.quantity, d.result
				ovo.setOdseq(rs.getInt("odseq"));
				ovo.setOseq(rs.getInt("oseq"));
				ovo.setIndate(rs.getTimestamp("indate"));
				ovo.setId(rs.getString("id"));
				ovo.setMname(rs.getString("mname"));
				ovo.setZip_num(rs.getString("zip_num"));
				ovo.setAddress1(rs.getString("address1"));
				ovo.setAddress2(rs.getString("address2"));
				ovo.setPhone(rs.getString("phone"));
				ovo.setPseq(rs.getInt("pseq"));
				ovo.setPname(rs.getString("pname"));
				ovo.setPrice2(rs.getInt("price2"));
				ovo.setQuantity(rs.getInt("quantity"));
				ovo.setResult(rs.getString("result"));
				list.add(ovo);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		
		return list;
	}
}
