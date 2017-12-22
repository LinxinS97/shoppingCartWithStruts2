<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'nav_shoppingCart.jsp' starting page</title>
	</head>

    <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        我的购物车<span class="badge">${cart.size() }</span><span class="caret"></span>
    </a>
    <%--<ul class="dropdown-menu" id="myWidth">--%>
        <%--<s:iterator value="cart" status="st" id="cartItem" begin="0" end="2">--%>
		<%--<li>--%>
			<%--<a class="media items" itemID="">--%>
				<%--<div class="media-left">--%>
				<%--<img class="media-object" src="" height="50px" width="50px" id="">--%>
			<%--</div>--%>
				<%--<div class="media-body">--%>
					<%--<p class="dots">...</p>--%>
				<%--</div>--%>
			<%--</a>--%>
		<%--</li>--%>
        <%--</s:iterator>--%>
		<%--<li role="separator" class="divider"></li>--%>
        <%--<s:if test="cart.size == 0 ">--%>
            <%--<li>亲，您的购物车空空如也...</li>--%>
        <%--</s:if>--%>
        <%--<s:if test="cart.size() > 3">--%>
            <%--<li>购物车中还有${cart.size() - 3 }件商品</li>--%>
        <%--</s:if>--%>
		<%--<li>--%>
			<%--<button type="button" class="btn btn-primary btn-xs">--%>
				<%--<a href="myCart.jsp"><font color="white">查看我的购物车</font></a>--%>
			<%--</button>--%>
		<%--</li>--%>
	<%--</ul>--%>
</html>
