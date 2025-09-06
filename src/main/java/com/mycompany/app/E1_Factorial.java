package com.mycompany.app;

public class E1_Factorial {
    public static void main(String[] args) {
        int n = 5; // Puedes cambiar este valor para probar otros casos
        System.out.println("Factorial de " + n + " es: " + factorial(n));
    }

    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;    // caso base
        return n * factorial(n - 1);       // caso recursivo
    }
}

