package Client;

/**Creation des Constantes utilisees dans {@link Controleurs.ControleurExterne} et {@link Controleurs.ControleurInterne}.
 * Correspond au {@link Requetes.Requete#libelle} d'une {@link Requetes.Requete}.
 * @author p14005728
 * @see Requetes.Requete
 */
public abstract class Constantes {
	
	/**correspond au {@link Requetes.Requete#libelle} "HAUT" d'une {@link Requetes.Requete}
	 * 
	 */
	public static final int HAUT        = 0;
	
	/**correspond au {@link Requetes.Requete#libelle} "BAS" d'une {@link Requetes.Requete}
	 * 
	 */
	public static final int BAS         = 1;
	
	/**correspond au {@link Requetes.Requete#libelle} "STOP" d'une {@link Requetes.Requete}
	 * 
	 */
	public static final int STOP        = 2;
	
	/**correspond au {@link Requetes.Requete#libelle} "DEPLACEMENT" d'une {@link Requetes.Requete}
	 * 
	 */
	public static final int DEPLACEMENT = 3;
}
