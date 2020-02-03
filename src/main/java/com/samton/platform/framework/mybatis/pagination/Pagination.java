package com.samton.platform.framework.mybatis.pagination;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public class Pagination<T> implements java.io.Serializable {

	private static final long serialVersionUID = -3435038120094008463L;
	private int page = 1; // 当前页
	private int rows=20;//每页大小,名字必须为rows
	private int totalPages = 0; // 总页数
	private int totalRows = 0; // 总数据数
	private int pageStartRow = 0; // 每页的起始行数
	private int pageEndRow = 0; // 每页显示数据的终止行数
	private boolean hasNextPage = false; // 是否有下一页
	private boolean hasPreviousPage = false; // 是否有前一页
	private List<T> data;// 传过一个list，就可以对list进行分页
	private RowBounds rowBounds=null;
    private Map<String, Map<String,String>> exportMap = new HashMap<String, Map<String,String>>();//导出时数值转换中文
	private Object search;// 查询对象
	
	public RowBounds getRowBounds() {
		return rowBounds;
	}

	public Pagination(){}
	
	public Pagination(int page, int rows){
		this.page = (page == 0 ? 1 : page);
		this.rows = (rows == 0 ? 20 : rows);
		this.pageStartRow = (page - 1) * rows;
		this.pageEndRow = pageStartRow + rows - 1;
		this.rowBounds=new RowBounds((page-1)*rows, rows);
	}
	
	public void setTotal(int totalRows){
		this.totalRows = totalRows;
		this.totalPages = Math.max(totalRows/rows+(totalRows%rows==0?0:1), 1);
		this.pageStartRow = (page-1)*rows+1;
		this.pageEndRow = Math.min(page*rows, totalRows);
		this.hasNextPage = page<totalPages;
		this.hasPreviousPage = page>1;
	}

	public int getPage() {
		return page;
	}

	public int getRows() {
		return rows;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getTotalRows() {
		return totalRows;
	}

	public int getPageStartRow() {
		return pageStartRow;
	}

	public int getPageEndRow() {
		return pageEndRow;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Map<String, Object> toMap(){
		Map<String, Object> jo=new HashMap<String, Object>();
		jo.put("totalRows",totalRows);
		jo.put("hasNextPage",hasNextPage);
		jo.put("totalPages", totalPages);
		jo.put("data",data);
		return jo;
	}

	public Map<String, Map<String, String>> getExportMap() {
		return exportMap;
	}

	public void setExportMap(Map<String, Map<String, String>> exportMap) {
		this.exportMap = exportMap;
	}
	/**
	 * 放入导出例表的对象
	 * @param key 要导出列的字段名
	 * @param name 要导出列的数字类型值
	 * @param value 要导出列的数字类型值   对应的文本
	 */
	public void putExprotKV(String key,String name,String value) {
		Map<String, String> map = this.exportMap.get(key);
		if(map != null){
			map.put(name, value);
			this.exportMap.put(key, map);	
		} else {
			Map<String, String> tempMap = new HashMap<String, String>();
			tempMap.put(name, value);
			this.exportMap.put(key, tempMap);
		}
	}
	
	/**
	 * 取出导出例表的对应值
	 * @param key 要导出列的字段名
	 * @param name 导出列的数字类型值
	 * @return
	 */
	public String getExprotValue(String key,String name) {
		Map<String, String> map = this.exportMap.get(key);
		if(map != null){
			return map.get(name);
		} 
		return null;
	}

	public Object getSearch() {
		return search;
	}

	public void setSearch(Object search) {
		this.search = search;
	}
	
}

