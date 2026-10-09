package Unidad1.Tarea3;

import Unidad1.Tarea1;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LanzadorSumador {
    private static final Logger logger = Logger.getLogger(LanzadorSumador.class.getName());

    public void processBuilder(Integer n1, Integer n2, String fichResultado){
        String clase = "Unidad1.Tarea2.Sumador";
        ProcessBuilder pb;
        try {
            pb = new ProcessBuilder("java", clase, n1.toString(), n2.toString());
            pb.start();
            pb.redirectError(new File("errores.txt"));
            pb.redirectOutput(new File(fichResultado));
        }catch (Exception e){
            logger.log(Level.SEVERE, "Error al crear process builder");

        }
    }

    static void main(String[] args) {
        LanzadorSumador l = new LanzadorSumador();
        l.processBuilder(3,6, "resultado1");
       // System.out.println("fah");
    }
}
