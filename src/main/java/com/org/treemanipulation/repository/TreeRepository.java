package com.hingehealth.demo.repository;

import com.hingehealth.demo.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, Integer> {
}