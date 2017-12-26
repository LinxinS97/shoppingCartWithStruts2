<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/25
  Time: 下午6:15
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
    <title>搜索结果</title>
</head>
<body class="body-class">
<jsp:include page="navbar/navBar.jsp"/>


<div class="container-fluid" style="height: 30%;background-color: #fcf3cf; padding: 0;">
    <div class="jumbotron" style="background-color:  #fcf3cf">
        <div class="col-xs-4 col-xs-offset-1">
            <h2>搜索结果</h2>
            <p>你想要<span class="h6">和不想要的</span>的<span class="h6">大概</span>都有</p>
        </div>
    </div>
    <div class="col-xs-12" style="margin-top: 4.5%; padding: 0">
        <ul class="nav nav-tabs" role="tablist" style="padding: 0 268px 0 268px; background-color: #ddedf6">
            <li role="presentation" class="order">

                <a href="getSearchResult?isTag=1&location=order">销量</a>
            </li>
            <li role="presentation" class="popular">
                <a href="getSearchResult?isTag=1&location=popular">人气</a>
            </li>
            <li class="dropdown price">
                <a class="dropdown-toggle"
                   data-toggle="dropdown"
                   role="button"
                   aria-haspopup="true"
                   aria-expanded="false">价格 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li class="price-li">

                        <a href="getSearchResult?isTag=1&location=price">价格从低到高</a></li>
                    <li class="price-li-d">

                        <a href="getSearchResult?isTag=1&location=price_desc">价格从高到低</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<div class="container" id="result-container">
    <div class="row">
        <div class="col-xs-12" >

            <%--销量排行--%>
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane fade" id="order">
                    <s:iterator value="itemList" id="item">
                        <div class="media">
                            <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>"
                               class="media-left thumbnail media-middle media-box">
                                <img class="media-object main-img" src="<s:property value="#item.itemImg"/> "/>
                            </a>
                            <div class="media-body">
                                <div class="col-xs-4">
                                    <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>">
                                        <s:property value="#item.itemName"/></a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <h4>￥<s:property value="#item.price"/></h4>
                                </div>
                                <div class="col-xs-4 text-center" style="padding-top: 5px">
                                    <p class="help-block"><s:property value="#item.completeOrder"/>人付款</p>
                                </div>
                            </div>
                        </div>
                    </s:iterator>

                </div>

                <%--人气排行--%>
                <div role="tabpanel" class="tab-pane fade" id="popular">
                    <s:iterator value="itemList" id="item">
                    <div class="media">
                        <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>"
                           class="media-left thumbnail media-middle media-box">
                            <img class="media-object main-img" src="<s:property value="#item.itemImg"/> "/>
                        </a>
                        <div class="media-body">
                            <div class="col-xs-4">
                                <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>">
                                    <s:property value="#item.itemName"/></a>
                            </div>
                            <div class="col-xs-4 text-center">
                                <h4>￥<s:property value="#item.price"/></h4>
                            </div>
                            <div class="col-xs-4 text-center" style="padding-top: 5px">
                                <p class="help-block"><s:property value="#item.completeOrder"/>人付款</p>
                            </div>
                        </div>
                    </div>
                    </s:iterator>
                </div>

                <%--价格从高到低排行--%>
                <div role="tabpanel" class="tab-pane fade" id="price">
                    <s:iterator value="itemList" id="item">
                        <div class="media">
                            <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>"
                               class="media-left thumbnail media-middle media-box">
                                <img class="media-object main-img" src="<s:property value="#item.itemImg"/> "/>
                            </a>
                            <div class="media-body">
                                <div class="col-xs-4">
                                    <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>">
                                        <s:property value="#item.itemName"/></a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <h4>￥<s:property value="#item.price"/></h4>
                                </div>
                                <div class="col-xs-4 text-center" style="padding-top: 5px">
                                    <p class="help-block"><s:property value="#item.completeOrder"/>人付款</p>
                                </div>
                            </div>
                        </div>
                    </s:iterator>
                </div>

                <%--价格从低到高排行--%>
                <div role="tabpanel" class="tab-pane fade" id="price_desc">
                    <s:iterator value="itemList" id="item">
                        <div class="media">
                            <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>"
                               class="media-left thumbnail media-middle media-box">
                                <img class="media-object main-img" src="<s:property value="#item.itemImg"/> "/>
                            </a>
                            <div class="media-body">
                                <div class="col-xs-4">
                                    <a href="getItemInfo?requestType=2&itemId=<s:property value="#item.itemId"/>">
                                        <s:property value="#item.itemName"/></a>
                                </div>
                                <div class="col-xs-4 text-center">
                                    <h4>￥<s:property value="#item.price"/></h4>
                                </div>
                                <div class="col-xs-4 text-center" style="padding-top: 5px">
                                    <p class="help-block"><s:property value="#item.completeOrder"/>人付款</p>
                                </div>
                            </div>
                        </div>
                    </s:iterator>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-4">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <%--向前翻页--%>
                    <s:if test="page == 1">
                        <li class="disabled">
                            <a aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </s:if>
                    <s:else>
                        <li>
                            <a href="getSearchResult?previous=1"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </s:else>

                    <%--页码翻页--%>
                    <s:iterator begin="1" end="maxPage" status="st">
                        <s:if test="page == #st.index + 1">
                            <li class="active">
                                <a class="page-num">
                                    <s:property value="#st.index + 1"/>
                                </a>
                            </li>
                        </s:if>
                        <s:else>
                            <li>
                                <a href="getSearchResult?page=<s:property value="#st.index + 1"/>"
                                   class="page-num">
                                    <s:property value="#st.index + 1"/>
                                </a>
                            </li>
                        </s:else>
                    </s:iterator>

                    <%--向后翻页--%>
                    <s:if test="page == maxPage">
                        <li class="disabled">
                            <a aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </s:if>
                    <s:else>
                        <li>
                            <a href="getSearchResult?next=1" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </s:else>
                </ul>
            </nav>
        </div>
    </div>
</div>


<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/localJs_main.js"></script>
<script src="js/localJs_SR.js"></script>
</body>
</html>
