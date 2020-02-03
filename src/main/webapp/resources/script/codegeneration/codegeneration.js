// 代码生成方法JS对象
var codeGeneration = function (){};

// 根据表名获得表字段信息
codeGeneration.getDbColumnByTableName = function () {
    var tableName = $("#tableName").val();

    if (tableName == ""){
        top.toastr.error("对不起，表名不能为空！");
        $("#tableName").focus();
        return;
    }

    $.post(ctx.path + '/platform/code/getDbColumnByTableName' + ctx.noAuthSuffix, {
        tableName : tableName
    }, function(data, status) {
        $("#columnTable").html("");
        if (data.data != undefined && data.data.columnList != "" && data.data.columnList.length > 0) {
            $.each(data.data.columnList, function (i, n) {
                var html = "<tr id='tr_" + (i + 1) + "'>";
                html += "<td>" + (i + 1) + "<input type='hidden' id='isAutoInctement_" + (i + 1) + "' value='" + n.isAutoInctement + "' /></td>";
                html += "<td>" + n.columnName + "<input type='hidden' id='columnName_" + (i + 1) + "' value='" + n.columnName + "' /></td>";
                if (n.detail != undefined && n.detail != "") {
                    html += "<td>" + n.detail + "<input type='hidden' id='detail_" + (i + 1) + "' value='" + n.detail + "' /></td>";
                } else {
                    html += "<td><input type='hidden' id='detail_" + (i + 1) + "' value='' /></td>";
                }
                html += "<input type='hidden' id='scale_" + (i + 1) + "' value='" + n.scale + "' />";
                html += "<input type='hidden' id='precision_" + (i + 1) + "' value='" + n.precision + "' />";
                html += "<input type='hidden' id='columnClassName_" + (i + 1) + "' value='" + n.columnClassName + "' />";
                html += "<input type='hidden' id='columnLabel_" + (i + 1) + "' value='" + n.columnLabel + "' />";
                html += "<input type='hidden' id='columnType_" + (i + 1) + "' value='" + n.columnType + "' />";
                html += "<input type='hidden' id='columnTypeName_" + (i + 1) + "' value='" + n.columnTypeName + "' />";
                if (!n.isAutoInctement){
                    html += "<td><input type='text' id='fieldLength_" + (i + 1) + "' value='' /></td>";
                    html += "<td><input type='checkbox' id='isForm_" + (i + 1) + "' /></td>";
                    html += "<td><input type='checkbox' id='isColumn_" + (i + 1) + "' /></td>";
                    html += "<td><input type='checkbox' id='isSearchable_" + (i + 1) + "' /></td>";
                    html += "<td><select id='searchType_" + (i + 1) + "'>";
                    html += "<option value='0'>普通查询</option>";
                    html += "<option value='1'>范围查询</option>";
                    html += "</select></td>";
                } else {
                    html += "<td></td><td></td><td></td><td></td><td></td>";
                }
                html += "</tr>";
                $("#columnTable").append(html);
                tableSize();
            });
        } else {
            top.toastr.error("对不起，表名【" + tableName + "】不存在，请重新查询！");
            $("#tableName").focus();
        }
    }, "json");
};

// 获得需要生成的代码、方法值
codeGeneration.getCheckBoxValue = function (node) {
    var checkVal = "";
    $.each($(node).parent().find("input[type='checkbox']"), function (i, n) {
        if ($(n).prop("checked")){
            checkVal += $(n).val() + ",";
        }
    });
    if (checkVal.length > 0){
        checkVal = checkVal.substr(0, checkVal.length - 1);
    }
    $(node).parent().find("input[type='hidden']").val(checkVal);
};

