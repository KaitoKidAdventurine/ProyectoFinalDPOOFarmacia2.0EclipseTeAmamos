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

public class AgregarPacientes extends JFrame {

	private JPanel contentPane;
	private JTextField NombresApellidos;
	private JTextField CIDelPaciente;
	private JTextField DireccionDelPaciente;
	private JTextField IdDelNucleo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AgregarPacientes frame = new AgregarPacientes();
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
	public AgregarPacientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 614);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		NombresApellidos = new JTextField();
		NombresApellidos.setBounds(25, 140, 289, 51);
		contentPane.add(NombresApellidos);
		NombresApellidos.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Ingrese su CI");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(102, 218, 126, 45);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Agregar Paciente");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 38));
		lblNewLabel_1.setBounds(234, 16, 298, 56);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Ingrese su Nombre y Apellidos");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(25, 97, 271, 39);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Ingrese su Direcci\u00F3n");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(504, 102, 181, 28);
		contentPane.add(lblNewLabel_3);
		
		CIDelPaciente = new JTextField();
		CIDelPaciente.setBounds(25, 268, 289, 51);
		contentPane.add(CIDelPaciente);
		CIDelPaciente.setColumns(10);
		
		DireccionDelPaciente = new JTextField();
		DireccionDelPaciente.setBounds(457, 140, 289, 51);
		contentPane.add(DireccionDelPaciente);
		DireccionDelPaciente.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese el id del Nucleo");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(504, 212, 219, 56);
		contentPane.add(lblNewLabel_4);
		
		IdDelNucleo = new JTextField();
		IdDelNucleo.setBounds(457, 268, 289, 51);
		contentPane.add(IdDelNucleo);
		IdDelNucleo.setColumns(10);
		
		JButton btnNewButton = new JButton("Ingresar");
		btnNewButton.setBounds(547, 369, 115, 51);
		contentPane.add(btnNewButton);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() 
		{
			// este boton limpia los datos de entrada
			public void actionPerformed(ActionEvent arg0) 
			{
				
			}
		});
		btnLimpiar.setBounds(102, 369, 115, 51);
		contentPane.add(btnLimpiar);
	}
}
