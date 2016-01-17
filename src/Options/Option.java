package Options;

import Controleurs.ControleurInterne;

/**description des Option
 * @author p14005728
 */
public abstract class Option {
	
	private ControleurInterne controleurInt;
	
	private boolean estActivee = false;

	/** fonction d'activation d'une IOption
	 */
	public abstract void activer();

	public boolean isEstActivee() {
		return estActivee;
	}

	public void setEstActivee(boolean estActivee) {
		this.estActivee = estActivee;
	}

	public ControleurInterne getControleurInterne() {
		return controleurInt;
	}

	public void setControleurInterne(ControleurInterne controleurInterne) {
		this.controleurInt = controleurInterne;
	}
}
