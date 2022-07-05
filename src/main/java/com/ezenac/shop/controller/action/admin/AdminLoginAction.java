package com.ezenac.shop.controller.action.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.AdminDao;
import com.ezenac.shop.dto.AdminVO;

public class AdminLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workId = request.getParameter("workId");
		String workPwd = request.getParameter("workPwd");
		
		String url = "shop.do?command=admin";
		
		AdminDao adao = AdminDao.getInstance();
		AdminVO avo = adao.workerCheck(workId);
		
		if(avo==null) request.setAttribute("msg", "아이디가 없습니다.");
		else if(avo.getPwd()==null) request.setAttribute("msg", "DB오류. 관리자에게 문의하세요.");
		else if(!avo.getPwd().equals(workPwd)) request.setAttribute("msg", "비밀번호가 맞지 않습니다.");
		else {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", avo);
			url = "shop.do?command=adminProductList";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
