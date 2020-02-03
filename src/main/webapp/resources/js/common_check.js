var checkUtil = {
	isNumber : function(str){//是否是数字
		var reg = /^\d+$/;
		return reg.test(str);
	},
	isInt : function(str){//是否是正整数
		var reg = /^[1-9]{1,}[0-9]*$/;
		return reg.test(str);
	},
	isNaturalNumber:function(str){//是否是自然数
		return isInt(str) || str==0;
	},
	isChinaOrNumbOrLett:function(str){//判断是否是汉字、字母、数字组成
		var regu = /^[0-9a-zA-Z\u4e00-\u9fa5]+$/;
		return regu.test(str);
	},
	isNumberOrLetter:function(str){//判断是否是数字或字母 (6-14位)
		var regu = /^[0-9a-zA-Z]{6,14}$/;
		return regu.test(str);
	},
	isMobileNum:function(str){//验证手机号
		var regu =/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
		return regu.test(str);
	},
	isTelephoneNum:function(str){//验证电话号
		var regu = /^(([0\+]\d{2,3}-?)?(0\d{2,3})-?)?(\d{7,8})(-?(\d{3,}))?$/ ;
		return regu.test(str);
	},
	isPhone:function(str){//验证手机号 或者 验证电话号
		return this.isTelephoneNum(str) || this.isMobileNum(str);
	},
	isEmail:function(str){//email格式验证
		var regu =/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		return regu.test(str);
	},
	isNull:function(str){//验证是否为空
		var value = $("#" + id).val();
		value = $.trim(value);
		if (value == "") {
			return true;
		} else {
			return false;
		}
	},
	isNullValue:function(value){//验证是否为空
		value = $.trim(value);
		if (value == "") {
			return true;
		} else {
			return false;
		}
	},
	getStrLength:function(str){//获取字符串字节长度
		return str.replace(/[^\x00-\xff]/g, 'xx').length;
	},
	validateWebsite:function(){//网址的验证
		 var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
	    + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
	    + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
	    + "|" // 允许IP和DOMAIN（域名）
	    + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
	    + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
	    + "[a-z]{2,6})" // first level domain- .com or .museum
	    + "(:[0-9]{1,4})?" // 端口- :80
	    + "((/?)|" // a slash isn't required if there is no file name
	    + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	    var re=new RegExp(strRegex);
	    if (re.test(str_url)){
	        return true;
	    }else{
	        return false;
	    }
	}
};