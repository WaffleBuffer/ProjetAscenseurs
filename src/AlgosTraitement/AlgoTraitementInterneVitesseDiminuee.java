package AlgosTraitement;

import Client.Ascenseur;
import Client.Constantes;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Description de le strategies de traitement interne standard.
 * @author Thomas
 */
public class AlgoTraitementInterneVitesseDiminuee implements IAlgoTraitementInterne {
	
	/**Defini si oui ou non, l'{@link Ascenseur} a attendu une iteration a ne rien faire, car il est lent.
	 */
	private boolean aDejaAttenduUneIteration = false;

	/**Fonction permettant de traiter les {@link Controleurs.Controleur#listeRequetes} de l'{@link Ascenseur}
	 *  gere par controleurInt pour une iteration de maniere standard.
	 * @param controleurInterne le {@link ControleurInterne} sur lequel appliquer l'algorithme.
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 */
	public String traiterRequetes(ControleurInterne controleurInterne){
		if (!(aDejaAttenduUneIteration)) {
			aDejaAttenduUneIteration = true;
			return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is being SLOOOOOOOOOOOW";
		}
			
		//Si le controleur n'a pas de requete a traiter alors on ignore le traitement
		if (0 == controleurInterne.getRequetes().size()) {
			if (controleurInterne.getAscenseur().isPortesOuvertes())
				controleurInterne.getAscenseur().fermerPortes();
			aDejaAttenduUneIteration = true;
			return "";
			/*"Elevator " + controleurInterne.getAscenseur().getNumAsc() + " has no queries processed";*/
		}
		//Si le bouton stop a ete appuyer, et que l'ascenseur est debloque, alors on le bloque. Sinon on le debloque
		if (controleurInterne.getRequetes().get(0).getLibelle() == Constantes.STOP) {
			if (controleurInterne.getAscenseur().estBloquer()) {
				controleurInterne.getAscenseur().debloquer();
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is released";
			}
			else {
				controleurInterne.getAscenseur().bloquer();
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is spirit to hang";
			}
		}
		//Si l'ascenseur est bloque alors on ignore le traitement.
		if (controleurInterne.getAscenseur().estBloquer()) {
			aDejaAttenduUneIteration = false;
			return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is blocked";
		}
		//Si c'est une requete de mouvement
		if (controleurInterne.getRequetes().get(0).getLibelle() == Constantes.DEPLACEMENT || 
				controleurInterne.getRequetes().get(0).getLibelle() == Constantes.HAUT || 
						controleurInterne.getRequetes().get(0).getLibelle() == Constantes.BAS) {
			
			//Si les portes sont ouvertes alors on les fermes
			if (controleurInterne.getAscenseur().isPortesOuvertes()) {
				controleurInterne.getAscenseur().fermerPortes();
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " closes its doors";
			}
			//Si l'ascenseur n'est pas arrive, on le met l'ascenseur en mouvement.
			else if (!controleurInterne.getAscenseur().isEstEnMouvement() && !isEtageDemande(controleurInterne)) {
				controleurInterne.getAscenseur().setEstEnMouvement(true);
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " move from : "
						+ controleurInterne.getAscenseur().getEtage() + " to : " 
				+ controleurInterne.getRequetes().get(0).getEtageDemande();
			}
			//Si l'ascenseur est arrete, a ce stade, c'est qu'il est arrive donc on ouvre les portes et on supprime la requete
			else if (!controleurInterne.getAscenseur().isEstEnMouvement()) {	
				controleurInterne.getAscenseur().ouvrirPortes();
				for (int i = 0; i < controleurInterne.getRequetes().size(); ++i) {
					if (controleurInterne.getRequetes().get(i).getEtageDemande() == controleurInterne.getAscenseur().getEtage()) {
						controleurInterne.getRequetes().remove(i);
						--i;
					}
				}
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " opens its doors";
			}
			//Si l'ascenseur est a un etage demande, on l'arrete.
			else if (isEtageDemande(controleurInterne)) {
				controleurInterne.getAscenseur().setEstEnMouvement(false);
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " stops on the floor " + controleurInterne.getAscenseur().getEtage();
			}
			//A ce stade, l'ascenseur se deplace et n'est pas arrive, donc on le fait changer d'etage
			else {
				if (controleurInterne.getRequetes().get(0).getEtageDemande() > controleurInterne.getAscenseur().getEtage()) {
					controleurInterne.getAscenseur().setEtage(controleurInterne.getAscenseur().getEtage() + 1);
				}
				else {
					controleurInterne.getAscenseur().setEtage(controleurInterne.getAscenseur().getEtage() - 1);
				}
				aDejaAttenduUneIteration = false;
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " goes through the " + controleurInterne.getAscenseur().getEtage() + " floor";
			}
		}
		//Si on arrive ici, alors c'est une requete non prise en charge.
		aDejaAttenduUneIteration = false;
		return "Unknown request";
	}//traiterRequetes()
	
	/**Verifie si l'une des {@link Requete} stockee par controleurInt correspond a l'etage courrant de l'{@link Ascenseur} de ControleurInt.
	 * Utilise dans {@link #traiterRequetes(ControleurInterne)}.
	 * @param controleurInt le {@link ControleurInterne} sur lequel s'applique le traitement.
	 * @return true si l'une des {@link Requetes.Requete} de ControleurInt
	 *  correspond a l'etage courrant de l'{@link Ascenseur} de ControleurInt, false sinon
	 */
	private Boolean isEtageDemande (ControleurInterne controleurInt) {
		for (Requete i : controleurInt.getRequetes()) {
			if (i.getEtageDemande() == controleurInt.getAscenseur().getEtage()) {
				return true;
			}
		}
		return false;
	}//isEtageDemande()
}