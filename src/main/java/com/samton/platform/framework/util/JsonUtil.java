package com.samton.platform.framework.util;

import java.io.OutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationUtils;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

/**
 * 
 * @Description:JSON工具类
 * @author:     Alex
 * @date:        2017年2月23日 上午10:31:03
 * Copyright (c) 2017, Samton. All rights reserved
 */
public class JsonUtil {

	private static Log log = LogFactory.getLog(JsonUtil.class);

	private static ObjectMapper objectMapper = null;

	static {
		objectMapper = new ObjectMapper();
		// objectMapper.setDateFormat(new
		// SimpleDateFormat(FormatUtil.DATE_FORMAT_LONG));
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

	public static String stringify(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static String stringify(Object object, String... properties) {
		try {
			return objectMapper.writer(
					new SimpleFilterProvider().addFilter(
							AnnotationUtils.getValue(AnnotationUtils.findAnnotation(
													object.getClass(), JsonFilter.class))
											.toString(), SimpleBeanPropertyFilter
											.filterOutAllExcept(properties)))
					.writeValueAsString(object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return null;
	}

	public static void stringify(OutputStream out, Object object) {
		try {
			objectMapper.writeValue(out, object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static void stringify(OutputStream out, Object object, String... properties) {
		try {
			objectMapper.writer(new SimpleFilterProvider().addFilter(AnnotationUtils.getValue(
																				AnnotationUtils.findAnnotation(
																						object.getClass(), JsonFilter.class))
																					.toString(), SimpleBeanPropertyFilter
																					.filterOutAllExcept(properties)))
						.writeValue(out, object);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	public static <T> T parse(String json, Class<T> clazz) {
		if (json == null || json.length() == 0) {
			return null;
		}

		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}