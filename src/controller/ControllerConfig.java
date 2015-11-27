package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import juego.Modelo;
import view.ViewConfig;

public class ControllerConfig extends Controller {

	public ControllerConfig(Modelo modelo, ViewConfig view) {
		super(modelo, view);
		addListeners();
	}

	public void addListeners() {
		final ViewConfig viewConfig = (ViewConfig) getView();
		
		viewConfig.addKeyListener(new BackKeyListener());
		
		viewConfig.addIncListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int nivel = getModelo().getNivel();
				if (nivel < 10) {
					getModelo().setNivel(nivel + 1);
					viewConfig.actualizarVista();
				}
			}
		});
		
		viewConfig.addDecListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int nivel = getModelo().getNivel();
				if (nivel > 0) {
					getModelo().setNivel(nivel - 1);
					viewConfig.actualizarVista();
				}
			}
		});
	}
}
