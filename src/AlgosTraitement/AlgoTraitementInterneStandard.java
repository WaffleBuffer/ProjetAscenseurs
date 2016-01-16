package AlgosTraitement;

import Client.Ascenseur;
import Client.Constantes;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Description de le strategies de traitement interne standard.
 * @author Thomas
 */
public class AlgoTraitementInterneStandard implements IAlgoTraitementInterne{

	/**Fonction permettant de traiter les {@link Controleurs.Controleur#requetes} de l'{@link Ascenseur}
	 *  gere par controleurInt pour une iteration de maniere standard.
	 * @param controleurInt le {@link ControleurInterne} sur lequel appliquer l'algorithme.
	 * @return String representant le resultat de l'iteration. Inutilise a ce jour. Etait utilisee pour des tests dans un terminal.
	 */
	public String traiterRequetes(ControleurInterne controleurInt){
		//Si le controleur n'a pas de requete a traiter alors on ignore le traitement
		if (0 == controleurInt.getRequetes().size()) {
			if (controleurInt.getAscenseur().isPortesOuvertes())
				controleurInt.getAscenseur().fermerPortes();
			return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " n'a pas de requete a traiter";
		}
		//Si le bouton stop a ete appuyer, et que l'ascenseur est debloque, alors on le bloque. Sinon on le debloque
		if (controleurInt.getRequetes().get(0).getLibelle() == Constantes.STOP) {
			if (controleurInt.getAscenseur().estBloquer()) {
				controleurInt.getAscenseur().debloque();
				return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " se debloque.";
			}
			else {
				controleurInt.getAscenseur().bloquer();
				return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " se bloque.";
			}
		}
		//Si l'ascenseur est bloque alors on ignore le traitement.
		if (controleurInt.getAscenseur().estBloquer()) {
			return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " est bloque.";
		}
		//Si c'est une requete de mouvement
		if (controleurInt.getRequetes().get(0).getLibelle() == Constantes.DEPLACEMENT || 
				controleurInt.getRequetes().get(0).getLibelle() == Constantes.HAUT || 
						controleurInt.getRequetes().get(0).getLibelle() == Constantes.BAS) {
			
			//Si les portes sont ouvertes alors on les fermes
			if (controleurInt.getAscenseur().isPortesOuvertes()) {
				controleurInt.getAscenseur().fermerPortes();
				return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " ferme ses portes";
			}
			//Si l'ascenseur n'est pas arrive, on le met l'ascenseur en mouvement.
			else if (!controleurInt.getAscenseur().isEstEnMouvement() && !isEtageDemande(controleurInt)) {
				controleurInt.getAscenseur().setEstEnMouvement(true);				
				return "Ascenceur " + controleurInt.getAscenseur().getNumAsc() + " se met en mouvement de l'etage : "
						+ controleurInt.getAscenseur().getEtage() + " a l'etage " 
				+ controleurInt.getRequetes().get(0).getEtageDemande();
			}
			//Si l'ascenseur est arrete, a ce stade, c'est qu'il est arrive donc on ouvre les portes et on supprime la requete
			else if (!controleurInt.getAscenseur().isEstEnMouvement()) {	
				controleurInt.getAscenseur().ouvrirPortes();
				for (int i = 0; i < controleurInt.getRequetes().size(); ++i) {
					if (controleurInt.getRequetes().get(i).getEtageDemande() == controleurInt.getAscenseur().getEtage()) {
						controleurInt.getRequetes().remove(i);
						--i;
					}
				}
				return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " ouvre ses portes";
			}
			//Si l'ascenseur est a un etage demande, on l'arrete.
			else if (isEtageDemande(controleurInt)) {
				controleurInt.getAscenseur().setEstEnMouvement(false);
				return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " s'arrete a l'etage " + controleurInt.getAscenseur().getEtage();
			}
			//A ce stade, l'ascenseur se deplace et n'est pas arrive, donc on le fait changer d'etage
			else {
				if (controleurInt.getRequetes().get(0).getEtageDemande() > controleurInt.getAscenseur().getEtage()) {
					controleurInt.getAscenseur().setEtage(controleurInt.getAscenseur().getEtage() + 1);
				}
				else {
					controleurInt.getAscenseur().setEtage(controleurInt.getAscenseur().getEtage() - 1);
				}
				return "ascenseur " + controleurInt.getAscenseur().getNumAsc() + " passe par l'etage " + controleurInt.getAscenseur().getEtage();
			}
		}
		//Si on arrive ici, alors c'est une requete non prise en charge.
		return "Requete non reconnue";
	}
	
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
	}
}