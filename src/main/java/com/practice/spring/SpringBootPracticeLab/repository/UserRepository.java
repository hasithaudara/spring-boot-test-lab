package com.practice.spring.SpringBootPracticeLab.repository;

import com.practice.spring.SpringBootPracticeLab.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
