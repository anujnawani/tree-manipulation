package com.hingehealth.demo.service;

import com.hingehealth.demo.dto.TreeDto;
import com.hingehealth.demo.dto.TreeRequestDto;
import com.hingehealth.demo.entity.Tree;
import com.hingehealth.demo.enums.ErrorCode;
import com.hingehealth.demo.exception.ApplicationException;
import com.hingehealth.demo.mapper.TreeMapper;
import com.hingehealth.demo.repository.TreeRepoInMemory;
import com.hingehealth.demo.util.AdjacencyMap;
import com.hingehealth.demo.util.TreeCache;
import com.hingehealth.demo.util.TreeParser;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TreeRepoInMemory treeRepository;

    @Autowired
    private TreeMapper treeMapper;

    @Autowired
    private TreeParser treeParser;

    public JSONArray getTree() {
        if (!TreeCache.stateChanged.get()) {
            return TreeCache.jsonArray;
        }
        List<Tree> treeList = treeRepository.findAll();
        if(treeList.isEmpty()) {
            return new JSONArray();
        }
        List<TreeDto> treeDtos = treeList.stream().map(treeMapper::toDto).collect(Collectors.toList());
        AdjacencyMap adjacencyMap = new AdjacencyMap(treeDtos);
        JSONArray jsonArray = treeParser.getJSONTree(adjacencyMap);
        TreeCache.stateChanged.set(false);
        TreeCache.jsonArray = jsonArray;
        return jsonArray;
    }

    public long saveTree(TreeRequestDto treeDto) {
        validateRequest(treeDto);
        TreeCache.stateChanged.set(true);
        long id = treeRepository.save(treeMapper.toEntity(treeDto));
        return id;
    }

    private void validateRequest(TreeRequestDto requestDto) {
        if (requestDto.getParentId() != null && !treeRepository.contains(requestDto.getParentId())) {
            String errMsg = String.format("parent id - %s does not exist", requestDto.getParentId());
            throw new ApplicationException(ErrorCode.PARENT_NOT_FOUND.getValue(), errMsg, HttpStatus.NOT_FOUND);
        }
        if (requestDto.getParentId() == null && treeRepository.containsRoot()) {
            String errMsg = "root already exist";
            throw new ApplicationException(ErrorCode.ROOT_ALREADY_EXIST.getValue(), errMsg, HttpStatus.BAD_REQUEST);
        }
    }
}