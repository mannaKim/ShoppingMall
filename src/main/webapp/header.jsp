<!-- main.jsp의 상단에서 사용할 header -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>main</title>
  <link href="css/shopping.css" rel="stylesheet">
  <script src="member/member.js"></script>
  <script src="mypage/mypage.js"></script>
</head>
<body>
  <div id="wrap">
    <header>
    <!-- 로고, 주상단 메뉴, 카테고리 메뉴가 표시되는 영역 -->
      <div id="logo">
        <a href="shop.do?command=index">
          <img src="images/logo.png" width="180" height="100">
        </a>
      </div>
      
      <nav id="top_menu"><!-- LOGIN, CART, MYPAGE 등등 -->
        <ul>
          <c:choose>
            <c:when test="${empty loginUser}">
          	  <li><a href="shop.do?command=loginForm">LOGIN</a></li>
          	  <li><a href="shop.do?command=contract">JOIN</a></li>
          	</c:when>
          	<c:otherwise>
	          <li>${loginUser.name}(${loginUser.id})</li>
	          <li><a href="shop.do?command=editForm">정보수정</a></li>
	          <li><a href="shop.do?command=logout">LOGOUT</a></li>
	        </c:otherwise>  
          </c:choose>
          <li><a href="shop.do?command=cartList">CART</a></li>
          <li><a href="shop.do?command=mypage">MY PAGE</a></li>
          <li><a href="">Q &amp; A</a></li>
        </ul>
      </nav>
      
      <nav id="category_menu"><!-- 카테고리 메뉴 Heels, Boots 등등  -->
        <ul>
          <li><a href="shop.do?command=category&kind=1">Heels</a></li>
          <li><a href="shop.do?command=category&kind=2">Boots</a></li>
          <li><a href="shop.do?command=category&kind=3">Sandals</a></li>
          <li><a href="shop.do?command=category&kind=4">Sneakers</a></li>
          <li><a href="shop.do?command=category&kind=5">Sleeper</a></li>
          <li><a href="shop.do?command=category&kind=6">On Sale</a></li>
        </ul>
      </nav>
    </header>  
  
