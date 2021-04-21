package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.utils.FileUploadUtil;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EgyFosController {
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private KepRepository kepRepository;

    @GetMapping("/egyfostest")
    public String testModel(Model model) {






        //todo 7. feladat ez igazabol nem is nem trivialis lekerdezes
        var kat = kategoriaRepository.findByMegnevezes(KategoriaEnum.TERMESZET_FOTOK.toString());

        var id2 = kepRepository.getFelhasznaloIdOrderByFrequency2(kat);
        id2.forEach(x -> System.out.println(x));

        return "testModel";
    }


    @GetMapping("/negyedik")
    public String negyedikFeladat(Model model)
    {
//         4. feladat
         kategoriaRepository.findAll().forEach(x -> System.out.println(x.getMegnevezes() + " " + x.getKepek().size()));
         model.addAttribute("kategoriak", kategoriaRepository.findAll());
         return "egyf/negyedikFeladat";
    }
    @GetMapping("/otodik")
    public String otodikFeladat(Model model)
    {

        // mukodik 5. feladat
         var id = kepRepository.getFelhasznaloIdOrderByFrequency().stream().findFirst();
         if(id.isPresent()) {
             System.out.println(felhasznaloRepository.findById(id.get()));
             model.addAttribute("felhasznalo", felhasznaloRepository.findById(id.get()).get());
         }

        return "egyf/otodikFeladat";
    }


    @GetMapping("/hetedik")
    public String hetedikFeladat(Model model)
    {

        List<Kep> legjobbKepList = new ArrayList<>();
        //todo 7. feladat ez igazabol nem is nem trivialis lekerdezes
        for (var x: KategoriaEnum.values()) {
            var kat = kategoriaRepository.findByMegnevezes(x.toString());

            var id2 = kepRepository.getFelhasznaloIdOrderByFrequency2(kat).stream().findFirst();
            if(id2.isPresent()) legjobbKepList.add(id2.get());
        }



        model.addAttribute("kepek",legjobbKepList);

        return "egyf/hetedikFeladat";
    }

}
