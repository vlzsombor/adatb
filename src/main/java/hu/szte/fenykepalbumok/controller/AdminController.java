package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @GetMapping("/admin")
    public String root(Model model) {

        model.addAttribute("felhasznalok", felhasznaloRepository.findAll());
        model.addAttribute("updatefelhasznalo", new Felhasznalo());


        return "/admin/index";

    }

}