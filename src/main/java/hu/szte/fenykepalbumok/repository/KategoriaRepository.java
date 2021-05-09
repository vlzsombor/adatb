package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Kep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface KategoriaRepository extends JpaRepository<Kategoria, Long> {

    Kategoria findByMegnevezes(String megnevezes);

    @Query(value = "SELECT kategoria.megnevezes, count(*) FROM kategoria, kep WHERE kep.kategoriaid = kategoria.Id GROUP BY kategoria.megnevezes", nativeQuery = true)
    List<String> kategoriankentHanyKep();

}
