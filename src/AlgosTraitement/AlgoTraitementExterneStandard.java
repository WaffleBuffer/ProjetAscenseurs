package AlgosTraitement;

import Client.Constantes;
import Controleurs.ControleurExterne;
import Controleurs.ControleurInterne;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Description de la strategie de traitement interne standard.
 * @author Thomas
 */
public class AlgoTraitementExterneStandard implements IAlgoTraitementExterne{

	/**Variable utilisee durant {@link #traiterRequetes (ControleurExterne controleurExt)}.
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private ControleurInterne controleurInterneUtilise;
	
	/** traitement standard des {@link RequeteExterne}.
	 * @see IAlgoTraitementExterne#traiterRequetes(Controleurs.ControleurExterne)
	 */
	@Override
	public void traiterRequetes (ControleurExterne controleurExterne) {
		for (int i = 0; i < controleurExterne.getRequetes().size();++i) {//parcours de toutes les requetes externe non affectees
			
			if (rechercheInactif(controleurExterne)) {//cherche d'abord si des ascenseurs sont inactifs
				
				controleurInterneUtilise.ajouterRequete(controleurExterne.getRequetes().get(i));
				controleurExterne.getRequetes().remove(controleurExterne.getRequetes().get(i));
				--i;//repositionne i sur la requete suivante, puisque la requete courante viens d'etre supprimee
			}
			else if (controleurExterne.getRequetes().get(i).getLibelle() == Constantes.HAUT) {//si on veut aller vers le haut
				
				if (rechercheVersHaut(controleurExterne.getRequetes().get(i).getEtageDemande(), controleurExterne)) {
					/*Cherche un ascenseur qui est en-dessous de l'etage de la requete 
					et qui se dirige vers un etage superieur ou egal a celui de la requete*/
					
					controleurInterneUtilise.ajouterRequetePrioritaire(controleurExterne.getRequetes().get(i));// ajout de la requete de maniere prioritaire
					controleurExterne.getRequetes().remove(controleurExterne.getRequetes().get(i));
					--i;
				}
				else {//Sinon on cherche l'ascenseur ayant le moins de requete en attente
					
					if (rechercheMoinsActif (controleurExterne)) {
						controleurInterneUtilise.ajouterRequete(controleurExterne.getRequetes().get(i));
						controleurExterne.getRequetes().remove(controleurExterne.getRequetes().get(i));
						--i;
					}
				}
			}
			else if (controleurExterne.getRequetes().get(i).getLibelle() == Constantes.BAS){//si on veut aller vers le bas
				
				if(rechercheVersBas(controleurExterne.getRequetes().get(i).getEtageDemande(), controleurExterne)) {
					/*Cherche un ascenseur qui est au-dessus de l'etage de la requete 
					et qui se dirige vers un etage inferieur ou egal a celui de la requete*/
					
					controleurInterneUtilise.ajouterRequetePrioritaire(controleurExterne.getRequetes().get(i));
					controleurExterne.getRequetes().remove(controleurExterne.getRequetes().get(i));
					--i;
				}
				else {//Sinon on cherche l'ascenseur ayant le moins de requete en attente
					
					if (rechercheMoinsActif (controleurExterne)) {
						controleurInterneUtilise.ajouterRequete(controleurExterne.getRequetes().get(i));
						controleurExterne.getRequetes().remove(controleurExterne.getRequetes().get(i));
						--i;
					}
				}				
			}
		}// boucle for
	}// traiterRequete()
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet de connaitre s'il y a un {@link ControleurInterne}
	 *  innactif parmi {@link ControleurExterne#listeControleursInternes}.
	 * Si c'est le cas, alors {@link #controleurInterneUtilise} le designera et la fonction renverra true.
	 * @param controleurExterne le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} inactif a ete trouve false sinon.
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private boolean rechercheInactif (ControleurExterne controleurExterne) {
		for (ControleurInterne controleur : controleurExterne.getControleurs()) {
			if (0 == controleur.getNumberOfRequete() && !controleur.getAscenseur().estBloquer()) {
				this.controleurInterneUtilise = controleur;
				return true;
			}
		}
		return false;
	}//rechercheInactif()
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet de verifier si l'etage d'ou provient la {@link Requete}
	 * courante est sur le chemin d'un {@link ControleurInterne} qui monte parmi {@link ControleurExterne#listeControleursInternes}. 
	 * Si c'est le cas, alors {@link #controleurInterneUtilise} le designera et la fonction renverra true.
	 * @param etage l'etage de la {@link Requete} consideree comme une {@link RequeteExterne}
	 * @param controleurExterne controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} passe par etage, false sinon.
	 * @see RequeteExterne
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private boolean rechercheVersHaut (int etage, ControleurExterne controleurExterne) {
		for (ControleurInterne controleurInterne : controleurExterne.getControleurs()) {
			if (controleurInterne.prochaineDest() != -1 && 
					(controleurInterne.getAscenseur().getEtage() < etage && controleurInterne.prochaineDest() >= etage &&
					!controleurInterne.getAscenseur().estBloquer())) {
				this.controleurInterneUtilise = controleurInterne;
				return true;
			}
		}
		return false;
	}//rechercheVersHaut()
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet de verifier si l'etage d'ou provient la {@link Requete}
	 * courante est sur le chemin d'un {@link ControleurInterne} qui descent parmis {@link ControleurExterne#listeControleursInternes}.
	 * Si c'est le cas, alors {@link #controleurInterneUtilise} le designera et la fonction renvera true.
	 * @param etage l'etage de la {@link Requete}. Consideree comme une {@link RequeteExterne}
	 * @param controleurExt controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} passe par etage, false sinon.
	 * @see RequeteExterne
	 * @see #traiterRequetes (ControleurExterne controleurExt)
	 */
	private boolean rechercheVersBas (int etage, ControleurExterne controleurExt) {
		for (ControleurInterne controleur : controleurExt.getControleurs()) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenseur().getEtage() > etage && controleur.prochaineDest() <= etage) && 
					!controleur.getAscenseur().estBloquer()) {
				this.controleurInterneUtilise = controleur;
				return true;
			}
		}
		return false;
	}//rechercheVersBas()
	
	/**Utilise dans {@link #traiterRequetes (ControleurExterne controleurExt)}. Permet d'attribuer a {@link #controleurInterneUtilise} le {@link ControleurInterne}
	 * possedant le moins de {@link Requete} parmi {@link ControleurExterne#listeControleursInternes} s'il y en a un.
	 * @param controleurExterne controleurExt le {@link ControleurExterne} sur lequel appliquer l'{@link AlgoTraitementExterneStandard}.
	 * @return true si un {@link ControleurInterne} disponible a ete trouve, false sinon (ils sont tous bloque).
	 * @see ControleurExterne#traiterRequetes()
	 * @see ControleurInterne
	 */
	private boolean rechercheMoinsActif (ControleurExterne controleurExterne) {
		//expliquer ce qu'est min?
		int min = controleurExterne.getNbEtage() + 1;
		for (ControleurInterne controleurInterne : controleurExterne.getControleurs()) {
			if (controleurInterne.getNumberOfRequete() == 0 && !controleurInterne.getAscenseur().estBloquer()) {
				this.controleurInterneUtilise = controleurInterne;
				return true;
			}
			else {
				if (controleurInterne.getNumberOfRequete() < min && !controleurInterne.getAscenseur().estBloquer()) {
					min = controleurInterne.getNumberOfRequete();
				}
			}
		}
		for (ControleurInterne controleurInterne : controleurExterne.getControleurs()) {
			if (controleurInterne.getNumberOfRequete() == min && !controleurInterne.getAscenseur().estBloquer()) {
				this.controleurInterneUtilise = controleurInterne;
				return true;
			}
		}
		return false;
	}//rechercheMoinsActif()
}