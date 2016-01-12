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
	public FenetreBatiment (Batiment bat, Ascenseur asc) {
		
		//layout de la fenêtre
		GridLayout layoutPrincipal = new GridLayout(bat.getNbEtages() + 1, 2);	//création du layout principal
		this.setLayout(layoutPrincipal);									//il est associé à la fenetre principale
		
		for (int i = 0; i <= bat.getNbEtages(); ++i){
			JLabel labelEtage = new JLabel("yolo", JLabel.CENTER);
			labelEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			this.add(labelEtage);
			
			JPanel panelBoutons = new JPanel();
			JButton boutonHaut = new JButton("Haut");
			JButton boutonBas = new JButton("Bas");
			panelBoutons.setLayout(new GridLayout(2, 1)); 
			this.add(panelBoutons);
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
		/*
		
		//partie batiment
		/*JLabel nomBat = new JLabel(bat.getNom());								//création label contenant le nom du batiment
		JLabel nbEtagesBat = new JLabel(Integer.toString(bat.getNbEtages()));	//création label contenant le nombre d'étages du batiment
		batPanel.add(nomBat);													
		batPanel.add(nbEtagesBat);												//ajout des labels dans le panel batiment
		batPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));		//ajout d'un padding à ce panel identique au précédent
		
		//partie ascenseur
		for (BoutonInterne i : asc.getListeBoutons()){							//ajout de chaque bouton de l'ascenseur au panel via des label
			ascPanel.add(new JLabel(i.getLibelle()));
		}
		
		final JProgressBar barre_progression = new JProgressBar(JProgressBar.VERTICAL, 0, bat.getNbEtages());//création barre de progression verticale pour représenter l'emplacement de l'ascenseur
		barre_progression.setValue(asc.getEtage());			//elle prend pour valeur l'emplacement de l'ascenseur
		barre_progression.setForeground(Color.BLUE);		//changement de la couleur de la barre pour une meilleure visibilité
		this.add(barre_progression, BorderLayout.CENTER);	//on l'ajoute au centre de la fenetre
		
		//boutons		
		JPanel boutonPanel = new JPanel();					//création panel qui contiendra les boutons cliquables
		BoxLayout boutonLayout = new BoxLayout(boutonPanel, BoxLayout.Y_AXIS);	//création  boxlayout vertical
		this.add(boutonPanel, BorderLayout.SOUTH);								//ajout du panel en bas de la fenetre
		
		//création en boucle des boutons qui ont pour effet de changer la valeur de la barre au clic
		for (int i = 0; i < asc.getListeBoutons().size(); ++i){
			if (i == asc.getListeBoutons().size() - 1)
			{
				JButton boutonStop = new JButton("STOP");
				boutonPanel.add(boutonStop, BorderLayout.SOUTH);
			}
			else
			{
				final int j = i;
				JButton boutonDestination = new JButton(Integer.toString(i));
				boutonPanel.add(boutonDestination, BorderLayout.SOUTH);
				boutonDestination.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
							barre_progression.setValue(j);
					}});
			}
		}
		*/
		//reglages de la fenêtre
		this.setTitle(bat.getNom() + " (" + bat.getNbEtages() + " étages)");	//Titre de la fenêtre 
		this.setSize(400, 500);									//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
		
	}
}
