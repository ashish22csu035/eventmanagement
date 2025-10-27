package com.event.dto;

import java.time.LocalDate;

public class EventDto {
    private int eventId;               // changed 'id' → 'eventId'
    private String eventName;
    private String location;
    private LocalDate date;
    private String description;
    private int availableSeats;

    // ✅ Getters & Setters
    public int getEventId() { return eventId; }         // changed
    public void setEventId(int eventId) { this.eventId = eventId; }  // changed

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getAvailableSeats() { return availableSeats; }
    public void setAvailableSeats(int availableSeats) { this.availableSeats = availableSeats; }

    @Override
    public String toString() {
        return "EventDto{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
