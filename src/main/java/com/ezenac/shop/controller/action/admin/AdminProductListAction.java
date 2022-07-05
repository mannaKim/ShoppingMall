package com.ezenac.shop.controller.action.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.AdminDao;
import com.ezenac.shop.dto.AdminVO;
import com.ezenac.shop.dto.ProductVO;

public class AdminProductListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/product/productList.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if(avo == null) url = "shop.do?command=admin";
		else {
			AdminDao adao = AdminDao.getInstance();
			ArrayList<ProductVO> productList = adao.listProduct();
			request.setAttribute("productList", productList);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
