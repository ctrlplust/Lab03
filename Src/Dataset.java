import java.util.*;
public class Dataset {
    private ArrayList<Game> data;
    private String sortedByAttribute = "";

    public Dataset(ArrayList<Game> data) {
        this.data = data;
    }

public ArrayList<Game> getGamesByPrice(int price) {
    ArrayList<Game> result = new ArrayList<>();
    if ("price".equals(sortedByAttribute)) {
        // Búsqueda binaria
        Comparator<Game> comparator = Comparator.comparingInt(Game::getPrice);
        int idx = Collections.binarySearch(data, new Game(null, null, price, 0), comparator);
        if (idx < 0) return result; // No encontrado
        // Expandir a izquierda y derecha
        int left = idx, right = idx;
        while (left > 0 && data.get(left - 1).getPrice() == price) left--;
        while (right < data.size() - 1 && data.get(right + 1).getPrice() == price) right++;
        for (int i = left; i <= right; i++) result.add(data.get(i));
        return result;
    } else {
        // Búsqueda lineal
        for (Game g : data) if (g.getPrice() == price) result.add(g);
        return result;
    }
}

    public ArrayList<Game> getGamesByPriceRange(int lowerPrice, int higherPrice) {
        ArrayList<Game> result = new ArrayList<>();
        if ("price".equals(sortedByAttribute)) {
            int low = 0, high = data.size() - 1;
            // Búsqueda binaria para encontrar el rango de precios
            while (low <= high) {
                int mid = (low + high) / 2;
                int midPrice = data.get(mid).getPrice();
                if (midPrice < lowerPrice) {
                    low = mid + 1;
                } else if (midPrice > higherPrice) {
                    high = mid - 1;
                } else {
                    // Encontró un precio dentro del rango
                    int left = mid, right = mid;
                    while (left - 1 >= 0 && data.get(left - 1).getPrice() >= lowerPrice) {
                        left--;
                    }
                    while (right + 1 < data.size() && data.get(right + 1).getPrice() <= higherPrice) {
                        right++;
                    }
                    for (int i = left; i <= right; i++) {
                        result.add(data.get(i));
                    }
                    break;
                }
            }
        } else {
            // Búsqueda lineal si no está ordenado por precio
            for (Game game : data) {
                if (game.getPrice() >= lowerPrice && game.getPrice() <= higherPrice) {
                    result.add(game);
                }
            }
        }
        return result;
}

