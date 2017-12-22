<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/4
  Time: 下午3:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta HTTP-EQUIV="Refresh" CONTENT="0; URL=Welcome">
    <title>跳转中</title>
  </head>
</html>
