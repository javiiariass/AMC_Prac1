/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import java.util.ArrayList;

/**
 *
 * @author javie
 */
public class QuickSort {
    
    // Función principal para ordenar el arreglo usando QuickSort
    public static void quickSort(ArrayList<Punto> puntos, int imin, int imax) {
        if (imin < imax) {
            // Índice de partición
            int pivote = particion(puntos, imin, imax);

            // Ordenar la parte izquierda y derecha
            quickSort(puntos, imin, pivote - 1);
            quickSort(puntos, pivote + 1, imax);
        }
    }

    // Función para encontrar el índice de partición (dividir el arreglo)
    private static int particion(ArrayList<Punto> puntos, int imin, int imax) {
        // Tomamos el último elemento como pivote
        Punto pivot = puntos.get(imax);
        int i = (imin - 1);  // Índice del elemento más pequeño

        // Ordenar los elementos en relación al pivote
        for (int j = imin; j < imax; j++) {
            // Si el punto actual es menor que el pivote (ordenar por coordenada X)
            if (puntos.get(j).getX() <= pivot.getX()) {
                i++;
                
                // Intercambiar puntos[i] y puntos[j]
                Punto temp = puntos.get(i);
                puntos.set(i, puntos.get(j));
                puntos.set(j, temp);
                
            }
        }

        // Intercambiar el pivote con el elemento que tiene el valor más pequeño
        // de los elementos mayores que el pivote
        Punto temp = puntos.get(i+1);
        puntos.set(i+1, puntos.get(imax));
        puntos.set(imax, temp);

        return i + 1;  // Retornar el índice del pivote
    }
}