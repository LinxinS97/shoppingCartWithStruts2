<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'navBar.jsp' starting page</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

	<nav class="navbar navbar-inverse navbar-fixed-top" id="myNavBar">

	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<a class="navbar-brand" href="#">四次元口袋</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">

				<!-- 购物车下拉菜单jsp -->
                <s:if test="#session.user != null">

                <li id="nav-mypocket"><a href="myInfo.jsp">我的口袋</a></li>
				<li class="dropdown" id="my-nav-dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                        我的购物车<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu" id="myWidth">
                        <s:if test="#session.cartSize == 0 ">
                            <li>亲，您的购物车空空如也...</li>
                        </s:if>
                        <s:else>
                            <s:iterator value="#session.navBar_cart" status="st" id="item">
                                <li style="border-top: 1px solid #f1f1f1">
                                    <a class="media">
                                        <div class="media-left main-box">
                                            <img class="media-object main-img"
                                                 src="<s:property value="#item.itemImg"/> ">
                                        </div>
                                        <div class="media-body">
                                            <div class="col-xs-4 text-font" style="margin:10px 0 0 0">
                                                <s:property value="#item.itemName"/>
                                            </div>
                                            <div class="col-xs-4" style="margin:10px 0 0 0">
                                                <s:property value="#item.type"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <h5>
                                                    ￥<s:property value="#item.price"/>
                                                </h5>
                                                <p class="help-block delete-cart" role="button"
                                                   itemId="<s:property value="#item.itemId"/> ">删除</p>
                                            </div>
                                        </div>
                                    </a>
                                </li>
                            </s:iterator>

                        </s:else>
                        <li role="separator" class="divider"></li>
                        <s:if test="#session.cartSize-3 <= 0 ">
                            <li>
                                <button type="button" class="btn btn-primary btn-xs">
                                    <a href="getCartItem"><font color="white">查看我的购物车</font></a>
                                </button>
                            </li>
                        </s:if>
                        <s:else>
                            <li>购物车中还有${cartSize - 3 }件商品</li>
                            <li>
                                <button type="button" class="btn btn-primary btn-xs">
                                    <a href="getCartItem"><font color="white">查看我的购物车</font></a>
                                </button>
                            </li>
                        </s:else>
                    </ul>
				</li>
                </s:if>
			</ul>
			<form class="navbar-form navbar-left" action="getSearchResult"
				method="get">
				<div class="form-group">
					<input type="text" value="order" name="location"
						style="display:none">
					<input type="text" class="form-control" placeholder="淘淘口袋" value="" name="itemName">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
			<!-- user信息jsp  -->
			<jsp:include page="nav_LoginInfo.jsp"/>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid --> 
	</nav>
</html>
