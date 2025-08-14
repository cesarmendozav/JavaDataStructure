package com.mycompany.app;

// SpotifyDoublyPlaylistDemo.java
import java.util.Objects;

public class SpotifyDoublyPlaylistDemo {

    /* ========= Modelo ========= */
    static class Cancion {
        private final String titulo;
        private final String artista;
        private final double duracionMin;

        public Cancion(String titulo, String artista, double duracionMin) {
            this.titulo = Objects.requireNonNull(titulo);
            this.artista = Objects.requireNonNull(artista);
            this.duracionMin = duracionMin;
        }
        public String getTitulo()  { return titulo; }
        public String getArtista() { return artista; }
        public double getDuracionMin() { return duracionMin; }

        @Override public String toString() {
            return "🎵 " + titulo + " - " + artista + " (" + duracionMin + " min)";
        }
    }

    /* ======= Nodo doble ======= */
    static class Nodo {
        Cancion dato;
        Nodo prev;   // anterior
        Nodo next;   // siguiente
        Nodo(Cancion dato) { this.dato = dato; }
    }

    /* ===== Playlist doble ===== */
    static class Playlist {
        private Nodo head;     // primera
        private Nodo tail;     // última
        private Nodo current;  // “sonando”
        private int size;

        public boolean estaVacia() { return head == null; }
        public int tamanio() { return size; }

        /* Insertar al final: O(1) con tail */
        public void agregarAlFinal(Cancion c) {
            Nodo n = new Nodo(c);
            if (estaVacia()) {
                head = tail = current = n;
            } else {
                tail.next = n;
                n.prev = tail;
                tail = n;
            }
            size++;
        }

        /* (Didáctico) Insertar al inicio: O(1) */
        public void agregarAlInicio(Cancion c) {
            Nodo n = new Nodo(c);
            if (estaVacia()) {
                head = tail = current = n;
            } else {
                n.next = head;
                head.prev = n;
                head = n;
            }
            size++;
        }

        /* Mostrar de inicio a fin */
        public void mostrarAdelante() {
            if (estaVacia()) { System.out.println("(Playlist vacía)"); return; }
            Nodo it = head;
            while (it != null) {
                System.out.println(it.dato + (it == current ? "  ⬅️ (sonando)" : ""));
                it = it.next;
            }
        }

        /* Mostrar de fin a inicio (para enseñar prev) */
        public void mostrarAtras() {
            if (estaVacia()) { System.out.println("(Playlist vacía)"); return; }
            Nodo it = tail;
            while (it != null) {
                System.out.println(it.dato + (it == current ? "  ⬅️ (sonando)" : ""));
                it = it.prev;
            }
        }

        /* Controles de reproducción */
        public Cancion reproducirActual() {
            if (current == null) return null;
            return current.dato;
        }

        public Cancion siguiente() {
            if (current == null) return null;
            if (current.next != null) current = current.next;
            return current.dato;
        }

        public Cancion anterior() {
            if (current == null) return null;
            if (current.prev != null) current = current.prev;
            return current.dato;
        }

        public void irAlPrincipio() { current = head; }
        public void irAlFinal()     { current = tail; }

        /* Buscar por título (primera coincidencia) */
        public Nodo buscarNodoPorTitulo(String titulo) {
            Nodo it = head;
            while (it != null) {
                if (it.dato.getTitulo().equals(titulo)) return it;
                it = it.next;
            }
            return null;
        }

        /* Eliminar por título (primera coincidencia) */
        public boolean eliminarPorTitulo(String titulo) {
            Nodo objetivo = buscarNodoPorTitulo(titulo);
            if (objetivo == null) return false;

            // Ajustar current si estaba en el eliminado
            if (objetivo == current) {
                current = (objetivo.next != null) ? objetivo.next : objetivo.prev;
            }

            if (objetivo.prev != null) objetivo.prev.next = objetivo.next;
            else head = objetivo.next; // era head

            if (objetivo.next != null) objetivo.next.prev = objetivo.prev;
            else tail = objetivo.prev; // era tail

            size--;
            // Help GC
            objetivo.prev = objetivo.next = null;
            return true;
        }

        /* (Opcional) Insertar DESPUÉS de un título dado */
        public boolean insertarDespuesDe(String tituloExistente, Cancion nueva) {
            Nodo base = buscarNodoPorTitulo(tituloExistente);
            if (base == null) return false;
            Nodo n = new Nodo(nueva);

            n.next = base.next;
            n.prev = base;
            if (base.next != null) base.next.prev = n;
            base.next = n;
            if (tail == base) tail = n;

            size++;
            return true;
        }
    }

    /* ============ Demo ============ */
    public static void main(String[] args) {
        Playlist p = new Playlist();

        p.agregarAlFinal(new Cancion("Blinding Lights", "The Weeknd", 3.2));
        p.agregarAlFinal(new Cancion("Shape of You", "Ed Sheeran", 4.0));
        p.agregarAlFinal(new Cancion("As It Was", "Harry Styles", 2.5));
        p.agregarAlFinal(new Cancion("Flowers", "Miley Cyrus", 3.2));

        System.out.println("📀 Playlist (inicio→fin):");
        p.mostrarAdelante();
        System.out.println("Total: " + p.tamanio());

        System.out.println("\n▶️ Reproducir actual:");
        System.out.println(p.reproducirActual());

        System.out.println("\n⏭️ Siguiente:");
        System.out.println(p.siguiente()); // avanza una
        System.out.println("⏭️ Siguiente:");
        System.out.println(p.siguiente()); // avanza otra

        System.out.println("\n⏮️ Anterior:");
        System.out.println(p.anterior());  // retrocede una

        System.out.println("\n🧭 Ir al principio y reproducir:");
        p.irAlPrincipio();
        System.out.println(p.reproducirActual());

        System.out.println("\n➕ Insertar después de 'Blinding Lights':");
        p.insertarDespuesDe("Blinding Lights", new Cancion("Levitating", "Dua Lipa", 3.4));
        p.mostrarAdelante();

        System.out.println("\n🗑️ Eliminar 'Shape of You':");
        p.eliminarPorTitulo("Shape of You");
        p.mostrarAdelante();

        System.out.println("\n🔁 Mostrar (fin→inicio):");
        p.mostrarAtras();
    }
}

