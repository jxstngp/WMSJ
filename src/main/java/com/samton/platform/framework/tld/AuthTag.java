package com.samton.platform.framework.tld;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.samton.platform.framework.constant.WebConstant;

/**
 * 
 * @Description:权限标签
 * @author: Alex
 * @date: 2017年2月22日 下午8:09:44
 * Copyright (c) 2017, Samton. All rights reserved
 */
@SuppressWarnings("unchecked")
public class AuthTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	// 权限码
	private String mcode;

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	@Override
	public int doStartTag() throws JspException {
		HttpSession httpSession = pageContext.getSession();
		Set<String> authCodeSet = (Set<String>) httpSession.getAttribute(WebConstant.AUTH_CODE_SET);
		if (authCodeSet.contains(mcode)) {
			return EVAL_PAGE;
		} else {
			return SKIP_BODY;
		}
	}

}