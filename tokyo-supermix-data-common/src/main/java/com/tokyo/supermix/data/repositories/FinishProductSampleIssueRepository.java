package com.tokyo.supermix.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.tokyo.supermix.data.entities.FinishProductSampleIssue;

public interface FinishProductSampleIssueRepository
    extends JpaRepository<FinishProductSampleIssue, Long>,
    QuerydslPredicateExecutor<FinishProductSampleIssue> {

}
