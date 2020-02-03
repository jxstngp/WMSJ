package com.samton.platform.framework.util;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Set;

public class ConstantsMap {
    private LinkedHashMap<Object, Object> map;

    public ConstantsMap() {
	map = new LinkedHashMap<Object, Object>();
    }

    public void putConstant(Object value, Object desc) {
	map.put(value, desc);
    }

    public Object getDescByValue(Object value) {
	return map.get(value);
    }

    public Set getValueSet() {
	return map.keySet();
    }

    public Collection getDescSet() {
	return map.values();
    }

    public Set getConstantsSet() {
	return map.entrySet();
    }

}
