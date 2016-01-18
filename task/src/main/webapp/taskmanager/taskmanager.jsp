<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	    <div class="row">
		    <div class="col-sm-12" style="padding-top:10px;padding-bottom:10px;">
			    <button type="button" class="btn btn-primary" id="addBtnId" title="创建新任务">创建新任务</button>
			    <span class="error" id="error">${error}</span>
			</div>
	    </div>
        <!-- row -->
        <div class="row">
            <!-- NEW WIDGET START -->
            <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <!-- Widget ID (each widget will need unique ID)-->
                <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
                    <header>
                        <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                        <h2>任务管理器</h2>
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
                                   <!-- <th>任务triger</th> -->
                                    <th>调用URL</th>
                                    <th>调用方式</th>
                                    <th>参数</th>
                                    <th>状态</th>
                                    <th>定时计划</th>
                                    <th>创建日期</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                               <c:forEach var="task" items="${taskList}" varStatus="stauts">
	                                <c:choose><c:when test="${stauts.count%2 == 1}"><tr class="odd"></c:when><c:otherwise><tr class="even"></c:otherwise></c:choose>
	                                <td align="center">${stauts.count}</td>
	                                <td align="center">${task.taskid}</td>
	                                <td align="center">${task.taskdesc}</td>
	                                <%-- <td align="center">${task.taskdesc}</td> --%>
	                                <%-- <td align="center">${task.tasktrigger}</td> --%>
	                                <%-- <td align="center">${task.execclass}</td> --%>
	                                <%-- <td align="center">${task.tasksystem}</td> --%>
	                                <td align="center">${task.taskurl}</td>
	                                <td align="center">${task.taskpost}</td>
	                                <td align="center">${task.taskparams}</td>
	                                <td align="center">${task.state}</td>
	                                <td align="center">${task.cronexpression}</td>
	                                <td align="center">${task.createdate}</td>
	                                <td align="center">
	                                    <p> 
	                                    	<c:if test="${task.state=='暂停'}"><a href='${home}taskmgr/resumeTask?taskid=${task.taskid}'> 恢复</a></c:if>
	                                    	<c:if test="${task.state=='正常'}"><a href='${home}taskmgr/stopTask?taskid=${task.taskid}'>暂停</a></c:if>
	                                        <a href='javascript:void(0)' taskId="${task.taskid}" class='updatetaskid'  >修改</a>
	                                        <a href='${home}taskmgr/execTask?taskid=${task.taskid}'> 执行</a>
	                                        <a href='${home}taskmgr/getTaskHis?taskId=${task.taskid}'>历史</a>
	                                        <a href="javascript:if(confirm('确认删除吗?'))window.location='${home}taskmgr/deleteTask?taskid=${task.taskid}'"> 删除</a>
	                                    </p>
	                                </td>
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
<div id="loginmodal" style="display:none;" title="创建新任务">
    <form id="taskform" name="taskform" method="post" action="javascript:void(0)">

      <!-- <label for="taskid">任务ID:</label> -->
      <input type="hidden" name="taskid" id="taskid" class="txtfield" tabindex="1">

      <p>	
      		<label for="taskname">任务描述:</label>
      		<input type="text" name="taskname" id="taskname" class="txtfield" tabindex="2">
	  </p>
      <!-- <label for="taskclass">任务路径:</label>
      <input type="text" name="taskclass" id="taskclass" class="txtfield" tabindex="3" value="com.xr.xuritask.task.XuriTask">
       -->
      <p> 
	      <label for="taskurl">调用URL:</label>
	      <input type="text" name="taskurl" id="taskurl" class="txtfield" tabindex="4">
	      完整url如 http://www.baidu.com
	  </p>
	  <p> 
	      <label for="taskpost">调用方式：</label>
	      <select id="taskpost" tabindex="3">
	      	<option value="2">GET</option>
	          <option value="1">POST</option>
	      </select>
	  </p>
	  <p> 
	      <label for="taskparams">参数:</label>
	      <input type="text" name="taskparams" id="taskparams" class="txtfield" tabindex="5">
	      GET方式参数无需填写，格式为name1=value1,name2=value2
	  </p>
	  <p> 
	      <label for="cronexpression">时间计划:</label>
		  <input type="text" name="cronexpression" id="cronexpression" class="txtfield" tabindex="6"> 
		  每天凌晨2点执行如 0 2 * * * ?     
	  </p>
      <div class="footer">
          <a href="javascript:void(0)" class="btn btn-primary" id="summitid">提交</a>
          <a href="javascript:void(0)" class="btn btn-primary" id="cancelid">取消</a>
      </div>
      <p></p>
    </form>
  </div>
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

<script src="${home}resources/taskmanager.js"></script>

<script>
$(function(){
	 pageSetUp();
	 $('#dt_basic').dataTable({});
	 
})
</script>