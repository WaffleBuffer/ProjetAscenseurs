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

	/**
	 *String.valueOf(panneau.getAscenseurActuel().getNumAsc()), JLabel.CENTER
	 *String.valueOf(panneau.getAscenseurActuel().getEtage()), JLabel.CENTER
	 */
	private JLabel labelNumAsc = new JLabel("1", JLabel.CENTER);
	private JLabel labelEtage = new JLabel("0", JLabel.CENTER);
	private ArrayList<JLabel> listeLabelNbAscenseursParEtage = new ArrayList<JLabel>();
	private Ascenseur ascenseurSelectionne;
	private	JLabel labelCourant;
	
	public FenetreBatiment (final Batiment bat) {
		
		ascenseurSelectionne = bat.getAscenseur(1);
		
		//layout de la fenêtre
		this.setLayout(new GridLayout(1, 2));
		
		JPanel panelPrincipalBatiment = new JPanel();
		panelPrincipalBatiment.setLayout(new GridLayout());
		this.add(panelPrincipalBatiment);
		panelPrincipalBatiment.setBorder(BorderFactory.createTitledBorder("Building"));
		
		JPanel panelBatiment = new JPanel();
		panelBatiment.setLayout(new GridLayout(bat.getNbEtages() + 1, 3));

		JScrollPane scrollBatiment = new JScrollPane(panelBatiment);
		panelPrincipalBatiment.add(scrollBatiment);
		
		for (int i = 0; i <= bat.getNbEtages(); ++i){
			JLabel labelEtage = new JLabel(DenominationEtages.nommerEtage(bat.getNbEtages() - i), JLabel.CENTER);
			panelBatiment.add(labelEtage);
			
			JLabel labelNbAscenseursParEtage = new JLabel("yolo", JLabel.CENTER);
			labelNbAscenseursParEtage.setOpaque(true);
			labelNbAscenseursParEtage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			listeLabelNbAscenseursParEtage.add(labelNbAscenseursParEtage);
			
			panelBatiment.add(listeLabelNbAscenseursParEtage.get(i));
			
			JPanel panelBoutons = new JPanel();
			JButton boutonHaut = new JButton("Up");
			JButton boutonBas = new JButton("Down");
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
		labelCourant = listeLabelNbAscenseursParEtage.get(bat.getNbEtages() - ascenseurSelectionne.getEtage());
		labelCourant.setBackground(Color.orange);
		
		JPanel panelInfos = new JPanel();
		panelInfos.setLayout(new GridLayout(5,1));
		panelInfos.setBorder(BorderFactory.createTitledBorder("Details current elevator"));
		this.add(panelInfos);
		
		JLabel labelTexteNumAsc = new JLabel("Elevator's number :", JLabel.CENTER);
		JLabel labelTexteEtageAsc = new JLabel("Elevator's floor :", JLabel.CENTER);
		panelInfos.add(labelTexteNumAsc);
		panelInfos.add(labelNumAsc);
		panelInfos.add(labelTexteEtageAsc);
		panelInfos.add(labelEtage);
		JButton boutonSimulation = new JButton("Simulate (step by step)");
		panelInfos.add(boutonSimulation);
		
		boutonSimulation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				bat.traiterControleurs();
				getLabelEtage().setText(String.valueOf(ascenseurSelectionne.getEtage()));
				if (ascenseurSelectionne.getEtage() >= 1)
					getListeNbAscenseursParEtage().get(bat.getNbEtages() - ascenseurSelectionne.getEtage() + 1).setBackground(null);
				getListeNbAscenseursParEtage().get(bat.getNbEtages() - ascenseurSelectionne.getEtage()).setBackground(Color.blue);
			}});
		
		//reglages de la fenêtre
		this.setTitle(bat.getNom() + " (sectionnal view [" + bat.getNbEtages() + " floors])");	//Titre de la fenêtre 
		this.setMinimumSize(new Dimension(500, 500));									//taille de la fenêtre fixe
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
}
