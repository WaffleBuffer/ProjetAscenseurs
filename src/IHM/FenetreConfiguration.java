package IHM;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import Client.Batiment;
import Controleurs.ControleurInterne;

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
		
		JLabel labelNomBat = new JLabel("Name", SwingConstants.CENTER);
		labelNomBat.setFont(new Font("Arial",Font.BOLD,18));
		gb.setConstraints(labelNomBat, gbc); // mise en forme des objets
		add(labelNomBat);
		
		final JTextField texteNomBatiment = new JTextField();
		gb.setConstraints(texteNomBatiment, gbc); // mise en forme des objets
		add(texteNomBatiment);
		
		JLabel labelNbEtages = new JLabel("Number of floors", SwingConstants.CENTER);
		labelNbEtages.setFont(new Font("Arial",Font.BOLD,16));
		gb.setConstraints(labelNbEtages, gbc); // mise en forme des objets
		add(labelNbEtages);
		
		SpinnerModel modele = new SpinnerNumberModel(1, 1, 50, 1);
		final JSpinner nbEtages = new JSpinner(modele);
		gb.setConstraints(nbEtages, gbc); // mise en forme des objets
		add(nbEtages);
		
		JLabel labelNbAsc = new JLabel("Number of elevators",SwingConstants.CENTER);
		labelNbAsc.setFont(new Font("Arial",Font.BOLD,16));
		gb.setConstraints(labelNbAsc, gbc); // mise en forme des objets
		add(labelNbAsc);
		
		SpinnerModel modeleNbAsc = new SpinnerNumberModel(1, 1, 50, 1);
		final JSpinner nbAscenseurs = new JSpinner(modeleNbAsc);
		gb.setConstraints(nbAscenseurs, gbc); // mise en forme des objets
		add(nbAscenseurs);
		
		final JCheckBox checkBoxOption = new JCheckBox("Enable options");
		gb.setConstraints(checkBoxOption, gbc); // mise en forme des objets
		add(checkBoxOption);
		
		JButton submit = new JButton ("Add");
		gb.setConstraints(submit, gbc); // mise en forme des objets
		add(submit);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {	
				Batiment batiment = new Batiment(texteNomBatiment.getText(), (Integer) nbEtages.getValue(), (Integer) nbAscenseurs.getValue());
				if (checkBoxOption.isSelected()) {
					FenetreOption fenetreOption = new FenetreOption(batiment);
					for (ControleurInterne i : batiment.getControleursInternes()) {
						i.getAscenseur().addObserver(fenetreOption);
					}
				}
				FenetreRequetes fenetreRequetes = new FenetreRequetes(batiment);
				FenetreBatiment fenetreBatiment = new FenetreBatiment(batiment);
				batiment.addObserver(fenetreRequetes);
				batiment.addObserver(fenetreBatiment);
				for (ControleurInterne i : batiment.getControleursInternes()) {
					i.getAscenseur().addObserver(fenetreRequetes);
					i.getAscenseur().addObserver(fenetreBatiment);
				}
				FenetrePanneau fenetrePanneau = new FenetrePanneau(batiment);
				fenetrePanneau.addObserver(fenetreBatiment);
				texteNomBatiment.setText("");
				nbEtages.setValue(1);
				nbAscenseurs.setValue(1);
				AudioInputStream audioIn = null;
				try {
					audioIn = AudioSystem.getAudioInputStream(new File("coin.wav"));
				} catch (UnsupportedAudioFileException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // Get a sound clip resource.
                Clip clip = null;
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                // Open audio clip and load samples from the audio input stream.
                try {
					clip.open(audioIn);
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		
		this.setTitle("Add a building");					//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);			//le programme s'arrete quand la fenetre se ferme
		this.setResizable(false);								//la fenetre de configuration n'a pas besoin d'être redimensionner
		this.setSize(new Dimension(250, 200));					//taille de la fenêtre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int width  = (int)dimension.getWidth();
		this.setLocation(width/2 - this.getWidth()/2, 0);
	}
}
