package com.homework.library.repositories;

import com.homework.library.models.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByStudent_Usn(String usn);
}