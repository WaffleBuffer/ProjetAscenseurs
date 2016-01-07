package IHM;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Boutons.BoutonInterne;
import Client.Ascenseur;
import Client.Batiment;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public MainWindow (Batiment bat, Ascenseur asc) {
		
		//layout de la fenêtre
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);
		
		//répartition des panels
		JPanel batPanel = new JPanel();
		JPanel ascPanel = new JPanel();
		this.add(batPanel, BorderLayout.WEST);
		this.add(ascPanel, BorderLayout.EAST);
		
		//layout partie batiment
		LayoutManager batLayout = new BoxLayout(batPanel, BoxLayout.Y_AXIS);
		batPanel.setLayout(batLayout);
		batPanel.add(new JLabel("Bâtiment"));
		
		//layout partie ascenseur
		LayoutManager ascLayout = new BoxLayout(ascPanel, BoxLayout.Y_AXIS);
		ascPanel.setLayout(ascLayout);
		ascPanel.add(new JLabel("Ascenseur"));
		
		//partie batiment
		JLabel nomBat = new JLabel(bat.getNom());
		JLabel nbEtagesBat = new JLabel(Integer.toString(bat.getNbEtages()));
		batPanel.add(nomBat);
		batPanel.add(nbEtagesBat);
		
		//partie ascenseur
		for (BoutonInterne i : asc.getListeBoutons()){
			ascPanel.add(new JLabel(i.getLibelle()));
		}
		
		//reglages de la fenêtre
		this.setTitle("Projet Java Ascenseur");						//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);	//le programme s'arrete quand la fenetre se ferme
		this.setSize(400, 400);							//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);				//la fenêtre apparait au centre de l'écran
		this.setVisible(true);							//la fenêtre apparaît
		
	}
	

}
