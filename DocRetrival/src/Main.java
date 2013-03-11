

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

/**
 * 
 * @author Oana Niculaescu
 * 
 * Clasa Main ce realizeaza cele doua functii principale ale programului 
 * de codificare si decodificare a textului, returnand textul codificat binar 
 * in cazul codificarii, si textul in format human-readable
 * pentru decodificare
 *
 */

public class Main {
	
	/**
	 * 
	 * Metoda main unde se realizeaza codificarea si decodificarea
	 * propriu-zisa a sirului de caractere
	 * 
	 * @param args  args[0] ~ c(codificare) / d(decodificare)
	 * 
	 */
	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		
		/**
		 * 
		 * Daca argumentul primit este c atunci incepe procesul de codificare
		 * 
		 */
	
		if(args[0].equals("c")){
			String temp=s.nextLine();
			String linie="";
			
			while(!linie.equals(".")){
				temp=temp.concat(linie);
				linie=s.nextLine();
				if (!linie.equals(".")){
					temp=temp.concat("\r\n");
				    
				}
			}
			
			ArrayList<Character> inputPhrase = new ArrayList<Character>();
            ListaSortata listas = new ListaSortata();
            
            for(int i=0; i<temp.length(); i++){
                inputPhrase.add(i,temp.charAt(i));
            }
            
            
            do{
                char temporar=inputPhrase.get(0);
                int frequencies=0;
                for(int i=0; i<inputPhrase.size(); i++){
                    if(inputPhrase.get(i).compareTo(temporar)==0){
                        frequencies++;
                        inputPhrase.remove(i);
                        if(inputPhrase.size()==0)
                            break;
                        i--;
                    }
                }
                
                listas.Add(new Node(temporar,frequencies));
            }while(inputPhrase.isEmpty()==false);
            
            
            HuffmanT arbore = new HuffmanT();
            arbore.Arbore(listas);
            String cod;
            Memorare[] vec = new Memorare[arbore.getListaSortat().size()];
                    
            for (int i=0;i<arbore.getListaSortat().size();i++){
            	cod="";
            	cod=arbore.getHuff(arbore.tata,arbore.getListaSortat().get(i).getCaracter(),cod);
                System.out.println((int)arbore.getListaSortat().get(i).getCaracter()+" "+cod);
                vec[i]=new Memorare(arbore.getListaSortat().get(i).getCaracter(),cod);
                
            }
            System.out.println(".");
            
            String output="";
            
            for(int i=0; i<temp.length(); i++){
            	for(int j=0; j<vec.length; j++){
            		if(temp.charAt(i)==vec[j].caracter){
            			output=output.concat(vec[j].cod);
            			break;
            		}
            	}
            }
            System.out.println(output);
		}
		
		/**
		 * 
		 * Daca argumentul primit este d atunci incepe procesul de decodificare
		 * 
		 */
		if(args[0].equals("d")){ 
			
			String linie;
			
			Memorare[] vec = new Memorare[256];
			
			linie=s.next();
			int j=0;
			while(!linie.equals(".")){
				vec[j]=new Memorare((char)Integer.parseInt(linie),s.next());
				linie=s.next();
				j=j+1;
			}
			
			int n=j;
			linie=s.next();
			String temp="";
			String output="";
			j=0;
			
			for(int i=1; i<=linie.length(); i++){
				temp=linie.substring(j,i);
				for(int l=0; l<n; l++){
					if(vec[l].cod.equals(temp)) { 
						output=output+vec[l].caracter;
						j=i;
						break;
					}
				}
			}
			
			System.out.println(output);
			System.out.print(".");
		}
	}
}