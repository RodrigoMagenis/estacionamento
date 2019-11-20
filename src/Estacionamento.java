import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Estacionamento 
{
	public static int totalParkingSpaces = -1;
	public static int qtFreeSpaces = -1;
	public static int qtEntry = -1;
	public static int qtExit = -1;
	public static Semaphore sem;
	
    public static void main(String[] args) 
    {
    	Scanner scanner = new Scanner(System.in);
    	sem = new Semaphore(1);
    	
    	while (totalParkingSpaces <= 0) {
    		try {
    			System.out.println("Informe a quantidade total de vagas no estacionamento");
        		System.out.print("Quantidade de vagas: ");
    			totalParkingSpaces = Integer.parseInt(scanner.nextLine());
    			
    			if(totalParkingSpaces <= 0) {
    				throw new Exception("invalidTotalAmount");
    			}
    		} catch(NumberFormatException e) {
    			System.out.println("Quantidade de vagas inv�lida: A quantidade total de vagas deve ser um n�mero inteiro");
    			System.out.print("Pressione enter para continuar");
    			scanner.nextLine();
    			clearConsole();
    		} catch (Exception e) {
				if(e.getMessage() == "invalidTotalAmount") {
					System.out.println("Quantidade de vagas inv�lida: A quantidade total de vagas deve ser um n�mero positivo maior que zero");
					System.out.print("Pressione enter para continuar");
					scanner.nextLine();
					clearConsole();
				} else {
					e.printStackTrace();
				}
			}
    	}
    	
    	clearConsole();
    	System.out.println("O total de vagas foi definido como " + totalParkingSpaces);
    	while (qtFreeSpaces < 0 || qtFreeSpaces > totalParkingSpaces) {
    		try {
            	System.out.println("Informe a quantidade de vagas dispon�veis no momento");
        		System.out.print("Quantidade de vagas dispon�veis: ");
    			qtFreeSpaces = Integer.parseInt(scanner.nextLine());
    			
    			if(qtFreeSpaces < 0 || qtFreeSpaces > totalParkingSpaces) {
    				throw new Exception("invalidFreeAmount");
    			}
    		} catch(NumberFormatException e) {
    			System.out.println("Quantidade de vagas dispon�veis inv�lida: A quantidade de vagas deve ser um n�mero inteiro");
    			System.out.print("Pressione enter para continuar");
    			scanner.nextLine();
    			clearConsole();
    		} catch (Exception e) {
				if(e.getMessage() == "invalidFreeAmount") {
					System.out.println("Quantidade de vagas dispo�veis inv�lida: A quantidade de vagas deve ser um n�mero positivo entre 0 e " + totalParkingSpaces);
					System.out.print("Pressione enter para continuar");
					scanner.nextLine();
					clearConsole();
				} else {
					e.printStackTrace();
				}
			}
    	}
    	
    	clearConsole();
    	System.out.println("O total de vagas foi definido como " + totalParkingSpaces);
    	System.out.println("A quantidade total de vagas dispon�veis foi definido como " + qtFreeSpaces);
    	while (qtEntry <= 0) {
    		try {
    			System.out.println("Informe a quantidade de cancelas de entrada no estacionamento");
        		System.out.print("Quantidade de entradas: ");
        		qtEntry = Integer.parseInt(scanner.nextLine());
    			
    			if(qtEntry <= 0) {
    				throw new Exception("invalidTotalAmount");
    			}
    		} catch(NumberFormatException e) {
    			System.out.println("Quantidade de cancelas inv�lida: A quantidade total de cancelas de entrada deve ser um n�mero inteiro");
    			System.out.print("Pressione enter para continuar");
    			scanner.nextLine();
    			clearConsole();
    		} catch (Exception e) {
				if(e.getMessage() == "invalidTotalAmount") {
					System.out.println("Quantidade de cancelas de entrada: A quantidade total de cancelas deve ser um n�mero positivo maior que zero");
					System.out.print("Pressione enter para continuar");
					scanner.nextLine();
					clearConsole();
				} else {
					e.printStackTrace();
				}
			}
    	}
    	
    	clearConsole();
    	System.out.println("O total de vagas foi definido como " + totalParkingSpaces);
    	System.out.println("A quantidade total de vagas dispon�veis foi definido como " + qtFreeSpaces);
    	System.out.println("A quantidade total de cancelas de entrada foi definido como " + qtEntry);
    	while (qtExit <= 0) {
    		try {
    			System.out.println("Informe a quantidade de cancelas de sa�das no estacionamento");
        		System.out.print("Quantidade de sa�das: ");
        		qtExit = Integer.parseInt(scanner.nextLine());
    			
    			if(qtExit <= 0) {
    				throw new Exception("invalidTotalAmount");
    			}
    		} catch(NumberFormatException e) {
    			System.out.println("Quantidade de cancelas inv�lida: A quantidade total de cancelas de sa�da deve ser um n�mero inteiro");
    			System.out.print("Pressione enter para continuar");
    			scanner.nextLine();
    			clearConsole();
    		} catch (Exception e) {
				if(e.getMessage() == "invalidTotalAmount") {
					System.out.println("Quantidade de cancelas inv�lida: A quantidade total de cancelas de sa�da deve ser um n�mero positivo maior que zero");
					System.out.print("Pressione enter para continuar");
					scanner.nextLine();
					clearConsole();
				} else {
					e.printStackTrace();
				}
			}
    	}
    	
    	scanner.close();
    	
    	
        for (int i=0; i < qtEntry; i++) 
        { 
            SemaforoDeEntrada semaforoEntrada = new SemaforoDeEntrada(i); 
            semaforoEntrada.start();
        }
        
        for (int i=0; i < qtExit; i++) 
        { 
            SemaforoDeSaida semaforoSaida = new SemaforoDeSaida(i); 
            semaforoSaida.start();
        }
    }
    
    public static void clearConsole () {
    	// Java porcaria n�o tem comando para limpar o console nativamente....
    	for (int i=0; i<50; i++)
    	{
    	    System.out.println();
    	}
    }
}