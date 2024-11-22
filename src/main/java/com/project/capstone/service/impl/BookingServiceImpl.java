package com.project.capstone.service.impl;

import com.project.capstone.entity.Booking;
import com.project.capstone.repository.BookingRepository;
import com.project.capstone.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private BookingRepository bookingRepository;

    @Override
    public void save(Booking booking) {
        bookingRepository.save(booking);
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepository.findById(id).get();
    }
}
