import java.util.*;
import java.util.concurrent.*;


/**
 * 
 * @author Oana Niculaescu
 * 
 * Clasa ce ia numaul de aparitii ale unui  cuvant dintr-un fragment si returneaza 
 * frecventele tuturor cuvintelor existente in acel fragment.
 *
 */

public class FirstReduce implements Callable<Map<String, Float>> {
//public class FirstReduce implements Callable<Map<String, Integer>> {

	final List<Map<String, Integer>> list;
	
	public FirstReduce(List<Map<String, Integer>> list)
	{
		this.list = list;
	}
	
	public Map<String, Float> call()
	//public Map<String, Integer> call()
	{
		Map<String, Float> floatFreq = new HashMap<String, Float>();
		Map<String, Integer> freq = new HashMap<String, Integer>();
		
		int wc = 0; //numarul de cuvinte numarate in document
		
		for (int i = 0; i < list.size(); i++)
		{
			for (Map.Entry<String, Integer> subEntry : list.get(i).entrySet())
			{
				//extragem cuvantul curent si numarul de aparitii al acestuia
				String word = subEntry.getKey();
				Integer occ = subEntry.getValue();
				
				Integer total = freq.get(word); //numarul total de aparitii pana in momentul curent
				
				if (total == null)
				{
					freq.put(word, occ);
				}
				else
				{
					freq.put(word, total + occ);
				}
				
				wc = wc + occ;
			}
		}
		
		
		/*for (Map<String, Integer> entry : list)
		{
			
		}*/
		
		//avem mapul de aparitii, cream mapul de aparitii in procente
		
		for (Map.Entry<String, Integer> entry : freq.entrySet())
		{
			floatFreq.put(entry.getKey(), (float) (((float)entry.getValue() / wc) * 100));
		}
		
		return floatFreq;
		
		//return freq;
	}
}
