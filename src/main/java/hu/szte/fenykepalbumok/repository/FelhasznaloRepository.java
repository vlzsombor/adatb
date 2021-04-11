package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FelhasznaloRepository extends JpaRepository<Felhasznalo, Long> {
    Felhasznalo findByEmail(String email);

}
