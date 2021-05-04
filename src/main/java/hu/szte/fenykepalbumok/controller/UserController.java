package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
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


        //
        // 4. feladat
        // kategoriaRepository.findAll().forEach(x -> System.out.println(x.getMegnevezes() + " " + x.getK//epek().size()));


        // mukodik 5. feladat
//         var id = kepRepository.getFelhasznaloIdOrderByFrequency().stream().findFirst();
//         if(id.isPresent()) System.out.println(felhasznaloRepository.findById(id.get()));


//        //todo 7. feladat ez igazabol nem is nem trivialis lekerdezes
//        var kat = kategoriaRepository.findByMegnevezes(KategoriaEnum.TERMESZET_FOTOK.toString());
//
//        var id2 = kepRepository.getFelhasznaloIdOrderByFrequency2(kat);
//        id2.forEach(x -> System.out.println(x));




        var a = kepRepository.test();
        System.out.println(a + "ide nezz");
        return "testModel";
    }

    @GetMapping("/geri")
    public String gyeregeribazdmeg(Model model) {
        return "geri";
    }

    // additional CRUD methods
}