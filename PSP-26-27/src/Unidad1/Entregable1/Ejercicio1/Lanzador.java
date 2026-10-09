package Unidad1.Entregable1.Ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lanzador {
    private static final Logger logger = Logger.getLogger(Lanzador.class.getName());

    //Este metodo abrirá el editor de texto predeterminado del sistema con el fichero indicado en rutaFichero. Si el fichero no existe, deberá crearse vacío antes de abrirlo.
    public static Process abrirEditorTexto(String rutaFichero) throws IOException {
        try {
            File fichero = new File(rutaFichero);
            if (fichero.isFile()) {

            } else {
                fichero.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ProcessBuilder pb;
        String[] comandos = new String[4];
        comandos[0] = "C:\\Windows\\System32\\cmd.exe";
        comandos[1] = "/c";
        comandos[1] = "start";
        comandos[2] = rutaFichero;

        pb = new ProcessBuilder(comandos);
        return pb.start();

    }

}

//Este metodo abrirá el explorador de archivos en la ruta indicada. Si el directorio no existe, deberá registrarse el error en un fichero ERRORES.LOG.
//  public static Process abrirExplorador(String rutaDirectorio) {}

