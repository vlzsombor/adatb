package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface KepRepository extends JpaRepository<Kep, Long> {
}
