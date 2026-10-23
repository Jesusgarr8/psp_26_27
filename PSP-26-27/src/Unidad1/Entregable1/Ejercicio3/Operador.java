package Unidad1.Entregable1.Ejercicio3;

public class Operador {

    static void main(String[] args) {

        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        int resultado;
        if(args[2].equals("+")){
            resultado = num1 + num2;
        } else if(args[2].equals("-")){
            resultado = num1 - num2;
        }else if(args[2].equals("*")){
            resultado = num1 * num2;
        }else if(args[2].equals("/")){
            resultado = num1 / num2;
        }else {
            resultado = -1;
            System.out.println("Operacion invallida");
        }

        System.out.println(resultado);
    }
}
