package Controleurs;

import java.util.ArrayList;

import Client.Batiment;
import Requetes.Requete;
import Requetes.RequeteExterne;
import AlgosTraitement.IAlgoTraitementExterne;

/**Controleur correspondant a celui du sujet. Il en existe un par {@link Batiment} et permet de traiter les {@link RequeteExterne}
 * en choisissant quel est le meilleur {@link ControleurInterne} (donc {@link Client.Ascenseur}) pour traiter la demande.
 * @author Thomas
 */
public class ControleurExterne extends Controleur{
	
	/**Liste des {@link ControleurInterne} (et donc des {@link Client.Ascenseur}) disponibles.
	 */
	private ArrayList<ControleurInterne> controleurs;

	/**Variable utilisee durant {@link ControleurExterne#traiterRequetes()}.
	 * @see ControleurExterne#traiterRequetes()
	 */
	private ControleurInterne aUtiliser;
	
	/**Utilise pour connaitre le nombre d'etages. Utilise dans {@link ControleurExterne#rechercheMoinsActif()}
	 * @see ControleurExterne#rechercheMoinsActif()
	 */
	private int nbEtage;
	
	private IAlgoTraitementExterne strategieTraitement;
	
	/**Construit un ControleurExterne
	 * @param controleurs liste des {@link ControleurInterne} disponnibles
	 * @param nbEtage nombre d'etage du {@link Batiment} correspondant.
	 * @see Batiment#Batiment(String, int, int)
	 */
	public ControleurExterne (ArrayList<ControleurInterne> controleurs, int nbEtage, IAlgoTraitementExterne strategie) {
		this.strategieTraitement = strategie;
		this.controleurs = controleurs;
		this.nbEtage = nbEtage;
	}
	
	/**Permet d'ajouter une {@link RequeteExterne} a {@link Controleur#requetes}.
	 * @param etage l'etage a partir duquel vient la {@link RequeteExterne}
	 * @param direction {@link Requete#libelle} que prend la {@link RequeteExterne} : {@link Client.Constantes#HAUT} ou {@link Client.Constantes#BAS}.
	 * @see RequeteExterne
	 */
	public void addRequete (int etage, int direction) {
		getRequetes().add(new RequeteExterne(etage, direction));
	}
	
	/**Permet de traiter toutes les requetes de {@link Controleur#requetes}
	 * 
	 */
	public void traiterRequetes () {
		strategieTraitement.traiterRequetes(this);
	}
	
	public int getNbEtage() {
		return nbEtage;
	}

	public ArrayList<ControleurInterne> getControleurs() {
		return controleurs;
	}
}
