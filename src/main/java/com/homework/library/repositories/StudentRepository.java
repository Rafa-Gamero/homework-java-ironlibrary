package com.homework.library.repositories;

import com.homework.library.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
    // You can add custom query methods here if needed
}