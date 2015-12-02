package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import modelo.ModeloJuego;
import modelo.ModeloJuego.Estado;
import utils.Direccion;
import view.ViewJuego;

public class ControladorJuego extends Controlador {

	public class MiKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent arg0) {
			ModeloJuego modelo = (ModeloJuego) getModelo();
			switch (arg0.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				modelo.moverFelix(Direccion.IZQUIERDA);
				break;
			case KeyEvent.VK_RIGHT:
				modelo.moverFelix(Direccion.DERECHA);
				break;
			case KeyEvent.VK_UP:
				modelo.moverFelix(Direccion.ARRIBA);
				break;
			case KeyEvent.VK_DOWN:
				modelo.moverFelix(Direccion.ABAJO);
				break;
			case KeyEvent.VK_ESCAPE:
				WreckItRalph.getInstancia().crearMenu();
				break;
			case KeyEvent.VK_SPACE:
				modelo.martillar();
				break;
			case KeyEvent.VK_P:
				modelo.pausar();
				break;
			}
		}
	}

	private Timer timerView;
	private Timer timerController;

	public ControladorJuego(ModeloJuego modelo, ViewJuego vista) {
		super(modelo, vista);
		setear();
		addListeners();
	}

	public boolean isCorriendo() {
		return ((ModeloJuego) getModelo()).isCorriendo();
	}

	private void setear() {
		timerView = new Timer(40, (ActionListener) getView());
		timerController = new Timer(40, this);
		timerView.start();
		timerController.start();
	}
	
	@Override
	public void addListeners() {
		getView().addKeyListener(new MiKeyListener());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ModeloJuego modeloJuego = (ModeloJuego) getModelo();
		modeloJuego.run();
		if (modeloJuego.getEstado() == Estado.TERMINADO) {
			WreckItRalph.getInstancia().crearAgregarJugador(modeloJuego.getPuntaje());
		}
	}

}
