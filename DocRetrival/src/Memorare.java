
/**
 * 
 * @author Oana Niculaescu
 * Clasa Memorare implementeaza un obiect cu doua campuri
 * caracter si cod, in care vom retine mai tarziu
 * codul binar al caracterului si simbolul acestuia
 *
 */

public class Memorare {
	
	char caracter;
	String cod;
	
	/**
	 * 
	 * Constructori cu si fara parametru
	 * care initializeaza campurile caracter si cod
	 * 
	 */
	
	public Memorare(){
		caracter='\u0000';
		cod="";
	}
	
	public Memorare(char c, String cod){
		this.caracter=c;
		this.cod=cod;
	}

}
