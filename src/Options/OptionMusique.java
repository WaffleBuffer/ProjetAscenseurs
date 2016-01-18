package Options;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controleurs.ControleurInterne;

/**description de l'OptionMusique, une implementation de {@link Option}
 * @author p14005728
 */
public class OptionMusique extends Option implements Cloneable {
	
	/**Permet de savoir si la fenetre de cette OptionMusique est ouverte.
	 */
	private boolean estFenetreOuverte;

	/**nom de la musique 
	 */
	private String nomMusique = "";
	
	private String[] listeMusiques = { "Bird", "Cat", "Dog", "Rabbit", "Pig" };;
	
	private Thread musique;
	
	/**Construit une OptionMusique et initialise {@link Option#controleurInt}.
	 * @param controleurInt le {@link ControleurInterne} a affecter a cette OptionMusique.
	 */
	public OptionMusique (ControleurInterne controleurInt) {
		setControleurInterne(controleurInt);
	}
	
	/**Constructeur par defaut d'une OptionMusique.
	 */
	public OptionMusique () {
	}
	
	/**permet de lancer la musique, pour l'instant ne fait qu'une notification.
	 */
	private void lancerMusique (String cheminFichier) {
		setEstActivee(true);
		musique = new JouerFichierWAV(cheminFichier);
        musique.start();
		JOptionPane.showMessageDialog(null, "the music : " + '"' + cheminFichier.substring(0, cheminFichier.length() - 4) + '"' +
				" is playing in lift " + getControleurInterne().getAscenseur().getNumAsc());
	}
	
	/**permet d'arreter la musique, pour l'instant ne fait qu'une notification.
	 */
	private void arreterMusique(String cheminFichier) {
		setEstActivee(false);
		musique.stop();
		JOptionPane.showMessageDialog(null, "the music : " + '"' + cheminFichier.substring(0, cheminFichier.length() - 4) + '"' + 
				" stopped in lift " + getControleurInterne().getAscenseur().getNumAsc());	
	}

	/** permet d'activer OptionMusique en utilisant {@link OptionMusique#lancerMusique}.
	 * Cela creer une fenetre qui permet d'interagire avec cette OptionMusique. 
	 */
	@Override
	public void activer() {
		if (!estFenetreOuverte) {//On verifie qu'il n'y ait pas d'autres fenetres d'ouvertes pour cette Option.

			estFenetreOuverte = true; 
			
			//Creation de la fenetre
			JFrame fenetreMusique = new JFrame("Music");
			fenetreMusique.setLayout(new FlowLayout());
			
			JLabel labelNomMusique = new JLabel("music's name : ");
			fenetreMusique.add(labelNomMusique);
			
			final JComboBox nomMusique = new JComboBox(listeMusiques);
			fenetreMusique.add(nomMusique);
			//nomMusique.setPreferredSize(new Dimension(fenetreMusique.getWidth() / 2 - 10, nomMusique.getFont().getSize() + 8));
			
			JButton lancerArreter = new JButton("Play/Stop");
			lancerArreter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setNomMusique(nomMusique.getSelectedItem().toString());
					System.out.println(nomMusique.getSelectedItem().toString());
					if (!isEstActivee()) {
						lancerMusique("Carol Malus Deinheim - Symphogear GX - Tomorrow.wav");
					}
					else {
						arreterMusique("Carol Malus Deinheim - Symphogear GX - Tomorrow.wav");
					}
					getControleurInterne().getAscenseur().notifyObservers();
				}
			});
			
			fenetreMusique.add(lancerArreter);
			fenetreMusique.setLocationRelativeTo(null);
			fenetreMusique.pack();
			fenetreMusique.setVisible(true);				
			
			estFenetreOuverte = false;
		}
	}

	/** Permet de connaitre l'etat de cette OptionMusique.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Music " + (nomMusique != null ? nomMusique : "") + (isEstActivee() ? " activated" : "");
	}

	/**Permet de definire si la fenetre d'interaction est ouverte
	 * @param estFenetreOuverte true si ouverte, false sinon.
	 * @see #estFenetreOuverte
	 */
	public void setEstFenetreOuverte(boolean estFenetreOuverte) {
		this.estFenetreOuverte = estFenetreOuverte;
	}
	
	/**Permet d'obtenir le {@link #nomMusique} de cette OptionMusique
	 * @return le {@link #nomMusique} de cette OptionMusique
	 */
	public String getNomMusique() {
		return nomMusique;
	}

	/**Permet de definir le {@link #nomMusique} de cette OptionMusique
	 * @param nomMusique {@link #nomMusique} a definir pour cette de cette OptionMusique
	 */
	public void setNomMusique(String nomMusique) {
		this.nomMusique = nomMusique;
	}
	
	/** Permet d'obtenir une copie de cette OptionMusique.
	 * @see Options.Option#clone()
	 */
	@Override
	public OptionMusique clone() {
		OptionMusique optionARetournee = new OptionMusique();
		optionARetournee.setEstFenetreOuverte(estFenetreOuverte);
		optionARetournee.setEstActivee(isEstActivee());
		if (this.nomMusique != null) {
			optionARetournee.setNomMusique(getNomMusique());
		}
		if (getControleurInterne() != null) {
			optionARetournee.setControleurInterne(getControleurInterne());
		}
		return optionARetournee;
	}
}
