package com.exchangeap.exchappart.controllers;

import com.exchangeap.exchappart.models.Application;
import com.exchangeap.exchappart.models.Search;
import com.exchangeap.exchappart.repository.ApplicationRepository;
import com.exchangeap.exchappart.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private SearchRepository searchRepository;

    @GetMapping("/")
    public String main(Model model) {
        Iterable<Application> applications = applicationRepository.findAll();
        model.addAttribute("applications", applications);
        return "main";
    }

    @PostMapping("/")
    public String searchAdd(
            @RequestParam(required = false) String fromsearchfloor,
            @RequestParam(required = false) String tosearchfloor,
            @RequestParam(required = false) Integer searchrooms,
            @RequestParam(required = false) String searchregion,
            Model model) {
        //перевірка коректності вводу поверху(переставлення місцями)
        if (tosearchfloor != null && fromsearchfloor != null) {
            try {
                int toFloor = Integer.parseInt(tosearchfloor);
                int fromFloor = Integer.parseInt(fromsearchfloor);
                if (toFloor < fromFloor) {
                    String temp = tosearchfloor;
                    tosearchfloor = fromsearchfloor;
                    fromsearchfloor = temp;
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
            }
        }
        Search search = new Search(
                fromsearchfloor,
                tosearchfloor,
                searchrooms,
                searchregion);
        searchRepository.save(search);
        long id = search.getId();
        return "redirect:/search/" + id;
    }
}
