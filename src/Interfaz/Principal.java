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
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1284, 670);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBackground(new Color(61,194,68));
		panel_1.setBounds(-14, -16, 1310, 61);
		panel.add(panel_1);
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
	}
}
