package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class FenetreRequetes extends JFrame{

	private JLabel requetes = new JLabel();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4236819230726511089L;

	public FenetreRequetes () {
		this.setLayout(new GridLayout(1, 2));	//il est associe a la fenetre principale
		JPanel panelRequete = new JPanel();
		panelRequete.setBackground(Color.WHITE);
		JPanel panel = new JPanel(new GridLayout(0, 1));
		JScrollPane scrollHistorique = new JScrollPane(panelRequete);
		panel.setBorder(BorderFactory.createTitledBorder(null, "Liste de requetes", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION));
		panel.add(scrollHistorique);
		this.add(panel);
		
		this.setTitle("Liste des requetes");			        //Titre de la fenetre 
		this.setResizable(false);								//la fenetre de configuration n'a pas besoin d'etre redimensionner
		this.setSize(new Dimension(300, 300));					//taille de la fenetre fixe
		this.setLocationRelativeTo(null);						//la fenetre apparait au centre de l'ecran
		this.setVisible(true);									//la fenetre apparaît
	}
	
	public void setText (String text) {
		requetes.setText(text);
	}
}
