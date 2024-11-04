/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;
import static java.lang.Math.sqrt;

/**
 *
 * @author javie
 */
public class Punto {
    public double x,y;
    
    
    public Punto(double x, double y){
        this.x =x;
        this.y=y;
    }
    
    public double distancia(Punto p){
        return (sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)));
    }
}


