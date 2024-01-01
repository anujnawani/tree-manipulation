package com.hingehealth.demo.util;

import org.json.JSONArray;
import org.json.JSONObject;

public class TestUtil {
    public static JSONArray data() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        JSONObjectOrdered jsonObjectOrdered = new JSONObjectOrdered();
        jsonObjectOrdered.put("label", "a");
        jsonObjectOrdered.put("children", new JSONArray());
        jsonObject.put("1", jsonObjectOrdered);
        jsonArray.put(jsonObject);
        return jsonArray;
    }
}