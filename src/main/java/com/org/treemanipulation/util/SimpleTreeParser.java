package com.hingehealth.demo.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class SimpleTreeParser implements TreeParser {

    private AdjacencyMap map;

    public JSONArray getJSONTree(AdjacencyMap adjacencyMap) throws JSONException {
        map = adjacencyMap;
        JSONArray jsonArray = new JSONArray();
        json(map.getRootId(), map.getRootLabel(), jsonArray);
        return jsonArray;
    }

    private void json(Long id, String label, JSONArray jsonArray) {
        if (!map.containsKey(id) || map.get(id).isEmpty()) {
            addNode(id, label, jsonArray, new JSONArray());
            return;
        }
        List<AdjacencyMap.Children> children = map.get(id);
        JSONArray childObject = new JSONArray();
        addNode(id, label, jsonArray, childObject);
        for (AdjacencyMap.Children child : children) {
            json(child.getId(), child.getLabel(), childObject);
        }
    }

    private void addNode(Long id, String label, JSONArray jsonArray, JSONArray child) {
        JSONObject idObject = new JSONObject();
        JSONObjectOrdered labelObject = new JSONObjectOrdered();
        labelObject.put("label", label);
        labelObject.put("children", child);
        idObject.put(String.valueOf(id), labelObject);
        jsonArray.put(idObject);
    }
}