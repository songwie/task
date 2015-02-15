<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${home}resources/css/layout.css"/>
    <!--<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>-->
</head>
<body>
<!--网页顶部-start-->
<div id="top">
    <div class="topRow">
        <ul class="topRight">
            <li>
                您好， ${ sessionScope.OPER_USER_NAME}
            </li>
            <li>
                <span>|</span>
            </li>
            <li>
                <a href="${home}help" target="_blank">帮助中心</a>
            </li>
            <li>
                <span>|</span>
            </li>
            <li>
                <a href="${home}setPassword" target="_self">修改密码</a>
            </li>
            <li>
                <span>|</span>
            </li>
            <li>
                <a href="${home}loginOut" target="_self">退出</a>
            </li>
        </ul>
    </div>
</div>
<!--网页顶部-end-->

<!--网页头部-start-->
<div id="header">
    <div class="headerRow">
    </div>
</div>
<!--网页头部-end-->
</body>
</html>


