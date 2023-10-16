package exercise.readfile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ReactiveFileReader {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\David\\Desktop\\DesarrolloWebEntornoServidor\\Ejercicios\\PracticaFunkosAsync\\data\\funkos.csv";  // Reemplaza con la ruta real de tu archivo

        CompletableFuture.supplyAsync(() -> {
            try {
                return readFromFile(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Error al leer el archivo", e);
            }
        }).thenAccept(result -> {
            String contenidoArchivo = result;
            System.out.println("Contenido del archivo: ");
            System.out.println(contenidoArchivo);
        }).exceptionally(ex -> {
            System.err.println("Error al leer el archivo: " + ex.getMessage());
            return null;
        });

        // Espera un tiempo
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.err.println("Error al dormir el hilo principal: " + e.getMessage());
        }
    }

    public static String readFromFile(String filePath) throws IOException {
        Path path = Path.of(filePath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
