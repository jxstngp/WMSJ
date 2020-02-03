package com.samton.platform.framework.filter.wrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 
 * @Description:GZIP压缩
 * @author:     Alex
 * @date:        2017年2月22日 下午4:56:11
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class GZIPResponseStream extends ServletOutputStream {

    private static final Logger logger = Logger.getLogger(GZIPResponseStream.class);

    protected GZIPOutputStream gout = null;
    protected HttpServletResponse response = null;
    protected ByteArrayOutputStream baos = null;
    protected ServletOutputStream output = null;
    protected boolean closed = false;

    public GZIPResponseStream(HttpServletResponse response) throws IOException {
        super();
        this.closed = false;
        this.response = response;
        this.output = this.response.getOutputStream();
        this.baos = new ByteArrayOutputStream();
        this.gout = new GZIPOutputStream(baos);
    }

    @Override
    public void write(int b) throws IOException {
        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        gout.write((byte) b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        logger.info("writing...");
        logger.info(new String(b).trim());
        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        gout.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        if (closed) {
            throw new IOException("Cannot flush a closed output stream");
        }
        gout.finish();
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("This output stream has already been closed");
        }
        gout.finish();
        byte[] bytes = baos.toByteArray();
        response.addHeader("Content-Length", Integer.toString(bytes.length));
        response.addHeader("Content-Encoding", "gzip");
        output.write(bytes);
        output.flush();
        output.close();
        closed = true;
    }

    public boolean closed() {
        return this.closed;
    }

	@Override
	public boolean isReady() {
		return false;
	}

	@Override
	public void setWriteListener(WriteListener writeListener) {
		
	}

}
