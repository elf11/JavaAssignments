import java.lang.reflect.Array;
import java.util.*;


/**
 * @author Nicualescu Oana 321CB
 * Clasa Main apeleazaa toate celelalte metode implementate de clasa Action si pentru fiecare comanda executata
 * gasita in lista de comenzi a switch'ului se executa intrusctiunea corespunzatoare.
 *
 */
public class Main {
	
	/*
	 * Initializarea directorului root sia  sitemului de fisiere.
	 */
	public static final Folder root=new Folder("root",null,null,null);
	
	
	public static void main(String[] args){
		/*
		 * Setarea permisiunilor pentru sirectorul root
		 */
		
		for(int k=0; k<=8; k++)
			root.permisiuni[k]=true;
		
		Scanner sc = new Scanner(System.in);
		String line="";
		String[] line2;
		User u,useradded;
		line = sc.nextLine();
		int x=0;
		
		while(!line.equals("quit")){
			line2=line.split(" ");
			
			u=User.getUser(line2[0]);
				
		/*
		 * Implementarea tuturor comenzilor din clasa Action
		 */
			
			if (line2[1].equals("read")) x=1;
			else if (line2[1].equals("write")) x=2;
			else if (line2[1].equals("create")) x=3;
			else if (line2[1].equals("mkdir")) x=4;
			else if (line2[1].equals("delete")) x=5;
			else if (line2[1].equals("rmdir")) x=6;
			else if (line2[1].equals("execute")) x=7;
			else if (line2[1].equals("ls")) x=8;
			else if (line2[1].equals("groupadd")) x=9;
			else if (line2[1].equals("groupdel")) x=10;
			else if (line2[1].equals("useradd")) x=11;
			else if (line2[1].equals("usermod")) x=12;
			else if (line2[1].equals("chmod")) x=13;
			
			switch (x){
			case 1:
				if (line2.length>=3){
					if(!Action.read(line2[2],u))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 2:
				if(line2.length>=4){
					String info[]=line.split("\"");
					if (info.length>1)
						info[0]=info[1];
					else
						info[0]="";
					if(!Action.write(line2[2], u, info[0]))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 3:
				if (line2.length>=4){
					String info[]=line.split("\"");
					if (info.length>1)
						info[0]=info[1];
					else
						info[0]="";
					if(!Action.create(line2[2],u,line2[3],info[0]))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 4:
				if(line2.length>=3){
					
					if(!Action.mkdir(line2[2], u, null))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 5:
				if(line2.length>=3){
					if(!Action.delete(line2[2],u))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 6:
				if(line2.length>=3){
					if(!Action.rmdir(line2[2], u))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 7:
				if(line2.length>=3){
					if(!Action.execute(line2[2], u))
						System.err.println("Eroare");
					else 
						System.err.println("Fisierul a fost executat cu succes");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 8:
				if(line2.length>=3){
					if(!Action.ls(line2[2], u))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 9:
				if(line2.length>=3){
					if(!Action.groupadd(line2[2], u))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 10:
				if(line2.length>=3){
					if(!Action.groupdel(line2[2], u))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 11:
				if(line2.length>=4){
					useradded=User.getUser(line2[3]);
					if(!Action.useradd(u,line2[2],useradded))
						System.err.println("Eroare");
				}
				else
					System.err.println("Eroare");
			break;
			
			case 12:
				if(line2.length>=4){
					String info[]=line.split(" ");
					if (info.length>1)
						info[0]=info[1];
					else
						info[0]="";
					User executer = User.getUser(line2[0]);
					User u1 =User.getUser(line2[line2.length-1]);
					for(int i=1; i<line2.length; i++)
						if(!Action.usermod(executer, line2[i], u1)){
							System.err.println("Eroare");
				        }
					System.err.println("Eroare");
				}
			break;
			
			case 13:
				if(line2.length>5){
					User executer = User.getUser(line2[0]);
					String proprietar = line2[2];
					String operator = line2[3];
					int lungime = line2[4].length();
					char[] drepturi = new char[lungime];
					for(int k=0;k<lungime; k++)
						drepturi[k]=line2[4].charAt(k);
					if(!Action.chmod(line2[5], executer, proprietar, operator, drepturi)){
							System.err.println("Eroare");
					}
				}	
					else
					   System.err.println("Eroare");
				
			break;
			
			default:
				System.err.println("Eroare");
			}
				
			line=sc.nextLine();
		}
		
	}

}
