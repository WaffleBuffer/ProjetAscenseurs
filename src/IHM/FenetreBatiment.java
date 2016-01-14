package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Client.Ascenseur;
import Client.Batiment;

public class FenetreBatiment extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labelNumAsc = new JLabel("1", JLabel.CENTER);
	private JLabel labelEtage = new JLabel("0", JLabel.CENTER);
	private ArrayList<JLabel> listeLabelNbAscenseursParEtage = new ArrayList<JLabel>();
	private Ascenseur ascenseurSelectionne;
	private	JLabel labelCourant;
	
	public FenetreBatiment (final Batiment bat, final FenetreRequetes fenetreRequetes) {
		
		ascenseurSelectionne = bat.getAscenseur(1);		//l'ascenseur 1 est celui qui est selectionné par défaut lors de la création
		
		this.setLayout(new GridLayout(1, 2));			//le layout principal de la fenetre est composé d'une ligne et deux colonnes
		
		JPanel panelPrincipalBatiment = new JPanel();	//le panel qui contiendra le jscrollpane du batiment
		panelPrincipalBatiment.setLayout(new GridLayout());	
		this.add(panelPrincipalBatiment);				//ajout de ce panel à la fenetre
		panelPrincipalBatiment.setBorder(BorderFactory.createTitledBorder("Building"));	//on encadre la partie batiment avec un titre
		
		JPanel panelBatiment = new JPanel();			//le panel qui sera dans un jscrollpane
		panelBatiment.setLayout(new GridLayout(bat.getNbEtages() + 1, 3));	
		//nb de lignes égal au nombre d'étage et une colonne pour le nom des étages, une pour la vue en coupe et une pour les boutons (haut/bas)

		JScrollPane scrollBatiment = new JScrollPane(panelBatiment);	//permet de scroll si le batiment est très haut
		panelPrincipalBatiment.add(scrollBatiment);		//ajout du jscollpane au panelprincipal de la partie batiment
		
		for (int i = 0; i <= bat.getNbEtages(); ++i){
			JLabel labelEtage = new JLabel(DenominationEtages.nommerEtage(bat.getNbEtages() - i), JLabel.CENTER);
			//un label par étage pour le nommer à l'aide de la fonction static prévue pour (le texte est centré)
			
			panelBatiment.add(labelEtage);				//ajout du label dans le panel representant le batiment
			
			JLabel labelNbAscenseursParEtage = new JLabel("yolo", JLabel.CENTER);	//ce label représente le nombre d'ascenseur présent à cet étage
			labelNbAscenseursParEtage.setOpaque(true);	//nécessaire pour colorer le fond du label
			labelNbAscenseursParEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));	//créer l'illusion du batiment vue en coupe avec des bordures noires 
			listeLabelNbAscenseursParEtage.add(labelNbAscenseursParEtage);	//ajout de ce label bien paramétré à une liste dans l'ordre pour pouvoir le retrouver
			panelBatiment.add(listeLabelNbAscenseursParEtage.get(i));	//on ajoute aussi cet élément au panel pour l'afficher
			
			JPanel panelBoutons = new JPanel();			//création du panel pour les boutons haut et bas
			JButton boutonHaut = new JButton("Up");		//création du bouton haut
			JButton boutonBas = new JButton("Down");	//création du bouton bas
			panelBoutons.setLayout(new GridLayout(2, 1)); 	//ce panel utilise un layout à deux lignes et une colonne
			panelBatiment.add(panelBoutons);	//ajout du panel des boutons à celui du batiment 
			if (i == 0){						//il n'y a pas de bouton haut au dernier étage
				panelBoutons.add(new JLabel());	
				panelBoutons.add(boutonBas);
			}
			else if (i == bat.getNbEtages()){	//il n'y a pas de bouton bas au rez-de-chaussée
				panelBoutons.add(boutonHaut);
			}
			else {								//tous les autres étages disposent des deux boutons haut et bas
				panelBoutons.add(boutonHaut);	
				panelBoutons.add(boutonBas);
			}
		}
		/*les cases représentant les étages du batiment vont prendre différentes couleurs selon l'état de
		 * l'ascenseur :	-ROUGE = bloqué
		 * 					-ORANGE = arrêté portes fermées
		 * 					-VERT = arrêté portes ouvertes
		 * 					-BLEU = en mouvement				
		 */
		labelCourant = listeLabelNbAscenseursParEtage.get(bat.getNbEtages() - ascenseurSelectionne.getEtage());
		//on sauvegarde le dernier label qui à été modifié en commençant par le rez-de-chaussée car les ascenseurs apparaissent là lors de leur création
		labelCourant.setBackground(Color.orange);	//lors de la création l'ascenseur est à l'arrêt portes ouvertes.
		
		JPanel panelInfos = new JPanel();			//panel qui contiendra les détails sur l'asenseur selectionné
		panelInfos.setLayout(new GridLayout(5,1));	//son layout est composé de 5 lignes et une colonne (alignés vertcalement)
		panelInfos.setBorder(BorderFactory.createTitledBorder("Details current elevator"));	//on encadre encore une fois cette partie pour plus de visibilité
		this.add(panelInfos);						//ajout de ce panel à la fenetre
		
		JLabel labelTexteNumAsc = new JLabel("Elevator's number :", JLabel.CENTER);	
		JLabel labelTexteEtageAsc = new JLabel("Elevator's floor :", JLabel.CENTER);
		panelInfos.add(labelTexteNumAsc);
		panelInfos.add(labelNumAsc);
		panelInfos.add(labelTexteEtageAsc);
		panelInfos.add(labelEtage);
		JButton boutonSimulation = new JButton("Simulate (step by step)");	//bouton qui va lancer une étape de la simulation 
		panelInfos.add(boutonSimulation);
		
		boutonSimulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bat.traiterControleurs();	//les requetes en attente sont traiter dans l'ordre de priorité
				getLabelEtage().setText(String.valueOf(ascenseurSelectionne.getEtage()));	//actualise l'étage actuel de l'ascenseur
				getLabelCourant().setBackground(null);
				setLabelCourant(getListeNbAscenseursParEtage().get(bat.getNbEtages() - ascenseurSelectionne.getEtage()));
				if (ascenseurSelectionne.estBloquer())
					getLabelCourant().setBackground(Color.red);
				else if (ascenseurSelectionne.isEstEnMouvement())
					getLabelCourant().setBackground(Color.blue);
				else if (!ascenseurSelectionne.isPortesOuvertes())
					getLabelCourant().setBackground(Color.orange);
				else 
					getLabelCourant().setBackground(Color.green);
				fenetreRequetes.actualiserText();
			}});
		
		//reglages de la fenêtre
		this.setTitle(bat.getNom() + " (sectionnal view [" + bat.getNbEtages() + " floors])");	//Titre de la fenêtre 
		this.setMinimumSize(new Dimension(590, 500));									//taille de la fenêtre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setLocation(width/2 - this.getWidth()/2, height - this.getHeight() - 10);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);									//la fenêtre apparaît
		
		//permet d'initialiser la scrollbar du batiment en bas pour que le rez-de-chaussée soit visible à l'apparition
		scrollBatiment.getVerticalScrollBar().setValue(scrollBatiment.getVerticalScrollBar().getMaximum()); 		
	}

	public Ascenseur getAscenseur() {
		return ascenseurSelectionne;
	}

	public void setAscenseur(Ascenseur ascenseur) {
		this.ascenseurSelectionne = ascenseur;
	}

	public void setLabelNumAsc(JLabel labelNumAsc) {
		this.labelNumAsc = labelNumAsc;
	}

	public void setLabelEtage(JLabel labelEtage) {
		this.labelEtage = labelEtage;
	}

	public ArrayList<JLabel> getListeNbAscenseursParEtage() {
		return listeLabelNbAscenseursParEtage;
	}

	public JLabel getLabelNumAsc() {
		return labelNumAsc;
	}

	public JLabel getLabelEtage() {
		return labelEtage;
	}

	public ArrayList<JLabel> getListeLabelNbAscenseursParEtage() {
		return listeLabelNbAscenseursParEtage;
	}

	public void setListeLabelNbAscenseursParEtage(
			ArrayList<JLabel> listeLabelNbAscenseursParEtage) {
		this.listeLabelNbAscenseursParEtage = listeLabelNbAscenseursParEtage;
	}

	public JLabel getLabelCourant() {
		return labelCourant;
	}

	public void setLabelCourant(JLabel labelCourant) {
		this.labelCourant = labelCourant;
	}
}
