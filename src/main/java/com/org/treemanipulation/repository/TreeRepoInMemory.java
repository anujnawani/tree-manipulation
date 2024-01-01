package com.hingehealth.demo.repository;

import com.hingehealth.demo.entity.Tree;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class TreeRepoInMemory {

    static CopyOnWriteArrayList<Tree> dataStore = new CopyOnWriteArrayList<>();

    public long save(Tree tree) {
        dataStore.add(tree);
        return tree.getId();
    }

    public List<Tree> findAll() {
        return new ArrayList<>(dataStore);
    }

    public boolean contains(Long id) {
        return id == null ? false : dataStore.stream().anyMatch(t -> id.equals(t.getParentId()) || id.equals(t.getId()));
    }

    public boolean containsRoot() {
        return dataStore.stream().anyMatch(t -> t.getParentId() == null);
    }
}