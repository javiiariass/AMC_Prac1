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
    private static int contador = 0;
    
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
            throw new Exception("Exhaustivo: Debe haber al menos dos puntos para calcular la distancia");


        double auxDistancia;
        parejaPuntos pareja= new parejaPuntos(
                p.get(inicio), 
                p.get(inicio+1), 
                p.get(inicio).distancia(p.get(inicio+1)));


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
                contador++;

            }
        }

        return pareja;
}
    
    //La idea del exhaustivo con poda es que, al tener ordenados los puntos,
    //si la distancia entre las coordenadas de un eje de dos puntos es mayor a la
    //distancia menor calculada, no tenemos que seguir iterando con ese punto
    public static parejaPuntos exhaustivoPoda(ArrayList<Punto> p) throws Exception{
        return exhaustivoPoda(p,0, p.size()-1);
        //ponemos la distancia como el máximo valor posible
        // double auxDistancia;
        // parejaPuntos pareja= new parejaPuntos(
        //         p.getFirst(), 
        //         p.get(1), 
        //         p.getFirst().distancia(p.get(1)));
        // double distanciaX;
        
        // for (int i = 0; i < p.size(); i++) {
        //     for (int j = i+1; j < p.size(); j++) {
                
        //         //Si la distancia entre las coordenadas del eje de los dos puntos son ya mayores o igual
        //         //a la distancia mínima calculada, dejamos de iterar con ese (primer)punto y pasamos al siguiente
        //         distanciaX = Math.abs((p.get(j).getX() - p.get(i).getX()));
        //         if( distanciaX >= pareja.getDistancia())
        //             break;
                
        //         auxDistancia = p.get(i).distancia(p.get(j));
        //         //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
        //         pareja.setCalculadas(pareja.getCalculadas()+1);
        //         if (auxDistancia < pareja.getDistancia()){
        //             pareja.setPunto1(p.get(i));
        //             pareja.setPunto2(p.get(j));
        //             pareja.setDistancia(auxDistancia);
        //         }
                    
        //     }
        // }
        
        //return pareja;
       
    }
    
    public static parejaPuntos exhaustivoPoda(ArrayList<Punto> p, int izquierda, int derecha) throws Exception{
        
        if(izquierda==derecha)
            throw new Exception("Exhaustivo: Debe haber al menos dos puntos para calcular la distancia");
        
        //asumimos que la distancia mínima es la primera pareja de puntos
        //Aunque en el doble bucle, volvemos a compararla, esta primera distancia calculada no
        //la contaremos para el total por calcularla 2 veces
        double auxDistancia;
        
        parejaPuntos pareja= new parejaPuntos(
                p.get(izquierda), 
                p.get(izquierda+1), 
                p.get(izquierda).distancia(p.get(izquierda+1)));
        double distanciaX;
        

        //? ¿Qué sería más eficiente? lo que he hecho o poner un if en el doble bucle para cuando i==iz && j ==i+1 -> continúe
        //? Así se añadiría una op elemental en cada iteración. Sería menos eficiente, ¿no? 
        for (int i = izquierda; i < derecha; i++) {
            for (int j = i+1; j <= derecha; j++) {
                
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
    
    public static parejaPuntos divideYVenceras(ArrayList<Punto> puntos) throws Exception {
        return divideYVenceras(puntos, 0, puntos.size() - 1);
    }

    /**
     * @param puntos
     * @param izquierda
     * @param derecha
     * @return
     * @throws Exception
     */
    public static parejaPuntos divideYVenceras(ArrayList<Punto> puntos, int izquierda, int derecha) throws Exception {

        if(izquierda==derecha)
            throw new Exception("Exhaustivo: Debe haber al menos dos puntos para calcular la distancia");
        if(izquierda == 0 && (derecha == puntos.size()-1))
            contador=0;
        //Cuando sean 3 o 2 puntos -> exhaustivo
        if (derecha - izquierda < 3)
            return exhaustivo(puntos, izquierda, derecha);

        int medio = (izquierda + derecha) / 2;
        System.out.println("----------------");
        parejaPuntos distanciaIzquierda = divideYVenceras(puntos, izquierda, medio);
        System.out.println("caldulados izda: " + distanciaIzquierda.getCalculadas());
        parejaPuntos distanciaDerecha = divideYVenceras(puntos, medio + 1, derecha);
        System.out.println("caldulados Dcha: " + distanciaDerecha.getCalculadas());
        //Nos quedamos con la menor distancia 
        parejaPuntos distanciaMinima; // = distanciaIzquierda.getDistancia() < distanciaDerecha.getDistancia() ? distanciaIzquierda : distanciaDerecha;
        
        if (distanciaIzquierda.getDistancia() < distanciaDerecha.getDistancia()){
            distanciaMinima = distanciaIzquierda;
            
            //Sumamos el número de distancias calculadas para mostrar el total
            distanciaMinima.setCalculadas(distanciaMinima.getCalculadas() + distanciaDerecha.getCalculadas());
        }else{
            distanciaMinima = distanciaDerecha;
            
            //Sumamos el número de distancias calculadas para mostrar el total
            distanciaMinima.setCalculadas(distanciaMinima.getCalculadas() + distanciaIzquierda.getCalculadas());
        }
        System.out.println("Suma: " +distanciaMinima.getCalculadas()+"\n----------------------");


        // ArrayList<Punto> franja = new ArrayList<>();
        // for (int i = izquierda; i <= derecha; i++) {
        //     if (Math.abs(puntos.get(i).getX() - puntos.get(medio).getX()) < distanciaMinima.getDistancia()) {
        //         franja.add(puntos.get(i));
        //     }
        // }

        //Obtenemos los elementos de la franja cental cuya distancia en X con respecto a m sea menor que distanciaMinima
        int i,j;
        double auxDistancia;
        
        
        //Puntos de la franja Izquierda
        for (i = medio; i >= izquierda; i--) {
            //si la distancia en x es igual a la mínima, en el mejor caso su 
            //distancia sería igual a la mínima actual -> no usamos ese punto
            
            //Tenemos que comparar la distancia del primer punto de la derecha con los puntos de la izda
            if (Math.abs( puntos.get(medio + 1).getX() - puntos.get(i).getX() ) >= distanciaMinima.getDistancia())
                break;
            
        }

        //Puntos de la franja Derecha
        for(j = medio + 1; j <= derecha; j++){
            //si la distancia en x es igual a la mínima, en el mejor caso su 
            //distancia sería igual a la mínima actual -> no usamos ese punto 
            if (Math.abs( puntos.get(medio).getX() - puntos.get(j).getX() ) >= distanciaMinima.getDistancia())
                break;
            
                
        }

        //Realizamos las comparaciones de las distancias. Podamos para ahorrar iteraciones
        //Si i==medio && j==medio+1 -> no hay ningun punto en la franja
        if(i!=medio || j!=(medio+1)){
            for(int a = medio; a > i; a--){
                for(int b = medio+1; b < j; b++){
                    //Cuando la distanciaX entre los dos puntos sea mayor o igual a distanciaMinima -> podamos
                    if( Math.abs(puntos.get(b).getX() - puntos.get(a).getX()) >= distanciaMinima.getDistancia() )
                        break;
                    else{
                        auxDistancia = puntos.get(a).distancia(puntos.get(b));
                        //Incrementamos contador de distancias calculadas
                        distanciaMinima.setCalculadas(distanciaMinima.getCalculadas()+1);
                        contador++;
                        //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
                        if (auxDistancia < distanciaMinima.getDistancia()){
                            distanciaMinima.setPunto1(puntos.get(a));
                            distanciaMinima.setPunto2(puntos.get(b));
                            distanciaMinima.setDistancia(auxDistancia);
                        }
                    }
                }
            }
        }
        
        if(izquierda==0 && derecha ==(puntos.size()-1))
            distanciaMinima.setCalculadas(contador);
        return distanciaMinima;

    }
 
    // public static parejaPuntos dyVMejorado(ArrayList<Punto> arrayList p) {
    //     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    // }
    
    // Función principal para ordenar el arreglo usando QuickSort
    public static void quickSort(ArrayList<Punto> puntos, int izquierda, int derecha) {
        
        if (izquierda < derecha) {
            Punto pivote = puntos.get(izquierda);
            int i = izquierda;
            int j = derecha;

            while (i <= j) {
                while (puntos.get(i).getX() < pivote.getX()) {
                    i++;
                }
                while (puntos.get(j).getX() > pivote.getX()) {
                    j--;
                }
                if (i <= j) {
                    Punto temp = puntos.get(i);
                    puntos.set(i, puntos.get(j));
                    puntos.set(j, temp);
                    i++;
                    j--;
                }
            }

            if (izquierda < j)
                quickSort(puntos, izquierda, j);
            if (i < derecha)
                quickSort(puntos, i, derecha);
        }
    }
    
}
