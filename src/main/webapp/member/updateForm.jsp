<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %> 
<%@ include file="sub_image_menu.html" %>

<article>
  <h2>Edit Profile</h2>
  <!-- 회원가입 폼과 같이 주소 검색시 결과를 받기 위해 폼 이름을 updateForm이 아니라 joinForm으로 설정 -->
  <form method="post" name="joinForm">
    <input type="hidden" name="command" value="memberUpdate">
    <fieldset>
      <legend>Basic Info</legend>
      <!-- id는 수정대상이 아니면서 submit할 때 전송될 대상이므로 readonly -->
      <label>User ID</label><input type="text" name="id" value="${loginUser.id}" readonly><br>
      <label>Password</label><input type="password" name="pwd"><br>
      <label>Retype Password</label><input type="password" name="pwdCheck"><br>
      <label>Name</label><input type="text" name="name" value="${loginUser.name}"><br>
      <label>E-Mail</label><input type="text" name="email" value="${loginUser.email}"><br>
    </fieldset>
    <fieldset>
      <legend>Option Info</legend>
      <label>Zip Code</label><input type="text" name="zip_num" size="10" value="${loginUser.zip_num}">
      <input type="button" value="주소 찾기" class="dup" onclick="post_zip()"><br>
      <label>Address</label><input type="text" name="address1" size="50" value="${loginUser.address1}"><br>
      <label>&nbsp;</label><input type="text" name="address2" size="25" value="${loginUser.address2}"><br>
      <label>Phone Number</label><input type="text" name="phone" value="${loginUser.phone}"><br>
    </fieldset>
    <div class="clear"></div>
    <div id="buttons">
      <input type="button" value="정보수정" class="submit" onClick="go_update()">
      <input type="reset" value="취소" class="cancel">
    </div>
  </form>
</article>

<%@ include file="../footer.jsp" %> 
