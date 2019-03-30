import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
    private static List<Isik> isikuteAndmebaas;
    private static List<Üksus> üksusteAndmebaas;
    private static HashMap<String, String> hashÜksused;

    public static List<Isik> getIsikuteAndmebaas() {
        return isikuteAndmebaas;
    }

    public static List<Üksus> getÜksusteAndmebaas() {
        return üksusteAndmebaas;
    }

    public static HashMap<String, String> getHashÜksused() {
        return hashÜksused;
    }

    //Tagastab true, kui antud idkoodiga isik on juba isikud listis.
    public static boolean kontrolliIsik(String idkood, List<Isik> isikud){
        for (Isik isik : isikud) {
            if (isik.getIsikukood().equals(idkood)){
                return true;
            }
        }
        return false;
    }
    private static void looAndmebaasid() throws IOException {
        isikuteAndmebaas = Sisselugeja.iskuteLugeja("isikuandmed.txt");
        üksusteAndmebaas = Sisselugeja.üksusteAndmebaasiLugeja("üksused.txt");
        hashÜksused = Sisselugeja.üksusteHashMapiLugeja("üksused.txt");

    }

    public static void main(String[] args) throws IOException {
        looAndmebaasid();
        Aruanne.lisaInimene(new Isik("3902984829", "Brunhilde", "Jones", "22", "kapten"));
        Aruanne.lisaInimene(new Isik("3902982821", "Aadam", "Kloun", "68", "auto"));
        Aruanne.lisaInimene(new Isik("3902984232", "Kiire", "Auto", "68", "auto"));

// Suhtlus kasutajaga terminali kaudu
        System.out.println("Teretulemast kasutama KV uut innovaatilist formeerumistarkvara.");
        System.out.println("Mida soovite teha?");
        Scanner input = new Scanner(System.in);
        while (true){
            System.out.println("\nLisada isik formeerimiseks (1)");
            System.out.println("Printida senise formeerumise aruanne (2)");
            System.out.println("Väljuda programmist (3)");
            int valik = input.nextInt();
            switch (valik){
                case 1:
                    System.out.println("Palun sisestage oma isikukood: ");
                    String isikukood = input.next();
                    System.out.println("sisestasite isikukoodi " + isikukood);
                    if (kontrolliIsik(isikukood, isikuteAndmebaas)){
                        // TODO: 29/03/2019 registreeri isik
                        System.out.println("Registreerisin isiku");
                    }
                    else System.out.println("Sisestatud isikukoodiga inimest ei leidu meie andmebaasis.");
                    break;

                case 2: // TODO: 29/03/2019 Väljasta registreerunud isikutega auranne ;
                    Aruanne.kirjutaAruanne();
                    System.out.println("Säh sulle tabelit");
                    break;
                case 3: System.exit(0);
                default:
                    System.out.println("something broken");
            }
        }



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
