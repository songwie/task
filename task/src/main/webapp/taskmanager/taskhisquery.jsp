<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<spring:url value="/" var="home"></spring:url>

<input type="hidden" value="${home}" id="home"/>
<input type="hidden" value="${merchantid}" id="merchantid"/>
<!--中间区域-start-->
<!-- MAIN PANEL -->
<div id="main" role="main">
<!-- MAIN CONTENT -->
<div id="content">
    <!-- widget grid -->
    <section id="widget-grid" class="">
        <!-- row -->
        <div class="row">
            <!-- NEW WIDGET START -->
            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <!-- Widget ID (each widget will need unique ID)-->
                <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
                    <header>
                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                        <h2>任务执行历史记录</h2>
                    </header>
                    <!-- widget div-->
                    <div>
                        <!-- widget edit box -->
                        <div class="jarviswidget-editbox">
                            <!-- This area used as dropdown edit box -->
                        </div>
                        <!-- end widget edit box -->
                        <style>
                            .ccc th,.ccc tr,.ccc td{
                                margin: 0;
                                padding:0;
                            }
                        </style>
                        <!-- widget content -->
                        <div class="widget-body no-padding ccc">
                            <div class="widget-body-toolbar">
                            </div>
                            <table id="dt_basic" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>任务ID</th>
                                    <th>任务描述</th>
                                    <th>执行完成时间</th>
                                    <th>执行结果</th>
                                </tr>
                                </thead>
                                <tbody>
                               <c:forEach var="task" items="${taskList}" varStatus="stauts">
	                            <c:choose><c:when test="${stauts.count%2 == 1}"><tr class="odd"></c:when><c:otherwise><tr class="even"></c:otherwise></c:choose>
	                                <td align="center">${stauts.count}</td>
	                                <td align="center">${task.jobId}</td>
	                                <td align="center">${task.jobName}</td>
	                                <td align="center"><fmt:formatDate type="both" dateStyle="full" value="${task.createTime.time}"></fmt:formatDate></td>
   	                                <td align="center"><c:out value="${task.result}"  escapeXml="true" /></td>
	                            </tr>
                            </c:forEach>
                                </tbody>
                            </table>

                        </div>
                        <!-- end widget content -->

                    </div>
                    <!-- end widget div -->

                </div>
                <!-- end widget -->

            </article>
            <!-- WIDGET END -->

        </div>

        <!-- end row -->

    </section>
    <!-- end widget grid -->

</div>
<!-- END MAIN CONTENT -->
</div>
<!-- END MAIN PANEL -->
<!--中间区域-end-->
<script type="text/javascript" src="${home}resources/js/jquery-1.10.2.min.js"></script>

<script src="${home}resources/js/libs/jquery-ui-1.10.3.min.js"></script>
<!-- BOOTSTRAP JS -->
<script src="${home}resources/js/bootstrap/bootstrap.min.js"></script>
<!-- PAGE RELATED PLUGIN(S) -->
<script src="${home}resources/js/plugin/datatables/jquery.dataTables-cust.min.js"></script>
<script src="${home}resources/js/plugin/datatables/ColReorder.min.js"></script>
<script src="${home}resources/js/plugin/datatables/FixedColumns.min.js"></script>
<script src="${home}resources/js/plugin/datatables/ColVis.min.js"></script>
<script src="${home}resources/js/plugin/datatables/ZeroClipboard.js"></script>
<script src="${home}resources/js/plugin/datatables/media/js/TableTools.min.js"></script>
<script src="${home}resources/js/plugin/datatables/DT_bootstrap.js"></script>

<!-- PAGE RELATED PLUGIN(S) -->
<script src="${home}resources/js/plugin/jquery-form/jquery-form.min.js"></script>

<script src="${home}resources/js/app.js"></script>

<script>
$(function(){
	 pageSetUp();
	 $('#dt_basic').dataTable({});
})
</script>