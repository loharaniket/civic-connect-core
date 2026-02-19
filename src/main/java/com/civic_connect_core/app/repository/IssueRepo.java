package com.civic_connect_core.app.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.civic_connect_core.app.entities.Issue;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {
    List<Issue> findByAutherId(Long autherId);
    List<Issue> findByDistId(Long distId);
}
