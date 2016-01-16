package Controleurs;

import Client.Ascenseur;
import Client.Constantes;
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
	
	/**Construit un ControleurInterne.
	 * @param ascenseur {@link Ascenseur} lie a ce ControleurInterne.
	 */
	public ControleurInterne (Ascenseur ascenseur) {
		this.ascenseur = ascenseur;
	}
	
	/**Renvoi l'{@link Ascenseur} gere par ce ControleurInterne
	 * @return l'{@link Ascenseur} gere par ce ControleurInterne
	 */
	public Ascenseur getAscenseur() {
		return this.ascenseur;
	}
	
	/**Fonction permettant de traiter les {@link Controleur#requetes} de l'{@link Ascenseur} gere par ce ControleurInterne pour une iteration
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 */
	public String traiterRequetes(){
		//Si le controleur n'a pas de requete a traiter alors on ignore le traitement
		if (0 == getRequetes().size()) {
			if (ascenseur.isPortesOuvertes())
				ascenseur.fermerPortes();
			return "ascenseur " + ascenseur.getNumAsc() + " n'a pas de requete a traiter";
		}
		//Si le bouton stop a ete appuyer, et que l'ascenseur est debloque, alors on le bloque. Sinon on le debloque
		if (getRequetes().get(0).getLibelle() == Constantes.STOP) {
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
		if (getRequetes().get(0).getLibelle() == Constantes.DEPLACEMENT || 
				getRequetes().get(0).getLibelle() == Constantes.HAUT || 
				getRequetes().get(0).getLibelle() == Constantes.BAS) {
			
			//Si les portes sont ouvertes alors on les fermes
			if (ascenseur.isPortesOuvertes()) {
				ascenseur.fermerPortes();
				return "ascenseur " + ascenseur.getNumAsc() + " ferme ses portes";
			}
			//Si l'ascenseur n'est pas arrive, on le met l'ascenseur en mouvement.
			else if (!ascenseur.isEstEnMouvement() && !isEtageDemande()) {
				ascenseur.setEstEnMouvement(true);				
				return "Ascenceur " + ascenseur.getNumAsc() + " se met en mouvement de l'etage : " + ascenseur.getEtage() + " a l'etage " 
				+ getRequetes().get(0).getEtageDemande();
			}
			//Si l'ascenseur est arrete, a ce stade, c'est qu'il est arrive donc on ouvre les portes et on supprime la requete
			else if (!ascenseur.isEstEnMouvement()) {	
				ascenseur.ouvrirPortes();
				for (int i = 0; i < getRequetes().size(); ++i) {
					if (getRequetes().get(i).getEtageDemande() == ascenseur.getEtage()) {
						getRequetes().remove(i);
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
				if (getRequetes().get(0).getEtageDemande() > ascenseur.getEtage()) {
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
	
	/**Verifie si l'une des {@link Requete} stockee correspond a l'etage courrant de l'{@link Ascenseur} de ce ControleurInterne.
	 * Utilise dans {@link #traiterRequetes()}.
	 * @return true si l'une des {@link Requete} correspond a l'etage courrant de l'{@link Ascenseur}, false sinon
	 */
	private Boolean isEtageDemande () {
		for (Requete i : getRequetes()) {
			if (i.getEtageDemande() == this.ascenseur.getEtage()) {
				return true;
			}
		}
		return false;
	}
	
	/**Ajout d'une {@link Requete} prioritaire sur les requetes en cours de traitement
	 * @param requete la {@link Requete} prioritaire a ajouter.
	 */
	public void addRequetePrioritaire (Requete requete) {
		this.getRequetes().add(0, requete);
	}
	
	/**Ajout d'une {@link RequeteInterne} specifique a {@link Controleur#requetes}
	 * @param etage l'etage de la {@link RequeteInterne}.
	 * @see RequeteInterne
	 */
	public void addRequete (int etage) {
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

	/** Renvoie l'etat de ce ControleurInterne.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControleurInterne [ascenseur=" + ascenseur + ", requetes="
				+ getRequetes() + "]";
	}
}
