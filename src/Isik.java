public class Isik implements Comparable<Isik> {
    private String isikukood;
    private String e_nimi;
    private String p_nimi;
    private String üksus;
    private String amet;

    private int pükse;
    private int särke;
    private int soblesid;
    private int ferntšisid;
    private int magamiskotte;

    public Isik(String isikukood, java.lang.String e_nimi, java.lang.String p_nimi, String üksus, java.lang.String amet) {
        this.isikukood = isikukood;
        this.e_nimi = e_nimi;
        this.p_nimi = p_nimi;
        this.üksus = üksus;
        this.amet = amet;

        if (amet.equals("Laskur")){
            this.pükse = 2;

        }

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

    public String getÜksus() {
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

    public void setÜksus(String üksus) {
        this.üksus = üksus;
    }

    public void setAmet(String amet) {
        this.amet = amet;
    }

    @Override
    public String toString() {
        return  amet + " \t|\t " + e_nimi + " " + p_nimi + " \t\t|\t "  + isikukood;
    }
    public int compareTo(Isik võrreldav) {
            if (Integer.parseInt(üksus) < Integer.parseInt(võrreldav.üksus))
                return -1; // negatiivne arv näitab, et this on väiksem kui võrreldav
            if (Integer.parseInt(üksus) > Integer.parseInt(võrreldav.üksus))
                return 1; // positiivne arv näitab, et this on suurem kui võrreldav
            return 0; // null tähendab, et mõlemad on võrdsed
    }
}
