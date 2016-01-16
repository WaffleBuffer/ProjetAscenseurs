package Requetes;

import Boutons.Bouton;
import Boutons.BoutonExterne;
import Client.Constantes;

/**Description des requetes externes.<br>
 * une RequeteExterne est cree par un {@link Boutons.BoutonExterne}.<br>
 * le {@link Requete#libelle} ne doit valoir que {@link Constantes#HAUT} ou {@link Constantes#BAS}
 *  sinon la requete sera ignoree par le {@link Controleurs.ControleurInterne}.
 * @author Thomas
 * @see Requete
 * @see Client.Constantes
 */
public class RequeteExterne extends Requete {
	
	/**Construit une RequeteExterne.
	 * @param etage le numero de l'{@link BoutonExterne#etage}} auquel se trouve le {@link BoutonExterne} lorsque l'on appuie dessu.
	 * @param direction La direction que l'on souhaite prendre. Le {@link Requete#libelle} 
	 * ne peut que etre {@link Constantes#HAUT} ou {@link Constantes#BAS} sinon elle sera ignore par le {@link Controleurs.ControleurInterne}.
	 * @see BoutonExterne#appuyer(Controleurs.Controleur)
	 * @see Client.Constantes
	 */
	public RequeteExterne (int etage, int direction){
		super (etage, direction);
	}
}
