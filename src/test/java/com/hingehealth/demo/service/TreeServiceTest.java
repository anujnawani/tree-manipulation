package com.hingehealth.demo.service;

import com.hingehealth.demo.dto.TreeDto;
import com.hingehealth.demo.dto.TreeRequestDto;
import com.hingehealth.demo.entity.Tree;
import com.hingehealth.demo.exception.ApplicationException;
import com.hingehealth.demo.mapper.TreeMapper;
import com.hingehealth.demo.repository.TreeRepoInMemory;
import com.hingehealth.demo.util.TestUtil;
import com.hingehealth.demo.util.TreeParser;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TreeServiceTest {
    @Mock
    TreeRepoInMemory treeRepo;

    @Mock
    TreeMapper treeMapper;

    @Mock
    TreeParser treeParser;

    @InjectMocks
    TreeServiceImpl treeService;

    @Test
    void saveTreeNode() {
        TreeRequestDto request = new TreeRequestDto(null, "a");
        when(treeRepo.containsRoot()).thenReturn(false);
        when(treeRepo.save(any())).thenReturn(1l);
        long result = treeService.saveTree(request);
        assertEquals(1, result);
    }

    @Test
    void saveTreeNodeNoParent() {
        TreeRequestDto request = new TreeRequestDto(1l, "a");
        when(treeRepo.contains(1l)).thenReturn(false);
        String expErrMsg = "parent id - 1 does not exist";
        ApplicationException exception = assertThrows(ApplicationException.class, () -> treeService.saveTree(request));
        assertEquals(expErrMsg, exception.getMessage());
    }

    @Test
    void getTree() {
        List<Tree> treeList = new ArrayList<>();
        treeList.add(new Tree(1l, "a", null));
        JSONArray jsonArray = TestUtil.data();
        when(treeRepo.findAll()).thenReturn(treeList);
        when(treeMapper.toDto(any())).thenReturn(new TreeDto(1l, "a", null));
        when(treeParser.getJSONTree(any())).thenReturn(jsonArray);
        JSONArray result = treeService.getTree();
        assertEquals(jsonArray, result);
    }
}