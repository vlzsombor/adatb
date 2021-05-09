package hu.szte.fenykepalbumok.model;


import javax.persistence.*;

@Entity
public class Report {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String ok;

    private String indok;

    //aki jelentette
    @ManyToOne
    private Felhasznalo felhasznalo;

    @ManyToOne
    private Kep kep;


    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getIndok() {
        return indok;
    }

    public void setIndok(String indok) {
        this.indok = indok;
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
        return "Report{" +
                "id=" + id +
                ", ok='" + ok + '\'' +
                ", indok='" + indok + '\'' +
                '}';
    }
}
