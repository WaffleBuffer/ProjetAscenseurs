package IHM;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	/**
	 * @param args
	 */
	public MainWindow () {
		this.setTitle("Bâtiment");				//Titre de la fenêtre 
		this.setSize(300, 300);					//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);		//la fenêtre apparait au centre de l'écran
		this.setVisible(true);					//la fenêtre apparaît
		
		//layout de la fenêtre
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);
		
	}
	

}
