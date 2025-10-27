package com.event.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.event.model.Event;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getInt("event_id"));
        event.setEventName(rs.getString("event_name"));
        event.setLocation(rs.getString("location"));
        event.setDate(rs.getString("date"));
        event.setDescription(rs.getString("description"));
        event.setAvailableSeats(rs.getInt("available_seats"));
        return event;
    }
}
