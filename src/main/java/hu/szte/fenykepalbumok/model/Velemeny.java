package hu.szte.fenykepalbumok.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
public class Velemeny implements Comparable<Velemeny> {
    public Velemeny() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = new Date();
        this.datum=formatter.format(date);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String datum;

    @NotEmpty
    private String hozzaszolasSzoveg;

    @OneToMany(mappedBy = "velemenyParent")
    private List<Velemeny> velemenyChild;

    @ManyToOne
    @JoinColumn(name = "velemenyParent")
    private Velemeny velemenyParent;

    public List<Velemeny> getForumHozzaszolasChild() {
        return velemenyChild;
    }

    public void setForumHozzaszolasChild(List<Velemeny> velemenyChild) {
        this.velemenyChild = velemenyChild;
    }

    public Velemeny getForumHozzaszolasParent() {
        return velemenyParent;
    }

    public void setForumHozzaszolasParent(Velemeny velemenyParent) {
        this.velemenyParent = velemenyParent;
    }

    public String getHozzaszolasSzoveg() {
        return hozzaszolasSzoveg;
    }

    public void setHozzaszolasSzoveg(String hozzaszolas) {
        this.hozzaszolasSzoveg = hozzaszolas;
    }

    @ManyToOne
    @JoinColumn(name = "felhasznaloid")
    private Felhasznalo felhasznalo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "kepid", nullable = false)
    private Kep kep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDatum() { return datum; }

    public Felhasznalo getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(Felhasznalo felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public Kep getKep() {
        return kep;
    }

    public void setKep(Kep kep) {
        this.kep = kep;
    }


    @Override
    public String toString() {
        return "Velemeny{" +
                "id=" + id +
                ", datum='" + datum + '\'' +
                ", hozzaszolasSzoveg='" + hozzaszolasSzoveg + '\'' +
                '}';
    }

    @Override
    public int compareTo(Velemeny o) {
        return 0;
    }
}
