package com.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.event.BookingService;
import com.event.dto.BookingDto;
import com.event.dto.PageResponse;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;


    @GetMapping("/all")
    public List<BookingDto> getAllBookings() {
        System.out.println("Fetching all bookings...");
        return bookingService.getAllBookings();
    }

  
    @GetMapping("/{id}")
    public BookingDto getBookingById(@PathVariable("id") int id) {
        System.out.println("Fetching booking with ID: " + id);
        return bookingService.getBookingById(id);
    }

   
    @PostMapping("/add")
    public BookingDto addBooking(@RequestBody BookingDto bookingDto) {
        System.out.println("Creating new booking: " + bookingDto);
        return bookingService.saveBooking(bookingDto);
    }

  
    @DeleteMapping("/delete/{id}")
    public String deleteBooking(@PathVariable("id") int id) {
        System.out.println("Cancelling booking with ID: " + id);
        bookingService.deleteBookingById(id);
        return "Booking cancelled successfully!";
    }

   
    @GetMapping("/paginated")
    public PageResponse<BookingDto> getBookingsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        System.out.println("Fetching bookings with pagination...");
        return bookingService.getBookingsPaginated(page, size);
    }
}
