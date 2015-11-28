package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		
		viewConfig.addIncListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int nivel = ((ModeloJuego) getModelo()).getNivel();
				if (nivel < 10) {
					((ModeloJuego) getModelo()).setNivel(nivel + 1);
					viewConfig.actualizarVista();
				}
			}
		});
		
		viewConfig.addDecListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int nivel = ((ModeloJuego) getModelo()).getNivel();
				if (nivel > 0) {
					((ModeloJuego) getModelo()).setNivel(nivel - 1);
					viewConfig.actualizarVista();
				}
			}
		});
	}
}
