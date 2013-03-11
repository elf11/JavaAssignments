import java.util.*;


/**
 * 
 * @author Oana Niculaescu
 * 
 * Clasa ce se ocupa de transformarea unui hashmap intr-o lista de entryuri pentru a le putea sorta in functie de frecventa.
 *
 */

public class SecondConverter {
	
    public static <K, V> List<Map.Entry<K, V>> convert(Map<K, V> map) {
        return new ArrayList<Map.Entry<K, V>>(map.entrySet());
    }
}
