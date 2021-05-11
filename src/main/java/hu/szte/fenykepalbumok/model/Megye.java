package hu.szte.fenykepalbumok.model;

import javax.persistence.*;

import hu.szte.fenykepalbumok.model.Orszag;

import java.util.List;

@Entity
public class Megye {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String megnevezes;

    @OneToMany(mappedBy = "megye")
    private List<Varos> varosok;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "orszagid")
    private Orszag orszag;

    public Orszag getOrszag() {
        return orszag;
    }

    public void setOrszag(Orszag orszag) {
        this.orszag = orszag;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public List<Varos> getVarosok() {
        return varosok;
    }

    public void setVarosok(List<Varos> varosok) {
        this.varosok = varosok;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Megye{" +
                "id=" + id +
                ", megnevezes='" + megnevezes + '\'' +
                ", orszag=" + orszag +
                '}';
    }
}

