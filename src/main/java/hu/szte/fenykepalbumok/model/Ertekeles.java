package hu.szte.fenykepalbumok.model;

import javax.persistence.*;

@Entity
public class Ertekeles {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer ertekeles;

    public Ertekeles(int ertekeles) {
        this.ertekeles = ertekeles;
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "kepid", nullable = false)
    private Kep kep;

    @ManyToOne
    @JoinColumn(name = "felhasznaloid")
    private Felhasznalo felhasznalo;

    public void setId(Long id) {
        this.id = id;
    }

    public Felhasznalo getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(Felhasznalo felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public Ertekeles() {

    }

    public Integer getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(Integer ertekeles) {
        this.ertekeles = ertekeles;
    }

    public Kep getKep() {
        return kep;
    }

    public void setKep(Kep kep) {
        this.kep = kep;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Ertekeles{" +
                "id=" + id +
                ", ertekeles=" + ertekeles +
                '}';
    }
}
