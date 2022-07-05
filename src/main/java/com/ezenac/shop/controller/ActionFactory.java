package com.ezenac.shop.controller;

import com.ezenac.shop.controller.action.Action;
import com.ezenac.shop.controller.action.ContractAction;
import com.ezenac.shop.controller.action.EditFormAction;
import com.ezenac.shop.controller.action.FindAccountAction;
import com.ezenac.shop.controller.action.FindIdFormAction;
import com.ezenac.shop.controller.action.FindIdStep1Action;
import com.ezenac.shop.controller.action.FindIdStep2Action;
import com.ezenac.shop.controller.action.FindPwFormAction;
import com.ezenac.shop.controller.action.FindPwStep1Action;
import com.ezenac.shop.controller.action.FindPwStep2Action;
import com.ezenac.shop.controller.action.FindZipNumAction;
import com.ezenac.shop.controller.action.IdCheckFormAction;
import com.ezenac.shop.controller.action.IndexAction;
import com.ezenac.shop.controller.action.JoinAction;
import com.ezenac.shop.controller.action.JoinFormAction;
import com.ezenac.shop.controller.action.LoginAction;
import com.ezenac.shop.controller.action.LoginFormAction;
import com.ezenac.shop.controller.action.LogoutAction;
import com.ezenac.shop.controller.action.MemberUpdateAction;
import com.ezenac.shop.controller.action.ResetPwAction;
import com.ezenac.shop.controller.action.admin.AdminAction;
import com.ezenac.shop.controller.action.admin.AdminLoginAction;
import com.ezenac.shop.controller.action.admin.AdminProductListAction;
import com.ezenac.shop.controller.action.admin.AdminQnaListAction;
import com.ezenac.shop.controller.action.mypage.CartDeleteAction;
import com.ezenac.shop.controller.action.mypage.CartInsertAction;
import com.ezenac.shop.controller.action.mypage.CartListAction;
import com.ezenac.shop.controller.action.mypage.MypageAction;
import com.ezenac.shop.controller.action.mypage.OrderDetailAction;
import com.ezenac.shop.controller.action.mypage.OrderInsertAction;
import com.ezenac.shop.controller.action.mypage.OrderInsertOneAction;
import com.ezenac.shop.controller.action.mypage.OrderListAction;
import com.ezenac.shop.controller.action.product.CategoryAction;
import com.ezenac.shop.controller.action.product.ProductDetailAction;
import com.ezenac.shop.controller.action.qna.QnaListAction;
import com.ezenac.shop.controller.action.qna.QnaViewAction;
import com.ezenac.shop.controller.action.qna.QnaWriteAction;
import com.ezenac.shop.controller.action.qna.QnaWriteFormAction;

public class ActionFactory {
	
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if(command.equals("index")) ac = new IndexAction();
		else if(command.equals("loginForm")) ac = new LoginFormAction();
		else if(command.equals("login")) ac = new LoginAction();
		else if(command.equals("logout")) ac = new LogoutAction();
		else if(command.equals("contract")) ac = new ContractAction();
		else if(command.equals("joinForm")) ac = new JoinFormAction();
		else if(command.equals("idCheckForm")) ac = new IdCheckFormAction();
		else if(command.equals("findZipNum")) ac = new FindZipNumAction();
		else if(command.equals("join")) ac = new JoinAction();
		else if(command.equals("editForm")) ac = new EditFormAction();
		else if(command.equals("memberUpdate")) ac = new MemberUpdateAction();
		else if(command.equals("findAccount")) ac = new FindAccountAction();
		else if(command.equals("findIdForm")) ac = new FindIdFormAction();
		else if(command.equals("findIdStep1")) ac = new FindIdStep1Action();
		else if(command.equals("findIdStep2")) ac = new FindIdStep2Action();
		else if(command.equals("findPwForm")) ac = new FindPwFormAction();
		else if(command.equals("findPwStep1")) ac = new FindPwStep1Action();
		else if(command.equals("findPwStep2")) ac = new FindPwStep2Action();
		else if(command.equals("resetPw")) ac = new ResetPwAction();
		
		else if(command.equals("category")) ac = new CategoryAction();
		else if(command.equals("productDetail")) ac = new ProductDetailAction();
		
		else if(command.equals("cartInsert")) ac = new CartInsertAction();
		else if(command.equals("cartList")) ac = new CartListAction();
		else if(command.equals("cartDelete")) ac = new CartDeleteAction();
		else if(command.equals("orderInsert")) ac = new OrderInsertAction();
		else if(command.equals("orderList")) ac = new OrderListAction(); //방금 주문한 주문내역
		else if(command.equals("mypage")) ac = new MypageAction();
		else if(command.equals("orderDetail")) ac = new OrderDetailAction();
		else if(command.equals("orderInsertOne")) ac = new OrderInsertOneAction();
		
		else if(command.equals("qnaList")) ac = new QnaListAction();
		else if(command.equals("qnaWriteForm")) ac = new QnaWriteFormAction();
		else if(command.equals("qnaWrite")) ac = new QnaWriteAction();
		else if(command.equals("qnaView")) ac = new QnaViewAction();
		
		else if(command.equals("admin")) ac = new AdminAction();
		else if(command.equals("adminLogin")) ac = new AdminLoginAction();
		else if(command.equals("adminProductList")) ac = new AdminProductListAction();
		else if(command.equals("adminQnaList")) ac = new AdminQnaListAction();
		
		
		return ac;
	}

}
