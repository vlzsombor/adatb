package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Ertekeles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MegtekintettKepekRepository extends JpaRepository<hu.szte.fenykepalbumok.model.megtekintettkepek, Long> {
}
