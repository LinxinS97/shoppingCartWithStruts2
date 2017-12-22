<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/11/5
  Time: 下午4:50
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <title>四次元口袋</title>
</head>
<body>
<div class="jumbotron">
    <div class="container">

        <div class="row">
            <div class="col-xs-12">
                <h2>注册</h2>
                <p>填写注册信息，千万别填错了</p>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-12">
                <table class="table text-center">
                    <tr>
                        <td id="td1">
                            ①填写用户名
                        </td>
                        <td id="td2" style="border-top: 1px #ec971f solid; color: #ec971f;">
                            ②填写用户信息
                        </td>
                        <td id="td3">
                            ③完成注册
                        </td>
                    </tr>
                </table>
            </div>
        </div>

        <!-- 第二部分表单提交，validator认证 -->
        <form method="post" action="RegisterInfo" enctype="multipart/form-data">
            <div id="info-div">

                <div class="row">
                    <div class="col-xs-3 col-md-offset-5">
                        <div class="form-group has-feedback" id="userName-div">
                            <label class="control-label">用户名*</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-phone"></span>
                                </div>
                                <input type="text" placeholder="请输入用户名" class="form-control"
                                       id="userName" name="userName"> <span
                                    class="glyphicon glyphicon-remove form-control-feedback"
                                    aria-hidden="true" style="display: none" id="wrong4"></span>
                            </div>
                            <span id="helpBlock5" class="help-block" style="display: none">请填写用户名</span>
                            <span class="helpBlockServer" class="help-block" style="display:block">
                                <s:fielderror fieldName="userName"/></span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-3 col-md-offset-5">
                        <div class="form-group has-feedback" id="psw-div">
                            <label for="exampleInputPassword1" class="control-label">密码*</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                                <input type="password" class="form-control" placeholder="请输入密码"
                                       id="psw" name="psw"> <span
                                    class="glyphicon glyphicon-remove form-control-feedback"
                                    aria-hidden="true" style="display: none" id="wrong2"></span>
                            </div>
                            <span id="helpBlock2" class="help-block" style="display: none">请填写密码</span>
                            <span class="helpBlockServer" class="help-block" style="display:block">
                                <s:fielderror fieldName="psw"/></span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-3 col-md-offset-5">
                        <div class="form-group has-feedback" id="psw-conform-div">
                            <label for="exampleInputPassword1" class="control-label">确认密码*</label>
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-lock"></span>
                                </div>
                                <input type="password" placeholder="再确认一次呗" class="form-control"
                                       id="psw-conform" name="psw_conform"> <span
                                    class="glyphicon glyphicon-remove form-control-feedback"
                                    aria-hidden="true" style="display: none" id="wrong3"></span>
                            </div>
                            <span id="helpBlock3" class="help-block" style="display: none">请再填写一次密码</span>
                            <span class="helpBlockServer" class="help-block" style="display:block">
                                <s:fielderror fieldName="psw_conform"/></span>
                        </div>
                    </div>
                </div>



                <div class="row">
                    <div class="col-xs-3 col-md-offset-5">
                        <div class="form-group has-feedback" id="img-div">
                            <label class="control-label">上传头像</label>
                            <span class="input-group">
                                <div class="input-group-addon">
                                    <span class="glyphicon glyphicon-camera"></span>
                                </div>
                                <input type="file" id="img" name="upload"/>
                            </span>
                            <img class="img-circle" src="" id="img-show" height="79px"
                                 width="79px"/>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-xs-3 col-xs-offset-5">
                        <button type="submit" class="btn btn-primary btn-lg btn-block" id="bt-submit-2">注册</button>
                    </div>
                </div>

            </div>
        </form>

    </div>
</div>
<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/localJs_register_2.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
