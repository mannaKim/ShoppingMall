package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.OrderDao;
import com.ezenac.shop.dto.MemberVO;

public class OrderInsertOneAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		
		//파라미터 저장
		int pseq = Integer.parseInt(request.getParameter("pseq"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		//로그인 체크
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			//pseq,quantity,로그인한 유저의 아이디를 이용해서
			//orders테이블과 order_detail 테이블에 레코드 추가
			//리턴되는 값은 추가된 주문번호(oseq)
			OrderDao odao = OrderDao.getInstance();
			int oseq = odao.insertOrderOne(pseq,quantity,mvo.getId());
			
			//추가된 주문번호를 이용하여 최종 목적지로 이동
			url = "shop.do?command=orderList&oseq="+oseq;
		}
		response.sendRedirect(url);

	}

}
