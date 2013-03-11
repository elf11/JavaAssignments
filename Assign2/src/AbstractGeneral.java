

/**
 * @author Niculaescu Oana 321CB
 * Clasa abstracta ce implmenteaza Comparable<AbstractGeneral> si in care sunt definite principalele metode de lucru
 * atat cu Foldere cat si cu Fisiere.
 * Fiecare entitate AbstractGeneral are un nume, un tip, un user owner, un grup al ownerului si permisiuni.
 *
 */
public abstract class AbstractGeneral implements Comparable<AbstractGeneral> {
	
	private String name;
	
	private String type;
	
	private User ownerUser;
	
	private String ownerGroup;
	
	boolean[] permisiuni;
	
	/**
	 * Constructor ce initializeaza fiecare entitate Folder/Fisier.
	 * @param nume String - numele Folderului/Fisierului.
	 * @param type String - unul dintre tipurile definite in functia Main sau null in cazul Folderului.
	 * @param ownerUser user -userul ce va detine fisierul.
	 * @param ownerGroup String - grupul ce va detine fisierul.
	 */
	public AbstractGeneral(String nume,String type, User ownerUser, String ownerGroup){
		this.name=nume;
		this.type=type;
		this.ownerUser=ownerUser;
		this.ownerGroup=ownerGroup;
		permisiuni=new boolean[9];
		
		for (int i=0; i<=5; i++)
			permisiuni[i]=true;
		for(int i=6; i<=8; i++)
			permisiuni[i]=false;
	}
	
	/**
	 * Metoda ce intoarce permisiunile pentru fiecare entitate AbstractGeneral.
	 * @param f AbstractGeneral - entitatea careia vrem sa-i aflam permisiunile.
	 * @param u user - userul ce se doreste a se verifica daca este owner al obiectului.
	 * @param i - Tipul de permisiune 0 pentru read, 1 pentru write, 2 pentru execute.
	 * @return boolean - daca utilizatorul respectiv are sau nu anumite permisiuni pe obiectul respectiv.
	 */
	public boolean getPermisiune(AbstractGeneral f,User u,int i){
		if (permisiuni[6+i]==true)
			return true;
		
		if(f.ownerUser==null)
			return false;
		if (f.ownerUser.equals(u))
			return permisiuni[i];
		
		if (User.groupMembershipNotOwned(u,f.ownerGroup))
			return permisiuni[3+i];
		
		return false;
	}
	
	/**
	 * Metoda ce seteaza pemisiunile unui obiect de tipul AbstractGeneral.
	 * @param f AbstractGeneral - obiectul caruia se doreste sa se afle permisiunile.
	 * @param u user - userul in raport cu care se verifica anumite permisiuni.
	 * @param proprietar String - u = user, g =grup, a =all
	 * @param operator String - + pentru adaugare, - pentru scoatere, = pentru setare de permisiuni
	 * @param drepturi - r=read, w=write,x=execute
	 * @return boolean - returneaza daca permisiunile au fost setate corect sau nu.
	 */
	public boolean setPermisiune(AbstractGeneral f, User u, String proprietar, String operator,char[] drepturi){
		if(f.ownerUser.equals(u)){
			if(operator.equals("+") || operator.equals("=")){
				if(proprietar.equals("u")){
					for(int k=0; k<drepturi.length; k++){
						if(drepturi[k]=='r')
							f.permisiuni[0]=true;
						else 
							if(drepturi[k]=='w')
								f.permisiuni[1]=true;
							else
								f.permisiuni[2]=true;
					}
					return true;
				}
				else
					if(proprietar.equals("g")){
						for(int k=0; k<drepturi.length; k++){
							if(drepturi[k]=='r')
								f.permisiuni[3+0]=true;
							else 
								if(drepturi[k]=='w')
									f.permisiuni[3+1]=true;
								else
									f.permisiuni[3+2]=true;
						}
						return true;
					}
					else 
						if(proprietar.equals("a")){
							for(int k=0; k<drepturi.length; k++){
								if(drepturi[k]=='r')
									f.permisiuni[6+0]=true;
								else 
									if(drepturi[k]=='w')
										f.permisiuni[6+1]=true;
									else
										f.permisiuni[6+2]=true;
							}
							return true;
						}
				return false;
			}
			else
			if(operator.equals("-")){
				if(proprietar.equals("u")){
					for(int k=0; k<drepturi.length; k++){
						if(drepturi[k]=='r')
							f.permisiuni[0]=false;
						else 
							if(drepturi[k]=='w')
								f.permisiuni[1]=false;
							else
								f.permisiuni[2]=false;
					}
					return true;
				}
				else
					if(proprietar.equals("g")){
						for(int k=0; k<drepturi.length; k++){
							if(drepturi[k]=='r')
								f.permisiuni[3+0]=false;
							else 
								if(drepturi[k]=='w')
									f.permisiuni[3+1]=false;
								else
									f.permisiuni[3+2]=false;
						}
						return true;
					}
					else 
						if(proprietar.equals("a")){
							for(int k=0; k<drepturi.length; k++){
								if(drepturi[k]=='r')
									f.permisiuni[6+0]=false;
								else 
									if(drepturi[k]=='w')
										f.permisiuni[6+1]=false;
									else
										f.permisiuni[6+2]=false;
							}
							return true;
						}
				return false;
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Metoda ce compara doua obiecte de tipul AbstractGeneral.
	 */
	public int compareTo(AbstractGeneral o){
		return name.compareTo(o.name);
	}
	
	/**
	 * Returneaza continutul unui obiect de tipul AbstractGeneral.
	 */
	public abstract void getContent();

	/**
	 * Returneaza numele unui obiect de tipul AbstractGeneral.
	 * @return String - numele obiectului
	 */
	public String getName() {
		return name;
	}

	/**
	 * Seteaza numele unui obiect de tipul AbstractGeneral.
	 * @param name String - numele setat.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Afla tipul unui obiect AbstractGeneral, tip definit in functia Main sau null pentru Foldere.
	 * @return String - tipul obiectului.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Seteaza tipul unui obiect AbstractGeneral.
	 * @param type String - tipul setat.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Afla ownerul unui obiect de tipul AbstractGeneral.
	 * @return user - intoarce ownerul obiectului
	 */
	public User getOwnerUser() {
		return ownerUser;
	}
	
	/**
	 * Seteaza ownerul unui obiect de tipul AbstractGeneral.
	 * @param ownerUser user - ownerul setat
	 */
	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	/**
	 * Afla grupul caruia apartine un obiect AbstractGeneral.
	 * @return String - numele grupului.
	 */
	public String getOwnerGroup() {
		return ownerGroup;
	}

	/**
	 * Seteaza grupul unui obiect de tipul AbstractGeneral.
	 * @param ownerGroup String - grupul ce trebuie setat.
	 */
	public void setOwnerGroup(String ownerGroup) {
		this.ownerGroup = ownerGroup;
	}


}
