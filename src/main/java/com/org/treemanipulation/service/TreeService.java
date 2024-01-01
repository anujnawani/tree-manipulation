package com.hingehealth.demo.service;

import com.hingehealth.demo.dto.TreeRequestDto;
import org.json.JSONArray;

public interface TreeService {
    JSONArray getTree();

    long saveTree(TreeRequestDto tree);
}