package Unidad2.Ejercicio2;

import java.util.Random;

public class Corredores extends Thread {
    private int id;
    private Carrera carrera;

    public Corredores(int id, Carrera carrera) {
        this.id = id;
        this.carrera = carrera;
    }

    public Corredores(int i) {
    }

    public void run(int idCorredor) throws InterruptedException {
        this.id = idCorredor;
        int segundos = (int) (Math.random() * 10);
        System.out.println("Soy el hilo " + id + ", corriendo");
        Thread.sleep(segundos * 1000L);
        System.out.println("Termine, paso el testigo al siguiente hiloo");
    }


}
