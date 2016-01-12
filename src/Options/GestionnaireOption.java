package Options;

import java.util.ArrayList;

public class GestionnaireOption {

	private ArrayList<IOption> options = new ArrayList<IOption>();

	public ArrayList<IOption> getOptions() {
		return options;
	}
	
	public void addOption (IOption option) {
		options.add(option);
	}
	
	public IOption getOption (int i) {
		return options.get(i);
	}

	public void activerOption (int numOption) {
		//traitement par defaut
		options.get(numOption).activer();
	}
}
