// JavaScript Document
var channel_account_add_btn_ststus = 1;
$(function() {

	loginHeight();
	// 显示当前列表td未显示的内容
	$(".tablelist_tbody td").mouseover(function() {
		// 当前对象宽度
		// var objwidth = $(this).width();
		var status = true;
		var text = $.trim($(this).html());
		// text = text.replace("/^\s*|\s*$/g", "");
		var arr = new Array("buttom", "span", "label");
		for ( var item in arr) {
			if (text.indexOf(arr[item]) > -1) {
				status = false;
			}
		}
		if (status) {
			$(this).attr("title", text);
		}
	});

	// 地区选择
/*	$(".saleset_add").click(function(event) {
		setUpSites(this.jsonClassName);
		event.stopPropagation();
		var $contentW = $(this).parents(".saleset_con").width();
		var $left = $(this).offset().left;
		$(".saleset_add").siblings("dl").hide();
		if (parseInt($contentW) - parseInt($left) < 472) {
			$(this).siblings("dl").css({
				left : parseInt($contentW) - parseInt($left) - 472
			});
		}
		$(this).siblings("dl").show();
	});
	$(document).click(function() {
		$(".saleset_nav").find("dl").hide();
	});*/

	// 提醒
	$("#tips,#announce").click(function(event) {
		event.stopPropagation();
		$(this).siblings("dd").find(".tipcon").hide();
		$(".admin_con").removeClass("open");
		$(this).children(".tipcon").show();
	});
	$(document).click(function() {
		$("#tips,#announce", top.document).children(".tipcon").hide();
		$(".admin_con", top.document).removeClass("open");
		// 清空输入框联想内容
		if ($("#user_type_ids", top.document).val() == "" || $("#user_type_ids", top.document).val() == undefined) {
			$("#inputusername", top.document).val("");
		}
		// 清空通道名称内容
		if ($("#channel_ids", top.document).val() == "" || $("#channel_ids", top.document).val() == undefined) {
			$("#inputchannel", top.document).val("");
		}
		// 清空网关名称内容
		if ($("#server_ids", top.document).val() == "" || $("#server_ids", top.document).val() == undefined) {
			$("#inputserver", top.document).val("");
		}
		$(".inputusernameall").each(function() {
			if ($(this, top.document).val() == "" || $(this, top.document).val() == undefined) {
				var $objthis = $(this).attr("data-id-name");
				$("#" + $objthis, top.document).val("");
			}
		});
		$("#inputusernamelist").hide();
	});
	$(".admin_toggle").click(function() {
		$(this).parents("dd").siblings().find(".tipcon").hide();
	});
	// 省份选择
	$(".region_down").click(function(event) {
		event.stopPropagation();
		$(this).next(".region_box").show();
	});
	$(document).click(function() {
		$(".region_box").hide();
	});
	$(".region_con li").click(function(event) {
		var $text = $(this).text();
		$(this).parents(".region_box").prev(".region_down").find("span").text($text);
	});

	$(".navbox .home_pannel").click(function() {
		$(this).find("a").removeClass("collapsed");
		$(".panel_con .panel_tit").find("a").addClass("collapsed");
	});
	$(".panel_con .panel_tit").click(function() {
		$(".home_pannel").find("a").addClass("collapsed");
	});

	// 二级菜单点击
	$(".panel_body li").click(function() {
		$(".home_pannel").find("a").addClass("collapsed");
		$(".panel_body li").removeClass("current");
		$(this).addClass("current").parents(".panel_body").siblings(".panel_tit").find("a").removeClass("collapsed");
	});

	// 首页单点击
	$(".home_pannel").click(function() {
		$(this).find("a").removeClass("collapsed");
		$(".panel_tit").find("a").addClass("collapsed");
		$(".panel_body li").removeClass("current");
	});

	// 登录输入框获得焦点
	$(".logintext .text").focus(function() {
		$(this).parents(".logintext").addClass("focus");
	});

	// 登录输入框失去焦点
	$(".logintext .text").blur(function() {
		$(this).parents(".logintext").removeClass("focus");
	});

	$(".checkbox").click(function() {
		if (!$(this).hasClass('disabled')) {
			$(this).toggleClass("checked");
		}
	});
	$(document).on('click', ".radio", function() {
		if (!$(this).hasClass('disabled')) {
			var val = $(this).attr('value');
			$(this).addClass("checked").siblings().removeClass("checked").siblings("input[type='hidden']").val(val);
		}
	});

	// 时间控件
	$(".start_datetime").datetimepicker({ // 开始时间
		minuteStep : 1,
		format : "yyyy-mm-dd hh:ii", // 选择日期后，文本框显示的日期格式
		language : 'zh-TW', // 汉化
		autoclose : true
	// 选择日期后自动关闭
	}).on("click", function(ev) {
		$(".start_datetime").datetimepicker("setEndDate",$(".end_datetime").val());
	});
	$(".end_datetime").datetimepicker({ // 结束时间
		minuteStep : 1,
		format : "yyyy-mm-dd hh:ii", // 选择日期后，文本框显示的日期格式
		language : 'zh-TW', // 汉化
		autoclose : true
	// 选择日期后自动关闭
	}).on("click", function(ev) {
		$(".end_datetime").datetimepicker("setStartDate",$(".start_datetime").val());
	});

	$(".create_date").datetimepicker({ // 付款时间
		minView : "month", // 选择日期后，不会再跳转去选择时分秒
		format : "yyyy-mm-dd", // 选择日期后，文本框显示的日期格式
		language : 'zh-TW', // 汉化
		autoclose : true
	// 选择日期后自动关闭
	});
	// 下拉列表控件触发
	// $(".select").select2();

	tablelistboxHeight();
	tableSize();
	$(window).resize(function() {
		loginHeight();
		tablelistboxHeight();
		tableSize();
	});
	 //横向滚动条
	// $(".mCustomScrollbar_x").mCustomScrollbar({
	// axis:"x",
	// autoHideScrollbar:true,
	// theme:"3d-thick",
	// scrollInertia:0,
	// });

	// 纵向滚动条
	$(".mCustomScrollbar_y").mCustomScrollbar({
		axis : "y", // horizontal scrollbar
		scrollbarPosition : "outside",
		autoHideScrollbar : true,
		scrollInertia : 0,
	});



	// 表格滚动条
	$(".tablelist_tbody").scroll(function() {
		$(".tablelist_thead").scrollLeft($(this).scrollLeft());
	});

	// 提示小工具
	$('[data-toggle="tooltip"]').tooltip({
		trigger : 'hover',
	});

	// 首页图表下拉列表点击效果
	$(".chart_tit .dropmenu li").click(function() {
		var $text = $(this).text();
		$(this).addClass("current").siblings().removeClass("current");
		$(this).parent(".dropmenu").siblings(".droptit").find(".text").text($text);

		// 讲字符串转为调用方法
		var f_name = eval("(" + $(this).attr('data-function') + ")");
		// 调用方法转参数
		f_name();
	});

	/* 重置按钮 */
	$(".reset_button").click(function() {
		var $form = $(this).parents("form");
		$form.find(".inputtext").val('');
		$form.find("select").each(function() {
			var $hiddenval = $(this).next(":hidden").val();
			$(this).val($hiddenval);
		});
	});


	// 网关联想返回信息
	$("div").delegate("#inputserver", "keyup", function() {
		objs = this;
		var width = $(this).outerWidth();
		var top = $(this).offset().top + $(this).outerHeight() + 1;
		var left = $(this).offset().left;
		var name = $(this).val();
		if (name != "") {
			$.post("/index.php/Admin/Index/ajax_server", {
				name : name
			}, function(data) {
				$("#server_ids", top.document).attr("value", ""); // 此代码无法运行
				if (data.info) {
					var html = "";
					html += "<ul>";
					for (var i = 0; i < data.info.length; i++) {
						html += '<li onclick="server_all('
								+ data.info[i].id + ',\''
								+ data.info[i].name + '\',' + '\''
								+ data.info[i].code + '\')">'
								+ data.info[i].name + '</li>';
					}
					html += "</ul>";
					$("#inputusernamelist").show().css("width", width + "px").css("top", top + "px").css('left', left + "px").html(html);
				} else {
					$("#inputusernamelist").hide().html("");
				}
			}, "json");
		} else {
			$("#inputusernamellist").hide().html("");
		}
	});

	$(".tab_hd h3").click(
			function() {
				var $index = $(this).index();
				$(this).addClass("active").siblings().removeClass("active");
				$(this).parent().next(".tab_bd").children().eq($index).show().siblings().hide();
			});
	// 查找当前页面日期选择框如果出现+号的直接替换
	$(".inputdateall").each(function() {
		var dates = $(this).val();
		if (dates != "") {
			if (dates.indexOf("+") > -1) {
				var datea = dates.replace("+", " ");
				$(this).val(datea);
			}
		}
	});

	/*
	 * //将当前位置的图片和导行标题换成当前分类图标和导行标题(每页头部导行图标和导行标题)
	 * if($('.currenttit').children('i').length > 0){ //导行图标 var icons =
	 * $("#"+newaction,top.document).attr("data-icon");
	 * $('.currenttit').children('i').addClass("midicon "+icons); //导行标题
	 * if($("#"+newaction,top.document).attr("data-title")!=null){ var title =
	 * $("#"+newaction,top.document).attr("data-title").split("|"); var ht = '';
	 * for (i=0;i<title.length ;i++ ){ var ii = i+1; ht+="<em>"+title[i]+"</em>";
	 * if(title[ii])ht+=">"; } $('.currenttit').children('span').html(ht); } }
	 */

	// 如页面未读到显示数据信息则将提示句中TD合并的个数调设为标题的总个数
	var colspan_int = $(".tablelist_tbody table tr td").attr("colspan");
	if (colspan_int == 20) {
		var thlength = $(".tablelist_thead table tr th").length;
		$(".tablelist_tbody table tr td").attr("colspan", thlength);
	}


});


