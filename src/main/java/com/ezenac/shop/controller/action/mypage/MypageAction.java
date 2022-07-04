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

public class MypageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "mypage/mypage.jsp";
		
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("loginUser");
		if(mvo==null) {
			url = "shop.do?command=loginForm";
		}else {
			OrderDao odao = OrderDao.getInstance();
			
			//mypage.jsp에 최종 전달돼서 화면에 보여질 리스트 생성
			ArrayList<OrderVO> finalList = new ArrayList<OrderVO>();
				//(finalList.get(0).getName() -> "XXXX 외 2건")
			
			
			//※ 진행중인 주문 내역
			//현재 로그인한 사용자의 아직 배송안된 주문내역이 보여집니다.
			//예를 들어 한번에 3개의 상품씩 4회에 걸쳐서 주문한 상태이고, 그 주문들이 배송전이라면
			//진행중인 주문 내역은 4줄이 표시됩니다. (=> 주문 건별 표시)
			//표시 내용은 주문 건 별 대표상품의 이름을 사용하여, 슬리퍼 포함 3, 부츠 외 2 등등 총 세줄이 표시됩니다.
			//그리고 각 행에는 상세보기 버튼이 있어서 클릭하면 그 주문에 속한 3개의 상품을 볼 수 있습니다.
			
			//이를 위해 가장 먼저 필요한 사항은 주문 번호들 입니다.
			//order_view에서 주문자 아이디로 검색하고, result가 1인 주문들을 검색해서, 주문 번호를 조회합니다.
			//위의 예를 든 상태라면 주문 번호들이 다음과 같습니다.
			//22 22 22  24 24 24  26 26 26  27 27 27
			
			//그러나 정작 우리에게 필요한 건 22 24 26 27  이므로, 
			//조회할 때 distinct 키워드로 중복을 제외하고 조회합니다.
			//select distinct oseq from order_view where id=? and result='1'
			
			//주문번호들의 리스트(중복을 없앤)
			ArrayList<Integer> oseqList = odao.selectOseqOrderIng(mvo.getId());
			
			//주문번호들로 반복 실행을 하면
			//주문번호별로 상품을 다시 조회해서 세개의 상품이 있다면
			//그들 가격의 총금액을 계산하고, 첫번째 상품의 price2에 저장합니다.
			//첫번째 상품의 상품이름도, '[대표상품이름] 외 n건' 으로 바꿔줍니다.
			//그 대표상품을 최종화면에 표시될 리스트에 따로 담습니다.
			
			//22번 주문의 3개의 상품에서 첫번째 상품의 상품이름을 "상품이름 외 2건"으로 변경,
			//3개의 가격을 합산한 금액을 22번상품의 price2에 저장
			//그 변경된 첫번째(대표)상품을 finalList에 저장합니다.
			for(Integer oseq : oseqList) {
				//주문번호로 상품 검색 & 리턴
				ArrayList<OrderVO> orderListByOseq = odao.listOrderByOseq(oseq);
				
				//리턴된 리스트의 첫번째 상품을 꺼냅니다.
				OrderVO ovo = orderListByOseq.get(0); 
				
				//꺼낸 상품(ovo)의 이름을 '[현재상품의 상품명] 외 n건' 으로 변경
				ovo.setPname(ovo.getPname()+" 외 "+(orderListByOseq.size()-1)+"건");
				
				//총 금액을 계산
				int totalPrice = 0;
				for(OrderVO ovo1 : orderListByOseq)
					totalPrice += ovo1.getPrice2()*ovo1.getQuantity();
				//계산한 총 금액을 꺼낸 상품(ovo)의 price2에 저장
				ovo.setPrice2(totalPrice);
				
				//모든 변경을 마친 ovo를 finalList에 담습니다.
				finalList.add(ovo);
			}
			//request에 전달할 값을 담습니다.
			request.setAttribute("orderList", finalList);
			request.setAttribute("title", "진행중인 주문 내역");
		}
	
		request.getRequestDispatcher(url).forward(request, response);	
	}

}
