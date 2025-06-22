package Utiles;
import java.util.HashMap;
import javax.swing.JFrame;

public class Navegacion 
{
	private static HashMap<String, JFrame> ventanas = new HashMap<String, JFrame>();
	private static String ventanaActual;

	
	//  Registra una ventana en el sistema de navegación
	 
	public static void registrar(String nombre, JFrame ventana) 
	{
		ventanas.put(nombre, ventana);
	}

	
	 // Obtiene una ventana registrada
	 
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
