<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/22
  Time: 下午2:25
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
    <title>Title</title>
</head>
<body class="body-class body-management">
<jsp:include page="../navbar/navBar.jsp"/>
<div class="container" id="main-manage">
    <div class="row">
        <%-- 用户操作板 --%>
        <div class="col-xs-2">
            <jsp:include page="userInfo.jsp"/>
        </div>

        <div class="col-xs-9" style="margin-left: 20px">
            <form class="form-horizontal" method="post" action="upload.action" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="itemName">商品名称</label>
                    <input type="text" class="form-control" id="itemName" name="itemName" placeholder="请输入商品名称">
                </div>
                <div class="form-group">
                    <label for="itemPrice">商品价格</label>
                    <input type="number" step="0.01" class="form-control" id="itemPrice" name="price" placeholder="请输入价格">
                </div>
                <div class="form-group">
                    <label for="itemStock">请输入商品库存</label>
                    <input type="number" class="form-control" id="itemStock" name="stock" placeholder="请输入库存">
                </div>
                <div class="form-group">
                    <label for="itemGroup">请选择商品类别</label>
                    <select class="form-control" id="itemGroup" name="type">
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
                <div class="form-group">
                    <label for="itemImg">请选择商品图片</label>
                    <img class="img-rounded" id="img-show" height="200px"
                         width="200px"/>
                    <input type="file" id="itemImg" name="upload">

                    <p class="help-block">请选择一张能直观的表现出商品特色的图片</p>

                </div>
                <button type="submit" class="btn btn-default">发布</button>
            </form>
        </div>

    </div>
</div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
</body>
</html>
