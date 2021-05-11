package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Megye;
import hu.szte.fenykepalbumok.model.Orszag;
import hu.szte.fenykepalbumok.model.Varos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MegyeRepository extends JpaRepository<Megye, Long> {

    Megye findMegyeByMegnevezes(String megnevezes);

}
