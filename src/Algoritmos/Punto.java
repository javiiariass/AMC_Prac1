/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author javi
 */
public class Punto {
    private double x,y;
    private final int id;

    /**
     *
     * @return devuelve coordenada X del punto
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return devuelve coordenada Y del punto
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @return devuelve id del punto
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param x coordenada X
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     *
     * @param y coordenada Y
     */
    public void setY(double y) {
        this.y = y;
    }
    
    /**
     * Constructor
     * @param id id del punto
     * @param x coordenada X
     * @param y coordenada Y
     */
    public Punto(int id, double x, double y){
        this.x =x;
        this.y=y;
        this.id=id;
    }
    
    /**
     *
     * @param p Segundo punto para calculo de distancia euclidea 
     * @return distancia euclidea de los dos puntos
     */
    public double distancia(Punto p){
        return (sqrt((x-p.x)*(x-p.x)+(y-p.y)*(y-p.y)));
    }

    /**
     *
     * @return "id x  y"
     */
    @Override
    public String toString() {
        return id + " " + x + "\t" + y;
    }
    
    

    /**
     * Genera un array de objetos tipo Punto
     * <b>Si mimaX==true todos los puntos se generan en la misma coordenada X
     * @param puntos Array en el que aniadir los puntos
     * @param numPuntos Numero de puntos a generar
     * @param mismaX true si peor caso activado (todos los puntos en la misma X)
     * @param decPrecision numero de decimales maximo que deben tener los puntos
     */
    public static void rellenarPuntos(ArrayList<Punto> puntos, int numPuntos, boolean mismaX, int decPrecision) {
        Random rand = new Random();
        double x;
        double y;
        // Formatear las coordenadas con la precisión especificada en string
        // Es necesario usar Locale.Us para poder usar parseDouble -> no acepta ',' como separador decimal
        // f indica que va a ser num coma flotante (double/float)
        String xFormateadoStr;
        String yFormateadoStr;
        //String xFormateadoStr = String.format(Locale.US,"%." + decPrecision + "f", x);
        //String yFormateadoStr = String.format(Locale.US,"%." + decPrecision + "f", y);
        double xFormateado;
        double yFormateado;
        
        // Pasamos de string a double manteniendo la precisión de decimales
        //double xFormateado = Double.parseDouble(xFormateadoStr);
        //double yFormateado = Double.parseDouble(yFormateadoStr);

        
        
        
        if (mismaX) { // Peor caso
            
            x = 1;
            for (int i = 0; i < numPuntos; i++) {
                double aux1 = rand.nextInt(1000) + 7; // Aleatorio entre 7 y 1006
                y = aux1 / ((double) i + 1 + i * 0.100); 
                int num = rand.nextInt(3); // Aleatorio entre 0 y 2
                y += ((i % 500) - num * (rand.nextInt(100))); // Modifica y con otro valor aleatorio
                
                //Ahora formateamos el valor aleatorio generado para eje Y
                yFormateadoStr = String.format(Locale.US,"%." + decPrecision + "f", y);
                yFormateado = Double.parseDouble(yFormateadoStr);
                
                // Añadimos el punto generado a la lista de puntos
                puntos.add(new Punto(i+1, x, yFormateado));
            }
        } else { // Caso medio
            //
            for (int i = 0; i < numPuntos; i++) {
                int num = rand.nextInt(4000) + 1; // Aleatorio entre 1 y 4000
                int den = rand.nextInt(11) + 7; // Aleatorio entre 7 y 17
                x = num / ((double) den + 0.37);
                y = (rand.nextInt(4000) + 1) / ((double) (rand.nextInt(11) + 7) + 0.37);
                
                // Formatear las coordenadas con la precisión especificada en string
                // Es necesario usar Locale.Us para poder usar parseDouble -> no acepta ',' como separador decimal
                xFormateadoStr = String.format(Locale.US,"%." + decPrecision + "f", x);
                yFormateadoStr = String.format(Locale.US,"%." + decPrecision + "f", y);

                // Pasamos de string a double manteniendo la precisión de decimales
                xFormateado = Double.parseDouble(xFormateadoStr);
                yFormateado = Double.parseDouble(yFormateadoStr);
                
                
                
                // Añadimos el punto generado a la lista de puntos
                puntos.add(new Punto(i+1, xFormateado, yFormateado));

            }
        }
    } 
}


