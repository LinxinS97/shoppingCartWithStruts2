<%--
  Created by IntelliJ IDEA.
  User: elpis
  Date: 2017/12/25
  Time: 下午4:24
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
    <title>Title</title>
</head>
<body>
    <!-- 模态框 -->
    <div class="modal fade bs-example-modal-sm" id="myModal_login"
         tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">请先登录</h4>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <div class="form-group has-feedback" id="tel-div">
                                <label class="control-label" for="tel">手机号</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-user"></span>
                                    </div>
                                    <input type="text" class="form-control" placeholder="请输入手机号"
                                           id="tel"> <span
                                        class="glyphicon glyphicon-remove form-control-feedback"
                                        aria-hidden="true" style="display:none" id="tel-span"></span>
                                </div>
                                <span id="helpBlock1" class="help-block" style="display:none">手机号必填</span>
                                <span id="helpBlock4" class="help-block" style="display:none">这个手机号没注册</span>
                            </div>
                        </div>
                    </div><!-- row -->
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <div class="form-group has-feedback" id="psw-div">
                                <label for="psw" class="control-label">密码</label>
                                <div class="input-group">
                                    <div class="input-group-addon">
                                        <span class="glyphicon glyphicon-lock"></span>
                                    </div>
                                    <input type="password" class="form-control" placeholder="请输入密码"
                                           id="psw"> <span
                                        class="glyphicon glyphicon-remove form-control-feedback"
                                        aria-hidden="true" style="display:none" id="psw-span"></span>
                                </div>
                                <span id="helpBlock2" class="help-block" style="display:none">朋友，密码也不能没有啊</span>
                                <span id="helpBlock3" class="help-block" style="display:none">朋友，你密码错了，再想想</span>
                            </div>
                        </div>
                    </div>
                    <!-- row -->
                </div>
                <!-- modal-body -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-default modal-button" data-dismiss="modal"
                            id="bt-submit">登录</button>
                    <a href="register/register_1.jsp" type="button" class="btn btn-primary">注册</a>
                </div>
            </div>
        </div>
    </div><!-- modal -->

    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel" id="myModal_addSuccess">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body">确实添加进购物车了！</div>
            </div>
        </div>
    </div><!-- modal -->
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel" id="myModal_itemExist">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body">这个宝贝已经在你的购物车里了！</div>
            </div>
        </div>
    </div><!-- modal -->
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel" id="myModal_perchaseSuccess">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body">购买成功！</div>
            </div>
        </div>
    </div><!-- modal -->
    <div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog"
         aria-labelledby="mySmallModalLabel" id="myModal_selfItem">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-body">您不能添加您自己的商品！</div>
            </div>
        </div>
    </div><!-- modal -->
</body>
</html>
