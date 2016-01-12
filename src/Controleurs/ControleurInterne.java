package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Requetes.Requete;
import Requetes.RequeteInterne;

/**Designe le traitement des {@link Requete} d'un {@link Ascenseur}. Il existe un ControleurInterne par {@link Ascenseur}.
 * @author Thomas
 */
public class ControleurInterne implements IControleur{
	
	/**{@link Ascenseur} gere par ce ControleurInterne.
	 * 
	 */
	private Ascenseur ascenseur;
	//liste des requetes
	
	/**Liste des {@link Requete} affectees a ce ControleurInterne.
	 * 
	 */
	private ArrayList<Requete> requetes = new ArrayList<Requete>();
	
	/**Construit un ControleurInterne.
	 * @param ascenseur {@link Ascenseur} lie a ce ControleurInterne.
	 */
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	/**Renvoi {@link Ascenseur} gere par ce ControleurInterne
	 * @return {@link Ascenseur} gere par ce ControleurInterne
	 */
	public Ascenseur getAscenceur() {
		return this.ascenseur;
	}
	
	/**Fonction permettant de traiter les {@link Requete} de l'{@link Ascenseur} gere par ce ControleurInterne pour chaque iteration
	 * 
	 */
	public String traiterRequetes(){
		//Si le bouton stop a ete appuyer, alors on ignore le traitement
		if (requetes.get(0).getLibelle() == "Stop") {
			if (ascenseur.estBloquer()) {
				ascenseur.debloque();
				return "ascenseur " + ascenseur.getNumAsc() + " se debloque.";
			}
			else {
				ascenseur.bloquer();
				return "ascenseur " + ascenseur.getNumAsc() + " se bloque.";
			}
		}
		if (ascenseur.estBloquer()) {
			return "ascenseur " + ascenseur.getNumAsc() + " est bloque.";
		}
		//Si c'est une requete de mouvement
		if (requetes.get(0).getLibelle() == "Allez a l'etage" || 
				requetes.get(0).getLibelle() == "Haut" || 
				requetes.get(0).getLibelle() == "Bas") {
			
			//Si les portes sont ouvertes alors on les fermes
			if (ascenseur.isPortesOuvertes()) {
				ascenseur.fermerPortes();
				return "ascenseur " + ascenseur.getNumAsc() + " ferme les portes";
			}
			//Si l'ascenseur n'est pas arrive, on le met l'ascenseur en mouvement
			else if (!ascenseur.isEstEnMouvement() && !(requetes.get(0).getEtageDemande() == ascenseur.getEtage())) {
				ascenseur.setEstEnMouvement(true);				
				return "Ascenceur " + ascenseur.getNumAsc() + " va de l'étage : " + ascenseur.getEtage() + " à l'étage " 
				+ requetes.get(0).getEtageDemande();
			}
			//Si l'ascenseur est arrete, a ce stade, c'est qu'il est arrive donc on ouvre les portes et on supprime la requete
			else if (!ascenseur.isEstEnMouvement()) {	
				ascenseur.ouvrirPortes();
				requetes.remove(requetes.get(0));
				return "ascenseur " + ascenseur.getNumAsc() + " ouvre les portes";
			}
			//A ce stade, l'ascenseur se deplace et n'est pas arrive, donc on le fait changer d'etage
			else {
				if (requetes.get(0).getLibelle().equals("Haut")) {
					ascenseur.setEtage(ascenseur.getEtage() + 1);
				}
				else {
					ascenseur.setEtage(ascenseur.getEtage() - 1);
				}
				return "ascenseur " + ascenseur.getNumAsc() + " passe par l'etage " + ascenseur.getEtage();
			}
		}
		return "Requete non reconnue";
	}
	
	/**Ajout d'une {@link Requete} specifique a {@link ControleurInterne#requetes}
	 * @see Controleurs.IControleur#addRequete(Requetes.Requete)
	 * @see Requete
	 */
	public void addRequete (Requete requete) {
		requetes.add(requete);
	}
	
	/**Ajout d'une {@link Requete} prioritaire sur les requetes en cours de traitement
	 * @param requete
	 */
	public void addRequetePrioritaire (Requete requete) {
		this.requetes.add(0, requete);
	}
	
	/**Ajout d'une {@link RequeteInterne} specifique a {@link ControleurInterne#requetes}
	 * @param etage l'etage de la {@link RequeteInterne}.
	 * @see Controleurs.IControleur#addRequete(Requetes.Requete)
	 * @see RequeteInterne
	 */
	public void addRequete (int etage) {
		requetes.add(new RequeteInterne(etage));
	}
	
	/**Renvoi le numero d'etage de la prochaine {@link Requete}. Si {@link ControleurInterne#requetes} est vide alors renvoie -1.
	 * @return le numero d'etage de la prochaine {@link Requete}. Si {@link ControleurInterne#requetes} est vide alors renvoie -1.
	 */
	public int prochaineDest () {
		if (requetes.size() > 0) {
			return requetes.get(0).getEtageDemande();
		}
		//S'il n'y a pas de requete retourne -1
		else
			return -1;
	}
	
	/**Renvoi le nombre de {@link Requete} dans {@link ControleurInterne#requetes}.
	 * @return le nombre de {@link Requete} dans {@link ControleurInterne#requetes}.
	 */
	public int getNumberOfRequete () {
		return requetes.size();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControleurInterne [ascenseur=" + ascenseur + ", requetes="
				+ requetes + "]";
	}
}
