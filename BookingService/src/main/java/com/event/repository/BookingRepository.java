package com.event.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.event.irepository.IBookingRepository;
import com.event.model.Booking;

@Repository
public class BookingRepository implements IBookingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Booking booking) {
        String sql = "INSERT INTO bookings (user_id, event_id, booking_date, status) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, booking.getUserId(), booking.getEventId(),
                booking.getBookingDate(), booking.getStatus());
    }

    @Override
    public List<Booking> findAll() {
        String sql = "SELECT * FROM bookings";
        return jdbcTemplate.query(sql, new BookingRowMapper());
    }

    @Override
    public Booking findById(int id) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        return jdbcTemplate.queryForObject(sql, new BookingRowMapper(), id);
    }

    @Override
    public boolean existsById(int id) {
        String sql = "SELECT COUNT(*) FROM bookings WHERE booking_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public int deleteById(int id) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(Booking booking) {
        String sql = "UPDATE bookings SET user_id=?, event_id=?, booking_date=?, status=? WHERE booking_id=?";
        return jdbcTemplate.update(sql,
                booking.getUserId(),
                booking.getEventId(),
                booking.getBookingDate(),
                booking.getStatus(),
                booking.getBookingId());
    }

    // ✅ NEW METHOD: Get paginated bookings
    @Override
    public List<Booking> findAllPaginated(int page, int size) {
        int offset = page * size;
        String sql = "SELECT * FROM bookings LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, new BookingRowMapper(), size, offset);
    }

    // ✅ NEW METHOD: Count total bookings
    @Override
    public int countAll() {
        String sql = "SELECT COUNT(*) FROM bookings";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count != null ? count : 0;
    }

    public List<Booking> getBookingsInRange(String startDate, String endDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBookingsInRange'");
    }
}