<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/4
  Time: 下午5:10
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
    <title>四次元口袋</title>
</head>
<body class="body-class">
    <jsp:include page="navbar/navBar.jsp"/>
    <div class="container" id="main-container">
        <div class="row">

            <%--商品类型--%>
            <div class="col-xs-2">
                <div class="list-group">
                    <a href="getSearchResult?type=服装&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        &nbsp;&nbsp;服装
                    </a>
                    <a href="getSearchResult?type=手机&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                        &nbsp;&nbsp;手机
                    </a>
                    <a href="getSearchResult?type=家电&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                        &nbsp;&nbsp;家电
                    </a>
                    <a href="getSearchResult?type=数码&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
                        &nbsp;&nbsp;数码
                    </a>
                    <a href="getSearchResult?type=游戏&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span>
                        &nbsp;&nbsp;游戏
                    </a>
                    <a href="getSearchResult?type=动漫&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-music" aria-hidden="true"></span>
                        &nbsp;&nbsp;动漫
                    </a>
                    <a href="getSearchResult?type=影视&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-film" aria-hidden="true"></span>
                        &nbsp;&nbsp;影视
                    </a>
                    <a href="getSearchResult?type=美食&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
                        &nbsp;&nbsp;美食
                    </a>
                    <a href="getSearchResult?type=办公&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
                        &nbsp;&nbsp;办公
                    </a>
                    <a href="getSearchResult?type=学习&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-education" aria-hidden="true"></span>
                        &nbsp;&nbsp;学习
                    </a>
                    <a href="getSearchResult?type=卡券&location=userOrder" class="list-group-item">
                        <span class="glyphicon glyphicon-usd" aria-hidden="true"></span>
                        &nbsp;&nbsp;卡券
                    </a>
                </div>
            </div>

            <!-- 轮播图片 -->
            <div class="col-xs-7">
                <div id="carousel-example-generic" class="carousel slide"
                     data-ride="carousel" data-pause="hover">
                    <!-- 小圆点 -->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0"
                            class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="3"></li>
                    </ol>
                    <!-- 轮播图片 -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <div style="width: 682px; height: 440px; text-align: center;
                                display: table-cell;vertical-align: middle;">
                                <img class="carousel-image" src="source/pic/carousel-active.jpg" alt="First IMG">
                            </div>
                            <div class="carousel-caption">
                                <h3>四次元口袋</h3>
                                <p>想象，是发现的本质</p>
                            </div>
                        </div>

                        <%--carousel图片，点击跳转到商品详情页面--%>
                        <s:iterator value="carouselItems" id="item">
                        <div class="item">
                            <div style="width: 682px; height: 440px; text-align: center;
                                display: table-cell;vertical-align: middle;">
                                <img href="GetItemInfo?itemId=<s:property value="#item.itemId"/>"
                                     class="carousel-image" src="<s:property value="#item.itemImg"/>">
                            </div>
                            <div class="carousel-caption">
                                <h3><s:property value="#item.itemName"/> </h3>
                                <p>￥<s:property value="#item.price"/> </p>
                            </div>
                        </div>
                        </s:iterator>
                    </div>

                    <!-- 左右两侧的控制按钮 -->
                    <a class="carousel-control left" href="#carousel-example-generic"
                       role="button" data-slide="prev"> <span
                            class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a> <a class="right carousel-control" href="#carousel-example-generic"
                            role="button" data-slide="next"> <span
                        class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
                </div><!-- carousel-example-generic -->
            </div>


            <!-- 网页右边部分 -->
            <div class="col-xs-3" id="main-right">
                <jsp:include page="main_loginInfo.jsp"/>
            </div>

            <%-- 分隔 --%>
            <div class="row">
                <div class="col-xs-12 text-center" id="manage-line">
                    <div class="col-xs-5"><hr></div>
                    <div class="col-xs-2" style="height: 20px;"><p>最新商品</p></div>
                    <div class="col-xs-5"><hr></div>
                </div>
            </div>


            <%--下方商品展示--%>
            <div class="row">
                <s:iterator value="itemList" id="item">
                    <div class="col-xs-3">
                        <div class="thumbnail items">
                            <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/> "
                               class="thumbnail main-box">
                                <img class="main-img" src="<s:property value="#item.itemImg"/>">
                            </a>
                            <div class="caption">
                                <h3>￥<s:property value="#item.price"/></h3>
                                <p class="text-font"><s:property value="#item.itemName"/> </p>
                            </div>
                        </div>
                    </div>
                </s:iterator>
            </div>
        </div><!-- row -->
    </div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
</body>
</html>
