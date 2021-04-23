package hu.szte.fenykepalbumok.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Varos {

    @Id
    private Long id;

    private String varos;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}