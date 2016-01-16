package Requetes;

import Client.Constantes;

/** Description generale d'une requete. Le libelle est donne par les sous-classes.
 * @author Thomas
 * @see RequeteInterne
 * @see RequeteExterne
 */
public abstract class Requete {
	
	/**Numero de l'etage demande par les {@link RequeteInterne} et le numero de l'etage du {@link Boutons.BoutonExterne} pour les {@link RequeteExterne}
	 */
	private int etageDemande;
	
	/**Identifiant de requete. Possibilitees selon le type de cette Requete:<br>
	 * {@link RequeteInterne}:<br>
	 * -{@link Boutons.BoutonDestination} = {@link Constantes#DEPLACEMENT}<br>
	 * -{@link Boutons.BoutonStop}        = {@link Constantes#STOP}<br>
	 * 
	 * {@link RequeteExterne}<br>
	 * -{@link Boutons.BoutonHaut} = {@link Constantes#HAUT}<br>
	 * -{@link Boutons.BoutonBas}  = {@link Constantes#BAS}<br>
	 * @see Constantes
	 */
	private int libelle;
	
	/** Constructeur appelle par les sous-classes
	 * @param libelle {@link #libelle} de cette requete, fourni par la sous-classe
	 * @param etageDemande {@link #etageDemande} par cette Requete
	 * @see #libelle
	 * @see #etageDemande
	 */
	protected Requete (int etageDemande, int libelle) {
		this.etageDemande = etageDemande;
		this.libelle = libelle;
	}
	
	
	/** Obtient le {@link #libelle} de cette requete
	 * @return le {@link #libelle} de la requete.
	 * @see #libelle
	 */
	public int getLibelle() {
		return libelle;
	}

	/**Obtient l'{@link #etageDemande} demande par cette requete
	 * @return l'{@link #etageDemande} par cette requete.
	 * @see #etageDemande
	 */
	public int getEtageDemande() {
		return etageDemande;
	}

	/** Renvoie l'etat de cette Requete
	 * @see java.lang.Object#toString()
	 * @see Constantes#toString(int)
	 */
	@Override
	public String toString() {
		return "type = " + Constantes.toString(libelle) + ", floor asked = " + etageDemande;
	}
}
