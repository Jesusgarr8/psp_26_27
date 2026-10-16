package Unidad1.Entregable2.Ejercicio1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class Restautante {
    static void main(String[] args) {
        if (args[0].equals("-f")) {
            File fichero = new File(args[1]);
            try {
                BufferedWriter bf = new BufferedWriter(new FileWriter(fichero));

            } catch (Exception e) {
                System.out.println("error!!");
            }
        } else {
            System.out.println();
        }
    }
}
