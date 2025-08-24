package com.mycompany.app;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionStack {
private Deque<String> stack;

// create()
public ActionStack() { stack = new ArrayDeque<>(); }

public boolean isEmpty() { return stack.isEmpty(); }
public int size() { return stack.size(); }
public void push(String action) { stack.push(action); }
public String peek() { return stack.peek(); }
public String pop() { return stack.pop(); }

public static void main(String[] args) {
ActionStack s = new ActionStack(); // create
s.push("Negritas");
s.push("Cursivas");
System.out.println("peek=" + s.peek()); // Cursivas
System.out.println("pop=" + s.pop()); // Cursivas
System.out.println("size=" + s.size()); // 1
System.out.println("pop=" + s.pop()); // Negritas
System.out.println("isEmpty=" + s.isEmpty());
}
}