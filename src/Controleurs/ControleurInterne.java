package Controleurs;

import java.util.ArrayList;

import Client.Ascenseur;
import Client.Constantes;
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
	public Ascenseur getAscenseur() {
		return this.ascenseur;
	}
	
	/**Fonction permettant de traiter les {@link Requete} de l'{@link Ascenseur} gere par ce ControleurInterne pour chaque iteration
	 * 
	 */
	public String traiterRequetes(){
		//Si le controleur n'a pas de requete a traiter alors on ignore le traitement
		if (0 == requetes.size()) {
			if (ascenseur.isPortesOuvertes())
				ascenseur.fermerPortes();
			return "ascenseur " + ascenseur.getNumAsc() + " n'a pas de requete a traiter";
		}
		//Si le bouton stop a ete appuyer, et que l'ascenseur est debloque, alors on le bloque. Sinon on le debloque
		if (requetes.get(0).getLibelle() == Constantes.STOP) {
			if (ascenseur.estBloquer()) {
				ascenseur.debloque();
				return "ascenseur " + ascenseur.getNumAsc() + " se debloque.";
			}
			else {
				ascenseur.bloquer();
				return "ascenseur " + ascenseur.getNumAsc() + " se bloque.";
			}
		}
		//Si l'ascenseur est bloque alors on ignore le traitement.
		if (ascenseur.estBloquer()) {
			return "ascenseur " + ascenseur.getNumAsc() + " est bloque.";
		}
		//Si c'est une requete de mouvement
		if (requetes.get(0).getLibelle() == Constantes.DEPLACEMENT || 
				requetes.get(0).getLibelle() == Constantes.HAUT || 
				requetes.get(0).getLibelle() == Constantes.BAS) {
			
			//Si les portes sont ouvertes alors on les fermes
			if (ascenseur.isPortesOuvertes()) {
				ascenseur.fermerPortes();
				return "ascenseur " + ascenseur.getNumAsc() + " ferme ses portes";
			}
			//Si l'ascenseur n'est pas arrive, on le met l'ascenseur en mouvement.
			else if (!ascenseur.isEstEnMouvement() && !isEtageDemande()) {
				ascenseur.setEstEnMouvement(true);				
				return "Ascenceur " + ascenseur.getNumAsc() + " se met en mouvement de l'etage : " + ascenseur.getEtage() + " a l'etage " 
				+ requetes.get(0).getEtageDemande();
			}
			//Si l'ascenseur est arrete, a ce stade, c'est qu'il est arrive donc on ouvre les portes et on supprime la requete
			else if (!ascenseur.isEstEnMouvement()) {	
				ascenseur.ouvrirPortes();
				for (int i = 0; i < requetes.size(); ++i) {
					if (requetes.get(i).getEtageDemande() == ascenseur.getEtage()) {
						requetes.remove(i);
						--i;
					}
				}
				return "ascenseur " + ascenseur.getNumAsc() + " ouvre ses portes";
			}
			//Si l'ascenseur est a un etage demande, on l'arrete.
			else if (isEtageDemande()) {
				ascenseur.setEstEnMouvement(false);
				return "ascenseur " + ascenseur.getNumAsc() + " s'arrete a l'etage " + ascenseur.getEtage();
			}
			//A ce stade, l'ascenseur se deplace et n'est pas arrive, donc on le fait changer d'etage
			else {
				if (requetes.get(0).getEtageDemande() > ascenseur.getEtage()) {
					ascenseur.setEtage(ascenseur.getEtage() + 1);
				}
				else {
					ascenseur.setEtage(ascenseur.getEtage() - 1);
				}
				return "ascenseur " + ascenseur.getNumAsc() + " passe par l'etage " + ascenseur.getEtage();
			}
		}
		//Si on arrive ici, alors c'est une requete non prise en charge.
		return "Requete non reconnue";
	}
	
	/**Verifie si l'une des {@link Requete} correspond a l'etage courrant de l'{@link Ascenseur} de ce ControleurInterne.
	 * Utilise dans {@link #traiterRequetes()}.
	 * @return true si l'une des {@link Requete} correspond a l'etage courrant de l'{@link Ascenseur}, false sinon
	 */
	private Boolean isEtageDemande () {
		for (Requete i : requetes) {
			if (i.getEtageDemande() == this.ascenseur.getEtage()) {
				return true;
			}
		}
		return false;
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
	
	/**Obtient {@link #requetes} de ce ControleurInterne
	 * @return {@link #requetes}
	 */
	public ArrayList<Requete> getRequetes() {
		return requetes;
	}
}
