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
}