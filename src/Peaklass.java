import java.io.IOException;
import java.util.*;

public class Peaklass {

    //Tegeleb isiku registreerimise ning andmebaasi ja aruande puhvrisse lisamisega.
    private static void registreeriIsik(){
        Scanner sisend = new Scanner(System.in);
        String isikukood;
        String e_nimi;
        String p_nimi;
        String üksus;
        String amet;

        //Isikukood
        System.out.println("Sisestage isikukood: ");
        isikukood = sisend.next();

        //Kui sisestatud isikukoodiga inimene on juba registreeritud
        if(Andmebaasid.getHashIsikud().containsKey(isikukood)){
            System.out.println("Isikukoodiga " + isikukood + " inimene on juba andmebaasis olemas!");
            System.out.println("Kas Te soovite uut isikut registreerida? (y/n)");
            String otsus = sisend.next();
            if (otsus.toLowerCase().equals("y")){
                registreeriIsik();
            }
            return;
        }

        //Ülejäänud info
        System.out.println("Sisestage isiku eesnimi");
        e_nimi = sisend.next();
        System.out.println("Sisestage isiku perenimi");
        p_nimi = sisend.next();
        System.out.println("Sisestage isiku üksus");
        üksus = sisend.next();
        System.out.println("Sisestage isiku amet");
        amet = sisend.next();
        Isik registreeritav = new Isik(isikukood, e_nimi, p_nimi, üksus, amet);
        Andmebaasid.lisaIsik(isikukood, registreeritav);
        Aruanne.lisaInimene(registreeritav);
        System.out.println("Registreeriti isik: " + registreeritav.toString());

    }

    public static void main(String[] args) throws IOException {
        Andmebaasid.looAndmebaasid();
        Aruanne.lisaInimene(new Isik("38508172103", "Bruno", "Jones", "22", "kapten"));
        Aruanne.lisaInimene(new Isik("3902982821", "Aadam", "Kloun", "68", "auto"));
        Aruanne.lisaInimene(new Isik("3902984232", "Kiire", "Auto", "68", "auto"));

        // Suhtlus kasutajaga terminali kaudu
        String[] tervitused = {"Tere. Kasutate KV tarkvara. Kogu source-code on asutusesiseseks kasutuseks",
                "Teretulemast kasutama KV uut innovaatilist formeerumistarkvara.",
                "Nii nad tapsidki meie Ferdinandi."};

        // Random kasutus :)
        Random juhutervitus = new Random();
        int juhuslik = juhutervitus.nextInt(tervitused.length);
        System.out.println(tervitused[juhuslik]);
        System.out.println("\nMida soovite teha?");
        Scanner input = new Scanner(System.in);


        System.out.println(Andmebaasid.getHashÜksused().keySet());

        while (true){
            System.out.println("\nVäljuda programmist (0)");
            System.out.println("Lisada isik formeerimiseks (1)");
            System.out.println("Trükkida senise formeerumise aruanne (2)");
            System.out.println("Väljastada kõik isikud andmebaasis (3)");
            System.out.println("Väljastada kõikide ladude seisud (4)");

            int valik = input.nextInt();
            switch (valik){
                case 0: System.exit(0);
                case 1:
                    registreeriIsik();
                    break;
                case 2:
                    Aruanne.kirjutaAruanne();
                    System.out.println("Valmis.");
                    break;
                case 3:
                    System.out.println("Registreeritud isikud: ");
                    HashMap<String, Isik> isikud = Andmebaasid.getHashIsikud();
                    for (String võti: isikud.keySet()){
                        System.out.println(Andmebaasid.getHashIsikud().get(võti).toString());
                    }
                    break;
                case 4:
                    System.out.println("Laod: ");
                    HashMap<String, Ladu> laod = Andmebaasid.getHashLaod();
                    for (String võti: laod.keySet()){
                        System.out.println(laod.get(võti).toString());
                    }
                    break;
                default:
                    System.out.println("Valikut: " + valik + " ei eksisteeri.");
            }
        }
    }
}