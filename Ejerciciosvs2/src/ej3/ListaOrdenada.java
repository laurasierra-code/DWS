package ej3;

import java.util.ArrayList;

public class ListaOrdenada<E extends Comparable<E>> {

    private ArrayList<E> lista;
    
    public ListaOrdenada() {
        lista = new ArrayList<E>();
    }

    public void add(E o) {
        int i = 0;
        while (i < lista.size() && lista.get(i).compareTo(o) < 0) {
            i++;
        }
        lista.add(i, o);
    }

    public E get(int index) {
        return lista.get(index);
    }

    public int size(){
        return lista.size();
    }

    public boolean isEmpty() {
        return lista.isEmpty();
    }

    boolean remove(E o) {
        return lista.remove(o);
    }

    public int indexOf(E o) {
        return lista.indexOf(o);
    }

    public String toString() {
        return lista.toString();
    }

}
