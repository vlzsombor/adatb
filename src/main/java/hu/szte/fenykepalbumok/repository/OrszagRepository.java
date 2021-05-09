package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Megye;
import hu.szte.fenykepalbumok.model.Orszag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrszagRepository extends JpaRepository<Orszag, Long> {

    public Orszag findOrszagByMegnevezes(String megnevezes);


    @Query(nativeQuery = true, value = "select orszag.MEGNEVEZES, count(*) from felhasznalo, orszag, megye, varos\n" +
            "where felhasznalo.VAROS_ID = varos.ID and\n" +
            "        varos.megyeid = megye.id and\n" +
            "        megye.orszagid = orszag.id group by orszag.megnevezes")
    public List<String> felhasznaloOrszagGroupBy();


    @Query(nativeQuery = true, value = "select megye.megnevezes, count(*) from felhasznalo, orszag, megye, varos\n" +
            "where felhasznalo.VAROS_ID = varos.id and varos.megyeid = megye.id and megye.orszagid = orszag.id group by megye.megnevezes")
    public List<String> felhasznaloMegyeGroupBy();


    @Query(nativeQuery = true, value = "SELECT varos.megnevezes, count(*) as darab FROM varos, kep WHERE kep.VAROS_ID = varos.Id GROUP BY varos.megnevezes")
    public List<String> felhasznaloVarosGroupBy();


}
