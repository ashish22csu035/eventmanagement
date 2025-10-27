package com.event.dto;

public class BookingDto {

    private int bookingId;
    private int userId;
    private int eventId;
    private String bookingDate;
    private String status;
    private String username;      // Add this field
    private String eventName;     // Add this field

    // Default Constructor
    public BookingDto() {
    }

    // Parameterized Constructor
    public BookingDto(int bookingId, int userId, int eventId, String bookingDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    // Getters and Setters
    
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // New getters and setters for username and eventName
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    // Override toString() for easy debugging
    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", bookingDate='" + bookingDate + '\'' +
                ", status='" + status + '\'' +
                ", username='" + username + '\'' +
                ", eventName='" + eventName + '\'' +
                '}';
    }
}