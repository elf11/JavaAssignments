import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


/**
 * @author Niculaescu Oana 321 CB
 * Clasa implementeaza modul in care fiecare utilizator din sistem va fi gestionat.
 * Fiecare utilizator are un nume, un grup, o lista de grupuri pe care le detine si o lista
 * de grupuri carora le este membru fara a le detine.
 *
 */
public class User {
	
	private String nume;
	private String grup;
	ArrayList<String> grupuri;
	ArrayList<String> grupuriMembre;
	static HashSet<User> ulist=new HashSet<User>();
	
	/**
	 * Constructor ce initializeaza fiecare user din sistem nou introdus.
	 * @param nume numele utilizatorului ce se doreste introdus in sistem.
	 */

	public User (String nume){
		this.nume=nume;
		grupuri=new ArrayList<String>();
		grupuriMembre=new ArrayList<String>();
		grup=null;
	}
	
	/**
	 * Meotda ce intoarce lista utilizatorilor din sistem.
	 * @param u userul ce se doreste a fi introdus in sistem
	 * @return boolean - intoarce daca utilizatorul a fost adaugat la lista a utilizatorilor din sistem sau nu
	 */
	
	public boolean addU(User u){
		return ulist.add(u);
	}
	
	/**
	 * Meotda ce intoarce daca unui utilizator i-a fost adaugat sau nu un nou grup la lista de
	 * grupuri detinute.
	 * @param g string - grupul ce se doreste adaugat
	 * @param u user - userul caruia se doreste sa se adauge grupul la lista de grupuri detinute.
	 * @return boolean - daca grupul a fost adaugat sau nu
	 */
	public static boolean addGroup(String g, User u){
		return u.grupuri.add(g); 
	}
	
	/**
	 * Metoda ce intarce daca unui utilizator i-a fost adaugat sau nu un nou grup la lista de
	 * grupuri din care face parte, nu grupuri detinute.
	 * @param g string - grupul ce se doreste adaugat
	 * @param u user - userul caruia se doreste sa se afauge grupul la lista de grupuri la care este membru
	 * @return boolean - daca grupul  a fost adaugat sau nu
	 */
	public static boolean addMembru(String g, User u){
		return u.grupuriMembre.add(g);
	}
	
	/**
	 * Metoda ce intoarce daca un grup a fost sters din lista de grupuri detinute ale unui utilizator.
	 * @param g string - grupul ce se doreste a fi sters din lista de grupuri detinute de utilizator
	 * @param u user - userul caruia se doreste a fi sters un grup din lista de grupuri detinute
	 * @return boolean - daca grupul a fost sters sau nu
	 */
	public static boolean delGroup(String g, User u){
		return u.grupuri.remove(g);
	}
	
	/**
	 * Metoda ce intoarce daca un grup a fost sters din lista de grupuri la care este membru un utilizator
	 * @param g string - grupul ce se doreste a fi sters din lista de grupuri la care utilizatorul este membru
	 * @param u user - userul caruia se doreste a fi sters grupul din lista de grupuri la care este membru
	 * @return boolean - daca grupul a fost sters sau nu
	 */
	public static boolean delMembru(String g,User u){
		return u.grupuriMembre.remove(g);
	}

	/**
	 * Metoda ce intoarce un anumit user din sistem
	 * @param string - numele utilizatorului cautat
	 * @return user - utilizatorul cautat
	 */
	public static User getUser(String utilizator) {
		for (User u: ulist)
			if (u.nume.equals(utilizator))
				return u;
		User aux;
		ulist.add(aux=new User(utilizator));
		return aux;
	}
	
	/**
	 * Meotda ce verifica daca un grup a fost creat de un anumit utilizator
	 * @param owner user - utilizatorul ce se verifica daca este owner al grupului
	 * @param group string - grupul ce se verifica daca este detinut de utilizatorul owner
	 * @return boolean - daca grupul este detinut sau nu de utilizatorul owner
	 */
	public static boolean groupMembershipOwned(User owner, String group){
		for (String o: owner.grupuri)
			if(o.equals(group))
				return true;
		return false;
	}
	
	/**
	 * Meotda ce verifica daca un grup face parte din lista de grupuri membre ale unui anumit utilizator
	 * @param owner user - utilizatorul in a carui lista de grupuri membre trebuie facuta verificarea
	 * @param group string - grupul ce este cautat
	 * @return boolean - daca grupul apartine sau nu listei de grupuri la care utilizatorul este membru
	 */
	public static boolean groupMembershipNotOwned(User owner, String group){
		for(String o: owner.grupuriMembre)
			if(o.equals(group))
				return true;
		return false;
	}

	/**
	 * Metoda ce intoarce grupul default al unui utilizator
	 * @param u user - userul al carui grup default trebuie aflat
	 * @return String - grupul default al utilizatorului 
	 */
	public static String getgrup(User u) {
		Iterator it = u.grupuri.iterator();
		if(it.hasNext())
			return ((String)it.next());
		return null;
	}
	
	/**
	 * Metoda ce seteaza grupul unui utilizator
	 * @param g - grupul ce trebuie setat
	 * @return grupul setat
	 */
	public String setGrup(String g){
		this.grup=g;
		return this.grup;
	}

	/**
	 * Metoda ce intoarce numele unui utilizator.
	 * @return String - numele utilizatorului cautat
	 */
	public String getNume() {
		return nume;
	}

}
