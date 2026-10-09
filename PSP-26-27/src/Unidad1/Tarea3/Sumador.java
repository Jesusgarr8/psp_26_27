package Unidad1.Tarea3;

public class Sumador {
    static void main(String[] args) {
        System.out.println("Sumador");
        int num1;
        int num2;
        int suma;
        if (args.length == 2){
            num1 =Integer.parseInt(args[0]);
            num2 =Integer.parseInt(args[1]);
            suma = num1 + num2;
            System.out.println("Suma: " + suma);
        }else {
            System.out.println("Numero incorrecto de parametros");
        }
    }
}
