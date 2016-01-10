package Requetes;

/** Description générale d'une requete. Le libelle est donne par les sous-classe {@link RequeteInterne} et {@link RequeteExterne}
 * @author Thomas
 *
 */
public class Requete {
	
	/**Numero de l'etage demande pour les {@link RequeteInterne} et le numero de l'etage du bouton pour les {@link RequeteExterne}
	 */
	private int etageDemande;
	
	/**Identifiant de requete. Possibilites selon le type de requete:<br>
	 * {@link RequeteInterne}:<br>
	 * -{@link BoutonDestination} = "Allez a l'etage"<br>
	 * -{@link BoutonStop}        = "Arreter l'ascenseur"<br>
	 * 
	 * {@link RequeteExterne}<br>
	 * -{@link BoutonHaut} = "Haut"<br>
	 * -{@link BoutonBas}  = "Bas"<br>
	 */
	private String libelle;
	
	/** Constructeur appele par les sous-classes
	 * @param libelle {@link Requete#libelle} identifiant de la requete, fournis par la sous-classe
	 * @param etageDemande {@link Requete#etageDemande} Numero de l'etage demande
	 * @see #libelle
	 * @see #etageDemande
	 */
	protected Requete (int etageDemande, String libelle) {
		this.etageDemande = etageDemande;
		this.libelle = libelle;
	}
	
	
	/** Obtient le {@link Requete#libelle} d'une requete
	 * @return le {@link Requete#libelle} de la requete. Voire l'attribut pour les valeurs possibles
	 * @see #libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**Obtient {@link Requete#etageDemande} demande par cette requete
	 * @return {@link Requete#etageDemande} par cette requete.
	 * @see #etageDemande
	 */
	public int getEtageDemande() {
		return etageDemande;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Requete [libelle=" + libelle + ", etageDemande=" + etageDemande
				+ "]";
	}
}
