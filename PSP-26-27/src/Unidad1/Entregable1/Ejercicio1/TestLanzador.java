package Unidad1.Entregable1.Ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class TestLanzador {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Probar el editor de texto
        System.out.println("Introduce la ruta del fichero:");
        String rutaFichero = sc.nextLine();

        try {
            Lanzador.abrirEditorTexto(rutaFichero);
            System.out.println("Se ha solicitado abrir el fichero.");
        } catch (IOException e) {
            System.out.println("Error al abrir el editor de texto: " + e.getMessage());
        }

        // Probar el explorador de archivos
        System.out.println("Introduce la ruta del directorio:");
        String rutaDirectorio = sc.nextLine();

        try {
            Lanzador.abrirExplorador(rutaDirectorio);

            if (new java.io.File(rutaDirectorio).isDirectory()) {
                System.out.println("Se ha solicitado abrir el explorador.");
            } else {
                System.out.println("El directorio no existe. Comprueba ERRORES.LOG.");
            }

        } catch (IOException e) {
            System.out.println("Error al abrir el explorador: " + e.getMessage());
        }

        sc.close();
    }
}