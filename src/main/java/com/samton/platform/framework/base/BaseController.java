package com.samton.platform.framework.base;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.exception.BaseException;
import com.samton.platform.framework.exception.UndefinedException;
import com.samton.platform.framework.exception.constant.ExpCodeConstant;
import com.samton.platform.framework.log.util.LogUtil;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.util.CurrentUtil;
import com.samton.platform.framework.util.DateUtil;
import com.samton.platform.framework.util.NetworkUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @Description:BaseController类
 * @author: Alex
 * @date: 2017年2月22日 上午10:37:01
 * Copyright (c) 2017, Samton. All rights reserved
 */
@Controller
@SuppressWarnings({"rawtypes"})
public class BaseController {
	/**
	 * 日志对象
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	protected HttpServletRequest getRequest() {
		return this.request;
	}

	protected HttpServletResponse getResponse() {
		return this.response;
	}

	protected HttpSession getSession() {
		return request.getSession();
	}

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		this.request = request;
		this.response = response;
		this.session = session;
	}

	/**
	 * 获取前端传递的参数，以字符串类型返回
	 *
	 * @param name
	 * @return
	 */
	protected String getString(String name) {
		return this.request.getParameter(name);
	}

	/**
	 * 获取前端传递的参数，以字符串类型返回，若参数值为空则返回默认值
	 *
	 * @param name
	 *            参数名称
	 * @param defaultVal
	 *            默认值
	 * @return
	 */
	protected String getString(String name, String defaultVal) {
		String param = this.getString(name);
		if (StringUtils.isNotBlank(param)) {
			return StringUtils.trim(param);
		} else {
			return defaultVal;
		}
	}

	/**
	 * 获取前端传递的参数，以整型类型返回
	 *
	 * @param name
	 * @return
	 */
	protected Integer getInt(String name) {
		Integer pInt = null;
		String param = this.getString(name);
		if (StringUtils.isNotBlank(param)) {
			try {
				pInt = Integer.parseInt(param);
			} catch (Exception e) {
				pInt = null;
			}
		}
		return pInt;
	}

	/**
	 * 获取前端传递的参数，以整型类型返回，若参数值为空则返回默认值
	 *
	 * @param name
	 * @param defaultVal
	 * @return
	 */
	protected Integer getInt(String name, Integer defaultVal) {
		Integer pInt = null;
		String param = this.getString(name);
		if (StringUtils.isNotBlank(param)) {
			try {
				pInt = Integer.parseInt(param);
			} catch (Exception e) {
				pInt = null;
			}
		} else {
			pInt = defaultVal;
		}
		return pInt;
	}

