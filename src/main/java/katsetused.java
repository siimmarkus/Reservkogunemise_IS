public class katsetused {

    static void test(Isik[] isikud){
        System.out.println();
    }


    public static void main(String[] args) {
        Isik tom = new Isik("1","a","a","21","a");
        Isik karl = new Isik("2","a","a","21","a");
        Isik[] isikud = {tom,karl};
        test(isikud);
    }
}
