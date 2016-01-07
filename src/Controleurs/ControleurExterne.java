package Controleurs;

public class ControleurExterne {

	final private static ControleurExterne singleton = new ControleurExterne ();
	
	private ControleurExterne () {}
	
	public ControleurExterne getControleurExterne() {
		return singleton;
	}
}
