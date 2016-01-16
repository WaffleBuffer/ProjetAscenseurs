package Boutons;

import Controleurs.Controleur;
import Requetes.RequeteInterne;

/**{@link BoutonInterne} representant les etages auquels peut acceder l'{@link Client.Ascenseur} qui les contient.
 * @author Thomas
 * @see BoutonInterne
 */
public class BoutonDestination extends BoutonInterne {

	/**Represente l'etage auquel ce BoutonDestination accede.
	 * 
	 */
	private int etage;
	
	/**Construit un BoutonDestination.
	 * @param libelle 	
	 * {@link Bouton#libelle} de ce BoutonDestination:<br>
	 * 	- etage 0 = "Rez-de-chausse"<br>
	 * 	- etage 1 = "1er etage"<br>
	 *  - etage i = i + "e etage"<br>
	 * @param etage {@link #etage} auquel ce BoutonDestination accede
	 */
	public BoutonDestination(String libelle, int etage) {
		super(libelle);
		this.etage = etage;
	}
	
	/** Definition de la fonction d'action. Creer une {@link Requetes.RequeteInterne} pour l'{@link #etage} et l'ajoute
	 * au {@link Controleurs.Controleur}. Le {@link Controleurs.Controleur} devrait etre un {@link Controleurs.ControleurInterne} 
	 * pour un fonctionnement normal. L'affectation manuelle 
	 * a un {@link Controleurs.ControleurExterne} resultera en une interpretation de la {@link Requetes.Requete} comme venant d'un {@link BoutonExterne}.
	 * @param controleur {@link Controleurs.Controleur} auquel le bouton va ajouter une {@link Requetes.RequeteInterne}.
	 */
	@Override
	public void appuyer (Controleur controleur) {
		controleur.addRequete(new RequeteInterne (etage));
	}

	/** Renvoie l'etat de ce BoutonDestination
	 * @see Boutons.Bouton#toString()
	 */
	@Override
	public String toString() {
		return "BoutonDestination [libelle=" + this.getLibelle() + "; etage cible=" + etage + "]";
	}
}
