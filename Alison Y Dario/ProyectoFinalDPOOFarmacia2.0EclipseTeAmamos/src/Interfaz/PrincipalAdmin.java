package Interfaz;

import Logica.Farmacia;
import Logica.Medicamento;
import LogicaUtiles.Porcentaje;
import LogicaUtiles.VentaDeMedicamentos;
import modelos.AlmohadillasNecesariasTableModel;
import modelos.ComparacionVentasTableModel;
import modelos.MedicamentoTableModel;
import modelos.MedicamentosMasVendidosTableModel;
import modelos.ModeloPrincipalTableModel;
import modelos.TarjetonesIncumplidosTableModel;

import javax.swing.table.DefaultTableCellRenderer;
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
import javax.swing.JTextArea;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import javax.swing.JTextPane;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

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
	private JTextField textNombreComun;
	private JTextField textNombreCientifico;
	private JTextField textPresentacion;
	private JTextField textPrecio;
	private JTextField textTipo;
	private JTextField textFortaleza;
	private JTextField textTemperatura;
	private JTextField textCantidad;
	private JTextField textFechaProduccion;
	private JTextField textFechaVencimiento;
	private JTable tablaMasVendidos;
	private MedicamentosMasVendidosTableModel medicamentosMasVendidosModel;
	private JTable tablaAlmohadillas;
	private AlmohadillasNecesariasTableModel modelAlmohadillas;
	private JTable tablaComparacionVentas;
	private ComparacionVentasTableModel comparacionVentasModel;
	private JTable tablaTarjetones;
	private TarjetonesIncumplidosTableModel tarjetonesModel;
	private JTextField textcodigoDelMed;

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

	public void limpiarCampos() {
		textNombreComun.setText("");
		textNombreCientifico.setText("");
		textPresentacion.setText("");
		textPrecio.setText("");
		textTipo.setText("");
		textFortaleza.setText("");
		textTemperatura.setText("");
		textCantidad.setText("");
		textNombreComun.setText("");
		textNombreCientifico.setText("");
		textPresentacion.setText("");
		textPrecio.setText("");
		textTipo.setText("");
		textFortaleza.setText("");
		textTemperatura.setText("");
		textCantidad.setText("");
		>>>>>>> 03f9524e3c78cf5e09fdce0425a676bc764d658e
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
		principal.setBackground(Color.WHITE);
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

		JLabel label_61 = new JLabel("");
		label_61.setBounds(540, 122, 453, 472);
		principal.add(label_61);
		UtilesInterfaz.ajustarImagen(label_61, "src/imagenes/istockphoto-1240167813-612x612.jpg");

		JPanel panel_13 = new JPanel();
		panel_13.setBackground(Color.WHITE);
		panel_13.setBorder(new CompoundBorder(new LineBorder(new Color(0, 100, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		panel_13.setBounds(43, 119, 485, 494);
		principal.add(panel_13);
		panel_13.setLayout(null);

		JTextPane txtpnBeneficioDeAdministrador = new JTextPane();
		txtpnBeneficioDeAdministrador.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnBeneficioDeAdministrador.setText("Beneficio de Administrador : Consultar reportes con lo m\u00E1s inmediato de la farmacia ");
		txtpnBeneficioDeAdministrador.setBounds(10, 287, 463, 55);
		panel_13.add(txtpnBeneficioDeAdministrador);

		JLabel label_62 = new JLabel("_____________");
		label_62.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_62.setBounds(0, -27, 511, 97);
		panel_13.add(label_62);

		JLabel label_63 = new JLabel("_____________");
		label_63.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_63.setBounds(0, 83, 511, 97);
		panel_13.add(label_63);

		JLabel label_64 = new JLabel("_____________");
		label_64.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_64.setBounds(0, 193, 511, 97);
		panel_13.add(label_64);

		JTextPane txtpnEnNuestroSistema = new JTextPane();
		txtpnEnNuestroSistema.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtpnEnNuestroSistema.setText("En nuestro sistema podr\u00E1:");
		txtpnEnNuestroSistema.setBounds(12, 13, 461, 66);
		panel_13.add(txtpnEnNuestroSistema);

		JTextPane txtpnRealizarLaCompra = new JTextPane();
		txtpnRealizarLaCompra.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnRealizarLaCompra.setText("Realizar la compra de medicamentos por 3 Tipos de ventas y la compra de almohadillas sanitarias para las mujeres de su n\u00FAcleo familiar ");
		txtpnRealizarLaCompra.setBounds(10, 71, 442, 80);
		panel_13.add(txtpnRealizarLaCompra);

		JTextPane txtpnConsultarLaDisponibilidad = new JTextPane();
		txtpnConsultarLaDisponibilidad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnConsultarLaDisponibilidad.setText("Consultar la disponibilidad de medicamentos para realizar la compra exitosa de ellos");
		txtpnConsultarLaDisponibilidad.setBounds(10, 179, 440, 122);
		panel_13.add(txtpnConsultarLaDisponibilidad);

		JLabel label_65 = new JLabel("_____________");
		label_65.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_65.setBounds(0, 276, 511, 97);
		panel_13.add(label_65);

		JTextPane txtpnCrditos = new JTextPane();
		txtpnCrditos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnCrditos.setText("Cr\u00E9ditos:\r\n-Alison Hidalgo Guerra\r\nGitHub: AlisonH17\r\n-Eriet Dario Armas Gonz\u00E1lez \r\nGitHub: KaitoKidAdventurine");
		txtpnCrditos.setBounds(140, 370, 254, 111);
		panel_13.add(txtpnCrditos);

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

		// INICIALIZAR MODELO DE TABLA (ya está)
		medicamentoTableModel = new MedicamentoTableModel();


		// CARGAR DATOS DESDE LA FARMACIA
		Farmacia.obtenerInstancia().inicializarDatosPrueba();

		// LLENAR EL MODELO CON LOS DATOS
		medicamentoTableModel.actualizar(Farmacia.obtenerInstancia().obtenerMedicamentos());


		JPanel medicamentos = new JPanel();
		pestanas.addTab("", null, medicamentos, null);
		medicamentos.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 395, 1005, 255);
		medicamentos.add(scrollPane_1);

		tablaMedicamentos = new JTable();
		scrollPane_1.setViewportView(tablaMedicamentos);

		// ASIGNAR MODELO A LA TABLA
		tablaMedicamentos.setModel(medicamentoTableModel);
		tablaMedicamentos.setRowHeight(28);


		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 1029, 43);
		panel_1.setBackground(Color.GREEN);
		medicamentos.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblComprar_1 = new JLabel("MEDICAMENTOS");
		lblComprar_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblComprar_1.setBounds(12, 0, 237, 43);
		panel_1.add(lblComprar_1);

		JLabel lblFiltrarTabla = new JLabel("Filtrar Tabla");
		lblFiltrarTabla.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblFiltrarTabla.setBounds(38, 39, 133, 43);
		medicamentos.add(lblFiltrarTabla);

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_8.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 128, 0)), new LineBorder(new Color(0, 0, 0))));
		panel_8.setBounds(12, 77, 184, 70);
		medicamentos.add(panel_8);
		panel_8.setLayout(null);

		JTextPane txtpnOrdenarNombresComunes = new JTextPane();
		txtpnOrdenarNombresComunes.setEditable(false);
		txtpnOrdenarNombresComunes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				medicamentoTableModel.actualizar(
						Farmacia.obtenerInstancia().obtenerMedicamentos()

						);
			}
		});
		txtpnOrdenarNombresComunes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnOrdenarNombresComunes.setText("Ordenar nombres comunes");
		txtpnOrdenarNombresComunes.setBounds(12, 13, 152, 44);
		panel_8.add(txtpnOrdenarNombresComunes);

		JPanel panel_9 = new JPanel();
		panel_9.setLayout(null);
		panel_9.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 128, 0)), new LineBorder(new Color(0, 0, 0))));
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(12, 153, 184, 70);
		medicamentos.add(panel_9);

		JTextPane txtpnOrdenarPreciosde = new JTextPane();
		txtpnOrdenarPreciosde.setEditable(false);
		txtpnOrdenarPreciosde.setText("Ordenar precios (de menor a mayor)");
		txtpnOrdenarPreciosde.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnOrdenarPreciosde.setBounds(12, 13, 160, 44);
		panel_9.add(txtpnOrdenarPreciosde);

		JPanel panel_10 = new JPanel();
		panel_10.setLayout(null);
		panel_10.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 128, 0)), new LineBorder(new Color(0, 0, 0))));
		panel_10.setBackground(Color.WHITE);
		panel_10.setBounds(12, 228, 184, 70);
		medicamentos.add(panel_10);

		JTextPane txtpnOrdenarPorPrximos = new JTextPane();
		txtpnOrdenarPorPrximos.setEditable(false);
		txtpnOrdenarPorPrximos.setText("Ordenar por pr\u00F3ximos a vencerse");
		txtpnOrdenarPorPrximos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnOrdenarPorPrximos.setBounds(12, 13, 160, 44);
		panel_10.add(txtpnOrdenarPorPrximos);

		JPanel panel_11 = new JPanel();
		panel_11.setLayout(null);
		panel_11.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 128, 0)), new LineBorder(new Color(0, 0, 0))));
		panel_11.setBackground(Color.WHITE);
		panel_11.setBounds(12, 303, 184, 70);
		medicamentos.add(panel_11);

		JTextPane txtpnSinOrdenar = new JTextPane();
		txtpnSinOrdenar.setEditable(false);
		txtpnSinOrdenar.setText("Sin ordenar");
		txtpnSinOrdenar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnSinOrdenar.setBounds(36, 23, 115, 34);
		panel_11.add(txtpnSinOrdenar);

		JLabel label_8 = new JLabel("|");
		label_8.setForeground(Color.GREEN);
		label_8.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_8.setBounds(208, -1, 56, 148);
		medicamentos.add(label_8);

		JLabel label_9 = new JLabel("|");
		label_9.setForeground(Color.GREEN);
		label_9.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_9.setBounds(208, 77, 56, 148);
		medicamentos.add(label_9);

		JLabel label_10 = new JLabel("|");
		label_10.setForeground(Color.GREEN);
		label_10.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_10.setBounds(208, 160, 56, 148);
		medicamentos.add(label_10);

		JLabel label_11 = new JLabel("|");
		label_11.setForeground(Color.GREEN);
		label_11.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_11.setBounds(208, 236, 56, 148);
		medicamentos.add(label_11);

		JLabel label_12 = new JLabel("-");
		label_12.setForeground(Color.GREEN);
		label_12.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_12.setBounds(0, 311, 56, 120);
		medicamentos.add(label_12);

		JLabel label_13 = new JLabel("-");
		label_13.setForeground(Color.GREEN);
		label_13.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_13.setBounds(22, 311, 56, 120);
		medicamentos.add(label_13);

		JLabel label_14 = new JLabel("-");
		label_14.setForeground(Color.GREEN);
		label_14.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_14.setBounds(41, 311, 56, 120);
		medicamentos.add(label_14);

		JLabel label_15 = new JLabel("-");
		label_15.setForeground(Color.GREEN);
		label_15.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_15.setBounds(68, 311, 56, 120);
		medicamentos.add(label_15);

		JLabel label_16 = new JLabel("-");
		label_16.setForeground(Color.GREEN);
		label_16.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_16.setBounds(90, 311, 56, 120);
		medicamentos.add(label_16);

		JLabel label_17 = new JLabel("-");
		label_17.setForeground(Color.GREEN);
		label_17.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_17.setBounds(115, 311, 56, 120);
		medicamentos.add(label_17);

		JLabel label_18 = new JLabel("-");
		label_18.setForeground(Color.GREEN);
		label_18.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_18.setBounds(136, 311, 56, 120);
		medicamentos.add(label_18);

		JLabel label_19 = new JLabel("-");
		label_19.setForeground(Color.GREEN);
		label_19.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_19.setBounds(155, 311, 56, 120);
		medicamentos.add(label_19);

		JLabel label_20 = new JLabel("-");
		label_20.setForeground(Color.GREEN);
		label_20.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_20.setBounds(183, 311, 56, 120);
		medicamentos.add(label_20);

		JLabel label_21 = new JLabel("-");
		label_21.setForeground(Color.GREEN);
		label_21.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_21.setBounds(208, 311, 56, 120);
		medicamentos.add(label_21);

		JLabel label_22 = new JLabel("-");
		label_22.setForeground(Color.GREEN);
		label_22.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_22.setBounds(218, 311, 56, 120);
		medicamentos.add(label_22);

		JLabel label_23 = new JLabel("-");
		label_23.setForeground(Color.GREEN);
		label_23.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_23.setBounds(238, 311, 56, 120);
		medicamentos.add(label_23);

		JLabel label_24 = new JLabel("-");
		label_24.setForeground(Color.GREEN);
		label_24.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_24.setBounds(261, 311, 56, 120);
		medicamentos.add(label_24);

		JLabel label_25 = new JLabel("-");
		label_25.setForeground(Color.GREEN);
		label_25.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_25.setBounds(276, 311, 56, 120);
		medicamentos.add(label_25);

		JLabel label_26 = new JLabel("-");
		label_26.setForeground(Color.GREEN);
		label_26.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_26.setBounds(306, 311, 56, 120);
		medicamentos.add(label_26);

		JLabel label_27 = new JLabel("-");
		label_27.setForeground(Color.GREEN);
		label_27.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_27.setBounds(326, 311, 56, 120);
		medicamentos.add(label_27);

		JLabel label_28 = new JLabel("-");
		label_28.setForeground(Color.GREEN);
		label_28.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_28.setBounds(344, 311, 56, 120);
		medicamentos.add(label_28);

		JLabel label_29 = new JLabel("-");
		label_29.setForeground(Color.GREEN);
		label_29.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_29.setBounds(276, 311, 56, 120);
		medicamentos.add(label_29);

		JLabel label_30 = new JLabel("-");
		label_30.setForeground(Color.GREEN);
		label_30.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_30.setBounds(287, 311, 56, 120);
		medicamentos.add(label_30);

		JLabel label_31 = new JLabel("-");
		label_31.setForeground(Color.GREEN);
		label_31.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_31.setBounds(977, 311, 56, 120);
		medicamentos.add(label_31);

		JLabel label_32 = new JLabel("-");
		label_32.setForeground(Color.GREEN);
		label_32.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_32.setBounds(355, 311, 56, 120);
		medicamentos.add(label_32);

		JLabel label_33 = new JLabel("-");
		label_33.setForeground(Color.GREEN);
		label_33.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_33.setBounds(374, 311, 56, 120);
		medicamentos.add(label_33);

		JLabel label_34 = new JLabel("-");
		label_34.setForeground(Color.GREEN);
		label_34.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_34.setBounds(399, 311, 56, 120);
		medicamentos.add(label_34);

		JLabel label_35 = new JLabel("-");
		label_35.setForeground(Color.GREEN);
		label_35.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_35.setBounds(423, 311, 56, 120);
		medicamentos.add(label_35);

		JLabel label_36 = new JLabel("-");
		label_36.setForeground(Color.GREEN);
		label_36.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_36.setBounds(451, 311, 56, 120);
		medicamentos.add(label_36);

		JLabel label_37 = new JLabel("-");
		label_37.setForeground(Color.GREEN);
		label_37.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_37.setBounds(467, 311, 56, 120);
		medicamentos.add(label_37);

		JLabel label_38 = new JLabel("-");
		label_38.setForeground(Color.GREEN);
		label_38.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_38.setBounds(491, 311, 56, 120);
		medicamentos.add(label_38);

		JLabel label_39 = new JLabel("-");
		label_39.setForeground(Color.GREEN);
		label_39.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_39.setBounds(519, 311, 56, 120);
		medicamentos.add(label_39);

		JLabel label_40 = new JLabel("-");
		label_40.setForeground(Color.GREEN);
		label_40.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_40.setBounds(547, 311, 56, 120);
		medicamentos.add(label_40);

		JLabel label_41 = new JLabel("-");
		label_41.setForeground(Color.GREEN);
		label_41.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_41.setBounds(574, 311, 56, 120);
		medicamentos.add(label_41);

		JLabel label_42 = new JLabel("-");
		label_42.setForeground(Color.GREEN);
		label_42.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_42.setBounds(602, 311, 56, 120);
		medicamentos.add(label_42);

		JLabel label_43 = new JLabel("-");
		label_43.setForeground(Color.GREEN);
		label_43.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_43.setBounds(627, 311, 56, 120);
		medicamentos.add(label_43);

		JLabel label_44 = new JLabel("-");
		label_44.setForeground(Color.GREEN);
		label_44.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_44.setBounds(655, 311, 56, 120);
		medicamentos.add(label_44);

		JLabel label_45 = new JLabel("-");
		label_45.setForeground(Color.GREEN);
		label_45.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_45.setBounds(681, 311, 56, 120);
		medicamentos.add(label_45);

		JLabel label_46 = new JLabel("-");
		label_46.setBackground(new Color(204, 255, 204));
		label_46.setForeground(Color.GREEN);
		label_46.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_46.setBounds(708, 311, 56, 120);
		medicamentos.add(label_46);

		JLabel label_47 = new JLabel("-");
		label_47.setForeground(Color.GREEN);
		label_47.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_47.setBounds(734, 311, 56, 120);
		medicamentos.add(label_47);

		JLabel label_48 = new JLabel("-");
		label_48.setForeground(Color.GREEN);
		label_48.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_48.setBounds(760, 311, 56, 120);
		medicamentos.add(label_48);

		JLabel label_49 = new JLabel("-");
		label_49.setForeground(Color.GREEN);
		label_49.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_49.setBounds(789, 311, 56, 120);
		medicamentos.add(label_49);

		JLabel label_50 = new JLabel("-");
		label_50.setForeground(Color.GREEN);
		label_50.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_50.setBounds(811, 311, 56, 120);
		medicamentos.add(label_50);

		JLabel label_51 = new JLabel("-");
		label_51.setForeground(Color.GREEN);
		label_51.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_51.setBounds(841, 311, 56, 120);
		medicamentos.add(label_51);

		JLabel label_52 = new JLabel("-");
		label_52.setForeground(Color.GREEN);
		label_52.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_52.setBounds(857, 311, 56, 120);
		medicamentos.add(label_52);

		JLabel label_53 = new JLabel("-");
		label_53.setForeground(Color.GREEN);
		label_53.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_53.setBounds(879, 311, 56, 120);
		medicamentos.add(label_53);

		JLabel label_54 = new JLabel("-");
		label_54.setForeground(Color.GREEN);
		label_54.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_54.setBounds(909, 311, 56, 120);
		medicamentos.add(label_54);

		JLabel label_55 = new JLabel("-");
		label_55.setForeground(Color.GREEN);
		label_55.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_55.setBounds(925, 311, 56, 120);
		medicamentos.add(label_55);

		JLabel label_56 = new JLabel("-");
		label_56.setForeground(Color.GREEN);
		label_56.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_56.setBounds(949, 311, 56, 120);
		medicamentos.add(label_56);

		JLabel label_57 = new JLabel("-");
		label_57.setForeground(Color.GREEN);
		label_57.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_57.setBounds(893, 311, 56, 120);
		medicamentos.add(label_57);

		JLabel label_58 = new JLabel("-");
		label_58.setForeground(Color.GREEN);
		label_58.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_58.setBounds(825, 311, 56, 120);
		medicamentos.add(label_58);

		JLabel label_59 = new JLabel("-");
		label_59.setForeground(Color.GREEN);
		label_59.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_59.setBounds(776, 311, 56, 120);
		medicamentos.add(label_59);

		JLabel label_60 = new JLabel("|");
		label_60.setForeground(Color.GREEN);
		label_60.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_60.setBounds(208, 249, 56, 148);
		medicamentos.add(label_60);

		JLabel lblNombreComn = new JLabel("Nombre Com\u00FAn");
		lblNombreComn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreComn.setBounds(261, 77, 139, 20);
		medicamentos.add(lblNombreComn);

		JLabel lblNombreCientfico = new JLabel("Nombre Cient\u00EDfico");
		lblNombreCientfico.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreCientfico.setBounds(261, 142, 139, 20);
		medicamentos.add(lblNombreCientfico);

		JLabel lblPresentacin = new JLabel("Presentaci\u00F3n");
		lblPresentacin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPresentacin.setBounds(261, 209, 139, 20);
		medicamentos.add(lblPresentacin);

		textNombreComun = new JTextField();
		textNombreComun.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c!=' '){
					e.consume();
				}
			}
		});
		textNombreComun.setBounds(261, 96, 169, 22);
		medicamentos.add(textNombreComun);
		textNombreComun.setColumns(10);

		textNombreCientifico = new JTextField();
		textNombreCientifico.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c!=' '){
					e.consume();
				}
			}
		});
		textNombreCientifico.setColumns(10);
		textNombreCientifico.setBounds(261, 160, 169, 22);
		medicamentos.add(textNombreCientifico);

		textPresentacion = new JTextField();
		textPresentacion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c!=' '){
					e.consume();
				}
			}
		});
		textPresentacion.setColumns(10);
		textPresentacion.setBounds(261, 228, 169, 22);
		medicamentos.add(textPresentacion);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPrecio.setBounds(261, 278, 139, 20);
		medicamentos.add(lblPrecio);

		JLabel lblTipo = new JLabel("Tipo");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTipo.setBounds(467, 77, 139, 20);
		medicamentos.add(lblTipo);

		JLabel lblFortaleza = new JLabel("Fortaleza");
		lblFortaleza.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFortaleza.setBounds(467, 142, 139, 20);
		medicamentos.add(lblFortaleza);

		JLabel lblTemperatura = new JLabel("Temperatura");
		lblTemperatura.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTemperatura.setBounds(467, 209, 139, 20);
		medicamentos.add(lblTemperatura);

		JLabel lblCantidadExistente = new JLabel("Cantidad existente");
		lblCantidadExistente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidadExistente.setBounds(464, 278, 139, 20);
		medicamentos.add(lblCantidadExistente);

		JLabel lblFechaDeProduccin = new JLabel("Fecha de Producci\u00F3n");
		lblFechaDeProduccin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeProduccin.setBounds(681, 77, 164, 20);
		medicamentos.add(lblFechaDeProduccin);

		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(261, 301, 169, 22);
		medicamentos.add(textPrecio);

		textTipo = new JTextField();
		textTipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isLetter(c) && c!=' '){
					e.consume();
				}
			}
		});
		textTipo.setColumns(10);
		textTipo.setBounds(467, 96, 169, 22);
		medicamentos.add(textTipo);

		textFortaleza = new JTextField();
		textFortaleza.setColumns(10);
		textFortaleza.setBounds(467, 160, 169, 22);
		medicamentos.add(textFortaleza);

		textTemperatura = new JTextField();
		textTemperatura.setColumns(10);
		textTemperatura.setBounds(467, 228, 169, 22);
		medicamentos.add(textTemperatura);

		textCantidad = new JTextField();
		textCantidad.setColumns(10);
		textCantidad.setBounds(467, 303, 169, 22);
		medicamentos.add(textCantidad);

		textFechaProduccion = new JTextField();
		textFechaProduccion.setColumns(10);
		textFechaProduccion.setBounds(681, 96, 169, 22);
		medicamentos.add(textFechaProduccion);

		JLabel lblFechaDeVencimiento = new JLabel("Fecha de Vencimiento");
		lblFechaDeVencimiento.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblFechaDeVencimiento.setBounds(681, 142, 164, 20);
		medicamentos.add(lblFechaDeVencimiento);

		textFechaVencimiento = new JTextField();
		textFechaVencimiento.setColumns(10);
		textFechaVencimiento.setBounds(681, 160, 169, 22);
		medicamentos.add(textFechaVencimiento);

		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(204, 255, 204));
		panel_12.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 100, 0)), new LineBorder(new Color(0, 0, 0))));
		panel_12.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				try {
					// Obtener los valores de los campos
					String txtNombreComun = textNombreComun.getText();
					String txtNombreCientifico = textNombreCientifico.getText();
					String txtPresentacion = textPresentacion.getText();
					double txtPrecio = Double.parseDouble(textPrecio.getText());
					String txtTipo = textTipo.getText();
					String txtFortaleza = textFortaleza.getText();
					double txtTemperatura = Double.parseDouble(textTemperatura.getText());
					long txtCantidad = Long.parseLong(textCantidad.getText());
					String txtFechaProduccion = textFechaProduccion.getText();
					String txtFechaVencimiento = textFechaVencimiento.getText();
					String codigoDelMed = textcodigoDelMed.getText();
					// Validación de campos obligatorios
					if (txtNombreComun.isEmpty() || 
							txtNombreCientifico.isEmpty() ||
							txtPresentacion.isEmpty()) {
						JOptionPane.showMessageDialog(null, 
								"Nombre común, nombre científico y presentación son obligatorios", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Validación de valores positivos
					if (txtPrecio <= 0 || txtCantidad <= 0) {
						JOptionPane.showMessageDialog(null, 
								"Precio y cantidad deben ser valores positivos", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Formato esperado de fecha
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date fechaProd = sdf.parse(txtFechaProduccion);
					Date fechaVenc = sdf.parse(txtFechaVencimiento);

					// Crear medicamento
					Medicamento nuevoMed = new Medicamento(
							txtNombreComun, txtNombreCientifico, txtPresentacion,
							txtPrecio, txtTipo, txtFortaleza, txtTemperatura,
							txtCantidad, fechaProd, fechaVenc, codigoDelMed
							);

					// Agregar a la farmacia
					LocalDate Exped = LocalDate.parse(txtFechaProduccion);
					LocalDate Venc = LocalDate.parse(txtFechaVencimiento);



					Farmacia.obtenerInstancia().agregarMedicamento
					(
							txtNombreComun, txtNombreCientifico, txtPresentacion,
							txtPrecio, txtTipo, txtFortaleza, txtTemperatura,
							txtCantidad, Exped, Venc, codigoDelMed 
							);

					Medicamento m = Farmacia.obtenerInstancia().obtenerMed(txtNombreComun, txtNombreCientifico, txtPresentacion,
							txtPrecio, txtTipo, txtFortaleza, txtTemperatura,
							txtCantidad, Exped, Venc, codigoDelMed);

					// Actualizar tabla
					medicamentoTableModel.actualizar(Farmacia.obtenerInstancia().obtenerMedicamentos());


					// Mensaje de éxito
					JOptionPane.showMessageDialog(null, 
							"Medicamento agregado exitosamente!\n" +
									"Código generado: " +m.getCodigo(), 
									"Éxito", JOptionPane.INFORMATION_MESSAGE);

					// Limpiar campos
					limpiarCampos();

				} catch (ParseException ex) {
					JOptionPane.showMessageDialog(null, 
							"Formato de fecha inválido. Use dd/MM/yyyy", 
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, 
							"Error en formato numérico: " + ex.getMessage(), 
							"Error", JOptionPane.ERROR_MESSAGE);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, 
							"Error al agregar medicamento: " + ex.getMessage(), 
							"Error", JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});
		panel_12.setBounds(673, 266, 245, 59);
		medicamentos.add(panel_12);
		panel_12.setLayout(null);

		JLabel lblAgregarMedicamento = new JLabel("Agregar Medicamento");
		lblAgregarMedicamento.setBounds(12, 13, 213, 27);
		panel_12.add(lblAgregarMedicamento);
		lblAgregarMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 22));

		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(681, 203, 69, 20);
		medicamentos.add(lblCodigo);

		textcodigoDelMed = new JTextField();
		textcodigoDelMed.setBounds(681, 228, 164, 22);
		medicamentos.add(textcodigoDelMed);
		textcodigoDelMed.setColumns(10);

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

		JScrollPane scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(59, 218, 800, 381);
		reporte1.add(scrollPane_11);

		tablaMasVendidos = new JTable();
		scrollPane_11.setViewportView(tablaMasVendidos);
		// Inicializar modelo
		medicamentosMasVendidosModel = new MedicamentosMasVendidosTableModel();
		tablaMasVendidos.setModel(medicamentosMasVendidosModel);
		tablaMasVendidos.setRowHeight(28);

		// Cargar datos
		List<VentaDeMedicamentos> masVendidos = Farmacia.obtenerInstancia().medicamentosMasVendidos();
		medicamentosMasVendidosModel.actualizar(masVendidos);

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

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(49, 286, 846, 125);
		reporte2.add(scrollPane_2);

		tablaAlmohadillas = new JTable();
		scrollPane_2.setViewportView(tablaAlmohadillas);

		// Obtener datos
		long necesarias = Farmacia.obtenerInstancia().cantDeAlmohadillasNecesarias();
		long stock = Farmacia.obtenerInstancia().getCantidadDeAlmohadillasSanitarias();

		// Configurar modelo
		AlmohadillasNecesariasTableModel modelAlmohadillas = new AlmohadillasNecesariasTableModel(necesarias, stock);
		tablaAlmohadillas.setModel(modelAlmohadillas);
		tablaAlmohadillas.setRowHeight(48);

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

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(37, 219, 799, 331);
		reporte3.add(scrollPane_3);

		tablaComparacionVentas = new JTable();
		scrollPane_3.setViewportView(tablaComparacionVentas);

		// Inicializar modelo
		comparacionVentasModel = new ComparacionVentasTableModel();
		tablaComparacionVentas.setModel(comparacionVentasModel); 
		tablaComparacionVentas.setRowHeight(28);

		// Cargar datos
		comparacionVentasModel.cargarPorcentajes(Farmacia.obtenerInstancia().comparacionDeVentasMensuales());
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


		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(111, 210, 781, 299);
		reporte4.add(scrollPane_4);

		tablaTarjetones = new JTable();
		scrollPane_4.setViewportView(tablaTarjetones);
		tarjetonesModel = new TarjetonesIncumplidosTableModel();
		tablaTarjetones.setModel(tarjetonesModel);
		tarjetonesModel.actualizar(Farmacia.obtenerInstancia().registroDeIncumplimiento());


		JLabel lblNewLabel_5 = new JLabel("New label"); 
		lblNewLabel_5.setBounds(307, 16, 69, 20); 
		panel_7.add(lblNewLabel_5);

	}
}