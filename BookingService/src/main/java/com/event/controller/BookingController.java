package com.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.dto.BookingDto;
import com.event.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService; // ✅ Injecting the Service Layer

    /**
     * 🟢 GET all bookings
     * -------------------------------------------------------------
     * URL: http://localhost:9004/bookings/all
     * Method: GET
     * Description: Fetches and returns a list of all booking records.
     * Example: Open this in browser or Postman → GET http://localhost:9004/bookings/all
     * Response: JSON array of all bookings
     */
    @GetMapping("/all")
    public List<BookingDto> getAllBookings() {
        System.out.println("Fetching all bookings...");
        return bookingService.getAllBookings();
    }

    /**
     * 🟢 GET booking by ID
     * -------------------------------------------------------------
     * URL: http://localhost:9004/bookings/{id}
     * Method: GET
     * Description: Fetches a single booking by its booking ID.
     * Example: GET http://localhost:9004/bookings/5
     * Response: JSON object of booking with ID 5
     */
    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable("id") int id) {
        System.out.println("Fetching booking with ID: " + id);
        return bookingService.getBookingById(id);
    }

    /**
     * 🟡 POST - Add a new booking
     * -------------------------------------------------------------
     * URL: http://localhost:9004/bookings/add
     * Method: POST
     * Description: Creates and saves a new booking record in the database.
     * Example (Postman - Body -> raw -> JSON):
     * {
     *     "userId": 1,
     *     "eventId": 2,
     *     "bookingDate": "2025-10-24",
     *     "status": "Confirmed"
     * }
     * Response: JSON object of the created booking
     */
    @PostMapping("/add")
    public BookingDto addBooking(@RequestBody BookingDto bookingDto) {
        System.out.println("Creating new booking: " + bookingDto);
        return bookingService.saveBooking(bookingDto);
    }

    /**
     * 🔴 DELETE - Delete booking by ID
     * -------------------------------------------------------------
     * URL: http://localhost:9004/bookings/delete/{id}
     * Method: DELETE
     * Description: Deletes a booking record with the given ID.
     * Example: DELETE http://localhost:9004/bookings/delete/3
     * Response: "Booking cancelled successfully!"
     */
    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        System.out.println("Cancelling booking with ID: " + id);
        bookingService.deleteBookingById(id);
        return "Booking cancelled successfully!";
    }
}
