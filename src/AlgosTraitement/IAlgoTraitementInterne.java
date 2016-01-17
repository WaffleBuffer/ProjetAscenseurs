package AlgosTraitement;

import Client.Ascenseur;
import Controleurs.ControleurInterne;

/**Description des strategies de traitement interne a appliquer sur un {@link ControleurInterne}.
 * @author Thomas
 */
public interface IAlgoTraitementInterne {
	
	/**Fonction permettant de traiter les {@link Controleurs.Controleur#requetes} de l'{@link Ascenseur} gere par ontroleurInt pour une iteration
	 * @param controleurInterne le {@link ControleurInterne} sur lequel appliquer l'algorithme.
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 */
	public String traiterRequetes(ControleurInterne controleurInterne);
}