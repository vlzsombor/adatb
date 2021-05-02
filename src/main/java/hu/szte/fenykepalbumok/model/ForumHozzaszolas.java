package hu.szte.fenykepalbumok.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class ForumHozzaszolas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String hozzaszolasSzoveg;

    @OneToMany(mappedBy = "forumHozzaszolasParent")
    private List<ForumHozzaszolas> forumHozzaszolasChild;

    @ManyToOne
    @JoinColumn(name = "forumhozzaszolasParent")
    private ForumHozzaszolas forumHozzaszolasParent;

    public List<ForumHozzaszolas> getForumHozzaszolasChild() {
        return forumHozzaszolasChild;
    }

    public void setForumHozzaszolasChild(List<ForumHozzaszolas> forumHozzaszolasChild) {
        this.forumHozzaszolasChild = forumHozzaszolasChild;
    }

    public ForumHozzaszolas getForumHozzaszolasParent() {
        return forumHozzaszolasParent;
    }

    public void setForumHozzaszolasParent(ForumHozzaszolas forumHozzaszolasParent) {
        this.forumHozzaszolasParent = forumHozzaszolasParent;
    }

    public String getHozzaszolasSzoveg() {
        return hozzaszolasSzoveg;
    }

    public void setHozzaszolasSzoveg(String hozzaszolas) {
        this.hozzaszolasSzoveg = hozzaszolas;
    }

    @ManyToOne
    @JoinColumn(name = "felhasznaloid")
    private Felhasznalo felhasznalo;

    @ManyToOne
    @JoinColumn(name = "kepid")
    private Kep kep;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Felhasznalo getFelhasznalo() {
        return felhasznalo;
    }

    public void setFelhasznalo(Felhasznalo felhasznalo) {
        this.felhasznalo = felhasznalo;
    }

    public Kep getKep() {
        return kep;
    }

    public void setKep(Kep kep) {
        this.kep = kep;
    }


    @Override
    public String toString() {
        return "ForumHozzaszolas{" +
                "id=" + id +
                ", hozzaszolas='" + hozzaszolasSzoveg + '\'' +
                ", felhasznalo=" + felhasznalo +
                ", kep=" + kep +
                '}';
    }
}
