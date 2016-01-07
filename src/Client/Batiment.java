package Client;

public class Batiment {
	private String nom;
	private int nbEtages;
	
	public Batiment(String nom, int nbEtages) {
		super();
		this.nom = nom;
		this.nbEtages = nbEtages;
	}

	public String getNom() {
		return nom;
	}

	public int getNbEtages() {
		return nbEtages;
	}

	@Override
	public String toString() {
		return "Batiment [nom=" + nom + ", nbEtages=" + nbEtages + "]";
	}

}
