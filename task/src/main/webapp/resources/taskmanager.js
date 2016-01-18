var TASK_CLASS = 'com.songwie.task.task.MyTask';

function query(){
	var baseurl = $("#home").val();
	var myWindow = window.open(baseurl+"taskmanager/newtask.jsp", "MsgWindow", "width=200, height=100");

    };

$(function(){
	var currdate = new Date();
	var startdate = currdate.format('yyyy-MM-dd');
	currdate.setDate(1);

	var enddate = currdate.format('yyyy-MM-dd');

    $('#addBtnId').bind("click",function(){
    	$("#loginmodal").dialog({width:600});
    });

    $("#summitid").bind("click",function(){
    	var taskname = $("#taskname").val();
		var taskclass = TASK_CLASS;
		var taskurl = $("#taskurl").val();
		var taskpost = $("#taskpost").val();
		var taskparams = $("#taskparams").val();
		var cronexpression = $("#cronexpression").val();
		var taskid = $("#taskid").val();

		if(taskname==null||taskname==""){
			return;
		}
		if(taskurl==null||taskurl==""){
			return;
		}
		if(taskpost==null||taskpost==""){
			return;
		}
		if(taskclass==null||taskclass==""){
			return;
		}
		if(cronexpression==null||cronexpression==""){
			return;
		}


		var data = {
		   taskname : taskname,
		   taskclass : taskclass,
		   taskurl : taskurl,
		   taskpost : taskpost,
		   taskparams : taskparams,
		   cronexpression : cronexpression,
		   taskid : taskid
		};

		var baseurl = $("#home").val();
		var form = document.createElement("form");
		document.body.appendChild(form);
		for(var name in data){
			var i = document.createElement("input");
			i.type = "hidden";
			form.appendChild(i);
			i.value = data[name];
			i.name = name;
		}

		form.action = baseurl + "taskmgr/saveTask";
		form.method="post";
		form.submit();
	});
    //关闭弹窗时，清除弹窗中所有input中的数据
    $("#cancelid").bind("click",function(){
    	$.each($("#loginmodal input"),function(i,val){
    		$(val).val("");
    	});
    	$("#loginmodal").dialog("close");
	});

    $(".updatetaskid").bind("click",function(event){
    	var taskId = $(this).attr("taskId");
    	var baseurl = $("#home").val();
    	$.ajax({
    		url:baseurl+'taskmgr/getTaskById',
    		type:'POST',
    		data:{taskId:taskId},
    		dataType:'json',
    		error: function(){alert('Error loading  json data');},
    		success: function(result){
    			$("#taskname").val(result.jobName);
    			$("#taskclass").val("taskclass");
    			$("#taskurl").val(result.taskUrl);
    			$("#taskpost").val(result.postType);
    			$("#taskparams").val(result.params);
    		    $("#cronexpression").val(result.cronExpression);
    		    $("#taskid").val(result.Id);
    		    $('#addBtnId').trigger("click");
    		}
    	});
	});



});
