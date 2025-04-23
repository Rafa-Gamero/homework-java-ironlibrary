package com.homework.library.repositories;

import com.homework.library.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // You can add custom query methods here if needed
}