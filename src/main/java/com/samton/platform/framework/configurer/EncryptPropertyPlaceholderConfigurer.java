/**
 * 
 */
package com.samton.platform.framework.configurer;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;

import com.samton.platform.framework.util.AesUtils;

/**
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     shenchu
 * @date:        2017年8月10日 下午4:55:38
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	
	private static final String SEC_KEY = "@^_^123aBcZ*";    //主密钥
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private Set<String> encryptedProps = Collections.emptySet();
	public void setEncryptedProps(Set<String> encryptedProps) {
		 	this.encryptedProps = encryptedProps;
	}
	
   
	@SuppressWarnings("unused")
	@Override
    protected String convertProperty(String propertyName, String propertyValue) {
        if (encryptedProps.contains(propertyName)) { //如果在加密属性名单中发现该属性
    		byte[] decryptFrom = AesUtils.parseHexStr2Byte(propertyValue);
            String decryptedPropValue = new String(AesUtils.decrypt(decryptFrom, SEC_KEY));  //调用AES进行解密
            if (decryptedPropValue != null) {  //!=null说明正常
                propertyValue = decryptedPropValue; //设置解决后的值
            } else {
            	//说明解密失败
                logger.error("Decrypt " + propertyName + "=" + propertyValue + " error!");
            }
        }
        return super.convertProperty(propertyName, propertyValue);  //将处理过的值传给父类继续处理

    }

    protected Resource[] locations;

    @Override
    public void setLocations(Resource[] locations) {   //由于location是父类私有，所以需要记录到本类的locations中
        super.setLocations(locations);
        this.locations = locations;
    }

    @Override
    public void setLocation(Resource location) {   //由于location是父类私有，所以需要记录到本类的locations中
        super.setLocation(location);
        this.locations = new Resource[]{location};
    }

}
