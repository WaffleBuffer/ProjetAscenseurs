package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Boutons.BoutonInterne;
import Client.Ascenseur;
import Client.Batiment;

public class FenetreBatiment extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	public FenetreBatiment (Batiment bat, Ascenseur asc) {
		
		//layout de la fenêtre
		BorderLayout mainLayout = new BorderLayout();	//création du layout principal
		this.setLayout(mainLayout);						//il est associé à la fenetre principale
		
		//répartition des panels
		JPanel batPanel = new JPanel();					//création d'un panel qui contiendra les infos sur le batiment
		JPanel ascPanel = new JPanel();					//création d'un panel qui contiendra les infos sur l'ascenseur
		this.add(batPanel, BorderLayout.WEST);			//la partie batiment sera a gauche de la fenetre
		this.add(ascPanel, BorderLayout.EAST);			//la partie ascenseur sera à droite de la fenetre
		
		//layout partie batiment
		LayoutManager batLayout = new BoxLayout(batPanel, BoxLayout.Y_AXIS);	//création layout qui align verticalement les éléments relatifs au batiment
		batPanel.setLayout(batLayout);											//il est associé au panel du batiment
		
		//layout partie ascenseur
		LayoutManager ascLayout = new BoxLayout(ascPanel, BoxLayout.Y_AXIS);	//layout identique au précédent mais avec l'ascenseur
		ascPanel.setLayout(ascLayout);											//il est associé au panel de l'ascenseur
		ascPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));		//ajout d'un padding à ce panel 
		
		//partie batiment
		/*JLabel nomBat = new JLabel(bat.getNom());								//création label contenant le nom du batiment
		JLabel nbEtagesBat = new JLabel(Integer.toString(bat.getNbEtages()));	//création label contenant le nombre d'étages du batiment
		batPanel.add(nomBat);													
		batPanel.add(nbEtagesBat);												//ajout des labels dans le panel batiment
		batPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));		//ajout d'un padding à ce panel identique au précédent
		*/
		//partie ascenseur
		for (BoutonInterne i : asc.getListeBoutons()){							//ajout de chaque bouton de l'ascenseur au panel via des label
			ascPanel.add(new JLabel(i.getLibelle()));
		}
		
		final JProgressBar barre_progression = new JProgressBar(JProgressBar.VERTICAL, 0, bat.getNbEtages());//création barre de progression verticale pour représenter l'emplacement de l'ascenseur
		barre_progression.setValue(asc.getEtage());			//elle prend pour valeur l'emplacement de l'ascenseur
		barre_progression.setForeground(Color.BLUE);		//changement de la couleur de la barre pour une meilleure visibilité
		this.add(barre_progression, BorderLayout.CENTER);	//on l'ajoute au centre de la fenetre
		
		//boutons		
		JPanel boutonPanel = new JPanel();					//création panel qui contiendra les boutons cliquables
		BoxLayout boutonLayout = new BoxLayout(boutonPanel, BoxLayout.Y_AXIS);	//création  boxlayout vertical
		this.add(boutonPanel, BorderLayout.SOUTH);								//ajout du panel en bas de la fenetre
		
		//création en boucle des boutons qui ont pour effet de changer la valeur de la barre au clic
		for (int i = 0; i < asc.getListeBoutons().size(); ++i){
			if (i == asc.getListeBoutons().size() - 1)
			{
				JButton boutonStop = new JButton("STOP");
				boutonPanel.add(boutonStop, BorderLayout.SOUTH);
			}
			else
			{
				final int j = i;
				JButton boutonDestination = new JButton(Integer.toString(i));
				boutonPanel.add(boutonDestination, BorderLayout.SOUTH);
				boutonDestination.addActionListener(new ActionListener() {
	
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
							barre_progression.setValue(j);
					}});
			}
		}
		
		//reglages de la fenêtre
		this.setTitle(bat.getNom() + " " + bat.getNbEtages() + " étages");	//Titre de la fenêtre 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);			//le programme s'arrete quand la fenetre se ferme
		this.setSize(400, 500);									//taille de la fenêtre fixe
		this.setLocationRelativeTo(null);						//la fenêtre apparait au centre de l'écran
		this.setVisible(true);									//la fenêtre apparaît
		
	}
}
