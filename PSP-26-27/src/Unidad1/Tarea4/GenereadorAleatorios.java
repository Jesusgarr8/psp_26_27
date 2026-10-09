package Unidad1.Tarea4;

import java.util.Random;

public class GenereadorAleatorios {
    static void main() {

        System.out.println(aleatorio(0, 10));

    }

    public static int aleatorio(int min, int max) {
        Random r = new Random();
        return min + r.nextInt(max - min + 1);  // Entre min y max -> [min, max];
    }
}
