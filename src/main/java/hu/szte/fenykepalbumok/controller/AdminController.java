package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

    @GetMapping("/admin")
    public String root(Model model) {

        return "/admin/index";

    }

}