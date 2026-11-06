package Unidad2.Ejercicio2;

import java.util.Random;

public class Carrera {
    static void main() {
        Carrera carrera;
        Corredores c1 = new Corredores(1);
        Corredores c2 = new Corredores(2);
        Corredores c3 = new Corredores(3);
        Corredores c4 = new Corredores(4);


        int segundos = (int) (Math.random() * 10);
        System.out.println(segundos);
        System.out.println("Los 4 hilos creados ");
        c1.start();
        c2.start();
        c3.start();
        c4.start();
        int turno = 1;
    }

//    public synchronized void correr(Corredores corredor) throws InterruptedException {
//        Random random = new Random();
//        int segundos = (int) (Math.random() * 10);
//        System.out.println("Soy el hilo " + corredor + ", corriendo");
//        Thread.sleep(segundos * 1000L);
//        System.out.println("Termine, paso el testigo al siguiente hiloo");
//    }

    public synchronized void esperarTurmo(int turno) throws InterruptedException {
        // if (turno == )
        wait();
    }


}
