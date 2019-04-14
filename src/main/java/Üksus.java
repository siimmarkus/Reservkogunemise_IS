public class Üksus {
    private int id;
    private String nimi;
    private static String kompanii;

    public Üksus(int id, String nimi, String kompanii) {
        this.id = id;
        this.nimi = nimi;
        Üksus.kompanii = kompanii;
    }

    @Override
    public String toString() {
        return "Üksuse id = " + id + ", nimi = '" + nimi + '\'' + ", kompanii = '" + kompanii + '\'' + '}';
    }
}