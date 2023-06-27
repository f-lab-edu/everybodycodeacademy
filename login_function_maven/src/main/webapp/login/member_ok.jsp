<%--
  Created by IntelliJ IDEA.
  User: gim-yeongju
  Date: 2023/06/27
  Time: 1:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<% request.setCharacterEncoding("utf-8"); %>
<%
    String id = request.getParameter("id");
    String pass = request.getParameter("pass");

    String checkId = "moneyfire";
    String checkpass = "15412258";
    if(id.equals(checkId) && pass.equals(checkpass)){
        session.setAttribute("id",id);
        response.sendRedirect("../index.jsp");
    }else if(id.equals(checkId)){
        out.println("<script>alert('비밀번호가 틀렸습니다.'); history.back();</script>");
    }else if(pass.equals(checkpass)){
        out.println("<script>alert('아이디가 틀렸습니다.'); history.back();</script>");
    }else{
        out.println("<script>alert('아이디와 비밀번호가 틀렸습니다.'); history.back();</script>");
    }
%>