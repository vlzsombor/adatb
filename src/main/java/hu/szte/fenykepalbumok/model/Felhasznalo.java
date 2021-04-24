package hu.szte.fenykepalbumok.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Felhasznalo {

    public Felhasznalo() {

    }

    public Felhasznalo(String keresztNev, String vezetekNev, String email, String jelszo, String jogosultsag) {
        this.keresztNev = keresztNev;
        this.vezetekNev = vezetekNev;
        this.jelszo = jelszo;
        this.jogosultsag = jogosultsag;
        this.email = email;
    }


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String keresztNev;

    private String vezetekNev;


    private String felhasznaloNev;

    private String jelszo;

    private String email;

    private String telefonszam;


    @OneToOne
    private Varos varos;

    //!!!!!!!!
    private String jogosultsag;

    @OneToMany(mappedBy = "felhasznalo")
    private List<Kep> kepek = new ArrayList();

    public List<Kep> getKepek() {
        return kepek;
    }

    public void setKepek(List<Kep> kepek) {
        this.kepek = kepek;
    }

    public String getKeresztNev() {
        return keresztNev;
    }

    public void setKeresztNev(String keresztNev) {
        this.keresztNev = keresztNev;
    }

    public String getVezetekNev() {
        return vezetekNev;
    }

    public void setVezetekNev(String vezetekNev) {
        this.vezetekNev = vezetekNev;
    }

    public String getFelhasznaloNev() {
        return felhasznaloNev;
    }

    public void setFelhasznaloNev(String felhasznaloNev) {
        this.felhasznaloNev = felhasznaloNev;
    }

    public String getJelszo() {
        return jelszo;
    }

    public void setJelszo(String jelszo) {
        this.jelszo = jelszo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }


    public String getJogosultsag() {
        return jogosultsag;
    }

    public void setJogosultsag(String jogosultsag) {
        this.jogosultsag = jogosultsag;
    }


    @Override
    public String toString() {
        return "Felhasznalo{" +
                "id=" + id +
                ", keresztNev='" + keresztNev + '\'' +
                ", vezetekNev='" + vezetekNev + '\'' +
                ", felhasznaloNev='" + felhasznaloNev + '\'' +
                ", jelszo='" + jelszo + '\'' +
                ", email='" + email + '\'' +
                ", telefonszam='" + telefonszam + '\'' +
                ", jogosultsag='" + jogosultsag + '\'' +
                ", kepek=" + kepek +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Varos getVaros() {
        return varos;
    }

    public void setVaros(Varos varos) {
        this.varos = varos;
    }
}
