package ej1;

import java.util.LinkedList;

public class Pila<T>{

    LinkedList<T> lista = new LinkedList<T>();

    public boolean estaVacia(LinkedList<T> lista) {
        return lista.isEmpty();
    }

    public T extraer(LinkedList<T> lista) {
        return lista.removeLast();
    }

    public T primero(LinkedList<T> lista) {
        return lista.getLast();
    }
    
    public void añadir(T elemento) {
        lista.addLast(elemento);
    }

    public void toString(LinkedList<T> lista) {
        System.out.println(lista);
    }

}