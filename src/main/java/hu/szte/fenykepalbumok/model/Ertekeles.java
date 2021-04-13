package hu.szte.fenykepalbumok.model;

import javax.persistence.*;

@Entity
public class Ertekeles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Integer ertekeles;

    public Ertekeles(int ertekeles) {
        this.ertekeles = ertekeles;
    }

    @OneToOne(mappedBy = "ertekeles")
    private Kep kep;

    public Ertekeles() {

    }

    public Long getId() {
        return id;
    }
}
