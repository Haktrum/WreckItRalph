package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import utils.Direccion;
import utils.Evento;
import utils.Modelo;
import utils.Utils;
import utils.Evento.EventoID;
import utils.Vista;
import view.MainWindow;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;
import view.MenuItem.NombreBoton;

import juego.Juego;

public class Controlador implements ActionListener {
	private MainWindow window = null;
	private boolean corriendo = false;
	private Vista vista;
	private Modelo modelo;
	private Timer timerModelo;
	private Timer timerVista;
	private int nivelInicial = 1;

	public Controlador() {
		window = new MainWindow();
		window.setTitulo("Wreck It Ralph");
		modelo = new Menu();
		vista = new ViewMenu();
		setear();
	}

	public void setCorriendo(boolean b) {
		corriendo = b;
	}

	public boolean isCorriendo() {
		return corriendo;
	}
	private void setear(){
		window.setContentPane(vista);
		window.setKeyListener(modelo);
		timerModelo = new Timer(40,modelo);
		timerVista = new Timer(40,vista);
		timerModelo.setInitialDelay(0);
		timerVista.setInitialDelay(0);
		timerModelo.start();
		timerVista.start();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		NombreBoton dest = modelo.getDestino();
		if(dest!=null){
			switch(dest){
			case CONFIG:
				modelo = new Configuracion();
				vista = new ViewConfig();
				break;
			case JUGAR:
				modelo = new Juego(nivelInicial);
				vista = new ViewJuego();
				break;
			case REGLAS:
				modelo = new Reglas();
				vista = new ViewReglas();
				break;
			case TOP:
				modelo = new Highscore();
				vista = new ViewTop();
				break;
			case MENU:
				if(modelo instanceof Configuracion){
					nivelInicial = ((Configuracion) modelo).getNivel();
				}
				modelo = new Menu();
				vista = new ViewMenu();
				break;
			}
			setear();
		}
		vista.setInfo(modelo.getInfo());
	}
}
