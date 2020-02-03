package com.samton.platform.framework.db.ds;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import com.samton.platform.framework.db.ds.remote.RemoteDS;
import com.samton.platform.framework.log.util.LogUtil;


public class DynamicDataSourceAop implements BeanPostProcessor{
	
	private DynamicDataSource dataSource;
	
	private List<String> readMethods=new ArrayList<String>();
	
	public void setDataSource(DynamicDataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(!(bean instanceof NameMatchTransactionAttributeSource)) {
            return bean;
        }
        try {
            NameMatchTransactionAttributeSource transactionAttributeSource = (NameMatchTransactionAttributeSource)bean;
            Field nameMapField = ReflectionUtils.findField(NameMatchTransactionAttributeSource.class, "nameMap");
            nameMapField.setAccessible(true);
            @SuppressWarnings("unchecked")
			Map<String, TransactionAttribute> nameMap = (Map<String, TransactionAttribute>) nameMapField.get(transactionAttributeSource);
            for(Entry<String, TransactionAttribute> entry : nameMap.entrySet()) {
                RuleBasedTransactionAttribute attr = (RuleBasedTransactionAttribute)entry.getValue();
                if(!attr.isReadOnly()) {
                    continue;
                }
                String methodName = entry.getKey();
                attr.setPropagationBehavior(Propagation.SUPPORTS.value());
                readMethods.add(methodName);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

	public Object doAroundMethod(ProceedingJoinPoint pjp) throws Throwable {
		Object response = null;
		Method method=((MethodSignature)pjp.getSignature()).getMethod();
		String methodName = method.getName();
		boolean hasBinded = false;
		boolean removeRemoteFlag=false;
		try {
			// 以下策略可以抽成一个类
			hasBinded = dataSource.hasBindedDataSourse();
			if (!hasBinded) {
				//优先切换远程数据源
				String remoteKey=RemoteDS.getRemoteKey();
				if(StringUtils.isNotEmpty(remoteKey)){
					if(dataSource.remoteChangeFlag(remoteKey)){
						LogUtil.debugSystemLog(methodName+"切换到远程数据源--->"+remoteKey);
						dataSource.markRemote(remoteKey);
						removeRemoteFlag=true;
					}else {
						LogUtil.debugSystemLog(methodName+"切换到远程数据源失败--->请确保remoteDataSources中含有"+remoteKey);
					}
				}else {					
					boolean markSlaveFlag=false;
					for (String readMethod : readMethods) {
						if(PatternMatchUtils.simpleMatch(readMethod, methodName)){
							markSlaveFlag=true;
							break;
						}
					}
					if(markSlaveFlag){
						LogUtil.debugSystemLog(methodName+"切换到读数据源");
						dataSource.markSlave();
					} else {
						LogUtil.debugSystemLog(methodName+"切换到写数据源");
						dataSource.markMaster();
					}
				}
			}
			response = pjp.proceed();
		} finally {
			if(removeRemoteFlag)RemoteDS.removeRemoteKey();
			if (!hasBinded) {
				dataSource.markRemove();
			}
		}
		return response;
	}

}