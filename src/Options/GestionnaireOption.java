package Options;

import java.util.ArrayList;

/**permet de gerer les {@link Option} d'un {@link Client.Ascenseur}
 * @author p14005728
 * @see Client.Ascenseur#gestionnaireOption
 */
public class GestionnaireOption {

	/** liste des {@link Option} de ce GestionnaireOption.
	 */
	private ArrayList<Option> options = new ArrayList<Option>();

	/** permet l'acces a la liste d'{@link Option} de ce GestionnaireOption
	 * @return {@link #options} de ce GestionnaireOption
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
	
	/** permet d'obtenir l'{@link Option} contenue dans {@link #options} a l'index index
	 * @param index index de l'{@link Option}
	 * @return {@link Option} d'index index
	 */
	public Option getOption (int index) {
		return options.get(index);
	}

	/**permet d'activer une {@link Option} de {@link #options} grace a son index
	 * @param index numero de l'{@link Option} a activer
	 */
	public void activerOption (int index) {
		options.get(index).activer();
	}

	/**Permet de supprimer une {@link Option} de {@link #options}
	 * @param option l'{@link Option} a supprimer de {@link #options}
	 */
	public void supprimerOption(Option option) {
		options.remove(option);
	}
}
