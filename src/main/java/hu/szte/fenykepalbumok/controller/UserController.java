package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.User;
import hu.szte.fenykepalbumok.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    // additional CRUD methods
}