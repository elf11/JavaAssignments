import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.*;


public class Parser {
	
	static int nrSearched; //numarul de cuvinte cheie cautate
	static int fragmentSize; //dimensiunea fragmentului
	static int mostFreqWord; // numarul de cuvinte retinute pentru fiecare document
	static int returnDocs; // numarul de documente pe care dorim sa le intoarcem
	static int nrDocs; //numarul de documente in care se va face cautarea
	static String[] searched; //vectorul de cuvinte cheie cautate
	static String[] docs; //vecotul de documente
	final String outFileName;
	final ExecutorService executor;
	Map<String, Float> filterMap = new HashMap<String, Float>();
	final Map<String, List<Map<String, Integer>>> freqMap = new HashMap<String, List<Map<String, Integer>>>();
	final Map<String, Map<String, Integer>> intFreq = new HashMap<String, Map<String, Integer>>();
	final Map<String, Map<String, Float>> topWords = new HashMap<String, Map<String, Float>>();
	final Map<String, Map<String, Float>> floatFreq = new HashMap<String, Map<String, Float>>();
	
	
	public Parser(final int nrOfThreads, final String inputFile, final String outFile) throws NumberFormatException, IOException
	{
		FileReader fileR = null;
		BufferedReader input = null;
		
		try {
			fileR = new FileReader(new File(inputFile));
			input = new BufferedReader(fileR);
			nrSearched = Integer.parseInt(input.readLine());
			searched = new String[nrSearched];
			String aux = input.readLine();
			StringTokenizer st = new StringTokenizer(aux, " ");
			int i = 0;
			while (st.hasMoreTokens() && i < nrSearched)
			{
				searched[i] = st.nextToken();
				i++;
			}
			fragmentSize = Integer.parseInt(input.readLine());
			mostFreqWord = Integer.parseInt(input.readLine());
			returnDocs = Integer.parseInt(input.readLine());
			nrDocs = Integer.parseInt(input.readLine());
			docs = new String[nrDocs];
			
			for (i = 0; i < nrDocs; i ++)
			{
				docs[i] = input.readLine();
			}
			
		} finally {
			try
			{
				if (fileR != null)
				{
					fileR.close();
				}
				if (input != null)
				{
					input.close();
				}
			} catch (IOException e)
			{
				
			}
		}
		
		outFileName = outFile;
		executor = Executors.newFixedThreadPool(nrOfThreads);
		
	}
	
	public void map() throws IOException, InterruptedException, ExecutionException
	{
		final Map<Fragment, Future<Map<String, Integer>>> futureMap = new HashMap<Fragment, Future<Map<String, Integer>>>();
		
		//fiecare fiesier este impartit de master in fragmente de fragmentSize si apoi este trimis ca task workerilor
		
		for (final String doc : docs)
		{
			FileReader fr = null;
			BufferedReader br = null;
			
			try {
				fr = new FileReader(new File(doc));
				br = new BufferedReader(fr);
				
				long curr = 0;
				long skip;
				
				do {
					skip = fr.skip(fragmentSize);
					Fragment frag = new Fragment(doc, curr, curr + skip);
					futureMap.put(frag, executor.submit(new Mapping(frag)));
					curr += skip;
				} while (skip == fragmentSize);
				
			} finally {
				try {
					if (fr != null)
					{
						fr.close();
					}
					if (br != null)
					{
						br.close();
					}
				} catch (IOException e)
				{}
				
			}
			
			for (Map.Entry<Fragment, Future<Map<String, Integer>>> entry : futureMap.entrySet())
			{
				String fN = entry.getKey().fileName;
				// calculez numarul de aparitii in fragmentul curent
				Map<String, Integer> fragNr = entry.getValue().get();
				// lista de aparitii a cuvintelor pentru fisierul curent
				List<Map<String, Integer>> fragList = freqMap.get(fN);
				
				if (fragList == null)
				{
					List<Map<String, Integer>> mL = new ArrayList<Map<String, Integer>>();
					mL.add(fragNr);
					freqMap.put(fN, mL);
				} else {
					fragList.add(fragNr);
				}
			}
		}
	}
	
