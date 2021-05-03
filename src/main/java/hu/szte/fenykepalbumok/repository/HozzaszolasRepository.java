package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Velemeny;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HozzaszolasRepository extends JpaRepository<Velemeny, Long> {

}
