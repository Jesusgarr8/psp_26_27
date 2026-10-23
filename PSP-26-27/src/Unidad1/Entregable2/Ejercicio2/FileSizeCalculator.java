package Unidad1.Entregable2.Ejercicio2;

import Unidad1.Tarea2.Shell;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileSizeCalculator {
    private static final Logger logger = Logger.getLogger(FileSizeCalculator .class.getName());
    static void main(String[] args) throws IOException {
        String shell = "C:\\Windows\\System32\\cmd.exe";
        Boolean fichero = false;
        String ruta ="a";
        if (args.length > 0){
             ruta = args[0];
            File argumento = new File(ruta);
            if (argumento.exists()){
                if (argumento.isFile()){
                    System.out.println("fichero");
                    fichero = true;
                }else if(argumento.isDirectory()){
                    System.out.println("directorio");
                }
            }
        }else {
            System.out.println("Debes introducir un parametro");
        }
        FileSizeCalculator pb = new FileSizeCalculator();
        pb.processBuilder(shell, ruta, fichero);
    }

    public void processBuilder(String shell, String argumento, Boolean fichero) throws IOException {
        String [] comandos = new String[6];
            comandos[0] = shell;
            comandos[1] = "/c";
            comandos[2] = "dir ";
            comandos[3] = "/s";
            comandos[4] = "/-c";
            comandos[5] = argumento;

        ProcessBuilder pb;
          try{
            pb = new ProcessBuilder(comandos);

            //pb.start();
              System.out.println(obtenerTamanoTotal(pb.start(), fichero));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error al crear process builder");
        }
    }



    private static long obtenerTamanoTotal(Process p, boolean fichero)
            throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream())
        );

        String linea;
        long tamanoTotal = 0;

        while ((linea = br.readLine()) != null) {

            if (fichero) {

                // Para un fichero, buscamos su línea de tamaño
                if (linea.contains("bytes")) {
                    String[] partes = linea.trim().split("\\s+");

                    try {
                        tamanoTotal = Long.parseLong(partes[0]);
                    } catch (NumberFormatException e) {
                        // Ignoramos líneas que no empiezan por un número
                    }
                }

            } else {

                // Para un directorio, buscamos la línea del total de archivos
                if (linea.contains("archivos") && linea.contains("bytes")) {

                    String[] partes = linea.trim().split("\\s+");

                    for (int i = 0; i < partes.length; i++) {
                        if (partes[i].equals("bytes") && i > 0) {
                            try {
                                tamanoTotal = Long.parseLong(partes[i - 1]);
                            } catch (NumberFormatException e) {
                                // Ignoramos valores no numéricos
                            }
                        }
                    }
                }
            }
        }

        br.close();

        return tamanoTotal;
    }

}
