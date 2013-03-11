/**
 * 
 */

/**
 * @author Niculaescu Oana
 *
 *Clasa LazyStudent extinde clasa Student si suprascrie doar metoda hashCode() - intr-un mod defectuos - fiecare intrare din mapul
 *creat anterior se va gasi la acelasi index, practic avand un map cu un singur bucket ce contine null + alte eventuale intrari.
 */
public class LazyStudent extends Student{
	
	/**
	 * 
	 * @param nume - String numele studentului generat aleator
	 * @param varsta - Integer varsta studentului generata aleator
	 * Constructor ce instantiaza un obiect de tipul LazyStudent prin apelul constructorului din clasa pe care o extinde Student.
	 */
	LazyStudent(String nume, int varsta){
		super(nume, varsta);
	}
	
	/**
	 * Metoda ce suprascrie metoda hashCode(), returnand intotdeauna valoarea 0, deci practic vom avea un singur bucket in care 
	 * introducem toate entry'urile.
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	
	
	/*
	 * Metoda ce suprascrie metoda equals(Object o) din clasa Object si care intoarce intotdeauna true. 
	 */
	/*
	@Override
	public boolean equals(Object o){
		return true;
	}

	*/
}
