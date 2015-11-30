package utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Loader {
	private static BufferedImage fondo = null;
	private static BufferedImage reglas = null;
	private static HashMap<String, BufferedImage> imagenes = new HashMap<>();
	private static Clip clip;
	private static Clip bgClip;
	
//	public static Font getFont(int size) {
//		if (font == null) {
//			try {
//				Font font = Font.createFont(Font.TRUETYPE_FONT, new File("res/ui/8-bit.ttf"));
//				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
//			} catch (FontFormatException e) {
//			} catch (IOException e) {
//			}
//			font = new Font("8BIT WONDER", Font.BOLD, size);
//		}
//		return font;
//	}
	public static Font getFont(int size){
			try {
				return Font.createFont(Font.TRUETYPE_FONT, new File("res/ui/8-bit.ttf")).deriveFont(Font.PLAIN, size);
			} catch (FontFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return null;
	}
	
	public static BufferedImage getFondo() {
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

	public static BufferedImage getReglas() {
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
	
	public static BufferedImage getImage(String ruta) {
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
	public static synchronized Thread playBgSonido(final String url){
		return  new Thread(new Runnable() {
			public void run() {
				try {
					InputStream is = new BufferedInputStream(new FileInputStream("res/sounds/"+url));
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
					DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
					if(bgClip!=null && bgClip.isOpen())
						bgClip.close();
					bgClip = (Clip) AudioSystem.getLine(info);
					bgClip.open(inputStream);
					bgClip.loop(Clip.LOOP_CONTINUOUSLY);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}
	public static synchronized Thread playSonido(final String url){
		return  new Thread(new Runnable() {
			public void run() {
				try {
					InputStream is = new BufferedInputStream(new FileInputStream("res/sounds/"+url));
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
					DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
					clip = (Clip) AudioSystem.getLine(info);
					clip.open(inputStream);
					clip.start();
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		});
	}
	
}
