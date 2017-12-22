<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/11/12
  Time: 下午5:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html lang="zh-CN">
<head>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/local.css" rel="stylesheet">
    <title>登录</title>
</head>
<body>
<div class="jumbotron" id="login-jumbotron">
    <div class="container" id="container-login">
        <div class="row">
            <div class="col-xs-4">
                <h2 >登录</h2>
                <p >请输入登录信息</p>
            </div>
        </div>

        <div class="row">

            <div class="col-xs-3 col-md-offset-8" id="login-top">

                <div class="form-group has-feedback" id="tel-div">
                    <label for="exampleInputEmail1" class="control-label">手机号</label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-user"></span>
                        </div>
                        <input type="text" class="form-control" placeholder="请输入注册时的手机号"
                               id="tel" name="tel"> <span
                            class="glyphicon glyphicon-remove form-control-feedback"
                            aria-hidden="true" style="display: none" id="tel-span"></span>
                    </div>
                    <span id="helpBlock1" class="help-block" style="display: none">请填写手机号</span>
                    <span id="helpBlock4" class="help-block" style="display: none">该手机号未注册</span>
                </div>
            </div>
            <div class="col-xs-3 col-md-offset-8" id="login-middle">

                <div class="form-group has-feedback" id="psw-div">
                    <label for="exampleInputPassword1" class="control-label">密码</label>
                    <div class="input-group">
                        <div class="input-group-addon">
                            <span class="glyphicon glyphicon-lock"></span>
                        </div>
                        <input type="password" class="form-control" placeholder="请输入密码"
                               id="psw" name="psw">
                        <span class="glyphicon glyphicon-remove form-control-feedback"
                              aria-hidden="true" style="display: none" id="psw-span"></span>
                    </div>
                    <span id="helpBlock2" class="help-block" style="display: none">别忘了密码</span>
                    <span id="helpBlock3" class="help-block" style="display: none">密码错了</span>
                </div>
            </div>
            <div class="col-xs-3 col-md-offset-8" id="login-below">
                <%--按钮组--%>
                <div class="btn-group" data-toggle="buttons" style="margin-bottom: 10px">
                    <label class="btn btn-info btn-xs active">
                        <input type="radio" name="options" class="options" value="buyer" autocomplete="off" checked> 买家
                    </label>
                    <label class="btn btn-info btn-xs">
                        <input type="radio" name="options" class="options" value="seller" autocomplete="off"> 卖家
                    </label>
                </div>

                <%--登录按钮--%>
                <button type="button" class="btn btn-primary btn-lg btn-block"
                        id="bt-submit">登录</button>
            </div>
        </div>

    </div>
</div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/localJs_login.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
