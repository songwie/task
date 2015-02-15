<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>

<!-- Left panel :左侧面板，菜单开始 -->
<aside id="left-panel">
    <nav>
        <ul>
            <li>
                <a href="${home}taskmgr/taskmgr"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">任务管理器</span></a>
            </li>
            <li>
                <a href="${home}taskmgr/getTaskHis"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">任务执行记录</span></a>
            </li>
            <li>
                <a href="${home}taskmgr/getTaskExecs"><i class="fa fa-lg fa-fw fa-bar-chart-o"></i> <span class="menu-item-parent">正在执行</span></a>
            </li>
        </ul>
    </nav>
    <span class="minifyme"> <i class="fa fa-arrow-circle-left hit"></i> </span>
</aside>
<script type="text/javascript" >
setNavCss();
/**
 * 动态设置左侧菜单的样式，实现展开闭合效果
 * @returns
 */
function setNavCss(){
	var nowUrl=document.location.href;   //获取当前页面路径
	var navAs=$("nav").find("a");        //获取菜单中所有超链接
	$.each(navAs,function(i,val){
		var nHref=$(val).attr("href");
		if(nowUrl.indexOf(nHref)>-1){
			$(val).parent().addClass("active");
			$(val).parent().parent().css({"display":"block"});
			$(val).parent().parent().parent().addClass("open");
			$(val).parent().parent().parent().find("em").removeClass("fa-expand-o");
			$(val).parent().parent().parent().find("em").addClass("fa-collapse-o");
		}
	})
}   
</script>
<!-- END NAVIGATION 菜单结束-->

