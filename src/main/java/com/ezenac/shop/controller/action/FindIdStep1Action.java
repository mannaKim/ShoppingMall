package com.ezenac.shop.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezenac.shop.dao.MemberDao;
import com.ezenac.shop.dto.MemberVO;

public class FindIdStep1Action implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//이 단계에서 인증번호를 해당 휴대폰번호에 문자로 전송하고 다음 페이지에서 사용자에게 입력받도록
		//화면을 구성합니다.

		String url="member/findIdForm.jsp";
		String name=request.getParameter("name");
		String phone=request.getParameter("phone");
		
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMemberByname(name,phone);
		//이름과 전화번호로 검색해서 일치하는 회원이 있으면 인증번호 입력창으로 이동
		//일치하는 회원이 없으면 이름과 전화번호 입력창으로 돌아갑니다.
		
		request.setAttribute("name", name);
		request.setAttribute("phone", phone);
		
		if(mvo==null) {
			request.setAttribute("msg", "해당 이름과 전화번호의 회원이 없습니다.");
		}else {
			request.setAttribute("MemberVO", mvo);
			url = "member/findIdconfirmNumber.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
