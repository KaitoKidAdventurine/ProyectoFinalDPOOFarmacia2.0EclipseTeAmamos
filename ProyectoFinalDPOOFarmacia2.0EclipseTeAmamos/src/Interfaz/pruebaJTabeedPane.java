package Interfaz;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class pruebaJTabeedPane extends JFrame {

	private JPanel contentPane;
	private JTabbedPane pestaña = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebaJTabeedPane frame = new pruebaJTabeedPane();
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
	public pruebaJTabeedPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		pestaña.setBounds(148, 71, 660, 454);
		contentPane.add(pestaña);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 0, 0));
		pestaña.addTab("New tab", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(105, 105, 105));
		pestaña.addTab("New tab", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		pestaña.addTab("New tab", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		pestaña.addTab("New tab", null, panel_4, null);
		
		JPanel panel = new JPanel();
		pestaña.addTab("New tab", null, panel, null);
		
		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestaña.setSelectedIndex(0);
			}
		});
		panel_5.setBounds(24, 96, 112, 48);
		contentPane.add(panel_5);
		
		JLabel lblPestaa = new JLabel("pesta\u00F1a1");
		panel_5.add(lblPestaa);
		
		JPanel panel_6 = new JPanel();
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestaña.setSelectedIndex(1);
			}
		});
		panel_6.setBounds(24, 171, 112, 56);
		contentPane.add(panel_6);
		
		JLabel lblPestaa_1 = new JLabel("pesta\u00F1a2");
		panel_6.add(lblPestaa_1);
	}
}
