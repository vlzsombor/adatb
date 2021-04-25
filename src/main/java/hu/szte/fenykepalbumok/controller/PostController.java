package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("/post")
    public String root(Model model) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        var felhasznalo=felhasznaloRepository.findByEmail(authentication);
        felhasznalo.getJogosultsag();
        model.addAttribute("asd", felhasznalo);
        return "kepcontainer";
    }

    @Autowired
    FelhasznaloRepository felhasznaloRepository;
}
