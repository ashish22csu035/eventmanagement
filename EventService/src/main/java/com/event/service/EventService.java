package com.event.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.dto.EventDto;
import com.event.irepository.IEventRepository;
import com.event.model.Event;

@Service
public class EventService {

    @Autowired
    private IEventRepository eventRepository;

    // 🟡 Save Event
    public EventDto saveEvent(EventDto eventDto) {
        Event event = new Event();
        event.setEventName(eventDto.getEventName());
        event.setLocation(eventDto.getLocation());
        event.setDate(eventDto.getDate().toString());
        event.setDescription(eventDto.getDescription());
        event.setAvailableSeats(eventDto.getAvailableSeats());

        eventRepository.save(event);

        // Copy back generated ID
        eventDto.setEventId(event.getEventId());
        return eventDto;
    }

    // 🟢 Get All Events
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventDto> dtoList = new ArrayList<>();

        for (Event e : events) {
            EventDto dto = new EventDto();
            dto.setEventId(e.getEventId());
            dto.setEventName(e.getEventName());
            dto.setLocation(e.getLocation());
            dto.setDate(java.time.LocalDate.parse(e.getDate()));
            dto.setDescription(e.getDescription());
            dto.setAvailableSeats(e.getAvailableSeats());
            dtoList.add(dto);
        }

        return dtoList;
    }

    // 🟢 Get Event by ID
    public EventDto getEventById(int id) {
        Event e = eventRepository.findById(id);

        if (e != null && e.isPresent()) {
            EventDto dto = new EventDto();
            dto.setEventId(e.getEventId());
            dto.setEventName(e.getEventName());
            dto.setLocation(e.getLocation());
            dto.setDate(java.time.LocalDate.parse(e.getDate()));
            dto.setDescription(e.getDescription());
            dto.setAvailableSeats(e.getAvailableSeats());
            return dto;
        } else {
            throw new RuntimeException("Event not found with ID: " + id);
        }
    }

    // 🟢 Get Event Name by ID (for microservice communication)
    public String getEventNameById(int id) {
        try {
            Event e = eventRepository.findById(id);
            
            if (e != null && e.isPresent()) {
                return e.getEventName();
            } else {
                return "Unknown Event";
            }
        } catch (Exception e) {
            return "Unknown Event";
        }
    }

    // 🔴 Delete Event by ID
    public void deleteEventById(int id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cannot delete. Event not found with ID: " + id);
        }
    }

    // 🟠 Update Event (optional implementation)
    public EventDto updateEvent(int id, EventDto eventDto) {
        if (!eventRepository.existsById(id)) {
            throw new RuntimeException("Cannot update. Event not found with ID: " + id);
        }

        Event event = new Event();
        event.setEventId(id);
        event.setEventName(eventDto.getEventName());
        event.setLocation(eventDto.getLocation());
        event.setDate(eventDto.getDate().toString());
        event.setDescription(eventDto.getDescription());
        event.setAvailableSeats(eventDto.getAvailableSeats());

        eventRepository.update(event);

        return eventDto;
    }
}