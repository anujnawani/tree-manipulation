package com.hingehealth.demo.controllers;

import com.hingehealth.demo.dto.TreeRequestDto;
import com.hingehealth.demo.service.TreeService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api")
@Slf4j
public class TreeController {

    @Autowired
    private TreeService treeService;

    @RequestMapping(method = RequestMethod.GET, value = "/tree", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getTree() {
        JSONArray response = treeService.getTree();
        return ResponseEntity.ok(response.toString());
    }

    @PostMapping("/tree")
    public long saveTree(@Valid @RequestBody TreeRequestDto treeRequestDto) {
        return treeService.saveTree(treeRequestDto);
    }
}