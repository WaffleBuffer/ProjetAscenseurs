package IHM;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import Client.Batiment;
import Controleurs.ControleurInterne;
import Requetes.Requete;

/**Vue de la liste des {@link Requete} a traiter par le systeme concernant le {{@link #batiment} de cette FenetreRequetes
 * @author dark1wador
 *
 */
public class FenetreRequetes extends JFrame implements Observer{

	/**{@link JTextArea} dans laquelle s'ecrie la liste des {@link Requete}
	 */
	private JTextArea AffichageRequetesInternes = new JTextArea();
	
	private JTextArea AffichageRequetesExternes = new JTextArea();
	
	/**{@link Batiment} correspondant a cette vue
	 */
	private Batiment batiment;
	
	/**Vient de {@link JFrame}
	 * @see JFrame
	 */
	private static final long serialVersionUID = 0L;

	/**Construit une FenetreRequetes correspondant a un {@link Batiment}
	 * @param batiment le {@link Batiment} correspondant a cette FenetreRequetes
	 */
	public FenetreRequetes (Batiment batiment) {
		this.batiment = batiment;						// le Batiment associe a cette fenetre
		AffichageRequetesInternes.setEditable(false);	// on empeche l'utilisateur d'ecrire dans la fenetre de texte
		
		this.setLayout(new GridLayout(2, 1, 0, 10));	// il est associe a la fenetre principale
		
		JPanel panelRequeteInterne = new JPanel();		// le JPanel contenant le JTextArea
		panelRequeteInterne.setBorder(BorderFactory.createTitledBorder("Internal queries"));
		panelRequeteInterne.setLayout(new GridLayout());
		panelRequeteInterne.add(AffichageRequetesInternes);		// on l'ajoute
		JScrollPane scrollRequeteInterne = new JScrollPane(panelRequeteInterne);
		this.add(scrollRequeteInterne);
		
		JPanel panelRequeteExterne = new JPanel();
		panelRequeteExterne.setBorder(BorderFactory.createTitledBorder("External queries"));
		panelRequeteExterne.setLayout(new GridLayout());
		panelRequeteExterne.add(AffichageRequetesExternes);
		JScrollPane scrollRequeteExterne = new JScrollPane(panelRequeteExterne);
		this.add(scrollRequeteExterne);
		
		// on cree le JPanel principale auquel on affecte un layout
		//JPanel panel = new JPanel(new GridLayout(0, 1));			
		
		// on met une JScrollBar en cas de besoin
		//JScrollPane scrollHistorique = new JScrollPane(panelRequeteInterne);	
		
		// Lors d'un changement de taille de la fenetre, on reactualise l'affichage
		this.addComponentListener(new ComponentAdapter ()
		{
			public void componentResized (ComponentEvent event) {
				FenetreRequetes c = (FenetreRequetes)event.getSource();
				c.update(null, null);
			}
		});
		
		this.setTitle(batiment.getNom() + " (queries)");		//Titre de la fenetre 
		this.setSize(new Dimension(400, 500));					//taille de la fenetre fixe
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		// la fenetre apparait au milieu, a droite de l'ecran
		this.setLocation(width - this.getWidth(), height/2 - this.getHeight()/2);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);									//la fenetre apparaît
	}

	/**Actualise le text dans le {@link #AffichageRequetesInternes} de cette FenetreRequetes
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Reinitialisation des JTextArea
		AffichageRequetesInternes.setText("");
		AffichageRequetesExternes.setText("");
		
		// Affichage des RequeteExterne du Batiment
		for (Requete i : batiment.getControleurExt().getRequetes()) {
			AffichageRequetesExternes.append(i.toString() + "\n");
		}
	
		String tiretsAffichage = "";	// variable d'affichage
		String egalesAffichage = "";	// variable d'affichage
		// configuration des variables d'affichage sela la taille de la JFrame
		for (int i = 0; i < this.getSize().width / 11; ++i) {
			tiretsAffichage += "-";
			if (0 == i % 2) {
				egalesAffichage += "=";
			}
		}
		
		// Affichage des RequeteInterne du Batiment
		//Parcour des ControleurInterne du Batiment
		for (ControleurInterne i : batiment.getControleursInternes()) {
				if (0 != i.getNumberOfRequete()) {
					AffichageRequetesInternes.append("\n====" + egalesAffichage + 
							"[Lift " + i.getAscenseur().getNumAsc() + "]" + egalesAffichage +"====\n");
					for (Requete j : i.getRequetes()) {		
						AffichageRequetesInternes.append("\n" + j.toString());
					}
	
					AffichageRequetesInternes.append("\n====" + egalesAffichage  + egalesAffichage + "========\n");
				}
		}
	}
}