	// master thread ofera taskuri de reducere - se aduna frecventele cuvintelor din vectorii obtinuti pentru fiecare fragment
	// dintr-un document, se pastreaza primele N cuvinte cu cele mai mari frecvente pentru fiecare document
	// Map<String, Map<String, Float>> topWords
	public void reduceFirst() throws InterruptedException, ExecutionException
	{
		Map<String, Future<Map<String, Float>>> partialSolution = new HashMap<String, Future<Map<String, Float>>>();
				
				
		//iterare prin mapul de aparitii, crearea unui nou task de reduce si stocarea rezultatului in solutii partiale
		for (Map.Entry<String, List<Map<String, Integer>>> entry : freqMap.entrySet() )
		{
			partialSolution.put(entry.getKey(), executor.submit(new FirstReduce(entry.getValue())));
		}
				
		// vin rezultatele partiale, pe care le pun in mapul de frecvente partiale
		for (Entry<String, Future<Map<String, Float>>> entry1 : partialSolution.entrySet())		
		{
			// am facut o lista cu toate cuvintele dintr-un document si frecventele acestora
			Map<String, Float> reducedMap1 = Converter.converter(entry1.getValue().get());
			//trebuie sa sortam noul map creat in ordine descrescatoare a frecventelor
			//transformam mapul intr-o lista de entry-uri
			List<Map.Entry<String, Float>> rList = SecondConverter.convert(reducedMap1);
			List<Map.Entry<String, Float>> sortedList = new ArrayList<Map.Entry<String, Float>>();
			
					
			Collections.sort(rList, new Comp<String, Float>());
			
			System.out.println("Afisam lista redusa ");
			for (int i = 0; i < rList.size(); i++)
			{
				System.out.println(rList.get(i).getKey() + " " + rList.get(i).getValue());
			}
						
			//repunem intr-un map primele mostFrequent cuvinte cu frecventa maxima din fiecare document
		
			Map<String, Float> searchedMap = new HashMap<String, Float>();
				
			int j = 0;
			for (int i = rList.size() - 1; i >= 0 && j < mostFreqWord ; i--)
			{
				searchedMap.put(rList.get(i).getKey(), rList.get(i).getValue());
				j++;
			}
					
			topWords.put(entry1.getKey(), searchedMap);	
		}
	}
	
	//determinarea documentelor care contin in vectorul de cuv cu frecvente maxime de aparitie, toate cele NC cuvinte primite
	//dupa care se face cautarea
	public void reduceSecond() throws IOException, InterruptedException, ExecutionException
	{
		Map<String, Future<Map<String, Integer>>> futureMap = new HashMap<String, Future<Map<String, Integer>>>();
		Map<String, Map<String, Float>> finalMap = new HashMap<String, Map<String, Float>>();
		List<String> finalList = new ArrayList<String>();
			
		//iterez prin documentele disponibile si le trimit workerilor
		for (Map.Entry<String, Map<String, Float>> entry : topWords.entrySet())
		{
			futureMap.put(entry.getKey(), executor.submit(new SecondReduce(entry.getValue())));
		}
			
		//TODO: Ca sa scapi de chestia cu object poti sa bagi direct in mapReduce2 faza cu sa iti faca noul map 
		//vezi ca ai codul mai jos incepand cu linia 251, dar cand le scoti trebuie sa scoti incepand cu linia 237
			
			
		// vin rezultatele pe care le pun in mapul future results
		for (Entry<String, Future<Map<String, Integer>>> entry : futureMap.entrySet())
		{
			//facem o lista cu cuvintele cautate si daca acestea apar sau nu in documentul curent
			Map<String, Integer> reducedMap2 = ConverterObject.converter(entry.getValue().get());
				
			// parcurgem mapul redus 2 si vedem daca ne-a fost intors vreun false, daca a fost intors vreun false
			// atunci nu afisam documentul, deoarece nu avem toate cuvintele cautate in el.
			int ok = 0;
			for (Map.Entry<String, Integer> e : reducedMap2.entrySet())
			{
				if (e.getValue() == 0)
				{
					ok = 1;
				}
			}
				
			//nu ne-a fost introdus niciun false, deci avem toate cuvintele cautate in document
			// putem sa il adaugam in lista de documente pentru care vom afisa frecventele
			if (ok == 0)
			{
				finalList.add(entry.getKey());
			}
		}
		
		for (int i = 0; i < finalList.size(); i++)
		{
			for (Map.Entry<String, Map<String, Float>> entry : topWords.entrySet())
			{
				if (finalList.get(i).equals(entry.getKey()))
				{
					finalMap.put(finalList.get(i), entry.getValue());
				}
			}
		}
			
			
		FileWriter fw = new FileWriter(new File(outFileName));
		PrintWriter writer = new PrintWriter(new BufferedWriter(fw));

		writer.print("Rezultate pentru: (");
			
			// daca numarul de documente in care se gasesc toate cuvintele cautate este mai mic
			// decat numarul de documente ce se doreau initial intoarse atunci afiseaza doar acele documente
			// care respecta conditiile deci modifica numarul de documente ce le vrei intoarse
		if (finalList.size() < returnDocs)
		{
			returnDocs = finalList.size();
		}
			
		for (int i = 0; i < nrSearched - 1; i ++)
		{
			writer.print(searched[i]);
			writer.print(", ");
		}
			
		writer.print(searched[nrSearched - 1] + ")");
		writer.println();
		
			
		for (Map.Entry<String, Map<String, Float>> entry : finalMap.entrySet())
		{
			writer.print(entry.getKey() + "(");
			Map<String, Float> conv = Converter.converter(entry.getValue());
			
			for (int i = 0; i < nrSearched; i++)
			{
				for (Map.Entry<String, Float> e : conv.entrySet())
				{
					if (e.getKey().equals(searched[i]))
					{
						writer.print( Math.floor(100 * e.getValue()) / 100);
					}
				}
					
				if (i != nrSearched - 1)
				{
					writer.print(", ");
				} else
				{
					writer.print(")");
				}
			}
			writer.print(")");
			writer.println();
		}
			
		writer.close();
			
	}
	
	
	public void shutDown()
	{
		executor.shutdown();
	}
	
	public void parse() throws IOException, InterruptedException, ExecutionException
	{
		map();
		reduceFirst();
		reduceSecond();
	}
}
