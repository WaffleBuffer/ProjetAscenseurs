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

/**Decrit la fenetre de vue en coupe d'un {@link Batiment}.
 * @author Thomas
 */
public class FenetreBatiment extends JFrame implements Observer{

	/**Demande par {@link JFrame}
	 * @see JFrame
	 */
	private static final long serialVersionUID = 1L;
	
	/**{@link JLabel} correspondant au {@link Ascenseur#numAscenseur} de l'{@link Ascenseur} selectionne.
	 */
	private JLabel labelNumAscenseur = new JLabel("1", JLabel.CENTER);
	
	/**{@link JLabel} correspondant a la representation de l'etage de l'{@link Ascenseur} selectionne.
	 */
	private JLabel labelEtageAscenseurSelectionne = new JLabel("0", JLabel.CENTER);
	
	/**Liste des {@link JLabel} representant le nombre d'{@link Ascenseur} a chaque etage.
	 */
	private ArrayList<JLabel> listeLabelNbAscenseursParEtage = new ArrayList<JLabel>();
	
	/**Le {@link JLabel} representant l'etat de l'{@link Ascenseur} selectionne. 
	 */
	private	JLabel labelCourant;
	
	/**Le {@link Batiment} sur laquelle porte cette FenetreBatiment.
	 */
	private Batiment batiment;
	
	/**L'{@link Ascenseur} actuellement selectionne la {@link FenetrePanneau} observee par cette FenetreBatiment.
	 * @see #update(Observable, Object)
	 * @see FenetreConfiguration#FenetreConfiguration()
	 */
	private Ascenseur ascenseurSelectionne;
	
	/**Construit une FenetreBatiment pour un {@link Batiment} donne.
	 * @param batiment le {@link Batiment} sur laquelle porte cette FenetreBatiment.
	 */
	public FenetreBatiment (final Batiment batiment) {
		
		this.batiment = batiment;
		this.ascenseurSelectionne = batiment.getAscenseur(0);
		this.setLayout(new GridLayout(1, 2));			//le layout principal de la fenetre est compose d'une ligne et deux colonnes
		
		JPanel panelPrincipalBatiment = new JPanel();	//le panel qui contiendra le jscrollpane du batiment
		panelPrincipalBatiment.setLayout(new GridLayout());	
		this.add(panelPrincipalBatiment);				//ajout de ce panel a la fenetre
		panelPrincipalBatiment.setBorder(BorderFactory.createTitledBorder("Building"));	//on encadre la partie batiment avec un titre
		
		JPanel panelBatiment = new JPanel();			//le panel qui sera dans un jscrollpane
		panelBatiment.setLayout(new GridLayout(batiment.getNbEtages() + 1, 3));	
		//nb de lignes egal au nombre d'etage et une colonne pour le nom des etages, une pour la vue en coupe et une pour les boutons (haut/bas)

		JScrollPane scrollBatiment = new JScrollPane(panelBatiment);	//permet de scroll si le batiment est tres haut
		panelPrincipalBatiment.add(scrollBatiment);		//ajout du jscollpane au panelprincipal de la partie batiment
		
		for (int i = 0; i <= batiment.getNbEtages(); ++i){
			final int j = i;
			JLabel labelEtage = new JLabel(FonctionsUtiles.nommerEtage(batiment.getNbEtages() - i), JLabel.CENTER);
			//un label par etage pour le nommer a l'aide de la fonction static prevue pour (le texte est centre)
			
			panelBatiment.add(labelEtage);				//ajout du label dans le panel representant le batiment
			
			JLabel labelNbAscenseursParEtage = new JLabel("0", JLabel.CENTER);
			if (i == batiment.getNbEtages())
				labelNbAscenseursParEtage.setText(String.valueOf(batiment.getNbAscenseurs()));	//ce label represente le nombre d'ascenseur present a cet etage

			labelNbAscenseursParEtage.setOpaque(true);	//necessaire pour colorer le fond du label
			labelNbAscenseursParEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));	//creer l'illusion du batiment vue en coupe avec des bordures noires 
			listeLabelNbAscenseursParEtage.add(labelNbAscenseursParEtage);	//ajout de ce label bien parametre a une liste dans l'ordre pour pouvoir le retrouver
			panelBatiment.add(listeLabelNbAscenseursParEtage.get(i));	//on ajoute aussi cet element au panel pour l'afficher
			
			JPanel panelBoutons = new JPanel();			//creation du panel pour les boutons haut et bas
			JButton boutonHaut = new JButton("Up");		//creation du bouton haut
			
