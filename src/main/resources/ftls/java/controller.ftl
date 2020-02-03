<#include "../constant/setHead.ftl" />
package ${classBean.packageName};

import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.samton.platform.framework.base.SdkBaseController;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.constant.WebConstant;
import com.samton.platform.pm.bean.entity.TSysLog;
import com.samton.platform.pm.constant.PmStateConstant;
import com.samton.platform.pm.service.ILogService;
import com.samton.platform.framework.util.ResponseMapUtil;
import ${classBean.iServicePackage}.${classBean.iServiceName};
import ${classBean.voPackageName}.${classBean.voName};

/**
 * 
 * @Description:${annotation.description}
 * @author:     ${annotation.authorName}
 * @date:       ${annotation.date}
 * ${annotation.copyright}
 */
@Controller
@RequestMapping("${classBean.moduleUrl}")
public class ${classBean.className} extends SdkBaseController {

	@Resource
	private ${classBean.iServiceName} ${classBean.iServiceName?substring(1)?uncap_first};
	
	@Resource
	private ILogService logService;

	/**
	*
	* @Title:        load${classBean.modelName}List
	* @Description:  加载查询页面
	* @param:        @return
	* @return:       String
	* @author        ${annotation.authorName}
	* @Date          ${annotation.date}
	*/
	@RequestMapping("/load${classBean.modelName}List" +  WebConstant.PAGE_SUFFIX)
	public String load${classBean.modelName}List(){
		return "${classBean.modelName?uncap_first}/${classBean.modelName?uncap_first}Manage";
	}

	/**
	 * 
	 * @Title:        query${classBean.modelName}List
	 * @Description:  分页查询
	 * @param:        @param paramBean
	 * @param:        @param ${classBean.voName?uncap_first}
	 * @param:        @throws Exception
	 * @return:       String    
	 * @author        ${annotation.authorName}
	 * @Date          ${annotation.date}
	 */
	@RequestMapping("/query${classBean.modelName}List" + WebConstant.BUSINESS_SUFFIX)
	@ResponseBody
	public Map<String, Object> query${classBean.modelName}List(Pagination<${classBean.voName}> paramBean, ${classBean.voName} ${classBean.voName?uncap_first}) throws Exception {
		ResponseMapUtil responseMapUtil = new ResponseMapUtil();
		try {
			if (${classBean.voName?uncap_first} != null) {
				<#list cfp.columns as c >
					<#if c.isSearchable && (c.columnClassName?index_of("String") > 0)>
						if (${classBean.voName?uncap_first}.get${c.attrName?cap_first}() != null) {
							${classBean.voName?uncap_first}.set${c.attrName?cap_first}(${classBean.voName?uncap_first}.get${c.attrName?cap_first}().trim());
						}
					</#if>
				</#list>
			}

			paramBean.setSearch(${classBean.voName?uncap_first});
			Pagination<${classBean.voName}> pageData = ${classBean.iServiceName?substring(1)?uncap_first}.query${classBean.modelName}List(paramBean);
			<#--this.request.setAttribute("pageData", pageData);-->
			<#--this.request.setAttribute("${classBean.voName?uncap_first}", ${classBean.voName?uncap_first});-->
			<#--return "${classBean.modelName?uncap_first}/${classBean.modelName?uncap_first}Manage";-->
			return responseMapUtil.toPageMap(pageData);
		} catch (Exception e) {
			return responseMapUtil.toPageMap(null);
		}
	}

	/**
	*
	* @Title:        open${cfp.moduleName}View
	* @Description:  跳转${cfp.tableNameZN}查询界面
	* @param:        @return
	* @param:        @throws Exception
	* @return:       String
	* @author        ${annotation.authorName}
	* @Date          ${annotation.date}
	*/
	@RequestMapping("/open${cfp.moduleName}View" + WebConstant.PAGE_SUFFIX)
	public String open${cfp.moduleName}View(Long id) throws Exception {
		${classBean.voName} ${classBean.voName?uncap_first} = ${classBean.iServiceName?substring(1)?uncap_first}.get${classBean.voName}ById(id);
		this.request.setAttribute("${classBean.voName?uncap_first}", ${classBean.voName?uncap_first});
		return "${classBean.modelName?uncap_first}/${classBean.modelName?uncap_first}View";
	}

