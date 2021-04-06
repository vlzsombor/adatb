package hu.szte.fenykepalbumok.model;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Felhasznalo {



    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    public Felhasznalo() {

    }

    public Felhasznalo(String firstName, String lastName, String email, String password, String jogosultsag) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.jogosultsag = jogosultsag;
        this.email = email;
    }

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    private String felhasznaloNev;

    private String jelszo;

    private String password;

    private String email;

    private String telefonszam;

    private String vezetekNev;

    private String keresztNev;

    //!!!!!!!!!!!!
    private String lakcim;

    //!!!!!!!!
    private String jogosultsag;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getVezetekNev() {
        return vezetekNev;
    }

    public void setVezetekNev(String vezetekNev) {
        this.vezetekNev = vezetekNev;
    }

    public String getKeresztNev() {
        return keresztNev;
    }

    public void setKeresztNev(String keresztNev) {
        this.keresztNev = keresztNev;
    }

    public String getLakcim() {
        return lakcim;
    }

    public void setLakcim(String lakcim) {
        this.lakcim = lakcim;
    }

    public String getJogosultsag() {
        return jogosultsag;
    }

    public void setJogosultsag(String jogosultsag) {
        this.jogosultsag = jogosultsag;
    }
}
