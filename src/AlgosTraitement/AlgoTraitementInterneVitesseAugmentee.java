package AlgosTraitement;

import Client.Ascenseur;
import Client.Constantes;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Description de le strategies de traitement interne standard.
 * @author Thomas
 */
public class AlgoTraitementInterneVitesseAugmentee implements IAlgoTraitementInterne{

	/**Fonction permettant de traiter les {@link Controleurs.Controleur#requetes} de l'{@link Ascenseur}
	 *  gere par controleurInt pour une iteration de maniere standard.
	 * @param controleurInterne le {@link ControleurInterne} sur lequel appliquer l'algorithme.
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 */
	public String traiterRequetes(ControleurInterne controleurInterne){
		//Si le controleur n'a pas de requete a traiter alors on ignore le traitement
		if (0 == controleurInterne.getRequetes().size()) {
			if (controleurInterne.getAscenseur().isPortesOuvertes())
				controleurInterne.getAscenseur().fermerPortes();
			return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " has no queries processed";
		}
		//Si le bouton stop a ete appuyer, et que l'ascenseur est debloque, alors on le bloque. Sinon on le debloque et on continue
		if (controleurInterne.getRequetes().get(0).getLibelle() == Constantes.STOP) {
			if (controleurInterne.getAscenseur().estBloquer()) {
				controleurInterne.getAscenseur().debloque();
			}
			else {
				controleurInterne.getAscenseur().bloquer();
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is spirit to hang";
			}
		}
		//Si l'ascenseur est bloque alors on ignore le traitement.
		if (controleurInterne.getAscenseur().estBloquer()) {
			return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " is blocked";
		}
		//Si c'est une requete de mouvement
		if (controleurInterne.getRequetes().get(0).getLibelle() == Constantes.DEPLACEMENT || 
				controleurInterne.getRequetes().get(0).getLibelle() == Constantes.HAUT || 
						controleurInterne.getRequetes().get(0).getLibelle() == Constantes.BAS) {
			
			//Si les portes sont ouvertes alors on les fermes et on continue
			if (controleurInterne.getAscenseur().isPortesOuvertes()) {
				controleurInterne.getAscenseur().fermerPortes();
			}
			//Si l'ascenseur n'est pas arrive, on le met l'ascenseur en mouvement et on continue.
			if (!controleurInterne.getAscenseur().isEstEnMouvement() && !isEtageDemande(controleurInterne)) {
				controleurInterne.getAscenseur().setEstEnMouvement(true);				
			}
			//Si l'ascenseur est a un etage demande, on l'arrete, on ouvre les portes et on supprime les requetes.
			if (isEtageDemande(controleurInterne)) {
				controleurInterne.getAscenseur().setEstEnMouvement(false);
				controleurInterne.getAscenseur().ouvrirPortes();
				for (int i = 0; i < controleurInterne.getRequetes().size(); ++i) {
					if (controleurInterne.getRequetes().get(i).getEtageDemande() == controleurInterne.getAscenseur().getEtage()) {
						controleurInterne.getRequetes().remove(i);
						--i;
					}
				}
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " stops on the " + controleurInterne.getAscenseur().getEtage() + " floor";
			}
			//A ce stade, l'ascenseur se deplace et n'est pas arrive, donc on le fait changer d'etage
			else {
				if (controleurInterne.getRequetes().get(0).getEtageDemande() > controleurInterne.getAscenseur().getEtage()) {
					if (controleurInterne.getRequetes().get(0).getEtageDemande() > controleurInterne.getAscenseur().getEtage() + 1) {
						controleurInterne.getAscenseur().setEtage(controleurInterne.getAscenseur().getEtage() + 1);
					}
					controleurInterne.getAscenseur().setEtage(controleurInterne.getAscenseur().getEtage() + 1);
				}
				else {
					if (controleurInterne.getAscenseur().getEtage() - 2 > 0 && 
							controleurInterne.getRequetes().get(0).getEtageDemande() < controleurInterne.getAscenseur().getEtage() - 1) {
						controleurInterne.getAscenseur().setEtage(controleurInterne.getAscenseur().getEtage() - 1);
					}
					controleurInterne.getAscenseur().setEtage(controleurInterne.getAscenseur().getEtage() - 1);
				}
				return "Elevator " + controleurInterne.getAscenseur().getNumAsc() + " goes through the  " + controleurInterne.getAscenseur().getEtage() + " floor";
			}
		}
		//Si on arrive ici, alors c'est une requete non prise en charge.
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