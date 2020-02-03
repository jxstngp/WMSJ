<#include "../constant/setHead.ftl" />
package ${classBean.packageName};

import java.io.Serializable;
<#if (classBean.extendName)??>import ${classBean.extendPackage}.${classBean.extendName}; </#if>

/**
 * 
 * @Description:${annotation.description}
 * @author:     ${annotation.authorName}
 * @date:       ${annotation.date}
 * ${annotation.copyright}
 */
public class ${classBean.className} <#if (classBean.extendName)??>extends ${classBean.extendName}</#if> implements Serializable {
	
    private static final long serialVersionUID = 1L;
  
}