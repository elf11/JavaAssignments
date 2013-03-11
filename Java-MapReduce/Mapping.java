import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;


public class Mapping implements Callable<Map<String, Integer>>{

	final Fragment frag;
	Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
	static final String delimiters = "[ .,!?;:\\t\\n'\"()\\[\\}{}=\\-+/*@\\\\0-9]+";
	static final Pattern begin = Pattern.compile("^[a-zA-z]{2,} + [^a-zA-z]{0,}");
	static final Pattern anythingelse = Pattern.compile("[^a-zA-Z]");
	static final Pattern word = Pattern.compile("[a-zA-Z]++", Pattern.CASE_INSENSITIVE);
	
	public Mapping(Fragment frag)
	{
		this.frag =  frag;
	}
	
	public Map<String, Integer> call() throws IOException {
		
		final Map<String, Integer> freqMap = new HashMap<String, Integer>();
		FileReader fr = null;
		BufferedReader br = null;
		
		String fN = frag.fileName;
		try {
			fr = new FileReader(new File(fN));
			br = new BufferedReader(fr);
			
			StringBuilder builder;
			char[] buf;
			int accStart;
			long start = frag.start;
			
			// in interiorul unui fragment din mijlocul textului atunci trebuie sa citim si primul caracter imediat dinaintea  inceputului
			if (start > 0)
			{
				br.skip(start -  1);
				buf = new char[(int) (frag.end - start + 1)];
				accStart = 1;
			} else {
				// altfel pozitia de start calculata anterior este cea corecta, adica 0
				buf = new char[(int) (frag.end - start)];
				accStart = 0;
			}
			
			//citirea din buffer
			br.read(buf, 0, buf.length);
			
			//punem ce am citit intr-un string builder
			builder = new StringBuilder();
			builder.append(buf);
			
			
			//Vezi ca aici ai putea sa faci astfel incat daca citesti ultimul cuvant intreg apoi cand ii dai iarasi sa mearga 
			//in fisiere sa inceapa de unde  a citit ultima data -- e o posibilitate ca sa nu mai faci chestia cu begin.
			// fragment in mijlocul textului, daca ne gasim in mijlocul unui cuvant atunci il sarim
			if (start > 0)
			{
				//vezi cum faci aici hmmm
				Matcher skip = begin.matcher(builder);
				
				if (skip.find())
				{
					accStart = skip.end();
				}
			}
			
			// primul cuvant dispare
			builder.replace(0, accStart, "");
			
			//citire completa a ultimului cuvant
			if (Character.isLetter(buf[buf.length - 1]))
			{
				int rd;
				char ch;
				rd = br.read();
				ch = (char) rd;
				
				while ((rd >= 0) && Character.isLetter(ch))
				{
					builder.append(ch);
					rd = br.read();
					ch = (char) rd;
				}
			}
			
			Matcher matcher = word.matcher(builder);
			
			while (matcher.find())
			{
				// gasim fiecare cuvant din bufferul curent al fragmentului si il convertim la lowerCase
				String word = matcher.group().toLowerCase();
				//gasim frecventa fiecarui cuvant din hash mapul de frecvente
				Integer freq = freqMap.get(word);
				//daca frecventa este nula atunci adaugam cuvantul in map, altfel updatam intrarea din hash map pentru cuvantul procesat
				if (freq != null)
				{
					freqMap.put(word, freq + 1);
				} 
				else
				{
					freqMap.put(word, 1);
				}
				
				
			}
			
		} finally{
			try {
				if (fr != null)
				{
					fr.close();
				}
				if (br != null)
				{
					br.close();
				}
			} catch (IOException e) {}
		}
		/*
		for (Map.Entry<String, Integer> entry : freqMap.entrySet())
		{
			System.out.println(entry.getKey());
		}
		*/
		return freqMap;
	}
}
