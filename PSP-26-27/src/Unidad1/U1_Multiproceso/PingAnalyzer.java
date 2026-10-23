package Unidad1.U1_Multiproceso;

import Unidad1.Entregable2.Ejercicio2.FileSizeCalculator;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PingAnalyzer {
    private static final Logger logger = Logger.getLogger(PingAnalyzer.class.getName());

    static void main(String[] args) {
        if (args.length == 0) {
            logger.log(Level.SEVERE, "Es obligatorio incluir al menos un host");
            return;
        }
        long inicio = System.nanoTime();
        Process[] procesos = new Process[args.length];
        String sistema = System.getProperty("os.name").toLowerCase();

        for (int i = 0; i < args.length; i++) {

            String host = args[i];

            try {
                ProcessBuilder pb;

                if (sistema.contains("win")) {
                    pb = new ProcessBuilder("ping", "-n", "4", host);
                } else {
                    pb = new ProcessBuilder("ping", "-c", "4", host);
                }

                pb.redirectErrorStream(true);
                procesos[i] = pb.start();

            } catch (IOException e) {
                System.err.println("Error al ejecutar ping para " + host
                        + ": " + e.getMessage());
            }
        }


        double menorMedia = Double.MAX_VALUE;
        String hostMasRapido = null;

        for (int i = 0; i < args.length; i++) {
            String host = args[i];
            if (procesos[i] != null) {

                double media = obtenerTiempoMedio(procesos[i]);

                guardarResultado(host, media);

                if (media >= 0 && media < menorMedia) {
                    menorMedia = media;
                    hostMasRapido = host;
                }
            } else {
                guardarResultado(host, -1);
            }
        }

        long fin = System.nanoTime();

        double tiempoTotal = (fin - inicio) / 1_000_000_000.0;

        String formateado = "";
        if (hostMasRapido != null) {
            System.out.printf(
                    Locale.forLanguageTag("es-ES"),
                    "Host con menor tiempo medio: %s (%.2f ms)%n",
                    hostMasRapido,
                    menorMedia);
        } else {
            System.out.println("Ningún host ha respondido.");
        }

        System.out.printf(
                Locale.forLanguageTag("es-ES"),
                "Tiempo total de ejecución: %.2f segundos%n",
                tiempoTotal
        );
    }

    private static double obtenerTiempoMedio(Process p) {

        double suma = 0;
        int contador = 0;

        // Detecta time=12.3, tiempo=12 o tiempo<1
        Pattern patron = Pattern.compile(
                "(?:time|tiempo)\\s*[=<]\\s*(\\d+(?:[.,]\\d+)?)",
                Pattern.CASE_INSENSITIVE
        );

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream()))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                Matcher matcher = patron.matcher(linea);

                if (matcher.find()) {

                    String valor = matcher.group(1).replace(',', '.');

                    double tiempo = Double.parseDouble(valor);

                    // En Windows, tiempo<1ms se aproxima a 0.5 ms
                    if (linea.toLowerCase().contains("tiempo<")) {
                        tiempo = 0.5;
                    }

                    suma += tiempo;
                    contador++;
                }
            }

            p.waitFor();

        } catch (IOException e) {
            System.err.println("Error al leer la salida del ping: "
                    + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Se ha interrumpido la espera del ping.");
        }

        if (contador == 0) {
            return -1;
        }

        return suma / contador;
    }

    private static void guardarResultado(String host, double media) {

        try (FileWriter fw = new FileWriter("Ping.txt", true)) {

            if (media == -1) {
                fw.write("El host " + host + " no responde"
                        + System.lineSeparator());
            } else {
                fw.write(String.format(
                        Locale.forLanguageTag("es-ES"),
                        "El host %s responde con un tiempo medio de %.2f ms%n",
                        host,
                        media
                ));
            }

        } catch (IOException e) {
            System.err.println("Error al escribir en Ping.txt: "
                    + e.getMessage());
        }
    }
}
