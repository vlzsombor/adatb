package hu.szte.fenykepalbumok.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class megtekintettkepek {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long kepid;

    private Long felhasznaloid;

    public megtekintettkepek() {

    }

    public megtekintettkepek(Long kepid, Long felhasznaloid) {
        this.kepid = kepid;
        this.felhasznaloid = felhasznaloid;
    }
}