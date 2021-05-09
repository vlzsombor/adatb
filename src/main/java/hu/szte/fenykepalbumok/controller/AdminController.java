package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.repository.OrszagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private OrszagRepository orszagRepository;
    @Autowired
    private KepRepository kepRepository;

    @GetMapping("/admin")
    public String root(Model model) {

        model.addAttribute("felhasznalok", felhasznaloRepository.findAll());
        model.addAttribute("updatefelhasznalo", new Felhasznalo());
        model.addAttribute("elsolekerdezes", orszagRepository.elso_lekerdezes());
        model.addAttribute("masodiklekerdezes", orszagRepository.masodik_lekerdezes());
        //harmadik megvalositva -> ertekeles megjelenítése
        model.addAttribute("negyediklekerdezes", orszagRepository.negyedik_lekerdezes());
        model.addAttribute("kilencediklekerdezes", kepRepository.telepulesenkentHanyFenyep());



        return "/admin/index";

    }

}