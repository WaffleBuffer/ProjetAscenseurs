package Options;

import java.util.ArrayList;

/** gestion des options de {@link IOption}
 * @author p14005728
 */
public class GestionnaireOption {

	/** création d'une liste de {@link IOption}
	 */
	private ArrayList<IOption> options = new ArrayList<IOption>();

	/** permet l'accès à la liste de {@link IOption}
	 * @return options liste de {@link IOption}
	 */
	public ArrayList<IOption> getOptions() {
		return options;
	}
	
	/** permet d'ajouter une option à la liste de {@link IOption}
	 * @param option
	 */
	public void addOption (IOption option) {
		options.add(option);
	}
	
	/** permet d'obtenir le numero de l'option
	 * @param i numero de l'option
	 * @return
	 */
	public IOption getOption (int i) {
		return options.get(i);
	}

	/**permet d'activer une option grace a son numero
	 * @param numOption numero de l'option
	 */
	public void activerOption (int numOption) {
		//traitement par defaut
		options.get(numOption).activer();
	}
}
