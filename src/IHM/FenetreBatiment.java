package IHM;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

	/**
	 *String.valueOf(panneau.getAscenseurActuel().getNumAsc()), JLabel.CENTER
	 *String.valueOf(panneau.getAscenseurActuel().getEtage()), JLabel.CENTER
	 */
	private JLabel labelNumAsc = new JLabel("1", JLabel.CENTER);
	private Ascenseur ascenseur;
	private JLabel labelEtage = new JLabel("0", JLabel.CENTER);
	private ArrayList<JLabel> listeLabelNbAscenseursParEtage = new ArrayList<JLabel>();
	
	public FenetreBatiment (final Batiment bat) {
		
		ascenseur = bat.getAscenseur(1);
		
		//layout de la fenêtre
		this.setLayout(new GridLayout(1, 2));
		
		JPanel panelPrincipalBatiment = new JPanel();
		panelPrincipalBatiment.setLayout(new GridLayout());
		this.add(panelPrincipalBatiment);
		panelPrincipalBatiment.setBorder(BorderFactory.createTitledBorder("Bâtiment"));
		
		JPanel panelBatiment = new JPanel();
		panelBatiment.setLayout(new GridLayout(bat.getNbEtages() + 1, 3));

		JScrollPane scrollBatiment = new JScrollPane(panelBatiment);
		panelPrincipalBatiment.add(scrollBatiment);
		
		for (int i = 0; i <= bat.getNbEtages(); ++i){
			JLabel labelEtage = new JLabel(String.valueOf(bat.getNbEtages() - i), JLabel.CENTER);
			panelBatiment.add(labelEtage);
			
			JLabel labelNbAscenseursParEtage = new JLabel("yolo", JLabel.CENTER);
			labelNbAscenseursParEtage.setOpaque(true);
			labelNbAscenseursParEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			listeLabelNbAscenseursParEtage.add(labelNbAscenseursParEtage);
			
			panelBatiment.add(listeLabelNbAscenseursParEtage.get(i));
			
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
		/*les cases représentant les étages du batiment vont prendre différentes couleurs selon l'état de
		 * l'ascenseur :	-ROUGE = bloqué
		 * 					-ORANGE = arrêté portes fermées
		 * 					-VERT = arrêté portes ouvertes
		 * 					-BLEU = en mouvement				
		 */
		listeLabelNbAscenseursParEtage.get(bat.getNbEtages() - ascenseur.getEtage()).setBackground(Color.orange);
		
		JPanel panelInfos = new JPanel();
		panelInfos.setLayout(new GridLayout(5,1));
		panelInfos.setBorder(BorderFactory.createTitledBorder("Détails ascenseur actuel"));
		this.add(panelInfos);
		
		JLabel labelTexteNumAsc = new JLabel("Numéro de l'ascenseur :", JLabel.CENTER);
		JLabel labelTexteEtageAsc = new JLabel("Etage de l'ascenseur :", JLabel.CENTER);
		panelInfos.add(labelTexteNumAsc);
		panelInfos.add(labelNumAsc);
		panelInfos.add(labelTexteEtageAsc);
		panelInfos.add(labelEtage);
		JButton boutonSimulation = new JButton("Simuler par étape");
		panelInfos.add(boutonSimulation);
		
		boutonSimulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bat.traiterControleurs();
				getLabelEtage().setText(String.valueOf(ascenseur.getEtage()));
				if (ascenseur.getEtage() >= 1)
					getListeNbAscenseursParEtage().get(bat.getNbEtages() - ascenseur.getEtage() + 1).setBackground(null);
				getListeNbAscenseursParEtage().get(bat.getNbEtages() - ascenseur.getEtage()).setBackground(Color.blue);
			}});
		
		//reglages de la fenêtre
		this.setTitle(bat.getNom() + " (" + bat.getNbEtages() + " étages)");	//Titre de la fenêtre 
		this.setSize(500, 500);									//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
		
		//permet d'initialiser la scrollbar du batiment en bas pour que le rez-de-chaussée soit visible à l'apparition
		scrollBatiment.getVerticalScrollBar().setValue(scrollBatiment.getVerticalScrollBar().getMaximum()); 		

	}

	public Ascenseur getAscenseur() {
		return ascenseur;
	}

	public void setAscenseur(Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
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
}
