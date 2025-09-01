package com.mycompany.app;

class PQArrayDesordenado {
    static class Nodo { String nombre; int prioridad; Nodo(String n,int p){nombre=n;prioridad=p;} }
    Nodo[] a; int n=0;
    PQArrayDesordenado(int cap){ a=new Nodo[cap]; }
    void push(String nombre, int p){ a[n++]=new Nodo(nombre,p); }
    Nodo peek(){
        if(n==0) return null;
        int best=0;
        for(int i=1;i<n;i++) if(a[i].prioridad<a[best].prioridad) best=i;
        return a[best];
    }
    Nodo poll(){
        if(n==0) return null;
        int best=0;
        for(int i=1;i<n;i++) if(a[i].prioridad<a[best].prioridad) best=i;
        Nodo res=a[best];
        a[best]=a[n-1]; n--;
        return res;
    }
}
