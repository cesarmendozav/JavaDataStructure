package com.mycompany.app;
import java.util.*;

public class E2_FibonacciMemo {
    static Map<Integer, Long> memo = new HashMap<>();

    public static long fib(int n) {
        if (n < 2) return n;                    // 0,1
        if (memo.containsKey(n)) return memo.get(n);
        long v = fib(n-1) + fib(n-2);
        memo.put(n, v);
        return v;
    }

    public static void main(String[] args) {
        int n = 10; // Puedes cambiar este valor para probar otros casos
        System.out.println("Fibonacci de " + n + " es: " + fib(n));
    }
}