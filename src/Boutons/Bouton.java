package Boutons;

import Controleurs.IControleur;
import Requetes.Requete;

/**Description des Boutons.
 * @author Thomas
 *
 */
public class Bouton {
	
	/**Indentifiant du bouton:<br>
	 * {@link BoutonDestination} :<br>
	 * 	- etage 0    = "Rez-de-chausse"<br>
	 * 	- etage 1 = "1er etage"<br>
	 *  - etage i = i + "e etage"<br>
	 *  <br>
	 * {@link BoutonStop} = "Stop"<br>
	 */
	private String libelle;

	/**Obtient le {@link Bouton#libelle} de ce Bouton
	 * @return le {@link Bouton#libelle} de ce Bouton
	 * @see Bouton#libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**Construit un Bouton. Appele par les sous-classes.
	 * @param libelle Nom du bouton. Donner par les sous-classes
	 * @see Bouton#libelle
	 */
	protected Bouton(String libelle) {
		
		this.libelle = libelle;
	}
	
	/**Fonction d'action du Bouton. Les Boutons creent des {@link Requete} et l'ajouter au {@link IControleur}. Defini dans les sous-classes.
	 * @param controleur {@link IControleur} auquel le bouton va ajouter une {@link Requete}.
	 */
	public void appuyer (IControleur controleur) {}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Bouton [libelle=" + libelle + "]";
	}
}
