<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<div id="sub_img">
  <img src="images/mypage/sub_img.jpg" style="border-radius:20px 20px 20px 20px;">
</div>
<div class="clear"></div>

<nav id="sub_menu">
  <ul>
    <li><a href="shop.do?command=cartList">장바구니내역</a></li>
    <li><a href="shop.do?command=myPage">진행중인 주문내역</a></li>
    <li><a href="shop.do?command=orderAll">총 주문내역</a></li>
    <li><a href="#" onClick="withdrawalConfirm();">회원탈퇴</a></li>
  </ul>
</nav>