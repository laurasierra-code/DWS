package ej2;

public class Matriz<T> {
    private T[][] matriz;
    private int filas;
    private int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        matriz = (T[][]) new Object[filas][columnas];
    }

    public void set(int fila, int columna, T elemento) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            matriz[fila][columna] = elemento;
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de los límites de la matriz.");
        }
    }

    public T get(int fila, int columna) {
        if (fila >= 0 && fila < filas && columna >= 0 && columna < columnas) {
            return matriz[fila][columna];
        } else {
            throw new IndexOutOfBoundsException("Índice fuera de los límites de la matriz.");
        }
    }

    public int Filas() {
        return filas;
    }

    public int Columnas() {
        return columnas;
    }

    public String toString() {

        String resultado = "";
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                resultado += matriz[i][j] + " ";
            }
            resultado += "\n";
        }
        return resultado;
    }

}
