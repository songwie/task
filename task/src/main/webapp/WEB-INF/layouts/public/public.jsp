<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>任务调度</title>
	<jsp:include page="/common/common.jsp"></jsp:include>
</head>
<body class="tundra spring claro">
        <input type="hidden" value="${home}" id="home"/>
        <tiles:insertAttribute name="header" ignore="true" />
		<tiles:insertAttribute name="menu" ignore="true" />
	    <tiles:insertAttribute name="body"/>
</body>
</html>
