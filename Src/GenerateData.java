import java.util.*;

public class GenerateData {
    private static final String[] palabras = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior"};
    private static final String[] categorias = {"Acción", "Aventura", "Estrategia", "RPG", "Deportes", "Simulación"};

    public static ArrayList<Game> generarJuegos(int N) {
        ArrayList<Game> juegos = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            String nombre = palabras[rand.nextInt(palabras.length)] + palabras[rand.nextInt(palabras.length)];
            String categoria = categorias[rand.nextInt(categorias.length)];
            int precio = rand.nextInt(70001); // 0 a 70000
            int calidad = rand.nextInt(101);  // 0 a 100
            juegos.add(new Game(nombre, categoria, precio, calidad));
        }
        return juegos;
    }
public static void guardarEnArchivo(ArrayList<Game> juegos, String nombreArchivo) {
    // Se usa un bloque try-with-resources para que el archivo se cierre automáticamente al terminar.
    try (PrintWriter writer = new PrintWriter(new File(nombreArchivo))) {

        // Escribimos la cabecera del archivo CSV (los nombres de las columnas).
        writer.println("Name,Category,Price,Quality");

        // Recorremos todos los juegos en la lista y escribimos cada uno en una nueva línea del archivo.
        for (Game juego : juegos) {
            // toCSV() debe devolver un String con los atributos separados por comas, por ejemplo:
            // "DragonQuest,RPG,50000,85"
            writer.println(juego.toCSV());
        }

        // Imprimimos un mensaje en la consola indicando que el archivo fue guardado con éxito.
        System.out.println("Archivo guardado: " + nombreArchivo);

    // Si ocurre algún error al trabajar con archivos (por ejemplo, permisos o disco lleno), lo capturamos aquí.
    } catch (IOException e) {
        // Imprimimos un mensaje de error con los detalles.
        System.out.println("Error al guardar archivo: " + e.getMessage());
    }
}

    public static void main(String[] args) {
        int[] N = {100, 10000, 1000000};
        for (int n : N) {
            ArrayList<Game> juegos = generarJuegos(n);
            String nombreArchivo = "juegos_" + n + ".csv";
            guardarEnArchivo(juegos, nombreArchivo);
        }
    }
}