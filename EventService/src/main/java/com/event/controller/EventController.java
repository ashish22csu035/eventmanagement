package com.event.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.event.dto.EventDto;
import com.event.service.EventService;

/**
 * EventController handles all HTTP requests related to "Event" management.
 * It connects the frontend / API client (like Postman) with the service layer.
 */
@RestController
@RequestMapping("/events")  // Base URL for all endpoints (e.g., /events/all, /events/{id})
public class EventController {

    // Inject the EventService to handle business logic and database operations
    @Autowired
    private EventService eventService;

    /**
     * ✅ GET /events/all
     * Fetch all events from the database.
     * Example: http://localhost:9001/events/all
     * Returns: List of all events in JSON format.
     */
    @GetMapping("/all")
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    /**
     * ✅ GET /events/{id}
     * Fetch details of a specific event by ID.
     * Example: http://localhost:9001/events/5
     * Returns: Event details (JSON) for the given ID, or error if not found.
     */
    @GetMapping("/{id}")
    public EventDto getEventById(@PathVariable int id) {
        return eventService.getEventById(id);
    }

    /**
     * ✅ GET /events/{id}/name
     * Fetch only the event name by ID (for microservice communication).
     * Example: http://localhost:9001/events/4/name
     * Returns: Event name as a plain String.
     */
    @GetMapping("/{id}/name")
    public ResponseEntity<String> getEventNameById(@PathVariable int id) {
        try {
            String eventName = eventService.getEventNameById(id);
            return ResponseEntity.ok(eventName);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error fetching event name");
        }
    }

    /**
     * ✅ POST /events/add
     * Create (add) a new event into the database.
     * Example: 
     *   POST http://localhost:9001/events/add
     *   Body (JSON):
     *   {
     *       "eventName": "Music Festival",
     *       "location": "Delhi",
     *       "date": "2025-12-05",
     *       "description": "A fun music event",
     *       "availableSeats": 100
     *   }
     * Returns: The saved event details including generated eventId.
     */
    @PostMapping("/add")
    public EventDto addEvent(@RequestBody EventDto eventDto) {
        return eventService.saveEvent(eventDto);
    }

    /**
     * ✅ DELETE /events/delete/{id}
     * Delete an event by its ID.
     * Example: http://localhost:9001/events/delete/5
     * Returns: A confirmation message if successfully deleted, or an error if not found.
     */
    @DeleteMapping("/delete/{id}")
    public String deleteEvent(@PathVariable int id) {
        eventService.deleteEventById(id);
        return "Event deleted successfully!";
    }

    /**
     * 🧩 (Optional) PUT /events/update/{id}
     * Update an existing event by ID.
     * Example:
     *   PUT http://localhost:9001/events/update/5
     *   Body (JSON):
     *   {
     *       "eventName": "Updated Event",
     *       "location": "Mumbai",
     *       "date": "2025-12-10",
     *       "description": "Updated description",
     *       "availableSeats": 150
     *   }
     * Returns: The updated event details.
     */
    @PutMapping("/update/{id}")
    public EventDto updateEvent(@PathVariable int id, @RequestBody EventDto eventDto) {
        return eventService.updateEvent(id, eventDto);
    }
}