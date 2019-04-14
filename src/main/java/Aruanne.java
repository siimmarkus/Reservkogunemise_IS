import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aruanne {
    public static List<Isik> formeerunud = new ArrayList<>(); // mälu seniks kuni inimesi pole veel faili kirjutatud

    public static void kirjutaAruanne() throws IOException {
        Collections.sort(formeerunud);
        try (java.io.PrintWriter pw = new java.io.PrintWriter("Üldaruanne.txt", StandardCharsets.UTF_8)) {

            if (formeerunud.size() == 0) { //
                System.out.println("Ühtegi inimest ei ole registreeritud.");
            }
            else{ // Et ei tekiks viga, siis lisan esimese registreerunu manuaalselt

                pw.println("\t==================\t" + Andmebaasid.getHashÜksused().get(formeerunud.get(0).getÜksus()) + "\t==================");
                pw.print(formeerunud.get(0));
                pw.println("\t|\t" + Andmebaasid.getHashRelvad().get(formeerunud.get(0).getIsikukood()));

                // Kui registreerunud inimeste arv on suurem kui 0, siis kirjuta faili üksuste pealkirjade alla inimeste andmed
                for (int i = 1; i < formeerunud.size(); i++) {

                    if (formeerunud.get(i - 1).getÜksus().equals(formeerunud.get(i).getÜksus()) ) {
                        pw.print(formeerunud.get(i));
                        pw.println("\t|\t" + Andmebaasid.getHashRelvad().get(formeerunud.get(i).getIsikukood()));
                    } else {
                        pw.println("\r\n\t==================\t" + Andmebaasid.getHashÜksused().get(formeerunud.get(i).getÜksus()) + "\t==================");
                        pw.print(formeerunud.get(i));
                        pw.println("\t|\t" + Andmebaasid.getHashRelvad().get(formeerunud.get(i).getIsikukood()));
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
