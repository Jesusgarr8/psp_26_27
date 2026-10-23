package Unidad1.U1_Multiproceso.Ejercicio3;

import java.util.Scanner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class CifradoCesar {

    private static final Logger logger = Logger.getLogger(CifradoCesar.class.getName());

    public static void main(String[] args) {
        String parametros = args[0];
        String[] partes = parametros.split(";");
        String operacion = partes[0];
        String desplazamiento = partes[1];
        String texto = partes[2];

        System.out.print(operacion + " (" + desplazamiento + "): " + texto + " -> ");
        System.out.println(cifrar(operacion, texto, Integer.parseInt(desplazamiento)));

        try {
            FileHandler fileHandler = new FileHandler("CIFRADOS.LOG", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Error al crear el fichero de registro: " + e.getMessage());
        }

        logger.info(operacion + " (" + desplazamiento + "): "
                + texto + " -> " + cifrar(operacion, texto, Integer.parseInt(desplazamiento)));
    }

    private static String cifrar(String operacion, String texto, int desplazamiento) {

        if (operacion.equals("D")) {
            desplazamiento = -desplazamiento;
        }
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < texto.length(); i++) {

            char caracter = texto.charAt(i);

            if (caracter >= 'a' && caracter <= 'z') {

                char nuevaLetra = (char) (
                        'a' + (caracter - 'a' + desplazamiento + 26) % 26);

                resultado.append(nuevaLetra);

            } else if (caracter >= 'A' && caracter <= 'Z') {

                char nuevaLetra = (char) (
                        'A' + (caracter - 'A' + desplazamiento + 26) % 26);

                resultado.append(nuevaLetra);

            } else {
                // Los espacios, números y signos se mantienen
                resultado.append(caracter);
            }
        }

        return resultado.toString();
    }
}