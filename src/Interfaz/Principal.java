package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import Utiles.UtilesInterfaz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;
import javax.swing.JTabbedPane;
import Utiles.UtilesInterfaz;
import javax.swing.JTextField;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
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
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1302, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel fondo = new JPanel();
		fondo.setBackground(Color.WHITE);
		fondo.setBounds(0, 0, 1284, 670);
		contentPane.add(fondo);
		fondo.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(61,194,68));
		panel_1.setBounds(-14, -16, 1310, 61);
		fondo.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel cruz = new JLabel("");
		cruz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		cruz.setBounds(1234, 13, 64, 48);
		panel_1.add(cruz);
		UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
		
		JLabel minimizar = new JLabel("");
		minimizar.setBounds(1183, 13, 63, 48);
		panel_1.add(minimizar);
		UtilesInterfaz.ajustarImagen(minimizar, "src/iconos/minimize0.png");
		
		JLabel lblGestinDeProcesos = new JLabel("Gesti\u00F3n de procesos en las farmacias");
		lblGestinDeProcesos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGestinDeProcesos.setBounds(23, 13, 406, 48);
		panel_1.add(lblGestinDeProcesos);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(79,255,125));
		panel_2.setBounds(-4, 37, 362, 633);
		fondo.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(56,181,89));
		panel_3.setBounds(0, 5, 362, 237);
		panel_2.add(panel_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(186, 7, 5, 5);
		panel_2.add(tabbedPane);
		
		JLabel icono1 = new JLabel("");
		icono1.setBounds(54, 260, 51, 50);
		panel_2.add(icono1);
		UtilesInterfaz.ajustarImagen(icono1, "src/iconos/hogar(2).png");
		
		JLabel lblInicio = new JLabel("INICIO");
		lblInicio.setFont(new Font("Sylfaen", Font.BOLD, 39));
		lblInicio.setBounds(127, 275, 174, 35);
		panel_2.add(lblInicio);
		
		JLabel lblVenta = new JLabel("VENTA");
		lblVenta.setFont(new Font("Sylfaen", Font.BOLD, 39));
		lblVenta.setBounds(127, 356, 160, 50);
		panel_2.add(lblVenta);
		
		JLabel icono2 = new JLabel("");
		icono2.setBounds(54, 356, 51, 50);
		panel_2.add(icono2);
		UtilesInterfaz.ajustarImagen(icono2, "src/iconos/compracarrito.png");
		
		JLabel lblReportes = new JLabel("REPORTES");
		lblReportes.setFont(new Font("Sylfaen", Font.BOLD, 39));
		lblReportes.setBounds(127, 460, 207, 35);
		panel_2.add(lblReportes);
		
		JLabel icono3 = new JLabel("");
		icono3.setBounds(54, 445, 51, 50);
		panel_2.add(icono3);
		UtilesInterfaz.ajustarImagen(icono3, "src/iconos/lista-de-verificacion-de-tareas.png");
		
		JLabel lblPacientes = new JLabel("Pacientes");
		lblPacientes.setFont(new Font("Sylfaen", Font.BOLD, 41));
		lblPacientes.setBounds(127, 541, 207, 50);
		panel_2.add(lblPacientes);
		
		JLabel icono4 = new JLabel("");
		icono4.setBounds(54, 530, 51, 50);
		panel_2.add(icono4);
		UtilesInterfaz.ajustarImagen(icono4, "src/iconos/usuario.png");
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(79,255,125));
		panel.setBounds(357, 37, 927, 61);
		fondo.add(panel);
	}
}
