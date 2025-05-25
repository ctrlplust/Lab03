import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Game> juegos = GenerateData.generarJuegos(10000);
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