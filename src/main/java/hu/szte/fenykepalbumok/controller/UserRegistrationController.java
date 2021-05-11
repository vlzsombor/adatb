package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.dto.UserRegistrationDto;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Megye;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.model.Varos;
import hu.szte.fenykepalbumok.repository.MegyeRepository;
import hu.szte.fenykepalbumok.repository.OrszagRepository;
import hu.szte.fenykepalbumok.repository.VarosRepository;
import hu.szte.fenykepalbumok.service.FelhasznaloService;
import hu.szte.fenykepalbumok.utils.RoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    @Autowired
    private FelhasznaloService felhasznaloService;

    @Autowired
    private VarosRepository varosRepository;

    @Autowired
    private MegyeRepository megyeRepository;

    @Autowired
    private OrszagRepository orszagRepository;

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {

        Felhasznalo existing = felhasznaloService.findByEmail(userDto.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }


        if (result.hasErrors()) {
            return "registration";
        }


        felhasznaloService.save(userDto);
        return "redirect:/login?success";
    }


}
