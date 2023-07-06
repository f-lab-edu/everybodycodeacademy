<%--
  Created by IntelliJ IDEA.
  User: gim-yeongju
  Date: 2023/06/27
  Time: 1:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    session.invalidate();
    if (session == null || !request.isRequestedSessionIdValid()) {
        out.println("<script>alert('로그아웃되었습니다');</script>");
        out.println("<script>location.href = 'index.jsp';</script>");
        //response.sendRedirect("../index.jsp");
    }
%>
