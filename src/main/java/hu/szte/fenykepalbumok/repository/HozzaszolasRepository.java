package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.ForumHozzaszolas;
import hu.szte.fenykepalbumok.model.Varos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HozzaszolasRepository extends JpaRepository<ForumHozzaszolas, Long> {

}
