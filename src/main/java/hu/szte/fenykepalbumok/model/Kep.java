package hu.szte.fenykepalbumok.model;

import hu.szte.fenykepalbumok.utils.URLPATH;

import javax.persistence.*;
import java.awt.print.Book;
import java.util.Collection;

@Entity
public class Kep {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String leiras;

    private String paths;

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategoria_id")
    private Kategoria kategoria;

    @ManyToOne
    @JoinColumn(name = "fk_felhasznalo")
    private Felhasznalo felhasznalo;


    @OneToOne(cascade = CascadeType.ALL)
    private Ertekeles ertekeles;

    public Ertekeles getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(Ertekeles ertekeles) {
        this.ertekeles = ertekeles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Kategoria getKategoria() {
        return kategoria;
    }

    public void setKategoria(Kategoria kategoria) {
        this.kategoria = kategoria;
    }

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

    @Override
    public String toString() {
        return "Kep{" +
                "id=" + id +
                ", leiras='" + leiras + '\'' +
                ", paths='" + paths + '\'' +
                ", kategoria=" + kategoria +
                ", ertekeles=" + ertekeles +
                '}';
    }
}
