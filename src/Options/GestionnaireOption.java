package Options;

import java.util.ArrayList;

/**permet de gerer les {@link IOption} d'un {@link Client.Ascenseur}
 * @author p14005728
 * @see Client.Ascenseur#gestionnaireOption
 */
public class GestionnaireOption {

	/** creation d'une liste de {@link IOption}
	 */
	private ArrayList<IOption> options = new ArrayList<IOption>();

	/** permet l'acces à la liste de {@link IOption}
	 * @return options liste de {@link IOption}
	 */
	public ArrayList<IOption> getOptions() {
		return options;
	}
	
	/** permet d'ajouter une {@link IOption} a {@link #options}
	 * @param option {@link IOption} a ajouter dans {@link #options}
	 */
	public void addOption (IOption option) {
		options.add(option);
	}
	
	/** permet d'obtenir le numero de l'{@link IOption} contenue dans {@link #options}
	 * @param i numero de l'{@link IOption}
	 * @return {@link IOption} de numero i
	 */
	public IOption getOption (int i) {
		return options.get(i);
	}

	/**permet d'activer une {@link IOption} de {@link #options} grace a son numero
	 * @param numOption numero de l'{@link IOption} a activer
	 */
	public void activerOption (int numOption) {
		//traitement par defaut
		options.get(numOption).activer();
	}
}