// 获取网关选择出来名称并赋值
function server_all(id, name, code) {
	var text = name;
	$(objs).val(text);
	$("#server_ids").val(id);
}

// 重新加载下拉和滚动条
function mCustomScrollbar() {
	// $(".select",parent.document).select2();
	// 纵向滚动条
	$(".mCustomScrollbar_y").mCustomScrollbar({
		axis : "y", // horizontal scrollbar
		scrollbarPosition : "outside",
		autoHideScrollbar : true,
		scrollInertia : 0,
	});

	$("input[vtype='tel'],input[vtype='mobile'],input[vtype='money'],input[vtype='icense'],input[vtype='identity']", top.document).keypress(function(event) {
		var type = $(this).attr('vtype');
		var eventObj = event || e;
		var keyCode = eventObj.keyCode || eventObj.which;
		// alert('keyCode '+keyCode);
		switch (type) {
		// 48-57:数字 45:- 8:后退 116:F5 13：回车 9:table制表键 123:F12 46:删除键
		case 'tel':
			if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 45)
					|| (keyCode == 67) || (keyCode == 86)
					|| (keyCode == 118) || (keyCode == 99)
					|| (keyCode == 8) || (keyCode == 13)
					|| (keyCode == 123) || (keyCode == 46)
					|| (keyCode == 37) || (keyCode == 39)
					|| (keyCode == 116) || (keyCode == 9))
				return true;
			else
				return false;
			break;

		case 'mobile':
			if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 8)
					|| (keyCode == 67) || (keyCode == 86)
					|| (keyCode == 118) || (keyCode == 99)
					|| (keyCode == 13) || (keyCode == 123)
					|| (keyCode == 46) || (keyCode == 37)
					|| (keyCode == 39) || (keyCode == 116)
					|| (keyCode == 9))
				return true;
			else
				return false;
			break;

		case 'money':

			if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 46)
					|| (keyCode == 8) || (keyCode == 13)
					|| (keyCode == 123) || (keyCode == 46)
					|| (keyCode == 37) || (keyCode == 39)
					|| (keyCode == 116) || (keyCode == 9))
				return true;
			else
				return false;

			break;

		case 'icense':
			if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 8)
					|| (keyCode == 13) || (keyCode == 123)
					|| (keyCode == 46) || (keyCode == 37)
					|| (keyCode == 39) || (keyCode == 116)
					|| (keyCode == 9))
				return true;
			else
				return false;
			break;

		case 'identity':
			if ((keyCode >= 48 && keyCode <= 57) || (keyCode == 120)
					|| (keyCode == 8) || (keyCode == 13)
					|| (keyCode == 123) || (keyCode == 46)
					|| (keyCode == 37) || (keyCode == 39)
					|| (keyCode == 116) || (keyCode == 9))
				return true;
			else
				return false;
			break;

		}

	});

	$('input,select', top.document).focus(function() {
		$(this).parents(".add_value").nextAll("div[class='error']").remove();
	});


}


