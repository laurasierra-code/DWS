package ej3;

public class Main {
    public static void main(String[] args) {
        ListaOrdenada<Integer> lista = new ListaOrdenada<>();
        lista.add(5);
        lista.add(2);
        lista.add(8);
        lista.add(1);
        System.out.println("Lista ordenada: " + lista);
        System.out.println("Tamaño de la lista: " + lista.size());
        System.out.println("Elemento en el índice 2: " + lista.get(2));
        System.out.println("Índice del elemento 5: " + lista.indexOf(5));
        System.out.println("¿La lista está vacía? " + lista.isEmpty());
        lista.remove(2);
        System.out.println("Lista después de eliminar el elemento 2: " + lista);
    }
}
