package com.event.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.event.irepository.IEventRepository;
import com.event.model.Event;

@Repository  // ✅ THIS IS VERY IMPORTANT
public class EventRepository implements IEventRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Event event) {
        String sql = "INSERT INTO events (event_name, location, date, description, available_seats) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, event.getEventName(), event.getLocation(), event.getDate(),
                event.getDescription(), event.getAvailableSeats());
    }

    @Override
    public List<Event> findAll() {
        String sql = "SELECT * FROM events";
        return jdbcTemplate.query(sql, new EventRowMapper());
    }

    @Override
    public Event findById(int id) {
        String sql = "SELECT * FROM events WHERE event_id = ?";
        return jdbcTemplate.queryForObject(sql, new EventRowMapper(), id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM events WHERE event_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Event event) {
        String sql = "UPDATE events SET event_name=?, location=?, date=?, description=?, available_seats=? WHERE event_id=?";
        return jdbcTemplate.update(sql, event.getEventName(), event.getLocation(), event.getDate(),
                event.getDescription(), event.getAvailableSeats(), event.getEventId());
    }
}
