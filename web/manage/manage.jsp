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

            <%--条件搜索面板--%>
            <div class="col-xs-10">
                <form class="form-inline" action="Management" method="get" id="manage-search">
                    <h4 style="margin: 5px 0 20px 0;">条件搜索</h4>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="itemName">商品名称：</label>
                                <input class="form-control" type="text" name="search_name" id="itemName">
                            </div>
                        </div>

                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="price">商品价格：</label>
                                <input class="form-control"
                                       type="number" id="price" step="0.01" name="search_price" value="0"
                                       style="width: 25%;">
                                到&nbsp;
                                <input class="form-control"
                                       type="number" step="0.01" name="search_price" value="0"
                                       style="width: 25%;">
                            </div>
                        </div>

                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="type">商品类别：</label>
                                <select class="form-control" id="type" name="search_type">
                                    <option>全部</option>
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
                    </div>

                    <br>
                    <div class="row">
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="order">成交数量：</label>
                                <input class="form-control" type="number" name="search_order" value="0" id="order"
                                       style="width: 25%;">
                                &nbsp;到
                                <input class="form-control" type="number" name="search_order" value="0"
                                       style="width: 25%;">

                            </div>
                        </div>
                        <div class="col-xs-4">
                            <div class="form-group">
                                <label for="stock">库存：</label>
                                <input class="form-control" type="number" name="search_stock" value="0" id="stock"
                                       style="width: 25%;">
                                &nbsp;到
                                <input class="form-control" type="number" name="search_stock" value="0"
                                       style="width: 25%;">

                            </div>
                        </div>
                        <div class="col-xs-4">
                            <input type="text" name="page" value="1" style="display: none;">
                            <input type="text" name="requestType" value="5" style="display: none;">
                            <input type="text" name="isSearch" value="1" style="display: none;">
                            <button class="btn-sm btn-default" type="submit">条件搜索</button>
                            <button class="btn-sm btn-warning" type="reset">重置信息</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-xs-10 text-center" id="manage-line">
                <div class="col-xs-5"><hr></div>
                <div class="col-xs-2" style="height: 20px;"><p>搜索结果</p></div>
                <div class="col-xs-5"><hr></div>
            </div>
            <s:iterator value="itemList" id="item">
            <div class="col-xs-10">
                <div class="media items">
                    <div class="media-left img-thumbnail manage-box">
                        <img class="media-object main-img" src="<s:property value="#item.itemImg"/> ">
                    </div>


                    <div class="media-body">
                        <h4 class="media-heading"><s:property value="#item.itemName"/></h4>
                        <h5>
                            商品ID：<s:property value="#item.itemId"/>
                        </h5>
                        <h6>
                            价格：￥<s:property value="#item.price"/> &nbsp;&nbsp;
                            类型：<s:property value="#item.type"/> &nbsp;&nbsp;
                            库存：<s:property value="#item.stock"/> &nbsp;&nbsp;
                            成交量：0
                        </h6>
                    </div>
                </div>
            </div>
            </s:iterator>
        </div>

        <div class="row">
            <div class="col-xs-4 col-xs-offset-2">
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
                                <a href="Management?requestType=${requestType}&previous=1"
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
                                    <a href="Management?requestType=${requestType}&page=<s:property value="#st.index + 1"/>"
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
                                <a href="Management?requestType=${requestType}&next=1" aria-label="Next">
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
    <script>
        $(function () {
            $("#date").blur(function () {
                console.log($(this).val());
            })
        })
    </script>
    <script src="js/localJs_main.js"></script>
</body>
</html>
