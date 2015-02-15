<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>
<header id="header">
    <div id="logo-group">
        <span id="logo"> <img src="${home}resources/img/logoInner.png" alt="SmartAdmin"> </span>
    </div>
    <div class="pull-right">

        <!-- logout button -->
        <div id="logout" class="btn-header transparent pull-right">
            <span> <a href="${home}loginOut" title="Sign Out"><i class="fa fa-sign-out"></i></a> </span>
        </div>
        <!-- end logout button -->
        <div class="content-header transparent pull-right">
        	<p style="line-height:50px;margin-right:10px;">您好， ${ sessionScope.OPER_USER_NAME}</p>
        </div>
    </div>
    <!-- end pulled right: nav area -->
</header>



