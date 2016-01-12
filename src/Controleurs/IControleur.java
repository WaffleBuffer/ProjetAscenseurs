package Controleurs;

import Requetes.Requete;

/**Interface montrant que les deux types de controleurs ({@link ControleurInterne} et {@link ControleurExterne}) ont une chose en commun:
 * le traitement des {@link Requete}. Mais cela s'arrete la.
 * @author Thomas
 *
 */
public interface IControleur {
	
	/**Permet d'ajouter une {@link Requete} a un IControleur
	 * @param requete la {@link Requete} a ajouter au IControleur
	 * @see Requete
	 * @see ControleurInterne
	 * @see ControleurExterne
	 */
	public void addRequete (Requete requete);
}
