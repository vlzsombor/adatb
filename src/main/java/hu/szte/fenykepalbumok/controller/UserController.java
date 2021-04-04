package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/testModel")
    public String testModel(Model model) {
        Felhasznalo felhasznalo = new Felhasznalo();
        felhasznalo.setEmail("fasz@fasz.hu");
        felhasznalo.setName("Fasz Joska");

        felhasznaloRepository.save(felhasznalo);

        System.out.println(felhasznaloRepository.findAll());
        model.addAttribute("users", felhasznaloRepository.findAll());
        return "testModel";
    }

    @GetMapping("/geri")
    public String gyeregeribazdmeg(Model model){
        return "geri";
    }

    // additional CRUD methods
}