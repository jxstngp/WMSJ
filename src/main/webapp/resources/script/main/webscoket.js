var websocket;
var baseUrl =  ctx.basePath;
var wsurl = baseUrl.replace("http://","");
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://"+wsurl+"/webSocketServer");
} else if ('MozWebSocket' in window) {
    websocket = new MozWebSocket("ws://"+wsurl+"/webSocketServer");
} else {
    websocket = new SockJS("http://"+wsurl+"/sockjs/webSocketServer");
}
websocket.onopen = function (evnt) {
};
websocket.onmessage = function (evnt) {
	//{"count":1,"data":[{"createDate":2015-9-21 13:33:35,"pageUrl":"proxy/user/loadUserList","receiveId":1,"remindContent":"您有一个提示要处理！"}]}
	var msg = eval( "(" + evnt.data + ")" );
	$("#remindNum").empty();
	$("#remindContent").empty();
	if(msg.count!=0){ //有消息提醒
		//动画效果
		$("#remindNum").append("<i class=\"midicon tips_icon animated swing\"><span class=\"tips_num\" >"+msg.count+"</span></i>");
		var content="<ul>";
		$.each(msg.data, function(key, val) {   
			content +="<li onclick=\"getMenu("+ val.menuId +","+ val.receiveId +")\"><a href=\"javascript:void(0);\"><i class=\"minicon sms_icon\"></i>"+ val.remindContent +"<span>"+val.createDate+"</span></a></li>";      
		});  
		content += "</ul><div class=\"tipall\"><a href=\"javascript:void(0);\" onclick=\"getMsgDetail()\">查看所有>></a><span onclick=\"readAll();\">全部阅读</span></div>";
		$("#remindContent").append(content);
	}else{
		$("#remindNum").append("<i class=\"midicon tips_icon\"></i>");
		$("#remindContent").append("<ul><li><a href=\"javascript:void(0);\"><i class=\"minicon sms_icon\"></i>暂无消息提醒！</a></li>");
	};
	


};
websocket.onerror = function (evnt) {
	toastr.error("通信连接异常！");	
};
websocket.onclose = function (evnt) {
	//toastr.warning("已关闭通信连接！");	
}

function getMsgDetail(){
	$("#menu_60").click();
}
//点击查询并且消除记录
function getMenu(id,receiveld){
	$.ajax({
	    type: "POST",
	    url: ctx.path+"/api/remind/readRemind?receiveld="+receiveld,
	    dataType: "json",
	    success: function (result){
			if(result.rs==1){
				$("#menu_"+id).click();
			}
	    }
	});
}

//全部阅读
function readAll(){
	$.ajax({
	    type: "POST",
	    url: ctx.path+"/api/remind/readAllRemind",
	    dataType: "json",
	    success: function (result){
			
	    }
	});
}


//当浏览器关闭的时候触发的关闭websocket
function body_onUnload(){
	if(websocket!=null){
		websocket.close();
	}
}

