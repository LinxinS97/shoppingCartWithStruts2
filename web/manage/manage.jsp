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
    <link href="css/star-rating.min.css" rel="stylesheet"/>
    <link href="css/local.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/localJs_main.js"></script>
    <script src="js/localJs_manage.js"></script>
    <script src="js/laydate.js"></script>
    <script src="js/star-rating.min.js"></script>
    <script>
        laydate.skin('qianhuang');      //切换皮肤

        //日期范围限制
        let start = {
            elem: '#time1',
            format: 'YYYY-MM-DD hh:mm:ss',
            max: '2099-12-31',          //最大日期
            istime: true,
            istoday: false,
            choose: function(datas){
                end.min = datas;        //开始日选好后，重置结束日的最小日期
                end.start = datas       //将结束日的初始值设定为开始日
            }
        };
        let end = {
            elem: '#time2',
            format: 'YYYY-MM-DD hh:mm:ss',
            max: '2099-12-31',
            istime: true,
            istoday: false,
            choose: function(datas){
                start.max = datas;      //结束日选好后，充值开始日的最大日期
            }
        };
        laydate(start);
        laydate(end);
    </script>
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
                    <h4 style="margin: 5px 0 20px 0;">商品管理</h4>
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
                        <s:if test="#session.requestType==1 || #session.requestType==5">
                            <div class="col-xs-4">
                                <div class="form-group">
                                    <label for="userOrder">成交数量：</label>
                                    <input class="form-control" type="number" name="search_order" value="0" id="userOrder"
                                           style="width: 25%; text-decoration:none;">
                                    &nbsp;到
                                    <input class="form-control" type="number" name="search_order" value="0"
                                           style="width: 25%; text-decoration:none;">

                                </div>
                            </div>
                            <div class="col-xs-4">
                                <div class="form-group">
                                    <label for="stock">商品库存：</label>
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
                        </s:if>
                        <s:elseif test="#session.requestType==3 || #session.requestType==7">
                            <div class="col-xs-8">
                                <div class="form-group">
                                    <label for="time1">下单日期：</label>
                                    <input class="form-control layday-icon" type="text" name="search_orderTime" id="time1"
                                           style="width: 38%;" placeholder="请选择开始日期">
                                    &nbsp;到&nbsp;
                                    <input class="form-control layday-icon" type="text" name="search_orderTime" id="time2"
                                           style="width: 38%;" placeholder="请选择结束日期">
                                </div>
                                <div class="form-group">
                                    <label for="status">&nbsp;&nbsp;状态：</label>
                                    <select class="form-control" id="status" name="search_status">
                                        <option value="0">全部</option>
                                        <option value="1">未发货</option>
                                        <option value="2">未收货</option>
                                        <option value="3">未评价</option>
                                    </select>

                                </div>
                            </div>
                            <div class="col-xs-4">
                                <input type="text" name="page" value="1" style="display: none;">
                                <input type="text" name="requestType" value="7" style="display: none;">
                                <input type="text" name="isSearch" value="1" style="display: none;">
                                <button class="btn-sm btn-default" type="submit">条件搜索</button>
                                <button class="btn-sm btn-warning" type="reset">重置信息</button>
                            </div>
                        </s:elseif>
                    </div>
                </form>
            </div>

            <%--搜索结果分隔--%>
            <div class="col-xs-10 text-center" id="manage-line">
                <div class="col-xs-5"><hr></div>
                <div class="col-xs-2" style="height: 20px;"><p>搜索结果</p></div>
                <div class="col-xs-5"><hr></div>
            </div>

            <%--货品信息--%>
            <s:if test="#session.requestType==3 || #session.requestType==7">
                <s:iterator value="orderList" id="item">
                    <div class="col-xs-10">
                        <div class="media items">
                            <div class="media-left img-thumbnail manage-box">
                                <img class="media-object main-img" src="<s:property value="#item.item.itemImg"/> ">
                            </div>


                            <div class="media-body">
                                <h4 class="media-heading"><s:property value="#item.item.itemName"/></h4>
                                <h5>
                                    订单编号：<s:property value="#item.order.orderId"/>
                                </h5>
                                <h6>
                                    下单时间：20<s:property value="#item.order.startTime"/> &nbsp;&nbsp;
                                    类别：<s:property value="#item.item.type"/> &nbsp;&nbsp;
                                    价格：￥<s:property value="#item.item.price"/> &nbsp;&nbsp;


                                    <p style="float: right; margin-right: 10px">
                                        <%--<s:property value="#item.status"/>--%>
                                        <s:if test="#session.user.userId == #item.order.buyerId">
                                            <s:if test="#item.order.comment">
                                                订单完成
                                            </s:if>
                                            <s:elseif test="#item.order.get">
                                                <span role="button" class="comment"
                                                      itemId="<s:property value="#item.item.itemId"/>"
                                                      orderId="<s:property value="#item.order.orderId"/>">去评价</span>
                                            </s:elseif>
                                            <s:elseif test="#item.order.delivery">
                                                <span role="button" orderId="<s:property value="#item.order.orderId"/>"
                                                      class="get">确认收货</span>
                                            </s:elseif>
                                            <s:else>
                                                等待卖家发货
                                            </s:else>
                                        </s:if>
                                        <s:else>
                                            <s:if test="!#item.order.delivery">
                                                <span class="delivery" role="button"
                                                      orderId="<s:property value="#item.order.orderId"/>">发货</span>
                                            </s:if>
                                            <s:elseif test="!#item.order.get">
                                                等待买家收货
                                            </s:elseif>
                                            <s:elseif test="!#item.order.comment">
                                                等待买家评价
                                            </s:elseif>
                                            <s:else>
                                                订单完成
                                            </s:else>
                                        </s:else>
                                    </p>
                                    <p style="float: right; margin-right: 10px">
                                            <%--<s:property value="#item.status"/>--%>
                                        <s:if test="#session.user.userId == #item.order.buyerId">
                                            买家
                                        </s:if>
                                        <s:else>
                                            卖家
                                        </s:else>
                                    </p>

                                </h6>

                            </div>
                        </div>
                    </div>
                </s:iterator>
            </s:if>
            <s:else>
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
                                    成交量：<s:property value="#item.completeOrder"/>
                                    <a href="getItemInfo?requestType=1&itemId=<s:property value="#item.itemId"/>"
                                       style="float: right;">
                                        编辑
                                    </a>

                                    <a class="delete-item" style="float: right; margin-right: 10px"
                                       role="button" itemId="<s:property value="#item.itemId"/> ">
                                        删除
                                    </a>
                                </h6>

                            </div>
                        </div>
                    </div>
                </s:iterator>
            </s:else>
        </div>

        <%--分页符--%>
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

    <!-- Modal : 发货-->
    <div class="modal fade" id="delivery-modal" tabindex="-1" role="dialog" aria-labelledby="delivery">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button"
                            class="close"
                            data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="delivery">发货</h4>
                </div>
                <div class="modal-body">
                    <label for="d-num">填写运单号：</label>
                    <span><input type="text" class="form-control" id="d-num"> </span>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">再想想</button>
                    <button id="delivery-c" type="button" class="btn btn-primary">发货</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal : 确认收货-->
    <div class="modal fade" id="get-modal" tabindex="-1" role="dialog" aria-labelledby="get">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button"
                            class="close"
                            data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="get">确认收货</h4>
                </div>

                <div class="modal-body">
                    <div class="form-group has-feedback" id="psw-div">
                        <label for="psw" class="control-label">请您确定收到了货物后才确认收货，否则可能物财两空！</label>
                        <div class="input-group">
                            <div class="input-group-addon">
                                <span class="glyphicon glyphicon-lock"></span>
                            </div>
                            <input type="password" class="form-control" placeholder="请输入密码"
                                   id="psw"> <span
                                class="glyphicon glyphicon-remove form-control-feedback"
                                aria-hidden="true" style="display:none" id="psw-span"></span>
                        </div>
                        <span id="helpBlock2" class="help-block" style="display:none">密码必填</span>
                        <span id="helpBlock3" class="help-block" style="display:none">密码错误</span>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">再想想</button>
                    <button id="get-c" type="button" class="btn btn-primary">确认收货</button>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal : 评论-->
    <div class="modal fade" id="comment-modal" tabindex="-1" role="dialog" aria-labelledby="get">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header">
                    <button type="button"
                            class="close"
                            data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="comment">添加评论</h4>
                </div>

                <div class="modal-body">
                    <div class="form-group has-feedback">
                        <label for="apprise">
                            评分
                        </label>
                        <input required id="apprise" type="number" class="rating">
                        <span id="helpBlock4" class="help-block" style="display:none">请给出您的评价</span>
                    </div>
                    <div class="form-group">
                        <label for="comment-textarea">
                            内容
                        </label>
                        <textarea wrap="hard"
                                  class="form-control" id="comment-textarea" cols="30" rows="5"></textarea>
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">再想想</button>
                    <button id="comment-c" type="button" class="btn btn-primary">添加评论</button>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
