<#include "../constant/setHead.ftl" />
<#macro mapperEl value>${r"#{"}${value}}</#macro>
<#macro jspEl value>${r"#{"}${value}}</#macro>
<#macro newTypeName value><#if ("SERIAL,INT8"?index_of(value?upper_case) >= 0)>BIGINT<#elseif ("INT2"?index_of(value?upper_case) >= 0)>SMALLINT<#else>${value?upper_case}</#if></#macro>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${cfp.packageUrl}.dao.${classBean.className}">
    <resultMap id="BaseResultMap" type="${cfp.packageUrl}.bean.vo.${classBean.voName}">
        <#list  cfp.columns as column>
            <#if column.isAutoInctement>
                <id column="${column.columnName}" property="${column.attrName}" jdbcType="<@newTypeName column.columnTypeName />" />
            <#else>
                <result column="${column.columnName}" property="${column.attrName}" jdbcType="<@newTypeName column.columnTypeName />" />
            </#if>
        </#list>
    </resultMap>
    <sql id="Base_Column_List">
        <#list  cfp.columns as column>
            ${column.columnName}<#if column_has_next>,</#if>
        </#list>
    </sql>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long">
        select
        <include refid="Base_Column_List" />
        from public.${cfp.tableName}
        <#list  cfp.columns as column>
            <#if column.isAutoInctement>
                where ${column.columnName} = <@jspEl 'id' />
            </#if>
        </#list>
    </select>
    <select id="query${classBean.modelName}List" resultMap="BaseResultMap" parameterType="com.samton.platform.framework.mybatis.pagination.Pagination">
        select
        <include refid="Base_Column_List" />
        from public.${cfp.tableName}
        <where>
            <#list  cfp.columns as column>
                <#if column.isSearchable>
                    <if test="${column.searchName} != null and ${column.searchName} != ''">
                        <#if column.searchType == 0>
                            AND ${column.columnName} = <@mapperEl column.searchName />
                        <#else>
                            AND ${column.columnName} LIKE '%'||<@mapperEl column.searchName />||'%'
                        </#if>
                    </if>
                </#if>
            </#list>
        </where>
    </select>
    <#list cfp.createMethodType as method>
        <#if method == 1>
            <insert id="add${classBean.modelName}" parameterType="${classBean.voPackageName}.${classBean.voName}">
                insert into public.${cfp.tableName}
                <trim prefix="(" suffix=")" suffixOverrides=",">
                    <#list  cfp.columns as column>
                        <#if !column.isAutoInctement>
                            <if test="${column.attrName} != null">
                            ${column.columnName},
                            </if>
                        </#if>
                    </#list>
                </trim>
                <trim prefix="values (" suffix=")" suffixOverrides=",">
                    <#list  cfp.columns as column>
                        <#if !column.isAutoInctement>
                            <if test="${column.attrName} != null">
                                <@mapperEl column.attrName />,
                            </if>
                        </#if>
                    </#list>
                </trim>
            </insert>

        <#elseif method == 2>
            <delete id="del${classBean.modelName}" parameterType="java.util.List">
                delete from public.${cfp.tableName}
                <#list  cfp.columns as column>
                    <#if column.isAutoInctement>
                        where ${column.columnName} in
                        <foreach collection="list" index="index" item="item" open="(" close=")" separator=",">
                            <@jspEl 'item' />
                        </foreach>
                    </#if>
                </#list>
            </delete>

        <#elseif method == 3>
            <update id="update${classBean.modelName}" parameterType="${classBean.voPackageName}.${classBean.voName}">
                update public.${cfp.tableName}
                <set>
                    <#list  cfp.columns as column>
                        <#if !column.isAutoInctement>
                            <if test="${column.attrName} != null">
                                ${column.columnName} = <@mapperEl column.attrName />,
                            </if>
                        </#if>
                    </#list>
                </set>
                <#list  cfp.columns as column>
                    <#if column.isAutoInctement>
                        where ${column.columnName} = <@mapperEl column.attrName />
                    </#if>
                </#list>
            </update>
        </#if>
    </#list>
</mapper>