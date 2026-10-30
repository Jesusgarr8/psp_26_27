package Unidad2.Ejercicio1;

import java.util.Random;

public class H1 extends Thread {
    Random random = new Random();

    BufferLetra metodo;

    public H1(BufferLetra bufferLetra) {
        this.metodo = bufferLetra;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5; i++) {
            char letra = (char) (random.nextInt(26) + 'A');
            metodo.dejarLetra(letra);
        }
    }
}
