<%@ page contentType="text/html;charset=UTF-8" language= "java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
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
    <!--/meta 作为公共模版分离出去-->

    <title>添加用户</title>
</head>
<body>
<article class="page-container">
    <form action="Stock"  method="post" class="form form-horizontal" id="objForm">
        <input type="hidden" name="oper" value="insertDeal" />
        <input type="hidden" name="stockId" value=${stockId} />
        <input type="hidden" name="id" value=${stockId} />

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                <span class="c-red">*</span>商品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <select id="cargoId" name="cargoId"  class="select-box" style="width: 200px">
                    <option value="" >请选择</option>
                    <c:forEach var="item" items="${cargoList}">
                        <option value="${item.cargoId}">${item.cargoName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                当前数量:
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text"   style="width: 200px" value="${number}" placeholder="" id="number" name="number">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                安全存量:
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text"  style="width: 200px" value="${safetyStock}" placeholder="" id="safetyStock" name="safetyStock">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                最后进货日期:
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="date" class="input-text"  style="width: 200px" value="${buyDate}" placeholder="" id="buyDate" name="buyDate">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                最后送货日期:
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="date" class="input-text"  style="width: 200px" value="${sellDate}" placeholder="" id="sellDate" name="sellDate">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                建议购买价:
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text"  style="width: 200px" value="${buyPrice}" placeholder="" id="buyPrice" name="buyPrice">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3">
                建议销售价:
            </label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text"   style="width: 200px" value="${sellPrice}" placeholder="" id="sellPrice" name="sellPrice">
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" id="btn1" type="submit" style="width: 200px" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/H-ui.admin/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript">

    $().ready(function() {
        $("#objForm").validate({
            rules:{
                cargoId:{
                    required:true,
                },
                number:{
                    min:0,
                }
            },

            messages: {
                cargoId:{
                    required:"商品名称不能为空",
                }
            },
            onkeyup:false,
            focusCleanup:true,
            success:"valid",
            submitHandler:function(form){
                form.submit();
            }
        });
    });

</script>

</body>
</html>