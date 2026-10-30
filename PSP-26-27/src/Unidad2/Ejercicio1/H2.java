package Unidad2.Ejercicio1;

import java.util.Random;

public class H2 extends Thread {
    Random random = new Random();

    BufferLetra metodo;

    public H2(BufferLetra bufferLetra) {
        this.metodo = bufferLetra;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            System.out.println("H2: " + metodo.cogerLetra());


        }

    }
}
