package com.mycompany.app;

// DoublyCircularPlaylistDemo.java
import java.util.Objects;

public class DoublyCircularPlaylistDemo {

    /* ===== Modelo ===== */
    static class Cancion {
        final String titulo;
        final String artista;
        final double duracionMin;

        Cancion(String titulo, String artista, double duracionMin){
            this.titulo = Objects.requireNonNull(titulo);
            this.artista = Objects.requireNonNull(artista);
            this.duracionMin = duracionMin;
        }
        @Override public String toString(){ return "🎵 " + titulo + " — " + artista + " (" + duracionMin + "m)"; }
        @Override public boolean equals(Object o){
            if(this==o) return true; if(!(o instanceof Cancion)) return false;
            Cancion c=(Cancion)o; return titulo.equals(c.titulo) && artista.equals(c.artista);
        }
        @Override public int hashCode(){ return Objects.hash(titulo, artista); }
    }

    /* ===== Lista doble circular ===== */
    static class PlaylistDoublyCircular {
        static class Nodo { Cancion dato; Nodo prev, next; Nodo(Cancion d){ dato=d; } }

        private Nodo head, tail; private int size;

        private PlaylistDoublyCircular() {}
        public static PlaylistDoublyCircular create(){ return new PlaylistDoublyCircular(); } // Create()

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

            if(size==0){                             // primer nodo
                head=tail=n; n.next=n.prev=n;
            } else if(pos==0){                        // inicio
                n.next=head; n.prev=tail;
                head.prev=n; tail.next=n;
                head=n;
            } else if(pos==size){                     // final
                n.prev=tail; n.next=head;
                tail.next=n; head.prev=n;
                tail=n;
            } else {                                  // medio
                Nodo prev=nodeAt(pos-1), curr=prev.next;
                prev.next=n; n.prev=prev;
                n.next=curr; curr.prev=n;
            }
            size++;
        }

        // Delete(posición)
        public void delete(int pos){
            ensureNotEmpty();
            if(pos<0 || pos>=size) throw new IndexOutOfBoundsException("La posición no existe en la lista: " + pos);

            if(size==1){ head=tail=null; size=0; return; }

            if(pos==0){                               // borrar head
                Nodo old=head; head=head.next;
                head.prev=tail; tail.next=head;
                old.next=old.prev=null;
            } else if(pos==size-1){                   // borrar tail
                Nodo old=tail; tail=tail.prev;
                tail.next=head; head.prev=tail;
                old.next=old.prev=null;
            } else {                                   // medio
                Nodo prev=nodeAt(pos-1), target=prev.next;
                prev.next=target.next; target.next.prev=prev;
                target.next=target.prev=null;
            }
            size--;
        }

        // Find(elemento) -> Nodo o null (una vuelta)
        public Nodo find(Cancion e){
            if(isEmpty()) return null;
            Nodo it=head;
            do { if(it.dato.equals(e)) return it; it=it.next; } while(it!=head);
            return null;
        }

        // FindIndex(elemento) -> Posición o -1 (una vuelta)
        public int findIndex(Cancion e){
            if(isEmpty()) return -1;
            int i=0; Nodo it=head;
            do { if(it.dato.equals(e)) return i; it=it.next; i++; } while(it!=head);
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
            Nodo it=head; for(int i=0;i<index;i++) it=it.next; return it; // circular: una vuelta como máx
        }
        public void mostrarUnaVueltaDesdeHead(){
            if(isEmpty()){ System.out.println("(Lista vacía)"); return; }
            Nodo it=head; int i=0;
            do { System.out.println("["+ (i++) +"] "+it.dato); it=it.next; } while(it!=head);
        }
    }

    /* ===== Demo ===== */
    public static void main(String[] args){
        PlaylistDoublyCircular dc = PlaylistDoublyCircular.create(); // Create()

        dc.insert(0, new Cancion("Blinding Lights", "The Weeknd", 3.2));
        dc.insert(1, new Cancion("Shape of You", "Ed Sheeran", 4.0));
        dc.insert(2, new Cancion("As It Was", "Harry Styles", 2.5));
        dc.insert(3, new Cancion("Flowers", "Miley Cyrus", 3.2));

        System.out.println("UNA vuelta desde head:");
        dc.mostrarUnaVueltaDesdeHead();

        System.out.println("\nGet(2): " + dc.get(2));
        System.out.println("FindIndex('As It Was'): " + dc.findIndex(new Cancion("As It Was","Harry Styles",0)));

        dc.update(1, new Cancion("Levitating", "Dua Lipa", 3.4));
        System.out.println("\nDespués de Update(1):");
        dc.mostrarUnaVueltaDesdeHead();

        dc.delete(0);
        System.out.println("\nDespués de Delete(0):");
        dc.mostrarUnaVueltaDesdeHead();
    }
}
