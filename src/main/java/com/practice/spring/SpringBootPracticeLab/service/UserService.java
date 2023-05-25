package com.practice.spring.SpringBootPracticeLab.service;

import com.practice.spring.SpringBootPracticeLab.dtos.UserDTO;
import com.practice.spring.SpringBootPracticeLab.entity.User;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(UserDTO user);

    void deleteUser(Long userId);
}
