package Unidad1.Tarea2;

import Unidad1.Tarea1;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shell {
    private static final Logger logger = Logger.getLogger(Shell.class.getName());

public void processBuilder(String shell, String operacion){
    String [] comandos = new String[4];
    comandos[0] = shell;
    comandos[1] = "/c";
    comandos[2] = "more";
    comandos[3] = operacion;

    ProcessBuilder pb;
    try {
        pb = new ProcessBuilder(comandos);
        pb.inheritIO();
        pb.start();
    }catch (Exception e){
        logger.log(Level.SEVERE, "Error al crear process builder");
    }
}

    static void main(String[] args) {
        String shell = "C:\\Windows\\System32\\cmd.exe";
        String direccion = " ";
        String operacion = "";

        if (args.length == 0){
            logger.log(Level.SEVERE, "Error, el parámetro está vacío");
        }else {
            direccion = args[0];
            System.out.println(direccion);
            File fichero = new File(direccion);
            if (fichero.isFile()){
                System.out.println("es true");
                operacion = fichero.toString();
            }else {
                System.out.println("es false");
            }

        }
        Shell pb = new Shell();
        pb.processBuilder(shell, operacion);
    }
}
