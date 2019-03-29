public class Üksus {
    private int id;
    private String nimi;
    private String kompanii;

    public Üksus(int id, String nimi, String kompanii) {
        this.id = id;
        this.nimi = nimi;
        this.kompanii = kompanii;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getKompanii() {
        return kompanii;
    }

    public void setKompanii(String kompanii) {
        this.kompanii = kompanii;
    }

    @Override
    public String toString() {
        return "Üksuse id = " + id + ", nimi = '" + nimi + '\'' + ", kompanii = '" + kompanii + '\'' + '}';
    }
}
