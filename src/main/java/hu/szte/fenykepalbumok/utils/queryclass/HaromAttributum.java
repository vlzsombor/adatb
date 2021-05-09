package hu.szte.fenykepalbumok.utils.queryclass;

public class HaromAttributum {
    private String elsoString;
    private String masodikString;
    private long harmadikLong;

    public HaromAttributum(String elsoString, String masodikString, long harmadikLong) {
        this.elsoString = elsoString;
        this.masodikString = masodikString;
        this.harmadikLong = harmadikLong;
    }

    public String getElsoString() {
        return elsoString;
    }

    public void setElsoString(String elsoString) {
        this.elsoString = elsoString;
    }

    public String getMasodikString() {
        return masodikString;
    }

    public void setMasodikString(String masodikString) {
        this.masodikString = masodikString;
    }

    public long getHarmadikLong() {
        return harmadikLong;
    }

    public void setHarmadikLong(long harmadikLong) {
        this.harmadikLong = harmadikLong;
    }

    @Override
    public String toString() {
        return "HaromAttributum{" +
                "elsoString='" + elsoString + '\'' +
                ", masodikString='" + masodikString + '\'' +
                ", harmadikLong=" + harmadikLong +
                '}';
    }
}
