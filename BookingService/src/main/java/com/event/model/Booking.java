package com.event.model;

public class Booking {

    private int bookingId;
    private int userId;
    private int eventId;
    private String bookingDate;
    private String status;

    // Default Constructor
    public Booking() {
    }

    //  Parameterized Constructor
    public Booking(int bookingId, int userId, int eventId, String bookingDate, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.eventId = eventId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    //  Getters and Setters
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

    // 🧾 toString() for debugging
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", userId=" + userId +
                ", eventId=" + eventId +
                ", bookingDate='" + bookingDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean isPresent() {
        // Consider a Booking "present" when it has a non-zero bookingId.
        return this.bookingId != 0;
    }

    public Booking get() {
        // Return this instance for compatibility with caller expectations.
        return this;
    }
}
