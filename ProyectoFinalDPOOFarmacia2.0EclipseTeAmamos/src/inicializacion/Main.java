package inicializacion;

import java.awt.EventQueue;
import java.util.Locale;
import javax.swing.JOptionPane;
import Interfaz.Login;
import LogicaUtiles.Validaciones;

public class Main {
    public static void main(String[] args) {
        // Configurar localizaci�n
        Locale.setDefault(new Locale("es", "ES"));
        
        try {
            
            // Iniciar la interfaz gr�fica
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    try {
                        // Mostrar pantalla de login
                        Login login = new Login();
                        login.setVisible(true);
                        
                        // Inicializar datos en segundo plano (opcional)
                        inicializarHilo();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, 
                            "Error al iniciar la aplicaci�n: " + e.getMessage(), 
                            "Error", 
                            JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, 
                e.getMessage(), 
                "Error Fatal", 
                JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
    
    private static void inicializarHilo() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    // Inicializar datos de la base de datos
                    InicializadoraBaseDatos.inicializarTodo();
                } catch(Exception e) {
                    e.printStackTrace();
                    // No mostramos error al usuario para no interrumpir su experiencia
                }
            }
        }).start();
    }
}