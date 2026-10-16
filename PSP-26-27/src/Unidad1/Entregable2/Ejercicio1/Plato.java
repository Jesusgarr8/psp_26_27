package Unidad1.Entregable2.Ejercicio1;

import java.util.Scanner;

public class Plato {


    static void main(String[] args) {
        String preparacion = "";
        Scanner sc = new Scanner(System.in);
        System.out.println("Elige un plato (salmorejo, gazpacho, pescaito, tortillita");
        String plato = sc.nextLine();
        if (plato.equals("salmoorejo")) {
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
