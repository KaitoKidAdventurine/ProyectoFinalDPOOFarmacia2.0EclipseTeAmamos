package Utiles;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class UtilesInterfaz {

	public static void ajustarImagen(JLabel lbl, String ruta) {
        try {
            // Cargar la imagen original
            ImageIcon imagenOriginal = new ImageIcon(ruta);
            
            // Escalar la imagen al tamaño del JLabel manteniendo calidad
            Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(
                lbl.getWidth(), 
                lbl.getHeight(),
                Image.SCALE_SMOOTH); // Usar SCALE_SMOOTH para mejor calidad
            
            // Crear nuevo ImageIcon y asignar al JLabel
            ImageIcon icono = new ImageIcon(imagenEscalada);
            lbl.setIcon(icono);
            
            // Forzar actualización
            lbl.revalidate();
            lbl.repaint();
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            // Puedes mostrar un mensaje de error o una imagen por defecto aquí
        }
    }
}
