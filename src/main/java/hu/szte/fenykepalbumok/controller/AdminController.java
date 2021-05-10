package hu.szte.fenykepalbumok.controller;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.repository.FelhasznaloRepository;
import hu.szte.fenykepalbumok.repository.KepRepository;
import hu.szte.fenykepalbumok.repository.OrszagRepository;
import hu.szte.fenykepalbumok.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


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


    private List<Object[]> Otodik()
    {
        String nativeQuery = "SELECT felhasznalo.vezetek_nev, felhasznalo.kereszt_nev, darab \n" +
                "FROM felhasznalo, (SELECT felhasznalo.Id as userid, COUNT(*) AS darab FROM felhasznalo, kep WHERE felhasznalo.Id = kep.felhasznaloid GROUP BY felhasznalo.Id ORDER BY darab DESC) \n" +
                "WHERE felhasznalo.Id = userid\n" +
                "FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

      return list;
    }

    private List<Object[]> Hatodik()
    {
        String nativeQuery = "SELECT kategoria.megnevezes AS kategorianev, kep.Id AS kep_id, pont, kep.leiras AS kep_leiras FROM kategoria, kep,(SELECT kepid, ROUND(SUM(ertekeles)/COUNT(*), 1) AS pont FROM ertekeles GROUP BY kepid)\n" +
                "WHERE kep.kategoriaid = kategoria.Id and kep.Id = kepid ORDER BY kategoria.megnevezes, pont DESC";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }
    private List<Object[]> Hetedik()
    {
        String nativeQuery = "SELECT  domain, darab, count(*)AS feltoltesek FROM felhasznalo, kep,(SELECT SUBSTR(SUBSTR(felhasznalo.email, INSTR(felhasznalo.email, '@'), INSTR(felhasznalo.email, '.')), 2) as domain,  count(*) as darab FROM felhasznalo GROUP BY (SUBSTR(SUBSTR(felhasznalo.email, INSTR(felhasznalo.email, '@'), INSTR(felhasznalo.email, '.')), 2))ORDER BY domain) WHERE felhasznalo.id=kep.felhasznaloid AND SUBSTR(SUBSTR(felhasznalo.email, INSTR(felhasznalo.email, '@'), INSTR(felhasznalo.email, '.')), 2) = domain GROUP BY domain,darab";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }
    private List<Object[]> Nyolcadik()
    {
        String nativeQuery = "SELECT kepid, ROUND(count(*)/(SELECT AVG(count(*)) AS atlagosHozzaszolas FROM kep, velemeny where kep.id = velemeny.kepid group by kepid)*1000, 1) AS nepszerusegiFaktor FROM kep, velemeny where kep.id = velemeny.kepid group by kepid";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }
    private List<Object[]> Tizedik()
    {
        String nativeQuery = "SELECT kep.id, kep.leiras,count(*) AS reportSzam FROM report, kep WHERE report.kep_id=kep.id GROUP BY kep.id,kep.leiras ORDER BY reportSzam DESC FETCH FIRST 10 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }
    private List<Object[]> Tizenegyedik()
    {
        String nativeQuery = "SELECT felhasznalo.felhasznalo_nev, count(*) AS reportSzam FROM report, felhasznalo WHERE report.felhasznalo_id=felhasznalo.id GROUP BY felhasznalo.felhasznalo_nev ORDER BY reportSzam DESC FETCH FIRST 5 ROWS ONLY";
        Query query = em.createNativeQuery(nativeQuery);

        List<Object[]> list = query.getResultList();

        return list;
    }
    private List<Object[]> Tizenkettedik()
    {
        String nativeQuery = "SELECT kategoria.megnevezes, count(*) AS reportSzam FROM report, kategoria, kep\n" +
                "                    WHERE kep.kategoriaid=kategoria.id AND kep.id=report.kep_id\n" +
                "                    GROUP BY kategoria.megnevezes\n" +
                "                    ORDER BY reportSzam DESC";
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


        model.addAttribute("reportok", reportRepository.findAll());


        return "/admin/index";

    }

}