package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.model.Report;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private KepRepository kepRepository;


    @GetMapping("admin/report")
    public String admin(Model model) {

        model.addAttribute("reportok", reportRepository.findAll());

        return "admin/report";
    }

    @GetMapping("/admin/delete/report/{id}")
    public String admin(@PathVariable("id") Long id) {

        reportRepository.deleteById(id);

        return "redirect:/admin";
    }


    @GetMapping("/report/{id}")
    public String showUserList(Model model, @PathVariable("id") Long id,
                               @RequestParam(name = "select") Optional<String> select,
                               @RequestParam(name = "textarea") Optional<String> textarea
    ) {

        Optional<Kep> kep = kepRepository.findById(id);

        if (select.isPresent() && textarea.isPresent() && kep.isPresent()) {


            String auth = SecurityContextHolder.getContext().getAuthentication().getName();

            Felhasznalo felhasznalo = felhasznaloRepository.findByEmail(auth);

            Report report = new Report();
            report.setFelhasznalo(felhasznalo);
            report.setKep(kep.get());
            report.setIndok(textarea.get());
            report.setOk(select.get());

            reportRepository.save(report);
        }

        return "redirect:/";
    }
}
