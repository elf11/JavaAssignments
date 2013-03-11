import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * 
 * 
 * @author Oana Niculaescu
 *
 */


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException, InterruptedException, ExecutionException
	{
		if (args.length < 3)
		{
			System.out.println("Programul se apeleaza astfel: java Main number_threads fisin fisout");
		} else
		{
			Parser pars = new Parser(Integer.parseInt(args[0]), args[1], args[2]);
			pars.parse();
			pars.shutDown();
		}
	}
}