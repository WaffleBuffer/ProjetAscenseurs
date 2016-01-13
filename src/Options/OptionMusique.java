package Options;


/**description de l'OptionMusique une implementation de {@link IOption}
 * @author p14005728
 */
public class OptionMusique implements IOption {
	
	/**nom de la musique 
	 */
	private String nomMusique;
	
	/**constructeur de OptionMusique
	 * @param nomMusique le nom de la musique de cette OptionMusique
	 */
	public OptionMusique (String nomMusique) {
		this.nomMusique = nomMusique;
	}
	
	/**permet de lancer la musique  
	 * affichage du nom de la musique
	 */
	private void lancerMusique () {
		System.out.println(nomMusique + " se lance");
	}

	/** permet d'activer OptionMusique en utilisant {@link OptionMusique#lancerMusique}
	 */
	@Override
	public void activer() {
		// TODO Auto-generated method stub
		this.lancerMusique();
	}
}
