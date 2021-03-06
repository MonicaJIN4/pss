<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,sup-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>用户查看</title>
</head>
<body>
<article class="page-container">
    <input type="hidden" name="userId" value="${bean.userId}" />
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">序号：</label>
        <div class="formControls col-xs-8 col-sm-9">${bean.userId}</div>
    </div>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">用户名：</label>
        <div class="formControls col-xs-8 col-sm-9">${bean.userName}</div>
    </div>

    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">用户密码：</label>
        <div class="formControls col-xs-8 col-sm-9">
            ${bean.userPass}</div>
    </div>
    <div class="row cl">
        <label class="form-label col-xs-4 col-sm-3">职位名：</label>
        <div class="formControls col-xs-8 col-sm-9">
            ${bean.roleName}</div>
    </div>
    <div class="row cl">
        <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
            <input id="close" type="button"class="btn btn-primary radius"
                   value="&nbsp;&nbsp;关闭&nbsp;&nbsp;"></div>
    </div>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<script type="text/javascript">
    $(function () {
        $("#close").click(function () {
            layer_close();
        });
    });
</script>
<!--请在下方写此页面业务相关的脚本-->
</body>
</html>