package com.samton.platform.framework.filter.wrapper;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @Description:包装返回的数据
 * @author:     Alex
 * @date:        2017年2月22日 下午4:56:24
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class GZIPResponseWrapper extends HttpServletResponseWrapper {

    //	private static final Logger logger = Logger.getLogger(GZIPResponseWrapper.class);

    protected HttpServletResponse origResponse = null;
    protected ServletOutputStream stream = null;
    protected PrintWriter writer = null;
    protected PrintWriter writer_1 = null;

    public GZIPResponseWrapper(HttpServletResponse response) {
        super(response);
        this.origResponse = response;
    }

    public ServletOutputStream createOutputStream() throws IOException {
        return new GZIPResponseStream(origResponse);
    }

    public void finishResponse() {
        try {
            if (writer != null) {
                writer.close();
                writer = null;
            } else {
                if (stream != null) {
                    stream.close();
                    stream = null;
                }
            }
        } catch (IOException e) {
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        stream.flush();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException("getWriter() has already been called!");
        }
        if (stream == null) {
            stream = createOutputStream();
        }
        return stream;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer != null)
            return writer;
        if (stream != null) {
            throw new IllegalStateException("getOutputStream() has already been called!");
        }
        stream = createOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(stream, "utf-8"));
        return writer;
    }

    @Override
    public void setContentLength(int len) {
        super.setContentLength(len);
    }
}
