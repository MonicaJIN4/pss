<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
    <![endif]-->
    <link href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.login.css"
          rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css" rel="stylesheet"
          type="text/css"/>
    <link href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css"
          rel="stylesheet" type="text/css"/>
    <!--[if IE 6]>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>后台登录 - 进销存管理系统</title>
</head>
<body>

<input type="hidden" id="TenantId" name="TenantId" value=""/>
<div class="header"><img class="bg" src="../static/images/logo1.png" width="260px" style="padding-left:18px "/></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="Login" method="post" id="objForm">
            <input type="hidden" name="oper" value="loginDeal"/>
            <input type="hidden" name="msg" id="msg" value="${msg}"/>

            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userName" name="userName" type="text" value="${userName}" placeholder="用户名"
                           class="input-text size-L">
                </div>

            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="userPass" name="userPass" type="password" placeholder="密码" class="input-text size-L">
                </div>

            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input id="validateCode" name="validateCode" class="input-text size-L" type="text" placeholder="验证码"
                           onblur="if(this.value==''){this.value='验证码:'}"
                           onclick="if(this.value=='验证码:'){this.value='';}"
                           value="" style="width:150px;">
                    <img id="validateCodeImg" src="">
                    <a id="kanbuq" href="javascript:changeValidateCode();">看不清，换一张</a>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <span>${msg}</span>
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="submit" class="btn btn-success radius size-L"
                           value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                    <input name="" type="reset" class="btn btn-default radius size-L"
                           value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">

                    <button class="btn btn-primary radius size-L">
                        <a id="register" href="Register?oper=insertRegisterView"
                           style="color: #ffffff;text-decoration:none; ">注册新账户</a>
                    </button>
                </div>
            </div>
        </form>

    </div>
</div>
<div class="footer">Copyright 你的公司名称 by H-ui.admin v3.1</div>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>


<script type="text/javascript">
    $(function () {
        //页面加载完就显示验证码
        changeValidateCode();
    });

    function changeValidateCode() {
        /***
         * 获取当前的时间作为参数，无具体意义
         * 每次请求需要一个不同的参数，否则可能会返回同样的验证码
         * 这和浏览器的缓存机制有关系，也可以把页面这设置为不缓存，这样就不用这个参数了。
         */
        var timenow = new Date().getTime();

        $("#validateCodeImg").attr("src", "ValidateCode?d=" + timenow);

    };

    // $().ready(function () {
    //     $("#objForm").validate({
    //         rules: {
    //             userName: {
    //                 required: true,
    //             },
    //             userPass: {
    //                 required: true,
    //             }
    //         },
    //         message: {
    //             userName: {
    //                 required: "用户名不能为空",
    //             },
    //             userPass: {
    //                 required: "用户密码不能为空。",
    //             }
    //         },
    //         onkeyup: false,
    //         focusCleanup: true,
    //         success: "valid",
    //         submitHandler: function (form) {
    //             form.submit();
    //         }
    //     });
    // });


</script>

<script type="text/javascript">
    // 如果当前页面不是最顶端页面，那么就将当前页面设置为最顶端页面。

    if (window != top) {
        top.location.href = location.href;
    }
</script>
</body>
</html>