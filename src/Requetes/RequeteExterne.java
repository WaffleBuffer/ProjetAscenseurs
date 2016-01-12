package Requetes;

/**Description des requetes externes. Sous-classe de {@link Requete}.<br>
 * Ces requetes correspondent aux {@link Boutons.BoutonExterne}.<br>
 * le {@link Requete#libelle} ne doit valoir que "Haut" ou "Bas" sinon la requete sera ignoree par le {@link Controleurs.IControleur}.
 * @author Thomas
 */
public class RequeteExterne extends Requete {
	
	/**Construit une RequeteExterne.
	 * @param etage le numero de l'etage auquel on se trouve lorsque l'on appuie.
	 * @param direction La direction que l'on souhaite prendre. Le {@link Requete#getLibelle()} 
	 * ne peut que etre "Haut" ou "Bas" sinon ignore par le {@link Controleurs.IControleur}.
	 */
	public RequeteExterne (int etage, int direction){
		super (etage, direction);
	}
}