//代码生成高度计算

// 表单区域高度计算函数
function tablelistboxHeight() {
	var $searchboxH = $(".search_box").outerHeight();
	var $totalH = parseInt($searchboxH) + 42 + 52;
	$(".tablelistboxH").css("height", "calc(100% - " + $totalH + "px)");
}

//gird
function tableGridHeight(){
	var h=$(window).height();
	var $topH = $(".headerbox").outerHeight();
	var $totalH = parseInt($topH) +32 + 30 + 10;
	return parseInt(h-$totalH);
}

//通用列表宽度计算
function tableSize(){
	var $thead=$(".tablelist_thead");
	var $theadTh=$(".tablelist_thead th");
	var $tbody=$(".tablelist_tbody");
	var $tbodyTr=$(".tablelist_tbody tr");
	var $firstTd=$(".tablelist_tbody tr:first td");
	var tablelistW=$(".tablelist_tbody table").innerWidth();
	var $w=parseInt(tablelistW)-20-2;
	var $totalWidth=0;
	var $changeW=0;
	var $unchangeW=0;
	var $theadW=0;
	btnTdSize($thead,$tbody,"operation_th","operation_td");
	$theadTh.each(function(i) {
		var $thWidth=parseInt($(this).attr("width"));
		$totalWidth=$totalWidth+$thWidth;
		if($(this).is(".change")){
			$(this).css("width",$thWidth);
			$firstTd.eq(i).css("width",$thWidth);
			$changeW=$changeW+$thWidth;
		} else{
			$(this).css("width",$thWidth);
			$firstTd.eq(i).css("width",$thWidth);
			$unchangeW=$unchangeW+$thWidth;
		}
	});
	//$theadW=$totalWidth;
	if($w>$totalWidth) {
		var $surplusW=$w-$unchangeW;
		$theadTh.each(function(i) {
			var $thWidth=parseInt($(this).attr("width"));
			if($(this).is(".change")) {
				var $thW=($thWidth/$changeW)*$surplusW;
				$(this).css("width",$thW);
				$firstTd.eq(i).css("width",$thW);
			} else {
				$(this).css("width",$thWidth);
				$firstTd.eq(i).css("width",$thW);
			}
		});
	}
	if($tbodyTr.length==1 && $firstTd.length==1){
		if($w>$totalWidth){
			$firstTd.css("width",$w);
		} else {
			$firstTd.css("width",$totalWidth);
		}
	}
	var $tablebodyw=$tbody.outerWidth();
	$theadW=$tablebodyw;
	if($tbody.get(0)){
		var $innerW=$tbody.get(0).clientWidth;
		var $outerW=$tbody.get(0).offsetWidth;
		var $sw=parseInt($outerW)-parseInt($innerW);
		if($sw>0){
			$thead.width($theadW-$sw);
		}else{
			$thead.width("100%");
		}
	}
}

