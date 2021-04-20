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


}
