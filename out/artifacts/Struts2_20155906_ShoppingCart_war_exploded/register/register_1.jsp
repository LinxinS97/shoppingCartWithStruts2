<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/11/4
  Time: 上午9:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html lang="zh-CN">
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
              <td id="td1" style="border-top: 1px #ec971f solid; color: #ec971f;">
                ①填写用户名
              </td>
              <td id="td2">
                ②填写用户信息
              </td>
              <td id="td3">
                ③完成注册
              </td>
            </tr>
          </table>
        </div>
      </div>

      <!-- 第一部分用form提交认证 -->
        <form action="RegisterUsername" method="post">
          <div class="row">
            <div class="col-xs-3 col-xs-offset-5">
            <div class="form-group has-feedback" id="username-div">
              <label class="control-label">手机号*</label>
              <div class="input-group">
                <div class="input-group-addon"><span class="glyphicon glyphicon-user"></span></div>
                <input type="text" class="form-control" placeholder="请输入手机号" id="tel" name="tel">
                <span class="glyphicon glyphicon-remove form-control-feedback" aria-hidden="true"
                      style="display:none" id="wrong1"></span>
              </div>
              <span id="helpBlock1" class="help-block" style="display:none">手机号不能忘啊</span>
              <span id="helpBlock7" class="help-block" style="display:none">你这手机号被人用过了</span>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-xs-3 col-xs-offset-5">
            <button type="submit" class="btn btn-primary btn-lg btn-block" id="bt-submit-1">下一步</button>
          </div>
        </div>
      </form>
      
    </div>
  </div>
  <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/localJs_register_1.js"></script>
  <script src="js/bootstrap.min.js"></script>
</body>
</html>