			boutonHaut.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					batiment.appuyerBoutonEtage(batiment.getNbEtages() - j, Constantes.HAUT);
				}});
			JButton boutonBas = new JButton("Down");	//creation du bouton bas
			boutonBas.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					batiment.appuyerBoutonEtage(batiment.getNbEtages() - j - 1, Constantes.BAS);
				}});
			panelBoutons.setLayout(new GridLayout(2, 1)); 	//ce panel utilise un layout a deux lignes et une colonne
			panelBatiment.add(panelBoutons);	//ajout du panel des boutons a celui du batiment 
			if (i == 0){						//il n'y a pas de bouton haut au dernier etage
				panelBoutons.add(new JLabel());	
				panelBoutons.add(boutonBas);
			}
			else if (i == batiment.getNbEtages()){	//il n'y a pas de bouton bas au rez-de-chausse
				panelBoutons.add(boutonHaut);
			}
			else {								//tous les autres etages disposent des deux boutons haut et bas
				panelBoutons.add(boutonHaut);	
				panelBoutons.add(boutonBas);
			}
		}
		/*les cases representant les etages du batiment vont prendre differentes couleurs selon l'etat de
		 * l'ascenseur :	-ROUGE = bloque
		 * 					-ORANGE = arrete portes fermees
		 * 					-VERT = arrete portes ouvertes
		 * 					-BLEU = en mouvement				
		 */
		labelCourant = listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - 0);
		//on sauvegarde le dernier label qui a ete modifie en commencant par le rez-de-chausse car les ascenseurs apparaissent la lors de leur creation
		labelCourant.setBackground(Color.orange);	//lors de la creation l'ascenseur est a l'arret portes ouvertes.
		
		JPanel panelInfos = new JPanel();			//panel qui contiendra les details sur l'asenseur selectionne
		panelInfos.setLayout(new GridLayout(7,1));	//son layout est compose de 5 lignes et une colonne (alignes vertcalement)
		panelInfos.setBorder(BorderFactory.createTitledBorder("Details current elevator"));	//on encadre encore une fois cette partie pour plus de visibilite
		this.add(panelInfos);						//ajout de ce panel a la fenetre
		
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
		JButton boutonSimulation = new JButton("Simulate (step by step)");	//bouton qui va lancer une etape de la simulation 
		panelInfos.add(boutonSimulation);
		
		boutonSimulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				batiment.traiterControleurs();	//les requetes en attente sont traiter dans l'ordre de priorite
			}});
		
		//reglages de la fenetre
		this.setTitle(batiment.getNom() + " (sectionnal view [" + batiment.getNbEtages() + " floors])");	
		this.setMinimumSize(new Dimension(720, 550));										
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		this.setLocation(width/2 - this.getWidth()/2, height - this.getHeight() - 10);	//positionnement de la fenetre en bas centree
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);									
		
		//permet d'initialiser la scrollbar du batiment en bas pour que le rez-de-chaussee soit visible a l'apparition
		scrollBatiment.getVerticalScrollBar().setValue(scrollBatiment.getVerticalScrollBar().getMaximum());
	}

	@Override
	public void update(Observable observe, Object arg1) {
		if (observe.getClass() == Ascenseur.class){
			if (((Ascenseur) observe).equals(ascenseurSelectionne)) {
				labelEtageAscenseurSelectionne.setText(Integer.toString(((Ascenseur) observe).getEtage()));	//actualise l'etage actuel de l'ascenseur
				labelCourant.setBackground(null);
				labelCourant.setOpaque(false);
				labelCourant = listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - ((Ascenseur) observe).getEtage());
				labelCourant.setOpaque(true);
				FonctionsUtiles.afficherEtatAscenseur(((Ascenseur) observe), labelCourant);
			}
			for (int i = 0; i < listeLabelNbAscenseursParEtage.size(); ++i){
				listeLabelNbAscenseursParEtage.get(batiment.getNbEtages() - i).setText(Integer.toString((FonctionsUtiles.NbAscenseursParEtage(batiment, i))));
			}
		}
		else if (observe.getClass() == FenetrePanneau.class){
			this.ascenseurSelectionne = ((FenetrePanneau) observe).getAscenseurSelectionne();
			labelNumAscenseur.setText(Integer.toString(ascenseurSelectionne.getNumAsc()));
			labelCourant.setOpaque(false);
			labelCourant.setBackground(null);
			labelCourant = listeLabelNbAscenseursParEtage.get(batiment.getNbEtages()
					- ascenseurSelectionne.getEtage());
			labelCourant.setOpaque(true);
			FonctionsUtiles.afficherEtatAscenseur(ascenseurSelectionne, labelCourant);
		}
	}
}
