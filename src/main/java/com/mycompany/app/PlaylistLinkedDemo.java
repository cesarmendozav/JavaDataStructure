package com.mycompany.app;

// PlaylistLinkedDemo.java
import java.util.Objects;

public class PlaylistLinkedDemo {

/* ===== Modelo ===== */
static class Cancion {
private final String titulo;
private final String artista;
private final double duracionMin;

public Cancion(String titulo, String artista, double duracionMin) {
this.titulo = Objects.requireNonNull(titulo);
this.artista = Objects.requireNonNull(artista);
this.duracionMin = duracionMin;
}
public String getTitulo() { return titulo; }

@Override
public String toString() {
return "🎵 " + titulo + " — " + artista + " (" + duracionMin + " min)";
}

/* Para Find(elemento): dos canciones “iguales” si coinciden título y artista */
@Override public boolean equals(Object o) {
if (this == o) return true;
if (!(o instanceof Cancion)) return false;
Cancion c = (Cancion) o;
return titulo.equals(c.titulo) && artista.equals(c.artista);
}
@Override public int hashCode() { return Objects.hash(titulo, artista); }
}

/* ===== Nodo simple ===== */
static class Nodo {
Cancion dato;
Nodo next;
Nodo(Cancion d) { this.dato = d; }
}

/* ===== Lista simplemente enlazada ===== */
static class PlaylistLinked {
private Nodo head, tail;
private int size;

private PlaylistLinked() {}
public static PlaylistLinked create() { return new PlaylistLinked(); } // Create()

public int size() { return size; }
public boolean isEmpty() { return head == null; }

/* --- Get(posición) --- */
public Cancion get(int pos) {
ensureNotEmpty();
Nodo n = nodeAt(pos); // valida rango
return n.dato;
}

/* --- Insert(posición, elemento) --- */
public void insert(int pos, Cancion e) {
if (pos < 0 || pos > size) {
throw new IndexOutOfBoundsException("La posición no existe en la lista: " + pos);
}
Nodo nuevo = new Nodo(e);
if (pos == 0) { // insertar al inicio
nuevo.next = head;
head = nuevo;
if (tail == null) tail = nuevo;
} else if (pos == size) { // insertar al final
if (tail == null) { head = tail = nuevo; }
else { tail.next = nuevo; tail = nuevo; }
} else { // insertar en medio
Nodo prev = nodeAt(pos - 1);
nuevo.next = prev.next;
prev.next = nuevo;
}
size++;
}

/* --- Delete(posición) --- */
public void delete(int pos) {
ensureNotEmpty();
if (pos < 0 || pos >= size) {
throw new IndexOutOfBoundsException("La posición no existe en la lista: " + pos);
}
if (pos == 0) { // borrar head
head = head.next;
if (head == null) tail = null;
} else {
Nodo prev = nodeAt(pos - 1);
Nodo target = prev.next;
prev.next = target.next;
if (target == tail) tail = prev;
}
size--;
}

/* --- Find(elemento) -> devuelve el nodo o null --- */
public Nodo find(Cancion e) {
Nodo it = head;
while (it != null) {
if (it.dato.equals(e)) return it;
it = it.next;
}
return null;
}

/* --- Update(posición, elemento) --- */
public void update(int pos, Cancion e) {
ensureNotEmpty();
Nodo n = nodeAt(pos);
n.dato = e;
}

/* Utilidades privadas */
private void ensureNotEmpty() {
if (isEmpty()) throw new IllegalStateException("La lista está vacía.");
}
private Nodo nodeAt(int index) {
if (index < 0 || index >= size) {
throw new IndexOutOfBoundsException("La posición no existe en la lista: " + index);
}
Nodo it = head;
for (int i = 0; i < index; i++) it = it.next;
return it;
}

/* Para la demo */
public void mostrar() {
if (isEmpty()) { System.out.println("(Lista vacía)"); return; }
Nodo it = head;
int i = 0;
while (it != null) {
System.out.println("[" + i + "] " + it.dato);
it = it.next; i++;
}
}
}

/* ===== Demo ===== */
public static void main(String[] args) {
PlaylistLinked pl = PlaylistLinked.create(); // Create()

// Insert(pos, elem)
pl.insert(0, new Cancion("Blinding Lights", "The Weeknd", 3.2)); // inicio
pl.insert(1, new Cancion("Shape of You", "Ed Sheeran", 4.0)); // final
pl.insert(2, new Cancion("As It Was", "Harry Styles", 2.5)); // final
pl.insert(1, new Cancion("Levitating", "Dua Lipa", 3.4)); // medio

System.out.println("Después de Insert:");
pl.mostrar();

// Get(pos)
System.out.println("\nGet(2): " + pl.get(2));

// Find(elemento)
Nodo found = pl.find(new Cancion("Shape of You", "Ed Sheeran", 0));
System.out.println("Find('Shape of You', Ed Sheeran): " + (found != null ? found.dato : "null"));

// Update(pos, elem)
pl.update(0, new Cancion("Flowers", "Miley Cyrus", 3.2));
System.out.println("\nDespués de Update(0, 'Flowers'):");
pl.mostrar();

// Delete(pos)
pl.delete(2);
System.out.println("\nDespués de Delete(2):");
pl.mostrar();
}
}