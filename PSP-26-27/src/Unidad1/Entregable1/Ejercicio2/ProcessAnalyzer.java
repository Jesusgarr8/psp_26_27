package Unidad1.Entregable1.Ejercicio2;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ProcessAnalyzer {
    private static final Logger logger = Logger.getLogger(ProcessAnalyzer.class.getName());
    public void processBuilder() throws IOException {
        String [] comandos = new String[3];
        comandos[0] = "C:\\Windows\\System32\\cmd.exe";
        comandos[1] = "/c";
        comandos[2] = "tasklist";

        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(comandos);
            pb.start();

            BufferedReader leer = new BufferedReader(
                    new InputStreamReader(pb.start().getInputStream())
            );
            BufferedWriter escribir = new BufferedWriter(new FileWriter("procesos.txt"));
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error executing process", e);
        }
    }


    static void main() throws IOException {
        ProcessAnalyzer pa = new ProcessAnalyzer();
        pa.processBuilder();
    }
}
