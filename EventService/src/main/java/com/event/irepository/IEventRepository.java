package com.event.irepository;

import java.util.List;

import com.event.model.Event;

public interface IEventRepository {
    int save(Event event);
    List<Event> findAll();
    Event findById(int id);
    boolean existsById(int id);
    int deleteById(int id);
    int update(Event event);
}
