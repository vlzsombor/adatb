package hu.szte.fenykepalbumok.repository;

import hu.szte.fenykepalbumok.model.Ertekeles;
import hu.szte.fenykepalbumok.model.Felhasznalo;
import hu.szte.fenykepalbumok.model.Kategoria;
import hu.szte.fenykepalbumok.model.Kep;
import hu.szte.fenykepalbumok.utils.test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface KepRepository extends JpaRepository<Kep, Long> {
    /*    @Query("select fe from Kep " +
                "group by Kep.felhasznalo order by k.id")
        Felhasznalo maxKepesFelhasznalo();



        @Query("SELECT MAX(COUNT(k.felhasznalo)) FROM Kep AS k GROUP BY k.felhasznalo ORDER BY COUNT(k.id)")
        Felhasznalo countTotalCommentsByYear();
    */
    @Query("SELECT MAX(k.felhasznalo.id) FROM Kep AS k GROUP BY k.felhasznalo.id ORDER BY Count(k.id) DESC")
    List<Long> getFelhasznaloIdOrderByFrequency();


//    @Query("SELECT k.kategoria.id FROM Kep AS k GROUP BY k.kategoria.id")
//    List<Kategoria> getFelhasznaloIdOrderByFrequency2();
//


    @Query("Select k from Kep As k where k.kategoria = :kategoria order by k.ertekeles.ertekeles")
    List<Kep> getFelhasznaloIdOrderByFrequency2(@Param("kategoria")Kategoria kategoria);
}


