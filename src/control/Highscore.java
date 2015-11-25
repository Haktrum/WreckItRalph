package control;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import utils.ListHeap;
import utils.Utils;

	/**
	 * Maneja los 10 puntajes m&aacute;s
	 * altos obtenidos en el juego
	 */
public class Highscore {
	/** Arreglo de jugadores con puntaje */
	private ListHeap<Jugador> jugadores;
	/** Cantidad de jugadores inicial*/	
	private int cantJugadores = 0;	
	/** Agrega un jugador al arreglo, ordenado
	 * por su puntaje
	 * @param jugador Jugador con alg&uacute;n puntaje
	 */
	public Highscore(){
		jugadores = leerXML("res/top5.xml");
	}
	private ListHeap<Jugador> leerXML(String archivo) {
		ListHeap<Jugador> res = new ListHeap<Jugador>(Utils.maxJugadores);
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		    Document doc = docBuilder.parse (new File(archivo));
		    doc.getDocumentElement ().normalize ();
		    NodeList jugadoresXml = doc.getElementsByTagName("jugador");
		    int cantidadXml = jugadoresXml.getLength();
		    for(int i=0; i<cantidadXml ; i++){
		    	Node nodo = jugadoresXml.item(i);
		        if(nodo.getNodeType() == Node.ELEMENT_NODE){
		        	Element elemento = (Element) nodo;
		        	Jugador jug = new Jugador(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue());
		        	jug.setPuntaje(Integer.valueOf(elemento.getElementsByTagName("puntaje").item(0).getChildNodes().item(0).getNodeValue()));
		        	res.add(jug);
		        }
		    }
		    return res;
		}catch (SAXParseException err) {
	        System.out.println ("** Parsing error" + ", line " 
	             + err.getLineNumber () + ", uri " + err.getSystemId ());
	        System.out.println(" " + err.getMessage ());
        }catch (SAXException e) {
        	Exception x = e.getException ();
        	((x == null) ? e : x).printStackTrace ();
        }catch (Throwable t) {
        	t.printStackTrace ();
        }
		return null;
    }
	public void agregarJugador(Jugador jugador) {
		jugadores.add(jugador);
		jugadores.extract();
		//if(this.cantJugadores<Utils.maxJugadores)
			//this.cantJugadores++;
		//escribirXml("res/top5.xml");
		
	}
	public ListHeap<Jugador> getJugadores(){
		return this.jugadores;
	}
	
	
	private void escribirXml(String archivo){
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("listado");
			doc.appendChild(rootElement);
			ListHeap<Jugador> aux = jugadores;
			while(!aux.isEmpty()){
				Jugador j = aux.extract();
				Element jug = doc.createElement("jugador");
				rootElement.appendChild(jug);

				Element nombre = doc.createElement("nombre");
				nombre.appendChild(doc.createTextNode(j.getNombre()));
				jug.appendChild(nombre);

				Element puntaje = doc.createElement("puntaje");
				puntaje.appendChild(doc.createTextNode(String.valueOf(j.getPuntaje())));
				jug.appendChild(puntaje);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(archivo));

			transformer.transform(source, result);


		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
	}
}
