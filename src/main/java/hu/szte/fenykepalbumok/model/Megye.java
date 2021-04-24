package hu.szte.fenykepalbumok.model;

import javax.persistence.*;

import hu.szte.fenykepalbumok.model.Orszag;

import java.util.List;

@Entity
public class Megye {

    @Id
    private Long id;

    private String megye;

    @OneToMany(mappedBy = "megye")
    private List<Varos> varosok;

    @ManyToOne(optional = false)
    @JoinColumn(name = "orszagid")
    private Orszag orszag;

    public Orszag getOrszag() {
        return orszag;
    }

    public void setOrszag(Orszag orszag) {
        this.orszag = orszag;
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }


}

