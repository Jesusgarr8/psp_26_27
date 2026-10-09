package Unidad1.Tarea2;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavegadorWebTemporal {
    private static final Logger logger = Logger.getLogger(NavegadorWebTemporal.class.getName());

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
        String ruta = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("Introduce un enlace");
            String enlace = sc.nextLine();
            NavegadorWebTemporal rt = new NavegadorWebTemporal();
            rt.runtime(ruta, enlace);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
