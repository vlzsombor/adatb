package hu.szte.fenykepalbumok.model;

import hu.szte.fenykepalbumok.utils.URLPATH;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
public class Kep {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String leiras;

    private String paths;

    @ManyToOne
    @JoinColumn(name = "fk_felhasznalo")
    private Felhasznalo felhasznalo;

    public Felhasznalo getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(Felhasznalo felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    @Transient
    public String getPhotosImagePath() {
        if (paths == null || id == null) return null;

        return "/" + URLPATH.KEP_RELATIVE_PATH + id + "/" + paths;
    }


    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }


    public Long getId() {
        return id;
    }
}
