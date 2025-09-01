package com.mycompany.app;

import java.util.*;
class PQStacks {
    Deque<String>[] ps = new Deque[3]; // 0: alta, 1: media, 2: baja
    { for(int i=0;i<3;i++) ps[i] = new ArrayDeque<>(); }
    void push(String x, int prioridad){ ps[prioridad-1].push(x); }
    String popPrioritario(){
        for(int i=0;i<3;i++) if(!ps[i].isEmpty()) return ps[i].pop();
        return null;
    }
}