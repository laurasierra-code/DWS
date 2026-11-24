package ej1;

public class Main {

public void main(String[] args) {
    Pila<Object> pila = new Pila<>();
    pila.añadir(1);
    pila.añadir(2);
    pila.añadir(3);
    pila.añadir("Paconi");
    pila.toString(pila.lista);
    System.out.println("Primero: " + pila.primero(pila.lista));
    System.out.println("Extraer: " + pila.extraer(pila.lista));
    pila.toString(pila.lista);
    System.out.println("¿Está vacía? " + pila.estaVacia(pila.lista));
}
}
