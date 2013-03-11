

/**
 * 
 * @author Oana Niculaescu
 * 
 * Clasa ce construieste nodurile arborelui
 *
 * @param <N> Node
 */
public class Node<N> {
	
	private int frecventa;
	private char caracter;
	private Node<N> stang;
	private Node<N> drept;
	
	/**
	 * 
	 * Constructori ai nodului arborelui, ce va seta frecventa si caracterul
	 * specifice fiecarui nod
	 * @param c char c ~ caracterul a carui frecventa se doreste aflata
	 * @param f int f ~ frecventa caracterului
	 * 
	 */
	
	public Node(char c,int f){
		caracter=c;
		frecventa=f;
	}
	
	public Node(char c){
		caracter=c;
		frecventa=1;
	}
	
	/**
	 * 
	 * Metodele setter si getter pentru caracter
	 * @param caracter char
	 * 
	 */
	
	public void setCaracter(char caracter){
		this.caracter=caracter;
	}
	
	public char getCaracter(){
		return caracter;
	}
	
	/**
	 * 
	 * Metodele setter si getter pentru frecventa
	 * @param frecventa int
	 * 
	 */
	
	public void setFrecventa(int frecventa){
		this.frecventa=frecventa;
	}
	
	public int getFrecventa(){
		return frecventa;
	}
	
	/**
	 * 
	 * Metodele setter si getter pentru nodul drept
	 * @param drept Node
	 * 
	 */
	
	public void setDreptNod(Node drept){
		this.drept=drept;
	}
	
	public Node getDreptNod(){
		return drept;
	}

	/**
	 * 
	 * Metodele setter si getter pentru nodul stang
	 * @param stang Node
	 * 
	 */
	
	public void setStangNod(Node stang){
		this.stang=stang;
	}
	
	public Node getStangNod(){
		return stang;
	}
	
	/**
	 * 
	 * Suprascrierea metodei toString
	 * 
	 */
	
	public String toString(){
		String numeCaracter="";
		numeCaracter=numeCaracter+getCaracter();
		return numeCaracter;
	}

}
