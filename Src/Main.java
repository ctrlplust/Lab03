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

    public static void main(String[] args) {
        ArrayList<Game> juegos = GenerateData.generarJuegos(10000);

        probarOrdenamientos(juegos, Comparator.comparingInt(Game::getPrice), "precio");
        probarOrdenamientos(juegos, Comparator.comparing(Game::getCategory), "categoría");
        probarOrdenamientos(juegos, Comparator.comparingInt(Game::getQuality), "calidad");
    }
}