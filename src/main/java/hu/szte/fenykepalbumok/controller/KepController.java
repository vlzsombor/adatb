package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Ertekeles;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.utils.FileUploadUtil;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
import hu.szte.fenykepalbumok.utils.URLPATH;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KepController {

    @Autowired
    private KepRepository kepRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    private KategoriaRepository kategoriaRepository;


    @GetMapping("upload")
    public String upload(Model model,Kep realEstate) {
        model.addAttribute("kep",realEstate);
        return "kep/upload";
    }



    @PostMapping("uploadKepek")
    public String uploadkepek(@ModelAttribute("kep") @Valid Kep kep, BindingResult result, @RequestParam("image") MultipartFile[] multipartFile) throws IOException {




        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        kep.setLeiras("setLeiras");


        kep.setKategoria(kategoriaRepository.findByMegnevezes(KategoriaEnum.TERMESZET_FOTOK.toString()));


        Felhasznalo f = felhasznaloRepository.findByEmail(currentPrincipalName);

        kep.setErtekeles(new Ertekeles(16));

        kep.setFelhasznalo(f);

        kepRepository.save(kep);
        String uploadDir = URLPATH.KEP_RELATIVE_PATH + kep.getId();
        kep.setPaths(uploadDir);
        savePhotoArray(multipartFile,uploadDir,kep);


        kepRepository.save(kep);
        return "redirect:/";
    }

    @PostMapping("upload")
    public String uploadKep(@ModelAttribute("kep") @Valid Kep kep, BindingResult result, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()){
            return "kep/upload";
        }

        if(multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().equals("")){
            return "kep/upload";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        kep.setLeiras("setLeiras");

        kep.setFileName(multipartFile.getOriginalFilename());


        Felhasznalo f = felhasznaloRepository.findByEmail(currentPrincipalName);

        kep.setErtekeles(new Ertekeles(18));

        kep.setFelhasznalo(f);

        kepRepository.save(kep);
        String uploadDir = URLPATH.KEP_RELATIVE_PATH + kep.getId();
        kep.setPaths(uploadDir);
        savePhoto(multipartFile,uploadDir,kep);

        kepRepository.save(kep);
        return "redirect:/";
    }

    private void savePhotoArray(MultipartFile[] multipartFile, String uploadDir, Kep realEstate) throws IOException {
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
    private void savePhoto(MultipartFile multipartFile, String uploadDir, Kep realEstate) throws IOException {
        List<String> fileNames = new ArrayList<>();

        if(Files.exists(Paths.get(uploadDir))) {
            FileUtils.cleanDirectory(new File(uploadDir));
        }
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            fileNames.add(fileName);
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

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
    @GetMapping("/bejegyzes/{id}")
    public String bejegyzes(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("kepid", id);
        return "bejegyzes/index";
    }
    @GetMapping("{id}")
    public String index(@PathVariable("id") Long id, Model model) {
        Kep kep = kepRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid real estate id:" + id));


        model.addAttribute("realEstate", kep);
        model.addAttribute("realEstatePhotos",null);


        try {
            if(kep.getPaths() != null){
                System.out.println(FileUploadUtil.getAllImages(new File(kep.getPaths())));
                model.addAttribute("realEstatePhotos", FileUploadUtil.getAllImages(new File(kep.getPaths())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "kep/index";
    }
}
