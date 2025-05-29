package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.SystemColor;

import javax.swing.border.BevelBorder;
import javax.swing.JPasswordField;
import javax.swing.border.CompoundBorder;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JLabel fondo;
	private JTextField txtIngreseSuNombre;
	private JPasswordField passwordField;
	private JTextField txtEntrar;
	private int posX;
	private int posY;
	private Point e;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setUndecorated(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel barra = new JPanel();
		barra.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Calcular nueva posición
                int newX = getLocation().x + e.getX() - posX;
                int newY = getLocation().y + e.getY() - posY;
                
                // Mover la ventana
                setLocation(newX, newY);
			}
		});
		barra.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Guardar posición inicial del mouse
                posX = e.getX();
                posY = e.getY();
			}
		});
		barra.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		barra.setBackground(new Color(61,194,68));
		barra.setBounds(-12, -9, 773, 50);
		contentPane.add(barra);
		barra.setLayout(null);
		
		final JLabel cruz = new JLabel("");
		cruz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ajustarImagen(cruz, "src/iconos/exit1.png");
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				ajustarImagen(cruz, "src/iconos/exit0.png");
			}
		});
		cruz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cruz.setBounds(721, 13, 40, 37);
		barra.add(cruz);
		this.ajustarImagen(cruz, "src/iconos/exit0.png");
		
		JLabel minimizar = new JLabel("");
		minimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizar.setBounds(681, 13, 40, 37);
		barra.add(minimizar);
		this.setLocationRelativeTo(null);
		this.ajustarImagen(minimizar, "src/iconos/minimize0.png");
		
		JLabel encabezado = new JLabel("Gesti\u00F3n de procesos en las farmacias ");
		encabezado.setFont(new Font("Tahoma", Font.BOLD, 25));
		encabezado.setBounds(272, 90, 471, 68);
		contentPane.add(encabezado);
		
		JLabel iniciarseccion = new JLabel("Iniciar sesi\u00F3n");
		iniciarseccion.setFont(new Font("Tahoma", Font.BOLD, 22));
		iniciarseccion.setBounds(417, 194, 203, 41);
		contentPane.add(iniciarseccion);
		
		JLabel usuario = new JLabel("Usuario");
		usuario.setFont(new Font("Tahoma", Font.BOLD, 19));
		usuario.setBounds(297, 237, 140, 35);
		contentPane.add(usuario);
		
		txtIngreseSuNombre = new JTextField();
		
		txtIngreseSuNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (txtIngreseSuNombre.getText().equals("Ingrese su nombre de usuario")) {
					txtIngreseSuNombre.setText("");
					txtIngreseSuNombre.setForeground(Color.BLACK);
                }
				if(String.valueOf(passwordField.getPassword()).isEmpty()){
					passwordField.setText("••••••••");
					passwordField.setForeground(Color.GRAY);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		txtIngreseSuNombre.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtIngreseSuNombre.setBackground(SystemColor.controlHighlight);
		txtIngreseSuNombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtIngreseSuNombre.setText("Ingrese su nombre de usuario");
		txtIngreseSuNombre.setBounds(295, 271, 391, 35);
		contentPane.add(txtIngreseSuNombre);
		txtIngreseSuNombre.setColumns(10);
		
		JLabel contraseña = new JLabel("Contrase\u00F1a");
		contraseña.setFont(new Font("Tahoma", Font.BOLD, 19));
		contraseña.setBounds(297, 329, 140, 35);
		contentPane.add(contraseña);
		
		passwordField = new JPasswordField();
		
		passwordField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (String.valueOf(passwordField.getPassword()).equals("••••••••")) {
					passwordField.setText("");
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar('•');
				}
				if(txtIngreseSuNombre.getText().isEmpty()){
					txtIngreseSuNombre.setText("Ingrese su nombre de usuario");
					txtIngreseSuNombre.setForeground(Color.GRAY);
                }
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
		passwordField.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		passwordField.setBackground(SystemColor.controlHighlight);
		passwordField.setText("••••••••");
		passwordField.setBounds(297, 364, 389, 35);
		contentPane.add(passwordField);
		
		JLabel lblBienvenidoAlSistema = new JLabel("Bienvenido al Sistema de");
		lblBienvenidoAlSistema.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblBienvenidoAlSistema.setBounds(348, 68, 323, 35);
		contentPane.add(lblBienvenidoAlSistema);
		
		JPanel entrar = new JPanel();
		entrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		entrar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		entrar.setBackground(new Color(61,194,68));
		entrar.setName("");
		entrar.setBounds(532, 412, 177, 50);
		contentPane.add(entrar);
		entrar.setLayout(null);
		
		txtEntrar = new JTextField();
		txtEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		txtEntrar.setEditable(false);
		txtEntrar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtEntrar.setOpaque(false);
		txtEntrar.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtEntrar.setText("      Entrar");
		txtEntrar.setBounds(0, 0, 177, 50);
		entrar.add(txtEntrar);
		txtEntrar.setColumns(10);
		
		JLabel fondo_1 = new JLabel("");
		fondo_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		fondo_1.setBounds(0, 35, 761, 456);
		contentPane.add(fondo_1);
		this.ajustarImagen(fondo_1, "src/imagenes/fondo con logo del minsap.png");
		this.setLocationRelativeTo(null);
	}
	
	public void ajustarImagen(JLabel lbl, String ruta) {
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
