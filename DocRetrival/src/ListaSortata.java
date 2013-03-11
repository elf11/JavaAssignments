import java.util.ArrayList;


/**
 * 
 * @author Oana Niculaescu
 * 
 * Clasa implementeaza ListaSortata pe care o vom folosi 
 * la construirea arborelui Huffman
 *
 */

public class ListaSortata {
	
	private ArrayList<Node> listaSortata;
	
	
	/**
	 * 
	 * Constructor al listei sortate
	 * 
	 */
	public ListaSortata(){
		listaSortata = new ArrayList<Node>();
	}
	
	
	/**
	 * 
	 * Metoda de verificare daca o lista este goala
	 * @return boolean 1/0
	 * 
	 */
	
	public boolean Empty(){
		if(listaSortata.size()==0)
			return true;
		else 
			return false;
	}
	
	
	/**
	 * 
	 * Metoda de adaugare a unui nou nod la lista
	 * @param nod Node nodul ce trebuie adaugat in lista
	 * 
	 */
	public void Add(Node nod){
		
		int lungime = listaSortata.size();
		
		/**
		 * Daca lista este goala adauga primul nod in aceasta, 
		 * altfel adauga noduri in lista in locul unde acestea trebuie inserate
		 * 
		 */
		
		if(this.Empty())
			this.listaSortata.add(nod);
		else{
			for(int i=0; i<this.listaSortata.size(); i++){
				if(nod.getFrecventa()<this.listaSortata.get(i).getFrecventa()){
					this.listaSortata.add(i,nod);
			    	break;
		     	}
		    }
			if(lungime==this.listaSortata.size())
				this.listaSortata.add(nod);
		}
	}
	
	/**
	 * Metoda de scoatere a unui nod din lista
	 * @return Node 
	 */
	
	public Node Remove(){
		return this.listaSortata.remove(0);
	}

	
	/**
	 * 
	 * Metoda de afisare a intregii liste
	 * @return ArrayList<Node>
	 * 
	 */
	
	public ArrayList<Node> getLista(){
		return listaSortata;
	}
	
	/**
	 * 
	 * Suprascriere a metodei toString - intoarce lista sortata
	 * 
	 */
	
	public String toString(){
		
		String sortat = "Lista sortata\n";
		
		for(int i=0; i<this.listaSortata.size(); i++){
			sortat=sortat+this.listaSortata.get(i).toString()+this.listaSortata.get(i).getFrecventa();
		}
		
		return sortat;
	}
}
