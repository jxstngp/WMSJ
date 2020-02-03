/**
 * 
 */
package com.samton.platform.common.webscoket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.samton.platform.framework.bean.UserCacheBean;
import com.samton.platform.framework.constant.WebConstant;

/**
 * @ClassName:   WebSocketHandshakeInterceptor
 * @Description: 创建握手（handshake）接口
 * @author:      shenchu
 * @date:        2015年9月18日 下午2:57:33
 */
public class WebSocketHandshakeInterceptor implements HandshakeInterceptor {


	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		 if (request instanceof ServletServerHttpRequest) {
	            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
	            HttpSession session = servletRequest.getServletRequest().getSession(false);
	            if (session != null) {
	                //使用userId区分WebSocketHandler，以便定向发送消息
	                String loginUserId =((UserCacheBean)session.getAttribute(WebConstant.USER_SESSION)).getUserId().toString();
	                attributes.put("loginUserId",loginUserId);
	            }
	        }
	        return true;
	}


	@Override
	public void afterHandshake(ServerHttpRequest request,
			ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception exception) {
		
		
	}

}
