package com.event.AuthenticationService.repository;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.event.AuthenticationService.dto.SignupDto;

@Repository
public class AuthRepository {
    
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean signUp(SignupDto cred, StringBuffer error) {
        try {
            String query = "INSERT INTO api_users (name, email, password) VALUES (?, ?, ?)";
            jdbcTemplate.update(query, cred.getName(), cred.getEmail(), cred.getPassword());
        } catch (Exception ex) {
            error.append(ex.getMessage());
            System.out.println("Error during user signup: " + error);
            return false;
        }
        return true;
    }

    public Boolean getPasswordFromEmail(String email, StringBuffer password, StringBuffer error) {
        try {
            String query = "SELECT password FROM api_users WHERE email = ?";
            String dbPassword = jdbcTemplate.queryForObject(query, String.class, email);
            password.append(dbPassword);
        } catch (Exception ex) {
            error.append(ex.getMessage());
            System.out.println("Error during user authentication: " + error);
            return false;
        }
        return true;
    }
}
