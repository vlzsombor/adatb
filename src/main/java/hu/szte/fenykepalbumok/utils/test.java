package hu.szte.fenykepalbumok.utils;

public class test{
    Integer sum;
    String ertekeles;

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public String getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(String ertekeles) {
        this.ertekeles = ertekeles;
    }


    @Override
    public String toString() {
        return "test{" +
                "sum=" + sum +
                ", ertekeles='" + ertekeles + '\'' +
                '}';
    }
}