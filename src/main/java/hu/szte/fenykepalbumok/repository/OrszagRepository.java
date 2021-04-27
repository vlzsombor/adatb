package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Megye;
import hu.szte.fenykepalbumok.model.Orszag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrszagRepository extends JpaRepository<Orszag, Long> {

    public Orszag findOrszagByMegnevezes(String megnevezes);
}
