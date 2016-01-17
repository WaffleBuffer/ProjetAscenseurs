package Options;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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
	
	/**permet de lancer la musique, pour l'instant ne fait qu'une notification
	 * affichage du nom de la musique.
	 */
	private void lancerMusique () {
		setEstActivee(true);
		JOptionPane.showMessageDialog(null, "the music : " + nomMusique + 
				" is playing in lift n°" + getControleurInterne().getAscenseur().getNumAsc());
	}
	
	protected void arreterMusique() {
		setEstActivee(false);
		JOptionPane.showMessageDialog(null, "the music : " + nomMusique + 
				" stopped in lift n°" + getControleurInterne().getAscenseur().getNumAsc());	
	}

	/** permet d'activer OptionMusique en utilisant {@link OptionMusique#lancerMusique}
	 */
	@Override
	public void activer() {
		if (!isEstOuverte()) {//On verifie qu'il n'y ait pas d'autres fenetres d'ouvertes pour cette Option.

			setEstOuverte(true); 
			
			JFrame fenetreMusique = new JFrame("Music");
			fenetreMusique.setSize(new Dimension(300, 100));
			GridBagLayout gb = new GridBagLayout();
			fenetreMusique.setLayout(gb);
			
			GridBagConstraints constraint = new GridBagConstraints();
			constraint.fill = GridBagConstraints.HORIZONTAL;
			constraint.weightx = 0;
			constraint.weighty = 0;
			constraint.gridx = 0;
			constraint.gridy = 0;
			constraint.ipadx = 0;
			constraint.ipady = 0;
			constraint.insets = new Insets(0, 0, 0, 0);
			constraint.anchor = GridBagConstraints.CENTER;
			
			JLabel textLabel = new JLabel("music's name : ");
			
			final JTextField nomMusique = new JTextField(this.nomMusique);
			nomMusique.setPreferredSize(new Dimension(fenetreMusique.getWidth() / 2 - 10, nomMusique.getFont().getSize() + 8));
			
			JButton lancerArreter = new JButton("Play/Stop");
			lancerArreter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setNomMusique(nomMusique.getText());
					if (!isEstActivee()) {
						lancerMusique();
					}
					else {
						arreterMusique();
					}
					getControleurInterne().getAscenseur().notifyObservers();
				}
			});
			
			fenetreMusique.add(textLabel, constraint);
			
			constraint.gridx = 1;
			fenetreMusique.add(nomMusique, constraint);
			
			constraint.gridx = 0;
			constraint.gridy = 1;
			fenetreMusique.add(lancerArreter, constraint);
			
			fenetreMusique.setLocationRelativeTo(null);
			fenetreMusique.setVisible(true);				
			
			setEstOuverte(false);
		}
	}

	@Override
	public String toString() {
		return "Music " + (nomMusique != null ? nomMusique : "") + (isEstActivee() ? " activated" : "");
	}

	public boolean isEstOuverte() {
		return estFenetreOuverte;
	}

	public void setEstOuverte(boolean estOuverte) {
		this.estFenetreOuverte = estOuverte;
	}

	public String getNomMusique() {
		return nomMusique;
	}

	public void setNomMusique(String nomMusique) {
		this.nomMusique = nomMusique;
	}
	
	@Override
	public OptionMusique clone() {
		OptionMusique optionARetournee = new OptionMusique();
		optionARetournee.setEstOuverte(isEstOuverte());
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
