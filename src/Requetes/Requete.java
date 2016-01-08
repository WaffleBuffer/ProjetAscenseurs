package Requetes;

public class Requete {
/*requetes possibles:
 * Internes:
 * -bouton etage -> Allez a l'etage
 * -bouton d'arr�t d'urgence -> arreter()
 * 
 * Externes:
 * -bouton "vers le haut" -> selectionAscenseur()
 * -bouton "vers le bas" -> selectionAscenseur()
 */
	//Identifiant de requete
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

	@Override
	public String toString() {
		return "Requete [libelle=" + libelle + ", etageDemande=" + etageDemande
				+ "]";
	}
}
