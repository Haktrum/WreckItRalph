package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class Loader {
	private static Font font = null;
	private static Image fondo = null;
	private static Image reglas = null;
	private static HashMap<String, Image> imagenes = new HashMap<>();
	
	public static Font getFont() {
		if (font == null) {
			try {
				Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/ui/8-bit.ttf"));
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
			} catch (FontFormatException e) {
			} catch (IOException e) {
			}
			font = new Font("8BIT WONDER", Font.BOLD, 25);
		}
		return font;
	}
	
	public static Image getFondo() {
		if (fondo == null) {
			File file = new File("res/ui/fondo.png");
			try {
				fondo = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fondo;
	}

	public static Image getReglas() {
		if (reglas == null) {
			File file = new File("res/ui/instrucciones.png");
			try {
				reglas = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return reglas;
	}
	
	public static Image getImage(String ruta) {
		if (!imagenes.containsKey(ruta)) {
			File file = new File(ruta);
			try {
				imagenes.put(ruta, ImageIO.read(file));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagenes.get(ruta);
	}
}
