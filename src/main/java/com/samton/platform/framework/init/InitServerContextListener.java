package com.samton.platform.framework.init;


import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.samton.platform.framework.log.util.LogUtil;
import com.samton.platform.framework.util.Public;
 
/**
 * 
 * @Description:Spring容器初始化监听器
 * @author:     Alex
 * @date:        2017年2月22日 下午4:59:30
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class InitServerContextListener extends org.springframework.web.context.ContextLoaderListener implements ServletContextListener {
	
    @Override
    public void contextInitialized(ServletContextEvent event) {
/*    	LogUtil.infoSystemLog("===================Begin to init==================");
        if (MemcachedContainer.getInstance().isMemcachedAliveTaskSwitch()) {
            new Timer().scheduleAtFixedRate(new InitMemcachedConnectionTimerTask(), new Date(), 5 * 60 * 1000);
        }*/
		System.out.println("Spring容器初始化开始......");
        super.contextInitialized(event);   
        System.out.println("Spring容器初始化结束......");
        
        
        //初始化参数表中的数据，加入到Application中
        ServletContext application = event.getServletContext();
        application.setAttribute("SYSTEM_NAME", Public.getParamValue("system_name", application));
        application.setAttribute("COPYRIGHT_INFO", Public.getParamValue("copyright_info", application));
        printSuccess();
    }

    private void printSuccess() {
        StringBuffer sb = new StringBuffer();
        sb.append("******SUCCESS***********SUCCESS*************SUCCESS*********\r\n");
        sb.append("=====SSS=====U===U=====CCC====CCC===EEEE=====SSS=====SSS====\r\n");
        sb.append("====S========U===U====C======C======E=======S=======S=======\r\n");
        sb.append("=====SSS=====U===U====C======C======EEEE=====SSS=====SSS====\r\n");
        sb.append("========S====U===U====C======C======E===========S=======S===\r\n");
        sb.append("=====SSS======UUU======CCC====CCC===EEEE=====SSS=====SSS====\r\n");
        sb.append("******SUCCESS***********SUCCESS*************SUCCESS*********\r\n");
        LogUtil.infoSystemLog(sb.toString());
    }

//    private void printFailed() {
//        StringBuffer sb = new StringBuffer();
//        sb.append("******FAILED*******FAILED *******FAILED******FAILED*********\r\n");
//        sb.append("=====FFFFF======A========IIIII====L=======EEEEE====DDDD=====\r\n");
//        sb.append("=====F=========A=A=========I======L=======E========D===D====\r\n");
//        sb.append("=====FFFF=====A===A========I======L=======EEEEE====D===D====\r\n");
//        sb.append("=====F=======AAAAAAA=======I======L=======E========D===D====\r\n");
//        sb.append("=====F=======A=====A=====IIIII====LLLLL===EEEEE====DDDD=====\r\n");
//        sb.append("******FAILED*******FAILED *******FAILED******FAILED*********\r\n");
//        LogUtil.infoSystemLog(sb.toString());
//    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}
}
