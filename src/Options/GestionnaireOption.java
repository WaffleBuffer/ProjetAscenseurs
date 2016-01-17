package Options;

import java.util.ArrayList;

/**permet de gerer les {@link Option} d'un {@link Client.Ascenseur}
 * @author p14005728
 * @see Client.Ascenseur#gestionnaireOption
 */
public class GestionnaireOption {

	/** creation d'une liste de {@link Option}
	 */
	private ArrayList<Option> options = new ArrayList<Option>();

	/** permet l'acces à la liste de {@link Option}
	 * @return options liste de {@link Option}
	 */
	public ArrayList<Option> getOptions() {
		return options;
	}
	
	/** permet d'ajouter une {@link Option} a {@link #options}
	 * @param option {@link Option} a ajouter dans {@link #options}
	 */
	public void addOption (Option option) {
		options.add(option);
	}
	
	/** permet d'obtenir le numero de l'{@link Option} contenue dans {@link #options}
	 * @param i numero de l'{@link Option}
	 * @return {@link Option} de numero i
	 */
	public Option getOption (int i) {
		return options.get(i);
	}

	/**permet d'activer une {@link Option} de {@link #options} grace a son index
	 * @param index numero de l'{@link Option} a activer
	 */
	public void activerOption (int index) {
		options.get(index).activer();
	}

	@Override
	public String toString() {
		return "GestionnaireOption [options=" + options + "]";
	}

	public void supprimerOption(Option option) {
		options.remove(option);
	}
}
