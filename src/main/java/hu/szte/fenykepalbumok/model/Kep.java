package hu.szte.fenykepalbumok.model;

import hu.szte.fenykepalbumok.utils.URLPATH;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Kep {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String leiras;

    private String paths;

    public List<Velemeny> getForumHozzaszolasList() {
        return velemenyList;
    }

    public void setForumHozzaszolasList(List<Velemeny> velemenyList) {
        this.velemenyList = velemenyList;
    }

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @OneToMany(mappedBy = "kep")
    private List<Velemeny> velemenyList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kategoriaid")
    @NotNull
    private Kategoria kategoria;

    @ManyToOne
    @JoinColumn(name = "felhasznaloid")
    private Felhasznalo felhasznalo;

    @OneToOne
    private Varos varos;

    public List<Velemeny> getVelemenyList() {
        return velemenyList;
    }

    public void setVelemenyList(List<Velemeny> velemenyList) {
        this.velemenyList = velemenyList;
    }

    public List<Ertekeles> getErtekelesek() {
        return ertekelesek;
    }

    public void setErtekelesek(List<Ertekeles> ertekelesek) {
        this.ertekelesek = ertekelesek;
    }

    public Varos getVaros() {
        return varos;
    }

    public void setVaros(Varos varos) {
        this.varos = varos;
    }

    @OneToMany(mappedBy = "kep")
    private List<Ertekeles> ertekelesek;


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
                ", fileName='" + fileName + '\'' +
                ", kategoria=" + kategoria +
                ", varos=" + varos +
                '}';
    }
}
