<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title></title>
  <link href="css/shopping.css" rel="stylesheet">
  <script src="member/member.js"></script>
</head>
<body>
  <center><h2>비밀번호 재설정</h2></center>
  <form method="post" name="frm" action="shop.do">
    <input type="hidden" name="command" value="resetPw">
    <input type="hidden" name="id" value="${id}">
    <table align="center" bgcolor="black" cellspacing="1" width="400">
      <tr align="center" bgcolor="#fde8ff">
        <td width="430">
          <h3>비밀번호<input type="password" name="pwd"></h3>
        </td>
      </tr>
      <tr align="center" bgcolor="#fde8ff">
        <td width="430">
          <h3>비밀번호 확인<input type="password" name="pwd_chk"></h3>
        </td>
      </tr>
      <tr align="center" bgcolor="#fde8ff">
        <td width="430">
          <h3><input type="submit" value="비밀번호 재설정" onClick="return resetPw();"></h3>
        </td>
      </tr>
    </table>
  </form>
</body>
</html>