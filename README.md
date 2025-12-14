# Práctica 6: Concurrencia y Hilos (Multithreading)

## Objetivo
El objetivo de esta práctica es comprender los conceptos de programación concurrente en Java. Se busca implementar múltiples hilos de ejecución (`Threads`) para dividir una tarea computacional intensiva y coordinar el acceso a un recurso compartido utilizando la palabra clave `synchronized` para evitar condiciones de carrera.

## Descripción
Este programa realiza el cálculo de una sumatoria compleja para un total de 100,000 elementos. La fórmula aplicada a cada número $i$ es:
$$f(i) = i^2 + 3i + 1$$

El programa permite al usuario definir **cuántos hilos** desea utilizar para realizar el trabajo. El sistema:
1. Divide el rango total (1 a 100,000) en partes iguales según el número de hilos.
2. Cada hilo calcula su parte independientemente.
3. Los hilos suman su resultado parcial a un `Acumulador` compartido de forma segura (sincronizada).
4. Mide y muestra el tiempo total de ejecución en milisegundos para comparar el rendimiento.

## Tecnologías utilizadas
- **Java (JDK)**
- **Clase `Thread`:** Para la creación de procesos paralelos.
- **`synchronized`:** Para el control de concurrencia y seguridad de hilos (Thread-Safety).
- **`System.nanoTime()`:** Para mediciones precisas de rendimiento.
- **Git & GitHub**


## Instrucciones de ejecución
Como todo el código se encuentra en un solo archivo para esta práctica:

1. **Clonar el repositorio:**
   ```bash
   git clone [URL-DEL-REPOSITORIO]
   javac Practica06.java
   java Practica06
