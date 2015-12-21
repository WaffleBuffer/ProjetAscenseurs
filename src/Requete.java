
public class Requete {
/*requetes possibles:
 * Internes:
 * -bouton étage X -> changerEtage()
 * -bouton d'arrêt d'urgence -> arreter()
 * 
 * Externes:
 * -bouton "vers le haut" -> selectionAscenseur()
 * -bouton "vers le bas" -> selectionAscenseur()
 */
	private String libelle;
	private int etageDemande;
	
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getEtage() {
		return etageDemande;
	}

	public void setEtage(int etage) {
		this.etageDemande = etage;
	}
}
