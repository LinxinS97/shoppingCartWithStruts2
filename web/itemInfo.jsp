<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/25
  Time: 下午1:37
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
    <title><s:property value="item.itemName"/></title>
</head>
<body id="mainInfo-body">
    <jsp:include page="navbar/navBar.jsp"/>
    <div class="container" id="mainInfo-container">
        <div class="row" style="background-color: #FFFFFF">

            <div class="col-xs-5 text-center img-thumbnail">
                <div class="info-box">
                    <img class="main-img" src="<s:property value="item.itemImg"/> ">
                </div>
            </div>
            <div class="col-xs-6" style="padding-left: 50px">

                <div class="row">
                    <div class="col-xs-12">
                        <div class="title">
                            <h5><s:property value="item.itemName"/> </h5>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 price">
                        <div class="col-xs-2 help-block title">惊爆价</div>
                        <div class="col-xs-7">
                            <h3>￥<s:property value="item.price"/></h3>
                        </div>
                        <div class="col-xs-3 title">
                            成交量&nbsp;0
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2 title help-block">
                        类型
                    </div>
                    <div class="col-xs-3">
                        <h3>
                            <span class="h5 help-block type"><s:property value="item.type"/></span>
                        </h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-2 title help-block">
                        购买数量
                    </div>
                    <div class="col-xs-3">
                        <h3>
                            <input class="form-control buy-number" type="number" id="item-num"
                                   stock="<s:property value="item.stock"/>">
                            <span class="h6 help-block">
                                （库存：<s:property value="item.stock"/>）
                            </span>
                        </h3>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-xs-2">
                        <button class="btn btn-default">
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购买&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </button>
                    </div>
                    <div class="col-xs-2">
                        <button class="btn btn-warning">
                            加入购物车
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="row" style="margin-top: 10px">
            <div class="col-xs-12 text-center" id="manage-line">
                <div class="col-xs-5"><hr></div>
                <div class="col-xs-2" style="height: 20px;"><p>所有评论</p></div>
                <div class="col-xs-5"><hr></div>
            </div>
        </div>

        <div class="row" style="background-color: #FFFFFF">
            <div class="media">
                <div class="media-left media-top media-box">
                    <img class="media-object img-thumbnail main-img" src="source/pic/IMG_2189.JPG">
                </div>
                <div class="media-body">
                    <h4 class="media-heading">用户A</h4>
                    <p>这是第一条评论hahahahahahah<span style="float: right">2010-10-10</span></p>
                </div>
            </div>
        </div>
    </div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
</body>
</html>
