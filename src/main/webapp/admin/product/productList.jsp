<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/admin/header.jsp" %>
<%@ include file="/admin/sub_menu.jsp" %>

<article>
  <h1>상품 리스트</h1>
  <form name="frm" method="post">
    <table>
      <tr>
        <td width="642">
          상품명<input type="text" name="key" value="${key}">
          <input type="button" class="btn" name="btn_search" value="검색" onClick="">
          <input type="button" class="btn" name="btn_total" value="전체보기" onClick="">
          <input type="button" class="btn" name="btn_write" value="상품등록" onClick="go_wrt();">
        </td>
      </tr>
    </table>
  </form>
  
  <table id="productList">
    <tr>
      <th>번호</th><th>상품명</th><th>원가</th>
      <th>판매가</th><th>등록일</th><th>사용유무</th>
    </tr>
    <c:forEach items="${productList}" var="productVO">
      <tr>
        <td height="23" align="center">${productVO.pseq}</td>
        <td style="text-align:left; padding-left:50px;">
          <a href="#" onClick="">${productVO.name}</a>
        </td>
        <td><fmt:formatNumber value="${productVO.price1}"/></td>
        <td><fmt:formatNumber value="${productVO.price2}"/></td>
        <td><fmt:formatDate value="${productVO.indate}"/></td>
        <td>
          <c:choose>
            <c:when test='${productVO.useyn=="n"}'>미사용</c:when>
            <c:otherwise>사용</c:otherwise>
          </c:choose>
        </td>
      </tr>
    </c:forEach>
  </table>
  <br><br>
  <jsp:include page="/admin/paging/paging.jsp">
    <jsp:param value="shop.do?command=adminProductList" name="command"/>
  </jsp:include>
</article>

<%@ include file="/admin/footer.jsp" %>