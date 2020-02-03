package com.samton.platform.framework.db.ds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	private static final ThreadLocal<String> dataSourceHolder = new ThreadLocal<String>();
	
	private List<String> masterDataSourcesKeys;
	private List<String> slaveDataSourcesKeys;
	private List<String> remoteDataSourcesKeys;
	
	private  Map<String,DataSource> masterDataSources;
	private  Map<String,DataSource> slaveDataSources;
	private  Map<String,DataSource> remoteDataSources;
	
	private AtomicInteger masterCounter = new AtomicInteger(0);
	private AtomicInteger slaveCounter = new AtomicInteger(0);

	@Override
	protected Object determineCurrentLookupKey() {
		return dataSourceHolder.get();
	}
	
	public void setMasterDataSources(Map<String, DataSource> masterDataSources) {
		if (masterDataSources == null||masterDataSources.size()==0) {
			throw new IllegalArgumentException("Property 'masterDataSource' is required");
		}
		this.masterDataSources = masterDataSources;
		this.masterDataSourcesKeys = new ArrayList<String>();
		this.masterDataSourcesKeys.addAll(masterDataSources.keySet());
	}
	
	public void setSlaveDataSources(Map<String, DataSource> slaveDataSources) {
		if (slaveDataSources == null || slaveDataSources.size() == 0) {
			return;
		}
		this.slaveDataSources = slaveDataSources;
		slaveDataSourcesKeys = new ArrayList<String>();
		slaveDataSourcesKeys.addAll(slaveDataSources.keySet());
	}
	
	public void setRemoteDataSources(Map<String, DataSource> remoteDataSources) {
		if (remoteDataSources == null || remoteDataSources.size() == 0) {
			return;
		}
		this.remoteDataSources = remoteDataSources;
		remoteDataSourcesKeys = new ArrayList<String>();
		remoteDataSourcesKeys.addAll(remoteDataSources.keySet());
	}

	@Override
	public void afterPropertiesSet() {
		logger.debug("开始向spring routing datasource 提供数据源选取");
		Map<Object, Object> allDataSources = new HashMap<Object, Object>();
		allDataSources.putAll(masterDataSources);
		if (slaveDataSources != null) {
			allDataSources.putAll(slaveDataSources);
		}
		if(remoteDataSources!=null){
			allDataSources.putAll(remoteDataSources);
		}
		super.setTargetDataSources(allDataSources);
		super.afterPropertiesSet();
		logger.debug("已经完成向spring routing datasource 提供数据源选取");
	}

	public void markSlave() {
		if (dataSourceHolder.get() != null) {
			throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dataSourceHolder.get());
		}
		String dataSourceKey = selectFromSlave();
		setDataSource(dataSourceKey);
	}
	
	public void markRemote(String remoteKey) {
		if (dataSourceHolder.get() != null) {
			throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dataSourceHolder.get());
		}
		setDataSource(remoteKey);
	}

	public void markMaster() {
		if (dataSourceHolder.get() != null) {
			throw new IllegalArgumentException("当前已有选取数据源,不允许覆盖,已选数据源key:" + dataSourceHolder.get());
		}
		String dataSourceKey = selectFromMaster();
		setDataSource(dataSourceKey);
	}

	public void markRemove() {
		dataSourceHolder.remove();
	}

	public boolean hasBindedDataSourse(){
		return dataSourceHolder.get()!=null;
	}
	
	private int getCurrentIndex(AtomicInteger counter,int count){
		if(count==1) return 0;
		int index=counter.getAndIncrement()%count;
		if(index==0)counter.set(0);
		return index;
	}

	private String selectFromSlave() {
		if (slaveDataSources == null) {
			return selectFromMaster();
		} else {
			int index=getCurrentIndex(slaveCounter,slaveDataSourcesKeys.size());
			return slaveDataSourcesKeys.get(index);
		}
	}

	private String selectFromMaster() {
		int index=getCurrentIndex(masterCounter,masterDataSourcesKeys.size());
		return masterDataSourcesKeys.get(index);
	}

	private void setDataSource(String dataSourceKey) {
		dataSourceHolder.set(dataSourceKey);
	}
	
	public boolean remoteChangeFlag(String remoteKey){
		return remoteDataSourcesKeys!=null&&StringUtils.isNotEmpty(remoteKey)&&remoteDataSourcesKeys.contains(remoteKey);
	}
}
