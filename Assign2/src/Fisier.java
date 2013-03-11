

/**
 * @author Niculaescu Oana 321CB
 * Clasa ce extinde clasa abstracta AbstractGeneral si implementeaza toate metodele acesteia intr-un mod in care
 * lucrul cu fisiere sa fie favorizat.
 * Fiecare fisier are un continut(content) si un tip(type),cel din urma fiind unul dintre tipurile definit in clasa
 * Main.
 *
 */
public class Fisier extends AbstractGeneral{
	
	
	public String content;
	public String type;
	
	/**
	 * Constructor ce initializeaza un obiect de tipul AbstractGeneral la tipul fisier.
	 * @param nume String - numele fisierului ce se doreste a fi initializat.
	 * @param type String - tipul fisierului, ce  afost definit in clasa Main.
	 * @param ownerUser user - ownerul fisierului.
	 * @param ownerGroup String - grupul default de care apartine fisierul.
	 * @param continut String - continutul cu care va fi initializat fisierul.
	 */
	public Fisier(String nume,String type, User ownerUser, String ownerGroup,String continut){
		super (nume,type,ownerUser,ownerGroup);
		this.type=type;
		if (!type.equals("executabil"))
			this.content=continut;
		else
			this.content=null;
	}
	
	/**
	 * Metoda ce intoarce continutul unui fisier.
	 */
	public void getContent(){
		if(this.type.equals("executabil"))
			System.err.println("Eroare");
		else
			System.out.println(getType()+" "+content);
	}
	
	/**
	 * Metoda ce scrie un anumit continut intr-un fisier.
	 * @param text String - textul/continutul ce trebuie scris intr-un anumit fisier.
	 */
	public void writeC(String text){
		content=text;
	}
	
	
}