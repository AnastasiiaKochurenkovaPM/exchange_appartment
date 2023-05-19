package com.exchangeap.exchappart.controllers;

import ch.qos.logback.core.model.Model;
import com.exchangeap.exchappart.models.Application;
import com.exchangeap.exchappart.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class createController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/create/application")
    public String createPage(Model model){
        return "create-page";
    }
    @PostMapping("/create/application")
    public String availableApartAdd(
            @RequestParam Integer rooms,
            @RequestParam Float area,
            @RequestParam String city,
            @RequestParam String floor,
            @RequestParam String wantfloor,
            @RequestParam String region,
            @RequestParam String wantregion,
            @RequestParam String number,
            Model model){
        Application application = new Application(
                rooms,
                area,
                city,
                floor,
                wantfloor,
                region,
                wantregion,
                number);
        applicationRepository.save(application);
        long id = application.getId();
        return "redirect:/exchange/" + id;
    }
}
