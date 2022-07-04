package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.OrderDao;
import com.ezenac.shop.dto.MemberVO;
import com.ezenac.shop.dto.OrderVO;

public class OrderDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		
		//전달된 주문번호 저장
		int oseq = Integer.parseInt(request.getParameter("oseq"));
		
		//로그인 체크
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			url = "mypage/orderDetail.jsp";
			
			//주문번호로 조회한 주문(상품) 리스트 리턴
			OrderDao odao = OrderDao.getInstance();
			ArrayList<OrderVO> orderList = odao.listOrderByOseq(oseq);
			//리퀘스트에 주문리스트를 담습니다.
			request.setAttribute("orderList", orderList);
			
			//리턴받은 주문리스트의 첫번째를 이용하여 orderDetail에 저장
			OrderVO orderDetail = orderList.get(0);
			//주문 총액을 계산하고 orderDetail의 price2에 저장
			//따로 totalPrice를 리퀘스트에 담는 방법도 있음 -> request.setAttribute("totalPrice", totalPrice);
			int totalPrice = 0;
			for(OrderVO ovo : orderList)
				totalPrice += ovo.getPrice2()*ovo.getQuantity();
			orderDetail.setPrice2(totalPrice);
			//리퀘스트에 orderDetail을 담습니다.
			request.setAttribute("orderDetail", orderDetail);
		}
		
		//이동
		request.getRequestDispatcher(url).forward(request, response);
	}

}
