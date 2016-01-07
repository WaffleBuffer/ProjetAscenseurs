package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Boutons.BoutonInterne;
import Client.Ascenseur;
import Client.Batiment;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public MainWindow (Batiment bat, final Ascenseur asc) {
		
		//layout de la fenêtre
		BorderLayout mainLayout = new BorderLayout();
		this.setLayout(mainLayout);
		
		//répartition des panels
		JPanel batPanel = new JPanel();
		JPanel ascPanel = new JPanel();
		this.add(batPanel, BorderLayout.WEST);
		this.add(ascPanel, BorderLayout.EAST);
		
		//layout partie batiment
		LayoutManager batLayout = new BoxLayout(batPanel, BoxLayout.Y_AXIS);
		batPanel.setLayout(batLayout);
		batPanel.add(new JLabel("Bâtiment"));
		
		//layout partie ascenseur
		LayoutManager ascLayout = new BoxLayout(ascPanel, BoxLayout.Y_AXIS);
		ascPanel.setLayout(ascLayout);
		ascPanel.add(new JLabel("Ascenseur"));
		ascPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		//partie batiment
		JLabel nomBat = new JLabel(bat.getNom());
		JLabel nbEtagesBat = new JLabel(Integer.toString(bat.getNbEtages()));
		batPanel.add(nomBat);
		batPanel.add(nbEtagesBat);
		batPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		//partie ascenseur
		for (BoutonInterne i : asc.getListeBoutons()){
			ascPanel.add(new JLabel(i.getLibelle()));
		}
		
		final JProgressBar barre_progression = new JProgressBar(JProgressBar.VERTICAL, 0, bat.getNbEtages());
		barre_progression.setValue(asc.getEtage());
		barre_progression.setForeground(Color.ORANGE);
		barre_progression.setStringPainted(true);
		this.add(barre_progression, BorderLayout.CENTER);
		
		//boutons
		JButton un = new JButton("1er");
		JButton deux = new JButton("2e");
		JButton trois = new JButton("3e");
		JButton quatre = new JButton("4e");
		
		JPanel boutonPanel = new JPanel();
		BoxLayout boutonLayout = new BoxLayout(boutonPanel, BoxLayout.Y_AXIS);
		this.add(boutonPanel, BorderLayout.SOUTH);
		boutonPanel.add(un, BorderLayout.SOUTH);
		boutonPanel.add(deux, BorderLayout.SOUTH);
		boutonPanel.add(trois, BorderLayout.SOUTH);
		boutonPanel.add(quatre, BorderLayout.SOUTH);
		
		un.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				barre_progression.setValue(1);
			}});
		
		deux.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				barre_progression.setValue(2);
			}});
		
		trois.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				barre_progression.setValue(3);
			}});
		
		quatre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				barre_progression.setValue(4);
			}});
		
		 /*barre_progression.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				barre_progression.setValue(1);
			}});
		*/
		barre_progression.addChangeListener(new ChangeListener() {
	
			@Override
			public void stateChanged(ChangeEvent arg0) {
				if (barre_progression.isIndeterminate())
					barre_progression.setIndeterminate(false);
				else if (!barre_progression.isIndeterminate())
					barre_progression.setIndeterminate(true);	
			}
		    });
		
		//reglages de la fenêtre
		this.setTitle("Projet Java Ascenseur");					//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);			//le programme s'arrete quand la fenetre se ferme
		this.setSize(300, bat.getNbEtages()*30);				//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
		
	}
}
