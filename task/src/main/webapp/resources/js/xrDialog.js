$(function()
{
	//对话框
	
});
var ldp = {};  //命名空间

ldp.dialog = {};
ldp.dialog.resultMsg=function(msg,title){
	if($("#_xrDialog_").length>0){
		$("#_xrDialog_").remove();
	}
	var newDiv = $("<div></div>");
	newDiv.attr("id","_xrDialog_");
	newDiv.attr("title",title);
	var newP = $("<p></p>");
	newP.html(msg);
	newDiv.append(newP);
	$("body").append(newDiv);
	 $( "#_xrDialog_" ).dialog({
		 modal: true,
		  buttons: [
		    {
	    	text: "关闭",
		      click: function() {
		    	  
		        $( this ).dialog( "close" );
		        return true;
		      }
		    }
		  ]
	 });
};
ldp.dialog.confirm=function(msg,title,callback){
	
	
	if($("#_xrDialog_").length>0){
		$("#_xrDialog_").remove();
	}
	
	
		var newDiv = $("<div></div>");
		newDiv.attr("id","_xrDialog_");
		newDiv.attr("title",title);
		var newP = $("<p></p>");
		newP.html(msg);
		newDiv.append(newP);
		$("body").append(newDiv);
	 $( "#_xrDialog_" ).dialog({
		 modal: true,
		  buttons: [
		    {
	    	text: "取消",
		      click: function() {
		        $( this ).dialog( "close" );
		      }
		    },
		    {
			      text: "确认",
			      click: function() {
			        $( this ).dialog( "close" );
			        
			        callback.call();
			      }
		    }
		  ]
		 
		 
	 });
};

