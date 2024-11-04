package Algoritmos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.lang.Object;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author javi
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        int casoN;
        int algoritmo = 0;
        int tamArray = 10;
        boolean salir = false;
        
        Punto[] puntos = new Punto[tamArray];
        
        String[] algoritmos = new String[]{"Divide y venceras","Voraz"};
        do {            
            System.out.println("\t\t\t\t\t\tAlgoritmo: " + algoritmos[algoritmo]);
            System.out.println("1. Generar Array.");
            System.out.println("2. Mostrar Array.");
            System.out.println("3. Dist. Busqueda Exhaustiva");
            System.out.println("4. Mostrar dataset actual");
            System.out.println("6. Leer DataSet");
            System.out.println("7. Salir");
            Scanner entrada = new Scanner(System.in);
            casoN = entrada.nextInt();
            
            switch (casoN) {
                case 1 -> {
                    
                    for (int i = 0; i < puntos.length; i++) {
                        puntos[i] = new Punto(Math.random(),Math.random());
                    }
                }

                case 2 ->{
                    for (int i = 0; i < puntos.length; i++) {
                        System.out.println("Punto " + (i+1) + ": " + puntos[i].x + ", " + puntos[i].y);
                    }
                }
                case 3 -> {
                    System.out.println("Distantia 1 y 2: " + puntos[0].distancia(puntos[1]));
                }   
                case 4 ->{
                    double aux;
                    aux = Algoritmos.exhaustivo(puntos);
                    System.out.println(aux);
                }
                case 6 -> {
                    lecturaArchivo();
                }
                case 7 ->{
                    salir = true;
                }
                default -> throw new AssertionError();
            }
        } while (!salir);
    }
    
    
    public static void lecturaArchivo() throws IOException{
        File fichero = new File("dataset_amc_1920/berlin52.tsp");
//        File fichero = new File("G:\\Mi unidad\\03_Universidad\\1er Cuatri\\AMC\\AMCPrac1\\dataset_amc_1920");
        FileReader fr = new FileReader(fichero);
        BufferedReader br = new BufferedReader(fr);
        String linea = null;    
        try {
            for (int i = 0; i < 4; i++) {
                linea = br.readLine();
            }
            String[] valores = linea.split(": ");
             System.out.println("El valor de esta linea es: " + valores[1]);
        } catch(FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        } finally {
            // Cerrar streams
            br.close();
            fr.close();
        }
    }
}





