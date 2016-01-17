package Options;

/**description des IOption
 * @author p14005728
 */
public abstract class Option {
	
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
}
