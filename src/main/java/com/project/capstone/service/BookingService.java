package com.project.capstone.service;


import com.project.capstone.entity.Booking;

public interface BookingService {
    void save(Booking booking);

    Booking getById(Long id);
}
