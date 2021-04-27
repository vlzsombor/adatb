package hu.szte.fenykepalbumok.utils.queryclass;

public class TelepulesHanyFenykep {
    private String telepulesNev;
    private Long gyakorisag;

    public TelepulesHanyFenykep(String telepulesNev, Long gyakorisag) {
        this.telepulesNev = telepulesNev;
        this.gyakorisag = gyakorisag;
    }

    public String getTelepulesNev() {
        return telepulesNev;
    }

    public void setTelepulesNev(String telepulesNev) {
        this.telepulesNev = telepulesNev;
    }

    public Long getGyakorisag() {
        return gyakorisag;
    }

    public void setGyakorisag(Long gyakorisag) {
        this.gyakorisag = gyakorisag;
    }

    @Override
    public String toString() {
        return "TelepulesHanyFenykep{" +
                "telepulesNev='" + telepulesNev + '\'' +
                ", gyakorisag=" + gyakorisag +
                '}';
    }
}
