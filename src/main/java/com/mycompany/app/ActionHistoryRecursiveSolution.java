package com.mycompany.app;

import java.util.*;

/**
 * SOLUCIÓN CON RECURSIVIDAD OBLIGATORIA
 * Reto 2: Historial de acciones (Stack LIFO con "deshacer")
 *
 * Estructura base: Deque<Action> como pila (ArrayDeque).
 * Métodos recursivos obligatorios en esta solución:
 *  - printStackTopToBottomRecursive()   -> imprime de tope a base SIN bucles y restaurando el estado.
 *  - undoLastKRecursive(int k)          -> deshace recursivamente las últimas k acciones.
 *  - clearAllRecursive()                -> deja la pila vacía mediante recursión.
 *  - cloneTo(Deque<Action> target)      -> clona recursivamente la pila a otra, preservando orden.
 */
public class ActionHistoryRecursiveSolution {

    private final Deque<Action> stack = new ArrayDeque<>();

    public static class Action {
        public final int id;
        public final String descripcion;

        public Action(int id, String descripcion) {
            this.id = id;
            this.descripcion = descripcion;
        }

        @Override
        public String toString() {
            return descripcion;
        }
    }

    /** Agrega una acción al tope (push). */
    public void pushAction(Action a) {
        stack.push(a);
    }

    /** Deshace la última acción (pop); retorna null si la pila está vacía. */
    public Action undoLastAction() {
        return stack.isEmpty() ? null : stack.pop();
    }

    /** ---------- RECURSIVIDAD OBLIGATORIA ---------- */

    /** Imprime de tope a base SIN bucles, restaurando la pila al final. */
    public void printStackTopToBottomRecursive() {
        System.out.print("Acciones actuales (tope→base): [");
        boolean[] first = new boolean[]{true};
        printStackHelper(first);
        System.out.println("]");
    }

    private void printStackHelper(boolean[] first) {
        if (stack.isEmpty()) return;
        Action top = stack.pop();
        if (!first[0]) System.out.print(", ");
        System.out.print(top.descripcion);
        first[0] = false;
        printStackHelper(first);
        stack.push(top); // restaurar estado
    }

    /** Deshace recursivamente las últimas k acciones. */
    public void undoLastKRecursive(int k) {
        if (k <= 0 || stack.isEmpty()) return;
        Action undone = stack.pop();
        System.out.println("Deshacer acción: " + undone.descripcion);
        undoLastKRecursive(k - 1);
    }

    /** Vacía completamente la pila de forma recursiva. */
    public void clearAllRecursive() {
        if (stack.isEmpty()) return;
        stack.pop();
        clearAllRecursive();
    }

    /** Clona recursivamente esta pila hacia 'target' preservando orden (tope queda tope). */
    public void cloneTo(Deque<Action> target) {
        cloneToHelper(target);
    }

    private void cloneToHelper(Deque<Action> target) {
        if (stack.isEmpty()) return;
        Action top = stack.pop();
        cloneToHelper(target);
        // cuando regresa, estamos del fondo al tope
        stack.push(top);     // restaurar
        target.push(top);    // replicar
    }

    /** Tamaño actual de la pila. */
    public int size() {
        return stack.size();
    }

    /** ¿Está vacía? */
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    /** Demostración en consola */
    public static void main(String[] args) {
        ActionHistoryRecursiveSolution history = new ActionHistoryRecursiveSolution();

        history.pushAction(new Action(1, "Escribir Hola"));
        history.pushAction(new Action(2, "Borrar o"));
        history.pushAction(new Action(3, "Escribir Mundo"));
        history.pushAction(new Action(4, "Cambiar fuente a Arial"));

        history.printStackTopToBottomRecursive(); // imprime sin bucles

        // Deshacer recursivamente 2 acciones
        history.undoLastKRecursive(2);
        history.printStackTopToBottomRecursive();

        // Clonar recursivamente
        Deque<Action> copia = new ArrayDeque<>();
        history.cloneTo(copia);
        System.out.println("Copia (tope→base) = " + copia);

        // Vaciar por recursión
        history.clearAllRecursive();
        history.printStackTopToBottomRecursive(); // []
        System.out.println("¿Vacía?: " + history.isEmpty());
    }
}