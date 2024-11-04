/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

/**
 *
 * @author javiiariass
 */

public class Algoritmos {
    public static double exhaustivo(Punto[] p){
        double dMin=1;
        
        for (int i = 0; i < p.length; i++) {
            for (int j = i+1; j < p.length; j++) {
                System.out.println("dis: " + i + ", " + j);
            }
        }
        
        return dMin;
    }
 
    
    
}
