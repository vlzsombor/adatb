package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.model.Varos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VarosRepository extends JpaRepository<Varos, Long> {

    public Varos findVarosByMegnevezes(String megnevezes);

}
