package com.ezenac.shop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.dao.MemberDao;
import com.ezenac.shop.dto.MemberVO;

public class FindPwStep1Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="member/findPwForm.jsp";
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		MemberDao mdao = MemberDao.getInstance();
		/*MemberVO mvo = mdao.getMemberByname(name,phone);
		
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		
		if(mvo==null) {
			request.setAttribute("msg", "해당 아이디의 회원이 없습니다.");
		}else if(mvo.getId().equals(id)) {
			request.setAttribute("id", id);
			//request.setAttribute("MemberVO", mvo);
			url = "member/findPwconfirmNumber.jsp";
		}
		*/
		
		MemberVO mvo = mdao.getMember(id);
		
		if(mvo==null) {
			request.setAttribute("msg", "해당 아이디의 회원이 없습니다.");
		}else if(!name.equals(mvo.getName())) {
			request.setAttribute("id", id);
			request.setAttribute("msg", "이름이 일치하지 않습니다.");
		}else if(!phone.equals(mvo.getPhone())) {
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("msg", "전화번호가 일치하지 않습니다.");
		}else {
			request.setAttribute("id", id);
			request.setAttribute("name", name);
			request.setAttribute("phone", phone);
			url = "member/findPwconfirmNumber.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}
