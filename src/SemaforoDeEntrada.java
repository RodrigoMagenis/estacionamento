import java.util.Random;
class SemaforoDeEntrada extends Thread 
{ 
	int name;
    public SemaforoDeEntrada(int name) {
    	this.name = name;
	}

	public void run() 
    { 
    	Random rand = new Random();
    	while(true) {
    		try {
    			Thread.sleep (rand.nextInt(250));
    			Estacionamento.sem.acquire();
    			if (Estacionamento.qtFreeSpaces < Estacionamento.totalParkingSpaces) {
    				Estacionamento.qtFreeSpaces += 1;
    				System.out.println(name + " Entrou "+ (Estacionamento.qtFreeSpaces -1) + " -> " + Estacionamento.qtFreeSpaces);
    			}
    			Estacionamento.sem.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
       }
    } 
}




