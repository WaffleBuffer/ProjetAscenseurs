package Boutons;

import Client.Constantes;

/**Definit un {@link BoutonExterne} pour aller vers le haut.
 * @author Thomas
 * @see BoutonExterne
 */
public class BoutonHaut extends BoutonExterne{

	/** Construit un BoutonHaut. {@link Bouton#libelle} = "Haut"
	 * @param etage numero de l'etage auquel se trouve ce BoutonHaut.
	 * @see Bouton#libelle
	 * @see BoutonExterne
	 */
	public BoutonHaut(int etage) {
		super(etage, "Haut", Constantes.HAUT);
	}
}
