package com.ezenac.shop.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.dao.ProductDao;
import com.ezenac.shop.dto.ProductVO;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//베스트 상품과 신상품 목록을 조회하고, 
		ProductDao pdao = ProductDao.getInstance();
		ArrayList<ProductVO> bestList = pdao.getBestList();
		ArrayList<ProductVO> newList = pdao.getNewList();
		
		//request에 담아서 
		request.setAttribute("bestList", bestList);
		request.setAttribute("newList", newList);
		
		//main.jsp로 이동합니다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
		dispatcher.forward(request, response);
		
	}

}
