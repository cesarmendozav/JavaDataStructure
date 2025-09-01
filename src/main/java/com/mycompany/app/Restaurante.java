package com.mycompany.app;

import java.util.*;

class Restaurante {
    Deque<String> express = new ArrayDeque<>(); // pila
    Queue<String> normal = new LinkedList<>();  // cola
    void pushExpress(String pedido){ express.push(pedido); }
    void enqueue(String pedido){ normal.add(pedido); }
    String nextOrder(){
        if(!express.isEmpty()) return express.pop();
        return normal.isEmpty()? null : normal.poll();
    }
}
