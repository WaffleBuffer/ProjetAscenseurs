package IHM;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Client.Ascenseur;
import Client.Batiment;

public class FenetreBatiment extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public FenetreBatiment (Batiment bat, Ascenseur asc, FenetrePanneau panneau) {
		
		//layout de la fenêtre
		this.setLayout(new GridLayout(1, 2));									//il est associé à la fenetre principale
		JPanel panelBatiment = new JPanel();
		GridLayout layoutBatiment = new GridLayout(bat.getNbEtages() + 1, 2);
		panelBatiment.setLayout(layoutBatiment);
		this.add(panelBatiment);
		
		for (int i = 0; i <= bat.getNbEtages(); ++i){
			JLabel labelEtage = new JLabel("yolo", JLabel.CENTER);
			labelEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			panelBatiment.add(labelEtage);
			
			JPanel panelBoutons = new JPanel();
			JButton boutonHaut = new JButton("Haut");
			JButton boutonBas = new JButton("Bas");
			panelBoutons.setLayout(new GridLayout(2, 1)); 
			panelBatiment.add(panelBoutons);
			if (i == 0){
				panelBoutons.add(new JLabel());
				panelBoutons.add(boutonBas);
			}
			else if (i == bat.getNbEtages()){
				panelBoutons.add(boutonHaut);
			}
			else {
				panelBoutons.add(boutonHaut);
				panelBoutons.add(boutonBas);
			}
		}
		
		JPanel panelInfos = new JPanel();
		this.add(panelInfos);
		
		JLabel labelNumAsc = new JLabel(String.valueOf(panneau.getAscenseurActuel().getNumAsc()), JLabel.CENTER);
		JLabel labelEtage = new JLabel(String.valueOf(panneau.getAscenseurActuel().getEtage()), JLabel.CENTER);
		panelInfos.add(labelNumAsc);
		panelInfos.add(labelEtage);
		
		//reglages de la fenêtre
		this.setTitle(bat.getNom() + " (" + bat.getNbEtages() + " étages)");	//Titre de la fenêtre 
		this.setSize(400, 500);									//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
		
	}
}
