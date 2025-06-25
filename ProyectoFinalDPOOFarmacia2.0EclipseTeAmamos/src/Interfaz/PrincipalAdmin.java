package Interfaz;

import Logica.Farmacia;
import Logica.Medicamento;
import LogicaUtiles.VentaDeMedicamentos;
import modelos.MedicamentoTableModel;
import modelos.ModeloPrincipalTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class PrincipalAdmin extends JFrame 
{
	private JTabbedPane pestanas;
	private JPanel contentPane;
	private int posX;
	private int posY;
	private Point e;
	private JTable tablaMedicamentos;
	private MedicamentoTableModel medicamentoTableModel;
	private JScrollPane scrollPane;

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
					PrincipalAdmin frame = new PrincipalAdmin();
					frame.setVisible(true);
				} 
				
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the frame.
	 */
	public PrincipalAdmin() 
	{
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1302, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tablaMedicamentos = new JTable();
	    scrollPane = new JScrollPane();
	    
		// Centrar la ventana en la pantalla
		
		this.setLocationRelativeTo(null);


		JPanel barraSuperior = new JPanel();
		barraSuperior.addMouseMotionListener(new MouseMotionAdapter() 
		{
			@Override
			public void mouseDragged(MouseEvent e) 
			{
				// Calcular nueva posición
				int newX = getLocation().x + e.getX() - posX;
				int newY = getLocation().y + e.getY() - posY;

				// Mover la ventana
				setLocation(newX, newY);
			}
		});
		barraSuperior.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mousePressed(MouseEvent e) 
			{
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
		cruz.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
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
		panel1.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseEntered(MouseEvent e) 
			{
				panel1.setBackground(new Color (0,237,21));

			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				panel1.setBackground(new Color (12,184,47));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pestanas.setSelectedIndex(0);
			}
		});
		panel1.setBackground(new Color (12,184,47));
		panel1.setBounds(0, 147, 294, 68);
		menu.add(panel1);
		panel1.setLayout(null);

		JLabel lblInicio = new JLabel("PRINCIPAL");
		lblInicio.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
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
			public void mouseClicked(MouseEvent e) {
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
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		panel2.setBounds(0, 221, 294, 68);
		menu.add(panel2);
		panel2.setLayout(null);

		JLabel lblComprar = new JLabel("COMPRAR");
		lblComprar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ae) {
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
			public void mouseClicked(MouseEvent e) {
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
			public void mouseClicked(MouseEvent e) {
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
			public void mouseClicked(MouseEvent e) {
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
			public void mouseExited(MouseEvent e) {
				panel4.setBackground(new Color(2,184,47));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
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
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(3);
			}
		});
		lblReportes.setBounds(64, 13, 200, 50);
		panel4.add(lblReportes);
		lblReportes.setFont(new Font("Times New Roman", Font.BOLD, 26));

		JLabel iconoReportes = new JLabel("");
		iconoReportes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
		lblPrincipal.setBounds(12, 0, 205, 43);
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
		lblNewLabel.setBounds(12, 0, 144, 43);
		panel_3.add(lblNewLabel);

		JLabel lblElijaComoDesea = new JLabel("Elija como desea realizar su compra");
		lblElijaComoDesea.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblElijaComoDesea.setBounds(10, 49, 477, 43);
		comprar.add(lblElijaComoDesea);

		Label label_7 = new Label("_______________________________________________________________________");
		label_7.setForeground(Color.GREEN);
		label_7.setFont(new Font("Arial Black", Font.BOLD, 50));
		label_7.setBounds(0, 37, 1005, 79);
		comprar.add(label_7);

		JPanel compra1 = new JPanel();
		compra1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra1.setBackground(new Color(116,255,78));
		compra1.setBounds(333, 133, 353, 200);
		comprar.add(compra1);

		JPanel compra2 = new JPanel();
		compra2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra2.setBackground(new Color(116,255,78));
		compra2.setBounds(96, 373, 353, 200);
		comprar.add(compra2);

		JPanel compra3 = new JPanel();
		compra3.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra3.setBackground(new Color(116,255,78));
		compra3.setBounds(589, 373, 353, 200);
		comprar.add(compra3);


		JPanel medicamentos = new JPanel();
		pestanas.addTab("", null, medicamentos, null);
		medicamentos.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 292, 970, 358);
		medicamentos.add(scrollPane);



		medicamentoTableModel = new MedicamentoTableModel();
		tablaMedicamentos.setModel(medicamentoTableModel);

		tablaMedicamentos = new JTable();
		scrollPane.setViewportView(tablaMedicamentos);



		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1029, 43);
		panel_1.setBackground(Color.GREEN);
		medicamentos.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblComprar_1 = new JLabel("MEDICAMENTOS");
		lblComprar_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblComprar_1.setBounds(12, 0, 237, 43);
		panel_1.add(lblComprar_1);



		// INICIALIZAR MODELO DE TABLA (ya está)
		medicamentoTableModel = new MedicamentoTableModel();

		// ASIGNAR MODELO A LA TABLA
		tablaMedicamentos.setModel(medicamentoTableModel);

		// CARGAR DATOS DESDE LA FARMACIA
		Farmacia.obtenerInstancia().inicializarDatosPrueba();
		medicamentoTableModel.cargar(Farmacia.obtenerInstancia().obtenerMedicamentos());

		// LLENAR EL MODELO CON LOS DATOS
		medicamentoTableModel.cargar(Farmacia.obtenerInstancia().obtenerMedicamentos());

		JPanel panel_1_bis = new JPanel(); 
		panel_1_bis.setBounds(0, 0, 1029, 43);
		panel_1_bis.setBackground(Color.GREEN);
		medicamentos.add(panel_1_bis);
		panel_1_bis.setLayout(null);

		JLabel lblComprar_1_bis = new JLabel("MEDICAMENTOS"); 
		lblComprar_1_bis.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblComprar_1_bis.setBounds(12, 0, 237, 43);
		panel_1_bis.add(lblComprar_1_bis);



		JPanel reportes = new JPanel();
		pestanas.addTab("", null, reportes, null);
		reportes.setLayout(null);

		JLabel lblListaDeTarjetones = new JLabel("Lista de tarjetones desactivados");
		lblListaDeTarjetones.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListaDeTarjetones.setBounds(530, 460, 349, 28);
		reportes.add(lblListaDeTarjetones);

		JLabel lblControlado = new JLabel("Controlado");
		lblControlado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblControlado.setBounds(86, 508, 158, 57);
		reportes.add(lblControlado);

		JLabel lblNewLabel_2 = new JLabel("Prescripci\u00F3n y Venta por Medicamento");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(86, 481, 361, 69);
		reportes.add(lblNewLabel_2);

		JLabel lblCadaTipoventa = new JLabel("cada tipo (Venta Libre, Venta por");
		lblCadaTipoventa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCadaTipoventa.setBounds(86, 473, 361, 43);
		reportes.add(lblCadaTipoventa);

		JLabel lblPorcentajeDeMayor = new JLabel("Porcentaje de Mayor a Menor venta de ");
		lblPorcentajeDeMayor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPorcentajeDeMayor.setBounds(86, 440, 349, 69);
		reportes.add(lblPorcentajeDeMayor);

		JLabel lblMujeresDeLos = new JLabel("mujeres de los n\u00FAcleos familiares");
		lblMujeresDeLos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMujeresDeLos.setBounds(530, 254, 281, 50);
		reportes.add(lblMujeresDeLos);

		JLabel lblNecesariasParaAbastecer = new JLabel("necesarias para abastecer a todas las");
		lblNecesariasParaAbastecer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNecesariasParaAbastecer.setBounds(530, 233, 349, 50);
		reportes.add(lblNecesariasParaAbastecer);

		JLabel lblCantidadDeAlmohadillas = new JLabel("Cantidad de almohadillas sanitarias");
		lblCantidadDeAlmohadillas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCantidadDeAlmohadillas.setBounds(530, 211, 329, 50);
		reportes.add(lblCantidadDeAlmohadillas);

		JLabel icon4 = new JLabel("");
		icon4.setBounds(407, 413, 40, 34);
		reportes.add(icon4);
		UtilesInterfaz.ajustarImagen(icon4, "src/iconos/senal-alt-1.png");

		JLabel icon3 = new JLabel("");
		icon3.setBounds(848, 413, 40, 34);
		reportes.add(icon3);
		UtilesInterfaz.ajustarImagen(icon3, "src/iconos/comprobacion-de-lista.png");

		JLabel lblTopVentas = new JLabel("TOP Ventas");
		lblTopVentas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTopVentas.setBounds(86, 403, 200, 50);
		reportes.add(lblTopVentas);

		JLabel lblActivos = new JLabel("Activos");
		lblActivos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblActivos.setBounds(530, 403, 200, 50);
		reportes.add(lblActivos);

		JLabel icon2 = new JLabel("");
		icon2.setBounds(848, 170, 40, 34);
		reportes.add(icon2);
		UtilesInterfaz.ajustarImagen(icon2, "src/iconos/toalla-sanitaria.png");

		JLabel lblAlmohadillasSanitarias = new JLabel("Almohadillas Sanitarias");
		lblAlmohadillasSanitarias.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlmohadillasSanitarias.setBounds(536, 161, 251, 50);
		reportes.add(lblAlmohadillasSanitarias);

		JLabel icon1 = new JLabel("");
		icon1.setBounds(407, 167, 40, 34);
		reportes.add(icon1);
		UtilesInterfaz.ajustarImagen(icon1, "src/iconos/libros-de-medicina.png");

		JLabel lblVendidos = new JLabel("vendidos");
		lblVendidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVendidos.setBounds(86, 233, 200, 50);
		reportes.add(lblVendidos);

		JLabel lblListadoConLos = new JLabel("Listado con los 10 medicamentos m\u00E1s ");
		lblListadoConLos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblListadoConLos.setBounds(86, 211, 349, 50);
		reportes.add(lblListadoConLos);

		JLabel label_6 = new JLabel("_______________________");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_6.setBounds(530, 413, 381, 50);
		reportes.add(label_6);

		JLabel label_5 = new JLabel("_______________________");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_5.setBounds(86, 413, 381, 50);
		reportes.add(label_5);

		JLabel repor3 = new JLabel("");
		repor3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(6);
			}
		});
		repor3.setOpaque(true);
		repor3.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		repor3.setBackground(new Color(116,255,78));
		repor3.setBounds(78, 403, 381, 216);
		reportes.add(repor3);

		JLabel label_4 = new JLabel("_______________________");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_4.setBounds(530, 170, 381, 50);
		reportes.add(label_4);

		JLabel repor2 = new JLabel("");
		repor2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(5);
			}
		});
		repor2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		repor2.setBackground(new Color(116,255,78));
		repor2.setOpaque(true);
		repor2.setBounds(520, 161, 381, 216);
		reportes.add(repor2);

		JLabel lblMedicamentos_1 = new JLabel("TOP Medicamentos");
		lblMedicamentos_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMedicamentos_1.setBounds(94, 167, 192, 43);
		reportes.add(lblMedicamentos_1);

		JLabel lbls = new JLabel("_______________________");
		lbls.setFont(new Font("Tahoma", Font.BOLD, 25));
		lbls.setBounds(86, 170, 381, 50);
		reportes.add(lbls);

		JLabel repor1 = new JLabel("");
		repor1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(4);
			}
		});
		repor1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		repor1.setOpaque(true);
		repor1.setBackground(new Color(116,255,78));
		repor1.setBounds(78, 161, 381, 216);
		reportes.add(repor1);

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
		lblReportes_1.setBounds(12, 0, 155, 43);
		panel_2.add(lblReportes_1);

		Label label_3 = new Label("_______________________________________________________________________");
		label_3.setFont(new Font("Arial Black", Font.BOLD, 50));
		label_3.setForeground(Color.GREEN);
		label_3.setBounds(0, 49, 1005, 79);
		reportes.add(label_3);

		JLabel repor4 = new JLabel("");
		repor4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				pestanas.setSelectedIndex(7);
			}
		});
		repor4.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		repor4.setBackground(new Color(116,255,78));
		repor4.setOpaque(true);
		repor4.setBounds(520, 403, 381, 216);
		reportes.add(repor4);

		JLabel iconMed = new JLabel("");
		iconMed.setBounds(94, 176, 40, 34);
		reportes.add(iconMed);

		Panel reporte1 = new Panel();
		reporte1.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, reporte1, null);
		reporte1.setLayout(null);


		final Farmacia farmacia = Farmacia.obtenerInstancia();


		JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.GREEN);
		panel_4.setBounds(0, 0, 1015, 57);
		reporte1.add(panel_4);
		panel_4.setLayout(null);

		JLabel regresar = new JLabel("");
		regresar.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pestanas.setSelectedIndex(3);
			}
		});
		regresar.setBounds(0, 0, 64, 57);
		panel_4.add(regresar);
		UtilesInterfaz.ajustarImagen(regresar, "src/iconos/deshacer.png");

		JLabel lblTopMedicamentos = new JLabel("TOP Medicamentos");
		lblTopMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTopMedicamentos.setBounds(79, 0, 200, 57);
		panel_4.add(lblTopMedicamentos);


		final JPanel panelLista = new JPanel();
		panelLista.setBackground(Color.LIGHT_GRAY);
		panelLista.setBounds(37, 176, 927, 446);
		reporte1.add(panelLista);
		panelLista.setLayout(null);

		panelLista.revalidate();
		panelLista.repaint();

		ArrayList<VentaDeMedicamentos> datos = farmacia.medicamentosMasVendidos();

		// Ordenar por cantidad (ya debería venir ordenado del método, pero por si acaso)
		Collections.sort(datos, farmacia.getComparador());

		// Limitar a 10 y mostrar
		int max = Math.min(10, datos.size());
		for(int i = 0; i < max; i++) {
			VentaDeMedicamentos vm = datos.get(i);

			JLabel item = new JLabel(
					(i+1) + ". " + vm.getNombre() + " - " + vm.getCantidadVendida() + " unidades"
					);
			item.setFont(new Font("Arial", Font.PLAIN, 16));


			if(i == 0) {
				item.setFont(new Font("Arial", Font.BOLD, 16));
				item.setForeground(new Color(0, 100, 0));
			}

			panelLista.add(item);
		}

		Panel reporte2 = new Panel();
		reporte2.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, reporte2, null);
		reporte2.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.GREEN);
		panel_5.setBounds(0, 0, 1015, 57);
		reporte2.add(panel_5);
		panel_5.setLayout(null);

		JLabel regresar_2 = new JLabel("");
		regresar_2.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pestanas.setSelectedIndex(3);
			}
		});
		regresar_2.setBounds(0, 0, 64, 57);
		panel_5.add(regresar_2);
		UtilesInterfaz.ajustarImagen(regresar_2, "src/iconos/deshacer.png");


		JLabel lblAlmohadillasSanitarias_1 = new JLabel("Almohadillas Sanitarias");
		lblAlmohadillasSanitarias_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblAlmohadillasSanitarias_1.setBounds(72, 0, 307, 57);
		panel_5.add(lblAlmohadillasSanitarias_1);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(312, 0, 122, 57);
		panel_5.add(lblNewLabel_3);

		Panel reporte3 = new Panel();
		reporte3.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, reporte3, null);
		reporte3.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.GREEN);
		panel_6.setBounds(0, 0, 1015, 57);
		reporte3.add(panel_6);
		panel_6.setLayout(null);

		JLabel regresar_3 = new JLabel("");
		regresar_3.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pestanas.setSelectedIndex(3);
			}
		});
		regresar_3.setBounds(0, 0, 64, 57);
		panel_6.add(regresar_3);
		UtilesInterfaz.ajustarImagen(regresar_3, "src/iconos/deshacer.png");


		JLabel lblTopVentas_1 = new JLabel("TOP Ventas");
		lblTopVentas_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTopVentas_1.setBounds(76, 0, 200, 57);
		panel_6.add(lblTopVentas_1);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(290, 17, 69, 20);
		panel_6.add(lblNewLabel_4);

		Panel reporte4 = new Panel();
		reporte4.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, reporte4, null);
		reporte4.setLayout(null);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(Color.GREEN);
		panel_7.setBounds(0, 0, 1015, 57);
		reporte4.add(panel_7);
		panel_7.setLayout(null);

		JLabel regresar_4 = new JLabel("");
		regresar_4.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pestanas.setSelectedIndex(3);
			}
		});
		regresar_4.setBounds(0, 0, 64, 57);
		panel_7.add(regresar_4);
		UtilesInterfaz.ajustarImagen(regresar_4, "src/iconos/deshacer.png");

		JLabel lblActivos_1 = new JLabel("Activos");
		lblActivos_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblActivos_1.setBounds(76, 0, 200, 57);
		panel_7.add(lblActivos_1);

		JLabel lblNewLabel_5 = new JLabel("New label"); 
		lblNewLabel_5.setBounds(307, 16, 69, 20); 
		panel_7.add(lblNewLabel_5);
	}


	// Método para cargar los datos
	private void cargarTablaMedicamentos() 
	{
		String[] columnas = {"Nombre Común", "Nombre Científico", "Presentación", "Precio", "Tipo", "Fortaleza", "Stock", "Fecha Vencimiento"};
		
		DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Farmacia.obtenerInstancia().inicializarMedicamentos();
		
		for (Medicamento med : Farmacia.obtenerInstancia().obtenerMedicamentos()) 
		{
			Object[] fila = 
				{
					med.getNomComun(),
					med.getNomCientifico(),
					med.getPresentacion(),
					"$" + String.format("%.2f", med.getPrecio()), // Formato de precio
					med.getTipo(),
					med.getFortalezaDelMed(),
					med.getCantExis(),
					sdf.format(med.getFechaDeVenc()) // Fecha formateada
				};
			modelo.addRow(fila);
		}
		tablaMedicamentos.setModel(modelo);
	}
}