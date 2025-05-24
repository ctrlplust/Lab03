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
        int low = 0, high = data.size() - 1;
        int found = -1;
        // Búsqueda binaria para encontrar una posición con el precio buscado
        while (low <= high) {
            int mid = (low + high) / 2;
            int midPrice = data.get(mid).getPrice();
            if (midPrice == price) {
                found = mid;
                break;
            } else if (midPrice < price) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // Si se encontro el precio, recorre de izquierda a derecha 
        if(found != -1) {
            int left = found;
            while (left -1 >=0 && data.get(left -1).getPrice() == price) {
                left--;
            }
            int right = found;
            while (right + 1 < data.size() && data.get(right + 1).getPrice() == price) {
                right++;
            }
            for (int i = left; i <= right; i++) {
                result.add(data.get(i));
            }
        }
    } else {
        // Búsqueda lineal si no está ordenado por precio
        for (Game game : data) {
            if (game.getPrice() == price) {
                result.add(game);
            }
        }
    }
    return result;
}
}