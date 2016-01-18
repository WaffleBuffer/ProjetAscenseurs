package Options;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controleurs.ControleurInterne;

/**description de l'OptionMusique, une implementation de {@link Option}
 * @author p14005728
 */
public class OptionMusique extends Option implements Cloneable {
	
	/**Permet de savoir si la fenetre de cette OptionMusique est ouverte.
	 */
	private boolean estFenetreOuverte;

	/**nom de la musique 
	 */
	private String nomMusique = "";
	
	private String[] listeMusiques = {"Tomorrow - Kurt", "MegalovaniaVania - Kurt", "Elevator Nyan Cat - Lucie",
			"Hey Pachuco - Julien", "Ievan Polka - Remi", "Ievan Polka Voacaloid - Lucie",
			"La petite maison dans la prairie - Julien", "Mr Sandman - Remi", "Trololo - Thomas",
			"Victory Day in Moscow - Thomas"};
	
	
	
	private Thread musique;
	
	/**Construit une OptionMusique et initialise {@link Option#controleurInt}.
	 * @param controleurInt le {@link ControleurInterne} a affecter a cette OptionMusique.
	 */
	public OptionMusique (ControleurInterne controleurInt) {
		setControleurInterne(controleurInt);
	}
	
	/**Constructeur par defaut d'une OptionMusique.
	 */
	public OptionMusique () {}
	
	/**permet de lancer la musique, pour l'instant ne fait qu'une notification.
	 */
	private void lancerMusique (String cheminFichier) {
		setEstActivee(true);
		musique = new JouerFichierWAV(cheminFichier);
        musique.start();
		//JOptionPane.showMessageDialog(null, "the music : " + '"' + cheminFichier.substring(0, cheminFichier.length() - 4) + '"' +
		//		" is playing in lift " + getControleurInterne().getAscenseur().getNumAsc());
	}
	
	/**permet d'arreter la musique, pour l'instant ne fait qu'une notification.
	 */
	@SuppressWarnings("deprecation")
	private void arreterMusique(String cheminFichier) {
		setEstActivee(false);
		musique.stop();
		//JOptionPane.showMessageDialog(null, "the music : " + '"' + cheminFichier.substring(0, cheminFichier.length() - 4) + '"' + 
		//		" stopped in lift " + getControleurInterne().getAscenseur().getNumAsc());	
	}
	
	/**
	 * @param cheminRepertoire
	 */
	public void trouverFichiers(final String cheminRepertoire) {
		File repertoire = new File(cheminRepertoire);
		if(!repertoire.exists()){
			System.out.println("Le fichier/répertoire '"+cheminRepertoire+"' n'existe pas");
		}else if(!repertoire.isDirectory()){
			System.out.println("Le chemin '"+cheminRepertoire+"' correspond à un fichier et non à un répertoire");
		}else{
			File[] sousFichiers = repertoire.listFiles();
			listeMusiques = new String[sousFichiers.length];
			for(int i = 0 ; i < sousFichiers.length; i++){
				listeMusiques[i] = sousFichiers[i].getName();
			}
		}
	}
	
	/**
	 * 
	 */
	public void ajouterMusique(){ 
		final JFileChooser chargerMusique = new JFileChooser();
		chargerMusique.setFileFilter(new FileNameExtensionFilter("Fichier WAV", "wav"));
		chargerMusique.setCurrentDirectory(new File("/")); 
		chargerMusique.changeToParentDirectory(); 
		int status = chargerMusique.showOpenDialog(null);
		
		chargerMusique.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("action");
				}
			});
		
		if (status == JFileChooser.APPROVE_OPTION){
			File selectedFile = chargerMusique.getSelectedFile();
			String chemin  = selectedFile.getName();
			selectedFile.renameTo(new File("music/" + chemin));
			activer();
		}

	}
	
	

	/** permet d'activer OptionMusique en utilisant {@link OptionMusique#lancerMusique}.
	 * Cela creer une fenetre qui permet d'interagire avec cette OptionMusique. 
	 */
	@Override
	public void activer() {
		if (!estFenetreOuverte) {//On verifie qu'il n'y ait pas d'autres fenetres d'ouvertes pour cette Option.

			estFenetreOuverte = true; 
			trouverFichiers("music");
			//Creation de la fenetre
			JFrame fenetreMusique = new JFrame("Music");
			fenetreMusique.setLayout(new FlowLayout());
			
			JLabel labelNomMusique = new JLabel("music's name : ");
			fenetreMusique.add(labelNomMusique);
			
			final JComboBox<String> nomMusique = new JComboBox<String>(listeMusiques);
			fenetreMusique.add(nomMusique);
			//nomMusique.setPreferredSize(new Dimension(fenetreMusique.getWidth() / 2 - 10, nomMusique.getFont().getSize() + 8));
			
			JButton lancerArreter = new JButton("Play/Stop");
			lancerArreter.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					setNomMusique(nomMusique.getSelectedItem().toString());
					if (!isEstActivee()) {
						lancerMusique("music/" + nomMusique.getSelectedItem().toString() + ".wav");
					}
					else {
						arreterMusique("music/" + nomMusique.getSelectedItem().toString() + ".wav");
					}
					getControleurInterne().getAscenseur().notifyObservers();
				}
			});
			
			JButton ajouterMusique = new JButton("Add");
			ajouterMusique.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					ajouterMusique();
				}
			});
			
			fenetreMusique.add(lancerArreter);
			fenetreMusique.add(ajouterMusique);
			fenetreMusique.setLocationRelativeTo(null);
			fenetreMusique.pack();
			fenetreMusique.setVisible(true);				
			
			estFenetreOuverte = false;
		}
	}

	/** Permet de connaitre l'etat de cette OptionMusique.
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Music " + (nomMusique != null ? nomMusique : "") + (isEstActivee() ? " activated" : "");
	}

	/**Permet de definire si la fenetre d'interaction est ouverte
	 * @param estFenetreOuverte true si ouverte, false sinon.
	 * @see #estFenetreOuverte
	 */
	public void setEstFenetreOuverte(boolean estFenetreOuverte) {
		this.estFenetreOuverte = estFenetreOuverte;
	}
	
	/**Permet d'obtenir le {@link #nomMusique} de cette OptionMusique
	 * @return le {@link #nomMusique} de cette OptionMusique
	 */
	public String getNomMusique() {
		return nomMusique;
	}

	/**Permet de definir le {@link #nomMusique} de cette OptionMusique
	 * @param nomMusique {@link #nomMusique} a definir pour cette de cette OptionMusique
	 */
	public void setNomMusique(String nomMusique) {
		this.nomMusique = nomMusique;
	}
	
	/** Permet d'obtenir une copie de cette OptionMusique.
	 * @see Options.Option#clone()
	 */
	@Override
	public OptionMusique clone() {
		OptionMusique optionARetournee = new OptionMusique();
		optionARetournee.setEstFenetreOuverte(estFenetreOuverte);
		optionARetournee.setEstActivee(isEstActivee());
		if (this.nomMusique != null) {
			optionARetournee.setNomMusique(getNomMusique());
		}
		if (getControleurInterne() != null) {
			optionARetournee.setControleurInterne(getControleurInterne());
		}
		return optionARetournee;
	}
}
