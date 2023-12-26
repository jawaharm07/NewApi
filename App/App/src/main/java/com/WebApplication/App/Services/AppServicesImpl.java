package com.WebApplication.App.Services;
import com.WebApplication.App.Modal.User;
import com.WebApplication.App.Repositary.AppRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppServicesImpl implements AppServices{
    @Autowired
    private AppRepositary appRepositary;
    @Autowired
    private JavaMailSender javaMailSender;


    @Override
    public void saveData(User data) {
        appRepositary.save(data);
    }

    @Override
    public List<User> listALl() {
      List<User> userList = appRepositary.findAll();
        return userList;
    }

    @Override
    public List<User> deleteBYId(int id) {
        appRepositary.deleteById(id);
        List<User> userList = appRepositary.findAll();
        return userList;
    }

    @Override
    public User editData(int id) {
        Optional<User> find  =appRepositary.findById(id);
        return find.get();
    }

    @Override
    public void updateData(User data) {
        appRepositary.save(data);
    }

    @Override
    public List<User> searchName(String keyword) {
        return appRepositary.findByUsernameContainingIgnoreCase(keyword);
    }
@Override
    public void sendWelcomeEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Welcome to Our Online Booking");
        String emailContent = String.format(
                "Dear %s,\n\nWelcome to our WebApplication! We are excited to have you on board,Your Movie was Booked Successfully.Please Kindly Check Details Mentioned.\n\n" +
                        "User Details:\nUsername: %s" + "\nBookingId: %s"+
                        "\nEmail: %s"+"\nMovie: %s"+"\nDate: %s\n\n" +
                        "Thank you for registering with our application. We appreciate your participation!",
                user.getUsername(), user.getUsername(),user.getUsername(), user.getEmail(),user.getMovie(),user.getDate()
        );
        message.setText(emailContent);
        javaMailSender.send(message);
    }


}
