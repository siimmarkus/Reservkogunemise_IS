import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Andmebaasid {
    private static List<Isik> isikuteAndmebaas;
    private static List<Üksus> üksusteAndmebaas;
    private static HashMap<String, String> hashÜksused;
    private static HashMap<String, List<String>> hashRelvad;

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

    public static void looAndmebaasid() throws IOException {
        isikuteAndmebaas = Sisselugeja.iskuteLugeja("isikuandmed.txt");
        üksusteAndmebaas = Sisselugeja.üksusteAndmebaasiLugeja("üksused.txt");
        hashÜksused = Sisselugeja.üksusteHashMapiLugeja("üksused.txt");
        hashRelvad = Sisselugeja.relvadeHashMapiLugeja("relvad.txt");
    }
}
