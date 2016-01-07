package IHM;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Batiment;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public MainWindow () {
		
		//layout de la fenêtre
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);
		
		//Panel qui contient batiment
		JPanel mainPanel = new JPanel();
		this.add(mainPanel, BorderLayout.CENTER);
		
		//layout partie batiment
		LayoutManager batLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
		mainPanel.setLayout(batLayout);
		mainPanel.add(new JLabel("Bâtiment"));
		
		//batiment
		Batiment bat = new Batiment("Immeuble", 6);
		JLabel nomBat = new JLabel(bat.getNom());
		JLabel nbEtagesBat = new JLabel();
		nbEtagesBat.setText(Integer.toString(bat.getNbEtages()));
		mainPanel.add(nomBat);
		mainPanel.add(nbEtagesBat);
		
		//reglages de la fenêtre
		this.setTitle("Bâtiment");						//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//le programme s'arrete quand la fenetre se ferme
		this.setSize(400, 400);							//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);				//la fenêtre apparait au centre de l'écran
		this.setVisible(true);							//la fenêtre apparaît
		
	}
	

}
