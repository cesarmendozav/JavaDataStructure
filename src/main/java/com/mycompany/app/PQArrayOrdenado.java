package com.mycompany.app;

class PQArrayOrdenado {
    static class Nodo {
        String nombre; int prioridad;
        Nodo(String n, int p) { nombre=n; prioridad=p; }
    }
    Nodo[] a; int n=0;
    PQArrayOrdenado(int cap){ a=new Nodo[cap]; }
    void push(String nombre, int p){
        int i=n-1;
        while(i>=0 && a[i].prioridad>p){ a[i+1]=a[i]; i--; }
        a[i+1]=new Nodo(nombre,p); n++;
    }
    Nodo peek(){ return n==0? null : a[0]; }
    Nodo poll(){
        if(n==0) return null;
        Nodo res=a[0];
        for(int i=1;i<n;i++) a[i-1]=a[i];
        n--;
        return res;
    }
}
