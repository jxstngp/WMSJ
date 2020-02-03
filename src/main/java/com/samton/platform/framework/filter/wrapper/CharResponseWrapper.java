package com.samton.platform.framework.filter.wrapper;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 
 * @Description:包装返回结果
 * @author:     Alex
 * @date:        2017年2月22日 下午4:55:20
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class CharResponseWrapper extends HttpServletResponseWrapper {

    protected CharArrayWriter output;

    public CharResponseWrapper(HttpServletResponse response) {
        super(response);
        output = new CharArrayWriter();
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(output);
    }

    @Override
    public String toString() {

        return output.toString();
    }
}
