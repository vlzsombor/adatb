package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.utils.FileUploadUtil;
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

    @Autowired
    private KepRepository kepRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

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

    @GetMapping("{id}")
    public String index(@PathVariable("id") Long id, Model model) {
        Kep realEstate = kepRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid real estate id:" + id));

        model.addAttribute("realEstate", realEstate);
        model.addAttribute("realEstatePhotos",null);

        try {
            if(realEstate.getPaths() != null){
                System.out.println(FileUploadUtil.getAllImages(new File(realEstate.getPaths())));
                model.addAttribute("realEstatePhotos", FileUploadUtil.getAllImages(new File(realEstate.getPaths())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "kep/index";
    }
    @GetMapping("upload")
    public String upload(Model model,Kep realEstate) {
        model.addAttribute("kep",realEstate);
        return "kep/upload";
    }
    @PostMapping("upload")
    public String addRealEstatePost(@ModelAttribute("kep") @Valid Kep kep, BindingResult result, @RequestParam("image") MultipartFile[] multipartFile) throws IOException {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        kep.setLeiras("setLeiras");


        Felhasznalo f = felhasznaloRepository.findByEmail(currentPrincipalName);


        kep.setFelhasznalo(f);

        kepRepository.save(kep);
        String uploadDir = URLPATH.KEP_RELATIVE_PATH + kep.getId();
        kep.setPaths(uploadDir);
        savePhoto(multipartFile,uploadDir,kep);


        kepRepository.save(kep);
        return "redirect:/testModel";
    }

    private void savePhoto(MultipartFile[] multipartFile, String uploadDir,Kep realEstate) throws IOException {
        List<String> fileNames = new ArrayList<>();

        if(Files.exists(Paths.get(uploadDir))) {
            FileUtils.cleanDirectory(new File(uploadDir));
        }
        for (var x: multipartFile) {
            String fileName = StringUtils.cleanPath(x.getOriginalFilename());

            fileNames.add(fileName);
            FileUploadUtil.saveFile(uploadDir, fileName, x);
        }
        System.out.println(realEstate.getPhotosImagePath());
    }


    @RequestMapping(value = "kep/{id}/{imageName}",produces = "image/jpeg")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "id") String id,@PathVariable(value = "imageName") String imageName) throws IOException {


        File resourcesDirectory = new File(URLPATH.KEP_RELATIVE_PATH + id + "/" + imageName);

        File serverFile = resourcesDirectory;
        System.out.println(serverFile);
        return Files.readAllBytes(serverFile.toPath());
    }

}