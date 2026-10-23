package Unidad1.U1_Multiproceso;

import java.io.*;
import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EjecutorTareas {
    private static final Logger logger = Logger.getLogger(EjecutorTareas.class.getName());
    private static final File FICHERO_ERRORES = new File("ERRORES.TXT");
    private static final String FICHERO_INFORME = "informe.log";

    public static void main(String[] args) {
        int tiempo = 10;
        if (args.length == 0) {
            logger.log(Level.SEVERE, "Es obligatorio incluir al menos un argumento");
            return;
        }
        if (args.length > 1) {
            if (!args[1].equals("-t")) {
                logger.log(Level.SEVERE, "Opción desconocida: " + args[1]);
                return;
            }
            if (args.length < 3) {
                logger.log(Level.SEVERE, "Falta el valor de -t");
                return;
            }
            tiempo = Integer.parseInt(args[2]);
        } else {
            logger.log(Level.SEVERE, "Falta -t");
        }
        String ruta = args[0];
        File f = new File(ruta);
        if (!f.exists()) {
            logger.log(Level.SEVERE, "No existe");
            return;
        }
        if (!f.isFile()) {
            logger.log(Level.SEVERE, "No es un fichero");
            return;
        }

        if (tiempo <= 0) {
            logger.log(Level.SEVERE, "El tiempo no puede ser negativo");
            return;
        }

        int numTarea = 0;
        int correctas = 0;
        int conError = 0;
        int canceladas = 0;

        try (BufferedReader br = Files.newBufferedReader(f.toPath())) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String comando = linea.trim();
                if (comando.isEmpty() || comando.startsWith("#")) {
                    continue;
                }

                numTarea++;
                File salida = new File("salida_" + numTarea + ".txt");
                String resultado;
                long inicio = System.currentTimeMillis();
                boolean interrumpido = false;

                try {
                    Process p = lanzarTarea(comando, salida);
                    boolean terminada = p.waitFor(tiempo, TimeUnit.SECONDS);
                    if (terminada) {
                        int codigo = p.exitValue();
                        resultado = "código " + codigo;
                        if (codigo == 0) {
                            correctas++;
                        } else {
                            conError++;
                        }
                    } else {
                        resultado = "CANCELADA";
                        canceladas++;
                    }

                    long milisegundos = System.currentTimeMillis() - inicio;
                    registrarInforme(numTarea, comando, resultado, milisegundos);

                } catch (IOException e) {
                    logger.log(Level.SEVERE, "No se pudo lanzar la tarea " + numTarea + ": " + e.getMessage());
                    resultado = "ERROR al lanzar";
                    conError++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    resultado = "CANCELADA";
                    canceladas++;
                    interrumpido = true;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Tareas ejecutadas: " + numTarea);
        System.out.println("Terminadas correctamente (código 0): " + correctas);
        System.out.println("Terminadas con error: " + conError);
        System.out.println("Canceladas por tiempo: " + canceladas);
    }

    public static Process lanzarTarea(String comando, File salida) throws IOException {
        boolean windows = System.getProperty("os.name").toLowerCase().contains("win");

        ProcessBuilder pb;
        if (windows) {
            pb = new ProcessBuilder("cmd", "/c", comando);
        } else {
            pb = new ProcessBuilder("bash", "-c", comando);
        }

        pb.redirectOutput(salida);
        pb.redirectError(ProcessBuilder.Redirect.appendTo(FICHERO_ERRORES));
        return pb.start();
    }


    private static void registrarInforme(int numTarea, String comando, String resultado, long milisegundos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_INFORME, true))) {
            bw.write("[Tarea " + numTarea + "] " + comando + " -> " + resultado + " (" + milisegundos + " ms)");
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir en informe.log: " + e.getMessage());
        }
    }


}
