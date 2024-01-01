package com.hingehealth.demo.mapper;

import com.hingehealth.demo.dto.TreeDto;
import com.hingehealth.demo.dto.TreeRequestDto;
import com.hingehealth.demo.entity.Tree;
import com.hingehealth.demo.util.IdGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TreeMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    IdGenerator idGenerator;

    public Tree toEntity(TreeRequestDto treeDto) {
        Tree tree = modelMapper.map(treeDto, Tree.class);
        tree.setId(idGenerator.generateId());
        return tree;
    }

    public TreeDto toDto(Tree tree) {
        TreeDto treeDto = modelMapper.map(tree, TreeDto.class);
        return treeDto;
    }
}
