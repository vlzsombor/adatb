package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.*;
import hu.szte.fenykepalbumok.repository.*;
import hu.szte.fenykepalbumok.utils.FileUploadUtil;
import hu.szte.fenykepalbumok.utils.KategoriaEnum;
import hu.szte.fenykepalbumok.utils.URLPATH;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class KepController {

    @Autowired
    private KepRepository kepRepository;

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;

    @Autowired
    private KategoriaRepository kategoriaRepository;

    @Autowired
    private VarosRepository varosRepository;

    @Autowired
    private MegyeRepository megyeRepository;

    @Autowired
    private OrszagRepository orszagRepository;

    @Autowired
    private HozzaszolasRepository hozzaszolasRepository;
    @Autowired
    private ErtekelesRepository ertekelesRepository;

    @GetMapping("update/{id}")
    public String update(Model model,
                         @PathVariable("id") Long id) {

        if (!(SecurityContextHolder.getContext().getAuthentication().getName()
                .equals(kepRepository.findById(id).get().getFelhasznalo().getEmail()))) {
            return "index";
        }

        var kep = kepRepository.findById(id).get();
        model.addAttribute("kep", kep);
        var kategoriank = kategoriaRepository.findAll();
        model.addAttribute("kategoriank", kategoriank);
        var varos = varosRepository.findAll();
        model.addAttribute("varosunk", varos);
        return "kep/update";
    }


    @GetMapping(value = "/delete/{id}")
    public String deletePost(@PathVariable Long id) {

        String auth = SecurityContextHolder.getContext().getAuthentication().getName();
        if (auth.equals(kepRepository.findById(id).get().getFelhasznalo().getEmail())) {
            kepRepository.deleteById(id);
        }


        return "redirect:/";
    }

    @PostMapping("update/{id}")
    public String updateKep(@ModelAttribute("kep") @Valid Kep kep, BindingResult result, @PathVariable("id") Long id) throws IOException {

        Kep saveKep = kepRepository.findById(id).get();

        System.out.println(kep + "kep id ");
        if (result.hasErrors()) {
            return "redirect:update/"+id;
        }

        saveKep.setKategoria(kategoriaRepository.findByMegnevezes(KategoriaEnum.TERMESZET_FOTOK.toString()));


        Varos varos = lakcimbeallitas(kep.getVaros().getMegnevezes(), kep.getVaros().getMegye().getMegnevezes(), kep.getVaros().getMegye().getOrszag().getMegnevezes());


        saveKep.setVaros(varos);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        saveKep.setLeiras(kep.getLeiras());

//        kep.setFileName();


        kepRepository.save(saveKep);
        return "redirect:/";
    }


    @GetMapping("upload")
    public String upload(Model model, Kep kep) {
        model.addAttribute("kep", kep);
        var kategoriank = kategoriaRepository.findAll();
        model.addAttribute("kategoriank", kategoriank);
        var varos = varosRepository.findAll();
        model.addAttribute("varosunk", varos);
        return "kep/upload";
    }


    @PostMapping("uploadKepek")
    public String uploadkepek(@ModelAttribute("kep") @Valid Kep kep, BindingResult result, @RequestParam("image") MultipartFile[] multipartFile) throws IOException {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        kep.setLeiras("setLeiras");


        kep.setKategoria(kategoriaRepository.findByMegnevezes(KategoriaEnum.TERMESZET_FOTOK.toString()));


        Felhasznalo f = felhasznaloRepository.findByEmail(currentPrincipalName);


        kep.setFelhasznalo(f);

        kepRepository.save(kep);
        String uploadDir = URLPATH.KEP_RELATIVE_PATH + kep.getId();
        kep.setPaths(uploadDir);
        savePhotoArray(multipartFile, uploadDir, kep);


        kepRepository.save(kep);
        return "redirect:/";
    }

    @PostMapping("upload")
    public String uploadKep(@ModelAttribute("kep") @Valid Kep kep, BindingResult result, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (result.hasErrors()) {
            return "redirect:/upload";
        }

        if (multipartFile.getOriginalFilename() == null || multipartFile.getOriginalFilename().equals("")) {
            return "redirect:/upload";
        }

        Varos varos = lakcimbeallitas(kep.getVaros().getMegnevezes(), kep.getVaros().getMegye().getMegnevezes(), kep.getVaros().getMegye().getOrszag().getMegnevezes());


        kep.setVaros(varos);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        kep.setLeiras(kep.getLeiras());

        kep.setFileName(multipartFile.getOriginalFilename());


        Felhasznalo f = felhasznaloRepository.findByEmail(currentPrincipalName);


        kep.setFelhasznalo(f);

        kepRepository.save(kep);
        String uploadDir = URLPATH.KEP_RELATIVE_PATH + kep.getId();
        kep.setPaths(uploadDir);
        savePhoto(multipartFile, uploadDir, kep);

        kepRepository.save(kep);
        return "redirect:/";
    }

    public Varos lakcimbeallitas(String varosNev, String megyeNev, String orszagNev) {

        Orszag orszag = orszagRepository.findOrszagByMegnevezes(orszagNev);

        if (orszag == null) {
            orszag = new Orszag();
            orszag.setMegnevezes(orszagNev);
            Megye megye = new Megye();
            megye.setMegnevezes(megyeNev);
            megye.setOrszag(orszag);
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye);
            varosRepository.save(varos);
            return varos;
        }

        if (!orszag.getMegyek().stream().anyMatch(n -> n.getMegnevezes().equals(megyeNev))) {
            Megye megye = new Megye();
            megye.setMegnevezes(megyeNev);
            megye.setOrszag(orszag);
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye);

            varosRepository.save(varos);
            return varos;

        }
        var megye = orszag.getMegyek().stream().filter(n -> n.getMegnevezes().equals(megyeNev)).findFirst();

        if (!megye.get().getVarosok().stream().anyMatch(n -> n.getMegnevezes().equals(varosNev))) {
            Varos varos = new Varos();
            varos.setMegnevezes(varosNev);
            varos.setMegye(megye.get());

            varosRepository.save(varos);

            return varos;
        }
        var varos = megye.get().getVarosok().stream().filter(n -> n.getMegnevezes().equals(varosNev)).findFirst().get();

        return varos;
    }

    private void savePhotoArray(MultipartFile[] multipartFile, String uploadDir, Kep realEstate) throws IOException {
        List<String> fileNames = new ArrayList<>();

        if (Files.exists(Paths.get(uploadDir))) {
            FileUtils.cleanDirectory(new File(uploadDir));
        }
        for (var x : multipartFile) {
            String fileName = StringUtils.cleanPath(x.getOriginalFilename());

            fileNames.add(fileName);
            FileUploadUtil.saveFile(uploadDir, fileName, x);
        }
        System.out.println(realEstate.getPhotosImagePath());
    }

    private void savePhoto(MultipartFile multipartFile, String uploadDir, Kep realEstate) throws IOException {
        List<String> fileNames = new ArrayList<>();

        if (Files.exists(Paths.get(uploadDir))) {
            FileUtils.cleanDirectory(new File(uploadDir));
        }
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        fileNames.add(fileName);
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        System.out.println(realEstate.getPhotosImagePath());
    }

    @RequestMapping(value = "kep/{id}", produces = "image/jpeg")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "id") Long id) throws IOException {
        var imageName = kepRepository.findById(id).get().getFileName();


        File resourcesDirectory = new File(URLPATH.KEP_RELATIVE_PATH + id + "/" + imageName);

        File serverFile = resourcesDirectory;
        System.out.println(serverFile);
        return Files.readAllBytes(serverFile.toPath());
    }

    @GetMapping("/bejegyzes/{id}")
    public String bejegyzes(Model model, @PathVariable("id") Long id, @RequestParam(name = "ertekeles") Optional<Integer> ertekeles) {
        var bejegyzes = kepRepository.findById(id).get();

        Velemeny velemeny = new Velemeny();
        Collections.reverse(bejegyzes.getForumHozzaszolasList());
        velemeny.setKep(bejegyzes);
        model.addAttribute("bejegyzes", bejegyzes);
        model.addAttribute("hozzaszolas", velemeny);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        Ertekeles ertekeles1 = new Ertekeles();
        ertekeles1.setErtekeles(ertekeles.orElse(0));

        Felhasznalo felhasznalo = felhasznaloRepository.findByEmail(currentPrincipalName);

        ertekeles1.setKep(bejegyzes);
        ertekeles1.setFelhasznalo(felhasznalo);


        if (ertekeles.isPresent()) {

//            var a = bejegyzes.getErtekelesek().stream().filter(n->n.getFelhasznalo().getEmail().equals(currentPrincipalName)).findFirst();
            var a = ertekelesRepository.lekerdezes(felhasznalo.getId(), bejegyzes.getId());

            if (a.isPresent()) {
                a.get().setErtekeles(ertekeles.get());
                ertekeles1 = a.get();
            }

            ertekelesRepository.save(ertekeles1);
        }
        model.addAttribute("ertekeles", bejegyzes.getErtekelesek().stream().filter(n -> n.getFelhasznalo().getEmail().equals(currentPrincipalName)).findFirst().orElse(ertekeles1).getErtekeles());

        return "bejegyzes/index";
    }

    @PostMapping("/bejegyzes/{id}")
    public String bejegyzes(@ModelAttribute("hozzaszolas") @Valid Velemeny velemeny, BindingResult result,
                            @PathVariable("id") Long id,
                            @RequestParam(name = "hozzaszolasid") Optional<String> hozzaszolasid,
                            @RequestParam(name = "hozzaszolasfelhasznalo") Optional<String> hozzaszolasfelhasznalo

    ) {


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = auth.getName();

        var felhasznalo = felhasznaloRepository.findByEmail(currentPrincipalName);

        velemeny.setFelhasznalo(felhasznalo);

        System.out.println("\t\t\t\t\t hozzaszolasid " + hozzaszolasid);

        if (hozzaszolasid.isPresent()) {
            var parentHozzaszolas = hozzaszolasRepository.findById(Long.parseLong(hozzaszolasid.get()));
            velemeny.setForumHozzaszolasParent(parentHozzaszolas.get());

        }
        var kep = kepRepository.findById(id).get();
        velemeny.setKep(kep);

        System.out.println("\t\t\t\t alma" + velemeny);

        System.out.println("\n\n\n\n\n\n\n");

        hozzaszolasRepository.save(velemeny);

        return "redirect:/bejegyzes/" + id;
    }

    @GetMapping("{id}")
    public String index(@PathVariable("id") Long id, Model model) {
        Kep kep = kepRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid real estate id:" + id));


        model.addAttribute("realEstate", kep);
        model.addAttribute("realEstatePhotos", null);


        try {
            if (kep.getPaths() != null) {
                System.out.println(FileUploadUtil.getAllImages(new File(kep.getPaths())));
                model.addAttribute("realEstatePhotos", FileUploadUtil.getAllImages(new File(kep.getPaths())));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "kep/index";
    }
}
