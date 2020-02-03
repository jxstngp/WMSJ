<#include "../constant/setHead.ftl" />
//代码生成方法JS对象
var ${cfp.moduleName?uncap_first}UpdateJs = {};

var frameId = window.frameElement && window.frameElement.id || '';
$(function(){
    //校验编辑表单
    $("#update${cfp.moduleName}Form").validate({
        rules : {
        },
        messages : {
        },
        submitHandler : function(form) {
            $(form).ajaxSubmit({
                //表单提交成功后的回调
                success : function(responseText, statusText, xhr, $form) {
                    if (responseText.rs > 0) {
                        top.toastr.success("编辑${cfp.tableNameZN}【】成功！");
                        parent.${cfp.moduleName?uncap_first}ManageJs.reloadGrid();
                        layer.closeAll(); //再执行关闭
                    }
                }
            });
        }
    });
});

//提交表单
${cfp.moduleName?uncap_first}UpdateJs.submitForm = function(){
    $("#update${cfp.moduleName}Form").submit();
};