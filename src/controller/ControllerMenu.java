package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import control.Controlador;
import juego.Modelo;
import view.MainWindow;
import view.View;
import view.ViewConfig;
import view.ViewJuego;
import view.ViewMenu;
import view.ViewReglas;
import view.ViewTop;

public class ControllerMenu extends Controller {

	public ControllerMenu(Modelo modelo, View view) {
		super(modelo, view);
		if (!(view instanceof ViewMenu)) {
			throw new IllegalArgumentException();
		}
		
		addListeners();
	}
	
	public void addListeners() {
		final ViewMenu viewMenu = (ViewMenu) getView();
		
		viewMenu.addKeyListener(new MiKeyAdapter(viewMenu));
		
		viewMenu.addConfigListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewConfig view = new ViewConfig(getModelo());
				MainWindow.getInstancia().setView(view);
				new ControllerConfig(getModelo(), view);
			}
		});
		
		viewMenu.addJugarListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ViewJuego view = new ViewJuego(getModelo());
				MainWindow.getInstancia().setView(view);
				Controlador controlador = new Controlador(getModelo(), view);
			}
		});
		
		viewMenu.addReglasListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.getInstancia().setView(new ViewReglas(getModelo()));
			}
		});
		
		viewMenu.addTopListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainWindow.getInstancia().setView(new ViewTop(getModelo()));
			}
		});
	}

	class MiKeyAdapter extends KeyAdapter {

		private ViewMenu viewMenu;

		public MiKeyAdapter(ViewMenu viewMenu) {
			this.viewMenu = viewMenu;
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println(e.getKeyCode() + ".");
			switch (e.getKeyCode()) {
			case KeyEvent.VK_UP:
				viewMenu.flechaArriba();
				break;
			case KeyEvent.VK_DOWN:
				viewMenu.flechaAbajo();
				break;
			case KeyEvent.VK_LEFT:
				viewMenu.flechaIzq();
				break;
			case KeyEvent.VK_RIGHT:
				viewMenu.flechaDer();
				break;
			}
		}
	}
	public static void main(String[] args) {
		Modelo modelo = new Modelo();
		View view = new ViewMenu(modelo);
		@SuppressWarnings("unused")
		Controller controlador = new ControllerMenu(modelo, view);
		MainWindow mainWindow = MainWindow.getInstancia();
		mainWindow.setView(view);
		view.actualizarVista();
	}
}
