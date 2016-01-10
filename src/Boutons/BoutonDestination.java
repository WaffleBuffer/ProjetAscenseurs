package Boutons;

import Controleurs.ControleurInterne;
import Controleurs.IControleur;
import Requetes.Requete;
import Requetes.RequeteInterne;

/**{@link BoutonInterne} representant les etages auquels peut acceder l'{@link Ascenseur} correspondant.
 * @author Thomas
 * @see BoutonInterne
 */
public class BoutonDestination extends BoutonInterne {

	/**Represente l'etage que ce BoutonDestination accede.
	 * 
	 */
	private int etage;
	
	/**Construit un BoutonDestination.
	 * @param libelle 	
	 * Indentifiant du bouton:<br>
	 * 	- etage 0 = "Rez-de-chausse"<br>
	 * 	- etage 1 = "1er etage"<br>
	 *  - etage i = i + "e etage"<br>
	 * @param etage Etage ou se trouve le BoutonDirection
	 */
	public BoutonDestination(String libelle, int etage) {
		super(libelle);
		this.etage = etage;
		// TODO Auto-generated constructor stub
	}
	
	/** Definition de la fonction d'action. Creer une {@link RequeteInterne} pour un etage et l'ajoute
	 * au {@link IControleur}. Le {@link IControleur} devrait etre un {@link ControleurInterne} pour un fonctionnement normal. L'affectation manuelle 
	 * a un {@link ControleurInterne} resultera en une interpretation de la {@link Requete} comme venant d'un {@link BoutonExterne}.
	 * @param controleur {@link IControleur} auquel le bouton va ajouter une {@link Requete}.
	 */
	@Override
	public void appuyer (IControleur controleur) {
		controleur.addRequete(new RequeteInterne (etage));
	}

	/* (non-Javadoc)
	 * @see Boutons.Bouton#toString()
	 */
	@Override
	public String toString() {
		return "BoutonDestination [libelle=" + this.getLibelle() + "; etage ciblé=" + etage + "]";
	}
}
