//是否特殊字符
jQuery.validator.addMethod('specChar', function(invalue, element){
	return this.optional(element) || invalue.match(/^[^<>&@*#]*$/);
},"请不要输入特殊字符作为登录名!");

//扩展方法，校验正负整数
jQuery.validator.addMethod('digitIncludeMinus', function(value, element) {
	return this.optional(element) || (/^([-])?\d+$/.test(value));
  }, '请输入正确的数字！');

//扩展方法，校验是否是手机号
jQuery.validator.addMethod('isMobile', function(value, element){
	var length = value.length;
	mobile = /^1[3578]\d{9}$/;
	mobile1 = /^147\d{8}$/;
	return this.optional(element) || (mobile.test(value) && length == 11 ) || (mobile1.test(value) && length == 11 );
},"请输入正确的手机号码");

//扩展方法，校验是否是电话号码
jQuery.validator.addMethod('isTel', function(value, element){
	tel = /^0[0-9]{2,3}\d{6,9}$/;
	mobile = /^1[3578]\d{9}$/;
	mobile1 = /^147\d{8}$/;
	return this.optional(element) || (tel.test(value)) || (mobile.test(value)) || (mobile1.test(value));
},"请输入正确的电话号码");

//扩展方法，校验IP地址
jQuery.validator.addMethod('ipAddr', function(ipvalue, element) {
    return this.optional(element) ||ipvalue.match(/^\d+\.\d+\.\d+\.\d+$/);
  }, '请输入有效的IP地址');

//扩展方法，校验qq
jQuery.validator.addMethod('qq', function(value, element) {
	qq = /^\d{5,11}$/;
	return this.optional(element) || (qq.test(value));
  }, '请输入正确的QQ号码！');

//扩展方法，校验身份证号码
jQuery.validator.addMethod('idcard', function(value, element) {
	idcard = /^[0-9a-zA-Z]{18}$/;
	return this.optional(element) || (idcard.test(value));
  }, '请输入正确的身份证号码！');

//扩展方法，校验邮政编码
jQuery.validator.addMethod('postCode', function(value, element) {
	postCode = /^[1-9]\d{5}(?!\d)$/;
	return this.optional(element) || (postCode.test(value));
  }, '请输入正确的邮政编码！');

//扩展方法，校验400号码
jQuery.validator.addMethod('isEps400', function(value, element) {
	value = $.trim(value);
	element.value = value; 
	eps400 = /^400\d{7}$/;
	return this.optional(element) || (eps400.test(value));
  }, '请输入正确的400号码！');

//扩展方法，钱数，必须是非负整数或小数，小数点后最多3位
jQuery.validator.addMethod('money', function(value, element) {
	value = $.trim(value);
	element.value = value; 
	money = /^[0-9]{1,9}([.]{1}[0-9]{1,3})?$/;
	return this.optional(element) || (money.test(value));
  }, '请输入正确的金额');

//扩展方法，小数点后最多3位判断,包括整数和负数
jQuery.validator.addMethod('threePoint', function(value, element) {
	value = $.trim(value);
	element.value = value; 
	money = /^-?\d+([.]{1}[0-9]{1,3})?$/;
	return this.optional(element) || (money.test(value));
  }, '请输入小数点后最多三位的数字');

//扩展方法，金额必须大于0，且非负的小数点后最多3位
jQuery.validator.addMethod('moneyOverZero', function(value, element) {
	value = $.trim(value);
	if(value <= 0){
		return false;
	} else {
		return true;
	}
	/*element.value = value; 
	var length = value.length;
	money = /^[0-9]+([.]{1}[0-9]{1,3})?$/;
	return this.optional(element) || (money.test(value));*/
  }, '请输入大于0的数字');

//扩展方法，代理提款
jQuery.validator.addMethod('accountMoney', function(value, element) {
	var flag = false;
	value = $.trim(value);
	var accMoney = $("#accountMoney").val();  //代理账户金额
	if (Number(accMoney) >= Number(value)) {
		flag = true;
	}
	return flag;
  }, '申请提款金额不能大于代理账户金额！');

//扩展方法，代理商编码只限数字或字母
jQuery.validator.addMethod('isNumOrChar', function(value, element) {
	value = $.trim(value);
	element.value = value;
	numOrChar = /^[0-9a-zA-Z]*$/;
	return this.optional(element) || (numOrChar.test(value));
  }, '只能由数字或字母组成');

//扩展方法，上传文件大小验证
jQuery.validator.addMethod('fileSize', function(value, element) {
	var flag = false;
	var limit_size = 2 * 1024 * 1024;
	var upload_size = 0;
	if(value != null && $.trim(value) != ''){
		upload_size = $("input[type='file']")[0].files[0].size;
	}
	if (Number(limit_size) >= Number(upload_size)) {
		flag = true;
	}
	return flag;
  }, '上传附件不能超过2M');

//扩展方法，上传文件必须以.csv扩展名
jQuery.validator.addMethod('uploadFile', function(value, element) {	
	var reg = /\.(csv)$/;
	var reg1 = /\.(CSV)$/;
	return this.optional(element) || reg.test(value) || reg1.test(value);
  }, '请上传格式为.csv的文件!');

//扩展方法，选择了省份校验城市也要填,之前的数据不为空的情况下、与之关联的也不为空
jQuery.validator.addMethod('beforeIsNotBlank', function(value, element) {
	
	return false;
  }, '之前的数据不为空的情况下、与之关联的也不为空');

//扩展方法，代理商选择控件，输入不存在的代理商时验证
jQuery.validator.addMethod('unSelectProxyId', function(value, element) {
	var flag = false;
	var proxyId = $("#proxyId").val();
	if (proxyId != null && proxyId != '') {
		flag = true;
	}
	return flag;
  }, '请选择正确的代理商');

//扩展方法，公司选择控件，输入不存在的公司时验证
jQuery.validator.addMethod('unSelectEnterpriseId', function(value, element) {
	var flag = false;
	var enterpriseId = $("#enterpriseId").val();
	if (enterpriseId != null && enterpriseId != '') {
		flag = true;
	}
	return flag;
}, '请选择正确的公司');

//扩展方法，邮政编码验证
jQuery.validator.addMethod("postCode", function(value, element) {   
    var tel = /^[1-9]\d{5}(?!\d)$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的邮政编码");

//地址加端口
jQuery.validator.addMethod("ipAndPort", function(value, element) {   
    var tel =/^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9]):\d{0,5}$/;
    return this.optional(element) || (tel.test(value));
}, "请输入正确的IP地址和端口");
