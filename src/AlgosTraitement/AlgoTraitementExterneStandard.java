package AlgosTraitement;

import Client.Constantes;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Description de le strategies de traitement interne standard.
 * @author Thomas
 */
public class AlgoTraitementExterneStandard implements IAlgoTraitementExterne{

	/**Variable utilisee durant {@link #traiterRequetes (ControleurExterne controleurExt)}.
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private ControleurInterne aUtiliser;
	
	/** traitement standard des {@link RequeteExterne}.
	 * @see IAlgoTraitementExterne#traiterRequetes(Controleurs.ControleurExterne)
	 */
	@Override
	public void traiterRequetes (ControleurExterne controleurExt) {
		for (int i = 0; i < controleurExt.getRequetes().size();++i) {//parcours de toutes les requetes externe non affectees
			
			if (rechercheInactif(controleurExt)) {//cherche d'abord si des ascenseurs sont inactifs
				
				aUtiliser.addRequete(controleurExt.getRequetes().get(i));
				controleurExt.getRequetes().remove(controleurExt.getRequetes().get(i));
				--i;//repositionne i sur la requete suivante, puisque la requete courante viens d'etre supprimee
			}
			else if (controleurExt.getRequetes().get(i).getLibelle() == Constantes.HAUT) {//si on veut aller vers le haut
				
				if (rechercheVersHaut(controleurExt.getRequetes().get(i).getEtageDemande(), controleurExt)) {
					/*Cherche un ascenseur qui est en-dessous de l'etage de la requete 
					et qui se dirige vers un etage superieur ou egal a celui de la requete*/
					
					aUtiliser.addRequetePrioritaire(controleurExt.getRequetes().get(i));// ajout de la requete de maniere prioritaire
					controleurExt.getRequetes().remove(controleurExt.getRequetes().get(i));
					--i;
				}
				else {//Sinon on cherche l'ascenseur ayant le moins de requete en attente
					
					rechercheMoinsActif (controleurExt);
					aUtiliser.addRequete(controleurExt.getRequetes().get(i));
					controleurExt.getRequetes().remove(controleurExt.getRequetes().get(i));
					--i;
				}
			}
			else {//si on veut aller vers le bas
				
				if(rechercheVersBas(controleurExt.getRequetes().get(i).getEtageDemande(), controleurExt)) {
					/*Cherche un ascenseur qui est au-dessus de l'etage de la requete 
					et qui se dirige vers un etage inferieur ou egal a celui de la requete*/
					
					aUtiliser.addRequetePrioritaire(controleurExt.getRequetes().get(i));
					controleurExt.getRequetes().remove(controleurExt.getRequetes().get(i));
					--i;
				}
				else {//Sinon on cherche l'ascenseur ayant le moins de requete en attente
					
					rechercheMoinsActif (controleurExt);
					aUtiliser.addRequete(controleurExt.getRequetes().get(i));
					controleurExt.getRequetes().remove(controleurExt.getRequetes().get(i));
					--i;
				}				
			}
		}
	}
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet de connaitre s'il y a un {@link ControleurInterne}
	 *  innactif parmis {@link ControleurExterne#controleurs}.
	 * Si c'est le cas, alors {@link #aUtiliser} le designera et la fonction renvera true.
	 * @param controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} innactif a ete trouve false sinon.
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private boolean rechercheInactif (ControleurExterne controleurExt) {
		for (ControleurInterne controleur : controleurExt.getControleurs()) {
			if (0 == controleur.getNumberOfRequete()) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet de verifier si l'etage d'ou provient la {@link Requete}
	 * courante est sur le chemin d'un {@link ControleurInterne} qui monte parmis {@link ControleurExterne#controleurs}. 
	 * Si c'est le cas, alors {@link #aUtiliser} le designera et la fonction renvera true.
	 * @param etage l'etage de la {@link Requete} consideree comme une {@link RequeteExterne}
	 * @param controleurExt controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} passe par etage, false sinon.
	 * @see RequeteExterne
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private boolean rechercheVersHaut (int etage, ControleurExterne controleurExt) {
		for (ControleurInterne controleur : controleurExt.getControleurs()) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenseur().getEtage() < etage && controleur.prochaineDest() >= etage)) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet de verifier si l'etage d'ou provient la {@link Requete}
	 * courante est sur le chemin d'un {@link ControleurInterne} qui descent parmis {@link ControleurExterne#controleurs}.
	 * Si c'est le cas, alors {@link #aUtiliser} le designera et la fonction renvera true.
	 * @param etage l'etage de la {@link Requete}. Consideree comme une {@link RequeteExterne}
	 * @param controleurExt controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} passe par etage, false sinon.
	 * @see RequeteExterne
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private boolean rechercheVersBas (int etage, ControleurExterne controleurExt) {
		for (ControleurInterne controleur : controleurExt.getControleurs()) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenseur().getEtage() > etage && controleur.prochaineDest() <= etage)) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet d'attribuer a {@link #aUtiliser} le {@link ControleurInterne}
	 * possedant le moins de {@link Requete} parmis {@link ControleurExterne#controleurs}.
	 * @param controleurExt controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @see ControleurExterne#traiterRequetes()
	 * @see ControleurInterne
	 */
	private void rechercheMoinsActif (ControleurExterne controleurExt) {
		int min = controleurExt.getNbEtage() + 1;
		for (ControleurInterne controleur : controleurExt.getControleurs()) {
			if (controleur.getNumberOfRequete() == 0) {
				this.aUtiliser = controleur;
				return;
			}
			else {
				if (controleur.getNumberOfRequete() < min) {
					min = controleur.getNumberOfRequete();
				}
			}
		}
		for (ControleurInterne controleur : controleurExt.getControleurs()) {
			if (controleur.getNumberOfRequete() == min) {
				this.aUtiliser = controleur;
				return;
			}
		}
	}
}