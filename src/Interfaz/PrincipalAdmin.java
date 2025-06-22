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

import Utiles.UtilesInterfaz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Window.Type;
import javax.swing.JTabbedPane;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.border.MatteBorder;

public class PrincipalAdmin extends JFrame {

	private JTabbedPane pestanas;
	private JPanel contentPane;
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
					PrincipalAdmin frame = new PrincipalAdmin();
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
	public PrincipalAdmin() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1302, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    // Centrar la ventana en la pantalla
	    this.setLocationRelativeTo(null);
		
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
		barraSuperior.setBounds(0, 0, 1302, 64);
		contentPane.add(barraSuperior);
		barraSuperior.setLayout(null);
		
		final JLabel cruz = new JLabel("");
		cruz.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cruz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit1.png");
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
			}
		});
		cruz.setBounds(1225, 0, 77, 58);
		barraSuperior.add(cruz);
		UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
		
		JLabel minimizar = new JLabel("");
		minimizar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimizar.setBounds(1149, 0, 77, 58);
		barraSuperior.add(minimizar);
		UtilesInterfaz.ajustarImagen(minimizar, "src/iconos/minimize0.png");
		
		JLabel lblNewLabel_1 = new JLabel("Sistema de Gesti\u00F3n de Procesos en las Farmacia");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(12, 13, 681, 45);
		barraSuperior.add(lblNewLabel_1);
		
		JPanel menu = new JPanel();
		menu.setBackground(new Color (12,184,47));
		menu.setBounds(0, 57, 294, 660);
		contentPane.add(menu);
		menu.setLayout(null);
		
		final JPanel panel1 = new JPanel();
		panel1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
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
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(0);
			}
		});
		panel1.setBackground(new Color (12,184,47));
		panel1.setBounds(0, 147, 294, 68);
		menu.add(panel1);
		panel1.setLayout(null);
		
		JLabel lblInicio = new JLabel("PRINCIPAL");
		lblInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(0);
			}
		});
		lblInicio.setBounds(68, 24, 144, 31);
		panel1.add(lblInicio);
		lblInicio.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblInicio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		final JLabel iconoPrincipal = new JLabel("");
		iconoPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(0);
			}
		});
		iconoPrincipal.setBounds(12, 13, 43, 42);
		panel1.add(iconoPrincipal);
		UtilesInterfaz.ajustarImagen(iconoPrincipal, "src/iconos/hogar(2).png");
		
		final JPanel panel2 = new JPanel();
		panel2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel2.setBackground(new Color(2,184,47));
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
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(1);
			}
		});
		panel2.setBounds(0, 221, 294, 68);
		menu.add(panel2);
		panel2.setLayout(null);
		
		JLabel lblComprar = new JLabel("COMPRAR");
		lblComprar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(1);
			}
		});
		lblComprar.setBounds(67, 24, 136, 31);
		panel2.add(lblComprar);
		lblComprar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblComprar.setForeground(SystemColor.textText);
		lblComprar.setFont(new Font("Times New Roman", Font.BOLD, 26));
		
		JLabel iconoComprar = new JLabel("");
		iconoComprar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(1);
			}
		});
		iconoComprar.setBounds(12, 13, 43, 42);
		panel2.add(iconoComprar);
		UtilesInterfaz.ajustarImagen(iconoComprar, "src/iconos/compracarrito.png");
		
		final JPanel panel3 = new JPanel();
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
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(2);
			}
		});
		panel3.setBackground(new Color(2,184,47));
		panel3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel3.setBounds(0, 295, 294, 68);
		menu.add(panel3);
		panel3.setLayout(null);
		
		JLabel lblMedicamentos = new JLabel("MEDICAMENTOS");
		lblMedicamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(2);
			}
		});
		lblMedicamentos.setBounds(62, 13, 232, 50);
		panel3.add(lblMedicamentos);
		lblMedicamentos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblMedicamentos.setFont(new Font("Times New Roman", Font.BOLD, 26));
		
		JLabel iconoMedicamentos = new JLabel("");
		iconoMedicamentos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(2);
			}
		});
		iconoMedicamentos.setBounds(12, 13, 43, 42);
		panel3.add(iconoMedicamentos);
		UtilesInterfaz.ajustarImagen(iconoMedicamentos, "src/iconos/medicamento(1).png");
		
		final JPanel panel4 = new JPanel();
		panel4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel4.setBackground(new Color(0,237,21));
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				panel4.setBackground(new Color(2,184,47));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(3);
			}
		});
		panel4.setBackground(new Color(2,184,47));
		panel4.setBounds(0, 370, 294, 68);
		menu.add(panel4);
		panel4.setLayout(null);
		
		JLabel lblReportes = new JLabel("REPORTES");
		lblReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(3);
			}
		});
		lblReportes.setBounds(64, 13, 200, 50);
		panel4.add(lblReportes);
		lblReportes.setFont(new Font("Times New Roman", Font.BOLD, 26));
		
		JLabel iconoReportes = new JLabel("");
		iconoReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(3);
			}
		});
		iconoReportes.setBounds(12, 13, 43, 42);
		panel4.add(iconoReportes);
		UtilesInterfaz.ajustarImagen(iconoReportes, "src/iconos/lista-de-verificacion-de-tareas.png");
		
		JLabel label = new JLabel("");
		label.setBounds(84, 13, 113, 122);
		menu.add(label);
		UtilesInterfaz.ajustarImagen(label, "src/imagenes/logoMinsap.png");
		
		Label label_2 = new Label("_________________________________________________");
		label_2.setFont(new Font("Arial Black", Font.BOLD, 29));
		label_2.setBounds(0, 408, 294, 47);
		menu.add(label_2);
		
		Label label_1 = new Label("_________________________________________________");
		label_1.setBounds(0, 109, 294, 47);
		menu.add(label_1);
		label_1.setFont(new Font("Arial Black", Font.BOLD, 29));
		
		pestanas = new JTabbedPane(JTabbedPane.TOP);
		pestanas.setBounds(292, 33, 1010, 684);
		contentPane.add(pestanas);
		
		JPanel principal = new JPanel();
		pestanas.addTab("", null, principal, null);
		principal.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GREEN);
		panel.setBounds(0, 0, 1016, 43);
		principal.add(panel);
		panel.setLayout(null);
		
		JLabel lblPrincipal = new JLabel("PRINCIPAL");
		lblPrincipal.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblPrincipal.setBounds(373, 0, 205, 43);
		panel.add(lblPrincipal);
		
		JPanel comprar = new JPanel();
		pestanas.addTab("", null, comprar, null);
		comprar.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.GREEN);
		panel_3.setBounds(0, 0, 1029, 43);
		comprar.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COMPRAR");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(434, 13, 144, 25);
		panel_3.add(lblNewLabel);
		
		
		JPanel medicamentos = new JPanel();
		pestanas.addTab("", null, medicamentos, null);
		medicamentos.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.GREEN);
		panel_1.setBounds(0, 0, 1029, 43);
		medicamentos.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblComprar_1 = new JLabel("MEDICAMENTOS");
		lblComprar_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblComprar_1.setBounds(394, 0, 237, 43);
		panel_1.add(lblComprar_1);
		
		JPanel reportes = new JPanel();
		pestanas.addTab("", null, reportes, null);
		reportes.setLayout(null);
		
		JLabel repor1 = new JLabel("");
		repor1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		repor1.setOpaque(true);
		repor1.setBackground(new Color(116,255,78));
		repor1.setBounds(78, 161, 381, 216);
		reportes.add(repor1);
		
		JLabel repor2 = new JLabel("");
		repor2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		repor2.setBackground(new Color(116,255,78));
		repor2.setOpaque(true);
		repor2.setBounds(520, 161, 381, 216);
		reportes.add(repor2);
		
		JLabel lblElijaUnReporte = new JLabel("Elija un reporte");
		lblElijaUnReporte.setForeground(Color.BLACK);
		lblElijaUnReporte.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblElijaUnReporte.setBounds(10, 56, 251, 43);
		reportes.add(lblElijaUnReporte);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GREEN);
		panel_2.setBounds(0, 0, 1029, 43);
		reportes.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblReportes_1 = new JLabel("REPORTES");
		lblReportes_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblReportes_1.setBounds(439, 0, 155, 43);
		panel_2.add(lblReportes_1);
		
		Label label_3 = new Label("_______________________________________________________________________");
		label_3.setFont(new Font("Arial Black", Font.BOLD, 50));
		label_3.setForeground(Color.GREEN);
		label_3.setBounds(0, 49, 1005, 79);
		reportes.add(label_3);
		
		JLabel repor3 = new JLabel("");
		repor3.setOpaque(true);
		repor3.setBackground(new Color(116,255,78));
		repor3.setBounds(78, 403, 381, 216);
		reportes.add(repor3);
		
		JLabel repor4 = new JLabel("");
		repor4.setBackground(new Color(116,255,78));
		repor4.setOpaque(true);
		repor4.setBounds(520, 403, 381, 216);
		reportes.add(repor4);
		
		Panel reporte1 = new Panel();
		pestanas.addTab("New tab", null, reporte1, null);
		reporte1.setLayout(null);
		
		Panel reporte2 = new Panel();
		pestanas.addTab("New tab", null, reporte2, null);
		
		Panel reporte3 = new Panel();
		pestanas.addTab("New tab", null, reporte3, null);
		
		Panel reporte4 = new Panel();
		pestanas.addTab("New tab", null, reporte4, null);
		
		
		
	}
}
