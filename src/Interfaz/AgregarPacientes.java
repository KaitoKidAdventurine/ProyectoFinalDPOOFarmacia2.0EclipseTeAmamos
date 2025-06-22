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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import Interfaces_Enum.Sexo;
import javax.swing.JRadioButton;
import java.awt.SystemColor;

public class AgregarPacientes extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */

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
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public AgregarPacientes() {

		setTitle("Registro Paciente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 391, 596);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelDatosGenerales = new JPanel();
		panelDatosGenerales.setBackground(Color.WHITE);
		panelDatosGenerales.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDatosGenerales.setName("");
		panelDatosGenerales.setBounds(12, 79, 330, 412);
		contentPane.add(panelDatosGenerales);
		panelDatosGenerales.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNombre.setBounds(12, 13, 80, 25);
		panelDatosGenerales.add(lblNombre);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c!=' '){
					e.consume();
				}
			}
		});
		textField.setBounds(12, 35, 274, 25);
		panelDatosGenerales.add(textField);
		textField.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblApellidos.setBounds(12, 70, 80, 25);
		panelDatosGenerales.add(lblApellidos);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c!=' '){
					e.consume();
				}
			}
		});
		textField_1.setBounds(12, 99, 274, 25);
		panelDatosGenerales.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblCi = new JLabel("Carnet de identidad");
		lblCi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCi.setBounds(12, 137, 182, 25);
		panelDatosGenerales.add(lblCi);
		
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)){
					e.consume();
				}
			}
		});
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_2.setBounds(12, 164, 274, 25);
		panelDatosGenerales.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblDireccin.setBounds(12, 196, 145, 25);
		panelDatosGenerales.add(lblDireccin);
		
		textField_3 = new JTextField();
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetterOrDigit(c) && !Character.isISOControl(c) && c != ' '){
					e.consume();
				}
			}
		});
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField_3.setBounds(12, 222, 274, 25);
		panelDatosGenerales.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblSexo = new JLabel("Sexo");
		lblSexo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSexo.setBounds(12, 260, 56, 16);
		panelDatosGenerales.add(lblSexo);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(Sexo.values()));
		comboBox.setBounds(12, 278, 137, 22);
		panelDatosGenerales.add(comboBox);
		
		JLabel lblperteneceAUn = new JLabel("\u00BFPertenece a un n\u00FAcleo familiar?");
		lblperteneceAUn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblperteneceAUn.setBounds(12, 305, 274, 25);
		panelDatosGenerales.add(lblperteneceAUn);
		
		JRadioButton rdbtnSi = new JRadioButton("Si");
		rdbtnSi.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnSi.setBounds(49, 331, 56, 25);
		panelDatosGenerales.add(rdbtnSi);
		
		JRadioButton rdbtnNo = new JRadioButton("No");
		rdbtnNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		rdbtnNo.setBounds(120, 331, 74, 25);
		panelDatosGenerales.add(rdbtnNo);
		
		JLabel lblFormularioDeRegistro = new JLabel("Formulario de Registro");
		lblFormularioDeRegistro.setForeground(SystemColor.desktop);
		lblFormularioDeRegistro.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblFormularioDeRegistro.setBounds(22, 13, 339, 53);
		contentPane.add(lblFormularioDeRegistro);
	}
}
