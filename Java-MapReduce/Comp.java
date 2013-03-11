import java.util.Map;
import java.util.Comparator;

public final class Comp<K, V extends Comparable<? super V>>
implements Comparator<Map.Entry<K, V>> {
	
	public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2)
	{
		if (o1.getValue().equals(o2.getValue()))
			return (o1.getKey().toString().compareTo(o2.getKey().toString()));
		return o1.getValue().compareTo(o2.getValue());
	}
}
