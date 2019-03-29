import java.io.IOException;
import java.util.List;
import java.util.Scanner;

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
        //GUI.main(new String[]{});
        List<Isik> isikud = Sisselugeja.iskuteLugeja("isikuandmed.txt");
        List<Üksus> üksused = Sisselugeja.üksusteLugeja("üksused.txt");

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
                case 1:{
                    System.out.println("Palun sisestage oma isikukood: ");
                    String isikukood = input.next();
                    System.out.println("sisestasite isikukoodi " + isikukood);
                    if (kontrolliIsik(isikukood, isikud)){
                        // TODO: 29/03/2019 registreeri isik
                        System.out.println("Registreerisin isiku");
                    }
                    else System.out.println("Sisestatud isikukoodiga inimest ei leidu meie andmebaasis.");
                }
                case 2: // TODO: 29/03/2019 Väljasta registreerunud isikutega auranne ;
                    System.out.println("Säh sulle tabelit");
                case 3: System.exit(0);
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
