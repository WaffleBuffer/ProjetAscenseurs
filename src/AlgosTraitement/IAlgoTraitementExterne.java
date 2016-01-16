package AlgosTraitement;

import Controleurs.ControleurExterne;

/**Description des strategies de traitement externe a appliquer sur un {@link ControleurExterne}.
 * @author Thomas
 */
public interface IAlgoTraitementExterne {
	
	/**Fonction permettant de traiter les {@link Controleurs.Controleur#requetes} d'un {@link ControleurExterne}
	 * @param controleurExt le {@link ControleurExterne} sur lequel appliquer l'algorithme.
	 */
	public void traiterRequetes(ControleurExterne controleurExt);
}