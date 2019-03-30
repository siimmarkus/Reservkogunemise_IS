import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Sisselugeja {
    public static List<Isik> iskuteLugeja(String failinimi) throws IOException {
        List<Isik> isikud = new ArrayList<>();

        try(Scanner sc = new Scanner(new File(failinimi), StandardCharsets.UTF_8)){
            while (sc.hasNextLine()){
                String[] rida = sc.nextLine().split(";");
                isikud.add(new Isik(rida[0],rida[1],rida[2], rida[3],rida[4]));
            }
        }

        return isikud;
    }

    public static List<Üksus> üksusteAndmebaasiLugeja(String failinimi) throws IOException {
        List<Üksus> üksused = new ArrayList<>();

        try(Scanner sc = new Scanner(new File(failinimi), StandardCharsets.UTF_8)){
            while (sc.hasNextLine()){
                String[] rida = sc.nextLine().split(";");
                üksused.add(new Üksus(Integer.parseInt(rida[0]),rida[1],rida[2]));
            }
        }

        return üksused;
    }

    public static HashMap<String, String> üksusteHashMapiLugeja(String failinimi) throws IOException {
        HashMap<String, String> üksused = new HashMap<>();

        try(Scanner sc = new Scanner(new File(failinimi), StandardCharsets.UTF_8)){
            while (sc.hasNextLine()){
                String[] rida = sc.nextLine().split(";");
                üksused.put(rida[0], rida[1] + " " + rida[2]);
            }
        }

        return üksused;
    }

    public static HashMap<String, List<String>> relvadeHashMapiLugeja(String failinimi) throws IOException {
        HashMap<String, List<String>> relvad = new HashMap<>();

        try(Scanner sc = new Scanner(new File(failinimi), StandardCharsets.UTF_8)){
            while (sc.hasNextLine()){
                String[] rida = sc.nextLine().split(";");
                if (relvad.get(rida[0]) != null){
                    System.out.println("Leidsin teise relva");
                    List<String> relvanimed = relvad.get(rida[0]);
                    relvanimed.add(rida[2]);
                    relvad.put(rida[0], relvanimed);
                }
                else {
                    List<String> relvanimed = new ArrayList<>();
                    relvanimed.add(rida[2]);
                    relvad.put(rida[0], relvanimed);
                }
            }
        }
        return relvad;
    }

    public static List<Varustus> varustuseLugeja(String failinimi) throws IOException {
        List<Varustus> varustus = new ArrayList<>();

        try(Scanner sc = new Scanner(new File(failinimi), StandardCharsets.UTF_8)){
            while (sc.hasNextLine()){
                String[] rida = sc.nextLine().split(";");
                int[] uusrida = new int[rida.length];
                for (int i = 0; i < rida.length; i++) {
                    uusrida[i] = Integer.parseInt(rida[i]);
                }
                varustus.add(new Varustus(uusrida[0],uusrida[1],uusrida[2],uusrida[3],uusrida[4],uusrida[5]));

            }
        }

        return varustus;
    }

}
