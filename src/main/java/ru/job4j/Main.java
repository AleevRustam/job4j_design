package ru.job4j;

public class Main {
    public static void main(String[] args) {
        System.out.println(fact(5));
        System.out.println("Git stash 1");
    }

    public static int fact(int n) {
       if (n == 1) {
           return 1;
       } else {
           return n * fact(n - 1);
       }
    }
}