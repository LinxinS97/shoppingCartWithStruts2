<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
				<li id="nav-mypocket"><a href="myInfo.jsp">我的口袋</a></li>
				<!-- 购物车下拉菜单jsp -->
				<li class="dropdown" id="my-nav-dropdown" style="display: none;">
					<jsp:include page="nav_shoppingCart.jsp"/>
				</li>
			</ul>
			<form class="navbar-form navbar-left" action="GetSearchResult"
				method="get">
				<div class="form-group">
					<input type="text" value="searchKey" name="type"
						style="display:none"> <input type="text"
						class="form-control" placeholder="淘淘口袋" value="" name="key">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
			<!-- user信息jsp  -->
			<jsp:include page="nav_LoginInfo.jsp"/>
		</div><!-- /.navbar-collapse -->
	</div><!-- /.container-fluid --> 
	</nav>
</html>
