package com.event.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.event.irepository.IUserRepository;
import com.event.model.User;

/**
 * UserRepository handles all database operations related to users.
 * Uses JdbcTemplate for performing SQL queries (CRUD operations).
 */
@Repository  // ✅ Marks this class as a Spring-managed bean
public class UserRepository implements IUserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * ✅ Insert a new user into the database.
     */
    @Override
    public int save(User user) {
        String sql = "INSERT INTO users (full_name, email, password, phone_number) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber());
    }

    /**
     * ✅ Fetch all users from the database.
     */
    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    /**
     * ✅ Fetch user details by ID.
     */
    @Override
    public User findById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), id);
    }

    /**
     * ✅ Check if user exists by ID.
     */
    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM users WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    /**
     * ✅ Delete a user by ID.
     */
    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * ✅ Update user details by ID.
     */
    @Override
    public int update(User user) {
        String sql = "UPDATE users SET full_name=?, email=?, password=?, phone_number=? WHERE user_id=?";
        return jdbcTemplate.update(sql,
                user.getFullName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getUserId());
    }
}
