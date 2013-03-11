/**
 * 
 */

/**
 * @author Niculaescu Oana
 * Clasa Student suprascrie metodele hashCode() si equals(Object o) pentru a putea adauga obiecte de tip Student in mapul creat 
 * anterior dupa propriile noastre criterii.
 *
 */
public class Student {
	
	String nume;
	Integer varsta;
	
	/**
	 * 
	 * @param nume - String numele studentului generat aleator
	 * @param varsta - Integer varsta studentului generata aleator
	 * Constructor ce creeaza un obiect de tipul studentului cu atributele nume si varsta primite ca parametru. 
	 */
	public Student(String nume, int varsta){
		this.nume = nume;
		this.varsta = varsta;
	}
	
	/**
	 * Suprascriere a functiei hash code asa cum a fost sugerata de cerinta de implementarea.
	 */
	@Override
	public int hashCode(){
		
		int h=22;
		h = 37 * h + nume.hashCode();
		
		return h;
	}
	
	/**
	 * 
	 * Metoda ce suprascrie metoda equals(Object o) din clasa Object, daca atat numele studentului cat si varsta acestuia corespund atunci 
	 * este vorba de acelasi student deci intoarce true, altfel intoarce false.
	 */
	@Override
	public boolean equals(Object o){
		Student s = (Student)o;
		
		if(s.nume.equals(nume) && s.varsta.equals(varsta)) return true;
		else
			return false;
	}

}
