package Options;

import java.util.ArrayList;

/**Classe statique permettant de lister toutes les {@link Option} disponnibles.
 * @author Thomas
 */
public abstract class OptionsDisponibles {
	/**La liste des {@link Option} disponnibles.
	 */
	private final static ArrayList<Option> OPTIONSDISPONNIBLES;
	
	static {
		ArrayList<Option> tmp = new ArrayList<Option> ();
		
		tmp.add(new OptionMusique());
		tmp.add(new OptionVitesseAugmentee());
		tmp.add(new OptionVistesseDiminuee());
		//Pour ajouter des options a liste des OptionsDisponnible, c'est ici.
		
		OPTIONSDISPONNIBLES = tmp;
	}

	/**Permet d'obtenir la liste des {@link Option} disponnibles.
	 * @return {@link #OPTIONSDISPONNIBLES}
	 */
	public static ArrayList<Option> getOptionsDisponnibles() {
		return OPTIONSDISPONNIBLES;
	}
}
