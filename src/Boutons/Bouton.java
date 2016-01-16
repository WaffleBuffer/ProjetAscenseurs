package Boutons;

import Controleurs.Controleur;
import Requetes.Requete;

/**Description des Boutons en generale.
 * @author Thomas
 */
public abstract class Bouton {
	
	/**Indentifiant du bouton:<br>
	 * {@link BoutonDestination} :<br>
	 * 	- etage 0 = "Rez-de-chausse"<br>
	 * 	- etage 1 = "1er etage"<br>
	 *  - etage i = i + "e etage"<br>
	 *  <br>
	 * {@link BoutonStop} = "Stop"
	 */
	private String libelle;

	/**Obtient le {@link #libelle} de ce Bouton
	 * @return le {@link #libelle} de ce Bouton
	 * @see #libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**Construit un Bouton. Appele par les sous-classes.
	 * @param libelle Nom du bouton. Donner par les sous-classes
	 * @see #libelle
	 */
	protected Bouton(String libelle) {
		
		this.libelle = libelle;
	}
	
	/**Fonction d'action du Bouton. Ce Bouton cree une {@link Requete} l'ajoute au {@link Controleur}. Defini dans les sous-classes.
	 * @param controleur {@link Controleur} auquel le bouton va ajouter une {@link Requete}.
	 */
	public abstract void appuyer (Controleur controleur);
}
