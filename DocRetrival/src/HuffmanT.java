
import java.util.ArrayList;

public class HuffmanT {
	
	Node<Character> tata;
	ArrayList<Node> huff = new ArrayList<Node>();
	ArrayList<Node> lista = new ArrayList<Node>();
	
	/**
	 * 
	 * Construim un nou arbore Huffman pornind de la radacina
	 * 
	 */
	
	HuffmanT(){
		tata = new Node((char) 0);
	}
	
	/**
	 * Metoda care creeaza un nou arbore, pornind de la o lista sortata
	 * primeste lista ca parametru si apoi pe baza listei(care este sortata in functie de
	 * frecventa de aparitie a caracterelor) construieste un nou arbore Huffman
	 * 
	 * @param lista ListaSortata 
	 */
	
	public void Arbore(ListaSortata lista){
		
		this.huff=lista.getLista();
		
		for(int i=0; i<this.huff.size(); i++){
			getListaSortat().add(this.huff.get(i));
		}
		
		this.tata.setCaracter(this.huff.get(0).getCaracter());
        this.huff.remove(0);
        
        
        while(huff.isEmpty()==false){
        	
        	Node drept = new Node((char) 0);
        	Node stang = new Node(huff.get(0).getCaracter());
        	
        	huff.remove(0);
        	
        	drept.setStangNod(tata.getStangNod());
        	drept.setDreptNod(tata.getDreptNod());
        	drept.setCaracter(tata.getCaracter());
        	
        	this.tata.setStangNod(stang);
        	this.tata.setDreptNod(drept);
        	this.tata.setCaracter((char) 0);
        	
        }
	}
	
	
	/**
	 * 
	 * Metoda care gaseste un caracter in nod
	 * 
	 * @param cautat char caracterul cautat
	 * @param nod Node nodul arborelui de la care se porneste cautarea
	 * @return boolean (gasit sau nu)
	 */
	
	public boolean gasesteCaracter(char cautat, Node nod){
		String compare=nod.toString();
		
		for(int i=0; i<compare.length(); i++){
			if(compare.charAt(0)==cautat)
				return true;
		}
		
		return false;
	}
	
	/**
	 * Metoda care returneaza codul binar al caracterului
	 * 
	 * @param nod - Node locul in arbore de unde porneste cautarea
	 * @param c - char caracterul al carui cod binar dorim sa-l aflam
	 * @param cod - String codul binar cautat
	 * @return String - codul binar cautat
	 */
	
	public String getHuff(Node nod, char c, String cod){
		
		String cod1;
		if (nod==null)
			return null;
		if(gasesteCaracter(c,nod))
				return cod;
		cod1=getHuff(nod.getStangNod(),c,cod+"0");
			if (cod1!=null)
				return cod1;
		return getHuff(nod.getDreptNod(),c,cod+"1");
		
				
	}
	
	/**
	 * Metoda care returneaza lista curenta
	 * @return ArrayList<Node>
	 */
	public ArrayList<Node> getListaSortat(){
		return lista;
	}
}