//操作按钮列宽计算
function btnTdSize($thead,$tbody,operationth,operationtd){
	var $tdPaddingLeftW=$tbody.find("td").css("padding-left");
	var $tdPaddingRightW=$tbody.find("td").css("padding-right");
	var $tdPaddingW=parseInt($tdPaddingLeftW)+parseInt($tdPaddingRightW);
	var $btnWarr=[];
	$tbody.find("."+operationtd).each(function() {
		var $btn=$(this).find(".operationbtn");
		var $currentLen=$btn.length;
		var $btnWidth=0;
		for(var i=0;i<$currentLen;i++){
			$btnWidth+=$btn.eq(i).outerWidth(true);
		};
          
		$btnWarr.push($btnWidth);
	});
	var $operationtdW=0;
	for(j=0;j<$btnWarr.length;j++){
		if($operationtdW<$btnWarr[j]){
			$operationtdW=$btnWarr[j];
		}
	}
    if($operationtdW>0){	  
    	$thead.find("."+operationth).attr("width",$operationtdW+$tdPaddingW);
    }else{
    	$thead.find("."+operationth).attr("width",$tdPaddingW+30);
    }
}

function loginHeight() {
	var $winh = $(window).height();
	if ($winh <= 600) {
		return;
	} else {
		var $marginTop = ($winh - 600) / 2 - 20;
		$("body.white").css("padding-top", $marginTop);
	}
}

/**
 * 提示成功和失败信息
 * 
 * @data 数组型式包括状态和提示内容
 * @time 时间，默认为3秒
 */
