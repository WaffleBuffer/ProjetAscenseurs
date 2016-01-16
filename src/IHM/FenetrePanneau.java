package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import Client.Ascenseur;
import Client.Batiment;

public class FenetrePanneau extends JFrame {


	private JToggleButton boutonAscenseurSelectionne;
	private static final long serialVersionUID = 1L;

	public FenetrePanneau (final Batiment batiment){
		GridLayout layoutPrincipal = new GridLayout(2, 1);		//création layout principal de la fenetre
		layoutPrincipal.setVgap(20);							//ajout margin vertical entre éléments du layout
		this.setLayout(layoutPrincipal);						//associationd du layout à la fenetre
		JPanel panelPrincipalAscenseurs = new JPanel();
		panelPrincipalAscenseurs.setLayout(new GridLayout());
		this.add(panelPrincipalAscenseurs);
		panelPrincipalAscenseurs.setBorder(BorderFactory.createTitledBorder("Lifts"));
		
		JPanel panelAscenseurs = new JPanel();
		panelAscenseurs.setLayout(new GridLayout(batiment.getNbAscenseurs(), 1));	
		
		JScrollPane scrollAscenseurs = new JScrollPane(panelAscenseurs);
		panelPrincipalAscenseurs.add(scrollAscenseurs);
		
		//creation de la liste des ascenseur sous forme de boutons alignés verticalement
		for(int i = 1; i <= batiment.getNbAscenseurs(); ++i){		
			final int j = i;
			final JToggleButton ascenseur = new JToggleButton("Elevator n°" + i);
			panelAscenseurs.add(ascenseur);
			if (i == 1){
				boutonAscenseurSelectionne = ascenseur;
				boutonAscenseurSelectionne.setSelected(true);
			}
			ascenseur.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (batiment.getAscenseurSelectionne() != batiment.getAscenseur(j - 1)) {
						boutonAscenseurSelectionne.setSelected(false);			//le dernier sélectionné ne l'est plus
						boutonAscenseurSelectionne = ascenseur;					//le bouton sélectionné devient le nouveau cliqué
						batiment.setAscenseurSelectionne(batiment.getAscenseur(j - 1));		//et l'ascenseur selectionne devient celui cliqué
						
						//fenetreBatiment.setAscenseur(ascenseurSelectionne);	//idem pour la fenetreBatiment
						//fenetreBatiment.getLabelNumAsc().setText(String.valueOf(ascenseurSelectionne.getNumAsc()));	//actualisation du numéro du nouvel ascenseur
						//fenetreBatiment.getListeLabelNbAscenseursParEtage().get(bat.getNbEtages() - 
						//ascenseurSelectionne.getEtage()).setBackground(Color.orange);
						//AscenseurAvecSonLabelActuel.get(ascenseurSelectionne).setBackground(Color.orange);
						//fenetreBatiment.getLabelCourant().setBackground(null);
					}
					else
						boutonAscenseurSelectionne.setSelected(true);
				}});
		}
		
		//===============BOUTONS===============
		
		JPanel panelPrincipalPanneau = new JPanel();
		panelPrincipalPanneau.setLayout(new GridLayout());
		this.add(panelPrincipalPanneau);
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
						if (!batiment.getAscenseurSelectionne().estBloquer())
							batiment.getAscenseurSelectionne().bloquer();
						else
							batiment.getAscenseurSelectionne().debloque();
						
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
						batiment.appuyerBoutonAscenseur(batiment.getAscenseurSelectionne(), j);
					}});
			}
		}
		batiment.setAscenseurSelectionne(batiment.getAscenseur(0));
		
		this.setTitle(batiment.getNom() + " (lifts)");	//Titre de la fenêtre 
		this.setMinimumSize(new Dimension(300, 500));
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		this.setLocation(0, height/2 - this.getHeight()/2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);												//la fenetre apparait
	}
}
