package com.hingehealth.demo.controllers;

import com.hingehealth.demo.dto.TreeRequestDto;
import com.hingehealth.demo.service.TreeService;
import com.hingehealth.demo.util.TestUtil;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreeControllerTest {
    @Mock
    TreeService treeService;

    @InjectMocks
    TreeController treeController;

    @Test
    void saveNode() {
        TreeRequestDto request = new TreeRequestDto(null, "a");
        when(treeService.saveTree(request)).thenReturn(1l);
        long response = treeController.saveTree(request);
        assertEquals(1, response);
    }

    @Test
    void getEmptyTree() {
        JSONArray result = new JSONArray();
        when(treeService.getTree()).thenReturn(result);
        ResponseEntity response = treeController.getTree();
        System.out.println(response.getBody());
        assertEquals("[]", response.getBody());
    }

    @Test
    void getTree() {
        JSONArray result = TestUtil.data();
        when(treeService.getTree()).thenReturn(result);
        ResponseEntity response = treeController.getTree();
        System.out.println(response.getBody());
        assertEquals("[{\"1\":{\"label\":\"a\",\"children\":[]}}]", response.getBody());
    }
}