package Algoritmos;

import java.io.*;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author javi
 */
public class Application {
    
    private static final int PRECISION_MSEG=4;
    private static final int PRECISION_PUNTOS=10;
    private static final int PRECISION_DISTANCIA=8;
    //Número de tallas a calcular cuando compruebe algoritmos
    private static final int N_TALLAS = 8;
    private static boolean peorCaso;
    /**
     * @param args the command line arguments
     * //@throws java.io.IOException
     */
    public static void main(String[] args) {//throws IOException {
        
        int opcionMenu;
        
        peorCaso = false;
        boolean salir = false;
        ArrayList<Punto> puntos = new ArrayList<>();
        String nombreArchivoLeido = null;
        
        do {            
            System.out.println("""
                               
                               
                               -------------------------------------------------------
                                                                        Peor Caso:""" + (peorCaso == true ? " ON":" OFF"));
            System.out.println("1. Generar Array aleatorio");
            System.out.println("2. Mostrar Array cargado");
            System.out.println("3. Comprobar estrategias con dataset cargado");
            System.out.println("4. Comparar todas las estrategias");
            System.out.println("5. Estudiar dos estrategias");
            System.out.println("6. Leer DataSet");
            System.out.println("7. Activar/Desactivar peor caso (Todos los puntos en la misma coordenada X)");
            System.out.println("0. Salir");
            Scanner entrada = new Scanner(System.in);
            opcionMenu = entrada.nextInt();
            try {
                switch (opcionMenu) {

                    case 1 -> {
                        
                        //Antes de empezar a añadir elementos a la lista, la vacíamos
                        puntosVacios(puntos);
                        System.out.print("Introduce la talla del archivo a generar: ");
                        int talla = entrada.nextInt();
                        System.out.println("Creando nuevo dataset de puntos...");
                        Punto.rellenarPuntos(puntos, talla, peorCaso, 10);
                        
                        nombreArchivoLeido = "dataset" + puntos.size();
                        guardarEnArchivo(puntos,nombreArchivoLeido);
                        
                    }
                    case 2 ->{
                        for (Punto i : puntos) {
                            System.out.println("Punto " + i);
                            //System.out.println("Punto " + i.getId() + ": " + i.getX() + ", " + i.getY());
                        }
                    }
                    case 3 -> {
                        if(puntos.isEmpty()){
                            System.out.println("Aún no se ha cargado/generado ningún dataset en memoria");
                            break;
                        }
                        System.out.println("dataset seleccionado: " + nombreArchivoLeido);
                        comprobarEstrategias(puntos,true);
                        
                    }   
                    case 4 ->{
                        compararTodasEstrategias(N_TALLAS);
                    }
                    case 5-> {
                        System.out.println("""
                                           Introduce los algoritmos a comparar:
                                           1. Exhaustivo
                                           2. Exhaustivo poda
                                           3. DyV
                                           4. DyV mejorado
                                           """);
                        int alg1 = entrada.nextInt();
                        System.out.println("Segundo algoritmo: ");
                        int alg2 = entrada.nextInt();
                        comparaDosEstrategias(puntos, N_TALLAS, alg1, alg2);
                    }
                    case 6 -> {
                        entrada.nextLine(); // Limpiamos el buffer antes de leer el nombre del archivo
                        System.out.print("Introduce el nombre del archivo a cargar (por ejemplo, berlin52): ");
                        String nombreArchivo = entrada.nextLine();
                        puntosVacios(puntos);
                        puntos = lecturaArchivo(nombreArchivo);
                        nombreArchivoLeido = nombreArchivo;
                    }
                    case 7 ->{ 
                        peorCaso = (peorCaso != true); 
                        
                    }
                    case 0 ->{
                        salir = true;
                    }
                    default -> { 
                        System.out.println("Elección no válida");
                        sleep(1000);
                    }
                }
                
            } catch (Exception e) {
                System.err.println("Error " + e.getMessage());
            }
            
        } while (!salir);
    }
    
    
    /**
     * 
     * @param rutaArchivo introducir nombre del archivo.
     * <p> Usa ruta relativa desde la carpeta raíz del proyecto ->"dataset/nombreDelDataset.tsp".
     * <p> Introducir UNICAMENTE NOMBRE de archivo ubicado en carpeta datasets del proyecto. El programa se encarga de ubicarlo y 
     * añadirle la extensión .tsp
     * @return Devuelve una lista de los puntos leidos del fichero o una lista vacía en su defecto 
     * //@throws IOException 
     */
    public static ArrayList<Punto> lecturaArchivo(String rutaArchivo) {//throws IOException{
        File fichero = new File("datasets/" + rutaArchivo + ".tsp");      
        int dimension =0;
        ArrayList<Punto> puntos = new ArrayList<>();
        boolean empiezanCoordenadas = false;
        //Con try-with-resources nos aseguramos de que los recursos se cierren, ocurra o no una excepción
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))){
            
            
            
            String linea;
            //leemos mientras que la linea leida no devuelva nulo
            while((linea = br.readLine()) != null){
                //Si la linea empieza por dimension, la dividimos en dos desde los :
                if (linea.startsWith("DIMENSION")) {
                    String[] valores = linea.split(":");
                    //Una vez dividida, tomamos la parte derecha, 
                    //le quitamos los espacios en blanco y lo pasamos a entero
                    dimension = Integer.parseInt(valores[1].trim());

                //Después de leer está línea, empiezan las coordenadas
                }else if(linea.equals("NODE_COORD_SECTION")){
                    empiezanCoordenadas = true;
                 
                //Si empiezan las coordenadas y no estamos en final de fichero
                }else if(empiezanCoordenadas && !linea.equals("EOF")){
                    //Guadamos en un array de Strings el num de la cordenada y
                    //los dos puntos de la cordenada -> deberían ser 3
                    //Dividimos por el patrón \\s+ -> uno o más carácteres de espacio en blanco
                    String[] coordenadas = linea.trim().split("\\s+");
                    if (coordenadas.length == 3) {
                        //id -> num de la coordenada en la dimension total
                        int id = Integer.parseInt(coordenadas[0]);
                        //coordenada eje X
                        double x = Double.parseDouble(coordenadas[1]);
                        //coordenada eje y
                        double y = Double.parseDouble(coordenadas[2]);
                        puntos.add(new Punto(id, x, y));
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch(IOException e) {
            System.out.println("Error de lectura: " + e.getMessage());
        }
        
        
        if(puntos.size()!=dimension){
            System.out.println("Advertencia! El número de puntos leídos no coincide con la dimensión especificada");
        }
        
        return puntos;
    }
    
    /**
     *
     * @param puntos lista de puntos a escribir en archivo
     * @param nombreArchivo nombre del archivo a guardar
     */
    public static void guardarEnArchivo(ArrayList<Punto> puntos, String nombreArchivo) {
        
                                //Si el archivo ya existe, lo sobrescribe 
        

        //al usar try-with-resources writer se cierra automáticamente se produzca o no una excepción
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("datasets/" + nombreArchivo + ".tsp"))){
            // Escribir la cabecera
            writer.write("NAME: dataset" + puntos.size() + "\n");
            writer.write("TYPE: TSP\n");
            writer.write("COMMENT: " + puntos.size() + " random locations\n");
            writer.write("DIMENSION: " + puntos.size() + "\n");
            writer.write("EDGE_WEIGHT_TYPE: EUC_2D\n");
            writer.write("NODE_COORD_SECTION\n");
            
            // Escribir los puntos
            for (Punto punto : puntos) {
                writer.write(punto.getId() + " " + punto.getX() + " " + punto.getY() + "\n");
            }
            
            //Escribir EOF al final del archivo
            writer.write("EOF\n");
        }catch(IOException e){
            System.out.println("Error de escritura: " + e.getMessage() + "\nPor favor, elimine el archivo generado");
        }
    }
 
    /**
     * Comprueba si el ArrayList de puntos está vacío.
     * <p> En caso de que haya elementos, vacía la lista
     * @param puntos Lista de puntos
     */
    public static void puntosVacios(ArrayList<Punto> puntos){
        if(!puntos.isEmpty())
            puntos.clear();
    }
    
    /**
     *
     * @param numero numero a formatear
     * @param numPrecision valor de la precisión para el formato (10 decimales, 1 decimal,etc)
     * @return devuelve el numero con la precisión pasada por parámetro
     */
    public static double formateaDouble(double numero, int numPrecision){
        String distanciaFormateadaStr = String.format(Locale.US,"%." + numPrecision + "f", numero);
        return Double.parseDouble(distanciaFormateadaStr);
    }
    
    /**
     * 
     * comprueba los algoritmos con nTallas de tamaño 2*n -> n[1,nTalla]
     * @param nTallas número de Tallas (1,2,3...)
     * @throws Exception
     */
    public static void compararTodasEstrategias(int nTallas) throws Exception{
        
        ArrayList<Punto> lista = new ArrayList<>();
        int talla = 500;
        for (int i = 1; i <= nTallas ; i++) {
            lista.clear();
            Punto.rellenarPuntos(lista, talla*i, peorCaso, PRECISION_PUNTOS);
            System.out.println("""
                                
                                    ______________________________________________________
                                    Talla: """);
            System.out.println(talla*i + "\n");
            comprobarEstrategias(lista,false);     
        }
                    
                     
    }
    
    public static void comparaDosEstrategias(ArrayList<Punto> puntos, int nTallas, int Estrategia1, int Estrategia2) throws Exception{
        ArrayList<Punto> lista = new ArrayList<>();
        int talla = 500;
        for (int i = 1; i <= nTallas ; i++) {
            lista.clear();
            Punto.rellenarPuntos(lista, talla*i, peorCaso, PRECISION_PUNTOS);
            System.out.println("""
                                
                                    ______________________________________________________
                                    Talla: """);
            System.out.println(talla*i + "\n");
            compararDosEstrategias(lista,Estrategia1,Estrategia2);  
        }
            
        
    }
    
    public static void compararDosEstrategias(ArrayList<Punto> puntos, int Estrategia1, int Estrategia2) throws Exception{
        long tiempoInicio,tiempoFin;
        double tiempoEjecucion;
        parejaPuntos resultado;
        // Mostramos cabecera
        System.out.println("Estrategia\t" + 
                            "Punto1\t\t\t\t\t" + 
                            "Punto2\t\t\t\t\t" + 
                            "distancia\t" + 
                            "calculadas\t" + 
                            "tiempo(ms)");
        
        
        //-----------------------------------Exhaustivo-----------------------------------
        if(Estrategia1 ==0 || Estrategia2 ==0){
           //Capturar tiempo de inicio
            tiempoInicio = System.nanoTime();

            //Ejecutamos algoritmo
            resultado = Algoritmos.exhaustivo(puntos);

            //Capturamos tiempo de fin
            tiempoFin = System.nanoTime();

            //Formateamos la distancia mínima a 8 cifras decimales
            resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));

            //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
            tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;

            //Guardamos el tiempo de ejecución con la precisión de 4 decimales
            formateaDouble(tiempoEjecucion, PRECISION_MSEG);

            //Imprimimos el resultado
            System.out.println("Exhaustivo\t" + resultado + "\t\t " + tiempoEjecucion);

            //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"Exhaustivo");

            //Guardamos archivo tsp
            guardarEnArchivo(puntos, "Exhaustivo"); 
        }
        
        //Creamos copia para no modificar array original
        ArrayList<Punto> puntosCopia = new ArrayList<>(puntos);
       
        //---------------------------------Exhaustivo poda---------------------------------
        if(Estrategia1 == 1 || Estrategia2 ==1){
            
            //Capturar tiempo de inicio
            tiempoInicio = System.nanoTime();

            //Ordenamos Array
            Algoritmos.quickSort(puntosCopia, 0, puntosCopia.size()-1);
            //Ejecutamos algoritmo
            resultado = Algoritmos.exhaustivoPoda(puntosCopia);

            //Capturamos tiempo de fin
            tiempoFin = System.nanoTime();

            //Formateamos la distancia mínima a 8 cifras decimales
            resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));

            //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
            tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;

            //Guardamos el tiempo de ejecución con la precisión de 4 decimales
            formateaDouble(tiempoEjecucion, PRECISION_MSEG);

            //Imprimimos el resultado
            System.out.println("Exh. Poda\t" + resultado + "\t\t " + tiempoEjecucion);

            //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"ExhaustivoPoda");

            //Guardamos archivo tsp
            guardarEnArchivo(puntosCopia, "ExhaustivoPoda");
        }
        
        
        //---------------------------------Divide y Vencerás---------------------------------

        if(Estrategia1 ==2 || Estrategia2 ==2)
            
            //reset de arrayList
            puntosCopia = new ArrayList<>(puntos);

            //Capturar tiempo de inicio
            tiempoInicio = System.nanoTime();

            //Ordenamos Array
            Algoritmos.quickSort(puntosCopia, 0, puntosCopia.size()-1);
            //Ejecutamos algoritmo
            resultado = Algoritmos.divideYVenceras(puntosCopia);

            //Capturamos tiempo de fin
            tiempoFin = System.nanoTime();

            //Formateamos la distancia mínima a 8 cifras decimales
            resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));

            //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
            tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;

            //Guardamos el tiempo de ejecución con la precisión de 4 decimales
            formateaDouble(tiempoEjecucion, PRECISION_MSEG);

            //Imprimimos el resultado
            System.out.println("DyV\t\t" + resultado + "\t\t " + tiempoEjecucion);

            //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"DivideVenceras");

            //Guardamos archivo tsp
            guardarEnArchivo(puntosCopia, "DivideVenceras");
        //----------------------------Divide y Vencerás Mejorado-----------------------------
        if(Estrategia1 ==3 || Estrategia2 ==3){
            //reset de arrayList
            puntosCopia = new ArrayList<>(puntos);

            //Capturar tiempo de inicio
            tiempoInicio = System.nanoTime();

            //Ordenamos Array
            Algoritmos.quickSort(puntosCopia, 0, puntosCopia.size()-1);
            //Ejecutamos algoritmo
            resultado = Algoritmos.divideYVencerasMejorado(puntosCopia);

            //Capturamos tiempo de fin
            tiempoFin = System.nanoTime();

            //Formateamos la distancia mínima a 8 cifras decimales
            resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));

            //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
            tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;

            //Guardamos el tiempo de ejecución con la precisión de 4 decimales
            formateaDouble(tiempoEjecucion, PRECISION_MSEG);

            //Imprimimos el resultado
            System.out.println("DyV mej. \t" + resultado + "\t\t " + tiempoEjecucion);

            //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"DyVMejorado");

            //Guardamos archivo tsp
        guardarEnArchivo(puntosCopia, "DyVMejorado");
       }
       
        
        
        
        // Si abrimos una ventan por cada algoritmo y cada talla.......
        //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"Algoritmos");
    }
    
    /**
     * Comprueba las estrategias con el dataset cargado
     * @param puntos 
     * @throws Exception Lanza posibles excepciones de los 4 algoritmos 
     */
    public static void comprobarEstrategias(ArrayList<Punto> puntos,boolean pinta) throws Exception {
        long tiempoInicio,tiempoFin;
        double tiempoEjecucion;
        parejaPuntos resultado;
        // Mostramos cabecera
        System.out.println("Estrategia\t" + 
                            "Punto1\t\t\t\t\t" + 
                            "Punto2\t\t\t\t\t" + 
                            "distancia\t" + 
                            "calculadas\t" + 
                            "tiempo(ms)");
        
        
        //-----------------------------------Exhaustivo-----------------------------------
            
        //Capturar tiempo de inicio
        tiempoInicio = System.nanoTime();
        
        //Ejecutamos algoritmo
        resultado = Algoritmos.exhaustivo(puntos);
        
        //Capturamos tiempo de fin
        tiempoFin = System.nanoTime();

        //Formateamos la distancia mínima a 8 cifras decimales
        resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));
        
        //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
        tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        //Guardamos el tiempo de ejecución con la precisión de 4 decimales
        formateaDouble(tiempoEjecucion, PRECISION_MSEG);
        
        //Imprimimos el resultado
        System.out.println("Exhaustivo\t" + resultado + "\t\t " + tiempoEjecucion);
        
        //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"Exhaustivo");

        //Guardamos archivo tsp
        guardarEnArchivo(puntos, "Exhaustivo");
       
        //---------------------------------Exhaustivo poda---------------------------------
        
        //Creamos copia para no modificar array original
        ArrayList<Punto> puntosCopia = new ArrayList<>(puntos);
        
        //Capturar tiempo de inicio
        tiempoInicio = System.nanoTime();
        
        //Ordenamos Array
        Algoritmos.quickSort(puntosCopia, 0, puntosCopia.size()-1);
        //Ejecutamos algoritmo
        resultado = Algoritmos.exhaustivoPoda(puntosCopia);
        
        //Capturamos tiempo de fin
        tiempoFin = System.nanoTime();

        //Formateamos la distancia mínima a 8 cifras decimales
        resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));
        
        //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
        tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        //Guardamos el tiempo de ejecución con la precisión de 4 decimales
        formateaDouble(tiempoEjecucion, PRECISION_MSEG);
        
        //Imprimimos el resultado
        System.out.println("Exh. Poda\t" + resultado + "\t\t " + tiempoEjecucion);
        
        //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"ExhaustivoPoda");

        //Guardamos archivo tsp
        guardarEnArchivo(puntosCopia, "ExhaustivoPoda");
        
        //---------------------------------Divide y Vencerás---------------------------------
       
        //reset de arrayList
        puntosCopia = new ArrayList<>(puntos);
        
        //Capturar tiempo de inicio
        tiempoInicio = System.nanoTime();
        
        //Ordenamos Array
        Algoritmos.quickSort(puntosCopia, 0, puntosCopia.size()-1);
        //Ejecutamos algoritmo
        resultado = Algoritmos.divideYVenceras(puntosCopia);
        
        //Capturamos tiempo de fin
        tiempoFin = System.nanoTime();

        //Formateamos la distancia mínima a 8 cifras decimales
        resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));
        
        //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
        tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        //Guardamos el tiempo de ejecución con la precisión de 4 decimales
        formateaDouble(tiempoEjecucion, PRECISION_MSEG);
        
        //Imprimimos el resultado
        System.out.println("DyV\t\t" + resultado + "\t\t " + tiempoEjecucion);
        
        //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"DivideVenceras");

        //Guardamos archivo tsp
        guardarEnArchivo(puntosCopia, "DivideVenceras");

        //----------------------------Divide y Vencerás Mejorado-----------------------------
       
        //reset de arrayList
        puntosCopia = new ArrayList<>(puntos);
        
        //Capturar tiempo de inicio
        tiempoInicio = System.nanoTime();
        
        //Ordenamos Array
        Algoritmos.quickSort(puntosCopia, 0, puntosCopia.size()-1);
        //Ejecutamos algoritmo
        resultado = Algoritmos.divideYVencerasMejorado(puntosCopia);
        
        //Capturamos tiempo de fin
        tiempoFin = System.nanoTime();

        //Formateamos la distancia mínima a 8 cifras decimales
        resultado.setDistancia(formateaDouble(resultado.getDistancia(), PRECISION_DISTANCIA));
        
        //Obtenemos el tiempo de ejecución (en nanosegundos) y lo pasamos a ms
        tiempoEjecucion = (tiempoFin - tiempoInicio) / 1_000_000.0;
        
        //Guardamos el tiempo de ejecución con la precisión de 4 decimales
        formateaDouble(tiempoEjecucion, PRECISION_MSEG);
        
        //Imprimimos el resultado
        System.out.println("DyV mej. \t" + resultado + "\t\t " + tiempoEjecucion);
        
        //new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"DyVMejorado");

        //Guardamos archivo tsp
        guardarEnArchivo(puntosCopia, "DyVMejorado");
        
        
        
        
        if(pinta) 
            new VentanaGrafica(puntos, resultado.getPunto1(), resultado.getPunto2(),"Algoritmos");

        
    }
    
    
}





