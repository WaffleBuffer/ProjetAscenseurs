package Boutons;

import Client.Constantes;

/**Definie un {@link BoutonExterne} pour aller vers le bas.
 * @author Thomas
 * @see BoutonExterne
 */
public class BoutonBas extends BoutonExterne{

	/** Construit un BoutonBas. {@link Bouton#libelle} = "Bas"
	 * @param etage numero de l'etage auquel se trouve ce BoutonHaut.
	 * @see Bouton
	 * @see BoutonExterne
	 */
	public BoutonBas(int etage) {
		super(etage, "Bas", Constantes.BAS);
	}
}
