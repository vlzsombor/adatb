package hu.szte.fenykepalbumok.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class testModel {
    @Id
    private Long id;

    private String test;

    private Integer alma;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Integer getAlma() {
        return alma;
    }

    public void setAlma(Integer alma) {
        this.alma = alma;
    }
}

