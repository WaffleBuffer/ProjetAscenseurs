package Boutons;

import Controleurs.IControleur;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Definie les BoutonExterne qui sont a chaque etage des {@link Batiment}
 * @author Thomas
 *
 */
public class BoutonExterne extends Bouton {
	
	/**Etage auquel se trouve le BoutonExterne
	 * 
	 */
	private int etage;

	/**Constructeur de BoutonExterne. Appele par les sous-classes
	 * @param etage etage auquel se trouve le BoutonExterne
	 * @param libelle Indentifiant du BoutonExterne. Donner par les sous-classes
	 * @see Bouton#libelle
	 */
	protected BoutonExterne(int etage, String libelle) {
		super(libelle);
		this.etage = etage;
		// TODO Auto-generated constructor stub
	}

	
	/** Definition de la fonction d'action. Creer une {@link RequeteExterne} pour un etage et une direction donnee par les sous-classes et l'ajoute
	 * au {@link IControleur}. Le {@link IControleur} devrait etre un {@link Controleurs.ControleurExterne} pour un fonctionnement normal (bien que l'affecter
	 * manuellement a un {@link Controleurs.ControleurInterne} fonctionne aussi).
	 * @param controleur {@link IControleur} auquel le bouton va ajouter une {@link Requete}.
	 */
	@Override
	public void appuyer (IControleur controleur) {
		RequeteExterne requete = new RequeteExterne(etage, this.getLibelle());
		controleur.addRequete(requete);
	}

	/**Permet de connaitre l'etage ou se trouve le BoutonExterne 
	 * @return l'etage ou se trouve le BoutonExterne
	 */
	public int getEtage() {
		return etage;
	}
	
	/* (non-Javadoc)
	 * @see Boutons.Bouton#toString()
	 */
	@Override
	public String toString() {
		return "BoutonExterne [libelle=" + this.getLibelle() + "; etage=" + this.getEtage() + "]";
	}
}
