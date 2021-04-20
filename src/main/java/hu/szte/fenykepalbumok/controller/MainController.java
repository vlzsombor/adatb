package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Ertekeles;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.utils.FileUploadUtil;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
import hu.szte.fenykepalbumok.utils.URLPATH;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {



    @GetMapping("/")
    public String root() {
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



}