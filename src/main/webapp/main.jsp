<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jsp" %> <!-- main의 상단엔 header를 include해서 사용 -->

<!-- 메인 이미지 시작 : 각 기능별 페이지를 대표하는 이미지 -->
<div id="main_img">
  <img src="images/main_img.jpg" 
  style="border-radius:20px 20px 20px 20px; border:2px solid white;">
</div>

<!-- 신상품 -->
<h2>New Item</h2>
<div id="bestProduct">
  <c:forEach items="${newList}" var="productVO">
    <div id="item">
      <a href="shop.do?command=productDetail&pseq=${productVO.pseq}">
        <img src="product_images/${productVO.image}">
        <h3>
          ${productVO.name} - <fmt:formatNumber value="${productVO.price2}" type="currency"/>
        </h3>
      </a>
    </div>
  </c:forEach>
</div>

<div class="clear"></div>

<!-- 베스트 상품 -->
<h2>Best Item</h2>
<div id="bestProduct">
  <c:forEach items="${bestList}" var="productVO">
    <div id="item">
      <a href="shop.do?command=productDetail&pseq=${productVO.pseq}">
        <img src="product_images/${productVO.image}">
        <h3>
          ${productVO.name} - <fmt:formatNumber value="${productVO.price2}" type="currency"/>
        </h3>
      </a>
    </div>
  </c:forEach>
</div>
<%@ include file="footer.jsp" %> <!-- main의 하단엔 footer를 include해서 사용 -->