package com.hingehealth.demo.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;

public class JSONObjectOrdered extends JSONObject {

    @Override
    public JSONObject put(String key, Object value) throws JSONException {
        try {
            Field map = JSONObject.class.getDeclaredField("map");
            map.setAccessible(true);
            Object mapValue = map.get(this);
            if (!(mapValue instanceof LinkedHashMap)) {
                map.set(this, new LinkedHashMap<>());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return super.put(key, value);
    }
}