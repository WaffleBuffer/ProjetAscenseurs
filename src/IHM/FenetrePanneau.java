package IHM;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class FenetrePanneau extends JFrame{
	
	public FenetrePanneau (int nbAsc){
		GridLayout layoutPrincipal = new GridLayout(2, 1);
		this.setLayout(layoutPrincipal);
		
		
		
		this.setTitle("Gestion des ascenseurs");					//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);			//le programme s'arrete quand la fenetre se ferme
		this.setResizable(false);								//la fenetre de configuration n'a pas besoin d'être redimensionner
		this.setSize(new Dimension(300, 300));					//taille de la fenêtre fixe
		//this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
	}
}
