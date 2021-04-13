package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

   // @Autowired
   // private FelhasznaloRepository2 felhasznaloRepository2;
   //
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private KepRepository kepRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/testModel")
    public String testModel(Model model) {



        //kategoriaRepository.findAll().forEach(x -> System.out.println(x.getMegnevezes() + " " + x.getKepek().size()));


        //todo 5. feladat
//        var id = kepRepository.getFelhasznaloIdOrderByFrequency().stream().findFirst();
//        if(id.isPresent()) System.out.println(felhasznaloRepository.findById(id.get()));

//todo ITT FOLYTATNI, hogy mukodjon
//        var id2 = kepRepository.getFelhasznaloIdOrderByFrequency2();

//        id2.forEach(x-> System.out.println(x));

        return "testModel";
    }

    @GetMapping("/geri")
    public String gyeregeribazdmeg(Model model) {
        return "geri";
    }

    // additional CRUD methods
}