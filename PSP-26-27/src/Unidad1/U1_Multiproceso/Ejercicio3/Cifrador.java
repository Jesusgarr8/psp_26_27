package Unidad1.U1_Multiproceso.Ejercicio3;

import Unidad1.Entregable2.Ejercicio1.Plato;
import Unidad1.Entregable2.Ejercicio1.Proceso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Cifrador {
    public static void main() {
        Scanner sc = new Scanner(System.in);
        String operacion = "";
        int desplazamiento = 0;
        String texto = "";
        String repetir = "N";
        do {
            boolean comprobador = false;
            while (!comprobador) {
                System.out.println("Introduce una operacion (C/D):");
                operacion = sc.nextLine();
                if (operacion.equals("C") || operacion.equals("D")) {
                    comprobador = true;
                }
            }
            comprobador = false;
            while (!comprobador) {
                System.out.println("Introduce un entero del 1 al 25");
                desplazamiento = sc.nextInt();
                sc.nextLine();
                if (desplazamiento >= 1 && desplazamiento <= 25) {
                    comprobador = true;
                }
            }

            comprobador = false;
            while (!comprobador) {
                System.out.println("Introduce untexto a procesar:");
                texto = sc.nextLine();
                if (!texto.isEmpty()) {
                    comprobador = true;
                }
            }


            String parametros[] = new String[1];
            parametros[0] = operacion + ";" + desplazamiento + ";" + texto;

            //System.out.println(parametros);

            try {
                Process p = Proceso.exec(CifradoCesar.class, parametros);
                p.waitFor();
                leerSalida(p);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Desea continuar (S/N):");
            repetir = sc.nextLine();
        } while (repetir.equals("S"));
    }


    private static void leerSalida(Process p) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(p.getInputStream())
        );
        String linea;
        while ((linea = br.readLine()) != null) {

            System.out.println(linea);
        }

        br.close();
    }
}
