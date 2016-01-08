package Requetes;

public class Requete {
/*libelle possibles:
 * Internes:
 * -bouton etage -> "Allez a l'etage"
 * -bouton stop  -> "Arreter l'ascenseur"
 * 
 * Externes:
 * -bouton haut -> "Haut"
 * -bouton bas  -> "Bas"
 */
	//Identifiant de requete
	private String libelle;
	//Numero de l'etage demande pour les requetes internes et le numero de l'etage du bouton pour
	//les requetes externes
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
