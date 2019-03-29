import java.io.FileNotFoundException;
import java.util.List;

public class Peaklass {

    public static void main(String[] args) throws FileNotFoundException {
        //GUI.main(new String[]{});

        List<Isik> isikud = Sisselugeja.iskuteLugeja("isikuandmed.txt");
        List<Üksus> üksused = Sisselugeja.üksusteLugeja("üksused.txt");

        for (Isik isik:isikud) {
            System.out.println(isik.toString());
        }
        for (Üksus üksus : üksused) {
            System.out.println(üksus.toString());
        }


    }
}
