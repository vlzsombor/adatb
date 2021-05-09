package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.*;
import hu.szte.fenykepalbumok.repository.*;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
import hu.szte.fenykepalbumok.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/felhasznalo")
public class UserController {


    // @Autowired
    // private FelhasznaloRepository2 felhasznaloRepository2;
    //
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private KategoriaRepository kategoriaRepository;

    @Autowired
    private OrszagRepository orszagRepository;

    @Autowired
    private VarosRepository varosRepository;


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

        return "testModel";
    }


    @GetMapping("/delete/{id}")
    public String deleteFelhasznalo(@PathVariable Long id) {

        var auths = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        felhasznaloRepository.deleteById(id);

        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateFelhasznalo(Model model, @PathVariable Long id) {
        model.addAttribute("felhasznalo", felhasznaloRepository.findById(id).orElse(new Felhasznalo()));

        return "user/update";
    }


    @PostMapping("update/{id}")
    public String updateKep(@ModelAttribute("felhasznalo") @Valid Felhasznalo felhasznalo, BindingResult result, @PathVariable("id") Long id) throws IOException {

        Felhasznalo saveFelhasznalo = felhasznaloRepository.findById(id).get();

        System.out.println(felhasznalo + "kep id ");
        if (result.hasErrors()) {
            return "redirect:update/"+id;
        }


        saveFelhasznalo.setFelhasznaloNev(felhasznalo.getFelhasznaloNev());


        saveFelhasznalo.setKeresztNev(felhasznalo.getKeresztNev());

        saveFelhasznalo.setVezetekNev(felhasznalo.getVezetekNev());
        saveFelhasznalo.setTelefonszam(felhasznalo.getTelefonszam());
        saveFelhasznalo.setJogosultsag(felhasznalo.getJogosultsag());


        if(felhasznalo.getVaros() != null && felhasznalo.getVaros().getMegye() != null && felhasznalo.getVaros().getMegye().getOrszag() != null) {
            Varos varos = lakcimbeallitas(felhasznalo.getVaros().getMegnevezes(), felhasznalo.getVaros().getMegye().getMegnevezes(), felhasznalo.getVaros().getMegye().getOrszag().getMegnevezes());
            saveFelhasznalo.setVaros(varos);
        }
        if(felhasznalo.getEmail() != null){
            saveFelhasznalo.setEmail(felhasznalo.getEmail());
        }

        felhasznaloRepository.save(saveFelhasznalo);
        return "redirect:/";
    }
    public Varos lakcimbeallitas(String varosNev, String megyeNev, String orszagNev) {

        Orszag orszag = orszagRepository.findOrszagByMegnevezes(orszagNev);

        if (orszag == null) {
            orszag = new Orszag();
            orszag.setMegnevezes(orszagNev);
            Megye megye = new Megye();
            megye.setMegnevezes(megyeNev);
            megye.setOrszag(orszag);
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye);
            varosRepository.save(varos);
            return varos;
        }

        if (!orszag.getMegyek().stream().anyMatch(n -> n.getMegnevezes().equals(megyeNev))) {
            Megye megye = new Megye();
            megye.setMegnevezes(megyeNev);
            megye.setOrszag(orszag);
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye);

            varosRepository.save(varos);
            return varos;

        }
        var megye = orszag.getMegyek().stream().filter(n -> n.getMegnevezes().equals(megyeNev)).findFirst();

        if (!megye.get().getVarosok().stream().anyMatch(n -> n.getMegnevezes().equals(varosNev))) {
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye.get());

            varosRepository.save(varos);

            return varos;
        }
        var varos = megye.get().getVarosok().stream().filter(n -> n.getMegnevezes().equals(varosNev)).findFirst().get();

        return varos;
    }
}