package com.hingehealth.demo.util;

import com.hingehealth.demo.dto.TreeDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdjacencyMap {
    private Map<Long, List<Children>> map = new ConcurrentHashMap<>();
    private Long rootId;
    private String rootLabel;

    @AllArgsConstructor
    @Getter
    static class Children {
        private long id;
        private String label;
    }

    public AdjacencyMap(List<TreeDto> treeList) {
        for (TreeDto treeDto : treeList) {
            addChildren(treeDto.getId(), treeDto.getParentId(), treeDto.getLabel());
        }
    }

    public List<Children> get(long id) {
        return map.get(id);
    }

    public boolean containsKey(long id) {
        return map.containsKey(id);
    }

    public void addChildren(Long id, Long parentId, String label) {
        if (parentId == null) {
            rootId = id;
            rootLabel = label;
            map.put(id, new ArrayList<>());
        } else {
            map.putIfAbsent(parentId, new ArrayList<>());
            map.get(parentId).add(new Children(id, label));
        }
    }
}