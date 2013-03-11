import java.util.concurrent.Callable;
import java.util.*;
import java.util.Map.Entry;


/**
 * 
 * @author Oana Niculaescu
 * 
 * Operatia 2 de reducere, se determina documentele care contin
 * in vectorul de cuvinte cu frecventa maxima de aparitie, toate
 * cele NC cuvinte cheie primite.
 *
 */

public class SecondReduce implements Callable<Map<String, Integer>> {
	
	Map<String, Float> map;

	public SecondReduce(Map<String, Float> map)
	{
		this.map = map;
	}
	
	// the fuck does this thing work?!?
	public Map<String, Integer> call() {
		Map<String, Integer> docMap = new HashMap<String, Integer>();
		int contains = 1;
		
		//verificam daca toate cuvintele cautate se gasesc intre primele N cuvinte cheie
		//daca unul dintre aceste cuvinte nu se gaseste intre cele N cuvinte, atunci fisierul
		// nu poate fi afisat si trimitem 0 inapoi
		
		for (int i = 0; i < Parser.nrSearched; i ++)
		{
			if (map.containsKey(Parser.searched[i]) == false)
			{
				contains = 0;
			}
			else {
				contains = 1;
			}
			docMap.put(Parser.searched[i], contains);
		}
		
/*		for (Entry<String, Float> entry : map.entrySet())
		{
			for (int i = 0; i < Parser.nrSearched; i++)
			{
				if (entry.getKey() != Parser.searched[i])
				{
					
				}
			}
		}
*/		
		//return contains;
		return docMap;
	}
	
}
