package Unidad1.Entregable1.Ejercicio1;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

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
        comandos[2] = "start";
        comandos[3] = rutaFichero;

        pb = new ProcessBuilder(comandos);
        return pb.start();

    }
//Este metodo abrirá el explorador de archivos en la ruta indicada. Si el directorio no existe, deberá registrarse el error en un fichero ERRORES.LOG.
  public static Process abrirExplorador(String rutaDirectorio) throws IOException {
        File directorio = new File(rutaDirectorio);
        if (directorio.isDirectory()){
            ProcessBuilder pb;
            String[] comandos = new String[2];
            comandos[0] = "explorer.exe";
            comandos[1] = rutaDirectorio;

            pb = new ProcessBuilder(comandos);
            return pb.start();
        }else {

                FileHandler fh = new FileHandler("ERRORES.LOG", true);
                fh.setFormatter(new SimpleFormatter());
                logger.addHandler(fh);

                logger.log(Level.SEVERE, "El directorio no existe: " + rutaDirectorio);
                logger.removeHandler(fh);
                fh.close();


        }
return null;
    }

    static void main() {
 //       Process process = abrirEditorTexto("persona.txt");
    }
}



