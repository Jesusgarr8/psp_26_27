import java.util.logging.Level;
import java.util.logging.Logger;

public class lanzar_procesos {
    private static final Logger logger = Logger.getLogger(lanzar_procesos.class.getName());

    //falta añadir ifs para el tercer argumento y hacerlo en process builder
    public void processBuilder(String ruta) {
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder(ruta);
            pb.start();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error executing process", e);
        }
    }

    public void runTime(String ruta, String url) {
        String[] comandos = new String[2];
        comandos[0] = (ruta);
        comandos[1] = (url);
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec(comandos);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error ejecutando el proceso", e);
        }
    }

    public static void main(String[] args) {
        String ruta = "";
        String url = "https://www.youtube.com"; //hay q ponerlo en los args
        lanzar_procesos lp = new lanzar_procesos();

        if (args.length >= 2 && args.length < 4) {

            if (args[1].equals("C")) {

                ruta = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
            } else if (args[1].equals("N")) {
                ruta = "C:\\Windows\\System32\\notepad.exe";
            }
            if (args[0].equals("P")) {
                lp.processBuilder(ruta);
            } else if (args[0].equals("R")) {
                lp.runTime(ruta, url);
            }
            System.out.println("Finalizado");
            System.out.println(ruta);
            System.out.println(url);
        } else {
            System.out.println("Error, número incorrecto de parametros.");
        }
    }
}
