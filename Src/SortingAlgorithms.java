import java.util.*;

public class SortingAlgorithms {

    public static void bubbleSort(ArrayList<Game> list, Comparator<Game> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                }
            }
        }
    }

    public static void insertionSort(ArrayList<Game> list, Comparator<Game> comparator) {
        int n = list.size();
        for (int i = 1; i < n; i++) {
            Game key = list.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static void selectionSort(ArrayList<Game> list, Comparator<Game> comparator) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (comparator.compare(list.get(j), list.get(minIdx)) < 0) {
                    minIdx = j;
                }
            }
            Collections.swap(list, i, minIdx);
        }
    }

    public static void mergeSort(ArrayList<Game> list, Comparator<Game> comparator) {
        if (list.size() > 1) {
            int mid = list.size() / 2;
            ArrayList<Game> left = new ArrayList<>(list.subList(0, mid));
            ArrayList<Game> right = new ArrayList<>(list.subList(mid, list.size()));

            mergeSort(left, comparator);
            mergeSort(right, comparator);

            merge(list, left, right, comparator);
        }
    }

    private static void merge(ArrayList<Game> list, ArrayList<Game> left, ArrayList<Game> right, Comparator<Game> comparator) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (comparator.compare(left.get(i), right.get(j)) <= 0) {
                list.set(k++, left.get(i++));
            } else {
                list.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            list.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            list.set(k++, right.get(j++));
        }
    }

    public static void quickSort(ArrayList<Game> list, Comparator<Game> comparator) {
        quickSortHelper(list, 0, list.size() - 1, comparator);
    }

    private static void quickSortHelper(ArrayList<Game> list, int low, int high, Comparator<Game> comparator) {
        if (low < high) {
            int pi = partition(list, low, high, comparator);
            quickSortHelper(list, low, pi - 1, comparator);
            quickSortHelper(list, pi + 1, high, comparator);
        }
    }

    private static int partition(ArrayList<Game> list, int low, int high, Comparator<Game> comparator) {
        Game pivot = list.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(list.get(j), pivot) <= 0) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, high);
        return i + 1;
    }

    public static void collectionsSort(ArrayList<Game> list, Comparator<Game> comparator) {
        Collections.sort(list, comparator);
    }
}