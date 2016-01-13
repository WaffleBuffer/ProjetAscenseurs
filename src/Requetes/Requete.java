package Requetes;

import Client.Constantes;

/** Description generale d'une requete. Le libelle est donne par les sous-classes {@link RequeteInterne} et {@link RequeteExterne}
 * @author Thomas
 *
 */
public class Requete {
	
	/**Numero de l'etage demande pour les {@link RequeteInterne} et le numero de l'etage du {@link Boutons.BoutonExterne} pour les {@link RequeteExterne}
	 */
	private int etageDemande;
	
	/**Identifiant de requete. Possibilites selon le type de requete:<br>
	 * {@link RequeteInterne}:<br>
	 * -{@link Boutons.BoutonDestination} = "Aller a l'etage"<br>
	 * -{@link Boutons.BoutonStop}        = "Arreter l'ascenseur"<br>
	 * 
	 * {@link RequeteExterne}<br>
	 * -{@link Boutons.BoutonHaut} = "Haut"<br>
	 * -{@link Boutons.BoutonBas}  = "Bas"<br>
	 */
	private int libelle;
	
	/** Constructeur appele par les sous-classes
	 * @param libelle {@link Requete#libelle} identifiant de la requete, fourni par la sous-classe
	 * @param etageDemande {@link Requete#etageDemande} Numero de l'etage demande
	 * @see #libelle
	 * @see #etageDemande
	 */
	protected Requete (int etageDemande, int libelle) {
		this.etageDemande = etageDemande;
		this.libelle = libelle;
	}
	
	
	/** Obtient le {@link Requete#libelle} d'une requete
	 * @return le {@link Requete#libelle} de la requete. Voir l'attribut pour les valeurs possibles
	 * @see #libelle
	 */
	public int getLibelle() {
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
		return "libelle = " + Constantes.toString(libelle) + ", etage Demande = " + etageDemande;
	}
}
