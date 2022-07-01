package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.CartDao;
import com.ezenac.shop.dao.OrderDao;
import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.MemberVO;

public class OrderInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "";
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			//주문자 아이디로 검색한 카트 목록(지금 주문 처리할) 목록을 먼저 조회합니다
			CartDao cdao = CartDao.getInstance();
			ArrayList<CartVO> list = cdao.selectCart(mvo.getId());
			
			//추출한 list와 주문자의 아이디를 갖고 OrderDao에 가서 오더와 오더디테일에 데이터 추가
			OrderDao odao = OrderDao.getInstance();
			//주문 추가 후 추가한 주문의 주문번호를 리턴받습니다.
			int oseq = odao.insertOrder(list, mvo.getId());
			
			//방금 주문에 성공한 주문번호를 갖고 오더리스트로 이동하여 주문번호로 주문내역을 다시 조회
			url = "shop.do?command=orderList&oseq="+oseq;
		}
		response.sendRedirect(url);
	}

}