	/**
	 * 向request中添加变量，提供前端获取
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	protected BaseController addAttr(String key, Object value) {
		this.request.setAttribute(key, value);
		return this;
	}

	/**
	 * 跳转到页面
	 *
	 * @param page
	 *            页面地址
	 * @return
	 */
	protected void forward(String page) {
		try {
			request.getRequestDispatcher(page).forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * @Title:        exp 
	 * @Description:  基于@ExceptionHandler异常处理
	 * @param:        @param request
	 * @param:        @param exp
	 * @param:        @return    
	 * @return:       JSONObject    
	 * @author        Alex
	 * @Date          2017年2月22日 上午10:39:00
	 */
	@ExceptionHandler
	@ResponseBody
	public JSONObject exp(HttpServletRequest request, Exception exp) {
		// 操作用户信息
		String userInfo = "IP：" + NetworkUtil.getIpAddress(request) + "\nSession：" + this.getSessionId() + "\n";
		UserCacheBean userCacheBean = CurrentUtil.getCurrentUser();
		if (userCacheBean != null) {
			userInfo += "EnterpriseId：" + userCacheBean.getEnterpriseId() + "\nUserId：" + userCacheBean.getUserId() + "\n";
		}
		// 错误编号
		String showCode = "编号：" + DateUtil.currentDatetimeNum();
		// 日志记录
		LogUtil.errorSystemLog(userInfo + showCode, exp);
		BaseException baseException = null;
		// 判断错误类型
		if (exp instanceof BaseException) {
			baseException = (BaseException) exp;
		} else {
			String errorCode = ExpCodeConstant.DEFAULT_CODE;
			Exception causeExp = (Exception) exp.getCause();
			if (causeExp == null){
				causeExp = exp;
			}
			if (causeExp instanceof PSQLException) {
				PSQLException pgExp = (PSQLException) causeExp;
				if (pgExp.getSQLState().equals("22001")) {
					errorCode = ExpCodeConstant.CHARACTER_TOO_LONG;
					showCode = null;
				} else if (pgExp.getSQLState().equals("22003")) {
					errorCode = ExpCodeConstant.NUMBER_TOO_LARGER;
					showCode = null;
				}
			} else if (causeExp instanceof InvalidFormatException) {
				errorCode = ExpCodeConstant.INVALID_FORMAT_ERROR;
				showCode = null;
			}
			baseException = new UndefinedException(exp, errorCode, showCode);
		}
		return baseException.processException();
	}

	/**
	 * 
	 * @Title:        getSessionAttribute 
	 * @Description:  相当于session.getAttribute方法
	 * @param:        @param name
	 * @param:        @return    
	 * @return:       Object    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:39:04
	 */
	protected Object getSessionAttribute(String name) {
		if (this.getSession() != null) {
			return this.getSession().getAttribute(name);
		}
		return null;
	}

	/***
	 * 
	 * @Title:        setSessionAttribute 
	 * @Description:  相当于session.setAttribute方法
	 * @param:        @param name
	 * @param:        @param obj    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:39:18
	 */
	protected void setSessionAttribute(String name, Object obj) {
		if (this.getSession() != null) {
			this.getSession().setAttribute(name, obj);
		}
	}

	/**
	 * 
	 * @Title:        removeSessionAttribute 
	 * @Description:  相当于session.removeAttribute方法
	 * @param:        @param name    
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:39:33
	 */
	protected void removeSessionAttribute(String name) {
		if (this.getSession() != null) {
			this.getSession().removeAttribute(name);
		}
	}

	/**
	 * 
	 * @Title:        getSessionId 
	 * @Description:  获得SeesionId
	 * @param:        @return    
	 * @return:       String    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:41:09
	 */
	protected String getSessionId() {
		return this.getSession().getId();
	}

	protected Map<String, Object> getResultMap(boolean rs) {
		return getResultMap(rs, null);
	}

	protected Map<String, Object> getResultMap(Object obj) {
		if (obj != null && obj instanceof Boolean) {
			return this.getResultMap((boolean) obj);
		}
		return getResultMap(null, obj);
	}

	protected Map<String, Object> getResultMap(Boolean rs, Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean rsFlag = true;
		if (rs != null) {
			rsFlag = rs;
		} else {
			rsFlag = obj != null ? true : false;
		}
		map.put("rs", rsFlag ? 1 : 0);
		if (!rsFlag)
			return map;
		if (obj != null) {
			if (obj instanceof Pagination) {
				map.put("data", ((Pagination) obj).toMap());
			} else {
				map.put("data", obj);
			}
		}
		return map;
	}

	protected void setAttribute(String name, Object obj) {
		this.getRequest().setAttribute(name, obj);
	}

	/**
	 * 
	 * @Title:        export 
	 * @Description:  导出文件，格式为cvs
	 * @param:        @param response
	 * @param:        @param sheetName	导出的文件名称
	 * @param:        @param title		标题
	 * @param:        @param colNames	要导出的列
	 * @param:        @param rowDatas	要导出分页对象
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:42:05
	 */
	public void export(HttpServletResponse response, String sheetName, String title, List<String> colNames, List rowDatas) {
		PrintWriter out = null;
		try {
			if (sheetName == null) {
				sheetName = String.format("%1$tY%1$tm%1$td", new Date());
			}
			if (this.getRequest().getHeader("user-agent").indexOf("MSIE") > 0) {
				sheetName = java.net.URLEncoder.encode(sheetName, "UTF-8");
			} else {
				sheetName = new String(sheetName.getBytes(), "ISO-8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=gbk;");
			response.addHeader("Content-Disposition", "attachment; filename=" + sheetName + ".csv");
			response.setCharacterEncoding("gbk");
			out = response.getWriter();
			StringBuffer rows = new StringBuffer();
			rows.append(title);
			rows.append(" \n");
			if (rowDatas != null && rowDatas.size() > 0) {
				if (colNames != null && !colNames.isEmpty()) {
					getAppointDatas(rows, colNames, rowDatas);
				} else {
					getAllDatas(rows, rowDatas);
				}
				out.print(rows.toString());
			} else {
				out.print(title);
			}
		} catch (Exception e) {
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 
	 * @Title:        export 
	 * @Description:  导出文件，格式为cvs
	 * @param:        @param response
	 * @param:        @param sheetName	导出的文件名称
	 * @param:        @param title		标题
	 * @param:        @param colNames	要导出的列
	 * @param:        @param pageData   要导出分页对象 
	 * @return:       void    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:47:07
	 */
	public void export(HttpServletResponse response, String sheetName, String title, List<String> colNames, Pagination pageData) {
		PrintWriter out = null;
		try {
			if (sheetName == null) {
				sheetName = String.format("%1$tY%1$tm%1$td", new Date());
			}
			if (this.getRequest().getHeader("user-agent").indexOf("MSIE") > 0) {
				sheetName = java.net.URLEncoder.encode(sheetName, "UTF-8");
			} else {
				sheetName = new String(sheetName.getBytes(), "ISO-8859-1");
			}
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=gbk;");
			response.addHeader("Content-Disposition", "attachment; filename=" + sheetName + ".csv");
			response.setCharacterEncoding("gbk");

			out = response.getWriter();
			StringBuffer rows = new StringBuffer();
			rows.append(title);
			rows.append(" \n");
			List rowDatas = pageData.getData();
			if (rowDatas != null && rowDatas.size() > 0) {
				if (colNames != null && !colNames.isEmpty()) {
					getAppointDatas(rows, colNames, pageData);
				} else {
					getAllDatas(rows, pageData);
				}
				out.print(rows.toString());
			} else {
				out.print(title);
			}
		} catch (Exception e) {
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 
	 * @Title:        getAppointDatas 
	 * @Description:  得到指定的数据字符串格式
	 * @param:        @param rows		
	 * @param:        @param colNames	指定的列
	 * @param:        @param pageData	分页对象
	 * @param:        @return    
	 * @return:       StringBuffer    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:48:41
	 */
	public StringBuffer getAppointDatas(StringBuffer rows, List<String> colNames, Pagination pageData) {
		List rowDatas = pageData.getData();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		for (int i = 0; i < rowDatas.size(); i++) {
			Map map = (Map) rowDatas.get(i);
			for (int j = 0; j < colNames.size(); j++) {
				boolean addQuot = false;// 如果是长数字要在前面加单引号
				String colName = colNames.get(j);
				if (colName != null && colName.length() > 1 && colName.startsWith("'")) {
					addQuot = true;
					colName = colName.substring(1);
				}
				String col = null;
				Object val = map.get(colName);
				if (val != null && val instanceof Timestamp) {
					col = sdf.format(val);
				} else {
					col = map.get(colName) != null ? map.get(colName).toString() : "";
				}
				// String col = map.get(colName) != null ? map.get(colName).toString() : "";
				// 得到当前导出列的对应文本值
				String exportValue = pageData.getExprotValue(colName, col);
				if (exportValue != null) {
					col = exportValue;
				}
				if (addQuot) {
					col = "'" + col;
				}
				// int __l = col.indexOf("\r\n");
				col = col.replaceAll("\r\n", "");
				col = col.replaceAll("<br>", "");
				col = col.replaceAll("<br/>", "");
				col = col.replaceAll("&gt;", ">");
				col = col.replaceAll("\"", ""); // 解决字段中有“"”特殊字符时，导致错位的问题

				// if(!noReplace){
				// col = col.replaceAll(",", "，");
				// }
				// if ( __l>=1 ) {
				// col = col.substring(0, __l);
				// }
				// __l = col.indexOf("<br>");
				// if (__l >= 1) {
				// col = col.substring(0, __l);
				// }
				rows.append("\"" + col + "\",");
			}
			rows.deleteCharAt(rows.length() - 1);
			rows.append(" \n");
		}
		return rows;
	}

	/**
	 * 
	 * @Title:        getAllDatas 
	 * @Description:  得到所有的数据字符串格式
	 * @param:        @param rows
	 * @param:        @param rowDatas	要导出的数据
	 * @param:        @return    
	 * @return:       StringBuffer    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:54:54
	 */
	public StringBuffer getAllDatas(StringBuffer rows, List rowDatas) {
		for (int i = 0; i < rowDatas.size(); i++) {
			Map map = (Map) rowDatas.get(i);
			for (Iterator j = map.entrySet().iterator(); j.hasNext();) {
				Entry entry = (Entry) j.next();
				String col = entry.getValue() != null ? entry.getValue().toString() : "";
				rows.append("\"" + col + "\"\t,");
			}
			rows.deleteCharAt(rows.length() - 1);
			rows.append(" \n");
		}
		return rows;
	}

	/**
	 * 
	 * @Title:        getAllDatas 
	 * @Description:  得到所有的数据字符串格式
	 * @param:        @param rows
	 * @param:        @param pageData	要导出的数据
	 * @param:        @return    
	 * @return:       StringBuffer    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:55:18
	 */
	public static StringBuffer getAllDatas(StringBuffer rows, Pagination pageData) {
		List rowDatas = pageData.getData();
		for (int i = 0; i < rowDatas.size(); i++) {
			Map map = (Map) rowDatas.get(i);
			for (Iterator j = map.entrySet().iterator(); j.hasNext();) {
				Entry entry = (Entry) j.next();
				String col = entry.getValue() != null ? entry.getValue().toString() : "";
				rows.append("\"" + col + "\"\t,");
			}
			rows.deleteCharAt(rows.length() - 1);
			rows.append(" \n");
		}
		return rows;
	}

	/**
	 * 
	 * @Title:        getAppointDatas 
	 * @Description:  得到指定的数据字符串格式
	 * @param:        @param rows		返回的字符串
	 * @param:        @param colNames	指定的列
	 * @param:        @param rowDatas	要导出的数据
	 * @param:        @return    
	 * @return:       StringBuffer    
	 * @author        Alex
	 * @Date          2017年2月22日 上午11:55:42
	 */
	public static StringBuffer getAppointDatas(StringBuffer rows, List<String> colNames, List rowDatas) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		for (int i = 0; i < rowDatas.size(); i++) {
			Map map = (Map) rowDatas.get(i);
			for (int j = 0; j < colNames.size(); j++) {
				boolean addQuot = false;// 如果是长数字要在前面加单引号
				String colName = colNames.get(j);
				if (colName != null && colName.length() > 1 && colName.startsWith("'")) {
					addQuot = true;
					colName = colName.substring(1);
				}
				String col = null;
				Object val = map.get(colName);
				if (val != null && val instanceof Timestamp) {
					col = sdf.format(val);
				} else {
					col = map.get(colName) != null ? map.get(colName).toString() : "";
				}
				// String col = map.get(colName) != null ? map.get(colName).toString() : "";
				if (addQuot) {
					col = "'" + col;
				}
				col = col.replaceAll("\r\n", "");
				col = col.replaceAll("&gt;", ">");
				col = col.replaceAll(",", "，");
				// int __l = col.indexOf("\r\n");
				// if ( __l>=1 ) {
				// col = col.substring(0, __l);
				// }
				// __l = col.indexOf("<br>");
				// if (__l >= 1) {
				// col = col.substring(0, __l);
				// }
				rows.append("\"" + col + "\",");
			}
			rows.deleteCharAt(rows.length() - 1);
			rows.append("\n");
		}
		return rows;
	}



}