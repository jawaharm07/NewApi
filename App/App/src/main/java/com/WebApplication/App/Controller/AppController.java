package com.WebApplication.App.Controller;
import com.WebApplication.App.Modal.User;
import com.WebApplication.App.Services.AppServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    private AppServices appServices;


    @RequestMapping("/welcome")
    public String home(){
        return "home";
    }
    @RequestMapping("/login")
    public  String login(){
        return "login";
    }
    @RequestMapping("/save")
    public String saveData(User data){
        appServices.saveData(data);
       appServices.sendWelcomeEmail(data);
        return "saved";
    }
    @RequestMapping("/list")
    public String listData(Model model){
        List<User> datas =appServices.listALl();
        model.addAttribute("datas",datas);
        return "list";
    }
    @RequestMapping("/delete/{id}")
    public String deleteData(@PathVariable int id,Model model) {
        List<User> datas =appServices.deleteBYId(id);
        model.addAttribute("datas",datas);
        return "list";

    }
    @RequestMapping("/update/{id}")
    public String updateData(@PathVariable ("id") int id,Model model) {
        User datas =appServices.editData(id);
        model.addAttribute("datas",datas);
        return "update";
    }
    @RequestMapping("/updatelist")
    public String updateList(User data,Model model) {
        appServices.updateData(data);
        List<User> datas =appServices.listALl();
        model.addAttribute("datas",datas);
        return "list";
    }
    @GetMapping("/search")
    public String search(Model model, @RequestParam ("keyword") String keyword) {
        List<User> datas =appServices.searchName(keyword);
        model.addAttribute("datas",datas);
        return "list";
    }

}
