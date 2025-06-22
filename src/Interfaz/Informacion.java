package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import Utiles.Navegacion;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Informacion extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Informacion frame = new Informacion();
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
	public Informacion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 710, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 686, 317);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblInformacin = new JLabel("Informaci\u00F3n");
		lblInformacin.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblInformacin.setBounds(256, 16, 174, 34);
		panel.add(lblInformacin);
		
		JLabel lblContraseaDePaciente = new JLabel("Contrase\u00F1a de Paciente: 12345678");
		lblContraseaDePaciente.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblContraseaDePaciente.setBounds(15, 104, 379, 44);
		panel.add(lblContraseaDePaciente);
		
		JLabel lblNewLabel = new JLabel("El Usuario de Paciente puede ser el que guste");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel.setBounds(15, 55, 473, 44);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("El Usuario de Administrador es: OmarDavid");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_1.setBounds(15, 164, 445, 44);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("La password de Admin es: TeQueremosRodolfo");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblNewLabel_2.setBounds(15, 220, 486, 44);
		panel.add(lblNewLabel_2);
		
		JLabel volverAlLogin = new JLabel("  Volver al Login");
		volverAlLogin.setBackground(Color.GREEN);
		volverAlLogin.setForeground(Color.BLACK);
		volverAlLogin.addMouseListener(new MouseAdapter() 
		{
		    @Override
		    public void mouseClicked(MouseEvent e) 
		    {
		    	dispose();
		    }
		});
		
		volverAlLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		volverAlLogin.setBounds(536, 248, 135, 53);
		panel.add(volverAlLogin);
	}
}
