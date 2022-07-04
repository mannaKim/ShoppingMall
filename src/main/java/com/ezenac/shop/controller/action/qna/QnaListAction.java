package com.ezenac.shop.controller.action.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.dao.QnaDao;
import com.ezenac.shop.dto.MemberVO;
import com.ezenac.shop.dto.QnaVO;

public class QnaListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "qna/qnaList.jsp";
		
		//로그인체크
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo==null) {
			url="shop.do?command=loginForm";
		}else {
			//로그인한 아이디로 qna 목록을 조회하고 리턴받습니다. (메서드 이름:listQna)
			QnaDao qdao = QnaDao.getInstance();
			ArrayList<QnaVO> qnaList = qdao.listQna(mvo.getId());
			
			//리턴받은 리스크(qnaList)를 리쿼스트에 담습니다.
			request.setAttribute("qnaList", qnaList);
		}
		
		//목적지로 이동
		request.getRequestDispatcher(url).forward(request, response);

	}

}
