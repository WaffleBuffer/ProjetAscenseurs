package Boutons;

import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import Controleurs.IControleur;
import Requetes.Requete;
import Requetes.RequeteInterne;

/**{@link BoutonInterne} permettant de stopper l'{@link Client.Ascenseur} dans lequel il se trouve.
 * @author Thomas
 */
public class BoutonStop extends BoutonInterne{

	/**Construit un BoutonStop. {@link Bouton#libelle} = "Stop".
	 * @see BoutonExterne
	 */
	public BoutonStop() {
		super("Stop");
		// TODO Auto-generated constructor stub
	}

	/** Definition de la fonction d'action. Creer une {@link RequeteInterne} et l'ajoute
	 * au {@link IControleur}. Le {@link IControleur} devrait etre un {@link ControleurInterne} pour un fonctionnement normal. L'affectation manuelle 
	 * a un {@link ControleurExterne} resultera en une interpretation de la {@link Requete} comme venant d'un {@link BoutonExterne} de l'etage 0.
	 * @param controleur {@link IControleur} auquel le bouton va ajouter une {@link Requete}.
	 * @see RequeteInterne#RequeteInterne()
	 */
	@Override
	public void appuyer (IControleur controleur) {
		controleur.addRequete(new RequeteInterne());
	}
	
	/** Renvoie l'etat de ce BoutonStop
	 * @see Boutons.Bouton#toString()
	 */
	@Override
	public String toString() {
		return "BoutonStop [" + super.toString() + "]";
	}
}
