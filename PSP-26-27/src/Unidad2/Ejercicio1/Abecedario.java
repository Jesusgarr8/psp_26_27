package Unidad2.Ejercicio1;

public class Abecedario {
    static void main(String[] args) {
        BufferLetra bufferLetra = new BufferLetra();
        H1 hilo1 = new H1(bufferLetra);
        H2 hilo2 = new H2(bufferLetra);
        hilo1.start();
        hilo2.start();
    }
}
