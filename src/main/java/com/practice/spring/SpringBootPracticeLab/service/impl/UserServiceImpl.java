package com.practice.spring.SpringBootPracticeLab.service.impl;

import com.practice.spring.SpringBootPracticeLab.dtos.UserDTO;
import com.practice.spring.SpringBootPracticeLab.entity.User;
import com.practice.spring.SpringBootPracticeLab.exception.UserNotfoundException;
import com.practice.spring.SpringBootPracticeLab.repository.UserRepository;
import com.practice.spring.SpringBootPracticeLab.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Override
    public UserDTO createUser(UserDTO userDto) {
        User user = modelMapper.map(userDto, User.class);
        User savedUser = userRepository.save(user);
        UserDTO savedUserDto = modelMapper.map(savedUser, UserDTO.class);
        LOGGER.info(savedUserDto.getFirstName() + " " + savedUser.getLastName() + " has been created successfully");
        return savedUserDto;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        LOGGER.info("At UserServiceImpl : getUserById() info");
        LOGGER.debug("At UserServiceImpl : getUserById() debug");
        Optional<User> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isPresent()) {
            UserDTO userDTO = modelMapper.map(optionalUserEntity.get(), UserDTO.class);
            return userDTO;
        } else {
            LOGGER.error("cannot find a user with id " + userId);
            throw new UserNotfoundException("cannot find a resource with id " + userId);
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTO> userDtos = new ArrayList<UserDTO>();
        for (User user : users) {
            userDtos.add(modelMapper.map(user, UserDTO.class));
        }
        return userDtos;
    }

    @Override
    public UserDTO updateUser(UserDTO userDto) {
        Optional<User> optionalUserEntity = userRepository.findById(userDto.getId());
        if (optionalUserEntity.isPresent()) {
            User existingUser = optionalUserEntity.get();
            existingUser.setFirstName(userDto.getFirstName());
            existingUser.setLastName(userDto.getLastName());
            existingUser.setEmail(userDto.getEmail());
            User updatedUser = userRepository.save(existingUser);
            UserDTO updatedUserDto = modelMapper.map(updatedUser, UserDTO.class);
            LOGGER.info(updatedUserDto.getFirstName() + " " + updatedUserDto.getLastName() + " has been updated successfully");
            return updatedUserDto;
        } else {
            LOGGER.error("Failed to update user " + userDto.getFirstName() + " " + userDto.getLastName());
            throw new UserNotfoundException("cannot find a resource with id " + userDto.getId() + " for update operation");
        }
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> optionalUserEntity = userRepository.findById(userId);
        if (optionalUserEntity.isPresent()) {
            userRepository.deleteById(userId);
            LOGGER.info("User with given id has been deleted successfully");
        } else {
            LOGGER.error("cannot find a user with id " + userId);
            throw new UserNotfoundException("cannot find a resource with id " + userId + " for delete operation");
        }
    }
}
