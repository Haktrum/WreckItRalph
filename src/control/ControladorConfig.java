package control;

import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import modelo.ModeloConfig;
import utils.Utils;
import view.ViewConfig;

public class ControladorConfig extends Controlador {

	public ControladorConfig(ModeloConfig modelo, ViewConfig view) {
		super(modelo, view);
		addListeners();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void addListeners() {
		getView().addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				ModeloConfig modelo = (ModeloConfig) getModelo();
				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					modelo.disminuirNivel();
					getView().actualizarVista();
					break;
				case KeyEvent.VK_RIGHT:
					modelo.aumentarNivel();
					getView().actualizarVista();
					break;
				case KeyEvent.VK_ENTER:
					Utils.nivelActual = modelo.getNivel();
				case KeyEvent.VK_ESCAPE:	//la falta del break es intencional
					WreckItRalph.getInstancia().crearMenu();
					break;
				}
			}
		});

	}

}
