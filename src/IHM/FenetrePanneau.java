package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import Client.Ascenseur;
import Client.Batiment;

public class FenetrePanneau extends Observable {

	private JToggleButton boutonAscenseurSelectionne;
	
	private Ascenseur ascenseurSelectionne;

	public FenetrePanneau (final Batiment batiment){
		ascenseurSelectionne = batiment.getAscenseur(0);
		JFrame fenetrePrincipale = new JFrame();
		GridLayout layoutPrincipal = new GridLayout(2, 1);		//creation layout principal de la fenetre
		layoutPrincipal.setVgap(20);							//ajout margin vertical entre éléments du layout
		fenetrePrincipale.setLayout(layoutPrincipal);						//associationd du layout à la fenetre
		JPanel panelPrincipalAscenseurs = new JPanel();
		panelPrincipalAscenseurs.setLayout(new GridLayout());
		fenetrePrincipale.add(panelPrincipalAscenseurs);
		panelPrincipalAscenseurs.setBorder(BorderFactory.createTitledBorder("Lifts"));
		
		JPanel panelAscenseurs = new JPanel();
		panelAscenseurs.setLayout(new GridLayout(batiment.getNbAscenseurs(), 1));	
		
		JScrollPane scrollAscenseurs = new JScrollPane(panelAscenseurs);
		panelPrincipalAscenseurs.add(scrollAscenseurs);
		
		//==========ASCENSEURS==========
		//creation de la liste des ascenseur sous forme de boutons alignes verticalement
		for(int i = 1; i <= batiment.getNbAscenseurs(); ++i){		
			final int j = i;
			final JToggleButton ascenseur = new JToggleButton("Elevator n�" + i);
			panelAscenseurs.add(ascenseur);
			if (i == 1){
				boutonAscenseurSelectionne = ascenseur;
				boutonAscenseurSelectionne.setSelected(true);
			}
			ascenseur.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ascenseurSelectionne != batiment.getAscenseur(j - 1)) {
						boutonAscenseurSelectionne.setSelected(false);			//le dernier selectionne ne l'est plus
						boutonAscenseurSelectionne = ascenseur;					//le bouton selectionne devient le nouveau clique
						ascenseurSelectionne = batiment.getAscenseur(j - 1);	//et l'ascenseur selectionne devient celui clique
						setChanged();
						notifyObservers();
					}
					else
						boutonAscenseurSelectionne.setSelected(true);
				}});
		}
		
		//===============BOUTONS===============
		
		JPanel panelPrincipalPanneau = new JPanel();
		panelPrincipalPanneau.setLayout(new GridLayout());
		fenetrePrincipale.add(panelPrincipalPanneau);
		panelPrincipalPanneau.setBorder(BorderFactory.createTitledBorder("Buttons"));
		
		JPanel panelPanneau = new JPanel();								//creation panel pour les boutons dans l'ascenseur
		panelPanneau.setLayout(new GridLayout(batiment.getNbEtages() + 2, 1));	//creation d'un layout pour ce panel
		
		JScrollPane scrollBoutons = new JScrollPane(panelPanneau);
		panelPrincipalPanneau.add(scrollBoutons);
		
		//création en boucle des boutons
		for (int i = 0; i <= batiment.getNbEtages() + 1; ++i){
			final int j = i;
			if (i == batiment.getNbEtages() + 1)
			{
				JButton boutonStop = new JButton("STOP");
				boutonStop.setBackground(Color.red);
				boutonStop.setForeground(Color.white);
				panelPanneau.add(boutonStop);
				boutonStop.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						if (!ascenseurSelectionne.estBloquer())
							ascenseurSelectionne.bloquer();
						else
							ascenseurSelectionne.debloquer();
						
					}});
			}
			else
			{
				JButton boutonDestination = new JButton(FonctionsUtiles.nommerEtage(i));
				boutonDestination.setBackground(Color.white);
				boutonDestination.setForeground(Color.black);
				panelPanneau.add(boutonDestination);
				boutonDestination.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						batiment.appuyerBoutonAscenseur(ascenseurSelectionne, j);
					}});
			}
		}
		
		fenetrePrincipale.setTitle(batiment.getNom() + " (lifts)");	//Titre de la fenêtre 
		fenetrePrincipale.setMinimumSize(new Dimension(300, 500));
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		fenetrePrincipale.setLocation(0, height/2 - fenetrePrincipale.getHeight()/2);
		fenetrePrincipale.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		fenetrePrincipale.setVisible(true);												
	}

	public Ascenseur getAscenseurSelectionne() {
		return ascenseurSelectionne;
	}
}
