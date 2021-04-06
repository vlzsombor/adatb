package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo2;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @Autowired
    private FelhasznaloRepository2 felhasznaloRepository2;

    @GetMapping("/index")
    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/testModel")
    public String testModel(Model model) {
        Felhasznalo2 felhasznalo2 = new Felhasznalo2();
        felhasznalo2.setEmail("fasz@fasz.hu");
        felhasznalo2.setName("Fasz Joska");

        felhasznaloRepository2.save(felhasznalo2);

        System.out.println(felhasznaloRepository2.findAll());
        model.addAttribute("users", felhasznaloRepository2.findAll());
        return "testModel";
    }

    @GetMapping("/geri")
    public String gyeregeribazdmeg(Model model){
        return "geri";
    }

    // additional CRUD methods
}