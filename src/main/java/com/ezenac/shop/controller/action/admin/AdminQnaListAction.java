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
import com.ezenac.shop.dto.QnaVO;
import com.ezenac.shop.util.Paging;

public class AdminQnaListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/qna/qnaList.jsp";
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
			int count = adao.getAllCount("qna",key);
			paging.setTotalCount(count);
			
			ArrayList<QnaVO> qnaList = adao.listQna(paging);
			request.setAttribute("qnaList", qnaList);
			request.setAttribute("paging", paging);
		}
		request.getRequestDispatcher(url).forward(request, response);

	}

}
