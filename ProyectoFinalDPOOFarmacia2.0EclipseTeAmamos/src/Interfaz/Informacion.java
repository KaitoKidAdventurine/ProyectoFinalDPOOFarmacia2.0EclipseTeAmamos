package Interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Informacion extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Informacion dialog = new Informacion();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Informacion() {
		setUndecorated(true);
		setBounds(100, 100, 476, 352);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new LineBorder(new Color(152, 251, 152), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	    // Centrar la ventana en la pantalla
	    this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setFocusable(false);
		panel.setFocusTraversalKeysEnabled(false);
		panel.setEnabled(false);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		panel.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(152, 251, 152), 2)));
		panel.setBounds(135, 260, 174, 59);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblRegresarAlLogin = new JLabel("Regresar al Login");
		lblRegresarAlLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblRegresarAlLogin.setBounds(12, 13, 150, 33);
		panel.add(lblRegresarAlLogin);
		
		JTextPane txtpnParaIniciarSesion = new JTextPane();
		txtpnParaIniciarSesion.setEditable(false);
		txtpnParaIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 20));
		txtpnParaIniciarSesion.setText("Para iniciar sesion en el programa utilice los siguientes usuarios de prueba :\r\n\r\n-Administrador:                 -Usuario\r\nNombre: Admin                Nombre : User\r\nContrase\u00F1a:1234               Contrase\u00F1a : 1234\r\n");
		txtpnParaIniciarSesion.setBounds(12, 54, 443, 181);
		contentPanel.add(txtpnParaIniciarSesion);
		
		JLabel lblInformacinDeUsuarios = new JLabel("Informaci\u00F3n de Usuarios de Prueba");
		lblInformacinDeUsuarios.setFont(new Font("Tahoma", Font.BOLD, 21));
		lblInformacinDeUsuarios.setBounds(47, 13, 378, 39);
		contentPanel.add(lblInformacinDeUsuarios);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
