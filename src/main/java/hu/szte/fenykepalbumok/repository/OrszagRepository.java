package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Megye;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.utils.queryclass.HaromAttributum;
import hu.szte.fenykepalbumok.utils.queryclass.OrszagDarab;
import hu.szte.fenykepalbumok.utils.queryclass.TelepulesHanyFenykep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrszagRepository extends JpaRepository<Orszag, Long> {

    public Orszag findOrszagByMegnevezes(String megnevezes);

//    @Query(nativeQuery = true, value = "select orszag.megnevezes, count(felhasznalo.id) AS darab from felhasznalo, orszag, megye, varos\n" +
//            "where felhasznalo.varos_id = varos.id and varos.megyeid = megye.id and megye.orszagid = orszag.id group by orszag.megnevezes")
//    List<String> elso_lekerdezes();

    @Query("SELECT new hu.szte.fenykepalbumok.utils.queryclass.OrszagDarab(o.megnevezes, count(f.id)) FROM Felhasznalo As f,Varos AS v ,Megye AS m, Orszag AS o where f.varos.id = v.id and v.megye.id = m.id and m.orszag.id= o.id group by o.megnevezes")
    List<OrszagDarab> elso_lekerdezes();

    // SELECT kategoria.megnevezes, count(*) FROM kategoria, kep WHERE kep.kategoriaid = kategoria.Id GROUP BY kategoria.megnevezes;
    @Query("SELECT new hu.szte.fenykepalbumok.utils.queryclass.OrszagDarab(ka.megnevezes, count(ke.id)) FROM Kep As ke, Kategoria AS ka where ke.kategoria.id=ka.id GROUP BY ka.megnevezes")
    List<OrszagDarab> masodik_lekerdezes();

    /*
  SELECT kepid, count(*) AS hozzaszolasok FROM kep, velemeny where kep.id = velemeny.kepid group by kepid;
     */
   @Query("SELECT new hu.szte.fenykepalbumok.utils.queryclass.OrszagDarab(k.id, count(v.id)) FROM Kep AS k, Velemeny AS v WHERE k.id=v.kep.id GROUP BY k.id")
    List<OrszagDarab> negyedik_lekerdezes();


}
