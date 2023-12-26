package com.WebApplication.App.Services;

import com.WebApplication.App.Modal.User;

import java.util.List;

public interface AppServices {

    void saveData(User data);

    List<User> listALl();

    List<User> deleteBYId(int id);

    User editData(int id);

    void updateData(User data);

    List<User> searchName(String keyword);

    void sendWelcomeEmail(User data);


}
