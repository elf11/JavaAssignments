import java.util.HashMap;
import java.util.Map;


public class ConverterObject {
	
	public static Map<String, Integer> converter(Map<String, Integer> map)
	{
		Map<String, Integer> reducedMap = new HashMap<String, Integer>();
		
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
			reducedMap.put(entry.getKey(), entry.getValue());
		}
		
		return reducedMap;
	}

}
