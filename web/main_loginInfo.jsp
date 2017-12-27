<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

        <center style="margin-bottom: 55px" id="loginCenter">
            <s:if test="#session.user == null">
            <div id="info_unLogin">
                <img src="source/pic/IMG_2189.JPG" alt="Head"
                     class="img-circle imgPosition" height="79px" width="79px">
                <h4 class="media-heading">Hi! 你好</h4>
            </div>

            </s:if>
            <s:else>
                <div id="info_isLogin">
                    <a href="myInfo.jsp"><img src="${user.getUserImg() }"
                                              alt="Head" class="img-circle imgPosition" height="79px"
                                              width="79px"></a>
                    <h4 class="media-heading">Hi! ${user.getUserName() }</h4>
                </div>
                <p>欢迎来到四次元口袋!</p>
            </s:else>
        </center>



	<br>
    <s:if test="#session.user == null">
        <table class="table" id="tb_unLogin">
            <tr>
                <td align="center" style="border-top: 0px"><a href="login.jsp" class="btn btn-primary btn-lg btn-block">马上登录</a></td>
            </tr>
            <tr>
                <td align="center" style="border-top: 0px"><a
                        href="register/register_1.jsp" class="btn btn-default btn-xs btn-block">马上注册</a></td>
            </tr>
        </table>
    </s:if>
    <s:else>
        <table class="table table-condensed table-hover" id="tb_isLogin">
            <tr>
                <td align="center" style="border-top: 0px"><span
                        class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span></td>
                <td style="border-top: 0px"><font size=2><a
                        href="getCartItem">待付款</a></font></td>
                <!-- 徽章 -->
                <td style="border-top: 0px"><span class="badge"><s:property value="#session.cartSize"/> </span></td>
            </tr>
            <tr>
                <td align="center" style="border-top: 0px"><span
                        class="glyphicon glyphicon-yen" aria-hidden="true"></span></td>
                <td style="border-top: 0px"><font size=2>待发货</font></td>
                <td style="border-top: 0px"><span class="badge">0</span></td>
            </tr>
            <tr>
                <td align="center" style="border-top: 0px"><span
                        class="glyphicon glyphicon-hourglass" aria-hidden="true"></span></td>
                <td style="border-top: 0px"><font size=2>待收货</font></td>
                <td style="border-top: 0px"><span class="badge">0</span></td>
            </tr>
            <tr>
                <td align="center" style="border-top: 0px"><span
                        class="glyphicon glyphicon-comment" aria-hidden="true"></span></td>
                <td style="border-top: 0px"><font size=2>待评价</font></td>
                <td style="border-top: 0px"><span class="badge">0</span></td>
            </tr>

        </table>
    </s:else>
</html>
