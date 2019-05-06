public class katsetused {

    static int test(int i){
        if (i<0){
            throw new RuntimeException("neg arv");
        }
        System.out.println("test");
        return i;
    }


    public static void main(String[] args) {
        try {
            test(-1);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }
}
