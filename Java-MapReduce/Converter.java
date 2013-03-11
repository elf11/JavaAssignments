import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @author Oana Niculaescu
 * 
 * Converteste intrarile unui map intr-un ArrayList
 *
 */

public class Converter {
	
	/*public static <K, V> List<Entry<String, Integer>> convert(Entry<String, Map<String, Integer>> entry)
	{
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();
		
		for (Map<String, Integer> o : )
		{
			list.add((Entry<String, Integer>) o);
		}
		return list;
	}
	public Converter()
	{
	}*/
	
	public static Map<String, Float> converter(Map<String, Float> map)
	{
		Map<String, Float> reducedMap = new HashMap<String, Float>();
		
		for (Map.Entry<String, Float> entry : map.entrySet())
		{
			reducedMap.put(entry.getKey(), entry.getValue());
		}
		
		return reducedMap;
	}
	
	/*public static Map<String, Integer> converter(Map<String, Integer> map)
	{
		Map<String, Integer> reducedMap = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
			reducedMap.put(entry.getKey(), entry.getValue());
		}
		
		return reducedMap;
	}*/


}