// 代码生成方法
codeGeneration.doGeneration = function (cfp) {
    // 判断是否有字段
    var tableName = $("#tableName").val();
    if ($("#columnTable tr").length <= 0){
        if (tableName == ""){
            top.toastr.error("对不起，请输入表名！");
        } else {
            top.toastr.error("对不起，该表的字段不存在，请重新选择表！");
        }
        $("#tableName").focus();
        return;
    }
    var tableNameZN = $("#tableNameZN").val();
    if (tableNameZN == ""){
        top.toastr.error("对不起，请输入表描述！");
        return;
    }

    // 判断是否填写模块名及格式
    var moduleReg = /^[A-Z][a-zA-Z]+$/;
    var moduleName = $("#moduleName").val();

    if (moduleName == ""){
        top.toastr.error("对不起，模块名不能为空，请填写模块名称！");
        $("#moduleName").focus();
        return;
    } else if (!moduleReg.test(moduleName)) {
        top.toastr.error("对不起，模块名格式不对，请重新填写模块名！");
        $("#moduleName").focus();
        return;
    }

    // 判断是否填写包路径及格式
    var packageReg = /^[a-zA-Z]+(.[a-zA-Z]+)+$/;
    var packageUrl = $("#packageUrl").val();
    if (packageUrl == ""){
        top.toastr.error("对不起，包路径不能为空，请填写包路径！");
        $("#packageUrl").focus();
        return;
    } else if (!packageReg.test(packageUrl)) {
        top.toastr.error("对不起，包路径格式不对，请重新填写包路径！");
        $("#packageUrl").focus();
        return;
    }

    var authorName = $("#authorName").val();
    if (authorName == "") {
        top.toastr.error("对不起，作者不能为空，请填写作者！");
        $("#authorName").focus();
        return;
    }

    // 判断是否选择了生成类型
    var createFileType = $("#createFileType").val();
    var createMethodType = $("#createMethodType").val();
    if (createFileType == ""){
        top.toastr.error("对不起，请选择需要生成的代码！");
        return;
    }

    // 获得字段信息
    var columns = [];
    var isSelectColumn = false;
    var errColumn = null;
    $.each($("#columnTable tr"), function (i, n) {
        var column = {};
        column.rowId = i + 1;
        column.isAutoInctement = $("#isAutoInctement_" + (i + 1)).val() == "true" ? true : false;
        column.columnName = $("#columnName_" + (i + 1)).val();
        column.detail = $("#detail_" + (i + 1)).val();
        column.isColumn = $("#isColumn_" + (i + 1)).prop("checked");
        if (!column.isColumn) {
            column.isColumn = false;
        }
        if ($("#isColumn_" + (i + 1)).prop("checked")){
            isSelectColumn = true;
        }
        column.isForm = $("#isForm_" + (i + 1)).prop("checked");
        if (!column.isForm) {
            column.isForm = false;
        }
        column.columnClassName = $("#columnClassName_" + (i + 1)).val();
        column.columnLabel = $("#columnLabel_" + (i + 1)).val();
        column.columnType = $("#columnType_" + (i + 1)).val();
        column.columnTypeName = $("#columnTypeName_" + (i + 1)).val();
        column.fieldLength = $("#fieldLength_" + (i + 1)).val();
        if (column.fieldLength != "" && isNaN(column.fieldLength) && !column.isAutoInctement){
            errColumn = column;
            return;
        }
        if (column.fieldLength == "") {
            column.fieldLength = 0;
        }
        column.isSearchable = $("#isSearchable_" + (i + 1)).prop("checked");
        if (!column.isSearchable) {
        	column.isSearchable = false;
        }
        column.searchType = parseInt($("#searchType_" + (i + 1)).val());
        column.scale = parseInt($("#scale_" + (i + 1)).val());
        column.precision = parseInt($("#precision_" + (i + 1)).val());
        columns.push(column);
    });

    if (errColumn != null) {
        top.toastr.error("对不起，第" + errColumn.rowId + "行的控制长度不是数字，请重新填写！");
        return;
    }

    // 判断选择了JSP界面，如果选择，则必须选择显示列
    if (createFileType.indexOf("2,") > 0 && !isSelectColumn){
        top.toastr.error("对不起，列表界面必须要有显示列，请选择需要显示的列！");
        return;
    }

    // 判断选择了生成后台代码，则必须选择生成Entity实体
    if ((createFileType.indexOf("3") > 0 || createFileType.indexOf("4") > 0 || createFileType.indexOf("5") > 0 || createFileType.indexOf("6") > 0 || createFileType.indexOf("7") > 0) && createFileType.indexOf("1") < 0) {
        top.toastr.error("对不起，生成后台代码时,必须选择生成Entity实体！");
        return;
    }

    // 发送后台生成代码
    $.ajax({
        url: ctx.path + '/platform/code/doGenerate' + ctx.noAuthSuffix,
        data:{
            tableName : tableName,
            authorName : authorName,
            tableNameZN : tableNameZN,
            packageUrl : packageUrl,
            moduleName : moduleName,
            createFileType : createFileType,
            createMethodType : createMethodType,
            columns : JSON.stringify(columns)
        },
        type:"POST",
        success:function(data){
            if (data.rs == 1) {
                window.location.href = ctx.path + "/platform/code/expZipFile" + ctx.noAuthSuffix + "?module=" + moduleName;
            }
        }
    });
};