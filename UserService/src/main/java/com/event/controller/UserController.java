package com.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.dto.UserDto;
import com.event.service.UserService;

/**
 * UserController handles all HTTP requests related to "User" management.
 * It connects the frontend / API client (like Postman) with the service layer.
 */
@RestController
@RequestMapping("/users")  // Base URL for all user-related endpoints (e.g., /users/all, /users/{id})
public class UserController {

    // Inject the UserService to handle business logic and database operations
    @Autowired
    private UserService userService;

    /**
     * ✅ GET /users/all
     * Fetch all users from the database.
     * Example: http://localhost:9002/users/all
     * Returns: List of all users in JSON format.
     */
    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * ✅ GET /users/{id}
     * Fetch details of a specific user by ID.
     * Example: http://localhost:9002/users/4
     * Returns: User details (JSON) for the given ID, or error if not found.
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    /**
     * ✅ GET /users/{id}/name
     * Fetch only the username by ID (for microservice communication).
     * Example: http://localhost:9002/users/1/name
     * Returns: Username as a plain String.
     */
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getUsernameById(@PathVariable int id) {
        try {
            String username = userService.getUsernameById(id);
            return ResponseEntity.ok(username);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error fetching username");
        }
    }

    /**
     * ✅ POST /users/add
     * Create (add) a new user into the database.
     * Example: 
     *   POST http://localhost:9002/users/add
     *   Body (JSON):
     *   {
     *       "fullName": "John Doe",
     *       "email": "john@example.com",
     *       "password": "12345",
     *       "phoneNumber": "9876543210"
     *   }
     * Returns: The saved user details including generated userId.
     */
    @PostMapping("/add")
    public UserDto addUser(@RequestBody UserDto userDto) {
        return userService.saveUser(userDto);
    }

    /**
     * ✅ DELETE /users/delete/{id}
     * Delete a user by their ID.
     * Example: http://localhost:9002/users/delete/5
     * Returns: A confirmation message if successfully deleted, or an error if not found.
     */
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return "User deleted successfully!";
    }

    /**
     * 🧩 PUT /users/update/{id}
     * Update an existing user by ID.
     * Example:
     *   PUT http://localhost:9002/users/update/5
     *   Body (JSON):
     *   {
     *       "fullName": "Jane Doe",
     *       "email": "jane@example.com",
     *       "password": "newpassword",
     *       "phoneNumber": "9876501234"
     *   }
     * Returns: The updated user details.
     */
    @PutMapping("/update/{id}")
    public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}