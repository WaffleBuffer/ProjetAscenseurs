package Boutons;

/**Definit les BoutonsInterne a l'interieur d'un {@link Client.Ascenseur}.
 * @author Thomas
 *
 */
public abstract class BoutonInterne extends Bouton {

	/**Construit un BoutonInterne. Appele par les sous-classes
	 * @param libelle Identifiant de ce BoutonInterne
	 * @see Bouton#libelle
	 * @see BoutonDestination
	 * @see BoutonStop
	 */
	protected BoutonInterne(String libelle) {
		super(libelle);
	}
}
