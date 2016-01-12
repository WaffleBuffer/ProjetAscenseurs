package Options;

public class OptionMusique implements IOption {

	private String nomMusique;
	
	public OptionMusique (String nomMusique) {
		this.nomMusique = nomMusique;
	}
	
	private void lancerMusique () {
		System.out.println(nomMusique + " se lance");
	}

	@Override
	public void activer() {
		// TODO Auto-generated method stub
		this.lancerMusique();
	}
}
