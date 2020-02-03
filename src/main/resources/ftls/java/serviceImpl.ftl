<#include "../constant/setHead.ftl" />
package ${classBean.packageName};

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import ${classBean.iServicePackage}.${classBean.iServiceName};
import ${classBean.mapperPackage}.${classBean.modelName}Mapper;
import com.samton.platform.framework.mybatis.pagination.Pagination;
import com.samton.platform.framework.mybatis.pagination.PageContext;
import ${classBean.voPackageName}.${classBean.voName};


/**
 * 
 * @Description:${annotation.description}
 * @author:     ${annotation.authorName}
 * @date:       ${annotation.date}
 * ${annotation.copyright}
 */
@Service("${classBean.serviceName?uncap_first}")
public class ${classBean.className} implements ${classBean.iServiceName} {

	@Resource
	private ${classBean.modelName}Mapper ${classBean.modelName?uncap_first}Mapper;
	
	@Override
	public Pagination<${classBean.voName}> query${classBean.modelName}List(Pagination<${classBean.voName}> paramBean) throws Exception {
		Pagination<${classBean.voName}> pagination = PageContext.initialize(paramBean.getPage(), paramBean.getRows());
		List<${classBean.voName}> ${classBean.modelName?uncap_first}List = ${classBean.modelName?uncap_first}Mapper.query${classBean.modelName}List(paramBean, pagination.getRowBounds());
		pagination.setData(${classBean.modelName?uncap_first}List);
		return pagination;
	}

	@Override
	public ${classBean.voName} get${classBean.voName}ById(Long id) {
		return ${classBean.modelName?uncap_first}Mapper.selectByPrimaryKey(id);
	}

	<#list cfp.createMethodType as method>
		<#if method == 1>
			@Override
			public long add${classBean.modelName}(${classBean.voName} ${classBean.voName?uncap_first}) throws Exception{
				return ${classBean.modelName?uncap_first}Mapper.add${classBean.modelName}(${classBean.voName?uncap_first});
			}

		<#elseif method == 2>
			@Override
			public long del${classBean.modelName}(String ids) throws Exception{
				List<Integer> list = new ArrayList<Integer>(0);
				for (String str : ids.split(",")){
					list.add(Integer.parseInt(str));
				}
        		return ${classBean.modelName?uncap_first}Mapper.del${classBean.modelName}(list);
			}

		<#elseif method == 3>
			@Override
			public long update${classBean.modelName}(${classBean.voName} ${classBean.voName?uncap_first}) throws Exception{
        		return ${classBean.modelName?uncap_first}Mapper.update${classBean.modelName}(${classBean.voName?uncap_first});
			}
			
		</#if>
	</#list>
}
