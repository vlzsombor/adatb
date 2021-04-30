package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.model.Varos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VarosRepository extends JpaRepository<Varos, Long> {

    public Varos findVarosByMegnevezes(String megnevezes);

    @Query("SELECT MAX(k.felhasznalo.id) FROM Kep AS k GROUP BY k.felhasznalo.id ORDER BY Count(k.id) DESC")
    List<Long> getFelhasznaloIdOrderByFrequency();
}
