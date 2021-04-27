package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.service.KepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {


    @GetMapping("/")
    public String root(Model model,
                       @RequestParam("page") Optional<Integer> page,
                       @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Kep> kepek = kepService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("kepek", kepek);

        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        var felhasznalo=felhasznaloRepository.findByEmail(authentication);
        felhasznalo.getJogosultsag();
        model.addAttribute("ezafelhasznalo", felhasznalo);


        int totalPages = kepek.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/admin/test")
    public String adminIndex() {
        return "index";
    }


    @Autowired
    private KepService kepService;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @GetMapping("/list/kep")
    public String listBooks(
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Kep> kepek = kepService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("kepek", kepek);


        int totalPages = kepek.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "index";
    }

}