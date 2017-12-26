<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/26
  Time: 下午5:26
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
<body id="body-cart">
<jsp:include page="navbar/navBar.jsp"/>
<div class="container-fluid">
    <div class="jumbotron">
        <div class="col-xs-offset-1">
            <div class="h2">
                购物车
            </div>
            <p>我把所有的宝贝都放在这里了</p>
        </div>
    </div>
</div>
<div class="container">
    <div class="row item-info-tag">
        <div class="col-xs-1">
            选择
        </div>
        <div class="col-xs-5">
            商品信息
        </div>
        <div class="col-xs-1 col-xs-offset-2">
            单价
        </div>
        <div class="col-xs-1">
            数量
        </div>
        <div class="col-xs-1">
            金额
        </div>
        <div class="col-xs-1">
            操作
        </div>
    </div>

    <%--购物车物品展示--%>
    <s:iterator value="cartList" id="item" status="st">
        <s:if test="#item.disable == 1">
            <s:if test="#st.index + 1 == maxItem">
                <div class="row item-info item-disable bottom">
                    <div class="col-xs-1">
                        商品被卖家下架
                    </div>
                    <div class="col-xs-5">
                        <div class="media">
                            <div class="media-left item-box">
                                <img class="media-object main-img">
                            </div>
                            <div class="media-body">
                                <h5 class="media-heading">
                                    已删除
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 col-xs-offset-4">
                    </div>
                    <div class="col-xs-1">
                        <span class="help-block delete-btn-page" role="button" itemId="<s:property value="#item.itemId"/>">删除</span>
                    </div>
                </div>
            </s:if>
            <s:else>
                <div class="row item-info item-disable">
                    <div class="col-xs-1">
                        商品被卖家下架
                    </div>
                    <div class="col-xs-5">
                        <div class="media">
                            <div class="media-left item-box">
                                <img class="media-object main-img">
                            </div>
                            <div class="media-body">
                                <h5 class="media-heading">
                                    已删除
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 col-xs-offset-4">
                    </div>
                    <div class="col-xs-1">
                        <span class="help-block delete-btn-page" role="button" itemId="<s:property value="#item.itemId"/>">删除</span>
                    </div>
                </div>
            </s:else>
        </s:if>
        <s:else>
            <s:if test="#st.index + 1 == maxItem">
                <div class="row item-info bottom">
                    <div class="col-xs-1">
                        <input type="checkbox" class="item-is-check"/>
                    </div>
                    <div class="col-xs-5">
                        <div class="media">
                            <div class="media-left item-box">
                                <img class="media-object main-img" src="<s:property value="#item.item.itemImg"/>">
                            </div>
                            <div class="media-body">
                                <h5 class="media-heading">
                                    <s:property value="#item.item.itemName"/>
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 col-xs-offset-2">
                        ￥<span class="price-tag"><s:property value="#item.item.price"/></span>
                    </div>
                    <div class="col-xs-1">
                        <input class="form-control num" type="number" value="<s:property value="#item.num"/>">
                        <span class="h6">(库存：<span class="stock"><s:property value="#item.item.stock"/></span>)</span>
                    </div>
                    <div class="col-xs-1">
                        ￥<span class="h5 sum-tag" style="color: #ed5f37;"><s:property value="#item.item.price"/></span>
                    </div>
                    <div class="col-xs-1">
                        <span class="help-block delete-btn-page" role="button" itemId="<s:property value="#item.item.itemId"/>">删除</span>
                    </div>
                </div>
            </s:if>
            <s:else>
                <div class="row item-info">
                    <div class="col-xs-1">
                        <input type="checkbox" class="item-is-check"/>
                    </div>
                    <div class="col-xs-5">
                        <div class="media">
                            <div class="media-left item-box">
                                <img class="media-object main-img" src="<s:property value="#item.item.itemImg"/>">
                            </div>
                            <div class="media-body">
                                <h5 class="media-heading">
                                    <s:property value="#item.item.itemName"/>
                                </h5>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-1 col-xs-offset-2">
                        ￥<span class="price-tag"><s:property value="#item.item.price"/></span>
                    </div>
                    <div class="col-xs-1">
                        <input class="form-control num" type="number" value="<s:property value="#item.num"/>">
                        <span class="h6">(库存：<span class="stock"><s:property value="#item.item.stock"/></span>)</span>
                    </div>
                    <div class="col-xs-1">
                        ￥<span class="h5 sum-tag" style="color: #ed5f37;"><s:property value="#item.item.price"/></span>
                    </div>
                    <div class="col-xs-1">
                        <span class="help-block delete-btn-page" role="button" itemId="<s:property value="#item.item.itemId"/>">删除</span>
                    </div>
                </div>
            </s:else>
        </s:else>
    </s:iterator>
</div>
<%--底部导航条--%>
<nav class="navbar navbar-default navbar-fixed-bottom">
    <div class="container">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li id="select-all" role="button">
                    <a>全选</a>
                </li>
                <li>
                    <a role="button">删除</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a>已选商品<span class="h4" id="sum-num" style="color: #ed5f37;">0</span>件</a>
                </li>
                <li>
                    <a>
                        总价：
                        <span class="h6" style="color: #ed5f37;">￥</span><span class="h4" id="sum-price" style="color: #ed5f37;">0.00</span>
                    </a>
                </li>
                <li>
                    <a role="button" disabled="disabled"><span class="h4">结算</span></a>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div>
</nav>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
<script src="js/localJs_cart.js"></script>
</body>
</html>
