import java.util.*;

public class Main {

    public static void probarOrdenamientos(ArrayList<Game> juegos, Comparator<Game> comparator, String atributo) {
        String[] algoritmos = {"bubbleSort", "insertionSort", "selectionSort", "mergeSort", "quickSort", "collectionsSort"};

        System.out.println("\nTiempos promedio ordenamiento por " + atributo + " (dataset tamaño " + juegos.size() + "):");
        for (String algoritmo : algoritmos) {
            // Omitir algoritmos lentos para datasets grandes
            if (juegos.size() > 10000 && (algoritmo.equals("bubbleSort") || algoritmo.equals("insertionSort") || algoritmo.equals("selectionSort"))) {
                System.out.printf("%-15s: omitido por tiempo%n", algoritmo);
                continue;
            }
            long suma = 0;
            for (int i = 0; i < 3; i++) {
                ArrayList<Game> copia = new ArrayList<>(juegos);
                long start = System.nanoTime();
                switch (algoritmo) {
                    case "bubbleSort":
                        SortingAlgorithms.bubbleSort(copia, comparator);
                        break;
                    case "insertionSort":
                        SortingAlgorithms.insertionSort(copia, comparator);
                        break;
                    case "selectionSort":
                        SortingAlgorithms.selectionSort(copia, comparator);
                        break;
                    case "mergeSort":
                        SortingAlgorithms.mergeSort(copia, comparator);
                        break;
                    case "quickSort":
                        SortingAlgorithms.quickSort(copia, comparator);
                        break;
                    case "collectionsSort":
                        SortingAlgorithms.collectionsSort(copia, comparator);
                        break;
                }
                long end = System.nanoTime();
                suma += (end - start);
            }
            long promedio = suma / 3;
            System.out.printf("%-15s: %.3f ms%n", algoritmo, promedio / 1_000_000.0);
        }
    }

    public static void probarBusquedas(Dataset dataset) {
        System.out.println("\nTiempos de ejecución de búsqueda (dataset tamaño " + dataset.getGames().size() + "):");

        // getGamesByPrice
        long start = System.nanoTime();
        dataset.getGamesByPriceLinear(50000);
        long end = System.nanoTime();
        System.out.printf("%-30s %-15s %.3f ms%n", "getGamesByPrice", "linearSearch", (end - start) / 1_000_000.0);

        dataset.sortByAlgorithm("quickSort", "price");
        start = System.nanoTime();
        dataset.getGamesByPriceBinary(50000);
        end = System.nanoTime();
        System.out.printf("%-30s %-15s %.3f ms%n", "getGamesByPrice", "binarySearch", (end - start) / 1_000_000.0);

        // getGamesByPriceRange
        start = System.nanoTime();
        dataset.getGamesByPriceRangeLinear(20000, 30000);
        end = System.nanoTime();
        System.out.printf("%-30s %-15s %.3f ms%n", "getGamesByPriceRange", "linearSearch", (end - start) / 1_000_000.0);


        dataset.sortByAlgorithm("quickSort", "price");
        start = System.nanoTime();
        List<Game> resultado = dataset.getGamesByPriceRangeBinary(20000, 30000);
        end = System.nanoTime();
        System.out.printf("%-30s %-15s %.3f ms (encontrados: %d)%n", "getGamesByPriceRange", "binarySearch", (end - start) / 1_000_000.0, resultado.size());

        // getGamesByCategory
        start = System.nanoTime();
        dataset.getGamesByCategoryLinear("RPG");
        end = System.nanoTime();
        System.out.printf("%-30s %-15s %.3f ms%n", "getGamesByCategory", "linearSearch", (end - start) / 1_000_000.0);

        dataset.sortByAlgorithm("quickSort", "category");
        start = System.nanoTime();
        dataset.getGamesByCategoryBinary("RPG");
        end = System.nanoTime();
        System.out.printf("%-30s %-15s %.3f ms%n", "getGamesByCategory", "binarySearch", (end - start) / 1_000_000.0);
    }

    public static void main(String[] args) {
        ArrayList<Game> juegos = GenerateData.generarJuegos(10000);

        probarOrdenamientos(juegos, Comparator.comparingInt(Game::getPrice), "precio");
        probarOrdenamientos(juegos, Comparator.comparing(Game::getCategory), "categoría");
        probarOrdenamientos(juegos, Comparator.comparingInt(Game::getQuality), "calidad");

        // Pruebas de búsqueda solo para 1_000_000
        ArrayList<Game> juegosGrandes = GenerateData.generarJuegos(1_000_000);
        Dataset dataset = new Dataset(juegosGrandes);
        probarBusquedas(dataset);
    }
}