$(document).ready(function(){
	toastr.options = {
	  "closeButton": false,
	  "debug": false,
	  "progressBar": false,
	  "positionClass": "toast-top-center",
	  "onclick": null,
	  "showDuration": "400",
	  "hideDuration": "1000",
	  "timeOut": "2000",
	  "extendedTimeOut": "1000",
	  "showEasing": "swing",
	  "hideEasing": "linear",
	  "showMethod": "fadeIn",
	  "hideMethod": "fadeOut"
	};
});

//获取框架的高度
function getFrameHeight(){
	return $("#content-main").height()-5;
}

//退出登录
function logout(){
	swal({
        title: "您确定要退出系统吗？",
        type: "warning",
        showCancelButton: true,
        confirmButtonColor: "#DD6B55",
        confirmButtonText: "确认",
        cancelButtonText:"取消",
        closeOnConfirm: true
    }, function () {
    	window.location.href=ctx.path+'/platform/pm/logout.do';
    });
}