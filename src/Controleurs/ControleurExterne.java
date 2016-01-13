package Controleurs;

import java.util.ArrayList;

import Client.Batiment;
import Client.Constantes;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Controleur correspondant a celui du sujet. Il en existe un par {@link Batiment} et permet de traiter les {@link RequeteExterne}
 * en choisissant quel est le meilleur {@link ControleurInterne} (donc {@link Client.Ascenseur}) pour traiter la demande.
 * @author Thomas
 */
public class ControleurExterne implements IControleur{

	/**Liste des {@link Requete} a traiter. Chaque {@link Requete} devrait etre une {@link RequeteExterne} pour fonctionner correctement.
	 * Sinon elle seront interpretee comme telle et n'auront pas le resultat attendu.
	 * 
	 */
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	/**Liste des {@link ControleurInterne} (et donc des {@link Client.Ascenseur}) disponibles.
	 * 
	 */
	private ArrayList<ControleurInterne> controleurs;
	
	
	/**Variable utilisee durant {@link ControleurExterne#traiterRequetes()}.
	 * @see ControleurExterne#traiterRequetes()
	 */
	private ControleurInterne aUtiliser;
	
	
	/**Utilise pour connaitre le nombre d'etages. Utilise dans {@link ControleurExterne#searchLessActive()}
	 * @see ControleurExterne#searchLessActive()
	 */
	private int nbEtage;
	
	/**Construit un ControleurExterne
	 * @param controleurs iste des {@link ControleurInterne} disponnibles
	 * @param nbEtage nombre d'etage du {@link Batiment} correspondant.
	 * @see Batiment#Batiment(String, int, int)
	 */
	public ControleurExterne (ArrayList<ControleurInterne> controleurs, int nbEtage) {
		this.controleurs = controleurs;
		this.nbEtage = nbEtage;
	}
	
	/**Permet d'ajouter une {@link RequeteExterne} a {@link ControleurExterne#requetes}.
	 * @param etage l'etage a partir duquel vient la {@link RequeteExterne}
	 * @param direction {@link Requete#libelle} que prend la {@link RequeteExterne} : "Haut" ou "Bas".
	 * @see RequeteExterne
	 */
	public void addRequete (int etage, int direction) {
		requetes.add(new RequeteExterne(etage, direction));
	}
	
	/**Permet de traiter toutes les requetes de {@link ControleurExterne#requetes}
	 * 
	 */
	public void traiterRequetes () {

		for (int i = 0; i < requetes.size();++i) {//parcours de toutes les requetes externe non affectees
			
			if (searchForInactive()) {//cherche d'abord si des ascenseurs sont inactifs
				aUtiliser.addRequete(requetes.get(i));
				requetes.remove(requetes.get(i));
				--i;//repositionne i sur la requete suivante, puisque la requete courante viens d'etre supprimee
			}
			else if (requetes.get(i).getLibelle() == Constantes.HAUT) {//si on veut aller vers le haut
				if (searchToUp(requetes.get(i).getEtageDemande())) {
					/*Cherche un ascenseur qui est en-dessous de l'etage de la requete 
					et qui se dirige vers un etage superieur ou egal a celui de la requete*/
					aUtiliser.addRequetePrioritaire(requetes.get(i));// ajout de la requete de maniere prioritaire
					requetes.remove(requetes.get(i));
					--i;
				}
				else {//Sinon on cherche l'ascenseur ayant le moins de requete en attente
					searchLessActive ();
					aUtiliser.addRequete(requetes.get(i));
					requetes.remove(requetes.get(i));
					--i;
				}
			}
			else {//si on veut aller vers le bas
				if(searchToDown(requetes.get(i).getEtageDemande())) {
					/*Cherche un ascenseur qui est au-dessus de l'etage de la requete 
					et qui se dirige vers un etage inferieur ou egal a celui de la requete*/
					aUtiliser.addRequetePrioritaire(requetes.get(i));
					requetes.remove(requetes.get(i));
					--i;
				}
				else {//Sinon on cherche l'ascenseur ayant le moins de requete en attente
					searchLessActive ();
					aUtiliser.addRequete(requetes.get(i));
					requetes.remove(requetes.get(i));
					--i;
				}				
			}
		}
		

	}
	
	/**Utiliser dans {@link ControleurExterne#traiterRequetes()}. Permet de connaitre s'il y a un {@link ControleurInterne}
	 *  innactif parmis {@link ControleurExterne#controleurs}.
	 * Si c'est le cas, alors {@link ControleurExterne#aUtiliser} le designera et la fonction renvera true.
	 * @return true si un {@link ControleurInterne} innactif a ete trouve false sinon.
	 * @see ControleurExterne#traiterRequetes()
	 */
	private boolean searchForInactive () {
		for (ControleurInterne controleur : controleurs) {
			if (0 == controleur.getNumberOfRequete()) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	/**Utiliser dans {@link ControleurExterne#traiterRequetes()}. Permet de verifier si l'etage d'ou provient la {@link Requete}
	 * courante est sur le chemin d'un {@link ControleurInterne} qui monte parmis {@link ControleurExterne#controleurs}. 
	 * Si c'est le cas, alors {@link ControleurExterne#aUtiliser} le designera et la fonction renvera true.
	 * @param etage l'etage de la {@link Requete}. Consideree comme une {@link RequeteExterne}
	 * @return true si un {@link ControleurInterne} passe par etage, flase sinon.
	 * @see RequeteExterne
	 * @see ControleurExterne#traiterRequetes()
	 */
	private boolean searchToUp (int etage) {
		for (ControleurInterne controleur : controleurs) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenceur().getEtage() < etage && controleur.prochaineDest() >= etage)) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	/**Utiliser dans {@link ControleurExterne#traiterRequetes()}. Permet de verifier si l'etage d'ou provient la {@link Requete}
	 * courante est sur le chemin d'un {@link ControleurInterne} qui descent parmis {@link ControleurExterne#controleurs}.
	 * Si c'est le cas, alors {@link ControleurExterne#aUtiliser} le designera et la fonction renvera true.
	 * @param etage l'etage de la {@link Requete}. Consideree comme une {@link RequeteExterne}
	 * @return true si un {@link ControleurInterne} passe par etage, false sinon.
	 * @see RequeteExterne
	 * @see ControleurExterne#traiterRequetes()
	 */
	private boolean searchToDown (int etage) {
		for (ControleurInterne controleur : controleurs) {
			if (controleur.prochaineDest() != -1 && 
					(controleur.getAscenceur().getEtage() > etage && controleur.prochaineDest() <= etage)) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
	/**Utiliser dans {@link ControleurExterne#traiterRequetes()}. Permet d'attribuer a {@link ControleurExterne#aUtiliser} le {@link ControleurInterne}
	 * possedant le moins de {@link Requete} parmis {@link ControleurExterne#controleurs}.
	 * 
	 * @see ControleurExterne#traiterRequetes()
	 * @see ControleurInterne
	 */
	private void searchLessActive () {
		int min = nbEtage + 1;
		for (ControleurInterne controleur : controleurs) {
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
		for (ControleurInterne controleur : controleurs) {
			if (controleur.getNumberOfRequete() == min) {
				this.aUtiliser = controleur;
				return;
			}
		}
	}

	/**Ajoute une {@link Requete} a {@link ControleurExterne#requetes}. requete doit etre une {@link RequeteExterne} pour un fonctionnement nromal.
	 * Sinon elle sera traitee comme tel.
	 * @see Controleurs.IControleur#addRequete(Requetes.Requete)
	 */
	public void addRequete(Requete requete) {
		requetes.add(requete);
	}
}
