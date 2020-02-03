package com.samton.platform.pm.constant;

import org.springframework.stereotype.Component;

import com.samton.platform.framework.exception.constant.ExpCodeConstant;

//业务级别按模块自己划分，参考Module实体，1\2\3\4\5\6\7\8\9依次递增
//后三位错误编码，倒数第四位以上，模块ID
//每个模块报错编号预计1000个
@Component
public class PmExpCodeConstant extends ExpCodeConstant {

	public static final String PM_SESSION_EXPIRE = "1000";
	public static final String PM_LOGINNAME_PWD_ERROR = "1001";
	public static final String PM_LOGINNAME_FORMAT_ERROR = "1003";
	public static final String PM_CHANGE_OLD_PWD_ERROR = "1004";
	public static final String PM_CHANGE_NEW_PWD_ERROR = "1005";
	public static final String PM_CHANGE_NEW_PWD_AGAIN_ERROR = "1006";
	public static final String PM_CHANGE_OLD_NEW_PWD_ERROR = "1007";
	public static final String PM_CHANGE_NEW_ANAIN_PWD_ERROR = "1008";
	public static final String PM_OLD_PWD_ERROR = "1009";
	public static final String PM_LOGIN_NAME_REPEAT = "1010";
	public static final String PM_LOGINNAME_PWD_STATE = "1011";
	public static final String PM_NO_AUTH = "1012";
	public static final String PM_URL_INVALID = "1013";

	public static final String PM_ROLE_NULL_ERROR = "2000";
	public static final String PM_ROLE_NAME_ERROR = "2001";
	public static final String PM_ROLE_ID_ERROR = "2002";
	public static final String PM_ROLE_NAME_REPEAT_ERROR = "2003";
	public static final String PM_ROLE_DEL_NULL_ERROR = "2004";

	public static final String PM_MENU_HAS_CHILDREN_DEL_ERROR = "5000";
	public static final String PM_MENUCODE_REPEAT_ERROR = "5001";
	public static final String PM_MENU_HAS_CHILDREN_DISABLE_ERROR = "5002";

	public static final String PM_DEPART_DEPARTNAME_REPEAT = "4001";
	public static final String PM_DEPART_USER_EXIST = "4002";

	public static final String PM_ADVERTISE_DEL_NULL_ERROR = "6001";
	public static final String PM_ADVERTISE_ADD_REPEAT_ERROR ="6002";


	public static final String PM_FEEDBACK_DEL_NULL_ERROR = "7001";
	static {
		msgMap.put(PM_SESSION_EXPIRE, "登录超时，请重新登录！");
		msgMap.put(PM_LOGINNAME_PWD_ERROR, "您的用户名或登录密码输入错误，请重新输入！");
		msgMap.put(PM_LOGINNAME_FORMAT_ERROR, "登录名格式错误！");
		msgMap.put(PM_CHANGE_OLD_PWD_ERROR, "对不起，原密码不能为空，请输入原密码！");
		msgMap.put(PM_CHANGE_NEW_PWD_ERROR, "对不起，新密码不能为空，请输入新密码！");
		msgMap.put(PM_CHANGE_NEW_PWD_AGAIN_ERROR, "对不起，确认密码不能为空，请输入确认密码！");
		msgMap.put(PM_CHANGE_OLD_NEW_PWD_ERROR, "对不起，新密码不能与原密码一致，请重新输入新密码！");
		msgMap.put(PM_CHANGE_NEW_ANAIN_PWD_ERROR, "对不起，确认密码与新密码不一致，请重新输入确认密码！");
		msgMap.put(PM_OLD_PWD_ERROR, "对不起，原密码不正确，请重新输入原密码！");
		msgMap.put(PM_LOGIN_NAME_REPEAT, "对不起，新增用户登录名重复，请重新输入登录名！");
		msgMap.put(PM_LOGINNAME_PWD_STATE, "您的账户已被禁用，请联系管理员！");
		msgMap.put(PM_NO_AUTH, "您无权对此操作，请联系管理员！");
		msgMap.put(PM_URL_INVALID, "您访问的URL无效，请联系管理员！");

		msgMap.put(PM_ROLE_NULL_ERROR, "角色信息不存在！");
		msgMap.put(PM_ROLE_NAME_ERROR, "对不起，角色名称不能为空，请重新输入角色名称！");
		msgMap.put(PM_ROLE_ID_ERROR, "对不起，修改的角色信息不存在，请重新选择角色！");
		msgMap.put(PM_ROLE_NAME_REPEAT_ERROR, "对不起，角色名称已存在，请重新输入角色名称！");
		msgMap.put(PM_ROLE_DEL_NULL_ERROR, "对不起，删除的角色不存在，请重新选择角色！");

		msgMap.put(PM_MENU_HAS_CHILDREN_DEL_ERROR, "当前菜单下面有子菜单，不可删除！");
		msgMap.put(PM_MENUCODE_REPEAT_ERROR, "菜单编码不唯一！");
		msgMap.put(PM_MENU_HAS_CHILDREN_DISABLE_ERROR, "当前菜单下面有子菜单，不可禁用！");

		msgMap.put(PM_DEPART_DEPARTNAME_REPEAT, "对不起，部门名称已存在，请重新输入部门名称！");
		msgMap.put(PM_DEPART_USER_EXIST, "对不起，该部门存在用户，请将用户移出该部门后重新操作！");

		msgMap.put(PM_ADVERTISE_DEL_NULL_ERROR, "对不起，删除的图片不存在，请重新选择图片！");
		msgMap.put(PM_ADVERTISE_ADD_REPEAT_ERROR, "对不起，该图片位置已存在，请重新选择新增位置！");

		msgMap.put(PM_FEEDBACK_DEL_NULL_ERROR, "对不起，删除的意见不存在！");
	}

}
