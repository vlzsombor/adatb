package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Felhasznalo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FelhasznaloRepository extends JpaRepository<Felhasznalo, Long> {
    Felhasznalo findByEmail(String email);


    @Query("select max(t.kepek) from Felhasznalo as t")
    Felhasznalo countByKepek();

}
