package com.example.nandhu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.nandhu.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    
}