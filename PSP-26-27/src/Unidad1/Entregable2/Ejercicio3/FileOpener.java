package Unidad1.Entregable2.Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileOpener {
    private static final Logger logger = Logger.getLogger(FileOpener.class.getName());
    private static final String RUTA_NOTEPAD = "notepad.exe";
    private static final String RUTA_ADOBE = "C:\\Program Files\\Adobe\\Acrobat DC\\Acrobat\\Acrobat.exe";
    // Photos no es un .exe normal: explorer.exe abre la imagen con su app predeterminada
    private static final String RUTA_FOTOS = "explorer.exe";

    public static Process abrirArchivo(String rutaAplicacion, String archivo) throws IOException {
        ProcessBuilder pb = new ProcessBuilder(rutaAplicacion, archivo);
        return pb.start();
    }

    private static String getExtension(String archivo) {
        int punto = archivo.lastIndexOf('.');
        if (punto < 0) {
            return "";
        }
        return archivo.substring(punto + 1).toLowerCase();
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.log(Level.SEVERE, "Es obligatorio incluir al menos una ruta de archivo como parámetro");
            return;
        }
        for (String archivo : args) {
            if (!new File(archivo).exists()) {
                logger.log(Level.SEVERE, "El archivo no existe: " + archivo);
                continue;       // seguimos con el siguiente
            }
            String extension = getExtension(archivo);
            String aplicacion = null;
            if (extension.equals("txt")) {
                aplicacion = RUTA_NOTEPAD;
            } else if (extension.equals("pdf")) {
                aplicacion = RUTA_ADOBE;
            } else if (extension.equals("jpg") || extension.equals("png")) {
                aplicacion = RUTA_FOTOS;
            }

            if (aplicacion == null) {
                logger.log(Level.SEVERE, "Extensión no soportada: " + archivo);
                continue;
            }
            try {
                abrirArchivo(aplicacion, archivo);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "No se pudo abrir " + archivo + " con " + aplicacion + ": " + e.getMessage());
            }
        }
    }
}
