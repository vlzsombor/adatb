package hu.szte.fenykepalbumok.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import hu.szte.fenykepalbumok.model.Orszag;

@Entity
public class Megye {

    @Id
    private Long id;

    private String megye;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_orszag")
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