function alertbox(data) {
	var msg;
	if (data.status == 0) {
		msg = data.info;
	} else {
		msg = data.msg;
	}
	var type = data.status == "success" ? "success" : "failed";
	var html = '<div class="alert alert_' + type + '" role="alert">';
	html += '<span>' + msg + '</span>';
	html += '<button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>';
	html += '</div>';
	setTimeout("closebox()", 4000);
	$("#prompt_msg", parent.parent.document).html(html);

}
/**
 * 关闭提示弹框
 */
function closebox() {
	$("#prompt_msg", parent.parent.document).html('');
}

/**
 * 刷新子页面后如果有弹框信息则关闭弹框信息
 */
$(function() {
	if ($("#prompt_msg", parent.document).html() != '') {
		setTimeout("closebox()", 1500);
	}
});

// 切换顶级菜单信息
function top_menu_show(ids) {
	var id = ids > 0 ? ids : 1;
	$("#top_menu_id").val("0");
	$(".systypeall").hide();
	$(".systype" + id).show();
}
// 切换用户通道的代理商和企业
/*function list_show() {
	var user_type = $("#user_type").val();
	if (user_type == 1) {
		$(".proxy_list").show();
		$(".enterprise_list").hide();
	} else {
		$(".proxy_list").hide();
		$(".enterprise_list").show();
	}
	$("#ids").val('');
}*/

/**
 * 关闭公告
 */
function close_notice() {
	$(".announce_con").css("display", "none");
}

function inputFocus(formname) {
	$("form[name='" + formname + "']", top.document).find("input[type='text']").first().focus();
}

/**
 * 弹出地区选择的框
 */
var ns = 0;
function showprovince(obj, n) {
	ns = n;
	var e = arguments.callee.caller.arguments[0] || window.event;
	window.event ? e.returnValue = false : e.preventDefault();
	window.event ? e.cancelBubble : e.stopPropagation();
	e.stopPropagation();
	var $top = parseInt($(obj).offset().top) + 30;
	var $left = $(obj).offset().left;
	$("#discount_province_box").css({
		position : "fixed",
		top : $top,
		left : $left,
		zIndex : 2000
	}).show();
	return false;
}

// 选择省份
/*function chooseprovince(province_id, province_name) {
	var fc = 1;
	var childlis = $("#dc_add_plist" + ns).children();

	$.each(childlis, function(i, n) {
		if ($(n).hasClass("vlic" + ns + province_id)) {
			fc = 0;
			return false;
		}
	});

	if (fc) {
		var vli = '<li id="vli' + ns + province_id + '" class="vlic' + ns + province_id + '"><label class="label">' + province_name + '</label>';
		vli += '<input type="text" class="inputtext" name="discount_number' + ns + province_id + '" value="" />';
		vli += '<span onclick="delprovince(' + ns + province_id + ')"><i class="minicon delete_icon"></i></span></li>';
		$("#dc_add_plist" + ns).append(vli);
	}
}*/

// 删除省份
function delprovince(oid) {
	$("#vli" + oid).remove();
}

function open_menu(id, msg, url) {
	openMenu(id, msg, url);
}

// 充值提示信息的更改
/*function payment_name() {
	var paymentname = $("#source").val();
	var text = new Array("打款户名", "支付订单号", "交易号", "打款户名");
	if (paymentname != "") {
		$("#source_name").html(text[paymentname - 1]);
		$("#source_input").attr("field", text[paymentname - 1]);
	} else {
		$("#source_name").html(text[0]);
		$("#source_input").attr("field", text[0]);
	}
}
function payment_name_channel() {
	var paymentname = $("#source").val();
	var text = new Array("打款户名", "支付订单号", "交易号", "授信", "打款户名");
	if (paymentname != "") {
		if (paymentname == 4) {
			$("#channel_soruce").hide();
			$("#channel_time").hide();
			$("#source_input").val("-1");
			$("#payment_date").val("-1");
		} else {
			if ($("#source_input").val() == "-1") {
				$("#source_input").val("");
				$("#payment_date").val("");
				$("#channel_soruce").show();
				$("#channel_time").show();
			}
			$("#source_name").html(text[paymentname - 1]);
			$("#source_input").attr("field", text[paymentname - 1]);
		}
	} else {
		$("#source_name").html(text[0]);
		$("#source_input").attr("field", text[0]);
	}
}*/

