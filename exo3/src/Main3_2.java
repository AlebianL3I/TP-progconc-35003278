import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main3_2 extends JFrame implements  ActionListener{
	public static JLabel Texte=new JLabel("File=");
	public static JLabel Prod0=new JLabel("Prod 0 :ajout de l'entier ");
	public static JLabel Cons0=new JLabel("Cons 0 :retrait de effectué ");
	JButton Start=new JButton("Start");
	public Main3_2 (String titre, int x, int y, int w, int h) {
		super("Une fenetre");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Container pane=getContentPane();
		pane.setLayout(new BoxLayout(pane, BoxLayout.PAGE_AXIS));
		pane.add(Texte);
		pane.add(Start,pane.CENTER_ALIGNMENT);
		pane.add(Prod0);
		pane.add(Cons0);
		Start.setActionCommand("Start");
		Start.addActionListener(this);
        
		this.setBounds(x,y,w,h);
		this.setVisible(true);
		}
	public static void main(String args[]) throws InterruptedException {
		Main3_2 Fenetre = new Main3_2 ( "Ma première fenêtre ",300,200,500,400);
	}

	

	public void actionPerformed(ActionEvent a){
		
		Nombre2 n = new Nombre2();		
		// Creation des threads.
		ThreadProducteur2 p = new ThreadProducteur2(n, 2000, "p");
		ThreadConsommateur2 c  = new ThreadConsommateur2(n, 1000, "c");
		if(a.getActionCommand()!="Stop") {
			this.Start.setText("Stop");
			this.Start.setActionCommand("Stop");
			p.start();
			c.start();

		}
		if(a.getActionCommand()=="Stop") {
			System.exit(0);
		}
	}

}	