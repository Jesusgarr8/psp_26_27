package Unidad1.Entregable1.Ejercicio3;

import java.io.IOException;
import java.util.Scanner;

public class CalculadoraProcesos {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el primer numero");
        int num1 = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el segundo numero");
        int num2 = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el operacion (+,-,*,/)");
        String operacion = sc.nextLine();

        String [] array = new String[3];
        array[0] = String.valueOf(num1);
        array[1] = String.valueOf(num2);
        array[2] = operacion;

        Process proceso = Proceso.exec(Operador.class, array);
    }
}
