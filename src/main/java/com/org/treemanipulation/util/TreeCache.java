package com.hingehealth.demo.util;

import lombok.Data;
import org.json.JSONArray;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public class TreeCache {
    public static AtomicBoolean stateChanged = new AtomicBoolean(true);
    public static JSONArray jsonArray;
}
