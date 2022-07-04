package com.ezenac.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QnaDao {
	private QnaDao(){}
	private static QnaDao ist = new QnaDao();
	public static QnaDao getInstance() {return ist;}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
}
