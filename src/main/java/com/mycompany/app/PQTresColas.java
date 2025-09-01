package com.mycompany.app;

import java.util.*;

class PQTresColas {
    Queue<String> q1=new LinkedList<>(), q2=new LinkedList<>(), q3=new LinkedList<>();
    void push(String nombre, int prioridad){
        if(prioridad==1) q1.add(nombre);
        else if(prioridad==2) q2.add(nombre);
        else q3.add(nombre);
    }
    String poll(){
        if(!q1.isEmpty()) return q1.poll();
        if(!q2.isEmpty()) return q2.poll();
        if(!q3.isEmpty()) return q3.poll();
        return null;
    }
    String peek(){
        if(!q1.isEmpty()) return q1.peek();
        if(!q2.isEmpty()) return q2.peek();
        if(!q3.isEmpty()) return q3.peek();
        return null;
    }
}
