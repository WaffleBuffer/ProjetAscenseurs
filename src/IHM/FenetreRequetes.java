package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Client.Batiment;
import Controleurs.ControleurInterne;
import Requetes.Requete;

public class FenetreRequetes extends JFrame{

	private JTextArea requetes = new JTextArea("Liste des requetes:");
	
	private Batiment batiment;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4236819230726511089L;

	public FenetreRequetes (Batiment batiment) {
		this.batiment = batiment;
		requetes.setText("");
		requetes.setEditable(false);
		this.setLayout(new GridLayout(1, 2));	//il est associe a la fenetre principale
		JPanel panelRequete = new JPanel();
		panelRequete.add(requetes);
		panelRequete.setBackground(Color.WHITE);
		JPanel panel = new JPanel(new GridLayout(0, 1));
		JScrollPane scrollHistorique = new JScrollPane(panelRequete);
		panel.setBorder(BorderFactory.createTitledBorder(null, "Liste de requetes", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panel.add(scrollHistorique);
		this.add(panel);
		
		this.setTitle(batiment.getNom());			        	//Titre de la fenetre 
		//this.setResizable(false);								//la fenetre n'a pas besoin d'etre redimensionner
		this.setSize(new Dimension(300, 300));					//taille de la fenetre fixe
		this.setLocationRelativeTo(null);						//la fenetre apparait au centre de l'ecran
		this.setVisible(true);									//la fenetre apparaît
	}
	
	public void actualiserText () {
		String tiretsAffichage = "";
		String egalesAffichage = "";
		for (int i = 0; i < this.getSize().width / 11; ++i) {
			tiretsAffichage += "-";
			if (0 == i % 3) {
				egalesAffichage += "=";
			}
		}
		requetes.setText("\n" + tiretsAffichage + "Requetes Externes" + tiretsAffichage + "\n");
		for (Requete i : batiment.getControleurExt().getRequetes()) {
			requetes.setText(requetes.getText() + i.toString() + "\n");
		}
		
		requetes.setText("\n" + tiretsAffichage + "Requetes Internes" + tiretsAffichage + "\n");
		for (ControleurInterne i : batiment.getControleursInterne()) {
			requetes.setText(requetes.getText() + "\n" + egalesAffichage + 
					"[Ascenseur " + i.getAscenceur().getNumAsc() + "]" + egalesAffichage +"\n");
			for (Requete j : i.getRequetes()) {		
				requetes.setText(requetes.getText() + j.toString());
			}
			requetes.setText(requetes.getText() + "\n" + egalesAffichage  + egalesAffichage + "=========\n");
		}
		
	}
}
