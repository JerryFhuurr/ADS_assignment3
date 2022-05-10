package nQueens;

public class Main {
    public static void main(String[] args) {
        Algorithm a = new Algorithm(8, false);
        System.out.println(a.getSum());

        Algorithm a2 = new Algorithm(8, true);
        System.out.println(a2.getSum());
    }
}
