package cd.works.rsaprime;

public class MainApp {

    public static void main(String args[]) {
        FindPrimes findPrimes = new FindPrimes();
        Long[] factors = findPrimes.findPrimes(77);
        System.out.println("Factor1: "+factors[0]+" ; Factor2: "+factors[1]);
    }
}
