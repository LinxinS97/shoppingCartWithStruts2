<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">
        <title>My JSP 'userInfo.jsp' starting page</title>
    </head>

    <s:if test="#session.user == null">
        <ul class="nav navbar-nav navbar-right" id="nav_loginInfo_unLogin">
            <li><a href="login.jsp">亲，请登录</a></li>
            <li><a href="register/register_1.jsp">马上注册</a></li>
        </ul>
    </s:if>
    <s:else>
        <ul class="nav navbar-nav navbar-right" id="nav_loginInfo_isLogin">
            <li>
                <img src="${user.getUserImg() }" class="img-circle" style="width:50px;height:50px">
            </li>
            <li>
                <a href="Management?requestType=1">雷猴啊，${user.getUserName() }</a>
            </li>
            <li>
                <a href="logout">退出登录</a>
            </li>
        </ul>
    </s:else>

</html>
