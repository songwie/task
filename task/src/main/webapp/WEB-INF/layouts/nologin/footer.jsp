<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>
<!DOCTYPE html>
<html>
<!--底部-start-->
<input type="hidden" value="${home}" id="home"/>

<div id="footer">
    <div class="footerRow clearBorder">
        <p>
            <a target="_blank" href="http://www.laolaibao.com/laolaibao">关于老来宝</a>
            <span>|</span>
            <a target="_blank" href="http://www.laolaibao.com/lookingfortalent">诚征英才</a>
            <span>|</span>
            <a target="_blank" href="${home}help">联系我们 </a>
            <span>|</span>
            <a target="_blank" href="${home}help">商家加盟</a>
        </p>
        <p>
            ©2013 上海旭日养老服务有限公司. 沪ICP备13014816号 客服邮箱：service@xrpension.com
        </p>
    </div>
</div>
</html>