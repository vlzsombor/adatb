package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NemTrivialis {
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;
    @Autowired
    private KepRepository kepRepository;

    @Autowired
    private VarosRepository varosRepository;

    @Autowired
    private OrszagRepository orszagRepository;

//    @GetMapping("/egyfostest")
//    public String testModel(Model model) {
//        //todo 7. feladat ez igazabol nem is nem trivialis lekerdezes
//        var kat = kategoriaRepository.findByMegnevezes(KategoriaEnum.TERMESZET_FOTOK.toString());
//
//        var id2 = kepRepository.getFelhasznaloIdOrderByFrequency2(kat);
//        id2.forEach(x -> System.out.println(x));
//
//        return "testModel";
//    }

    @GetMapping("/nemTrivialis")
    public String nemTrivialis(Model model) {

        return "nemTrivialis/index";
    }

    @GetMapping("/nemTrivialis1")
    public String negyedikFeladat(Model model) {
//         4. feladat
        model.addAttribute("kategoriak", kategoriaRepository.findAll());
        return "nemTrivialis/egyf/negyedikFeladat";
    }

    @GetMapping("/nemTrivialis2")
    public String otodikFeladat(Model model) {

        // mukodik 5. feladat
        var id = kepRepository.getFelhasznaloIdOrderByFrequency().stream().findFirst();
        if (id.isPresent()) {
            System.out.println(felhasznaloRepository.findById(id.get()));
            model.addAttribute("felhasznalo", felhasznaloRepository.findById(id.get()).get());
        }

        return "nemTrivialis/egyf/otodikFeladat";
    }

    @GetMapping("/varosokarcai")
    public String varosokarcai(@RequestParam(name = "varos", required = false) String varos, Model model) {
        model.addAttribute("varosok", varosRepository.findAll());

        System.out.println(varos);

        if (varos != null) {
            model.addAttribute("kepek", kepRepository.getKepByVaros(varosRepository.findVarosByMegnevezes(varos)));
        }


        return "nemTrivialis/ketf/varosokarcai";


    }

    @GetMapping("/nemTrivialis4")
    public String legnepszerubb(Model model) {
        var b = varosRepository.legnepszerubb();

        model.addAttribute("cimek", b);

        return "nemTrivialis/ketf/legnepszerubb";
    }

    @GetMapping("/nemTrivialis5")
    public String orszagGroupByFelhasznalo(Model model) {
        var b = varosRepository.legnepszerubb();

        model.addAttribute("orszagFelhasznalo", orszagRepository.felhasznaloOrszagGroupBy());

        System.out.println("mi a lofasz" + orszagRepository.felhasznaloOrszagGroupBy());

        return "nemTrivialis/orszagGroupByFelhasznalo";
    }
    @GetMapping("/nemTrivialis6")
    public String varosGroupByFelhasznalo(Model model) {
        var b = varosRepository.legnepszerubb();

        model.addAttribute("orszagFelhasznalo", orszagRepository.felhasznaloMegyeGroupBy());


        return "nemTrivialis/megyeGroupByFelhasznalo";
    }

    @GetMapping("/nemTrivialis7")
    public String kategorianekntHanyKep(Model model) {
        var b = varosRepository.legnepszerubb();

        model.addAttribute("orszagFelhasznalo", kategoriaRepository.kategoriankentHanyKep());


        return "nemTrivialis/nemTrivialis7";
    }
//    @GetMapping("/hetedik")
//    public String hetedikFeladat(Model model)
//    {
//
//        List<Kep> legjobbKepList = new ArrayList<>();
//        //todo 7. feladat ez igazabol nem is nem trivialis lekerdezes
//        for (var x: KategoriaEnum.values()) {
//            var kat = kategoriaRepository.findByMegnevezes(x.toString());
//
//            var id2 = kepRepository.getFelhasznaloIdOrderByFrequency2(kat).stream().findFirst();
//            if(id2.isPresent()) legjobbKepList.add(id2.get());
//        }
//
//
//
//        model.addAttribute("kepek",legjobbKepList);
//
//        return "egyf/hetedikFeladat";
//    }

}
