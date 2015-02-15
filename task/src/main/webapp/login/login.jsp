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
    <link rel="stylesheet" type="text/css" media="screen" href="${home}resources/css/bs_login.css">
    <title>Web定时任务管理系统</title>

    <script type="text/javascript" src="${home}login/login.js"></script>

</head>
<body>
<input type="hidden" value="${home}" id="home"/>
<center>
<div id="wrap">
    <div class="loginWrap">
        <!--登录模块--start-->
        <div class="login">
            <form id="loginform" name="loginform" action="${home}login" method="GET" >
                <h1>Web定时任务管理系统</h1>
                <ul>
                    <li class="errorMsg"></li>
                    <li>
                        <label for="Merchant_No">用户名：</label>
                        <input type="text" name="Merchant_No" id="Merchant_No" placeholder="请输入用户名" />
                        <span class="error" id="mer_error"></span>
                    </li>
                    <li>
                        <label for="pwd"><span>密码：</span></label>
                        <input type="password"  name="pwd" id="pwd" placeholder="请输入密码" />
                        <span class="error" id="pwd_error"></span>
                    </li>
                    <li class="button">
                        <span class="error" id="error">${error}</span>
                        <input type="submit" class="loginBtn" id="button-submit"  value="登录"/>
                    </li>
                </ul>
            </form>
        </div>
        <!--登录模块--end-->
    </div>
</div>
</center>
</body>
</html>