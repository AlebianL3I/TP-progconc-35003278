

public class ThreadConsommateur2 extends Thread  implements Runnable{

	private final Nombre2 n;
	private final int tempsSommeil;

	public ThreadConsommateur2(Nombre2 n, int tempsSommeil, String nom) {
		super(nom);
		this.n = n;
		this.tempsSommeil = tempsSommeil;
		this.setDaemon(true);
	}

	
	public void run() {
		while (true) {
			if(n.affichage) {
				try {
					this.n.retirer();
					System.out.println("R");
					sleep(this.tempsSommeil);
					}
				catch (InterruptedException e) {}
			}
		}
	}
}
