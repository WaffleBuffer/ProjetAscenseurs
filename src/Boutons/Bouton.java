package Boutons;

public class Bouton {
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

	public Bouton(String libelle) {
		
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Bouton [libelle=" + libelle + "]";
	}
}
