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

import AlgosTraitement.AlgoTraitementInterneStandard;
import AlgosTraitement.AlgoTraitementInterneVitesseAugmentee;
import Client.Ascenseur;
import Controleurs.ControleurInterne;

/**description de l'OptionMusique une implementation de {@link Option}
 * @author p14005728
 */
public class OptionVitesseAugmentee extends Option {
	
	/**constructeur de OptionMusique
	 * @param nomMusique le nom de la musique de cette OptionMusique
	 */
	public OptionVitesseAugmentee (ControleurInterne controleurInt) {
		setControleurInterne(controleurInt);
	}
	
	public OptionVitesseAugmentee () {
	}

	/** permet d'activer OptionMusique en utilisant {@link OptionVitesseAugmentee#lancerMusique}
	 */
	@Override
	public void activer() {
		if (!isEstActivee()) {
			getControleurInterne().setStrategieTraitement(new AlgoTraitementInterneVitesseAugmentee());
			setEstActivee(true);
			JOptionPane.showMessageDialog(null, "the lift n°" 
			+ getControleurInterne().getAscenseur().getNumAsc() + " is speeding up.");
			getControleurInterne().getAscenseur().notifyObservers();
		}
		else {
			getControleurInterne().setStrategieTraitement(new AlgoTraitementInterneStandard());
			setEstActivee(false);
			JOptionPane.showMessageDialog(null, "the lift n°" 
			+ getControleurInterne().getAscenseur().getNumAsc() + " is slowing down.");		
			getControleurInterne().getAscenseur().notifyObservers();
		}
	}

	@Override
	public String toString() {
		return "speed upgrade " + (isEstActivee() ? " activated" : "");
	}
	
	@Override
	public OptionVitesseAugmentee clone() {
		OptionVitesseAugmentee optionARetournee = new OptionVitesseAugmentee();
		optionARetournee.setEstActivee(isEstActivee());
		if (getControleurInterne() != null) {
			optionARetournee.setControleurInterne(getControleurInterne());
		}
		return optionARetournee;
	}
}
