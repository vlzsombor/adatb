package hu.szte.fenykepalbumok.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Varos {

    @Id
    private Long id;

    private String varos;

    @ManyToOne(optional = false)
    @JoinColumn(name = "megyeid")
    private Megye megye;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}