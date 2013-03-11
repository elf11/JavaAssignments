import java.util.TreeSet;


/**
 * @author Niculaescu Oana 321 CB
 * Clasa ce extinde clasa abstracta AbstractGeneral si implementeaza metodele specifice acesteia.
 * Astfel se realizeaza metodele specifice Folderelor din sistem.Se implementeaza un sistem de fisiere de utilizand 
 * TreeSet si fiecare Folder are un continut de acest tip pentru o parcurgere eficienta a acestora.
 *
 */
public class Folder extends AbstractGeneral{

	
	TreeSet<AbstractGeneral> content = new TreeSet<AbstractGeneral>();
	String type;
	
	/**
	 * Constructor ce mosteneste clasa AbstractGeneral si initializeaza fiecare Folder/Director.
	 * @param nume String - numele Folderului ce se doreste initializat.
	 * @param type String - tipul default al folderului, acesta va fi intotdeauna null.
	 * @param ownerUser user - utilizatorul care a creat folderul.
	 * @param ownerGroup String - grupul default al utilizatorului ce a creat Folderul.
	 */
	public Folder(String nume,String type, User ownerUser, String ownerGroup){
		super (nume,null,ownerUser,ownerGroup);
	}
	
	public void getContent(){}
	
	
	/**
	 * Metoda ce implementeaza modul de parcurgere al arborelui de Foldere si implicit al fiecarui Folder in parte.
	 * @param nume string - numele Folderului caruia se doreste sa se cunoasca continutul.
	 * @return AbstractGeneral  -o ierarhie de Foldere.
	 */
	public AbstractGeneral getA(String nume) {
		for (AbstractGeneral o: content){
			if (o.getName().equals(nume))
				return o;
		}
		return null;
	}
	
	/**
	 * Meotda ce intoarce daca un Folder/Fisier se gaseste deja in interiorul Folderului tinta.
	 * @param nume String - numele Folderului in care cautam.
	 * @return boolean - daca s-a gasit sau nu.
	 */
	public boolean getFile(String nume){
		for(AbstractGeneral o: content){
			if(o.getName().equals(nume))
				return true;
		}
		return false;
	}
	
	/**
	 * Metoda ce intoarce o lista a tuturor Folderelor/Fisierelor dintr-un anume Folder.
	 * @param f Folder - Folderul tinta in care se cauta.
	 * @return AbstractGeneral - lista de Foldere/Fisiere ce sunt continute de Folderul tinta.
	 */
	public AbstractGeneral list(Folder f){
		for(AbstractGeneral o: f.content){
			System.out.println(o.getName());
		}
		return null;
	}
	
	/**
	 * Metoda ce adauga un nou Folder/Fisier la structura de fisiere deja existente.
	 * @param a AbstractGeneral - Folderul/Fisierul ce se doreste adaugat.
	 */
	public void addA(AbstractGeneral a){
		content.add(a);
    }
	
	/**
	 * Metoda ce indeparteaza un Folder/Fisier din structura de fisiere deja existente.
	 * @param a AbstractGeneral - Folderul/Fisierul ce se doreste indepartat.
	 */
	public void removeA(AbstractGeneral a){
		content.remove(a);
	}
}
