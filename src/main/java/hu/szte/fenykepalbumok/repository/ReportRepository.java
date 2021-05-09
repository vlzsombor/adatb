package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.model.Report;
import hu.szte.fenykepalbumok.model.Varos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {


}
