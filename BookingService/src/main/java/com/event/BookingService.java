package com.event;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.event.dto.BookingDto;
import com.event.dto.PageResponse;
import com.event.irepository.IBookingRepository;
import com.event.model.Booking;

@Service
public class BookingService {

    @Autowired
    private IBookingRepository bookingRepository;

    @Value("${pagination.default.size:100}")
    private int defaultPageSize;

    // ✅ Get all bookings
    public List<BookingDto> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // ✅ Get booking by ID
    public BookingDto getBookingById(int id) {
        Booking booking = bookingRepository.findById(id);
        return convertToDto(booking);
    }

    // ✅ Save booking
    public BookingDto saveBooking(BookingDto bookingDto) {
        Booking booking = convertToEntity(bookingDto);
        bookingRepository.save(booking);
        return bookingDto;
    }

    // ✅ Delete booking
    public void deleteBookingById(int id) {
        bookingRepository.deleteById(id);
    }

    // ✅ PAGINATION METHOD (no JPA, only JdbcTemplate)
    public PageResponse<BookingDto> getBookingsPaginated(int page, int size) {
        if (size <= 0) {
            size = defaultPageSize;
        }

        // Get total records
        int totalElements = bookingRepository.countAll();
        int totalPages = (int) Math.ceil((double) totalElements / size);

        // Fetch paginated data
        List<Booking> bookings = bookingRepository.findAllPaginated(page, size);

        List<BookingDto> bookingDtos = bookings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        // Return custom PageResponse
        PageResponse<BookingDto> response = new PageResponse<>();
        response.setPage(page);
        response.setSize(size);
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        response.setContent(bookingDtos);

        return response;
    }

    // ✅ RANGE METHOD (start–end indices)
    public PageResponse<BookingDto> getBookingsInRange(int page, int start, int end) {
        if (start < 0 || end < start) {
            throw new IllegalArgumentException("Invalid range: end must be >= start");
        }

        // Get all records
        List<Booking> allBookings = bookingRepository.findAll();

        int totalElements = allBookings.size();
        int totalPages = (int) Math.ceil((double) totalElements / (end - start + 1));

        // Safe sublist indices
        int safeStart = Math.min(start, totalElements);
        int safeEnd = Math.min(end + 1, totalElements);

        List<Booking> rangeBookings = allBookings.subList(safeStart, safeEnd);
        List<BookingDto> rangeDtos = rangeBookings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        PageResponse<BookingDto> response = new PageResponse<>();
        response.setPage(page);
        response.setSize(rangeDtos.size());
        response.setTotalElements(totalElements);
        response.setTotalPages(totalPages);
        response.setContent(rangeDtos);

        return response;
    }

    // ✅ Helper Methods
    private BookingDto convertToDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setBookingId(booking.getBookingId());
        dto.setUserId(booking.getUserId());
        dto.setEventId(booking.getEventId());
        dto.setBookingDate(booking.getBookingDate());
        dto.setStatus(booking.getStatus());
        return dto;
    }

    private Booking convertToEntity(BookingDto dto) {
        Booking booking = new Booking();
        booking.setBookingId(dto.getBookingId());
        booking.setUserId(dto.getUserId());
        booking.setEventId(dto.getEventId());
        booking.setBookingDate(dto.getBookingDate());
        booking.setStatus(dto.getStatus());
        return booking;
    }
}
