// 上传类型集合
var fileType = [
	"jpg,gif,png,bmp,jpeg",
	"wav,mp3",
	"txt,csv,docx,doc,xlsx,xls",
	"jpg,gif,png,bmp,jpeg,txt,csv,docx,doc,xlsx,xls,wav,mp3"
];
/**
 * 图片
 * @param divId 触发上传的onclick元素ID
 * @param type	上传文件类型
 */
function fileUpload(divId,type){
	getHtmlInit(divId,type);
	
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : divId + "Id",
		multi_selection: false,
		url : ctx.path+'/platform/common/fileUpload'+ctx.noAuthSuffix,
		file_data_name :'file',
		unique_names:true,
		chunk_size:0,  
		flash_swf_url : ctx.path + '/resources/components/plupload/Moxie.swf',
		silverlight_xap_url : ctx.path + '/resources/components/plupload/Moxie.xap',
		filters: {
			mime_types : [ //只允许上传图片文件和rar压缩文件
               {
            	   title : "图片文件", 
            	   extensions : fileType[type]
               }
			],
			max_file_size : '10240kb', //最大只能上传10Mb的文件
			prevent_duplicates : false //不允许队列中存在重复文件
		}
	});
	uploader.init(); //初始化
		
	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		  $.each(uploader.files, function (i, file) { //保留最新的第一个文件
			  if (uploader.files.length <= 1) {
				  return;
			  }
			  uploader.removeFile(file);
		  });		
		  uploader.start(); //开始上传
	});

	//上传完文件返回真实文件名称
	uploader.bind('FileUploaded',function(uploader,file,responseObject){
	var result = eval("("+responseObject.response+")");
		if(result.rs==1){
			$("#" + divId).find("input").eq(0).val(result.data.attachmentPath);
			if(type != 0){
				$("#" + divId).find("span").eq(2).html(result.data.attachmentName);
			}
			getHtmlInit(divId,type);
			top.toastr.success("上传成功！");
		}else {
			top.toastr.error("上传失败！");
		}
	});
	 
	uploader.bind('Error',function(uploader,err){
		var fileExe = err.file.name.substring(err.file.name.lastIndexOf('.') + 1);
		if(err.file.size>(1024*1024*10)){
			top.toastr.error("对不起，上传文件不能超过10M，请重新上传！");
		} else if(fileType[type].indexOf(fileExe) < 0){
			top.toastr.error("对不起，上传文件格式不对，请重新上传格式为" + fileType[type] + "！");
		} else{
			top.toastr.error("上传失败！");
		}
	});
}

// 初始化html
function getHtmlInit(divId,type){
	if(type == 0){
		if($("#" + divId).find("input").val() != ''){
			$("#" + divId).find("img").attr("src", ctx.path + $("#" + divId).find("input").val());
			$("#" + divId).find("img").show();
			$("#" + divId).find("a").eq(0).attr("style","display: inline; padding-left: 10px");
			$("#" + divId).find("a").eq(0).on("click", function(){
				imgView($("#" + divId).find("input").val());
			});
			$("#" + divId).find("a").eq(1).attr("style","display: inline;");
			$("#" + divId).find("a").eq(1).on("click", function(){
				location = ctx.path + '/platform/common/download'+ctx.bizSuffix+'?url=' + $("#" + divId).find("input").val();
			});
			$("#" + divId).find("a").eq(2).find("span").html("重新上传");
		} else {
			$("#" + divId).find("a").eq(2).find("span").html("上传");
		}
		$("#" + divId).find("a").eq(2).attr("id", divId + "Id");
	} else {
		if($("#" + divId).find("input").val() != ''){
			$("#" + divId).find("span").eq(0).html("重新上传");
		}
		$("#" + divId).find("button").attr("id", divId + "Id");
	}
}