package hu.szte.fenykepalbumok.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kategoria {


    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String megnevezes;

    @Column(unique = true)
    private String leiras;


    // todo eager vs lazy
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "kategoria", cascade = CascadeType.ALL)
    private Set<Kep> kepek = new HashSet<>();

    public Kategoria() {

    }

    public Set<Kep> getKepek() {
        return kepek;
    }

    public void setKepek(Set<Kep> kepek) {
        this.kepek = kepek;
    }

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

    public String getLeiras() {
        return leiras;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }


    public Kategoria(String megnevezes, String leiras) {
        this.megnevezes = megnevezes;
        this.leiras = leiras;
        this.kepek = kepek;
    }


    @Override
    public boolean equals(Object obj) {

        Kategoria kategoria;
        if (obj instanceof Kategoria) {
            kategoria = (Kategoria) obj;

            if(((Kategoria) obj).leiras.equalsIgnoreCase(leiras) && kategoria.megnevezes.equalsIgnoreCase(megnevezes)){
                return true;
            }
        }

        return super.equals(obj);
    }

}
