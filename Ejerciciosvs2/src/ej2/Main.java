package ej2;

public class Main {
    public static void main(String[] args) {
        // Crear una matriz de enteros de 3 filas y 4 columnas
        Matriz<Integer> matrizEnteros = new Matriz<>(3, 4);

        // Establecer elementos en la matriz
        matrizEnteros.set(0, 0, 1);
        matrizEnteros.set(0, 1, 2);
        matrizEnteros.set(0, 2, 3);
        matrizEnteros.set(0, 3, 4);
        matrizEnteros.set(1, 0, 5);
        matrizEnteros.set(1, 1, 6);
        matrizEnteros.set(1, 2, 7);
        matrizEnteros.set(1, 3, 8);
        matrizEnteros.set(2, 0, 9);
        matrizEnteros.set(2, 1, 10);
        matrizEnteros.set(2, 2, 11);
        matrizEnteros.set(2, 3, 12);

        // Imprimir los elementos de la matriz
        for (int i = 0; i < matrizEnteros.Filas(); i++) {
            for (int j = 0; j < matrizEnteros.Columnas(); j++) {
                System.out.print(matrizEnteros.get(i, j) + " ");
            }
            System.out.println();
        }
    }  
}
