import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Andmebaasid {
    private static List<Isik> isikuteAndmebaas;
    private static List<Üksus> üksusteAndmebaas;
    private static HashMap<String, String> hashÜksused;
    private static HashMap<String, List<String>> hashRelvad;
    private static HashMap<String, Ladu> hashLaod;
    private static HashMap<String, Isik> hashIsikud;

    public static List<Isik> getIsikuteAndmebaas() {
        return isikuteAndmebaas;
    }

    public static List<Üksus> getÜksusteAndmebaas() {
        return üksusteAndmebaas;
    }

    public static HashMap<String, String> getHashÜksused() {
        return hashÜksused;
    }

    public static HashMap<String, List<String>> getHashRelvad() {
        return hashRelvad;
    }

    public static HashMap<String, Ladu> getHashLaod() { return hashLaod; }

    public static HashMap<String, Isik> getHashIsikud() {
        return hashIsikud;
    }

    public static void lisaIsik(String võti, Isik isik){
        hashIsikud.put(võti, isik);
    }

    public static void looAndmebaasid() throws IOException {
        hashLaod = Sisselugeja.ladudeHashMapiLugeja("laod.txt");
        hashÜksused = Sisselugeja.üksusteHashMapiLugeja("üksused.txt");
        hashRelvad = Sisselugeja.relvadeHashMapiLugeja("relvad.txt");
        hashIsikud = Sisselugeja.iskuteHashMapiLugeja("isikuandmed.txt");
    }
}
