package com.ezenac.shop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.dto.MemberVO;

public class FindIdStep2Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String confirmNum = request.getParameter("confirmNum");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		request.setAttribute("id", id);
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		
		String url="member/viewId.jsp";
		
		if(!confirmNum.equals("0000")) {
			request.setAttribute("msg", "인증번호가 맞지 않습니다.");
			url="member/findIdconfirmNumber.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
