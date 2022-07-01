package com.ezenac.shop.controller.action.mypage;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.CartDao;
import com.ezenac.shop.dto.CartVO;
import com.ezenac.shop.dto.MemberVO;

public class CartListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "mypage/cartList.jsp"; 
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			//로그인유저의 아이디로 카트리스트를 검색해서 리턴받습니다.
			CartDao cdao = CartDao.getInstance();
			ArrayList<CartVO> list = cdao.selectCart(mvo.getId());
			request.setAttribute("cartList", list);
			
			
		}

	}

}
