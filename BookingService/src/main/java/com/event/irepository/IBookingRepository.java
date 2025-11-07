package com.event.irepository;

import java.util.List;

import com.event.model.Booking;

public interface IBookingRepository {
    // ✅ ADD THESE NEW METHODS FOR PAGINATION
    List<Booking> findAllPaginated(int page, int size);
    int countAll();

    // Save a new booking
    int save(Booking booking);

    // Fetch all bookings
    List<Booking> findAll();

    // Fetch a booking by ID
    Booking findById(int id);

    // Delete booking by ID
    int deleteById(int id);

    // Optional: Update booking by ID
    int update(Booking booking);

    public boolean existsById(int id);
}
