package Unidad2.Ejercicio1;

public class BufferLetra {
    char letra;
    protected boolean disponible = false;

    public synchronized void dejarLetra(char nuevaLetra) {
        while (disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e + "error esperado");
            }
        }
        this.letra = nuevaLetra;
        this.disponible = true;
        notifyAll();
    }

    public synchronized char cogerLetra() {
        //disponible = false;
        while (!disponible) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.disponible = false;
        notifyAll();
        return this.letra;
    }
}
