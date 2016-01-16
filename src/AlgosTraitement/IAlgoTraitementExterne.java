package AlgosTraitement;

import Client.Ascenseur;
import Controleurs.ControleurExterne;

/**Description des strategies de traitement externe a appliquer sur un {@link ControleurExterne}.
 * @author Thomas
 */
public interface IAlgoTraitementExterne {
	
	/**Fonction permettant de traiter les {@link Controleur#requetes} d'un {@link ControleurExterne}
	 * @param controleurInt le {@link ControleurExterne} sur lequel appliquer l'algorithme.
	 */
	public void traiterRequetes(ControleurExterne controleurExt);
}