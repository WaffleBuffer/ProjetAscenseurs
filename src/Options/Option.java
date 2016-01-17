package Options;

import Controleurs.ControleurInterne;

/**description des Option
 * @author p14005728
 */
public abstract class Option implements Cloneable{
	
	/**le {@link ControleurInterne} sur lequel va s'appliquer cette Option 
	 */
	private ControleurInterne controleurInt;
	
	/**designe si cette Option est activee ou non.
	 */
	private boolean estActivee = false;

	/** fonction d'activation d'une Option
	 */
	public abstract void activer();

	/**Permet de savoir si cette Option est activee
	 * @return {@link #activer()}, true si activee, false sinon.
	 */
	public boolean isEstActivee() {
		return estActivee;
	}

	/**Permet de definir si cette Option est activee
	 * @param estActivee true si activee, false sinon.
	 */
	public void setEstActivee(boolean estActivee) {
		this.estActivee = estActivee;
	}

	/**permet d'obtenir le {@link #controleurInt} de cette Option.
	 * @return le {@link #controleurInt} de cette Option.
	 */
	public ControleurInterne getControleurInterne() {
		return controleurInt;
	}

	/**Permet de definir le {@link #controleurInt} de cette Option.
	 * @param controleurInterne {@link #controleurInt} a definir pour cette Option.
	 */
	public void setControleurInterne(ControleurInterne controleurInterne) {
		this.controleurInt = controleurInterne;
	}
	
	/** Permet, dans les sous-classes, d'obtenir un clone d'une Option sans connaitre son type concret.
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Option clone();
}
