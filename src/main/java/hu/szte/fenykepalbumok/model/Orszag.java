package hu.szte.fenykepalbumok.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"megnevezes"})})
@Entity
public class Orszag {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @OneToMany(mappedBy = "orszag")
    private List<Megye> megyek;

    public List<Megye> getMegyek() {
        return megyek;
    }

    public void setMegyek(List<Megye> megyek) {
        this.megyek = megyek;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    private String megnevezes;


}