// 充值类型切换
/*function content_hide() {
	var recharge_type = $("#recharge_type").val();
	if (recharge_type == 1) {
		$(".recharge_type_div").show();
	} else {
		$(".recharge_type_div").hide();
	}
}*/

// 自定义回复切换
function customreply_type() {
	var replyname = $("#reply_type").val();
	$("#dtphf").hide();
	$("#mtphf").hide();
	$('#hdhf').hide();
	$("#wzhf").hide();
	if (replyname == 1) {
		$("#wzhf").show();
	}
	if (replyname == 2) {
		$('#dtphf').show();
	}
	if (replyname == 3) {
		$("#mtphf").show();
	}
	if (replyname == 4) {
		$("#hdhf").show();
	}
}

// 处理弹框中的金额将金额格式化并返回
function page_toThousands() {
	$("#layerdivid span , #layerdivid td").each(function() {
		$(this).html(js_toThousands($(this).html()));
	});
}

// 将 confirm 对话框中的数字格式化（传入字符串）
function js_toThousands(text) {
	var s = text, num;
	// var chr;
	// 提取数字
	num = s.match(/\d+(\.\d+)?/g);
	for (x in num) {
		var nums = num[x] + '元';
		if (s.indexOf(nums) > -1) {
			s = s.replace(num[x], toThousands(num[x]));
		}
	}
	return s;

	// 提取非数字字符
	// chr=s.match(/[^\d\.]/g)
	// alert(chr)
}

// 将方法传入的数字格式化（传入字符串和判断条件）
function js_toThousands2(text, content) {
	var s = text, num;
	// var chr;
	// 提取数字
	num = s.match(/\d+(\.\d+)?/g);
	for (x in num) {
		var nums = num[x] + content;
		if (s.indexOf(nums) > -1) {
			s = s.replace(num[x], toThousands(num[x]));
		}
	}
	return s;
}

/**
 * 将传入金额格式化并返回格式化后的数据
 * 
 * @param num
 *            金额
 * @returns {string}
 */
function toThousands(numi) {
	// 直接返回当前格式（如不需要格式化的时候打开直接返回的代码）
	return numi;
	//等于 0  直接返回
	numi = $.trim(numi);
	var numj = numi;
	if (numi == 0)
		return numi;
	//小于 0 去掉减号
	if (numj.indexOf('-') > -1) {
		var int = numi.split('-');
		numi = int[1];
	}
	//定义变量并将金额转成字符串加入数组中
	var num = (numi || 0).toString(), result = '', nums = [];
	if (num.indexOf('.') > -1) {
		nums = num.split('.');
		nums[1] = '.' + nums[1];
	} else {
		nums.push(num);
		nums.push('.000');
	}
	nums[0] = nums[0].replace(',', '');
	//将金额格式化
	while (nums[0].length > 3) {
		result = ',' + nums[0].slice(-3) + result;
		nums[0] = nums[0].slice(0, nums[0].length - 3);
	}
	if (nums[0]) {
		result = nums[0] + result;
	}
	//还原格式化后的金额
	if (numj.indexOf('-') > -1) {
		return '-' + result + nums[1];
	} else {
		return result + nums[1];
	}
}
/*
 *  表示显示过滤通道
 *  id用来标示是不是选择全国时出现所以市
 */
function get_is_filter(id) {
	var province_id = $("#province_id").val();
	if (id == 1 && province_id == "") {
		province_id = 1;
	}
	if (province_id == 1 && id != 1 || province_id == "") {
		$("#filter_type").hide();
		//$("#filter_city").hide();
		$("#is_filter").val(0);
		$(".filter_radio1").addClass("checked");
		$(".filter_radio2").removeClass("checked");
		var html = '<option value="" >请选择</option>';
		$("#city_id").html(html);
	} else {
		$("#filter_type").show();
		$.post("/index.php/Admin/Index/ajax_city", {
			province_id : province_id
		}, function(data) {
			if (data.info) {
				var html = '<option value="" >请选择</option>';
				for (var i = 0; i < data.info.length; i++) {
					html += '<option value="' + data.info[i]["city_id"] + '" >' + data.info[i]["city_name"] + '</option>';
				}
				$("#city_id").html(html);
			} else {
				$("#city_id").hide().html("");
			}
			$("#filter_city").show();
		}, "json");
	}
}