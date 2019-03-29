public class Isik {
    private String isikukood;
    private String e_nimi;
    private String p_nimi;
    private int üksus;
    private String amet;

    public Isik(String isikukood, java.lang.String e_nimi, java.lang.String p_nimi, int üksus, java.lang.String amet) {
        this.isikukood = isikukood;
        this.e_nimi = e_nimi;
        this.p_nimi = p_nimi;
        this.üksus = üksus;
        this.amet = amet;
    }

    public String getIsikukood() {
        return isikukood;
    }

    public java.lang.String getE_nimi() {
        return e_nimi;
    }

    public java.lang.String getP_nimi() {
        return p_nimi;
    }

    public int getÜksus() {
        return üksus;
    }

    public java.lang.String getAmet() {
        return amet;
    }

    public void setIsikukood(String isikukood) {
        this.isikukood = isikukood;
    }

    public void setE_nimi(String e_nimi) {
        this.e_nimi = e_nimi;
    }

    public void setP_nimi(String p_nimi) {
        this.p_nimi = p_nimi;
    }

    public void setÜksus(int üksus) {
        this.üksus = üksus;
    }

    public void setAmet(String amet) {
        this.amet = amet;
    }

    @Override
    public String toString() {
        return "Isik{" +
                " isikukood = " + isikukood +
                ", eesnimi = '" + e_nimi + '\'' +
                ", perenimi = '" + p_nimi + '\'' +
                ", üksus = " + üksus +
                ", amet = '" + amet + '\'' +
                '}';
    }
}
