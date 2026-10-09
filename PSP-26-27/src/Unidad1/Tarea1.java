package Unidad1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Tarea1 {
    private static final Logger logger = Logger.getLogger(Tarea1.class.getName());

    public void processBuilder(String ruta,String enlace) {
        String[] comandos = new String[2];
        comandos[0] = (ruta);
        comandos[1] = (enlace);
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(comandos);
            pb.start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al crear process builder");
        }
    }

    public void runtime(String ruta, String enlace) {
        String[] comandos = new String[2];
        comandos[0] = (ruta);
        comandos[1] = (enlace);
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(comandos);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error ejecutando el proceso", e);
        }
    }


    static void main(String[] args) {
        String ruta = "";
        String enlace  = " ";

        if (args.length >= 2 && args.length < 4) {
            if (args[0].equals("P")) {
                Tarea1 pb = new Tarea1();
                if (args[1].equals("C")) {
                    ruta = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
                    enlace = args[2];
                } else if (args[1].equals("N")) {
                    ruta = "C:\\Windows\\System32\\notepad.exe";
                }
                pb.processBuilder(ruta, enlace);
            } else if (args[0].equals("R")) {
                Tarea1 rt = new Tarea1();
                if (args[1].equals("C")) {
                    ruta = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
                    enlace = args[2];
                } else if (args[1].equals("N")) {
                    ruta = "C:\\Windows\\System32\\notepad.exe";
                }
                rt.runtime(ruta, enlace);
            }
        }
    }
}