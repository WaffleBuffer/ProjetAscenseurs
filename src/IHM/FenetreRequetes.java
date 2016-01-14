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
		
		this.setTitle(batiment.getNom() + " (queries)");			        	//Titre de la fenetre 
		this.setSize(new Dimension(400, 500));					//taille de la fenetre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setLocation(width - this.getWidth(), height/2 - this.getHeight()/2);
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
		if (0 != batiment.getControleurExt().getRequetes().size()) {
			requetes.setText("\n" + tiretsAffichage + "External queries" + tiretsAffichage + "\n");
			for (Requete i : batiment.getControleurExt().getRequetes()) {
				requetes.setText(requetes.getText() + i.toString() + "\n");
			}
		}
		
		requetes.setText(requetes.getText() + "\n" + tiretsAffichage + "Internal queries" + tiretsAffichage + "\n");
		for (ControleurInterne i : batiment.getControleursInterne()) {
				if (0 != i.getNumberOfRequete()) {
				requetes.setText(requetes.getText() + "\n" + egalesAffichage + 
						"[Lift " + i.getAscenceur().getNumAsc() + "]" + egalesAffichage +"\n");
				for (Requete j : i.getRequetes()) {		
					requetes.setText(requetes.getText() + j.toString());
				}

				requetes.setText(requetes.getText() + "\n" + egalesAffichage  + egalesAffichage + "=======\n");
				}
		}
		
	}
}
