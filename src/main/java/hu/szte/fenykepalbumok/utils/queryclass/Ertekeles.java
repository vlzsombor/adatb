package hu.szte.fenykepalbumok.utils.queryclass;

public class Ertekeles {
    private Long id;
    private Double avg;

    public Ertekeles(Long id, Double avg) {
        this.id = id;
        this.avg = avg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    @Override
    public String toString() {
        return "Ertekeles{" +
                "id=" + id +
                ", avg=" + avg +
                '}';
    }
}
