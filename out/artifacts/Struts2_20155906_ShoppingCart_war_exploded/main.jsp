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
                    <a href="GetSearchResult?type=服装" class="list-group-item">
                        <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                        &nbsp;&nbsp;服装
                    </a>
                    <a href="GetSearchResult?type=手机" class="list-group-item">
                        <span class="glyphicon glyphicon-phone" aria-hidden="true"></span>
                        &nbsp;&nbsp;手机
                    </a>
                    <a href="GetSearchResult?type=家电" class="list-group-item">
                        <span class="glyphicon glyphicon-home" aria-hidden="true"></span>
                        &nbsp;&nbsp;家电
                    </a>
                    <a href="GetSearchResult?type=数码" class="list-group-item">
                        <span class="glyphicon glyphicon-camera" aria-hidden="true"></span>
                        &nbsp;&nbsp;数码
                    </a>
                    <a href="GetSearchResult?type=游戏" class="list-group-item">
                        <span class="glyphicon glyphicon-blackboard" aria-hidden="true"></span>
                        &nbsp;&nbsp;游戏
                    </a>
                    <a href="GetSearchResult?type=动漫" class="list-group-item">
                        <span class="glyphicon glyphicon-music" aria-hidden="true"></span>
                        &nbsp;&nbsp;动漫
                    </a>
                    <a href="GetSearchResult?type=影视" class="list-group-item">
                        <span class="glyphicon glyphicon-film" aria-hidden="true"></span>
                        &nbsp;&nbsp;影视
                    </a>
                    <a href="GetSearchResult?type=美食" class="list-group-item">
                        <span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
                        &nbsp;&nbsp;美食
                    </a>
                    <a href="GetSearchResult?type=办公" class="list-group-item">
                        <span class="glyphicon glyphicon-briefcase" aria-hidden="true"></span>
                        &nbsp;&nbsp;办公
                    </a>
                    <a href="GetSearchResult?type=学习" class="list-group-item">
                        <span class="glyphicon glyphicon-education" aria-hidden="true"></span>
                        &nbsp;&nbsp;学习
                    </a>
                    <a href="GetSearchResult?type=卡券" class="list-group-item">
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
                        <li data-target="#carousel-example-generic" data-slide-to="4"></li>
                    </ol>
                    <!-- 轮播图片 -->
                    <div class="carousel-inner" role="listbox">
                        <div class="item active">
                            <img src="source/pic/carousel-active.jpg" alt="First IMG"
                                 style="width:739px;height:440px">
                            <div class="carousel-caption">
                                <h3>四次元口袋</h3>
                                <p>想象，是发现的本质</p>
                            </div>
                        </div>
                        <%--<c:forEach items="${carouselItem }" var="img">--%>
                            <%--<div class="item">--%>
                                <%--<a class="items" ItemID=<c:out value="${img.getID() }"/>>--%>
                                    <%--<img src=<c:out value="${img.getItemImgPath() }"/>--%>
                                         <%--style="width:739px;height:440px">--%>
                                <%--</a>--%>
                            <%--</div>--%>
                        <%--</c:forEach>--%>
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
            <div class="col-xs-3 main-right">
                <jsp:include page="main_loginInfo.jsp"/>
            </div>


        </div><!-- row -->
    </div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
</body>
</html>
