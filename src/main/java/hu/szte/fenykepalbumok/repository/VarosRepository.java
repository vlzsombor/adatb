package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.model.Varos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VarosRepository extends JpaRepository<Varos, Long> {

    public Varos findVarosByMegnevezes(String megnevezes);


    ///Legnépszerűbb úti célok (azokból a képekből, amelyet nem
    //azok a felhasználók töltöttek fel, akik ott laknak)

    @Query("select v.megnevezes,v.megye.megnevezes,v.megye.orszag.megnevezes from Varos v, Kep k, Felhasznalo f where k.varos.id <> f.varos.id group by v.megnevezes,v.megye.megnevezes,v.megye.orszag.megnevezes order by count(k.varos.id) desc ")
    public List<String> legnepszerubb();


    @Query("SELECT MAX(k.felhasznalo.id) FROM Kep AS k GROUP BY k.felhasznalo.id ORDER BY Count(k.id) DESC")
    List<Long> getFelhasznaloIdOrderByFrequency();
}
