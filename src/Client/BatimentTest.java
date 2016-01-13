package Client;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BatimentTest {

	private Batiment batiment;
	
	@BeforeClass
	public static void setUpClasse() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownClass() throws Exception {
		
	}
	
	@Before
	public void setUp() throws Exception {
		this.batiment = new Batiment("Hotel", 5, 2);
		System.out.println("==============================================");
		System.out.print("| Debut du test : ");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testRequetesInterne() {
		System.out.println("Test des Requetes Interne |");
		System.out.println("==============================================");
		System.out.println("");
		
		this.batiment.appuyerBoutonAscenseur(1, 3);
		this.batiment.appuyerBoutonAscenseur(2, 5);
		for (int i = 1; i <= 9; ++i) {
			System.out.println("Iteration " + i);
			System.out.println(this.batiment.traiterControleurs());
		}
		assertTrue(this.batiment.getAscenseur(1).getEtage() == 3);
		assertTrue(this.batiment.getAscenseur(2).getEtage() == 5);
		
		System.out.println("");
	}
	
	@Test
	public void testRequeteExterne() {
		System.out.println("Test des Requetes Externes |");
		System.out.println("==============================================");
		System.out.println("");
		
		this.batiment.appuyerBoutonEtage(0); //etage 0 haut
		this.batiment.appuyerBoutonEtage(2); //etage 1 bas
		this.batiment.appuyerBoutonEtage(8); //etage 4 haut
		this.batiment.appuyerBoutonEtage(0); //etage 0 haut
		
		this.batiment.getControleurExt().traiterRequetes();
		//System.out.println(this.batiment.getControleursInterne().toString());
		
		for (int i = 1; i <= 10; ++i) {
			System.out.println("Iteration " + i);
			System.out.println(this.batiment.traiterControleurs());
		}
		
		assertTrue(this.batiment.getAscenseur(1).getEtage() == 4);
		assertTrue(this.batiment.getAscenseur(2).getEtage() == 0);
	}

}
