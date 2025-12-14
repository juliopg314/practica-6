import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Acumulador {
    private long sumaTotal = 0;


    public synchronized void agregarSuma(long sumaParcial) {
        this.sumaTotal += sumaParcial;
    }

    public long getSumaTotal() {
        return sumaTotal;
    }
}


class HiloSumador extends Thread {
    private int inicio;
    private int fin;
    private Acumulador acumulador;

    public HiloSumador(int inicio, int fin, Acumulador acumulador) {
        this.inicio = inicio;
        this.fin = fin;
        this.acumulador = acumulador;
    }

    @Override
    public void run() {
        long sumaParcial = 0;

        for (int i = inicio; i <= fin; i++) {
            sumaParcial += ((long) i * i) + (3L * i) + 1;
        }

        acumulador.agregarSuma(sumaParcial);
    }
}

public class Practica06 {

    private static final int TOTAL_ELEMENTOS = 100000;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numeroHilos = 0;


        while (true) {
            try {
                System.out.print("Ingresa el número de hilos (N): ");
                numeroHilos = Integer.parseInt(scanner.nextLine());
                if (numeroHilos > 0) break;
                System.out.println("Por favor, ingresa un número mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número entero.");
            }
        }

        Acumulador acumulador = new Acumulador();
        List<Thread> hilos = new ArrayList<>();


        long tiempoInicio = System.nanoTime();


        int tamanoRango = TOTAL_ELEMENTOS / numeroHilos;
        int resto = TOTAL_ELEMENTOS % numeroHilos;
        int inicioActual = 1;

        for (int i = 0; i < numeroHilos; i++) {

            int finActual = inicioActual + tamanoRango - 1;
            if (i < resto) {
                finActual++;
            }

            HiloSumador hilo = new HiloSumador(inicioActual, finActual, acumulador);
            hilos.add(hilo);
            hilo.start();

            inicioActual = finActual + 1;
        }


        for (Thread h : hilos) {
            try {
                h.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        long tiempoFin = System.nanoTime();
        double tiempoTotalMs = (tiempoFin - tiempoInicio) / 1_000_000.0;


        System.out.println("\n--- Resultados ---");
        System.out.println("Hilos utilizados: " + numeroHilos);
        System.out.println("Suma total: " + acumulador.getSumaTotal());
        System.out.println("Tiempo de ejecución: " + tiempoTotalMs + " ms");

        scanner.close();
    }
}
