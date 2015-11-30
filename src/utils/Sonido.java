package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

public class Sonido extends Thread implements Runnable{
	private boolean loop;
	private Clip clip;
	private boolean corriendo = true;
	public Sonido(String url,boolean loop){
		this.loop = loop;
		try {
			InputStream is = new BufferedInputStream(new FileInputStream("res/sounds/"+url));
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(is);
			DataLine.Info info = new DataLine.Info(Clip.class, inputStream.getFormat());
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(inputStream);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public Sonido(String url){
		this(url,false);
	}
	public void play(){
		this.run();
	}
	public void parar(){
		clip.stop();
		corriendo = false;
	}
	@Override
	public void run(){
		while(corriendo){
			boolean primeraVez = true;
			while(primeraVez || loop){
				primeraVez = false;
				clip.start();
			}
		}
	}
}