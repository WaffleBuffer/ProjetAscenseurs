package Controleurs;

import java.util.ArrayList;

import Client.Batiment;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Controleur correspondant a celui du sujet. Il en existe un par {@link Batiment} et permet de traiter les {@link RequeteExterne}
 * en choisissant quel est le meilleur {@link ControleurInterne} (donc {@link Ascenseur}) pour traiter la demande.
 * @author Thomas
 */
public class ControleurExterne implements IControleur{

	/**Il n'existe qu'un seul ControleurExterne par {@link Batiment} donc c'est un singleton.
	 * 
	 */
	final private static ControleurExterne singleton = new ControleurExterne ();
	
	/**Liste des {@link Requete} a traiter. Chaque {@link Requete} devrait etre une {@link RequeteExterne} pour fonctionner correctement.
	 * Sinon elle seront interpretee comme telle et n'auront pas le resultat attendu.
	 * 
	 */
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	/**Liste des {@link ControleurInterne} (et donc des {@link Ascenseur}) disponibles.
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
	
	/**ControleurExterne est un singleton donc le constructeur est private.
	 * 
	 */
	private ControleurExterne () {}
	
	/**Comme c'est un singleton, on doit definir par la suite les attributs. Cela est effectue dans le constructeur de {@link Batiment}
	 * @param controleurs liste des {@link ControleurInterne} disponnibles
	 * @see Batiment#Batiment(String, int, int)
	 */
	public void defineControleursInterne (ArrayList<ControleurInterne> controleurs) {
		this.controleurs = controleurs;
	}
	
	/**Comme c'est un singleton, on doit definir par la suite les attributs. Cela est effectue dans le constructeur de {@link Batiment}
	 * @param nbEtage nombre d'etage du {@link Batiment} correspondant.
	 * @see Batiment#Batiment(String, int, int)
	 */
	public void defineBatiment (int nbEtage) {
		this.nbEtage = nbEtage;
	}
	
	/**Obtient le singleton
	 * @return le singleton
	 */
	public static ControleurExterne getControleurExterne() {
		return singleton;
	}
	
	/**Permet d'ajouter une {@link RequeteExterne} a {@link ControleurExterne#requetes}.
	 * @param etage l'etage a partir duquel vient la {@link RequeteExterne}
	 * @param direction {@link Requete#libelle} que prend la {@link RequeteExterne} : "Haut" ou "Bas".
	 * @see RequeteExterne
	 */
	public void addRequete (int etage, String direction) {
		requetes.add(new RequeteExterne(etage, direction));
	}
	
	/**Permet de traiter toutes les requetes de {@link ControleurExterne#requetes}
	 * 
	 */
	public void traiterRequetes () {

		//Algo Naif
//		for (int i = 0; i < requetes.size(); ++i) {
//			controleurs.get(0).addRequete(requetes.get(i));
//			requetes.remove(requetes.get(i));
//		}
		
		//Algo "avance"
		for (int i = 0; i < requetes.size();++i) {
			
			if (searchForInactive()) {}
			else if (requetes.get(i).getLibelle().equals("Haut")) {
				if (searchToUp(requetes.get(i).getEtageDemande())) {}
				else {
					searchLessActive ();
				}
			}
			else {
				if(searchToDown(requetes.get(i).getEtageDemande())) {}
				else {
					searchLessActive ();
				}				
			}
			aUtiliser.addRequete(requetes.get(i));
			requetes.remove(requetes.get(i));
			--i;
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
