UE.plugins['input'] = function() {
	var me = this;
	//单行文本
	me.commands['textinput'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//多行文本
	me.commands['textarea'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//多行文本
	me.commands['select'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//选择框
	me.commands['checkbox'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//时间类型
	me.commands['datetime'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//宏控件
	me.commands['auto'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//富文本编辑器
	me.commands['htmleditor'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	//人员选择
	me.commands['userdialog'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	//部门选择
	me.commands['deptdialog'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	//角色选择
	me.commands['roledialog'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	//明细字段
	me.commands['detail'] = {
		execCommand : function(cmdName) {
			//打开窗口
			initDialog(cmdName);
		}
	};
	
	function initDialog(t){
		me.curInputType = t;
		var title = me.options.labelMap[t] || me.getLang("labelMap." + t) || '';
        className="edui-for-" + t;
        iframeUrl ="~/extend/dialogs/"+t+"/edit.html";
		if(!me.ui._dialogs[t]){
			var dialog = new baidu.editor.ui.Dialog( UE.utils.extend({
               iframeUrl: me.ui.mapUrl(iframeUrl),
               editor: me,
               className: className,
               title: title
           },{
               buttons: [{
                   className: 'edui-okbutton',
                   label: '确认',
                   onclick: function (){
                       dialog.close(true);
                   }
               }, {
                   className: 'edui-cancelbutton',
                   label: '取消',
                   onclick: function (){
                       dialog.close(false);
                   }
               }]
           }));
			me.ui._dialogs[t] = dialog;
			dialog.render();			
		}
		me.ui._dialogs[t].open();
	};
}