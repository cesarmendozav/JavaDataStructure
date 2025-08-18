package com.mycompany.app;

// DoublyLinkedPlaylistDemo.java
import java.util.Objects;

public class DoublyLinkedPlaylistDemo {

    /* ===== Modelo ===== */
    static class Cancion {
        final String titulo;
        final String artista;
        final double duracionMin;

        Cancion(String titulo, String artista, double duracionMin) {
            this.titulo = Objects.requireNonNull(titulo);
            this.artista = Objects.requireNonNull(artista);
            this.duracionMin = duracionMin;
        }
        @Override public String toString() { return "🎵 " + titulo + " — " + artista + " (" + duracionMin + "m)"; }
        @Override public boolean equals(Object o){
            if(this==o) return true; if(!(o instanceof Cancion)) return false;
            Cancion c=(Cancion)o; return titulo.equals(c.titulo) && artista.equals(c.artista);
        }
        @Override public int hashCode(){ return Objects.hash(titulo, artista); }
    }

    /* ===== Lista doble (no circular) ===== */
    static class PlaylistDoublyLinked {
        static class Nodo { Cancion dato; Nodo prev, next; Nodo(Cancion d){ dato=d; } }

        private Nodo head, tail; private int size;

        private PlaylistDoublyLinked() {}
        public static PlaylistDoublyLinked create(){ return new PlaylistDoublyLinked(); } // Create()

        public boolean isEmpty(){ return head==null; }
        public int size(){ return size; }

        // Get(posición) -> Elemento
        public Cancion get(int pos){
            ensureNotEmpty(); return nodeAt(pos).dato;
        }

        // Insert(posición, elemento)
        public void insert(int pos, Cancion e){
            if(pos<0 || pos>size) throw new IndexOutOfBoundsException("La posición no existe en la lista: " + pos);
            Nodo n=new Nodo(e);
            if(pos==0){                           // inicio
                n.next=head; if(head!=null) head.prev=n; head=n; if(tail==null) tail=n;
            } else if(pos==size){                 // final
                if(tail==null){ head=tail=n; } else { tail.next=n; n.prev=tail; tail=n; }
            } else {                              // medio
                Nodo prev=nodeAt(pos-1), curr=prev.next;
                prev.next=n; n.prev=prev; n.next=curr; curr.prev=n;
            }
            size++;
        }

        // Delete(posición)
        public void delete(int pos){
            ensureNotEmpty();
            if(pos<0 || pos>=size) throw new IndexOutOfBoundsException("La posición no existe en la lista: " + pos);
            if(pos==0){
                Nodo old=head; head=head.next; if(head!=null) head.prev=null; if(old==tail) tail=null;
            } else if(pos==size-1){
                tail=tail.prev; if(tail!=null) tail.next=null; if(tail==null) head=null;
            } else {
                Nodo prev=nodeAt(pos-1), target=prev.next;
                prev.next=target.next; target.next.prev=prev;
            }
            size--;
        }

        // Find(elemento) -> Nodo o null
        public Nodo find(Cancion e){
            for(Nodo it=head; it!=null; it=it.next) if(it.dato.equals(e)) return it;
            return null;
        }

        // FindIndex(elemento) -> Posición o -1
        public int findIndex(Cancion e){
            int i=0; for(Nodo it=head; it!=null; it=it.next, i++) if(it.dato.equals(e)) return i;
            return -1;
        }

        // Update(posición, elemento)
        public void update(int pos, Cancion e){
            ensureNotEmpty(); nodeAt(pos).dato=e;
        }

        // Helpers
        private void ensureNotEmpty(){ if(isEmpty()) throw new IllegalStateException("La lista está vacía."); }
        private Nodo nodeAt(int index){
            if(index<0 || index>=size) throw new IndexOutOfBoundsException("La posición no existe en la lista: " + index);
            if(index <= size/2){ Nodo it=head; for(int i=0;i<index;i++) it=it.next; return it; }
            else { Nodo it=tail; for(int i=size-1;i>index;i--) it=it.prev; return it; }
        }
        public void mostrarAdelante(){
            if(isEmpty()){ System.out.println("(Lista vacía)"); return; }
            int i=0; for(Nodo it=head; it!=null; it=it.next) System.out.println("["+ (i++) +"] "+it.dato);
        }
        public void mostrarAtras(){
            if(isEmpty()){ System.out.println("(Lista vacía)"); return; }
            int i=size-1; for(Nodo it=tail; it!=null; it=it.prev) System.out.println("["+ (i--) +"] "+it.dato);
        }
    }

    /* ===== Demo ===== */
    public static void main(String[] args){
        PlaylistDoublyLinked dl = PlaylistDoublyLinked.create(); // Create()

        dl.insert(0, new Cancion("Blinding Lights", "The Weeknd", 3.2));
        dl.insert(1, new Cancion("Shape of You", "Ed Sheeran", 4.0));
        dl.insert(2, new Cancion("As It Was", "Harry Styles", 2.5));
        dl.insert(1, new Cancion("Levitating", "Dua Lipa", 3.4)); // medio

        System.out.println("→ Adelante:");
        dl.mostrarAdelante();

        System.out.println("\nGet(2): " + dl.get(2));
        System.out.println("FindIndex('Shape of You'): " + dl.findIndex(new Cancion("Shape of You","Ed Sheeran",0)));

        dl.update(0, new Cancion("Flowers","Miley Cyrus",3.2));
        System.out.println("\nDespués de Update(0):"); dl.mostrarAdelante();

        dl.delete(2);
        System.out.println("\nDespués de Delete(2):"); dl.mostrarAdelante();

        System.out.println("\n← Atrás:");
        dl.mostrarAtras();
    }
}
