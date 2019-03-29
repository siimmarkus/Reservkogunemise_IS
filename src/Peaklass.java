import java.io.IOException;
import java.util.List;

public class Peaklass {

    //Tagastab true, kui antud idkoodiga isik on juba isikud listis.
    public static boolean kontrolliIsik(String idkood, List<Isik> isikud){
        for (Isik isik : isikud) {
            if (isik.getIsikukood().equals(idkood)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        //Kutsub GUI välja
        GUI.main(new String[]{});
        List<Isik> isikud = Sisselugeja.iskuteLugeja("isikuandmed.txt");
        List<Üksus> üksused = Sisselugeja.üksusteLugeja("üksused.txt");

        /*
        for (Isik isik:isikud) {
            System.out.println(isik.toString());
        }
        for (Üksus üksus : üksused) {
            System.out.println(üksus.toString());
        }
        */

        //System.out.println("Isik on juba andmebaasis: " + kontrolliIsik("39102305432", isikud));


    }
}
