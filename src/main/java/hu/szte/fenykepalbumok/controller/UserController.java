package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.User;
import hu.szte.fenykepalbumok.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String showUserList(Model model) {
//        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/testModel")
    public String testModel(Model model) {
        User user = new User();
        user.setEmail("fasz@fasz.hu");
        user.setName("Fasz Joska");

        userRepository.save(user);

        System.out.println(userRepository.findAll());
        model.addAttribute("users",userRepository.findAll());
        return "testModel";
    }

    @GetMapping("/geri")
    public String gyeregeribazdmeg(Model model){
        return "geri";
    }

    // additional CRUD methods
}