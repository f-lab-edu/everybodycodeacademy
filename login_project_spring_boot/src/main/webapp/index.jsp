<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 예제</title>
</head>
<body>
<% if(session.getAttribute("id")==null){ //세션이 설정되지 않을 경우 %>
<form method="post" action="member_ok.jsp">
    <label for="id">아이디</label>
    <input  type="text" name="id" id="id" placeholder="비밀번호" required />
    <label for="pass">비밀번호</label>
    <input type="password" name="pass" id="pass" placeholder="비밀번호" required />
    <input type="submit" value="로그인" />
</form>
<% }else{ %>
<form method="post" action="logout.jsp">
    <%=session.getAttribute("id") %>님 환영합니다~
    <% out.println("<script>alert('로그인 되었습니다.'); </script>"); %>
    <input type="submit" value="로그아웃" />
</form>
<% } %>


</body>
</html>