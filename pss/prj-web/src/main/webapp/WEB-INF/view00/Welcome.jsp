<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style type="text/css">
    #bg {
        width: 99%;
        height: 99%;
        background-image: url("../static/images/fruit.jpg");
        background-size: cover;
    }

    #welcome {
        padding: 50px;
    }
</style>
<script>
    setInterval("show_time.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());", 1000);
</script>

<div id="bg">
    <div id="welcome">
        <img class="bg" src="../static/images/logo1.png" width="300px"/>
        <p>欢迎您。</p>
        <p>现在时间是：<span id="show_time"></span></p>
    </div>


</div>
