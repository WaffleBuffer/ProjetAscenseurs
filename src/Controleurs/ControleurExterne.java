package Controleurs;

import java.util.ArrayList;

import Client.Batiment;
import Requetes.Requete;
import Requetes.RequeteExterne;

/**Controleur correspondant a celui du sujet. Il en existe un par {@link Batiment} et permet de traiter les {@link RequeteExterne}
 * en choisissant quel est le meilleur {@link ControleurInterne} pour traiter la demande.
 * @author Thomas
 *
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
	
	/**Liste des {@link ControleurInterne} (et donc des {@link Ascenseur}) disponnibles.
	 * 
	 */
	private ArrayList<ControleurInterne> controleurs;
	
	
	/**Variable utiliser durant {@link ControleurExterne#traiterRequetes()}.
	 * @see ControleurExterne#traiterRequetes()
	 */
	private ControleurInterne aUtiliser;
	
	
	/**Utiliser pour connaitre le nombre d'etage.
	 * 
	 */
	private int nbEtage;
	
	private ControleurExterne () {}
	
	public void defineControleursInterne (ArrayList<ControleurInterne> controleurs) {
		this.controleurs = controleurs;
	}
	
	public void defineBatiment (int batiment) {
		this.nbEtage = batiment;
	}
	
	public static ControleurExterne getControleurExterne() {
		return singleton;
	}
	
	public void addRequete (int etage, String direction) {
		requetes.add(new RequeteExterne(etage, direction));
	}
	
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
	
	private boolean searchForInactive () {
		for (ControleurInterne controleur : controleurs) {
			if (0 == controleur.getNumberOfRequete()) {
				this.aUtiliser = controleur;
				return true;
			}
		}
		return false;
	}
	
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

	public void addRequete(Requete requete) {
		requetes.add(requete);
	}
}
