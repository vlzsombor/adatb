package hu.szte.fenykepalbumok.utils.queryclass;

public class OrszagDarab {

        private String OrszagNev;
        private Long gyakorisag;



        public OrszagDarab(String telepulesNev, Long gyakorisag) {
            this.OrszagNev = telepulesNev;
            this.gyakorisag = gyakorisag;
        }

    public OrszagDarab(Long telepulesNev, Long gyakorisag) {
        this.OrszagNev = Long.toString(telepulesNev);
        this.gyakorisag = gyakorisag;
    }

        public String getOrszagNev() {
            return OrszagNev;
        }

        public void setOrszagNev(String orszagNev) {
            OrszagNev = orszagNev;
        }

        public Long getGyakorisag() {
            return gyakorisag;
        }

        public void setGyakorisag(Long gyakorisag) {
            this.gyakorisag = gyakorisag;
        }

        @Override
        public String toString() {
            return "OrszagDarab{" +
                    "OrszagNev='" + OrszagNev + '\'' +
                    ", gyakorisag=" + gyakorisag +
                    '}';
        }


}
