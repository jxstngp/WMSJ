package com.samton.platform.framework.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samton.platform.framework.filter.wrapper.Wrapper;

/**
 * 
 * @Description:GZIP过滤器类
 * @author:     Alex
 * @date:        2017年2月22日 下午4:50:40
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class GZIPFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		String isGzip = req.getParameter("gzip");
		if (isGzip == null) {
			Wrapper wrapper = new Wrapper(resp);
			chain.doFilter(request, wrapper);
			byte[] gzipData = gzip(wrapper.getResponseData());
			// logger.info(new String(wrapper.getResponseData()));
			resp.addHeader("Content-Encoding", "gzip");
			resp.setContentLength(gzipData.length);
			ServletOutputStream output = response.getOutputStream();
			output.write(gzipData);
			output.flush();
		} else {
			response.setContentType("text/html;charset=UTF-8");
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	private byte[] gzip(byte[] data) {
		ByteArrayOutputStream byteOutput = new ByteArrayOutputStream(10240);
		GZIPOutputStream output = null;
		try {
			output = new GZIPOutputStream(byteOutput);
			output.write(data);
		} catch (IOException e) {
		} finally {
			try {
				output.close();
			} catch (IOException e) {
			}
		}
		return byteOutput.toByteArray();
	}
}
