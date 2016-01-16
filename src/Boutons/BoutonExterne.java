package Boutons;

import Controleurs.IControleur;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Definit les BoutonExterne qui sont a chaque etage d'un {@link Client.Batiment}
 * @author Thomas
 */
public abstract class BoutonExterne extends Bouton {
	
	/**Etage auquel se trouve ce BoutonExterne
	 * 
	 */
	private int etage;
	
	/**Direction de ce BoutonExterne : <br>
	 * {@link Client.Constantes#HAUT}
	 * {@link Client.Constantes#BAS}
	 * @see Client.Constantes
	 * @see BoutonHaut
	 * @see BoutonBas
	 */
	private int direction;

	/**Constructeur de BoutonExterne. Appelle par les sous-classes
	 * @param etage etage auquel se trouve le BoutonExterne
	 * @param libelle Indentifiant du BoutonExterne. Donne par les sous-classes:<br>
	 * - {@link BoutonHaut} : "Haut"<br>
	 * - {@link BoutonBas}  : "Bas"
	 * @param direction {@link #direction} de ce BoutonExterne
	 * @see Bouton#libelle
	 */
	protected BoutonExterne(int etage, String libelle, int direction) {
		super(libelle);
		this.etage = etage;
		this.direction = direction;
	}
	
	/** Definition de la fonction d'action. Creer une {@link RequeteExterne} pour l'{@link #etage} et
	 *  la {@link #direction} (donnee par les sous-classes) et l'ajoute
	 * au {@link IControleur}. Le {@link IControleur} devrait etre un {@link Controleurs.ControleurExterne}
	 *  pour un fonctionnement normal (bien que l'affecter
	 * manuellement a un {@link Controleurs.ControleurInterne} fonctionne aussi).
	 * @param controleur {@link IControleur} auquel le bouton va ajouter une {@link RequeteExterne}.
	 */
	@Override
	public void appuyer (IControleur controleur) {
		RequeteExterne requete = new RequeteExterne(etage, this.direction);
		controleur.addRequete(requete);
	}

	/**Permet de connaitre l'{@link #etage} ou se trouve ce BoutonExterne 
	 * @return l'etage ou se trouve le BoutonExterne
	 */
	public int getEtage() {
		return etage;
	}
	
	/** Renvoie l'etat de ce BoutonExterne
	 * @see Boutons.Bouton#toString()
	 */
	@Override
	public String toString() {
		return "BoutonExterne [libelle=" + this.getLibelle() + "; etage=" + this.getEtage() + "]";
	}
}
