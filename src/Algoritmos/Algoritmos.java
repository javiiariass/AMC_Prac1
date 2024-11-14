/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Algoritmos;

import java.util.ArrayList;

/**
 *
 * @author javi
 */

public class Algoritmos {
    
    
    /**
     * Calcula la pareja de puntos con menos distancia de todo el ArrayList
     * @param p ArrayList de objetos tipo Punto
     * @return Devuelve la pareja de puntos con la distancia más corta
     * @throws java.lang.Exception
     */
    public static parejaPuntos exhaustivo(ArrayList<Punto> p) throws Exception{ 
        return exhaustivo(p, 0, p.size()-1);
    }
    
    /**
     * Calcula la pareja de puntos con menos distancia de un fragmento del ArrayList
     * <p> Ejemplo: para un ArrayList de 10 elementos -> inicio=4 && fin = 9, calcula
     * la pareja de puntos desde el 5o elemento hasta el último elemento del ArrayList
     * @param p ArrayList de objetos tipo Punto
     * @param inicio Indica el inicio del fragmento del ArrayList a iterar 
     * INCLUIDO EL ELEMENTO DEL INDICE ESPECIFICADO
     * @param fin Indica el final del fragmento del ArrayList a iterar
     * INCLUIDO EL ELEMENTO DEL INDICE ESPECIFICADO
     * @return Devuelve la pareja de puntos con la distancia más corta del fragmento del ArrayList
     * @throws java.lang.Exception
     */
    public static parejaPuntos exhaustivo(ArrayList<Punto> p, int inicio, int fin) throws Exception{


        if(inicio==fin)
            throw new Exception("Debe haber al menos dos puntos para calcular la distancia");


        double auxDistancia;
        parejaPuntos pareja= new parejaPuntos(
                p.getFirst(), 
                p.get(1), 
                p.getFirst().distancia(p.get(1)));


        for (int i = inicio; i < fin; i++) {
            for (int j = i+1; j <= fin; j++) {
                auxDistancia = p.get(i).distancia(p.get(j));
                //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor

                if (auxDistancia < pareja.getDistancia()){
                    pareja.setPunto1(p.get(i));
                    pareja.setPunto2(p.get(j));
                    pareja.setDistancia(auxDistancia);
                }
                pareja.setCalculadas(pareja.getCalculadas()+1);

            }
        }

        return pareja;
}
    
    //La idea del exhaustivo con poda es que, al tener ordenados los puntos,
    //si la distancia entre las coordenadas de un eje de dos puntos es mayor a la
    //distancia menor calculada, no tenemos que seguir iterando con ese punto
    public static parejaPuntos exhaustivoPoda(ArrayList<Punto> p){
        //ponemos la distancia como el máximo valor posible
        double auxDistancia;
        parejaPuntos pareja= new parejaPuntos(
                p.getFirst(), 
                p.get(1), 
                p.getFirst().distancia(p.get(1)));
        double distanciaX;
        
        for (int i = 0; i < p.size(); i++) {
            for (int j = i+1; j < p.size(); j++) {
                
                //Si la distancia entre las coordenadas del eje de los dos puntos son ya mayores o igual
                //a la distancia mínima calculada, dejamos de iterar con ese (primer)punto y pasamos al siguiente
                distanciaX = Math.abs((p.get(j).getX() - p.get(i).getX()));
                if( distanciaX >= pareja.getDistancia())
                    break;
                
                auxDistancia = p.get(i).distancia(p.get(j));
                //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
                pareja.setCalculadas(pareja.getCalculadas()+1);
                if (auxDistancia < pareja.getDistancia()){
                    pareja.setPunto1(p.get(i));
                    pareja.setPunto2(p.get(j));
                    pareja.setDistancia(auxDistancia);
                }
                    
            }
        }
        
        return pareja;
       
    }
    
    public static parejaPuntos exhaustivoPoda(ArrayList<Punto> p, int izquierda, int derecha){
        //ponemos la distancia como el máximo valor posible
        double auxDistancia;
        
        parejaPuntos pareja= new parejaPuntos(
                p.get(izquierda), 
                p.get(izquierda+1), 
                p.get(izquierda).distancia(p.get(izquierda+1)));
        double distanciaX;
        
        for (int i = izquierda; i < derecha; i++) {
            for (int j = i+1; j < derecha; j++) {
                
                //Si la distancia entre las coordenadas del eje de los dos puntos son ya mayores o igual
                //a la distancia mínima calculada, dejamos de iterar con ese (primer)punto y pasamos al siguiente
                distanciaX = Math.abs((p.get(j).getX() - p.get(i).getX()));
                if( distanciaX >= pareja.getDistancia())
                    break;
                
                auxDistancia = p.get(i).distancia(p.get(j));
                //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
                pareja.setCalculadas(pareja.getCalculadas()+1);
                if (auxDistancia < pareja.getDistancia()){
                    pareja.setPunto1(p.get(i));
                    pareja.setPunto2(p.get(j));
                    pareja.setDistancia(auxDistancia);
                }
                    
            }
        }
        
        return pareja;
       
    }
    
    public static parejaPuntos divideYVenceras(ArrayList<Punto> puntos, int izquierda, int derecha) {
//        
//        int nElementos = derecha - izquierda + 1;
//        if (nElementos>=4){
//            int puntoMedio = (izquierda + derecha)/2;
//            
//            // Calcular la distancia mínima en la mitad izquierda y en la mitad derecha
//            parejaPuntos distanciaIzquierda = divideYVenceras(puntos, izquierda, puntoMedio);
//            parejaPuntos distanciaDerecha = divideYVenceras(puntos, puntoMedio + 1, derecha);
//            
//            // Tomar la menor distancia de ambos lados
//            parejaPuntos distanciaMinima = distanciaIzquierda.getDistancia() < distanciaDerecha.getDistancia() ?
//                 distanciaIzquierda : distanciaDerecha;
//            
//            int izda = puntoMedio - (int)distanciaMinima.getDistancia();
//            int dcha = puntoMedio + (int)distanciaMinima.getDistancia();
//            
//            parejaPuntos disMediaMin = Algoritmos.exhaustivoPoda(puntos, izda, dcha);
//            
//           
//            
//        }else{
//            
//        }
//   
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 
    public static parejaPuntos dyVMejorado(ArrayList<Punto> arrayList) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

    

   
    
    
}
