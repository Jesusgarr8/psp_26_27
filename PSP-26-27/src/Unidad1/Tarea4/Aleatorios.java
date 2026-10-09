package Unidad1.Tarea4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Aleatorios {
    private static final Logger logger = Logger.getLogger(Aleatorios.class.getName());

    public static void main(String[] args) {
        // Flujo de entrada para solicitar datos al usuario por teclado
        Scanner sc = new Scanner(System.in);
        try {
            // Introducción de datos (es necesario el control de errores)
            char opcion;

            do {
                Process p = Proceso.exec(GenereadorAleatorios.class);

                // Se obtiene stdout del proceso hijo
                getSalidaProceso(p);

                // El proceso padre espera a que el hijo finalice
                int salida = p.waitFor();

                System.out.println("Proceso hijo finalizado con valor: " + salida);
                System.out.println("Proceso padre finalizado");
                //        mostrarResultadoBuffer(p);

                // CONTROL DE NUEVA INSERCIÓN
                System.out.println("¿Desea lanzar más procesos (S/N)?: ");
                opcion = sc.nextLine().toUpperCase().charAt(0);
            } while (opcion == 'S');
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    // Función que obtiene la salida stdout del proceso hijo
    private static void getSalidaProceso(Process p) {
        String line = "";
        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        // Se muestra la salida del proceso por pantalla
        while (true) {
            try {
                if ((line = br.readLine()) == null) break;
            } catch (IOException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
            System.out.println(line);
        }

        // Cuando finaliza se cierra el descriptor del proceso
        try {
            is.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}
