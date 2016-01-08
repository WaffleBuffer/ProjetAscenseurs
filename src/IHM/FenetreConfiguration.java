package IHM;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import Client.Ascenseur;
import Client.Batiment;

public class FenetreConfiguration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FenetreConfiguration(){
		
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(gb);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.gridx = 1;
		
		JLabel labelNomBat = new JLabel("Nom du bâtiment :", SwingConstants.CENTER);
		labelNomBat.setFont(new Font("Arial",Font.BOLD,20));
		gb.setConstraints(labelNomBat, gbc); // mise en forme des objets
		add(labelNomBat);
		
		final JTextField textNomBat = new JTextField();
		gb.setConstraints(textNomBat, gbc); // mise en forme des objets
		add(textNomBat);
		
		JLabel labelNbEtages = new JLabel("Nombre d'étages :", SwingConstants.CENTER);
		labelNbEtages.setFont(new Font("Arial",Font.BOLD,16));
		gb.setConstraints(labelNbEtages, gbc); // mise en forme des objets
		add(labelNbEtages);
		
		SpinnerModel modele = new SpinnerNumberModel(0, 0, 100, 1);
		final JSpinner nbEtages = new JSpinner(modele);
		gb.setConstraints(nbEtages, gbc); // mise en forme des objets
		add(nbEtages);
		
		JLabel labelNbAsc = new JLabel("Nombre d'ascenseurs :",SwingConstants.CENTER);
		labelNbAsc.setFont(new Font("Arial",Font.BOLD,16));
		gb.setConstraints(labelNbAsc, gbc); // mise en forme des objets
		add(labelNbAsc);
		
		SpinnerModel modeleNbAsc = new SpinnerNumberModel(0, 0, 100, 1);
		final JSpinner nbAsc = new JSpinner(modeleNbAsc);
		gb.setConstraints(nbAsc, gbc); // mise en forme des objets
		add(nbAsc);
		
		JButton submit = new JButton ("Submit");
		gb.setConstraints(submit, gbc); // mise en forme des objets
		add(submit);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Batiment bat = new Batiment(textNomBat.getText(), (Integer) nbEtages.getValue(), (Integer) nbAsc.getValue());
				Ascenseur asc = new Ascenseur(bat.getNbEtages(), 1);
				FenetreBatiment mainWindow = new FenetreBatiment(bat, asc);
			}});
		
		this.setTitle("Projet Java Ascenseur");					//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);			//le programme s'arrete quand la fenetre se ferme
		this.setResizable(false);								//la fenetre de configuration n'a pas besoin d'être redimensionner
		this.setSize(new Dimension(300, 300));					//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
	}
}
