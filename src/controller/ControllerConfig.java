package controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import modelos.ModeloJuego;

import view.ViewConfig;

public class ControllerConfig extends Controller {

	public ControllerConfig(ModeloJuego modelo, ViewConfig view) {
		super(modelo, view);
		addListeners();
	}

	public void addListeners() {
		final ViewConfig viewConfig = (ViewConfig) getView();
		
		viewConfig.addKeyListener(new BackKeyListener());
		
		viewConfig.addIncListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_RIGHT){
					int nivel = ((ModeloJuego) getModelo()).getNivel();
					if (nivel < 10) {
						((ModeloJuego) getModelo()).setNivel(nivel + 1);
						viewConfig.actualizarVista();
					}
				}
			}
		});
		
		viewConfig.addDecListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_LEFT){
					int nivel = ((ModeloJuego) getModelo()).getNivel();
					if (nivel > 1) {
						((ModeloJuego) getModelo()).setNivel(nivel - 1);
						viewConfig.actualizarVista();
					}
				}
			}
		});
	}
}
