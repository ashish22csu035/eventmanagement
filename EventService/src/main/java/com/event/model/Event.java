package com.event.model;

public class Event {

    private int eventId;
    private String eventName;
    private String location;
    private String date;
    private String description;
    private int availableSeats;

    // 🧱 Default Constructor
    public Event() {
    }

    // ⚙️ Parameterized Constructor
    public Event(int eventId, String eventName, String location, String date, String description, int availableSeats) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.description = description;
        this.availableSeats = availableSeats;
    }

    // 🎯 Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    // 🧾 toString() for debugging and logs
    @Override
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", description='" + description + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }

    // ✅ Helper method (optional, similar to Booking model)
    public boolean isPresent() {
        // Consider an Event "present" when it has a non-zero eventId.
        return this.eventId != 0;
    }

    public Event get() {
        // Return this instance for compatibility with caller expectations.
        return this;
    }
}
