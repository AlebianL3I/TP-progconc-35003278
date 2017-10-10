public class ThreadProducteur2 extends Thread {

	private final Nombre2 n;
	private final int tempsSommeil;

	public ThreadProducteur2(Nombre2 n, int tempsSommeil, String nom) {
		super(nom);
		this.n = n;
		this.tempsSommeil = tempsSommeil;
		this.setDaemon(true);
	}

	public void run() {
		while (true) {
			try {
				this.n.ajouter();//test de passage
				System.out.println("A");//test de passage
				sleep(this.tempsSommeil);
			}
			catch (InterruptedException e) {}
		}
	}
}
