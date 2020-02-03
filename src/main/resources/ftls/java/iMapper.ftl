<#include "../constant/setHead.ftl" />
package ${classBean.packageName};

import java.util.List;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import org.apache.ibatis.session.RowBounds;
import ${classBean.voPackageName}.${classBean.voName};

/**
 * 
 * @Description:${annotation.description}
 * @author:     ${annotation.authorName}
 * @date:       ${annotation.date}
 * ${annotation.copyright}
 */
public interface ${classBean.className}{

	/** 
	 * @Title:        query${classBean.modelName}List 
	 * @Description:  分页
	 * @param:        @param paramBean
	 * @param:        @return    
	 * @return:       Pagination<${classBean.voName}>    
	 * @author        ${annotation.authorName}
	 * @Date          ${annotation.date}
	 */
	public List<${classBean.voName}> query${classBean.modelName}List(Pagination<${classBean.voName}> paramBean,RowBounds rowBounds) throws Exception;

	/**
	*
	* @Title:        selectByPrimaryKey
	* @Description:  根据主键获得${cfp.tableNameZN}
	* @param:        @param id
	* @param:        @return
	* @param:        @throws Exception
	* @return:       String
	* @author        ${annotation.authorName}
	* @Date          ${annotation.date}
	*/
	public ${classBean.voName} selectByPrimaryKey(Long id);

	<#list cfp.createMethodType as method>
		<#if method == 1>
			/**
			*
			* @Title:        add${classBean.modelName}
			* @Description:  新增${cfp.tableNameZN}
			* @param:        @param ${classBean.voName?uncap_first}
			* @param:        long
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
        	public long add${classBean.modelName}(${classBean.voName} ${classBean.voName?uncap_first}) throws Exception;

		<#elseif method == 2>
			/**
			*
			* @Title:        del${classBean.modelName}
			* @Description:  删除${cfp.tableNameZN}
			* @param:        @param ${classBean.voName?uncap_first}
			* @param:        long
			* @author        ${annotation.authorName}
			* @Date          ${annotation.date}
			*/
			public long del${classBean.modelName}(List<Integer> list) throws Exception;

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
			public long update${classBean.modelName}(${classBean.voName} ${classBean.voName?uncap_first}) throws Exception;
		</#if>
	</#list>
}
