import java.util.Random;
class SemaforoDeSaida extends Thread 
{ 
	int name;
    public SemaforoDeSaida(int name) {
    	this.name = name;
	}

	public void run() 
    { 
    	Random rand = new Random();
    	while(true) {
    		try {
    			Thread.sleep (rand.nextInt(250));
    			Estacionamento.sem.acquire();
    			if (Estacionamento.qtFreeSpaces > 0) {
    				Estacionamento.qtFreeSpaces -= 1;
    				System.out.println(name+" Saiu "+ (Estacionamento.qtFreeSpaces + 1) + " -> " + Estacionamento.qtFreeSpaces);
    			}
    			Estacionamento.sem.release();
			} catch (InterruptedException e) {
				Estacionamento.sem.release();
			}
       }
    } 
}