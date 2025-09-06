package com.mycompany.app;

import java.util.*;

public class E12_Anagramas {
    public static boolean sonAnagramas(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Integer> freq = new HashMap<>();
        llenar(s, 0, freq);
        consumir(t, 0, freq);
        // todas las frecuencias deben quedar en 0
        return freq.values().stream().allMatch(v -> v == 0);
    }

    private static void llenar(String s, int i, Map<Character, Integer> f) {
        if (i == s.length()) return;                       // base
        f.merge(s.charAt(i), 1, Integer::sum);
        llenar(s, i+1, f);
    }

    private static void consumir(String t, int i, Map<Character, Integer> f) {
        if (i == t.length()) return;                       // base
        char c = t.charAt(i);
        f.put(c, f.getOrDefault(c, 0) - 1);                // resta
        consumir(t, i+1, f);
    }

    public static void main(String[] args) {
        String s1 = "roma";
        String s2 = "amor";
        String s3 = "ramo";
        String s4 = "mora";
        String s5 = "casa";

        System.out.println("\"" + s1 + "\" y \"" + s2 + "\" son anagramas? " + sonAnagramas(s1, s2));
        System.out.println("\"" + s1 + "\" y \"" + s3 + "\" son anagramas? " + sonAnagramas(s1, s3));
        System.out.println("\"" + s1 + "\" y \"" + s4 + "\" son anagramas? " + sonAnagramas(s1, s4));
        System.out.println("\"" + s1 + "\" y \"" + s5 + "\" son anagramas? " + sonAnagramas(s1, s5));
    }
}
