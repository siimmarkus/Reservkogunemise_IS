public class Ladu {
    private String üksus;
    private int püksid;
    private int sobled;
    private int frentšid;
    private int särgid;
    private int magamiskotid;

    public Ladu(String üksus, int püksid, int sobled, int frentšid, int särgid, int magamiskotid) {
        this.üksus = üksus;
        this.püksid = püksid;
        this.sobled = sobled;
        this.frentšid = frentšid;
        this.särgid = särgid;
        this.magamiskotid = magamiskotid;
    }



    public int getPüksid() {
        return püksid;
    }

    public void setPüksid(int püksid) {
        this.püksid = püksid;
    }

    public int getSobled() {
        return sobled;
    }

    public void setSobled(int sobled) {
        this.sobled = sobled;
    }

    public int getFrentšid() {
        return frentšid;
    }

    public void setFrentšid(int frentšid) {
        this.frentšid = frentšid;
    }

    public int getSärgid() {
        return särgid;
    }

    public void setSärgid(int särgid) {
        this.särgid = särgid;
    }

    public int getMagamiskotid() {
        return magamiskotid;
    }

    public void setMagamiskotid(int magamiskotid) {
        this.magamiskotid = magamiskotid;
    }

    @Override
    public String toString() {
        return "Ladu{" +
                "üksus=" + üksus +
                ", püksid=" + püksid +
                ", sobled=" + sobled +
                ", frentšid=" + frentšid +
                ", särgid=" + särgid +
                ", magamiskotid=" + magamiskotid +
                '}';
    }
}
