package Boutons;

import Controleurs.IControleur;
import Requetes.RequeteInterne;

/**{@link BoutonInterne} representant les etages auquels peut acceder l'{@link Client.Ascenseur} correspondant.
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
	 * Indentifiant du {@link Bouton}:<br>
	 * 	- etage 0 = "Rez-de-chausse"<br>
	 * 	- etage 1 = "1er etage"<br>
	 *  - etage i = i + "e etage"<br>
	 * @param etage Etage ou se trouve ce BoutonDirection
	 */
	public BoutonDestination(String libelle, int etage) {
		super(libelle);
		this.etage = etage;
		// TODO Auto-generated constructor stub
	}
	
	/** Definition de la fonction d'action. Creer une {@link Requetes.RequeteInterne} pour un etage et l'ajoute
	 * au {@link Controleurs.IControleur}. Le {@link Controleurs.IControleur} devrait etre un {@link Controleurs.ControleurInterne} 
	 * pour un fonctionnement normal. L'affectation manuelle 
	 * a un {@link Controleurs.ControleurInterne} resultera en une interpretation de la {@link Requetes.Requete} comme venant d'un {@link BoutonExterne}.
	 * @param controleur {@link Controleurs.IControleur} auquel le bouton va ajouter une {@link Requetes.Requete}.
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
		return "BoutonDestination [libelle=" + this.getLibelle() + "; etage cible=" + etage + "]";
	}
}
