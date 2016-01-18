package IHM;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import Client.Ascenseur;

public class LabelAscenseurCellRenderer extends JLabel implements ListCellRenderer<Ascenseur> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LabelAscenseurCellRenderer () {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Ascenseur> list, Ascenseur value, int index,
			boolean isSelected, boolean cellHasFocus) {
		
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		if (cellHasFocus) {
			setBorder(list.getBorder());
		}
		else {
			setBorder(null);
		}
		
        this.setText("Lift " + value.getNumAsc());
        return this;
	}

}
