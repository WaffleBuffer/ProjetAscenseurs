package Controleurs;

import java.util.ArrayList;

import Requetes.Requete;

/**Description montrant que les sous-classes ont une chose en commun:
 * l'ajout de {@link Requete}, mais cela s'arrete la. Cela permet aussi la generecite de {@link Boutons.Bouton#appuyer(Controleur)}.
 * @author Thomas
 * @see ControleurInterne
 * @see ControleurExterne
 */
public abstract class Controleur {
	
	/**Liste des {@link Requete} affectees a ce Controleur.
	 * 
	 */
	private ArrayList<Requete> listeRequetes = new ArrayList<Requete>();
	
	/**Permet d'ajouter une {@link Requete} a ce Controleur
	 * @param requete la {@link Requete} a ajouter a ce Controleur
	 * @see Requete
	 * @see ControleurInterne
	 * @see ControleurExterne
	 */
	public void ajouterRequete (Requete requete) {
		listeRequetes.add(requete);
	}

	/**Retourne {@link #listeRequetes} de ce Controleur.
	 * @return {@link #listeRequetes} de ce Controleur.
	 */
	public ArrayList<Requete> getRequetes() {
		return listeRequetes;
	}
}
