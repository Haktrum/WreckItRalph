package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import utils.Modelo;
import view.ViewReglas;

public class ControladorReglas extends Controlador {

	public ControladorReglas(Modelo modelo, ViewReglas view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addListeners() {
		getView().addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_ESCAPE:
					WreckItRalph.getInstancia().crearMenu();
					break;
				}
			}
		});
	}

}
