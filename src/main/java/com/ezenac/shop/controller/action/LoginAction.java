package com.ezenac.shop.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.dao.MemberDao;
import com.ezenac.shop.dto.MemberVO;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//getMember 메서드를 만들고, 상황에 맞는 if문을 이용해서 로그인을 구현하세요
		//최종 목적지는 main.jsp입니다.
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String url = "member/login.jsp";//"shop.do?command=loginForm";
		MemberDao mdao = MemberDao.getInstance();
		MemberVO mvo = mdao.getMember(id);
		
		if(mvo==null) {
			request.setAttribute("message", "일치하는 아이디가 없습니다.");
		}else if(mvo.getPwd()==null) {
			request.setAttribute("message", "시스템 오류. 관리자에게 문의하세요.");
		}else if(!mvo.getPwd().equals(pwd)) {
			request.setAttribute("message", "비밀번호가 틀렸습니다.");
		}else if(mvo.getUseyn().equals("n")) {
			request.setAttribute("message", "휴면계정입니다. 사이트를 이용하려면 관리자에게 문의하세요.");
		}else if(mvo.getPwd().equals(pwd)) {
			url = "shop.do?command=index";
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", mvo);
		}else {
			request.setAttribute("message", "시스템 오류. 관리자에게 문의하세요.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

}
