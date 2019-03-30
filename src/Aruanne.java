import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aruanne {
    private static List<Isik> formeerunud = new ArrayList<>(); // mälu seniks kuni inimesi pole veel faili kirjutatud

    public static void kirjutaAruanne() throws FileNotFoundException, UnsupportedEncodingException {
        Collections.sort(formeerunud);
        try (java.io.PrintWriter pw = new java.io.PrintWriter("Üldaruanne.txt", "UTF-8")) {

            if (formeerunud.size() == 0) { //
                System.out.println("Ühtegi inimest ei ole registreeritud.");
            }
            else{
                pw.println("Amet\t|\tTäisnimi\t|\tIsikukood");
                pw.println("\t==================\t" + Peaklass.getHashÜksused().get(formeerunud.get(0).getÜksus()) + "\t==================");
                pw.println(formeerunud.get(0));

                for (int i = 1; i < formeerunud.size(); i++) {

                    if (formeerunud.get(i - 1).getÜksus().equals(formeerunud.get(i).getÜksus()) ) {
                        pw.println(formeerunud.get(i));
                    } else {
                        pw.println("\n\t==================\t" + Peaklass.getHashÜksused().get(formeerunud.get(i).getÜksus()) + "\t==================");
                        pw.println(formeerunud.get(i));
                    }
                }
                pw.flush();
            }
        }
    }

    public static void lisaInimene(Isik isik) {
        formeerunud.add(isik);
    }
}
