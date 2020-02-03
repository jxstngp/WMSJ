/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
(function ($) {
	$.extend($.validator.messages, {
		required: "必填字段！",
		remote: "请修正该字段！",
		email: "请输入正确格式的电子邮件！",
		url: "请输入正确的网址！",
		date: "请输入正确的日期！",
		dateISO: "请正确的日期格式 (1991-12-30)！",
		number: "请输入数字！",
		digits: "请输入整数！",
		creditcard: "请输入正确的信用卡号！",
		equalTo: "请再次输入相同的值！",
		accept: "请输入拥有合法后缀名的字符串！",
		maxlength: $.validator.format("该信息不得超过 {0} 个字符！"),
		minlength: $.validator.format("该信息不得少于 {0} 个字符！"),
		rangelength: $.validator.format("请输入{0} 到 {1} 个字符！"),
		range: $.validator.format("请输入一个介于 {0} 和 {1} 之间的值！"),
		max: $.validator.format("请输入一个最大为 {0} 的值！"),
		min: $.validator.format("请输入一个最小为 {0} 的值！")
	});
}(jQuery));