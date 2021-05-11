package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.repository.VarosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KetFosController {

    @Autowired
    KepRepository kepRepository;

    @Autowired
    VarosRepository varosRepository;


    @GetMapping("test")
    public String upload(Model model, Kep realEstate) {

        var a = kepRepository.telepulesenkentHanyFenyep();


        return "index2";
    }

    @GetMapping("varosokarcai")
    public String varosokarcai(@RequestParam(name = "varos", required = false) String varos, Model model) {
        model.addAttribute("varosok", varosRepository.findAll());

        System.out.println(varos);

        if (varos != null) {
            model.addAttribute("kepek", kepRepository.getKepByVaros(varosRepository.findVarosByMegnevezes(varos)));
        }


        return "ketf/varosokarcai";


    }


    @GetMapping("legnepszerubb")
    public String legnepszerubb(Model model) {
        var b = varosRepository.legnepszerubb();

        model.addAttribute("cimek", b);

        return "ketf/legnepszerubb";
    }
}
