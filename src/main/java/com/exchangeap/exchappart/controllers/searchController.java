package com.exchangeap.exchappart.controllers;

import com.exchangeap.exchappart.models.Application;
import com.exchangeap.exchappart.models.Search;
import com.exchangeap.exchappart.repository.ApplicationRepository;
import com.exchangeap.exchappart.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class searchController {
    @Autowired
    private SearchRepository searchRepository;
    @Autowired
    private ApplicationRepository applicationRepository;

    @GetMapping("/search/{id}")
    public String searchPage(@PathVariable(value = "id") Long id, org.springframework.ui.Model model) {
        Optional<Search> search = searchRepository.findById(id);

        // Перевірка наявності заявки з заданим id
        if (search.isEmpty()) {
            return "error-page";
        }

        ArrayList<Search> result = new ArrayList<>();
        search.ifPresent(result::add);
        model.addAttribute("search", result);

        // Пошук варіантів обміну, які задовольняють вимогам фільтра
        List<Application> options = new ArrayList<>();
        Search app = search.get();

        if (app.getFromsearchfloor().equals("null") ||
                app.getTosearchfloor().equals("null") ||
                app.getSearchrooms() == 0 ||
                app.getSearchregion().equals("null")) {
            for (Application option : applicationRepository.findAll()) {
                int fromsearchfloor = app.getFromsearchfloor().equals("null") ? 0 : Integer.parseInt(app.getFromsearchfloor());
                int floor = Integer.parseInt(option.getFloor());
                int tosearchfloor = app.getTosearchfloor().equals("null") ? 0 : Integer.parseInt(app.getTosearchfloor());
                if(app.getSearchrooms() == 0 ||
                        option.getRooms() == app.getSearchrooms()){
                    if(app.getSearchregion().equals("null") ||
                            option.getRegion().equals(app.getSearchregion())){
                        if(fromsearchfloor == 0 && tosearchfloor == 0){
                            options.add(option);
                        } else if(fromsearchfloor != 0 && tosearchfloor != 0 || fromsearchfloor == 0){
                            if(floor <= tosearchfloor
                                        && floor >= fromsearchfloor){
                                options.add(option);
                            }
                        } else if(tosearchfloor == 0){
                        if(floor <= tosearchfloor
                                || floor >= fromsearchfloor){
                            options.add(option);
                        }
                        }
                    }
                }
            }
        } else if (app.getFromsearchfloor() != "null" &&
                app.getTosearchfloor() != "null" &&
                app.getSearchrooms() != 0 &&
                app.getSearchregion() != "null") {
            for (Application option : applicationRepository.findAll()) {
                int fromsearchfloor = Integer.parseInt(app.getFromsearchfloor());
                int floor = Integer.parseInt(option.getFloor());
                int tosearchfloor = Integer.parseInt(app.getTosearchfloor());
                if (floor <= tosearchfloor &&
                        floor >= fromsearchfloor) {
                    if (option.getRooms() == app.getSearchrooms()) {
                        if (option.getRegion().equals(app.getSearchregion())) {
                            options.add(option);
                        }
                    }
                }
            }
        }

        if (options.isEmpty()) {
            return "no-search-page";
        }
        model.addAttribute("options", options);
        return "main-search";
    }

    @PostMapping("/search/{id}")
    public String searchEdit(@PathVariable(value="id") long id,
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
        Search search = searchRepository.findById(id).orElseThrow();
        search.setFromsearchfloor(fromsearchfloor);
        search.setTosearchfloor(tosearchfloor);
        search.setSearchrooms(searchrooms);
        search.setSearchregion(searchregion);
        searchRepository.save(search);
        return "redirect:/search/" + id;
    }

    @PostMapping("/search/{id}/remove")
    public String searchDelete(@PathVariable(value="id") long id, Model model){
        Search search = searchRepository.findById(id).orElseThrow();
        searchRepository.delete(search);
        return "redirect:/";
    }
}
