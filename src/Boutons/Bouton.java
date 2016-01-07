package Boutons;

public class Bouton {
	private String libelle;

	public Bouton(String libelle) {
		super();
		this.libelle = libelle;
	}

	@Override
	public String toString() {
		return "Bouton [libelle=" + libelle + "]";
	}
}
