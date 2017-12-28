<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/22
  Time: 下午2:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<div class="list-group">
    <a class="list-group-item">
        <div class="media">
            <div class="media-left manage-box" style="width: 10000px">
                <img class="media-object img-rounded main-img" src="${user.userImg }">
            </div>
            <div class="media-body">
                <h5 class="media-heading">你好，${user.userName }</h5>
                <h6>订单数量：${sessionScope.orderCount }</h6>
                <h6>上架商品数：${sessionScope.uploadedItem } </h6>
            </div>
        </div>
    </a>

    <a href="Management?requestType=1" class="list-group-item">
        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
        &nbsp;&nbsp;我的商品
    </a>
    <a href="Management?requestType=3" class="list-group-item">
        <span class="glyphicon glyphicon-align-left" aria-hidden="true"></span>
        &nbsp;&nbsp;订单管理
    </a>
    <a href="manage/uploadItem.jsp" class="list-group-item">
        <span class="glyphicon glyphicon-upload" aria-hidden="true"></span>
        &nbsp;&nbsp;发布商品
    </a>
</div>
</html>
