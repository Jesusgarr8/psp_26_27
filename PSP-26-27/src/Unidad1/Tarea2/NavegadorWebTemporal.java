package Unidad1.Tarea2;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavegadorWebTemporal {
    private static final Logger logger = Logger.getLogger(NavegadorWebTemporal.class.getName());
    private static final String RUTA_NAVEGADOR = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String direccion;
        do {
            System.out.print("¿Qué dirección web desea consultar? (FIN para salir): ");
            direccion = sc.nextLine().trim();
            if (!direccion.equalsIgnoreCase("FIN")) {
                try {
                    ProcessBuilder pb = new ProcessBuilder(RUTA_NAVEGADOR, direccion);
                    pb.start();
                    // Esperamos 10 segundos antes de volver a preguntar
                    Thread.sleep(10000);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "No se pudo abrir el navegador: " + e.getMessage());
                } catch (InterruptedException e) {
                    logger.log(Level.SEVERE, "Espera interrumpida");
                }
            }
        } while (!direccion.equalsIgnoreCase("FIN"));
    }
}
