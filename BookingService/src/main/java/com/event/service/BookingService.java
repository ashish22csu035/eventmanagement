package com.event.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.dto.BookingDto;
import com.event.model.Booking;
import com.event.repository.BookingRepository;
import com.event.restclient.EventRestClient;
import com.event.restclient.UserRestClient;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private EventRestClient eventRestClient;

    @Autowired
    private ModelMapper modelMapper;

    // Get all bookings
    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll()
                .stream()
                .map(booking -> {
                    BookingDto dto = modelMapper.map(booking, BookingDto.class);
                    dto.setUsername(userRestClient.getUsernameById(booking.getUserId()));
                    dto.setEventName(eventRestClient.getEventNameById(booking.getEventId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // Get booking by ID
    public BookingDto getBookingById(int id) {
        try {
            Booking booking = bookingRepository.findById(id);
            if (booking == null) {
                return null;
            }
            
            BookingDto dto = modelMapper.map(booking, BookingDto.class);
            dto.setUsername(userRestClient.getUsernameById(booking.getUserId()));
            dto.setEventName(eventRestClient.getEventNameById(booking.getEventId()));
            
            return dto;
        } catch (Exception e) {
            // Handle case when booking not found
            return null;
        }
    }

    // Save a new booking
    public BookingDto saveBooking(BookingDto bookingDto) {
        // Map DTO → Entity
        Booking booking = modelMapper.map(bookingDto, Booking.class);
        bookingRepository.save(booking);

        // For JDBC Template, we need to fetch the booking again or use the same object
        // Since save returns int (rows affected), we'll use the booking object
        BookingDto responseDto = modelMapper.map(booking, BookingDto.class);
        responseDto.setUsername(userRestClient.getUsernameById(booking.getUserId()));
        responseDto.setEventName(eventRestClient.getEventNameById(booking.getEventId()));

        return responseDto;
    }

    // Delete booking by ID
    public void deleteBookingById(int id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        }
    }
}