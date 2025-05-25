import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] palabras = {"Dragon", "Empire", "Quest", "Galaxy", "Legends", "Warrior"};
        String[] categorias = {"Aventura", "Estrategia", "Accion"};
        Random rand = new Random();
        ArrayList<Game> juegos = new ArrayList<>();

        // Generar 10000 juegos aleatorios
        for (int i = 0; i < 10000; i++) {
            String nombre = palabras[rand.nextInt(palabras.length)] + palabras[rand.nextInt(palabras.length)];
            String categoria = categorias[rand.nextInt(categorias.length)];
            int precio = 5000 + rand.nextInt(20000); // entre 5000 y 25000
            int calidad = rand.nextInt(101); // entre 0 y 100
            juegos.add(new Game(nombre, categoria, precio, calidad));
        }

        Dataset dataset = new Dataset(juegos);

        // Medir tiempo de ordenamiento
        long start = System.nanoTime();
        dataset.sortByAlgorithm("quickSort", "price");
        long end = System.nanoTime();
        System.out.println("Ordenamiento por precio (quickSort): " + (end - start) + " ns");

        // Medir tiempo de búsqueda
        start = System.nanoTime();
        ArrayList<Game> encontrados = dataset.getGamesByPrice(10000);
        end = System.nanoTime();
        System.out.println("Búsqueda por precio (10000): " + (end - start) + " ns, encontrados: " + encontrados.size());
    }
}