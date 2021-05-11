package hu.szte.fenykepalbumok.model;

import javax.persistence.*;

@Entity
public class Varos {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String megnevezes;

    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "megyeid")
    private Megye megye;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getMegnevezes() {
        return megnevezes;
    }

    public void setMegnevezes(String megnevezes) {
        this.megnevezes = megnevezes;
    }

    public Megye getMegye() {
        return megye;
    }

    public void setMegye(Megye megye) {
        this.megye = megye;
    }

    @Override
    public String toString() {
        return "Varos{" +
                "id=" + id +
                ", megnevezes='" + megnevezes + '\'' +
                ", megye=" + megye +
                '}';
    }
}