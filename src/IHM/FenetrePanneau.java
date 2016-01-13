package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;

import Client.Ascenseur;
import Client.Batiment;

public class FenetrePanneau extends JFrame{
	
	/**
	 * 
	 */
	private Ascenseur ascenseurSelectionne;
	
	public Ascenseur getAscenseurActuel() {
		return ascenseurSelectionne;
	}

	private JToggleButton boutonAscenseur;
	
	private static final long serialVersionUID = 1L;

	public FenetrePanneau (final Batiment bat, int nbAsc, final FenetreBatiment fenetreBatiment){
		GridLayout layoutPrincipal = new GridLayout(2, 1);		//création layout principal de la fenetre
		layoutPrincipal.setVgap(20);							//ajout margin vertical entre éléments du layout
		this.setLayout(layoutPrincipal);						//associationd du layout à la fenetre
		
		JPanel panelPrincipalAscenseurs = new JPanel();
		panelPrincipalAscenseurs.setLayout(new GridLayout());
		this.add(panelPrincipalAscenseurs);
		panelPrincipalAscenseurs.setBorder(BorderFactory.createTitledBorder("Lifts"));
		ascenseurSelectionne = bat.getAscenseur(1);					//l'ascenseur affiché lors de la creation de la fenetre est celui d'id 1
		
		JPanel panelAscenseurs = new JPanel();
		panelAscenseurs.setLayout(new GridLayout(nbAsc, 1));	
		
		JScrollPane scrollAscenseurs = new JScrollPane(panelAscenseurs);
		panelPrincipalAscenseurs.add(scrollAscenseurs);
		
		//creation de la liste des ascenseur sous forme de boutons alignés verticalement
		for(int i = 1; i <= nbAsc; ++i){		
			final int j = i;
			final JToggleButton ascenseur = new JToggleButton("Elevator n°" + i);
			panelAscenseurs.add(ascenseur);
			if (i == 1){
				boutonAscenseur = ascenseur;
				boutonAscenseur.setSelected(true);
			}
			ascenseur.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if (ascenseurSelectionne != bat.getAscenseur(j)) {
						boutonAscenseur.setSelected(false);
						boutonAscenseur = ascenseur;
						ascenseurSelectionne = bat.getAscenseur(j);		//et l'ascenseur selectionne devient celui cliqué
						fenetreBatiment.setAscenseur(ascenseurSelectionne);
						fenetreBatiment.getLabelNumAsc().setText(String.valueOf(ascenseurSelectionne.getNumAsc()));
						fenetreBatiment.getListeLabelNbAscenseursParEtage().get(bat.getNbEtages() - 
								ascenseurSelectionne.getEtage()).setBackground(Color.blue);
					}
					else
						boutonAscenseur.setSelected(true);
				}});
		}
		
		//===============BOUTONS===============
		
		JPanel panelPrincipalPanneau = new JPanel();
		panelPrincipalPanneau.setLayout(new GridLayout());
		this.add(panelPrincipalPanneau);
		panelPrincipalPanneau.setBorder(BorderFactory.createTitledBorder("Buttons"));
		
		JPanel panelPanneau = new JPanel();								//creation panel pour les boutons dans l'ascenseur
		panelPanneau.setLayout(new GridLayout(bat.getNbEtages() + 2, 1));	//creation d'un layout pour ce panel
		
		JScrollPane scrollBoutons = new JScrollPane(panelPanneau);
		panelPrincipalPanneau.add(scrollBoutons);
		
		//création en boucle des boutons
		for (int i = 0; i < bat.getNbEtages() + 2; ++i){
			final int j = i;
			if (i == bat.getNbEtages() + 1)
			{
				JButton boutonStop = new JButton("STOP");
				panelPanneau.add(boutonStop);
			}
			else
			{
				JButton boutonDestination = new JButton(DenominationEtages.nommerEtage(i));
				panelPanneau.add(boutonDestination);
				boutonDestination.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						bat.appuyerBoutonAscenseur(ascenseurSelectionne.getNumAsc(), j);
						
					}});
			}
		}
		
		this.setTitle(bat.getNom() + " (lifts)");	//Titre de la fenêtre 
		this.setMinimumSize(new Dimension(300, 500));
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setLocation(0, height/2 - this.getHeight()/2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);												//la fenêtre apparaît
	}
}
