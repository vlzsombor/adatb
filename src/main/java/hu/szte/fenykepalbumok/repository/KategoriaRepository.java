package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Kep;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface KategoriaRepository  extends JpaRepository<Kategoria, Long> {

    Kategoria findByMegnevezes(String megnevezes);


}
