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
        if (!Andmebaasid.getHashÜksused().containsKey(üksus)){
            throw new mitteEksisteerivaÜksuseExeption("Üksust " + üksus + " ei ole andmebaasides olemas");
        }
        this.üksus = üksus;
        this.amet = amet;
        annaVarustus();


    }

    /**
     * Isiku loomisel antakse talle laost teatav hulk varustust.
     */
    private void annaVarustus(){
        Ladu ladu = Andmebaasid.getHashLaod().get(üksus);

        this.pükse = 2;
        ladu.setPüksid(ladu.getPüksid() - pükse);
        this.särke = 2;
        ladu.setSärgid(ladu.getSärgid() - särke);
        this.soblesid = 2;
        ladu.setSobled(ladu.getSobled() - soblesid);
        this.ferntšisid = 2;
        ladu.setFrentšid(ladu.getFrentšid() - ferntšisid);
        this.magamiskotte = 1;
        ladu.setMagamiskotid(ladu.getMagamiskotid() - magamiskotte);
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

    /**
     * Isik objekte võrreldakse sorteerimisel nende üksuste järgi.
     */
    public int compareTo(Isik võrreldav) {
            if (Integer.parseInt(üksus) < Integer.parseInt(võrreldav.üksus))
                return -1; // negatiivne arv näitab, et this on väiksem kui võrreldav
            if (Integer.parseInt(üksus) > Integer.parseInt(võrreldav.üksus))
                return 1; // positiivne arv näitab, et this on suurem kui võrreldav
            return 0; // null tähendab, et mõlemad on võrdsed
    }
}


class mitteEksisteerivaÜksuseExeption extends RuntimeException{
    public mitteEksisteerivaÜksuseExeption(String message) {
        super(message);
    }
}