package Controleurs;

import java.util.ArrayList;

import Client.Batiment;
import Requetes.Requete;
import Requetes.RequeteExterne;
import AlgosTraitement.AlgoTraitementExterneStandard;
import AlgosTraitement.IAlgoTraitementExterne;

/**Controleur correspondant a celui du sujet. Il en existe un par {@link Batiment} et permet de traiter les {@link RequeteExterne}
 * en choisissant quel est le meilleur {@link ControleurInterne} (donc {@link Client.Ascenseur}) pour traiter la demande.
 * @author Thomas
 */
public class ControleurExterne extends Controleur{
	
	/**Liste des {@link ControleurInterne} (et donc des {@link Client.Ascenseur}) disponibles.
	 */
	private ArrayList<ControleurInterne> listeControleursInternes;
	
	/**Utilise pour connaitre le nombre d'etages. Utilise dans {@link AlgoTraitementExterneStandard#rechercheMoinsActif (ControleurExterne controleurExt)}
	 * @see AlgoTraitementExterneStandard#rechercheMoinsActif (ControleurExterne controleurExt)
	 */
	private int nbEtages;
	
	/**la strategie a appliquer pour traiter les {@link RequeteExterne} de ce ControleurExterne
	 */
	private IAlgoTraitementExterne strategieTraitement;
	
	/**Construit un ControleurExterne
	 * @param controleurs liste des {@link ControleurInterne} disponnibles
	 * @param nbEtages nombre d'etage du {@link Batiment} correspondant.
	 * @param strategie l'{@link IAlgoTraitementExterne} a appliquer pour traiter les {@link RequeteExterne} de ce ControleurExterne
	 * @see Batiment#Batiment(String, int, int)
	 */
	public ControleurExterne (ArrayList<ControleurInterne> controleurs, int nbEtages, IAlgoTraitementExterne strategie) {
		this.strategieTraitement = strategie;
		this.listeControleursInternes = controleurs;
		this.nbEtages = nbEtages;
	}
	
	/**Permet d'ajouter une {@link RequeteExterne} a {@link Controleur#listeRequetes}.
	 * @param etage l'etage a partir duquel vient la {@link RequeteExterne}
	 * @param direction {@link Requete#libelle} que prend la {@link RequeteExterne} : {@link Client.Constantes#HAUT} ou {@link Client.Constantes#BAS}.
	 * @see RequeteExterne
	 */
	public void addRequete (int etage, int direction) {
		getRequetes().add(new RequeteExterne(etage, direction));
	}
	
	/**Permet de traiter toutes les requetes de {@link Controleur#listeRequetes} 
	 * en appliquant la {@link #strategieTraitement} de ce ControleurExterne.
	 * 
	 */
	public void traiterRequetes () {
		strategieTraitement.traiterRequetes(this);
	}
	
	/**Permet d'obtenir le {@link #nbEtages} de ce ControleurExterne
	 * @return {@link #nbEtages} de ce ControleurExterne
	 */
	public int getNbEtage() {
		return nbEtages;
	}

	/**Permet d'obtenir les {@link #listeControleursInternes} de ce ControleurExterne.
	 * @return {@link #listeControleursInternes} de ce ControleurExterne.
	 */
	public ArrayList<ControleurInterne> getControleurs() {
		return listeControleursInternes;
	}
}
