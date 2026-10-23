package Unidad1.Entregable2.Ejercicio1;

import java.io.*;

public class Restautante {

    // CAMBIO 1: public para que Java pueda iniciar esta clase como proceso.
    public static void main(String[] args) {

        String[] plato = new String[1];

        // CAMBIO 2: guardamos si hay que escribir en un fichero.
        boolean guardarEnFichero = false;
        String nombreFichero = "";

        // CAMBIO 3: comprobamos los argumentos antes de acceder a ellos.
        if (args.length > 0 && args[0].equals("-f")) {

            // Necesitamos -f, el nombre del fichero y el plato.
            if (args.length < 3) {
                System.out.println("Faltan argumentos: -f fichero plato");
                return;
            }

            guardarEnFichero = true;
            nombreFichero = args[1];
            plato[0] = args[2];

        } else {

            // Si no hay -f, el primer argumento es el plato.
            if (args.length < 1) {
                System.out.println("Debes indicar un plato.");
                return;
            }

            plato[0] = args[0];
        }

        try {
            // CAMBIO 4: iniciamos el proceso hijo pasándole SOLO el plato.
            Process p = Proceso.exec(Plato.class, plato);

            // CAMBIO 5: leemos la salida del hijo.
            // De momento siempre se muestra por consola.
            leerSalida(p);

        } catch (IOException e) {
            System.out.println("Error al ejecutar o leer el proceso.");
            e.printStackTrace();
        }
    }

    private static void leerSalida(Process p) throws IOException {

        // CAMBIO 6: conectamos el lector a la salida del proceso hijo.
        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream())
        );

        String linea;

        // Leemos e imprimimos cada línea que produce Plato.
        while ((linea = br.readLine()) != null) {
            System.out.println(linea);
        }

        br.close();
    }
}