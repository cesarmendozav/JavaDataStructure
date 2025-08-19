package com.mycompany.app;

import java.util.Stack;

class Video {
    String titulo;
    Video next;
    Video(String titulo) {
        this.titulo = titulo;
    }
}

public class Ejercicio5_TikTok {
    private Video head;
    private Stack<Video> historial = new Stack<>();

    public void agregar(String titulo) {
        Video nuevo = new Video(titulo);
        if (head == null) {
            head = nuevo;
            head.next = head; // circular
        } else {
            Video temp = head;
            while (temp.next != head) temp = temp.next;
            temp.next = nuevo;
            nuevo.next = head;
        }
    }

    public void verVideos(int n) {
        Video actual = head;
        for (int i = 0; i < n; i++) {
            System.out.println("Viendo: " + actual.titulo);
            historial.push(actual);
            actual = actual.next;
        }
    }

    public void atras() {
        if (historial.isEmpty()) {
            System.out.println("No hay historial.");
        } else {
            System.out.println("Volviendo a: " + historial.pop().titulo);
        }
    }

    public static void main(String[] args) {
        Ejercicio5_TikTok app = new Ejercicio5_TikTok();
        app.agregar("Video1");
        app.agregar("Video2");
        app.agregar("Video3");

        app.verVideos(4); // simula ver 4 videos
        app.atras();
        app.atras();
    }
}