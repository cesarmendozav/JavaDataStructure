package com.mycompany.app;

public class ArrayInventory {
private String[] data;
private int count; // elementos válidos

// create(capacity)
public ArrayInventory(int capacity) {
data = new String[capacity];
count = 0;
}

public int size() { return count; }

// get(index)
public String get(int index) {
if (index < 0 || index >= count) throw new IndexOutOfBoundsException();
return data[index];
}

// find(value) -> retorna índice o -1
public int find(String value) {
for (int i = 0; i < count; i++) if ((data[i] == null && value == null) || (data[i] != null && data[i].equals(value))) return i;
return -1;
}

// insert(index, value) con corrimiento a la derecha
public boolean insert(int index, String value) {
if (count == data.length) return false;
if (index < 0 || index > count) return false;
for (int i = count; i > index; i--) data[i] = data[i - 1];
data[index] = value;
count++;
return true;
}

// delete(index) con corrimiento a la izquierda
public boolean delete(int index) {
if (index < 0 || index >= count) return false;
for (int i = index; i < count - 1; i++) data[i] = data[i + 1];
data[count - 1] = null;
count--;
return true;
}

// update(index, value)
public boolean update(int index, String value) {
if (index < 0 || index >= count) return false;
data[index] = value;
return true;
}

public static void main(String[] args) {
ArrayInventory inv = new ArrayInventory(5); // create
inv.insert(0,"Mouse");
inv.insert(1,"Teclado");
inv.insert(1,"USB"); // Mouse, USB, Teclado
System.out.println("size=" + inv.size());
System.out.println("get(1)=" + inv.get(1));
System.out.println("find(Mouse)=" + inv.find("Mouse"));
inv.update(0,"Mouse Gamer");
inv.delete(2); // borra Teclado
for (int i=0;i<inv.size();i++) System.out.println(i+": "+inv.get(i));
}
}