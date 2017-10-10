import java.util.ArrayList;

import javax.swing.JLabel;

public class Nombre2 {
	private int element = 0;
	public boolean affichage = true;
	public static String S;
	public ArrayList<Integer> tab = new ArrayList<Integer>();
	public int ajout;
	public int retrait;
	
	public synchronized void ajouter() throws InterruptedException {
		affichage=Math.random() < 0.5;
		while (element>=20) wait();
		
		ajout = 1+ (int) ( Math.random()*100);
		afficher();
		Main3_2.Prod0.setText("Prod 0 :ajout de l'entier "+ajout);
		tab.add(ajout);
		element=tab.size();
		System.out.println(element);
		notifyAll();
	}
	public synchronized void retirer() throws InterruptedException {
		
		while (element<=0) wait();
		retrait= (int) ( Math.random()*tab.size());
		afficher();
		Main3_2.Cons0.setText("Cons 0 :retrait de effectué "+tab.get(retrait));
		tab.remove(retrait);
		element=tab.size();
		this.affichage=false;
		notifyAll();
	}
	
	public synchronized void afficher() throws InterruptedException {
		S ="File=" + tab.toString();
		Main3_2.Texte.setText(S);
		System.out.println(tab);
		notifyAll();
	}
}
