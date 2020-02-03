/**
 * 
 */
package com.samton.platform.common.webscoket;

import java.io.IOException;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     shenchu
 * @date:        2017年3月4日 下午10:28:14
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class SystemWebSocketHandler implements WebSocketHandler{
	private static final Logger logger;
    private static final ArrayList<WebSocketSession> users;
    static {
        users = new ArrayList<WebSocketSession>();
        logger =  Logger.getLogger(SystemWebSocketHandler.class);
    }
    
	@Override
	public void afterConnectionEstablished(WebSocketSession session)
			throws Exception {
		  logger.debug("connect to the websocket success......");
		  users.add(session);
		  Long loginUserId = Long.parseLong(session.getAttributes().get("loginUserId").toString()) ;
		  if(loginUserId!= null){
	            //session.sendMessage(new TextMessage(remindService.getRemindMsg(loginUserId)));
	        }
	}


	@Override
	public void handleMessage(WebSocketSession session,
			WebSocketMessage<?> message) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void handleTransportError(WebSocketSession session,
			Throwable exception) throws Exception {
	      if(session.isOpen()){
	            session.close();
	        }
	        logger.debug("websocket connection closed......");
	        users.remove(session);
	}


	@Override
	public void afterConnectionClosed(WebSocketSession session,
			CloseStatus closeStatus) throws Exception {
		 logger.debug("websocket connection closed......");
	     users.remove(session);
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
    /**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     */
    public void sendMessageToUser(Long userId, TextMessage message) {
    
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("loginUserId").equals(userId.toString())) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //break;
            }
        }
    }	

}
