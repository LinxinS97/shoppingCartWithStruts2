<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/21
  Time: 下午9:56
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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/local.css" rel="stylesheet">
    <title>四次元口袋-卖家端</title>
</head>
<body class="body-class body-management">
<jsp:include page="../navbar/navBar.jsp"/>
<div class="container" id="main-manage">
    <div class="row">
        <%-- 用户操作板 --%>
        <div class="col-xs-2">
            <jsp:include page="userInfo.jsp"/>
        </div>
        <div class="col-xs-10">
            <form class="form-horizontal manage-form" action="reUpload" method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="itemImg">
                        商品图片
                    </label>
                    <label for="itemImg" class="col-sm-10 control-label itemInfo-box">
                        <img class="main-img img-rounded" src="<s:property value="item.itemImg"/> "
                             role="button" id="img-show">
                    </label>
                    <input type="file" name="upload" id="itemImg" style="display: none">
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="itemName">
                        商品名称
                    </label>
                    <div class="col-sm-10">
                        <input value="<s:property value="item.itemName"/>" class="form-control" type="text"
                               name="itemName" id="itemName">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="price">
                        商品价格
                    </label>
                    <div class="col-sm-10">
                        <input value="<s:property value="item.price"/>" class="form-control" type="number" step="0.01"
                               name="price" id="price">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label" for="type">
                        商品类别
                    </label>
                    <div class="col-sm-10">
                        <select class="form-control" id="type" name="type">
                            <option>服装</option>
                            <option>手机</option>
                            <option>家电</option>
                            <option>数码</option>
                            <option>游戏</option>
                            <option>动漫</option>
                            <option>影视</option>
                            <option>美食</option>
                            <option>办公</option>
                            <option>游戏</option>
                            <option>卡券</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="stock" class="col-sm-2 control-label">
                        商品库存
                    </label>
                    <div class="col-sm-10">
                        <input value="<s:property value="item.stock"/>" class="form-control" type="number"
                               id="stock" name="stock">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-3 text-center">
                        <input type="number" name="itemId" value="<s:property value="item.itemId"/>"
                               style="display: none;"/>
                        <input type="text" name="requestType" value="1" style="display: none"/>
                        <button class="btn btn-info" type="submit">重新发布</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
</body>
</html>
