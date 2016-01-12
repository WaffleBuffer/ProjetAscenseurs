package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import Client.Ascenseur;
import Client.Batiment;

public class FenetrePanneau extends JFrame{
	
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private Ascenseur ascenseurActuel;
	private JButton ascenseurSelectionne;
	
	private static final long serialVersionUID = 1L;

	public FenetrePanneau (final Batiment bat, int nbAsc){
		GridLayout layoutPrincipal = new GridLayout(2, 1);		//création layout principal de la fenetre
		layoutPrincipal.setVgap(10);							//ajout margin vertical entre éléments du layout
		this.setLayout(layoutPrincipal);						//associationd du layout à la fenetre
		
		JPanel panelAscenseurs = new JPanel();					//création panel pour afficher la liste des ascenseurs
		panelAscenseurs.setLayout(new GridLayout(nbAsc, 1));	//ajout d'un layout à ce panel
		ascenseurActuel = bat.getAscenseur(1);					//l'ascenseur affiché lors de la creation de la fenetre est celui d'id 1
		
		JScrollPane scrollAsc = new JScrollPane(panelAscenseurs);
		this.add(scrollAsc);
		
		//creation de la liste des ascenseur sous forme de boutons alignés verticalement
		for(int i = 1; i <= nbAsc; ++i){		
			final int j = i;
			final JButton ascenseur = new JButton("Ascenseur n°" + i);
			panelAscenseurs.add(ascenseur);
			if (i == 1){
				ascenseurSelectionne = ascenseur;
				ascenseurSelectionne.setBackground(Color.WHITE);
			}
			ascenseur.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
						ascenseurSelectionne.setBackground(null);
						ascenseurSelectionne = ascenseur;
						ascenseurSelectionne.setBackground(Color.WHITE);		//lorsqu'on clique sur un ascenseur, il se colore en blanc 
						ascenseurActuel = bat.getAscenseur(j);		//et l'ascenseur selectionne devient celui cliqué
				}});
		}
		
		//===============BOUTONS===============
		
		JPanel panelPanneau = new JPanel();			//creation panel pour les boutons dans l'ascenseur
		panelPanneau.setLayout(new GridLayout(bat.getNbEtages(), 1));	//creation d'un layout pour ce panel
		
		JScrollPane scrollBoutons = new JScrollPane(panelPanneau);
		this.add(scrollBoutons);
		
		//création en boucle des boutons
		for (int i = 0; i < bat.getNbEtages(); ++i){
			final int j = i;
			if (i == bat.getNbEtages() - 1)
			{
				JButton boutonStop = new JButton("STOP");
				panelPanneau.add(boutonStop);
			}
			else
			{
				JButton boutonDestination = new JButton();
				if (i == 0)
					boutonDestination = new JButton("Rez-de-chaussée");
				else if (i == 1)
					boutonDestination = new JButton("1er");
				else
					boutonDestination = new JButton(Integer.toString(i) + "e");
				panelPanneau.add(boutonDestination);
				boutonDestination.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						bat.appuyerBoutonAscenseur(ascenseurActuel.getNumAsc(), j);
					}});
			}
		}
		
		this.setTitle("Ascenseurs du bâtiment \"" + bat.getNom() + "\"");	//Titre de la fenêtre 
		this.setMinimumSize(new Dimension(400, 500));						//taille de la fenêtre fixe
		this.setVisible(true);												//la fenêtre apparaît
	}
}
