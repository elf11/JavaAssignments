
/**
 * 
 * @author Niculaesc Oana
 *
 * Clasa ce reprezinta un fragment dintr-un document.
 */

public class Fragment {
	
	final String fileName;
	final long start;
	final long end;
	
	public Fragment(String fileName, long start, long end)
	{
		this.fileName = fileName;
		this.start = start;
		this.end = end;
	}
}
