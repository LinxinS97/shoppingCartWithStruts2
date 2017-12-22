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

            <s:iterator value="itemList" id="item">
            <div class="col-xs-10">
                <div class="media items">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object img-thumbnail main-img" src="<s:property value="#item.itemImg"/> ">
                        </a>
                    </div>


                    <div class="media-body">
                        <h4 class="media-heading"><s:property value="#item.itemName"/></h4>
                        <h5>
                            价格：<s:property value="#item.price"/> &nbsp;&nbsp;
                        </h5>
                        <h6>
                            类型：<s:property value="#item.type"/> &nbsp;&nbsp;
                            库存：<s:property value="#item.stock"/> &nbsp;&nbsp;
                            成交量：11
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
                        <li>
                            <a class="page-num">
                                1
                            </a>
                        </li>
                        <li>
                            <a class="page-num">
                                2
                            </a>
                        </li>
                        <li>
                            <a class="page-num">
                                3
                            </a>
                        </li>
                        <li>
                            <a class="page-num">
                                4
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/localJs_main.js"></script>
</body>
</html>
