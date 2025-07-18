package Utiles;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Navegacion 
{
	private static HashMap<String, JDialog> ventanasDialogs = new HashMap<String , JDialog>();
	private static HashMap<String, JFrame> ventanas = new HashMap<String, JFrame>();
	private static String ventanaActual;

	
	//  Registra una ventana en el sistema de navegación
	
	public static void registrarJDialog(String nombre, JDialog ventana) 
	{
	    ventanasDialogs.put(nombre, ventana);
	}
	 
	public static void registrar(String nombre, JFrame ventana) 
	{
		ventanas.put(nombre, ventana);
	}

	
	 // Obtiene una ventana registrada
	
	// Obtiene un JDialog registrado
	public static JDialog obtenerDialog(String nombre) {
	    JDialog dialog = ventanasDialogs.get(nombre);
	    if (dialog == null) {
	        throw new IllegalArgumentException("Diálogo no registrado: " + nombre);
	    }
	    return dialog;
	}
	 
	//Obtiene un Jframe registrado
	public static JFrame obtenerVentana(String nombre) 
	{
		JFrame ventana = ventanas.get(nombre);
		if (ventana == null) 
		{
			throw new IllegalArgumentException("Ventana no registrada: " + nombre);
		}
		return ventana;
	}

	
	  // Navega a la ventana especificada
	
	//Navega al JDialog especificado
	public static void irADialog(String nombre) {
	    obtenerDialog(nombre).setVisible(true);
	    if (ventanaActual != null) {
	        obtenerDialog(ventanaActual).setVisible(false);
	    }
	    ventanaActual = nombre;
	}
	
	//Navega a JFrame especificado 
	public static void irA(String nombre) 
	{
		obtenerVentana(nombre).setVisible(true);
		if (ventanaActual != null) 
		{
			obtenerVentana(ventanaActual).setVisible(false);
		}
		
		ventanaActual = nombre;
	}

		
}
