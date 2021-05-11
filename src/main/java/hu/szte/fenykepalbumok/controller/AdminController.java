package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KategoriaRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.repository.OrszagRepository;
import hu.szte.fenykepalbumok.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@Controller
public class AdminController {

    @Autowired
    private FelhasznaloRepository felhasznaloRepository;
    @Autowired
    private OrszagRepository orszagRepository;
    @Autowired
    private KepRepository kepRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    EntityManager em;

    @Autowired
    private KategoriaRepository kategoriaRepository;

    private List<Object[]> Otodik() {
        String nativeQuery = "SELECT felhasznalo.vezetek_nev, felhasznalo.kereszt_nev, darab \n" +
                "FROM felhasznalo, (SELECT felhasznalo.Id as userid, COUNT(*) AS darab FROM felhasznalo, kep WHERE felhasznalo.Id = kep.felhasznaloid GROUP BY felhasznalo.Id ORDER BY darab DESC) \n" +
                "WHERE felhasznalo.Id = userid\n" +
                "ORDER BY darab DESC \n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Hatodik() {
        String nativeQuery = "SELECT kategoria.megnevezes AS kategorianev, kep.Id AS kep_id, pont, kep.leiras AS kep_leiras FROM kategoria, kep,(SELECT kepid, ROUND(SUM(ertekeles)/COUNT(*), 1) AS pont FROM ertekeles GROUP BY kepid)\n" +
                "WHERE kep.kategoriaid = kategoria.Id and kep.Id = kepid ORDER BY kategoria.megnevezes, pont DESC";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Hetedik() {
        String nativeQuery = "SELECT  domain, darab, count(*)AS feltoltesek FROM felhasznalo, kep,(SELECT SUBSTR(SUBSTR(felhasznalo.email, INSTR(felhasznalo.email, '@'), INSTR(felhasznalo.email, '.')), 2) as domain,  count(*) as darab FROM felhasznalo GROUP BY (SUBSTR(SUBSTR(felhasznalo.email, INSTR(felhasznalo.email, '@'), INSTR(felhasznalo.email, '.')), 2))ORDER BY domain) WHERE felhasznalo.id=kep.felhasznaloid AND SUBSTR(SUBSTR(felhasznalo.email, INSTR(felhasznalo.email, '@'), INSTR(felhasznalo.email, '.')), 2) = domain GROUP BY domain,darab";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Nyolcadik() {
        String nativeQuery = "SELECT kepid, ROUND(count(*)/(SELECT AVG(count(*)) AS atlagosHozzaszolas FROM kep, velemeny where kep.id = velemeny.kepid group by kepid)*1000, 1) AS nepszerusegiFaktor FROM kep, velemeny where kep.id = velemeny.kepid group by kepid order by kepid";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizedik() {
        String nativeQuery = "SELECT kep.id, kep.leiras,count(*) AS reportSzam FROM report, kep WHERE report.kep_id=kep.id GROUP BY kep.id,kep.leiras ORDER BY reportSzam DESC FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizenegyedik() {
        String nativeQuery = "SELECT felhasznalo.felhasznalo_nev, count(*) AS reportSzam FROM report, felhasznalo WHERE report.felhasznalo_id=felhasznalo.id GROUP BY felhasznalo.felhasznalo_nev ORDER BY reportSzam DESC FETCH FIRST 5 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizenkettedik() {
        String nativeQuery = "SELECT kategoria.megnevezes, count(*) AS reportSzam FROM report, kategoria, kep\n" +
                "                    WHERE kep.kategoriaid=kategoria.id AND kep.id=report.kep_id\n" +
                "                    GROUP BY kategoria.megnevezes\n" +
                "                    ORDER BY reportSzam DESC";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    // innentol
    private List<Object[]> Tizenharmadik() {
        String nativeQuery = "SELECT felhasznalo.Id,felhasznalo.felhasznalo_nev, COUNT(*) as darab FROM felhasznalo, megtekintettkepek \n" +
                "WHERE felhasznalo.id = megtekintettkepek.felhasznaloid GROUP BY felhasznalo.id, felhasznalo.felhasznalo_nev\n" +
                "ORDER BY darab DESC\n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizennegyedik() {
        String nativeQuery = "SELECT kep.id, COUNT(*) AS darab FROM kep, megtekintettkepek\n" +
                "WHERE kep.id = megtekintettkepek.kepid GROUP BY kep.id\n" +
                "ORDER BY darab DESC\n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizenotodik() {
        String nativeQuery = "SELECT Kategoria.megnevezes, count(*) AS darab FROM kategoria, kep, megtekintettkepek\n" +
                "WHERE kep.kategoriaid = kategoria.id AND kep.id = megtekintettkepek.kepid\n" +
                "GROUP BY Kategoria.megnevezes\n" +
                "ORDER BY darab DESC\n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizenhatodik() {
        String nativeQuery = "SELECT Varos.megnevezes, COUNT(*) AS darab FROM varos, kep, megtekintettkepek\n" +
                "WHERE varos.id = kep.varos_id AND kep.id = megtekintettkepek.kepid\n" +
                "GROUP BY  varos.megnevezes\n" +
                "ORDER BY darab DESC\n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizenhetedik() {
        String nativeQuery = "SELECT Orszag.megnevezes, COUNT(*) AS darab FROM orszag, megye, varos, kep, megtekintettkepek\n" +
                "WHERE orszag.id = megye.orszagid AND megye.id = varos.megyeid AND varos.id = kep.varos_id AND kep.id = megtekintettkepek.kepid\n" +
                "GROUP BY Orszag.megnevezes\n" +
                "ORDER BY darab DESC\n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    private List<Object[]> Tizennyolcadik() {
        String nativeQuery = "SELECT meg as kepid, ROUND((reportSzam+1)/(megtekintesek+1), 1) as veszelyfaktor\n" +
                "FROM \n" +
                "(SELECT kep.id AS meg, COUNT(*) AS megtekintesek FROM kep, megtekintettkepek\n" +
                "WHERE kep.id = megtekintettkepek.kepid GROUP BY kep.id), \n" +
                "(SELECT kep.id AS reportos, count(*) AS reportSzam FROM report, kep\n" +
                "WHERE report.kep_id=kep.id GROUP BY kep.id) \n" +
                "WHERE meg = reportos\n" +
                "ORDER BY veszelyfaktor DESC\n" +
                "FETCH FIRST 5 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }

    @GetMapping("/admin")
    public String root(Model model) {

        model.addAttribute("felhasznalok", felhasznaloRepository.findAll());
        model.addAttribute("updatefelhasznalo", new Felhasznalo());
        model.addAttribute("elsolekerdezes", orszagRepository.elso_lekerdezes());
        model.addAttribute("masodiklekerdezes", orszagRepository.masodik_lekerdezes());
        //harmadik megvalositva -> ertekeles megjelenítése
        model.addAttribute("negyediklekerdezes", orszagRepository.negyedik_lekerdezes());
        model.addAttribute("otodiklekerdezes", Otodik());
        model.addAttribute("hatodiklekerdezes", Hatodik());
        model.addAttribute("hetediklekerdezes", Hetedik());
        model.addAttribute("nyolcadiklekerdezes", Nyolcadik());
        model.addAttribute("kilencediklekerdezes", kepRepository.telepulesenkentHanyFenyep());
        model.addAttribute("tizediklekerdezes", Tizedik());
        model.addAttribute("tizenegyediklekerdezes", Tizenegyedik());
        model.addAttribute("tizenkettediklekerdezes", Tizenkettedik());

        model.addAttribute("tizenharmadiklekerdezes", Tizenharmadik());
        model.addAttribute("tizennegyediklekerdezes", Tizennegyedik());
        model.addAttribute("tizenotodiklekerdezes", Tizenotodik());
        model.addAttribute("tizenhatodiklekerdezes", Tizenhatodik());
        model.addAttribute("tizenhetediklekerdezes", Tizenhetedik());
        model.addAttribute("tizennyolcadiklekerdezes", Tizennyolcadik());


        model.addAttribute("reportok", reportRepository.findAll());


        return "/admin/index";

    }


    @GetMapping("/admin/kategoriahozzaadas")
    public String kategoriahozzaadas(@RequestParam(name = "text") Optional<String> text,
                                     @RequestParam(name = "textarea") Optional<String> textarea) {

        boolean ifExist = kategoriaRepository.findAll().stream().anyMatch(n -> n.getMegnevezes().equals(text));

        if (!ifExist) {
            if (text.isPresent() && textarea.isPresent()) {

                Kategoria kategoria = new Kategoria();
                kategoria.setMegnevezes(textarea.get());
                kategoria.setLeiras(text.get());
                kategoriaRepository.save(kategoria);
            }
        }


        System.out.println("alma");

        return "redirect:/admin";
    }
}