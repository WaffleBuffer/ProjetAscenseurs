package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Client.Ascenseur;
import Client.Batiment;
import Client.Constantes;

public class FenetreBatiment extends JFrame implements Observer{

	private static final long serialVersionUID = 1L;
	private JLabel labelNumAscenseur = new JLabel("1", JLabel.CENTER);
	private JLabel labelEtageAscenseurSelectionne = new JLabel("0", JLabel.CENTER);
	private ArrayList<JLabel> listeLabelNbAscenseursParEtage = new ArrayList<JLabel>();
	private	JLabel labelCourant;
	private Batiment batiment;
	
	public FenetreBatiment (final Batiment batiment) {
		
		this.batiment = batiment;
		this.setLayout(new GridLayout(1, 2));			//le layout principal de la fenetre est composé d'une ligne et deux colonnes
		
		JPanel panelPrincipalBatiment = new JPanel();	//le panel qui contiendra le jscrollpane du batiment
		panelPrincipalBatiment.setLayout(new GridLayout());	
		this.add(panelPrincipalBatiment);				//ajout de ce panel à la fenetre
		panelPrincipalBatiment.setBorder(BorderFactory.createTitledBorder("Building"));	//on encadre la partie batiment avec un titre
		
		JPanel panelBatiment = new JPanel();			//le panel qui sera dans un jscrollpane
		panelBatiment.setLayout(new GridLayout(batiment.getNbEtages() + 1, 3));	
		//nb de lignes égal au nombre d'étage et une colonne pour le nom des étages, une pour la vue en coupe et une pour les boutons (haut/bas)

		JScrollPane scrollBatiment = new JScrollPane(panelBatiment);	//permet de scroll si le batiment est très haut
		panelPrincipalBatiment.add(scrollBatiment);		//ajout du jscollpane au panelprincipal de la partie batiment
		
		for (int i = 0; i <= batiment.getNbEtages(); ++i){
			final int j = i;
			JLabel labelEtage = new JLabel(FonctionsUtiles.nommerEtage(batiment.getNbEtages() - i), JLabel.CENTER);
			//un label par étage pour le nommer à l'aide de la fonction static prévue pour (le texte est centré)
			
			panelBatiment.add(labelEtage);				//ajout du label dans le panel representant le batiment
			
			JLabel labelNbAscenseursParEtage = new JLabel("0", JLabel.CENTER);
			if (i == batiment.getNbEtages())
				labelNbAscenseursParEtage.setText(String.valueOf(batiment.getNbAscenseurs()));	//ce label représente le nombre d'ascenseur présent à cet étage

			labelNbAscenseursParEtage.setOpaque(true);	//nécessaire pour colorer le fond du label
			labelNbAscenseursParEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));	//créer l'illusion du batiment vue en coupe avec des bordures noires 
			listeLabelNbAscenseursParEtage.add(labelNbAscenseursParEtage);	//ajout de ce label bien paramétré à une liste dans l'ordre pour pouvoir le retrouver
			panelBatiment.add(listeLabelNbAscenseursParEtage.get(i));	//on ajoute aussi cet élément au panel pour l'afficher
			
			JPanel panelBoutons = new JPanel();			//création du panel pour les boutons haut et bas
			JButton boutonHaut = new JButton("Up");		//création du bouton haut
			
