package hu.szte.fenykepalbumok.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class ForumHozzaszolas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String hozzaszolasSzoveg;

    public String getHozzaszolasSzoveg() {
        return hozzaszolasSzoveg;
    }

    public void setHozzaszolasSzoveg(String hozzaszolas) {
        this.hozzaszolasSzoveg = hozzaszolas;
    }

    @ManyToOne
    @JoinColumn(name = "felhasznaloid")
    private Felhasznalo felhasznalo;

    @ManyToOne
    @JoinColumn(name = "kepid")
    private Kep kep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "ForumHozzaszolas{" +
                "id=" + id +
                ", hozzaszolas='" + hozzaszolasSzoveg + '\'' +
                ", felhasznalo=" + felhasznalo +
                ", kep=" + kep +
                '}';
    }
}
