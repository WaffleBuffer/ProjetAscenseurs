package Options;

import javax.swing.JOptionPane;
import AlgosTraitement.AlgoTraitementInterneStandard;
import AlgosTraitement.AlgoTraitementInterneVitesseAugmentee;
import AlgosTraitement.IAlgoTraitementInterne;
import Controleurs.ControleurInterne;

/**description de l'OptionVitesseAugmentee, une implementation de {@link Option}
 * @author Thomas
 */
public class OptionVitesseAugmentee extends Option {
	
	/**Construit une OptionVitesseAugmentee et initialise {@link Option#controleurInt}.
	 * @param controleurInt le {@link ControleurInterne} a affecter a cette OptionVitesseAugmentee.
	 */
	public OptionVitesseAugmentee (ControleurInterne controleurInt) {
		setControleurInterne(controleurInt);
	}
	
	/**Constructeur par defaut d'une OptionVitesseAugmentee.
	 */
	public OptionVitesseAugmentee () {
	}

	/** permet d'activer cette OptionVitesseAugmentee.
	 * Cela a pour effet de changer l'{@link IAlgoTraitementInterne} du {@link Option#controleurInt}
	 * de cette OptionVitesseAugmentee.
	 */
	@Override
	public void activer() {
		if (!isEstActivee()) {
			getControleurInterne().setStrategieTraitement(new AlgoTraitementInterneVitesseAugmentee());
			setEstActivee(true);
			JOptionPane.showMessageDialog(null, "the lift " 
			+ getControleurInterne().getAscenseur().getNumAsc() + " is speeding up.");
			getControleurInterne().getAscenseur().notifyObservers();
		}
		else {
			getControleurInterne().setStrategieTraitement(new AlgoTraitementInterneStandard());
			setEstActivee(false);
			JOptionPane.showMessageDialog(null, "the lift " 
			+ getControleurInterne().getAscenseur().getNumAsc() + " is slowing down.");		
			getControleurInterne().getAscenseur().notifyObservers();
		}
	}

	/** Permet de connaitre l'etat de cette OptionVitesseAugmentee.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "speed upgrade " + (isEstActivee() ? " activated" : "");
	}
	
	/** Permet d'obtenir une copie de cette OptionVitesseAugmentee.
	 * @see Options.Option#clone()
	 */
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
