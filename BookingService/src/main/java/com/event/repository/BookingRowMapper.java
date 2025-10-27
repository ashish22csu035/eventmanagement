package com.event.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.event.model.Booking;

public class BookingRowMapper implements RowMapper<Booking> {

    @Override
    public Booking mapRow(ResultSet rs, int rowNum) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setEventId(rs.getInt("event_id"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setBookingDate(rs.getDate("booking_date").toString());
        booking.setStatus(rs.getString("status"));
        return booking;
    }
}
