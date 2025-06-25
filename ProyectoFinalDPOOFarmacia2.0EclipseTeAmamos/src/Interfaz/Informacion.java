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
		setBounds(100, 100, 702, 393);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.GREEN);
		panel.setBounds(0, 0, 680, 47);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblInformacion = new JLabel("  Informaci\u00F3n");
		lblInformacion.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblInformacion.setBounds(246, 0, 172, 47);
		panel.add(lblInformacion);
		
		JLabel lblNewLabel = new JLabel("Usuario de Admin: OmarDavid");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblNewLabel.setBounds(29, 63, 312, 40);
		contentPanel.add(lblNewLabel);
		
		JLabel lblContrasennaDeAdmin = new JLabel("Contrasenna de admin: TeQueremosRodolfo");
		lblContrasennaDeAdmin.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContrasennaDeAdmin.setBounds(29, 119, 390, 20);
		contentPanel.add(lblContrasennaDeAdmin);
		
		JLabel lblElUsuarioDe = new JLabel("El usuario normal puedes colocar cualquier nombre: ");
		lblElUsuarioDe.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblElUsuarioDe.setBounds(26, 167, 665, 30);
		contentPanel.add(lblElUsuarioDe);
		
		JLabel lblContrasennaDeUsuario = new JLabel("Contrasenna de Usuario: 12345678");
		lblContrasennaDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblContrasennaDeUsuario.setBounds(25, 269, 344, 34);
		contentPanel.add(lblContrasennaDeUsuario);
		
		JLabel lblEjemploOmarSonia = new JLabel("Ejemplo: Omar, Sonia, Rodolfo.");
		lblEjemploOmarSonia.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblEjemploOmarSonia.setBounds(29, 213, 322, 40);
		contentPanel.add(lblEjemploOmarSonia);
	}
}
