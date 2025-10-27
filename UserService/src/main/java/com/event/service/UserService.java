package com.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.dto.UserDto;
import com.event.irepository.IUserRepository;
import com.event.model.User;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    // 🟡 Save User
    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.save(user);

        // Copy back generated ID
        userDto.setUserId(user.getUserId());
        return userDto;
    }

    // 🟢 Get All Users
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> dtoList = new ArrayList<>();

        for (User u : users) {
            UserDto dto = new UserDto();
            dto.setUserId(u.getUserId());
            dto.setFullName(u.getFullName());
            dto.setEmail(u.getEmail());
            dto.setPassword(u.getPassword());
            dto.setPhoneNumber(u.getPhoneNumber());
            dtoList.add(dto);
        }

        return dtoList;
    }

    // 🟢 Get User by ID
    public UserDto getUserById(int id) {
        User u = userRepository.findById(id);

        if (u != null) {
            UserDto dto = new UserDto();
            dto.setUserId(u.getUserId());
            dto.setFullName(u.getFullName());
            dto.setEmail(u.getEmail());
            dto.setPassword(u.getPassword());
            dto.setPhoneNumber(u.getPhoneNumber());
            return dto;
        } else {
            throw new RuntimeException("User not found with ID: " + id);
        }
    }

    // 🟢 Get Username by ID (for microservice communication)
    public String getUsernameById(int id) {
        try {
            User u = userRepository.findById(id);
            
            if (u != null) {
                return u.getFullName();
            } else {
                return "Unknown User";
            }
        } catch (Exception e) {
            return "Unknown User";
        }
    }

    // 🔴 Delete User by ID
    public void deleteUserById(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. User not found with ID: " + id);
        }
    }

    // 🟠 Update User
    public UserDto updateUser(int id, UserDto userDto) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Cannot update. User not found with ID: " + id);
        }

        User user = new User();
        user.setUserId(id);
        user.setFullName(userDto.getFullName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());

        userRepository.update(user);

        return userDto;
    }
}