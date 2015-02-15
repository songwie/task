<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <jsp:include page="/common/common.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css" media="screen" href="${home}resources/css/bs_login.css">
    <link rel="stylesheet" type="text/css" href="${home}resources/css/layout.css"/>
    <link rel="SHORTCUT ICON" href="http://a.laolaibao.com/resources/agentstyle/images/favicon.ico">
    <title>错误</title>
</head>
<body>
 <input type="hidden" value="${home}" id="home"/>

<div id="wrap">
    <div class="resetPwdOkWrap">
        <p>您未登录或服务异常，请重新登录！</p>
        <span id="error">${error}</span>
        <a href="${home}_login" >点击进入登录界面</a>
    </div>
</div>

</body>
</html>