package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

public class AgregarPacientes extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
<<<<<<< HEAD
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					AgregarPacientes frame = new AgregarPacientes();
					frame.setVisible(true);
				} catch (Exception e) 
				{
=======
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarPacientes frame = new AgregarPacientes();
					frame.setVisible(true);
				} catch (Exception e) {
>>>>>>> a1967e86147c5d9379e2a6965cc52413a721fbe8
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
<<<<<<< HEAD
	public AgregarPacientes() 
	{
=======
	public AgregarPacientes() {
>>>>>>> a1967e86147c5d9379e2a6965cc52413a721fbe8
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatosGenerales = new JPanel();
		panelDatosGenerales.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Datos Generales", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDatosGenerales.setName("");
		panelDatosGenerales.setBounds(12, 79, 471, 124);
		contentPane.add(panelDatosGenerales);
		
		JLabel lblFormularioDeRegistro = new JLabel("Formulario de Registro");
		lblFormularioDeRegistro.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblFormularioDeRegistro.setBounds(103, 13, 339, 53);
		contentPane.add(lblFormularioDeRegistro);
	}
}
