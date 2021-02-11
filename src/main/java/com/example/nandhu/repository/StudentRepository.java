package com.example.nandhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nandhu.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

}
