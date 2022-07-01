<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %> 
<%@ include file="sub_image_menu.html" %>

<article>
  <h1>Login</h1>
  <form method="post" action="shop.do" name="loginFrm">
    <input type="hidden" name="command" value="login">
    <fieldset>
      <legend></legend>
        <label>User ID</label><input type="text" name="id"><br>
        <label>Password</label><input type="password" name="pwd"><br>
    </fieldset>
    <div id="buttons">
      <input type="submit" value="Login" class="submit" onClick="return loginCheck()">
      <input type="button" value="Join" class="cancel" onClick="">
      <input type="button" value="아이디/비밀번호 찾기" class="submit" onClick="find_account()">
    </div><br><br>
    <div>&nbsp;&nbsp;&nbsp;${message}</div>
  </form>
</article>

<%@ include file="../footer.jsp" %> 