<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/common/common.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" media="screen" href="${home}resources/css/login.css">
    <title>Web定时任务管理系统</title>
	<script type="text/javascript" src="${home}resources/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${home}resources/login.js"></script>

</head>
<body>
<input type="hidden" value="${home}" id="home"/>
<div id="main" role="main">
    <!-- MAIN CONTENT -->
    <!--<form id="loginOld-form" class="lockscreen animated flipInY" action="index.html" >-->
    <form id="login-form" class="lockscreen animated" action="${home}login" method="GET" >
    <div class="logo">
        <h1 class="semi-bold"><img src="${home}resources/img/logo.png" alt="" />Web定时任务管理系统</h1>
    </div>
    <div>
        <div class="smart-form client-form">

            <header>登录</header>

            <fieldset>

                <section>
                    <label class="label">用户名或用户ID</label>
                    <label class="input">
                        <i class="icon-append fa fa-user"></i>
                        <input type="text" name="Merchant_No" id="Merchant_No"/>
                        <b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i> 请填写用户名或者用户ID</b>
                    </label>
                    <em class="invalid" style="display:none;">请填写用户名或者用户ID</em>
                </section>

                <section>
                    <label class="label">密码</label>
                    <label class="input">
                        <i class="icon-append fa fa-lock"></i>
                        <input type="password"  name="pwd" id="pwd" />
                        <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i> 请填写您的密码</b>
                    </label>
                    <em class="invalid" style="display:none;">请填写您的密码</em>
                    <div class="note">
                        <a href="#">忘记密码?</a>
                    </div>
                </section>

            </fieldset>
            <footer>
                <button id="button-submit" type="submit" class="btn btn-primary">登录</button>
            </footer>

        </div>
    </div>
    <p class="font-xs margin-top-5">
        Copyright 2014-2020.
    </p>
    </form>

</div>

<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
<script data-pace-options='{ "restartOnRequestAfter": true }' src="${home}js/plugin/pace/pace.min.js"></script>-->

<script src="${home}resources/js/libs/jquery-ui-1.10.3.min.js"></script>

<!-- BOOTSTRAP JS -->
<script src="${home}resources/js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="${home}resources/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!--[if IE 7]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->


<!-- MAIN APP JS FILE -->
<script src="${home}resources/js/app.js"></script>
</body>
</html>