# **Documentación de la Aplicación**

## **Resumen de la Aplicación**
Esta aplicación tiene como objetivo resolver el problema de encontrar el par de puntos más cercanos en un plano cartesiano utilizando cuatro estrategias algorítmicas:
1. **Búsqueda Exhaustiva**
2. **Búsqueda Exhaustiva con Poda**
3. **Divide y Vencerás**
4. **Divide y Vencerás Mejorado**

### **Características principales:**
- Permite generar datasets aleatorios o cargarlos desde archivos `.tsp` con un formato compatible para archivos del [repositorio TSP](https://web.iwr.uni-heidelberg.de/groups/comopt/software/TSPLIB95/tsp/)
- Analiza los algoritmos mostrando los puntos más cercanos, la distancia mínima, el número de cálculos y el tiempo de ejecución.
- Incluye un módulo para comparar las estrategias con diferentes tamaños de datasets y generar gráficas.

---

## **Descripción General de los Algoritmos**

### **1. Búsqueda Exhaustiva**
Compara todos los pares de puntos posibles:
- **Complejidad:** $( O(n^2) )$ para todos los casos.
- No realiza optimizaciones, por lo que su tiempo crece cuadráticamente.

### **2. Búsqueda Exhaustiva con Poda**
Optimiza la búsqueda exhaustiva ordenando previamente los puntos por \( x \) y descartando pares lejanos:
- **Mejor caso:** $( O(n log n) )$ si la poda es muy efectiva.
- **Caso medio y peor caso:** $( O(n^2) )$, ya que la poda depende de la distribución.

### **3. Divide y Vencerás**
Divide los puntos recursivamente y combina las soluciones:
- **Mejor caso y caso medio:** $( O(n log n) )$ debido al ordenamiento y la división balanceada.
- **Peor caso:** $(O(n^2) )$, si todos los puntos están alineados verticalmente.

### **4. Divide y Vencerás Mejorado**
Mejora el Divide y Vencerás ordenando los puntos de la franja por \( y \) y limitando las comparaciones a los 11 vecinos más cercanos:
- **Mejor caso y caso medio:** $( O(n log n) )$, gracias a las optimizaciones.
- **Peor caso:** $( O(n^2) )$, si no hay reducción significativa en la franja.

---

## **Estudio Teórico: Complejidad de los Algoritmos**

### **1. Complejidad Teórica**

| Algoritmo                | Mejor Caso         | Caso Medio        | Peor Caso         |
|--------------------------|--------------------|-------------------|-------------------|
| Búsqueda Exhaustiva      | O(n²)             | O(n²)            | O(n²)            |
| Exhaustiva con Poda      | O(n log n)        | O(n²)            | O(n²)            |
| Divide y Vencerás        | O(n log n)        | O(n log n)       | O(n²)            |
| Divide y Vencerás Mejorado | O(n log n)        | O(n log n)       | O(n²)            |

### **2. Tiempos de Ejecución Simulados**

| Tamaño del Dataset | Exhaustivo (ms) | Exhaustiva con Poda (ms) | Divide y Vencerás (ms) | DyV Mejorado (ms) |
|---------------------|-----------------|--------------------------|------------------------|-------------------|
| 1000               | 9.24            | 0.24                     | 0.54                   | 0.67              |
| 2000               | 37.68           | 0.57                     | 1.38                   | 1.53              |
| 3000               | 85.32           | 0.88                     | 2.11                   | 2.18              |
| 4000               | 151.33          | 1.22                     | 3.07                   | 3.26              |
| 5000               | 236.35          | 1.53                     | 4.07                   | 4.11              |

---
