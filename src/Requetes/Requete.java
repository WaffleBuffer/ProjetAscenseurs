package Requetes;

public class Requete {
/*requetes possibles:
 * Internes:
 * -bouton �tage X -> changerEtage()
 * -bouton d'arr�t d'urgence -> arreter()
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
