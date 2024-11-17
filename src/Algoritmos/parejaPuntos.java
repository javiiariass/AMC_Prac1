/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

/**
 *
 * @author javi
 * 
 */
public class parejaPuntos {
    private Punto punto1, punto2;
    private double distancia;
    //Num de operaciones para calcular el menor.
    private int calculadas;

    public int getCalculadas() {
        return calculadas;
    }

    public void setCalculadas(int calculadas) {
        this.calculadas = calculadas;
    }

    public Punto getPunto1() {
        return punto1;
    }

    public Punto getPunto2() {
        return punto2;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setPunto1(Punto punto1) {
        this.punto1 = punto1;
    }

    public void setPunto2(Punto punto2) {
        this.punto2 = punto2;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }
    
    public parejaPuntos(Punto punto1, Punto punto2, double distancia){
        this.punto1=punto1;
        this.punto2=punto2;
        this.distancia=distancia;
        this.calculadas = 0;
    }

    public parejaPuntos(Punto punto1, Punto punto2, double distancia, int calculadas){
        this.punto1=punto1;
        this.punto2=punto2;
        this.distancia=distancia;
        this.calculadas = calculadas;
    }
    
    public parejaPuntos(){
        this.punto1=null;
        this.punto2=null;
        this.distancia=0;
        this.calculadas = 0;
    }
    
    @Override
    public String toString() {
        return punto1.getId() + " (" + punto1.getX() + ", " + punto1.getY() + ")\t" +
                punto2.getId() + " (" + punto2.getX() + ", " + punto2.getY() + ")\t" + 
                distancia + "\t" + calculadas;
    }
    
}
