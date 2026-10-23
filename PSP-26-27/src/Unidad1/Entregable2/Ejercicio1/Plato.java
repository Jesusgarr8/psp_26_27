package Unidad1.Entregable2.Ejercicio1;

import java.util.Scanner;

public class Plato {


    public static void main(String[] args) {

        String plato = args[0];
        String preparacion = "";
        if (plato.equals("salmorejo")) {
            preparacion = "el salmorejo se hace asin";
        } else if (plato.equals("gazpacho")) {
            preparacion = "el gazpacho se hace asao";
        } else if (plato.equals("pescaito")) {
            preparacion = "el pescaito se hace con cariño";
        } else if (plato.equals("tortillita")) {
            preparacion = "la tortillita se hace con camarones";
        }

        System.out.println(plato);
        System.out.println(preparacion);
    }
}
