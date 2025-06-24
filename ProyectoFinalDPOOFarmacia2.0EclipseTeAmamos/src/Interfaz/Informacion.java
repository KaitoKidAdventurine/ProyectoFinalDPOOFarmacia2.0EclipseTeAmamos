package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;

import Utiles.Navegacion;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Informacion extends JFrame 
{

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		try 
		{
			Informacion dialog = new Informacion();
			dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Informacion() 
	{
		setUndecorated(true);
		setBounds(100, 100, 702, 393);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new LineBorder(new Color(0, 100, 0), 3));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	    // Centrar la ventana en la pantalla
	    this.setLocationRelativeTo(null);

		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.BOLD, 26));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 2), "Informacion de usuario de prueba", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 13, 660, 305);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario de Admin: Admin");
		lblNewLabel.setBounds(12, 80, 246, 27);
		panel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblContrasennaDeAdmin = new JLabel("Contrase\u00F1a de admin: 1234");
		lblContrasennaDeAdmin.setBounds(12, 120, 390, 20);
		panel.add(lblContrasennaDeAdmin);
		lblContrasennaDeAdmin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblElUsuarioDe = new JLabel("El usuario normal puedes colocar cualquier nombre: ");
		lblElUsuarioDe.setBounds(12, 153, 665, 30);
		panel.add(lblElUsuarioDe);
		lblElUsuarioDe.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		JLabel lblEjemploOmarSonia = new JLabel("Ejemplo: Omar, Sonia, Rodolfo.");
		lblEjemploOmarSonia.setBounds(12, 196, 322, 40);
		panel.add(lblEjemploOmarSonia);
		lblEjemploOmarSonia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblContrasennaDeUsuario = new JLabel("Contrase\u00F1a de Usuario: 12345678");
		lblContrasennaDeUsuario.setBounds(22, 235, 344, 34);
		panel.add(lblContrasennaDeUsuario);
		lblContrasennaDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		
		JLabel lblRegresarAlLogin = new JLabel("Regresar al Login");
		lblRegresarAlLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Navegacion.registrar("Login", new Login());
				Navegacion.irA("Login");
				dispose();
			}
		});
		lblRegresarAlLogin.setBorder(new CompoundBorder(new LineBorder(new Color(0, 100, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 0))));
		lblRegresarAlLogin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblRegresarAlLogin.setBounds(486, 332, 186, 48);
		contentPanel.add(lblRegresarAlLogin);
	}
}