			boutonHaut.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					batiment.appuyerBoutonEtage(batiment.getNbEtages() - j, Constantes.HAUT);
				}});
			JButton boutonBas = new JButton("Down");	//création du bouton bas
			boutonBas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					batiment.appuyerBoutonEtage(batiment.getNbEtages() - j - 1, Constantes.BAS);
				}});
			panelBoutons.setLayout(new GridLayout(2, 1)); 	//ce panel utilise un layout à deux lignes et une colonne
			panelBatiment.add(panelBoutons);	//ajout du panel des boutons à celui du batiment 
			if (i == 0){						//il n'y a pas de bouton haut au dernier étage
				panelBoutons.add(new JLabel());	
				panelBoutons.add(boutonBas);
			}
			else if (i == batiment.getNbEtages()){	//il n'y a pas de bouton bas au rez-de-chaussée
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
		labelCourant = listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - 0);
		//on sauvegarde le dernier label qui à été modifié en commençant par le rez-de-chaussée car les ascenseurs apparaissent là lors de leur création
		labelCourant.setBackground(Color.orange);	//lors de la création l'ascenseur est à l'arrêt portes ouvertes.
		
		batiment.setAscenseurSelectionne(this.batiment.getAscenseur(0));
		JPanel panelInfos = new JPanel();			//panel qui contiendra les détails sur l'asenseur selectionné
		panelInfos.setLayout(new GridLayout(7,1));	//son layout est composé de 5 lignes et une colonne (alignés vertcalement)
		panelInfos.setBorder(BorderFactory.createTitledBorder("Details current elevator"));	//on encadre encore une fois cette partie pour plus de visibilité
		this.add(panelInfos);						//ajout de ce panel à la fenetre
		
		JLabel labelTexteLegende = new JLabel("Legend :", JLabel.CENTER);
		panelInfos.add(labelTexteLegende);
		
		JPanel panelLegende = new JPanel();
		panelLegende.setLayout(new GridLayout(1, 4, 2, 0));
		
		JLabel labelRouge = new JLabel("Blocked", JLabel.CENTER);
		labelRouge.setOpaque(true);
		labelRouge.setBorder(BorderFactory.createLineBorder(Color.black));
		labelRouge.setForeground(Color.white);
		labelRouge.setBackground(Color.red);
		JLabel labelOrange = new JLabel("Stopped", JLabel.CENTER);
		labelOrange.setOpaque(true);
		labelOrange.setBorder(BorderFactory.createLineBorder(Color.black));
		labelOrange.setForeground(Color.white);
		labelOrange.setBackground(Color.orange);
		JLabel labelBleu = new JLabel("Move", JLabel.CENTER);
		labelBleu.setOpaque(true);
		labelBleu.setBorder(BorderFactory.createLineBorder(Color.black));
		labelBleu.setForeground(Color.white);
		labelBleu.setBackground(Color.blue);
		JLabel labelVert = new JLabel("Open doors", JLabel.CENTER);
		labelVert.setOpaque(true);
		labelVert.setBorder(BorderFactory.createLineBorder(Color.black));
		labelVert.setForeground(Color.white);
		labelVert.setBackground(Color.green);
		
		panelLegende.add(labelRouge);
		panelLegende.add(labelOrange);
		panelLegende.add(labelBleu);
		panelLegende.add(labelVert);
		panelInfos.add(panelLegende);
		
		JLabel labelTexteNumAsc = new JLabel("Elevator's number :", JLabel.CENTER);	
		JLabel labelTexteEtageAsc = new JLabel("Elevator's floor :", JLabel.CENTER);
		panelInfos.add(labelTexteNumAsc);
		panelInfos.add(labelNumAscenseur);
		panelInfos.add(labelTexteEtageAsc);
		panelInfos.add(labelEtageAscenseurSelectionne);
		JButton boutonSimulation = new JButton("Simulate (step by step)");	//bouton qui va lancer une étape de la simulation 
		panelInfos.add(boutonSimulation);
		
		boutonSimulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				batiment.traiterControleurs();	//les requetes en attente sont traiter dans l'ordre de priorité
			}});
		
		//reglages de la fenêtre
		this.setTitle(batiment.getNom() + " (sectionnal view [" + batiment.getNbEtages() + " floors])");	
		this.setMinimumSize(new Dimension(720, 550));										
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setLocation(width/2 - this.getWidth()/2, height - this.getHeight() - 10);	//positionnement de la fenetre en bas centrée
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);									
		
		//permet d'initialiser la scrollbar du batiment en bas pour que le rez-de-chaussée soit visible à l'apparition
		scrollBatiment.getVerticalScrollBar().setValue(scrollBatiment.getVerticalScrollBar().getMaximum());
	}

	public void setLabelNumAsc(JLabel labelNumAsc) {
		this.labelNumAscenseur = labelNumAsc;
	}

	public void setLabelEtage(JLabel labelEtage) {
		this.labelEtageAscenseurSelectionne = labelEtage;
	}

	public ArrayList<JLabel> getListeNbAscenseursParEtage() {
		return listeLabelNbAscenseursParEtage;
	}

	public JLabel getLabelNumAsc() {
		return labelNumAscenseur;
	}

	public JLabel getLabelEtageAscenseurSelectionne() {
		return labelEtageAscenseurSelectionne;
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

	@Override
	public void update(Observable observe, Object arg1) {
		if (observe.getClass() == Batiment.class || observe.getClass() == Ascenseur.class){
			labelEtageAscenseurSelectionne.setText(Integer.toString(batiment.getAscenseurSelectionne().getEtage()));	//actualise l'étage actuel de l'ascenseur
			labelCourant.setOpaque(false);
			setLabelCourant(listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - batiment.getAscenseurSelectionne().getEtage()));
			labelCourant.setOpaque(true);
			FonctionsUtiles.afficherEtatAscenseur(batiment.getAscenseurSelectionne(), labelCourant);
			for (int i = 0; i < listeLabelNbAscenseursParEtage.size(); ++i){
				listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - i).setText(Integer.toString((FonctionsUtiles.NbAscenseursParEtage(batiment, i))));
			}
		}
		else if (observe.getClass() == FenetrePanneau.class){
			for (int i = 0; i < listeLabelNbAscenseursParEtage.size(); ++i){
				listeLabelNbAscenseursParEtage.get(i).setBackground(null);
			}
			labelCourant.setBackground(null);
			setLabelCourant(listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - batiment.getAscenseurSelectionne().getEtage()));
			FonctionsUtiles.afficherEtatAscenseur(batiment.getAscenseurSelectionne(), labelCourant);
		}
	}
}