	<#list cfp.createMethodType as method>
		<#if method == 1>
			/**
			*
			* @Title:        add${classBean.modelName}
			* @Description:  新增${cfp.tableNameZN}
			* @param:        @param ${classBean.voName?uncap_first}
			* @param:        Map<String,Object>
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
			@RequestMapping("/add${classBean.modelName}" + WebConstant.BUSINESS_SUFFIX)
			@ResponseBody
			public Map<String, Object> add${classBean.modelName}(${classBean.voName} ${classBean.voName?uncap_first}) throws Exception {
				long result = ${classBean.iServiceName?substring(1)?uncap_first}.add${classBean.modelName}(${classBean.voName?uncap_first});
				if (result > 0) {
					logService.addLog(new TSysLog("${cfp.tableNameZN}-新增", "新增${cfp.tableNameZN}成功 ！", PmStateConstant.LOG_PLATFORM));
				} else {
					logService.addLog(new TSysLog("${cfp.tableNameZN}-新增", "新增${cfp.tableNameZN}失败 ！", PmStateConstant.LOG_PLATFORM));
				}
				return this.getResultMap(true, result);
			}

			/**
			*
			* @Title:        open${classBean.modelName}Add
			* @Description:  跳转${cfp.tableNameZN}新增页面
			* @param:        @return
			* @param:        @throws Exception
			* @return:       String
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
			@RequestMapping("/open${classBean.modelName}Add" + WebConstant.NO_AUTH_SUFFIX)
			public String open${classBean.modelName}Add() throws Exception {
				return "${classBean.modelName?uncap_first}/${classBean.modelName?uncap_first}Add";
			}

		<#elseif method == 2>
			/**
			*
			* @Title:        del${classBean.modelName}
			* @Description:  删除${cfp.tableNameZN}
			* @param:        @param ${classBean.voName?uncap_first}
			* @param:        Map<String,Object>
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
			@RequestMapping("/del${classBean.modelName}" + WebConstant.BUSINESS_SUFFIX)
			@ResponseBody
			public Map<String, Object> del${classBean.modelName}(String id) throws Exception {
				long result = ${classBean.iServiceName?substring(1)?uncap_first}.del${classBean.modelName}(id);
				if (result > 0) {
					logService.addLog(new TSysLog("${cfp.tableNameZN}-删除", "删除${cfp.tableNameZN}成功 ！", PmStateConstant.LOG_PLATFORM));
				} else {
					logService.addLog(new TSysLog("${cfp.tableNameZN}-删除", "删除${cfp.tableNameZN}失败 ！", PmStateConstant.LOG_PLATFORM));
				}
				return this.getResultMap(true, result);
			}

		<#elseif method == 3>
			/**
			*
			* @Title:        update${classBean.modelName}
			* @Description:  修改${cfp.tableNameZN}
			* @param:        @param ${classBean.voName?uncap_first}
			* @param:        Map<String,Object>
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
			@RequestMapping("/update${classBean.modelName}" + WebConstant.BUSINESS_SUFFIX)
			@ResponseBody
			public Map<String, Object> update${classBean.modelName}(${classBean.voName} ${classBean.voName?uncap_first}) throws Exception {
				long result = ${classBean.iServiceName?substring(1)?uncap_first}.update${classBean.modelName}(${classBean.voName?uncap_first});
				if (result > 0) {
					logService.addLog(new TSysLog("${cfp.tableNameZN}-修改", "修改${cfp.tableNameZN}成功 ！", PmStateConstant.LOG_PLATFORM));
				} else {
					logService.addLog(new TSysLog("${cfp.tableNameZN}-修改", "修改${cfp.tableNameZN}失败 ！", PmStateConstant.LOG_PLATFORM));
				}
				return this.getResultMap(true, result);
			}

			/**
			*
			* @Title:        open${classBean.modelName}Update
			* @Description:  编辑${cfp.tableNameZN}页面
			* @param:        @param id
			* @param:        @return
			* @param:        @throws Exception
			* @return:       String
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
			@RequestMapping("/open${classBean.modelName}Update" + WebConstant.NO_AUTH_SUFFIX)
			public String open${classBean.modelName}Update(Long id) throws Exception{
				${classBean.voName} ${classBean.voName?uncap_first} = ${classBean.iServiceName?substring(1)?uncap_first}.get${classBean.voName}ById(id);
				this.request.setAttribute("${classBean.voName?uncap_first}", ${classBean.voName?uncap_first});
				return "${classBean.modelName?uncap_first}/${classBean.modelName?uncap_first}Update";
			}
			
		</#if>
	</#list>

}