    public ArrayList<Game> getGamesByCategory(String category) {
        ArrayList<Game> result = new ArrayList<>();
        if ("category".equals(sortedByAttribute)) {
            int low = 0, high = data.size() - 1;
            int found = -1;
            // Búsqueda binaria para encontrar una posición con la categoría buscada
            while (low <= high) {
                int mid = (low + high) / 2;
                int cmp = data.get(mid).getCategory().compareTo(category);
                if (cmp == 0) {
                    found = mid;
                    break;
                } else if (cmp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            // Si se encontró, buscar todos los juegos con esa categoría a izquierda y derecha
            if (found != -1) {
                int left = found;
                while (left - 1 >= 0 && data.get(left - 1).getCategory().equals(category)) {
                    left--;
                }
                int right = found;
                while (right + 1 < data.size() && data.get(right + 1).getCategory().equals(category)) {
                    right++;
                }
                for (int i = left; i <= right; i++) {
                    result.add(data.get(i));
                }
            }
        } else {
            // Búsqueda lineal si no está ordenado por categoría
            for (Game game : data) {
                if (game.getCategory().equals(category)) {
                    result.add(game);
                }
            }
        }
        return result;
    }

    public ArrayList<Game> getGamesByQuality(int quality) {
        ArrayList<Game> result = new ArrayList<>();
        if ("quality".equals(sortedByAttribute)) {
            int low = 0, high = data.size() - 1;
            int found = -1;
            // Búsqueda binaria para encontrar una posición con la calidad buscada
            while (low <= high) {
                int mid = (low + high) / 2;
                int midQuality = data.get(mid).getQuality();
                if (midQuality == quality) {
                    found = mid;
                    break;
                } else if (midQuality < quality) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            // Si se encontró, buscar todos los juegos con esa calidad a izquierda y derecha
            if (found != -1) {
                int left = found;
                while (left - 1 >= 0 && data.get(left - 1).getQuality() == quality) {
                    left--;
                }
                int right = found;
                while (right + 1 < data.size() && data.get(right + 1).getQuality() == quality) {
                    right++;
                }
                for (int i = left; i <= right; i++) {
                    result.add(data.get(i));
                }
            }
        } else {
            // Búsqueda lineal si no está ordenado por calidad
            for (Game game : data) {
                if (game.getQuality() == quality) {
                    result.add(game);
                }
            }
        }
        return result;
    }

public void sortByAlgorithm(String algorithm, String attribute) {
    Comparator<Game> comparator;
    switch (attribute) {
        case "price":
            comparator = Comparator.comparingInt(Game::getPrice);
            break;
        case "category":
            comparator = Comparator.comparing(Game::getCategory);
            break;
        case "quality":
            comparator = Comparator.comparingInt(Game::getQuality);
            break;
        default:
            comparator = Comparator.comparingInt(Game::getPrice);
            attribute = "price"; // Actualiza el atributo real
    }
    switch (algorithm) {
        case "bubbleSort":
            bubbleSort(comparator);
            break;
        case "quickSort":
            quickSort(0, data.size() - 1, comparator);
            break;
        case "countingSort":
            countingSort();
            attribute = "quality"; // countingSort siempre ordena por calidad
            break;
        default:
            data.sort(comparator);
    }
    this.sortedByAttribute = attribute;
}

    // Métodos de ordenamiento auxiliares

    private void bubbleSort(Comparator<Game> comparator) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(data.get(j), data.get(j + 1)) > 0) {
                    Collections.swap(data, j, j + 1);
                }
            }
        }
    }

    private void insertionSort(Comparator<Game> comparator) {
        int n = data.size();
        for (int i = 1; i < n; i++) {
            Game key = data.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(data.get(j), key) > 0) {
                data.set(j + 1, data.get(j));
                j--;
            }
            data.set(j + 1, key);
        }
    }

    private void selectionSort(Comparator<Game> comparator) {
        int n = data.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(data.get(j), data.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(data, i, minIdx);
        }
    }

    private ArrayList<Game> mergeSort(ArrayList<Game> list, Comparator<Game> comparator) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        ArrayList<Game> left = mergeSort(new ArrayList<>(list.subList(0, mid)), comparator);
        ArrayList<Game> right = mergeSort(new ArrayList<>(list.subList(mid, list.size())), comparator);
        return merge(left, right, comparator);
    }

    private ArrayList<Game> merge(ArrayList<Game> left, ArrayList<Game> right, Comparator<Game> comparator) {
        ArrayList<Game> merged = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                merged.add(left.get(i++));
            } else {
                merged.add(right.get(j++));
            }
        }
        while (i < left.size()) merged.add(left.get(i++));
        while (j < right.size()) merged.add(right.get(j++));
        return merged;
    }

    private void quickSort(int low, int high, Comparator<Game> comparator) {
        if (low < high) {
            int pi = partition(low, high, comparator);
            quickSort(low, pi - 1, comparator);
            quickSort(pi + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<Game> comparator) {
        Game pivot = data.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(data.get(j), pivot) <= 0) {
                i++;
                Collections.swap(data, i, j);
            }
        }
        Collections.swap(data, i + 1, high);
        return i + 1;
    }

    // Ordena la lista data por calidad usando Counting Sort (de 0 a 100)
    private void countingSort() {
        int maxQuality = 100;
        List<List<Game>> buckets = new ArrayList<>(maxQuality + 1);
        // Inicializa los buckets
        for (int i = 0; i <= maxQuality; i++) {
            buckets.add(new ArrayList<>());
        }
        // Distribuye los juegos en los buckets según su calidad
        for (Game g : data) {
            buckets.get(g.getQuality()).add(g);
        }
        // Reconstruye la lista data ordenada por calidad
        data.clear();
        for (List<Game> bucket : buckets) {
            data.addAll(bucket);
        }
    }

}