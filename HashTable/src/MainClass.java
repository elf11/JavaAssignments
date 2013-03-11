import java.util.*;
import java.io.*;

/**
 * @author Niculaescu Oana
 * 
 * Clasa principala in care se implementeaza metode de acces pentru obiectele de tip Student si LazyStudent si se compara timpul 
 * de acces dintre cele doua accesari.
 *
 */
public class MainClass {
	
	public static void main(String[] args){
		
		ArrayList<Student> list = new ArrayList<Student>();
		MyHashMap<Student, Integer> map = new MyHashMapImpl<Student, Integer>(64);
		ArrayList<LazyStudent> listLazy = new ArrayList<LazyStudent>();
		MyHashMap<LazyStudent, Integer> mapLazy = new MyHashMapImpl<LazyStudent, Integer>(64);
		
		for(int i=0; i<10000; i++){
			
		    //Gasim un numar random care sa fie lungimea numelui, numele nu poate fi mai scurt de 10 caractere si mai lung de 35 
			//de caractere.
			
			int minN = 10;
			int maxN = 35;
			

	        Random rr = new Random();
	        double rr1 = rr.nextDouble();
			int r=minN + (int)(rr1 * ((maxN - minN) + 1));
			
			String nume = new String();
			
			//Construim un nume random cu numar de litere egal cu cel random generat r.
			
	        for (int j = 0; j < r; j++) {
	        	Random r1 = new Random();
	        	double randomNumber = r1.nextDouble();
	            double randomNumberSetup = (randomNumber * 26 + 'a');
	            char randomCharacter = (char) randomNumberSetup;
	            nume = nume+Character.toString(randomCharacter);
	        }
	        
	        //Varsta minima a studentilor este 17 ani, varsta maxima a acestora este 33 de ani, generam un numar random in acest interval.
	        int vMin=17;
	        int vMax=33;
	        
	        rr1 = rr.nextDouble();
	        
	        int varsta = vMin + (int)(rr1 * ((vMax - vMin) + 1));
	        
	        //Adaugam intrarea in lista de studenti.
	        listLazy.add(new LazyStudent(nume,varsta));
	        list.add(new Student(nume,varsta));
		}
		
		//Nota fiecarui student este un numar random intre 0-10
		int nMin=0;
		int nMax=10;
		for(Student std : list){
			Random rr = new Random();
		    double rr1 = rr.nextDouble();
			int nota = nMin + (int)(rr1* ((nMax - nMin) + 1));
			map.put(std,nota);
		}
		
		for(LazyStudent std1 : listLazy){
			Random rr = new Random();
		    double rr1 = rr.nextDouble();
			int nota1 = nMin + (int)(rr1 * ((nMax - nMin) + 1));
			mapLazy.put(std1,nota1);
		}

		
		//Accesare Student
		long time1=System.currentTimeMillis();
		for (int i=0;i<10000; i++)
			map.get(list.get(i));
		long time2=System.currentTimeMillis();
		System.out.println("Accesare Student: "+(time2-time1));
		
		
		//Accesare LazyStudent
		long timeLazy1=System.currentTimeMillis();
		for (int i=0;i<10000; i++)
			mapLazy.get(listLazy.get(i));
		long timeLazy2=System.currentTimeMillis();
		System.out.println("Accesare LazyStudent: "+(timeLazy2-timeLazy1));
	}

}
