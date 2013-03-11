import java.io.File;
import java.util.Iterator;


/**
 * @author Niculaescu Oana 321CB
 * Clasa ce implementeaza metodele aferente tuturor comenzilor enumerate in clasa Main.
 *
 */
public class Action{
	
	/**
	 * Metoda ce creaza un nou Folder.
	 * @param name String  - Folderul ce se doreste a fi creat(calea catre acesta).
	 * @param u user - userul caruia ii va apartine Folderul.
	 * @param tip String - tipul fisierului, in cazul Folderelor este null.
	 * @return boolean  - intoarce daca Folderul a fost creat cu succes sau nu.
	 */
	static public boolean mkdir(String name, User u,String tip) {
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		
		if (cale.length==0 || cale.length==1)
			return false;
		
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		if (dir!=null && dir instanceof Folder){
				if(!(((Folder)dir).getFile(cale[i]))){
					AbstractGeneral aux=new Folder(cale[i],tip,u,User.getgrup(u));
					if(((Folder)dir).getPermisiune(((Folder)aux),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)dir,u,1)) || (User.groupMembershipNotOwned(u,((Folder)dir).getOwnerGroup())) && ((Folder)dir).getPermisiune((Folder)dir,u, 1)) {
						((Folder)dir).addA(aux);
						return true;
					}
					return false;
				}
				return false;
		}
		return false;
	}
	
	/**
	 * Metoda ce sterge un Folder din ierarhia de fisiere.
	 * @param name String - numele fisierului ce se doreste sters(calea catre acesta)
	 * @param u user - userul pentru care se verifica daca are dreptul de a sterge Folderul.
	 * @return boolean - daca Folderul a fost sters cu succes sau nu.
	 */
	static public boolean rmdir(String name, User u){
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		
		if (cale.length==0 || cale.length==1)
			return false;
	
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		
		if (dir!=null && dir instanceof Folder){
			AbstractGeneral aux=((Folder)dir).getA(cale[i]);
			if(((Folder)dir).getPermisiune(((Folder)aux),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)aux,u,1)) || (User.groupMembershipNotOwned(u,((Folder)aux).getOwnerGroup())) && ((Folder)dir).getPermisiune((Folder)aux,u, 1)) {
				if(aux!=null){
					if(((Folder)aux).getPermisiune(((Folder)aux),u,1) || u.getNume().equals("root") || (u.equals(aux.getOwnerUser()) && ((Folder)aux).getPermisiune((Folder)aux,u,1)) || (User.groupMembershipNotOwned(u,((Folder)aux).getOwnerGroup())) && ((Folder)aux).getPermisiune((Folder)aux,u, 1)) {
						((Folder)dir).removeA(aux);
						return true;
					}
	
		        return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	
	/**
	 * Metoda ce creaza un nou Fisier in ierarhia de fisiere.
	 * @param name String - numele noului Fisier ce se doreste creat(calea catre acesta).
	 * @param u user - userul ce doreste crearea unui nou fisier.
	 * @param tip String - tipul noului fisier creat.
	 * @param content String  - continutul cu care va fi initializat noul fisier.
	 * @return boolean - daca fisierul a fost creat cu succes sau nu.
	 */
	static public boolean create(String name,User u,String tip,String content)
    {		
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		if (!tip.equals("html") && !tip.equals("txt") && !tip.equals("executabil") && !tip.equals("sursa") && !tip.equals("tex") && !tip.equals("mp3") && !tip.equals("zip") && !tip.equals("obiect"))
			return false;
	
		if (cale.length==0 || cale.length==1)
			return false;
	
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		if (dir!=null && dir instanceof Folder){
			AbstractGeneral aux=new Fisier(cale[i],tip,u,u.getgrup(u),content);
			if(((Folder)dir).getPermisiune(((Folder)dir),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)dir,u,1)) || (User.groupMembershipNotOwned(u,((Folder)dir).getOwnerGroup())) && ((Folder)dir).getPermisiune((Folder)dir,u, 1)) {
				((Folder)dir).addA(aux);
				return true;
			}
			return false;
		}
		
		return false;
    }
	
	/**
	 * Metoda ce sterge un Fisier din ierarhia de fisiere.
	 * @param name String - numele Fisierului ce se doreste sters(calea catre acesta).
	 * @param u user - userul care doreste stergerea fisierului.
	 * @return boolean -  daca stergerea  a avut loc cu succes sau nu.
	 */
	static public boolean delete(String name, User u) {
		
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		
		if (cale.length==0 || cale.length==1)
			return false;
		
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		
		if (dir!=null && dir instanceof Folder){
			AbstractGeneral aux=((Folder)dir).getA(cale[i]);
			if(((Folder)dir).getPermisiune(((Folder)dir),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)dir,u,1)) || (User.groupMembershipNotOwned(u,((Fisier)aux).getOwnerGroup())) && ((Folder)dir).getPermisiune((Fisier)aux,u, 1)) {
				((Folder)dir).removeA(aux);
				return true;
	    	}
	
			return false;
		}
		return false;
   }

	/**
	 * Metoda ce implementeaza citirea continutului unui Fisier.
	 * @param name String - numele fisierului al carui continut se doreste citit(calea catre acesta)
	 * @param u user -userul care doreste realizarea citirii.
	 * @return boolean - daca citirea  a avut loc  cu succes sau nu.
	 */
	public static boolean read(String name, User u) {
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		if (cale.length==0 || cale.length==1)
			return false;
		
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		
		if (dir!=null && dir instanceof Folder){
			AbstractGeneral aux=((Folder)dir).getA(cale[i]);
			if(aux!=null && !(aux instanceof Folder)){
				if(((Fisier)aux).getPermisiune(((Fisier)aux),u,0) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Fisier)aux).getPermisiune((Fisier)aux,u,0)) || (User.groupMembershipNotOwned(u,((Fisier)aux).getOwnerGroup())) && ((Fisier)dir).getPermisiune((Fisier)aux,u, 0)) {
					((Fisier)aux).getContent();
					return true;
				}
			return false;
			}
			return false;
		}
		
		return false;
	}
	
	/**
	 * Metoda ce implementeaza suprascrierea unui Fisier.
	 * @param name String - numele fisierului ce se doreste suprascris(calea catre acesta)
	 * @param u user - userul ce doreste suprascrierea.
	 * @param content String  - continutul ce se doreste sa fie scris in fisier.
	 * @return boolean - daca operatia a avut succes sau nu.
	 */
	public static boolean write(String name,User u,String content){
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		String type="";
		
		if (cale.length==0 || cale.length==1)
			return false;
		
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		
		if (dir!=null && dir instanceof Folder){
				AbstractGeneral aux=((Folder)dir).getA(cale[i]);
				if(((Folder)dir).getPermisiune(((Folder)dir),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)dir,u,1)) || (User.groupMembershipNotOwned(u,((Folder)dir).getOwnerGroup())) && ((Folder)dir).getPermisiune((Folder)dir,u, 1)) {
					if(((Fisier)aux).type.equals("executabil"))
						return false;
					((Fisier)aux).writeC(content);
					return true;
				}
				return false;
			
	    }
		
		return false;
		
	}
	
	/**
	 * Metoda ce implemnteaza executia unui fisier executabil.
	 * @param name String - numele fisierului ce se doreste executat(calea catre acesta).
	 * @param u user - userul ce doreste executarea fisierului.
	 * @return boolean -daca executare s-a realizat cu succes sau nu.
	 */
	public static boolean execute(String name, User u){
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;

	
		if (cale.length==0 || cale.length==1)
			return false;
	
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		if (dir!=null && dir instanceof Folder){
			
			AbstractGeneral aux=((Folder)dir).getA(cale[i]);
			if(aux instanceof Fisier){
				if(((Fisier)aux).getPermisiune(((Fisier)aux),u,2) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Fisier)aux).getPermisiune((Fisier)aux,u,2)) || (User.groupMembershipNotOwned(u,((Fisier)aux).getOwnerGroup())) && ((Fisier)dir).getPermisiune((Fisier)aux,u, 2)) {
					if(((Fisier)aux).type.equals("executabil")) 
						return true;
				return false;
				}
				return false;
			}
			return false;
		}
	
		return false;
		
	}
	
	/**
	 * Metoda ce implementeaza listarea continutului unui Folder.
	 * @param name String  - numele Folderului ce se doreste listat(calea catre acesta).
	 * @param u user - userul ce doreste listarea.
	 * @return boolean - daca listarea s-a realizat cu succes sau nu.
	 */
	public static boolean ls(String name, User u){
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		
		for (i=1; i<=cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		
		if (dir!=null && dir instanceof Folder){
			if(((Folder)dir).getPermisiune(((Folder)dir),u,2) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)dir,u,2)) || (User.groupMembershipNotOwned(u, ((Folder)dir).getOwnerGroup())) && ((Folder)dir).getPermisiune((Folder)dir,u, 2)) {
				((Folder)dir).list((Folder)dir);
				return true;
			}
			return false;
		}
       return false;
	}


	/**
	 * Metoda ce implementeaza adaugarea unui nou grup de utilizatori in lista de utilizatori creati ai
	 * unui user.
	 * @param group String - numele grupului de utilizatori ce se doreste adaugat.
	 * @param u user - userul ce doreste adaugarea
	 * @return boolean - daca metoda s-a realziat cu succes sau nu.
	 */
	public static boolean groupadd(String group, User u){
		User.ulist.add(u);
		User.addGroup(group,u);
		return true;
	}
	
	/**
	 * Metoda ce implementeaza stergerea unui grup de utilizatori din lista de utilizatori creati ai 
	 * unui user.
	 * @param group String - grupul ce se doreste sters.
	 * @param u user - userul ce incearca sa realizeze operatia.
	 * @return boolean - daca operatia a fost realizata cu succes sau nu.
	 */
	public static boolean groupdel(String group, User u){
		if(!User.groupMembershipOwned(u,group) && !u.equals("root")) 
			return false;
		User.delGroup(group,u);
		AbstractGeneral dir=Main.root;
		Iterator it =(u.grupuri).iterator();
		String grup="";
		if(it.hasNext())
			grup = (String)it.next();
		Iterator it1 = (Main.root.content).iterator(); 
		while(it1.hasNext()){
			AbstractGeneral obj = (AbstractGeneral)it1.next();
			if(obj instanceof Folder && ((Folder)obj).getOwnerGroup().equals(group))
					obj.setOwnerGroup(grup);
			for(AbstractGeneral o: ((Folder)obj).content)
				if(o.getOwnerGroup().equals(group)){
						o.setOwnerGroup(grup);
				}
		}
			
			return true;
		}
	
	/**
	 * Metoda ce implementeaza adaugarea unui grup la lista de grupuri membre ale unui user si implicit
	 * adaugare userului la membrii grupului.
	 * @param owner user - userul ce incearca adaugarea grupului.
	 * @param group String - grupul la care se incearca adaugarea.
	 * @param u user - userul ce se incearca adaugat.
	 * @return boolean - daca operatia s-a realizat cu succes sau nu.
	 */
	public static boolean useradd(User owner, String group, User u){
		if(!User.groupMembershipOwned(owner, group) && !owner.equals("root"))
			return false;
		User.addMembru(group,u);
        return true;
	}
	
	/**
	 * Stergerea unuia sau a mai multor userii dintr-un anumit grup.
	 * @param executer use - userul care doreste sa stearga altii useri dintr-un grup.
	 * @param group String -grupul de la care se incearca stergerea
	 * @param u user - userul sters
	 * @return boolean - daca metoda s-a realzia cu succes sau nu
	 */
	public static boolean usermod(User executer, String group, User u){
		if(!User.groupMembershipOwned(executer, group) && !executer.equals("root"))
			return false;
		
		if(executer.equals(u))
			return false;
		
		User.delMembru(group,u);
		return true;
	}
	
	/**
	 * Metoda ce implementeaza schimbarea permisiunilor pentru un anumit Folder/Fisier. 
	 * @param name String - Folderul/Fisierul ce se incearca sa-i fie schimbate permisiunile(calea catre el)
	 * @param u user - userul care incearca schimbarea permisiunilor
	 * @param proprietar String - g=group, a =all, u=user
	 * @param operator String- +:adauga, =:seteaza la; -:scoate
	 * @param drepturi char[] - r=read, w=write,x=execute
	 * @return boolean -daca operatia s-a executat cu succes sau nu
	 */
	public static boolean chmod(String name,User u, String proprietar, String operator,char[] drepturi){
		String[] cale=name.split("/");
		AbstractGeneral dir=Main.root;
		int i;
		
		for (i=1; i<cale.length-1; i++){
			if (!(dir instanceof Folder))
				return false;
			dir=((Folder)dir).getA(cale[i]);
		}
		if (dir!=null && (dir instanceof Folder || dir instanceof Fisier)){
			AbstractGeneral aux=((Folder)dir).getA(cale[i]);
			if(aux!=null){
				if(aux instanceof Folder){
					if(((Folder)dir).getPermisiune(((Folder)aux),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Folder)dir).getPermisiune((Folder)aux,u,1)) || (User.groupMembershipNotOwned(u,((Folder)aux).getOwnerGroup())) && ((Folder)dir).getPermisiune((Folder)aux,u, 1)) {
						if(!(aux.setPermisiune(aux, u, proprietar, operator, drepturi))) 
							return false;
						return true;
					}
				}
				else
				if(aux instanceof Fisier){
					if(((Fisier)aux).getPermisiune(((Fisier)aux),u,1) || u.getNume().equals("root") || (u.equals(dir.getOwnerUser()) && ((Fisier)dir).getPermisiune((Fisier)aux,u,1)) || (User.groupMembershipNotOwned(u,((Fisier)aux).getOwnerGroup())) && ((Fisier)dir).getPermisiune((Fisier)aux,u, 1)) {
						if(!(aux.setPermisiune(aux, u, proprietar, operator, drepturi))) 
							return false;
						return true;
					}
				}
				return false;
			}
			return false;
		}
		return false;
	}	
}