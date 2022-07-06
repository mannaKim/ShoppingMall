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
import com.ezenac.shop.util.Paging;

public class AdminProductListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/product/productList.jsp";
		HttpSession session = request.getSession();
		AdminVO avo = (AdminVO)session.getAttribute("loginAdmin");
		if(avo == null) url = "shop.do?command=admin";
		else {
			AdminDao adao = AdminDao.getInstance();
			
			Paging paging = new Paging();
			paging.setDisplayPage(5);
			paging.setDisplayRow(10);
			
			if(request.getParameter("page")!=null) {
				paging.setPage(Integer.parseInt(request.getParameter("page")));
				session.setAttribute("page",Integer.parseInt(request.getParameter("page")));
			}else if(session.getAttribute("page")!=null) {
				paging.setPage((Integer)session.getAttribute("page"));
			}else {
				paging.setPage(1);
				session.removeAttribute("page");
			}
			
			String key = "";
			
			if(request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			}else if(session.getAttribute("key")!=null) {
				key = (String)session.getAttribute("key");
			}else {
				session.removeAttribute("key");
			}
			
			int count = adao.getAllCount("product", key);
			paging.setTotalCount(count);
			
			ArrayList<ProductVO> productList = adao.listProduct(paging, key);
			request.setAttribute("productList", productList);
			request.setAttribute("paging", paging);
			request.setAttribute("key", key);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
