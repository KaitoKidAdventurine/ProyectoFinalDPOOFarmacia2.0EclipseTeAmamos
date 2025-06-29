package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import Logica.Farmacia;
import Utiles.UtilesInterfaz;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Label;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.MedicamentoTableModel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTextPane;

public class PrincipalUsuario extends JFrame {

	private JTabbedPane pestanas;
	private JPanel contentPane;
	private int posX;
	private int posY;
	private JTable tablaMedicamentos;
	private MedicamentoTableModel medicamentoTableModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrincipalUsuario frame = new PrincipalUsuario();
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
	public PrincipalUsuario() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1159, 696);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    // Centrar la ventana en la pantalla
	    this.setLocationRelativeTo(null);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color (12,184,47));
		menu.setBounds(0, 55, 281, 641);
		contentPane.add(menu);
		menu.setLayout(null);
		
		JLabel logoMinsap = new JLabel("");
		logoMinsap.setBounds(84, 13, 117, 122);
		menu.add(logoMinsap);
		UtilesInterfaz.ajustarImagen(logoMinsap, "src/imagenes/logoMinsap.png");
		
		Label label = new Label("_________________________________________________");
		label.setFont(new Font("Arial Black", Font.BOLD, 29));
		label.setBounds(0, 114, 294, 47);
		menu.add(label);
		
		final JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel1.setBackground(new Color (0,237,21));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel1.setBackground(new Color (12,184,47));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(0);
			}
		});
		panel1.setBackground(new Color(12, 184, 47));
		panel1.setBounds(-13, 162, 294, 68);
		menu.add(panel1);
		
		JLabel label_1 = new JLabel("PRINCIPAL");
		label_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(0);
			}
		});
		label_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_1.setBounds(83, 24, 144, 31);
		panel1.add(label_1);
		
		JLabel iconoPrincipal = new JLabel("");
		iconoPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(0);
			}
		});
		iconoPrincipal.setBounds(27, 13, 44, 42);
		panel1.add(iconoPrincipal);
		UtilesInterfaz.ajustarImagen(iconoPrincipal, "src/iconos/hogar(2).png");
		
		final JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel2.setBackground(new Color(0,237,21));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel2.setBackground(new Color(2,184,47));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		panel2.setBackground(new Color(2, 184, 47));
		panel2.setBounds(-13, 232, 294, 68);
		menu.add(panel2);
		
		JLabel label_3 = new JLabel("COMPRAR");
		label_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		label_3.setForeground(Color.BLACK);
		label_3.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_3.setBounds(92, 24, 136, 31);
		panel2.add(label_3);
		
		JLabel iconoComprar = new JLabel("");
		iconoComprar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		iconoComprar.setBounds(23, 13, 46, 42);
		panel2.add(iconoComprar);
		UtilesInterfaz.ajustarImagen(iconoComprar, "src/iconos/compracarrito.png");
		
		final JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		panel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel3.setBackground(new Color(0,237,21));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel3.setBackground(new Color(2,184,47));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(2);
			}
		});
		panel3.setBackground(new Color(2, 184, 47));
		panel3.setBounds(-13, 302, 294, 68);
		menu.add(panel3);
		
		JLabel label_5 = new JLabel("MEDICAMENTOS");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(2);
			}
		});
		label_5.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_5.setBounds(62, 13, 232, 50);
		panel3.add(label_5);
		
		JLabel iconoMedicamentos = new JLabel("");
		iconoMedicamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(2);
			}
		});
		iconoMedicamentos.setBounds(25, 13, 38, 42);
		panel3.add(iconoMedicamentos);
		UtilesInterfaz.ajustarImagen(iconoMedicamentos, "src/iconos/medicamento(1).png");
		
		Label label_8 = new Label("_________________________________________________");
		label_8.setFont(new Font("Arial Black", Font.BOLD, 29));
		label_8.setBounds(0, 347, 294, 47);
		menu.add(label_8);
		
		JPanel barraSuperior = new JPanel();
		
		barraSuperior.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// Calcular nueva posición
                int newX = getLocation().x + e.getX() - posX;
                int newY = getLocation().y + e.getY() - posY;
                
                // Mover la ventana
                setLocation(newX, newY);
			}
		});
		barraSuperior.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Guardar posición inicial del mouse
                posX = e.getX();
                posY = e.getY();
			}
		});
		barraSuperior.setBackground(new Color(8,117,30));
		barraSuperior.setBounds(0, 0, 1159, 56);
		contentPane.add(barraSuperior);
		barraSuperior.setLayout(null);
		
		final JLabel cruz = new JLabel("");
		cruz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit1.png");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
			}
		});
		cruz.setBounds(1081, 0, 78, 56);
		barraSuperior.add(cruz);
		UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
		
		JLabel minimizar = new JLabel("");
		minimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimizar.setBounds(1015, 0, 78, 56);
		barraSuperior.add(minimizar);
		UtilesInterfaz.ajustarImagen(minimizar, "src/iconos/minimize0.png");
		
		JLabel label_2 = new JLabel("Sistema de Gesti\u00F3n de Procesos en las Farmacia");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_2.setBounds(12, 11, 681, 45);
		barraSuperior.add(label_2);
		
		this.pestanas = new JTabbedPane(JTabbedPane.TOP);
		pestanas.setBounds(279, 25, 880, 671);
		contentPane.add(pestanas);
		
		JPanel principal = new JPanel();
		principal.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, principal, null);
		principal.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 875, 43);
		principal.add(panel);
		
		JLabel label_4 = new JLabel("PRINCIPAL");
		label_4.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_4.setBounds(12, 0, 205, 43);
		panel.add(label_4);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBorder(new CompoundBorder(new LineBorder(new Color(0, 100, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(27, 108, 424, 415);
		principal.add(panel_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setText("Cr\u00E9ditos:\r\n-Alison Hidalgo Guerra\r\nGitHub: AlisonH17\r\n-Eriet Dario Armas Gonz\u00E1lez \r\nGitHub: KaitoKidAdventurine");
		textPane_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane_4.setBounds(109, 291, 280, 111);
		panel_3.add(textPane_4);
		
		JLabel label_11 = new JLabel("_____________");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_11.setBounds(0, -27, 511, 97);
		panel_3.add(label_11);
		
		JLabel label_12 = new JLabel("_____________");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_12.setBounds(0, 83, 511, 97);
		panel_3.add(label_12);
		
		JLabel label_13 = new JLabel("_____________");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_13.setBounds(0, 193, 511, 97);
		panel_3.add(label_13);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setText("En nuestro sistema podr\u00E1:");
		textPane_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		textPane_1.setBounds(12, 13, 400, 66);
		panel_3.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setText("Realizar la compra de medicamentos por 3 Tipos de ventas y la compra de almohadillas sanitarias para las mujeres de su n\u00FAcleo familiar ");
		textPane_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane_2.setBounds(10, 71, 402, 80);
		panel_3.add(textPane_2);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setText("Consultar la disponibilidad de medicamentos para realizar la compra exitosa de ellos");
		textPane_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textPane_3.setBounds(10, 179, 347, 122);
		panel_3.add(textPane_3);
		
		JLabel label_14 = new JLabel("");
		label_14.setBounds(463, 81, 381, 490);
		principal.add(label_14);
		UtilesInterfaz.ajustarImagen(label_14, "src/imagenes/istockphoto-1240167813-612x612.jpg");
		
		JPanel comprar = new JPanel();
		pestanas.addTab("New tab", null, comprar, null);
		comprar.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(0, 0, 875, 43);
		comprar.add(panel_1);
		
		JLabel label_6 = new JLabel("COMPRAR");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_6.setBounds(12, 0, 144, 43);
		panel_1.add(label_6);
		
		JPanel medicamentos = new JPanel();
		pestanas.addTab("New tab", null, medicamentos, null);
		medicamentos.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(Color.GREEN);
		panel_2.setBounds(0, 0, 875, 43);
		medicamentos.add(panel_2);
		
		JLabel label_7 = new JLabel("MEDICAMENTOS");
		label_7.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_7.setBounds(12, 0, 237, 43);
		panel_2.add(label_7);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 151, 851, 460);
		medicamentos.add(scrollPane);
		
		tablaMedicamentos = new JTable();
		scrollPane.setViewportView(tablaMedicamentos);
		
		// INICIALIZAR MODELO DE TABLA (ya está)
				medicamentoTableModel = new MedicamentoTableModel();

				// ASIGNAR MODELO A LA TABLA
				tablaMedicamentos.setModel(medicamentoTableModel);
				tablaMedicamentos.setRowHeight(28);
				
				JLabel label_9 = new JLabel("_________");
				label_9.setFont(new Font("Tahoma", Font.PLAIN, 99));
				label_9.setForeground(new Color(0, 255, 0));
				label_9.setBounds(0, 0, 494, 165);
				medicamentos.add(label_9);
				
				JLabel label_10 = new JLabel("_________");
				label_10.setForeground(Color.GREEN);
				label_10.setFont(new Font("Tahoma", Font.PLAIN, 99));
				label_10.setBounds(398, 0, 494, 165);
				medicamentos.add(label_10);
				
				JLabel lblMedicamentosDisponibles = new JLabel("Medicamentos Disponibles");
				lblMedicamentosDisponibles.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblMedicamentosDisponibles.setBounds(244, 70, 368, 43);
				medicamentos.add(lblMedicamentosDisponibles);
				

				// CARGAR DATOS DESDE LA FARMACIA
				Farmacia.obtenerInstancia().inicializarDatosPrueba();

				// LLENAR EL MODELO CON LOS DATOS
				medicamentoTableModel.actualizar(Farmacia.obtenerInstancia().obtenerMedicamentos());
	}
}
