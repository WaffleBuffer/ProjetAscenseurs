package Controleurs;

import AlgosTraitement.IAlgoTraitementInterne;
import Client.Ascenseur;
import Requetes.Requete;
import Requetes.RequeteInterne;

/**Designe le traitement des {@link Requete} d'un {@link Ascenseur}. Il existe un ControleurInterne par {@link Ascenseur}.
 * @author Thomas
 */
public class ControleurInterne extends Controleur{
	
	/**{@link Ascenseur} gere par ce ControleurInterne.
	 * 
	 */
	private Ascenseur ascenseur;
	
	/**la strategie a appliquer pour traiter les {@link RequeteInterne} de ce ControleurInterne.
	 */
	private IAlgoTraitementInterne strategieTraitement;
	
	/**Construit un ControleurInterne.
	 * @param ascenseur {@link Ascenseur} lie a ce ControleurInterne.
	 * @param strategie l'{@link IAlgoTraitementInterne} a appliquer sur ce ControleurInterne pour le traitement des {@link Requete}.
	 */
	public ControleurInterne (Ascenseur ascenseur, IAlgoTraitementInterne strategie) {
		this.ascenseur = ascenseur;
		this.strategieTraitement = strategie;
	}
	
	/**Renvoi l'{@link Ascenseur} gere par ce ControleurInterne
	 * @return l'{@link Ascenseur} gere par ce ControleurInterne
	 */
	public Ascenseur getAscenseur() {
		return this.ascenseur;
	}
	
	/**Fonction permettant de traiter les {@link Controleur#requetes} pour l'{@link Ascenseur} gere par ce ControleurInterne pour une iteration
	 * grace a la {@link #strategieTraitement} de ce ControleurInterne.
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 */
	public String traiterRequetes(){
		return strategieTraitement.traiterRequetes(this);
	}
	
	/**Ajout d'une {@link Requete} prioritaire sur les requetes en cours de traitement
	 * @param requete la {@link Requete} prioritaire a ajouter.
	 */
	public void ajouterRequetePrioritaire (Requete requete) {
		this.getRequetes().add(0, requete);
	}
	
	/**Ajout d'une {@link RequeteInterne} specifique a {@link Controleur#requetes}
	 * @param etage l'etage de la {@link RequeteInterne}.
	 * @see RequeteInterne
	 */
	public void ajouterRequete (int etage) {
		getRequetes().add(new RequeteInterne(etage));
	}
	
	/**Renvoi le numero d'etage de la prochaine {@link Requete} a traiter. Si {@link #requetes} est vide alors renvoie -1.
	 * @return le numero d'etage de la prochaine {@link Requete} a traiter. Si {@link ControleurInterne#requetes} est vide alors renvoie -1.
	 */
	public int prochaineDest () {
		if (getRequetes().size() > 0) {
			return getRequetes().get(0).getEtageDemande();
		}
		//S'il n'y a pas de requete retourne -1
		else
			return -1;
	}
	
	/**Renvoi le nombre de {@link Requete} dans {@link Controleur#requetes}.
	 * @return le nombre de {@link Requete} dans {@link Controleur#requetes}.
	 */
	public int getNumberOfRequete () {
		return getRequetes().size();
	}

	public void setStrategieTraitement(IAlgoTraitementInterne strategieTraitement) {
		this.strategieTraitement = strategieTraitement;
	}

	/** Renvoie l'etat de ce ControleurInterne.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControleurInterne [ascenseur=" + ascenseur + ", requetes="
				+ getRequetes() + "]";
	}
}
