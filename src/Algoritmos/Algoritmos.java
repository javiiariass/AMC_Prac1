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
    public static parejaPuntos exhaustivo(ArrayList<Punto> puntos, int inicio, int fin) throws Exception{


        if(inicio==fin)
            throw new Exception("Exhaustivo: Debe haber al menos dos puntos para calcular la distancia");


        double auxDistancia;
        parejaPuntos distanciaMinima= new parejaPuntos(
                puntos.get(inicio), 
                puntos.get(inicio+1), 
                puntos.get(inicio).distancia(puntos.get(inicio+1)));


        for (int i = inicio; i < fin; i++) {
            for (int j = i+1; j <= fin; j++) {
                auxDistancia = puntos.get(i).distancia(puntos.get(j));
                //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
                
                distanciaMinima.setCalculadas(distanciaMinima.getCalculadas()+1);
                if (auxDistancia < distanciaMinima.getDistancia()){
                    distanciaMinima = new parejaPuntos(
                                puntos.get(i),
                                puntos.get(j),
                                auxDistancia,
                                distanciaMinima.getCalculadas());
                }
            }
        }

        return distanciaMinima;
}
    
    //La idea del exhaustivo con poda es que, al tener ordenados los puntos,
    //si la distancia entre las coordenadas de un eje de dos puntos es mayor a la
    //distancia menor calculada, no tenemos que seguir iterando con ese punto
    public static parejaPuntos exhaustivoPoda(ArrayList<Punto> p) throws Exception{
        return exhaustivoPoda(p,0, p.size()-1);       
    }
    
    public static parejaPuntos exhaustivoPoda(ArrayList<Punto> puntos, int izquierda, int derecha) throws Exception{
        
        if(izquierda==derecha)
            throw new Exception("Exhaustivo Poda: Debe haber al menos dos puntos para calcular la distancia");
        
        //asumimos que la distancia mínima es la primera pareja de puntos
        //Aunque en el doble bucle, volvemos a compararla, esta primera distancia calculada no
        //la contaremos para el total por calcularla 2 veces
        double auxDistancia;
        
        parejaPuntos distanciaMinima= new parejaPuntos(
                puntos.get(izquierda), 
                puntos.get(izquierda+1), 
                puntos.get(izquierda).distancia(puntos.get(izquierda+1)));
        double distanciaX;
        

        //? ¿Qué sería más eficiente? lo que he hecho o poner un if en el doble bucle para cuando i==iz && j ==i+1 -> continúe
        //? Así se añadiría una op elemental en cada iteración. Sería menos eficiente, ¿no? 
        for (int i = izquierda; i < derecha; i++) {
            for (int j = i+1; j <= derecha; j++) {
                
                //Si la distancia entre las coordenadas del eje de los dos puntos son ya mayores o igual
                //a la distancia mínima calculada, dejamos de iterar con ese (primer)punto y pasamos al siguiente
                distanciaX = Math.abs((puntos.get(j).getX() - puntos.get(i).getX()));
                if( distanciaX >= distanciaMinima.getDistancia())
                    break;
                
                auxDistancia = puntos.get(i).distancia(puntos.get(j));
                //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
                distanciaMinima.setCalculadas(distanciaMinima.getCalculadas()+1);
                if (auxDistancia < distanciaMinima.getDistancia()){
                    distanciaMinima = new parejaPuntos(
                                puntos.get(i),
                                puntos.get(j),
                                auxDistancia,
                                distanciaMinima.getCalculadas());
                }
                    
            }
        }
        
        return distanciaMinima;
       
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
            throw new Exception("DyV: Debe haber al menos dos puntos para calcular la distancia");
        
        //Cuando sean 3 o 2 puntos -> exhaustivo
        if (derecha - izquierda < 3)
            return exhaustivo(puntos, izquierda, derecha);

        int medio = (izquierda + derecha) / 2;
        parejaPuntos distanciaIzquierda = divideYVenceras(puntos, izquierda, medio);
        parejaPuntos distanciaDerecha = divideYVenceras(puntos, medio + 1, derecha);
        
        
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
                        
                        //Si la distancia calculada es menor a la menor actual, reemplazamos valores de la pareja menor
                        if (auxDistancia < distanciaMinima.getDistancia()){
                            distanciaMinima = new parejaPuntos(
                                puntos.get(a),
                                puntos.get(b),
                                auxDistancia,
                                distanciaMinima.getCalculadas());
                        }
                    }
                }
            }
        }
        
       
        return distanciaMinima;

    }
 
    /**
     * 
     * @param puntos
     * @return
     * @throws Exception
     */
    public static parejaPuntos divideYVencerasMejorado(ArrayList<Punto> puntos) throws Exception {
        return divideYVencerasMejorado(puntos, 0, puntos.size() - 1);
    }

    /**
     * @param puntos
     * @param izquierda
     * @param derecha
     * @return
     * @throws Exception
     */
    public static parejaPuntos divideYVencerasMejorado(ArrayList<Punto> puntos,int izquierda, int derecha) throws Exception {
        if (derecha - izquierda < 3) {
            // Caso base: si el subarreglo tiene 3 puntos o menos, usar búsqueda exhaustiva
            return exhaustivo(puntos, izquierda, derecha);
         }
 
 
 
        // Dividir el conjunto de puntos en dos partes
        int medio = (izquierda + derecha) / 2;
         
 
        // Dividir recursivamente en dos subproblemas
        parejaPuntos resultadoIzquierda = divideYVencerasMejorado(puntos, izquierda, medio);
        parejaPuntos resultadoDerecha = divideYVencerasMejorado(puntos, medio + 1, derecha);

        // Obtener el mejor resultado entre ambos subproblemas
        //parejaPuntos mejorResultado = mejorDe(resultadoIzquierda, resultadoDerecha);
        
        parejaPuntos mejorResultado;
        if(resultadoIzquierda.getDistancia()< resultadoDerecha.getDistancia()){
            mejorResultado = resultadoIzquierda;
            mejorResultado.setCalculadas(mejorResultado.getCalculadas() + resultadoDerecha.getCalculadas());
        }else{
            mejorResultado = resultadoDerecha;
            mejorResultado.setCalculadas(mejorResultado.getCalculadas() + resultadoIzquierda.getCalculadas());
        }

        // Crear la franja central
        ArrayList<Punto> franjaCentral = new ArrayList<>();
        for (int i = izquierda; i <= derecha; i++) {
            if (Math.abs(puntos.get(i).getX() - puntos.get(medio).getX()) < mejorResultado.getDistancia()) 
                franjaCentral.add(puntos.get(i));
        }

        // Ordenar la franja central por la coordenada Y
    
        quickSortY(franjaCentral, 0, franjaCentral.size()-1);
    
        // Comparar los puntos en la franja central
    
        for (int i = 0; i < franjaCentral.size()-1; i++) {
            for (int j = i + 1; j < franjaCentral.size() && franjaCentral.get(j).getY() - franjaCentral.get(i).getY() < mejorResultado.getDistancia(); j++) {
                mejorResultado.setCalculadas(mejorResultado.getCalculadas() + 1);
                double distancia = franjaCentral.get(i).distancia(franjaCentral.get(j));
                if (distancia < mejorResultado.getDistancia()) 
                    mejorResultado = new parejaPuntos(franjaCentral.get(i), franjaCentral.get(j), distancia, mejorResultado.getCalculadas());                 
            }
        }

        return mejorResultado;
     }

   
    /**
     * Ordena el array de puntos por la coordenada X de manera creciente
     * @param puntos
     * @param izquierda
     * @param derecha
     */
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

    

    /**
     * Ordena el array de puntos por la coordenada Y de manera creciente
     * @param puntos
     * @param izquierda
     * @param derecha
     */
    public static void quickSortY(ArrayList<Punto> puntos, int izquierda, int derecha) {
        
        if (izquierda < derecha) {
            Punto pivote = puntos.get(izquierda);
            int i = izquierda;
            int j = derecha;

            while (i <= j) {
                while (puntos.get(i).getY() < pivote.getY()) {
                    i++;
                }
                while (puntos.get(j).getY() > pivote.getY()) {
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
                quickSortY(puntos, izquierda, j);
            if (i < derecha)
                quickSortY(puntos, i, derecha);
        }
    }
    
}
