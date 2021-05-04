package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Ertekeles;
import hu.szte.fenykepalbumok.model.Orszag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ErtekelesRepository extends JpaRepository<Ertekeles, Long> {

    @Query("select e from Ertekeles e where e.felhasznalo.id = :id and e.kep.id = :kepId")
    public Optional<Ertekeles> lekerdezes(Long id, Long kepId);
}
