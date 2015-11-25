package graficos;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JPanel;


public class ContainerTop extends JPanel{
	public ContainerTop(){
		this.setBackground(Color.BLACK);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {20,240,20,240,20,200,40,20};
		gridBagLayout.rowHeights = new int[] {40,40,250,40};
		this.setLayout(gridBagLayout);
	}
}