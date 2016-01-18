package Options;

import javax.swing.JOptionPane;

import AlgosTraitement.AlgoTraitementInterneVitesseDiminuee;
import AlgosTraitement.IAlgoTraitementInterne;
import Controleurs.ControleurInterne;

/**description de l'OptionVitesseDiminuee, une implementation de {@link Option}
 * @author Kurt
 */
public class OptionVistesseDiminuee extends Option {
	
	/**Permet de stocker l'ancien {@link IAlgoTraitementInterne} du {@link Option#controleurInt}.
	 */
	private IAlgoTraitementInterne ancienAlgoTraitementInterne;
	
	/**Construit une OptionVitesseDiminuee et initialise {@link Option#controleurInt}.
	 * @param controleurInt le {@link ControleurInterne} a affecter a cette OptionVitesseDiminuee.
	 */
	public OptionVistesseDiminuee (ControleurInterne controleurInt) {
		setControleurInterne(controleurInt);
	}
	
	/**Constructeur par defaut d'une OptionVitesseDiminuee.
	 */
	public OptionVistesseDiminuee () {
	}

	/** permet d'activer cette OptionVitesseDiminuee.
	 * Cela a pour effet de changer l'{@link IAlgoTraitementInterne} du {@link Option#controleurInt}
	 * de cette OptionVitesseAugmentee.
	 */
	@Override
	public void activer() {
		if (!isEstActivee()) {
			ancienAlgoTraitementInterne = getControleurInterne().getStrategieTraitement();
			getControleurInterne().setStrategieTraitement(new AlgoTraitementInterneVitesseDiminuee());
			setEstActivee(true);
			JOptionPane.showMessageDialog(null, "the lift " 
			+ getControleurInterne().getAscenseur().getNumAsc() + " is speeding up.");
		}
		else {
			if (getControleurInterne().getStrategieTraitement().getClass() == AlgoTraitementInterneVitesseDiminuee.class) {
				getControleurInterne().setStrategieTraitement(ancienAlgoTraitementInterne);
				setEstActivee(false);
				JOptionPane.showMessageDialog(null, "the lift " 
				+ getControleurInterne().getAscenseur().getNumAsc() + " is slowing down.");		
			}
			else {
				JOptionPane.showMessageDialog(null, "the lift " 
						+ getControleurInterne().getAscenseur().getNumAsc() + " already has a different handling methode.");
			}
		}
	}

	/** Permet de connaitre l'etat de cette OptionVitesseDiminuee.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "speed downgrade " + (isEstActivee() ? " activated" : "");
	}
	
	/** Permet d'obtenir une copie de cette OptionVitesseDiminuee.
	 * @see Options.Option#clone()
	 */
	@Override
	public OptionVistesseDiminuee clone() {
		OptionVistesseDiminuee optionARetournee = new OptionVistesseDiminuee();
		optionARetournee.setEstActivee(isEstActivee());
		if (getControleurInterne() != null) {
			optionARetournee.setControleurInterne(getControleurInterne());
		}
		return optionARetournee;
	}
}
