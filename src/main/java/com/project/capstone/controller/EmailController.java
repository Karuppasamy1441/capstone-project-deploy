package com.project.capstone.controller;

import com.project.capstone.entity.Booking;
import com.project.capstone.repository.BookingRepository;
import com.project.capstone.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class EmailController {
    @Autowired
    private EmailService emailService;

    private BookingRepository bookingRepository;


    @PostMapping("/pay/{id}")
    public String processPayment(@RequestParam("email") String email, @PathVariable("id")Long id, Model model) {
        Booking booking=bookingRepository.findById(id).get();
        String subject = "🎟 Your Bus Booking Confirmation [Booking ID: #" + booking.getId() + "]";
        String body="  Thank you for booking with "+booking.getBusName()+" Bus Services! Your booking has been successfully confirmed. Below are your trip details:\n" +
                "\n" + " **Booking Details:**\n" + " - Booking ID: " +booking.getId() +" \n"+booking.getBus().getSource()+" to "+booking.getBus().getDestination()+"\n"+
                " - Date : " +booking.getDate()+"\n"+" -Time :"+booking.getTime()+"\n" +"-No of passengers : "+booking.getNoOfPersons()+"\n\n"+
                "Please arrive at the boarding point at least 15 minutes before departure.\n\n" + "\n" +
                " Best Regards,\n"  +booking.getBusName()+" Bus Service\n";
        emailService.sendEmail(email,subject,body);
        model.addAttribute("message", "Payment successful! Confirmation email sent...");

        return "confirmation";
    }
}
