package Interfaz;

import Logica.Farmacia;
import Logica.Medicamento;
import Logica.MedicamentoControlado;
import Logica.NucleoFamiliar;
import Logica.Paciente;
import LogicaUtiles.Porcentaje;
import LogicaUtiles.VentaDeMedicamentos;
import modelos.AlmohadillasNecesariasTableModel;
import modelos.AlmohadillasTableModel;
import modelos.ButtonRenderer;
import modelos.ComparacionVentasTableModel;
import modelos.CompraControladaTableModel;
import modelos.ComprasTableModel;
import modelos.MedicamentoTableModel;
import modelos.MedicamentosComboBoxModel;
import modelos.MedicamentosControladosComboBoxModel;
import modelos.MedicamentosMasVendidosTableModel;
import modelos.ModeloPrincipalTableModel;
import modelos.NucleosComboBoxModel;
import modelos.PacientesControladosComboBoxModel;
import modelos.TablaDePacientes;
import modelos.PacientesComboBoxModel;
import modelos.TarjetonesIncumplidosTableModel;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
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

import Utiles.Navegacion;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.DefaultComboBoxModel;

import java.awt.ComponentOrientation;

public class PrincipalAdmin extends JFrame 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	private TablaDePacientes tablaPacientes;
	private JTable tablaCompras;
	private JTextField txtTotal;
	private JTextField txtEfectivo;
	private JTextField txtCambio;
	private PacientesComboBoxModel pacientesComboBoxModel;
	private ComprasTableModel comprasTableModel;
	// Mapa para registrar las cantidades acumuladas de cada medicamento
	private Map<String, Integer> cantidadesAcumuladas = new HashMap<>();
	private boolean pacienteSeleccionado = false;
	private boolean cambioCalculado = false;
	private JComboBox<String> comboBoxPacientes;
	private JComboBox<String> comboBoxMedicamentos;
	private JComboBox <Integer>comboBoxCantidad;
	private JScrollPane scrollPane_6;
	private JTextField textTotal;
	private JTextField textEfectivo;
	private JTextField textCambio;
	private JLabel lblMujeres;
	private NucleosComboBoxModel nucleosComboBoxModel;
	private AlmohadillasTableModel modeloAlmohadillas;
	private JTable tableALmohadillas;
	private NucleoFamiliar nucleo;
	private JComboBox <String>comboBoxNucleos;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private PacientesControladosComboBoxModel modeloPacientesControlados;
	private JTable tablaControlados;
	private MedicamentosControladosComboBoxModel modeloMedControlados;
	private CompraControladaTableModel compraControladaTableModel ;
	private Medicamento medicamento;


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
	}

	private void actualizarTotal(JTextField txtTotal, ComprasTableModel comprasTableModel) 
	{
		double total = 0.0;

		for (int i = 0; i < comprasTableModel.getRowCount(); i++) {
			double precioMedicamento = (double) comprasTableModel.getValueAt(i, 2);
			int cantidad = (int) comprasTableModel.getValueAt(i, 3);

			total += precioMedicamento * cantidad;
		}

		txtTotal.setText(String.format("%.2f", total)); // Actualizar el campo de texto con el total
	}

	// Método para restar la cantidad eliminada del registro de cantidades acumuladas
	private void restarCantidadAcumulada(String nombreMedicamento, int cantidadEliminada) {
		int cantidadAcumuladaActual = cantidadesAcumuladas.getOrDefault(nombreMedicamento, 0);
		int nuevaCantidadAcumulada = cantidadAcumuladaActual - cantidadEliminada;

		if (nuevaCantidadAcumulada <= 0) {
			cantidadesAcumuladas.remove(nombreMedicamento); // Eliminar si llega a cero
		} else {
			cantidadesAcumuladas.put(nombreMedicamento, nuevaCantidadAcumulada); // Actualizar
		}
	}

	private boolean hayComprasEnLaTabla() {
		return comprasTableModel.getRowCount() > 0;
	}
	


	private boolean comproNucleo(){
		return modeloAlmohadillas.getRowCount() > 0 ;
	}

	private void actualizarTotal(JTextField textTotal, AlmohadillasTableModel modeloAlmohadillas) {
		double total = 0.0;

		// Recorrer todas las filas de la tabla
		for (int i = 0; i < modeloAlmohadillas.getRowCount(); i++) {
			// Obtener el valor de la columna "Total" (índice 4)
			double importe = (double) modeloAlmohadillas.getValueAt(i, 4);
			total += importe;
		}

		// Actualizar el campo de texto con el total acumulado
		textTotal.setText(String.format("%.2f", total));
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
		barraSuperior.setBorder(new LineBorder(new Color(0, 0, 0), 4));
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


		barraSuperior.setBackground(new Color(0, 255, 127));
		barraSuperior.setBounds(-15, -12, 1346, 76);
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
			public void mouseEntered(MouseEvent arg0) 
			{
				UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit1.png");
			}
			@Override
			public void mouseExited(MouseEvent arg0) 
			{
				UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
			}
		});
		cruz.setBounds(1228, 13, 77, 58);
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
		minimizar.setBounds(1152, 13, 77, 58);
		barraSuperior.add(minimizar);
		UtilesInterfaz.ajustarImagen(minimizar, "src/iconos/minimize0.png");

		JLabel lblNewLabel_1 = new JLabel("Sistema de Gesti\u00F3n de Procesos en las Farmacia");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel_1.setBounds(26, 18, 681, 45);
		barraSuperior.add(lblNewLabel_1);

		JPanel menu = new JPanel();
		menu.setBackground(new Color(152, 251, 152));
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
				panel1.setBackground(new Color(75, 255, 112));

			}
			@Override
			public void mouseExited(MouseEvent e) 
			{
				panel1.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				pestanas.setSelectedIndex(0);
			}
		});
		panel1.setBackground(new Color(152, 251, 152));
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
		panel2.setBackground(new Color(152, 251, 152));
		panel2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel2.setBackground(new Color(75, 255, 112));

			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel2.setBackground(new Color(152, 251, 152));
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
			public void mouseClicked(MouseEvent e) {
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
				panel3.setBackground(new Color(75, 255, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel3.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(2);
			}
		});
		panel3.setBackground(new Color(152, 251, 152));
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
			public void mouseEntered(MouseEvent e) {
				panel4.setBackground(new Color(75, 255, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel4.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(3);
			}
		});
		panel4.setBackground(new Color(152, 251, 152));
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

		final JPanel panel_14 = new JPanel();
		panel_14.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_14.setBackground(new Color(75, 255, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_14.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(12);
			}
		});
		panel_14.setBackground(new Color(152, 251, 152));
		panel_14.setBounds(0, 460, 294, 68);
		menu.add(panel_14);
		panel_14.setLayout(null);

		JLabel lblUsuarios = new JLabel("USUARIOS");
		lblUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(12);
			}
		});
		lblUsuarios.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblUsuarios.setBounds(74, 13, 165, 42);
		panel_14.add(lblUsuarios);

		JLabel iconoUsuarios = new JLabel("");
		iconoUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconoUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(12);
			}
		});
		iconoUsuarios.setBounds(12, 13, 42, 42);
		panel_14.add(iconoUsuarios);
		UtilesInterfaz.ajustarImagen(iconoUsuarios, "src/iconos/usuario(1).png");

		final JPanel panel_15 = new JPanel();
		panel_15.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_15.setBackground(new Color(75, 255, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_15.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener el JFrame/JDialog padre del componente que disparó el evento
				Window ventanaPadre = SwingUtilities.getWindowAncestor((Component) e.getSource());

				int opcion = JOptionPane.showConfirmDialog(
						ventanaPadre, // Usamos la ventana padre obtenida
						"¿Estás seguro de cerrar sesión?",
						"Confirmar",
						JOptionPane.YES_NO_OPTION
						);

				if (opcion == JOptionPane.YES_OPTION) {
					ventanaPadre.dispose(); // Cierra la ventana actual

					// Regresar al login
					Navegacion.registrar("Login", new Login());
					Navegacion.irA("Login");
				}
			}
		});
		panel_15.setBackground(new Color(152, 251, 152));
		panel_15.setBounds(0, 541, 294, 75);
		menu.add(panel_15);
		panel_15.setLayout(null);

		JLabel lblCerrarSesin = new JLabel("CERRAR SESI\u00D3N");
		lblCerrarSesin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCerrarSesin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener el JFrame/JDialog padre del componente que disparó el evento
				Window ventanaPadre = SwingUtilities.getWindowAncestor((Component) e.getSource());

				int opcion = JOptionPane.showConfirmDialog(
						ventanaPadre, // Usamos la ventana padre obtenida
						"¿Estás seguro de cerrar sesión?",
						"Confirmar",
						JOptionPane.YES_NO_OPTION
						);

				if (opcion == JOptionPane.YES_OPTION) {
					ventanaPadre.dispose(); // Cierra la ventana actual

					// Regresar al login
					Navegacion.registrar("Login", new Login());
					Navegacion.irA("Login");
				}
			}
		});
		lblCerrarSesin.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblCerrarSesin.setBounds(71, 13, 238, 49);
		panel_15.add(lblCerrarSesin);

		JLabel iconoCerrarSesion = new JLabel("");
		iconoCerrarSesion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconoCerrarSesion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener el JFrame/JDialog padre del componente que disparó el evento
				Window ventanaPadre = SwingUtilities.getWindowAncestor((Component) e.getSource());

				int opcion = JOptionPane.showConfirmDialog(
						ventanaPadre, // Usamos la ventana padre obtenida
						"¿Estás seguro de cerrar sesión?",
						"Confirmar",
						JOptionPane.YES_NO_OPTION
						);

				if (opcion == JOptionPane.YES_OPTION) {
					ventanaPadre.dispose(); // Cierra la ventana actual

					// Regresar al login
					Navegacion.registrar("Login", new Login());
					Navegacion.irA("Login");
				}
			}
		});
		iconoCerrarSesion.setBounds(12, 13, 47, 37);
		panel_15.add(iconoCerrarSesion);
		UtilesInterfaz.ajustarImagen(iconoCerrarSesion, "src/iconos/cierre-de-sesion-de-usuario.png");

		pestanas = new JTabbedPane(JTabbedPane.TOP);
		pestanas.setBackground(Color.WHITE);
		pestanas.setBounds(292, 33, 1010, 684);
		contentPane.add(pestanas);

		JPanel principal = new JPanel();
		principal.setBackground(Color.WHITE);
		pestanas.addTab("", null, principal, null);
		principal.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(75, 255, 112));
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
		txtpnBeneficioDeAdministrador.setEditable(false);
		txtpnBeneficioDeAdministrador.setBackground(Color.WHITE);
		txtpnBeneficioDeAdministrador.setDisabledTextColor(Color.BLACK);
		txtpnBeneficioDeAdministrador.setEnabled(false);
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
		txtpnEnNuestroSistema.setBackground(Color.WHITE);
		txtpnEnNuestroSistema.setDisabledTextColor(Color.BLACK);
		txtpnEnNuestroSistema.setEnabled(false);
		txtpnEnNuestroSistema.setEditable(false);
		txtpnEnNuestroSistema.setFont(new Font("Tahoma", Font.PLAIN, 24));
		txtpnEnNuestroSistema.setText("En nuestro sistema podr\u00E1:");
		txtpnEnNuestroSistema.setBounds(12, 13, 461, 66);
		panel_13.add(txtpnEnNuestroSistema);

		JTextPane txtpnRealizarLaCompra = new JTextPane();
		txtpnRealizarLaCompra.setBackground(Color.WHITE);
		txtpnRealizarLaCompra.setEnabled(false);
		txtpnRealizarLaCompra.setEditable(false);
		txtpnRealizarLaCompra.setDisabledTextColor(Color.BLACK);
		txtpnRealizarLaCompra.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnRealizarLaCompra.setText("Realizar la compra de medicamentos por 3 Tipos de ventas y la compra de almohadillas sanitarias para las mujeres de su n\u00FAcleo familiar ");
		txtpnRealizarLaCompra.setBounds(10, 71, 442, 80);
		panel_13.add(txtpnRealizarLaCompra);

		JTextPane txtpnConsultarLaDisponibilidad = new JTextPane();
		txtpnConsultarLaDisponibilidad.setBackground(Color.WHITE);
		txtpnConsultarLaDisponibilidad.setDisabledTextColor(Color.BLACK);
		txtpnConsultarLaDisponibilidad.setEnabled(false);
		txtpnConsultarLaDisponibilidad.setEditable(false);
		txtpnConsultarLaDisponibilidad.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnConsultarLaDisponibilidad.setText("Consultar la disponibilidad de medicamentos para realizar la compra exitosa de ellos");
		txtpnConsultarLaDisponibilidad.setBounds(10, 179, 440, 122);
		panel_13.add(txtpnConsultarLaDisponibilidad);

		JLabel label_65 = new JLabel("_____________");
		label_65.setFont(new Font("Tahoma", Font.PLAIN, 69));
		label_65.setBounds(0, 276, 511, 97);
		panel_13.add(label_65);

		JTextPane txtpnCrditos = new JTextPane();
		txtpnCrditos.setBackground(Color.WHITE);
		txtpnCrditos.setDisabledTextColor(Color.BLACK);
		txtpnCrditos.setEnabled(false);
		txtpnCrditos.setEditable(false);
		txtpnCrditos.setFont(new Font("Tahoma", Font.PLAIN, 17));
		txtpnCrditos.setText("Cr\u00E9ditos:\r\n-Alison Hidalgo Guerra\r\nGitHub: AlisonH17\r\n-Eriet Dario Armas Gonz\u00E1lez \r\nGitHub: KaitoKidAdventurine");
		txtpnCrditos.setBounds(140, 370, 254, 111);
		panel_13.add(txtpnCrditos);

		JPanel comprar = new JPanel();
		comprar.setBackground(Color.WHITE);
		pestanas.addTab("", null, comprar, null);
		comprar.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 1029, 43);
		panel_3.setBackground(new Color(75, 255, 112));
		comprar.add(panel_3);
		panel_3.setLayout(null);

		JLabel lblNewLabel = new JLabel("COMPRAR");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblNewLabel.setBounds(12, 0, 144, 43);
		panel_3.add(lblNewLabel);

		JLabel lblElijaComoDesea = new JLabel("Elija como desea realizar su compra");
		lblElijaComoDesea.setBounds(10, 49, 477, 43);
		lblElijaComoDesea.setFont(new Font("Times New Roman", Font.BOLD, 28));
		comprar.add(lblElijaComoDesea);

		Label label_7 = new Label("_______________________________________________________________________");
		label_7.setBounds(0, 37, 1005, 79);
		label_7.setForeground(new Color(75, 255, 112));
		label_7.setFont(new Font("Arial Black", Font.BOLD, 50));
		comprar.add(label_7);

		JPanel compra_1 = new JPanel();
		compra_1.setBounds(96, 128, 353, 200);
		compra_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(8);
			}
		});
		compra_1.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra_1.setBackground(new Color(150, 255, 147));
		comprar.add(compra_1);
		compra_1.setLayout(null);

		JLabel label_67 = new JLabel("_______________________");
		label_67.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_67.setBounds(0, 13, 381, 50);
		compra_1.add(label_67);

		JLabel lblVentaLibre_1 = new JLabel("Venta Libre");
		lblVentaLibre_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblVentaLibre_1.setBounds(12, 13, 192, 43);
		compra_1.add(lblVentaLibre_1);

		JLabel lblVentaDeMedicamentos = new JLabel("Venta de medicamentos que no requieren");
		lblVentaDeMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVentaDeMedicamentos.setBounds(10, 13, 402, 119);
		compra_1.add(lblVentaDeMedicamentos);

		JLabel lblDeTarjetnO = new JLabel("de tarjet\u00F3n o receta m\u00E9dica");
		lblDeTarjetnO.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeTarjetnO.setBounds(10, 82, 317, 22);
		compra_1.add(lblDeTarjetnO);

		JPanel compra_3 = new JPanel();
		compra_3.setBounds(96, 369, 353, 200);
		compra_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(10);
			}
		});
		compra_3.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra_3.setBackground(new Color(150, 255, 147));
		comprar.add(compra_3);
		compra_3.setLayout(null);

		JLabel label_69 = new JLabel("_______________________");
		label_69.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_69.setBounds(0, 13, 381, 50);
		compra_3.add(label_69);

		JLabel lblVentaMedicamentoControlado = new JLabel("Venta Medicamento Controlado");
		lblVentaMedicamentoControlado.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblVentaMedicamentoControlado.setBounds(12, 13, 341, 43);
		compra_3.add(lblVentaMedicamentoControlado);

		JLabel lblVentaDeMedicamentos_1 = new JLabel("Venta de medicamentos que requieren");
		lblVentaDeMedicamentos_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVentaDeMedicamentos_1.setBounds(10, 53, 331, 22);
		compra_3.add(lblVentaDeMedicamentos_1);

		JLabel lblDeUnTarjetn = new JLabel("de un tarjet\u00F3n");
		lblDeUnTarjetn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeUnTarjetn.setBounds(10, 76, 210, 22);
		compra_3.add(lblDeUnTarjetn);

		JPanel compra_2 = new JPanel();
		compra_2.setBounds(544, 128, 353, 200);
		compra_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(9);
			}
		});
		compra_2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra_2.setBackground(new Color(150, 255, 147));
		comprar.add(compra_2);
		compra_2.setLayout(null);

		JLabel label_68 = new JLabel("_______________________");
		label_68.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_68.setBounds(0, 13, 381, 50);
		compra_2.add(label_68);

		JLabel lblVentaAlmohadillasSanitarias = new JLabel("Venta Almohadillas Sanitarias");
		lblVentaAlmohadillasSanitarias.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblVentaAlmohadillasSanitarias.setBounds(12, 13, 329, 43);
		compra_2.add(lblVentaAlmohadillasSanitarias);

		JLabel lblVentaDeAlmohadillas = new JLabel("Venta de almohadillas sanitarias para");
		lblVentaDeAlmohadillas.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVentaDeAlmohadillas.setBounds(10, 57, 331, 29);
		compra_2.add(lblVentaDeAlmohadillas);

		JLabel lblLasMujeresDel = new JLabel("las mujeres del n\u00FAcleo familiar");
		lblLasMujeresDel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLasMujeresDel.setBounds(10, 69, 316, 41);
		compra_2.add(lblLasMujeresDel);

		JPanel compra_4 = new JPanel();
		compra_4.setBounds(544, 369, 353, 200);
		compra_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(11);
			}
		});
		compra_4.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		compra_4.setBackground(new Color(150, 255, 147));
		comprar.add(compra_4);
		compra_4.setLayout(null);

		JLabel label_70 = new JLabel("_______________________");
		label_70.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_70.setBounds(0, 13, 381, 50);
		compra_4.add(label_70);

		JLabel lblVentaPrescripcinMdica = new JLabel("Venta Prescripci\u00F3n M\u00E9dica");
		lblVentaPrescripcinMdica.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblVentaPrescripcinMdica.setBounds(12, 13, 329, 43);
		compra_4.add(lblVentaPrescripcinMdica);

		JLabel lblVentaDeMedicamentos_2 = new JLabel("Venta de medicamentos que requieren");
		lblVentaDeMedicamentos_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVentaDeMedicamentos_2.setBounds(10, 58, 331, 22);
		compra_4.add(lblVentaDeMedicamentos_2);

		JLabel lblDeUnaReceta = new JLabel("de una receta m\u00E9dica");
		lblDeUnaReceta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDeUnaReceta.setBounds(10, 79, 314, 22);
		compra_4.add(lblDeUnaReceta);

		// INICIALIZAR MODELO DE TABLA 
		medicamentoTableModel = new MedicamentoTableModel();


		// CARGAR DATOS DESDE LA FARMACIA
		Farmacia.obtenerInstancia().inicializarDatosPrueba();

		// LLENAR EL MODELO CON LOS DATOS
		medicamentoTableModel.actualizar(Farmacia.obtenerInstancia().getMedicamentos());


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
		panel_1.setBackground(new Color(75, 255, 112));

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
						Farmacia.obtenerInstancia().getMedicamentos()

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
		label_8.setForeground(new Color(75, 255, 112));
		label_8.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_8.setBounds(208, -1, 56, 148);
		medicamentos.add(label_8);

		JLabel label_9 = new JLabel("|");
		label_9.setForeground(Color.GREEN);
		label_9.setForeground(new Color(75, 255, 112));
		label_9.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_9.setBounds(208, 77, 56, 148);
		medicamentos.add(label_9);

		JLabel label_10 = new JLabel("|");
		label_10.setForeground(new Color(75, 255, 112));
		label_10.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_10.setBounds(208, 160, 56, 148);
		medicamentos.add(label_10);

		JLabel label_11 = new JLabel("|");

		label_11.setForeground(new Color(75, 255, 112));

		label_11.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_11.setBounds(208, 236, 56, 148);
		medicamentos.add(label_11);

		JLabel label_12 = new JLabel("-");
		label_12.setForeground(new Color(75, 255, 112));
		label_12.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_12.setBounds(0, 311, 56, 120);
		medicamentos.add(label_12);

		JLabel label_13 = new JLabel("-");
		label_13.setForeground(new Color(75, 255, 112));
		label_13.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_13.setBounds(22, 311, 56, 120);
		medicamentos.add(label_13);

		JLabel label_14 = new JLabel("-");
		label_14.setForeground(new Color(75, 255, 112));
		label_14.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_14.setBounds(41, 311, 56, 120);
		medicamentos.add(label_14);

		JLabel label_15 = new JLabel("-");
		label_15.setForeground(new Color(75, 255, 112));

		label_15.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_15.setBounds(68, 311, 56, 120);
		medicamentos.add(label_15);

		JLabel label_16 = new JLabel("-");
		label_16.setForeground(Color.GREEN);
		label_16.setForeground(new Color(75, 255, 112));
		label_16.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_16.setBounds(90, 311, 56, 120);
		medicamentos.add(label_16);

		JLabel label_17 = new JLabel("-");
		label_17.setForeground(new Color(75, 255, 112));
		label_17.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_17.setBounds(115, 311, 56, 120);
		medicamentos.add(label_17);

		JLabel label_18 = new JLabel("-");
		label_18.setForeground(new Color(75, 255, 112));
		label_18.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_18.setBounds(136, 311, 56, 120);
		medicamentos.add(label_18);

		JLabel label_19 = new JLabel("-");
		label_19.setForeground(new Color(75, 255, 112));
		label_19.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_19.setBounds(155, 311, 56, 120);
		medicamentos.add(label_19);

		JLabel label_20 = new JLabel("-");
		label_20.setForeground(new Color(75, 255, 112));
		label_20.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_20.setBounds(183, 311, 56, 120);
		medicamentos.add(label_20);

		JLabel label_21 = new JLabel("-");
		label_21.setForeground(new Color(75, 255, 112));
		label_21.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_21.setBounds(208, 311, 56, 120);
		medicamentos.add(label_21);

		JLabel label_22 = new JLabel("-");
		label_22.setForeground(new Color(75, 255, 112));
		label_22.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_22.setBounds(218, 311, 56, 120);
		medicamentos.add(label_22);

		JLabel label_23 = new JLabel("-");
		label_23.setForeground(new Color(75, 255, 112));
		label_23.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_23.setBounds(238, 311, 56, 120);
		medicamentos.add(label_23);

		JLabel label_24 = new JLabel("-");
		label_24.setForeground(new Color(75, 255, 112));
		label_24.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_24.setBounds(261, 311, 56, 120);
		medicamentos.add(label_24);

		JLabel label_25 = new JLabel("-");
		label_25.setForeground(new Color(75, 255, 112));

		label_25.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_25.setBounds(276, 311, 56, 120);
		medicamentos.add(label_25);

		JLabel label_26 = new JLabel("-");
		label_26.setForeground(new Color(75, 255, 112));
		label_26.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_26.setBounds(306, 311, 56, 120);
		medicamentos.add(label_26);

		JLabel label_27 = new JLabel("-");
		label_27.setForeground(new Color(75, 255, 112));
		label_27.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_27.setBounds(326, 311, 56, 120);
		medicamentos.add(label_27);

		JLabel label_28 = new JLabel("-");
		label_28.setForeground(new Color(75, 255, 112));
		label_28.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_28.setBounds(344, 311, 56, 120);
		medicamentos.add(label_28);

		JLabel label_29 = new JLabel("-");
		label_29.setForeground(new Color(75, 255, 112));
		label_29.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_29.setBounds(276, 311, 56, 120);
		medicamentos.add(label_29);

		JLabel label_30 = new JLabel("-");
		label_30.setForeground(new Color(75, 255, 112));
		label_30.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_30.setBounds(287, 311, 56, 120);
		medicamentos.add(label_30);

		JLabel label_31 = new JLabel("-");
		label_31.setForeground(new Color(75, 255, 112));
		label_31.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_31.setBounds(977, 311, 56, 120);
		medicamentos.add(label_31);

		JLabel label_32 = new JLabel("-");
		label_32.setForeground(new Color(75, 255, 112));
		label_32.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_32.setBounds(355, 311, 56, 120);
		medicamentos.add(label_32);

		JLabel label_33 = new JLabel("-");
		label_33.setForeground(new Color(75, 255, 112));
		label_33.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_33.setBounds(374, 311, 56, 120);
		medicamentos.add(label_33);

		JLabel label_34 = new JLabel("-");
		label_34.setForeground(new Color(75, 255, 112));
		label_34.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_34.setBounds(399, 311, 56, 120);
		medicamentos.add(label_34);

		JLabel label_35 = new JLabel("-");
		label_35.setForeground(new Color(75, 255, 112));
		label_35.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_35.setBounds(423, 311, 56, 120);
		medicamentos.add(label_35);

		JLabel label_36 = new JLabel("-");
		label_36.setForeground(new Color(75, 255, 112));
		label_36.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_36.setBounds(451, 311, 56, 120);
		medicamentos.add(label_36);

		JLabel label_37 = new JLabel("-");
		label_37.setForeground(new Color(75, 255, 112));
		label_37.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_37.setBounds(467, 311, 56, 120);
		medicamentos.add(label_37);

		JLabel label_38 = new JLabel("-");
		label_38.setForeground(new Color(75, 255, 112));
		label_38.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_38.setBounds(491, 311, 56, 120);
		medicamentos.add(label_38);

		JLabel label_39 = new JLabel("-");
		label_39.setForeground(new Color(75, 255, 112));
		label_39.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_39.setBounds(519, 311, 56, 120);
		medicamentos.add(label_39);

		JLabel label_40 = new JLabel("-");
		label_40.setForeground(new Color(75, 255, 112));
		label_40.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_40.setBounds(547, 311, 56, 120);
		medicamentos.add(label_40);

		JLabel label_41 = new JLabel("-");
		label_41.setForeground(new Color(75, 255, 112));
		label_41.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_41.setBounds(574, 311, 56, 120);
		medicamentos.add(label_41);

		JLabel label_42 = new JLabel("-");
		label_42.setForeground(new Color(75, 255, 112));
		label_42.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_42.setBounds(602, 311, 56, 120);
		medicamentos.add(label_42);

		JLabel label_43 = new JLabel("-");
		label_43.setForeground(new Color(75, 255, 112));
		label_43.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_43.setBounds(627, 311, 56, 120);
		medicamentos.add(label_43);

		JLabel label_44 = new JLabel("-");
		label_44.setForeground(new Color(75, 255, 112));
		label_44.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_44.setBounds(655, 311, 56, 120);
		medicamentos.add(label_44);

		JLabel label_45 = new JLabel("-");
		label_45.setForeground(new Color(75, 255, 112));
		label_45.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_45.setBounds(681, 311, 56, 120);
		medicamentos.add(label_45);

		JLabel label_46 = new JLabel("-");
		label_46.setBackground(new Color(204, 255, 204));
		label_46.setForeground(new Color(75, 255, 112));
		label_46.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_46.setBounds(708, 311, 56, 120);
		medicamentos.add(label_46);

		JLabel label_47 = new JLabel("-");
		label_47.setForeground(new Color(75, 255, 112));
		label_47.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_47.setBounds(734, 311, 56, 120);
		medicamentos.add(label_47);

		JLabel label_48 = new JLabel("-");
		label_48.setForeground(new Color(75, 255, 112));
		label_48.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_48.setBounds(760, 311, 56, 120);
		medicamentos.add(label_48);

		JLabel label_49 = new JLabel("-");
		label_49.setForeground(new Color(75, 255, 112));
		label_49.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_49.setBounds(789, 311, 56, 120);
		medicamentos.add(label_49);

		JLabel label_50 = new JLabel("-");
		label_50.setForeground(new Color(75, 255, 112));
		label_50.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_50.setBounds(811, 311, 56, 120);
		medicamentos.add(label_50);
		JLabel label_51 = new JLabel("-");
		label_51.setForeground(new Color(75, 255, 112));
		label_51.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_51.setBounds(841, 311, 56, 120);
		medicamentos.add(label_51);

		JLabel label_52 = new JLabel("-");
		label_52.setForeground(new Color(75, 255, 112));
		label_52.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_52.setBounds(857, 311, 56, 120);
		medicamentos.add(label_52);

		JLabel label_53 = new JLabel("-");
		label_53.setForeground(new Color(75, 255, 112));
		label_53.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_53.setBounds(879, 311, 56, 120);
		medicamentos.add(label_53);

		JLabel label_54 = new JLabel("-");
		label_54.setForeground(new Color(75, 255, 112));
		label_54.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_54.setBounds(909, 311, 56, 120);
		medicamentos.add(label_54);

		JLabel label_55 = new JLabel("-");
		label_55.setForeground(new Color(75, 255, 112));
		label_55.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_55.setBounds(925, 311, 56, 120);
		medicamentos.add(label_55);

		JLabel label_56 = new JLabel("-");
		label_56.setForeground(new Color(75, 255, 112));
		label_56.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_56.setBounds(949, 311, 56, 120);
		medicamentos.add(label_56);

		JLabel label_57 = new JLabel("-");
		label_57.setForeground(new Color(75, 255, 112));
		label_57.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_57.setBounds(893, 311, 56, 120);
		medicamentos.add(label_57);

		JLabel label_58 = new JLabel("-");
		label_58.setForeground(new Color(75, 255, 112));
		label_58.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_58.setBounds(825, 311, 56, 120);
		medicamentos.add(label_58);

		JLabel label_59 = new JLabel("-");
		label_59.setForeground(new Color(75, 255, 112));
		label_59.setFont(new Font("Arial Black", Font.BOLD, 99));
		label_59.setBounds(776, 311, 56, 120);
		medicamentos.add(label_59);

		JLabel label_60 = new JLabel("|");
		label_60.setForeground(new Color(75, 255, 112));
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
		textPrecio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = textPrecio.getText();

				// Permitir solo dígitos y un único punto
				if (!Character.isDigit(c) && c != '.') {
					e.consume(); // Consumir el evento si no es un número o punto
				}

				// Permitir solo un punto en el campo
				if (c == '.' && text.contains(".")) {
					e.consume(); // Consumir el evento si ya hay un punto
				}

				// Evitar que el punto sea el primer carácter
				if (c == '.' && text.isEmpty()) {
					e.consume(); // Consumir el evento si el punto está al inicio
				}
			}
		});
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

					Medicamento m = Farmacia.obtenerInstancia().agregarMed(txtNombreComun, txtNombreCientifico, txtPresentacion,
							txtPrecio, txtTipo, txtFortaleza, txtTemperatura,
							txtCantidad, Exped, Venc, codigoDelMed);

					// Actualizar tabla
					medicamentoTableModel.actualizar(Farmacia.obtenerInstancia().getMedicamentos());


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
		repor3.setBackground(new Color(150, 255, 147));
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
		repor2.setBackground(new Color(150, 255, 147));
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
		repor1.setBackground(new Color(150, 255, 147));
		repor1.setBounds(78, 161, 381, 216);
		reportes.add(repor1);

		JLabel lblElijaUnReporte = new JLabel("Elija un reporte");
		lblElijaUnReporte.setForeground(Color.BLACK);
		lblElijaUnReporte.setFont(new Font("Times New Roman", Font.BOLD, 28));
		lblElijaUnReporte.setBounds(10, 56, 251, 43);
		reportes.add(lblElijaUnReporte);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(75, 255, 112));
		panel_2.setBounds(0, 0, 1029, 43);
		reportes.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblReportes_1 = new JLabel("REPORTES");
		lblReportes_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblReportes_1.setBounds(12, 0, 155, 43);
		panel_2.add(lblReportes_1);

		Label label_3 = new Label("_______________________________________________________________________");
		label_3.setFont(new Font("Arial Black", Font.BOLD, 50));
		label_3.setForeground(new Color(75, 255, 112));
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
		repor4.setBackground(new Color(150, 255, 147));
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


		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(75, 255, 112));
		panel_4.setBounds(0, 0, 1015, 57);
		reporte1.add(panel_4);
		panel_4.setLayout(null);

		JLabel regresar = new JLabel("");
		regresar.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
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
		panel_5.setBackground(new Color(75, 255, 112));
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
		panel_6.setBackground(new Color(75, 255, 112));
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
		panel_7.setBackground(new Color(75, 255, 112));
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



		final JPanel VentaLibre = new JPanel();
		VentaLibre.setBackground(Color.WHITE);

		pestanas.addTab("New tab", null, VentaLibre, null);
		VentaLibre.setLayout(null);

		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setFont(new Font("Arial", Font.PLAIN, 19));
		scrollPane_5.setBounds(38, 224, 941, 197);
		VentaLibre.add(scrollPane_5);


		//Inicializar el modelo de la tabla
		comprasTableModel = new ComprasTableModel();
		tablaCompras = new JTable();
		tablaCompras.setModel(comprasTableModel);

		// 2. Configurar el renderizador de botones para la columna de acción
		tablaCompras.getColumnModel().getColumn(5).setCellRenderer(new ButtonRenderer());

		// 3. Agregar el MouseListener para manejar el evento de clic en "Eliminar"
		tablaCompras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = tablaCompras.rowAtPoint(e.getPoint());
				int columna = tablaCompras.columnAtPoint(e.getPoint());

				if (columna == 5) { // Columna de acción ("Eliminar")
					int opcion = JOptionPane.showConfirmDialog(
							null,
							"¿Está seguro de que desea eliminar esta compra?",
							"Confirmar eliminación",
							JOptionPane.YES_NO_OPTION
							);

					if (opcion == JOptionPane.YES_OPTION) {
						// Obtener los datos de la fila seleccionada
						String nombreMedicamento = (String) comprasTableModel.getValueAt(fila, 1);
						int cantidadEliminada = (int) comprasTableModel.getValueAt(fila, 3);

						// Validar que la cantidad eliminada sea válida
						if (cantidadEliminada <= 0) {
							JOptionPane.showMessageDialog(null, 
									"La cantidad eliminada debe ser mayor que cero.", 
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}

						// Restar la cantidad eliminada del registro de cantidades acumuladas
						int cantidadAcumuladaActual = cantidadesAcumuladas.getOrDefault(nombreMedicamento, 0);
						int nuevaCantidadAcumulada = cantidadAcumuladaActual - cantidadEliminada;

						if (nuevaCantidadAcumulada <= 0) {
							cantidadesAcumuladas.remove(nombreMedicamento);
						} else {
							cantidadesAcumuladas.put(nombreMedicamento, nuevaCantidadAcumulada);
						}

						// Eliminar la fila del modelo de tabla
						comprasTableModel.eliminarFila(fila);

						// Recalcular el total acumulado
						actualizarTotal(txtTotal, comprasTableModel);
					}
				}
			}
		});
		scrollPane_5.setViewportView(tablaCompras);

		JPanel panel_17 = new JPanel();
		panel_17.setLayout(null);
		panel_17.setBackground(new Color(75, 255, 112));
		panel_17.setBounds(0, 0, 1015, 57);
		VentaLibre.add(panel_17);

		JLabel label_66 = new JLabel("");
		label_66.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);

			}
		});
		label_66.setBounds(12, 0, 57, 57);
		panel_17.add(label_66);
		UtilesInterfaz.ajustarImagen(label_66, "src/iconos/deshacer.png");

		JLabel lblVentaLibre = new JLabel("VENTA LIBRE");
		lblVentaLibre.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblVentaLibre.setBounds(79, 0, 200, 57);
		panel_17.add(lblVentaLibre);

		JLabel lblPaciente = new JLabel("Paciente :");
		lblPaciente.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPaciente.setBounds(56, 80, 123, 32);
		VentaLibre.add(lblPaciente);

		final JComboBox<String> comboBoxPacientes = new JComboBox<String>();
		comboBoxPacientes.setBorder(new LineBorder(Color.BLACK));
		comboBoxPacientes.setBackground(Color.WHITE);
		comboBoxPacientes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxPacientes.setBounds(191, 85, 270, 32);

		// Crear el modelo del combobox
		PacientesComboBoxModel pacientesComboBoxModel = new PacientesComboBoxModel();

		// Asignar el modelo al combobox
		comboBoxPacientes.setModel(pacientesComboBoxModel);

		// Cargar datos desde la clase Farmacia
		pacientesComboBoxModel.cargarDatos(Farmacia.obtenerInstancia().obtenerListaPacientes());

		// Cargar datos desde la clase Farmacia
		VentaLibre.add(comboBoxPacientes);

		JLabel lblMedicamento = new JLabel("Medicamento :");
		lblMedicamento.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMedicamento.setBounds(24, 132, 180, 32);
		VentaLibre.add(lblMedicamento);

		final JComboBox<String> comboBoxMedicamentos = new JComboBox<String>();
		comboBoxMedicamentos.setBorder(new LineBorder(Color.BLACK));
		comboBoxMedicamentos.setBackground(new Color(255, 255, 255));
		comboBoxMedicamentos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxMedicamentos.setBounds(191, 130, 270, 32);

		// Crear el modelo del combobox
		MedicamentosComboBoxModel medicamentosComboBoxModel = new MedicamentosComboBoxModel();

		// Asignar el modelo al combobox
		comboBoxMedicamentos.setModel(medicamentosComboBoxModel);

		// Cargar datos desde la clase Farmacia
		medicamentosComboBoxModel.cargarDatos(Farmacia.obtenerInstancia().obtenerMedicamentosVentaLibre());

		VentaLibre.add(comboBoxMedicamentos);

		JLabel lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCantidad.setBounds(493, 80, 128, 32);
		VentaLibre.add(lblCantidad);

		final JComboBox <Integer>comboBoxCant = new JComboBox<Integer>();
		comboBoxCant.setBorder(new LineBorder(Color.BLACK));
		comboBoxCant.setModel(new DefaultComboBoxModel(new String[] {"0", "1 ", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxCant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxCant.setBounds(611, 85, 79, 32);
		VentaLibre.add(comboBoxCant);

		JButton btnAadirALa = new JButton("A\u00F1adir a la Compra");
		// Acción del botón usando MouseListener


		btnAadirALa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Obtener el nombre del medicamento seleccionado
				String nombreMedicamento = (String) comboBoxMedicamentos.getSelectedItem();

				// Obtener el nombre del paciente seleccionado
				String nombrePaciente = (String) comboBoxPacientes.getSelectedItem();

				// Validar selección del medicamento
				if ("<Seleccione un medicamento>".equals(nombreMedicamento)) {
					JOptionPane.showMessageDialog(VentaLibre, "Por favor, seleccione un medicamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validar selección del paciente
				if ("<Seleccione un paciente>".equals(nombrePaciente)) {
					JOptionPane.showMessageDialog(VentaLibre, "Por favor, seleccione un paciente válido.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}


				// Si es la primera vez que se selecciona un paciente, deshabilitar el combobox
				if (!pacienteSeleccionado) {
					comboBoxPacientes.setEnabled(false); // Deshabilitar el combobox
					pacienteSeleccionado = true; // Marcar que ya se seleccionó un paciente
				}

				// Obtener la cantidad seleccionada en el combobox
				int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCant.getSelectedItem()).trim());

				// Validar que la cantidad sea mayor que cero
				if (cantidadSeleccionada <= 0) {
					JOptionPane.showMessageDialog(VentaLibre, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
					// Verificar si el medicamento ya tiene una cantidad acumulada
					int cantidadAcumulada = cantidadesAcumuladas.getOrDefault(nombreMedicamento, 0);

					// Calcular la cantidad total si se agrega esta compra
					int cantidadTotal = cantidadAcumulada + cantidadSeleccionada;

					// Validar que la cantidad total no exceda el límite de 10 unidades
					if (cantidadTotal > 10) {
						JOptionPane.showMessageDialog(VentaLibre, "No puede comprar más de 10 unidades de este medicamento.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					comprasTableModel.cantidad = cantidadSeleccionada; // Actualizar la variable externa
					// Obtener el precio del medicamento
					double precioMedicamento = Farmacia.obtenerInstancia().obtenerPrecioMedicamento(nombreMedicamento);

					// Calcular el importe total
					double importeTotal = precioMedicamento * cantidadSeleccionada;

					// Convertir LocalDate a Date
					Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

					// Crear una nueva instancia de VentaLibre
					Logica.VentaLibre venta = new Logica.VentaLibre(
							fechaDeCompra, // Fecha de compra
							importeTotal, // Importe total
							nombreMedicamento, // Nombre del medicamento
							"MC-001", // Código del medicamento (puedes ajustarlo según sea necesario)
							cantidadSeleccionada // Cantidad vendida
							);

					// Actualizar el registro de cantidades acumuladas
					int cantidadAcumuladaActual = cantidadesAcumuladas.getOrDefault(nombreMedicamento, 0);
					cantidadesAcumuladas.put(nombreMedicamento, cantidadAcumuladaActual + cantidadSeleccionada);
					// Agregar la venta al modelo de la tabla
					comprasTableModel.adicionar(venta);

					actualizarTotal(txtTotal, comprasTableModel);
				} catch (RuntimeException ex) {
					// Manejar errores, como medicamento no encontrado
					JOptionPane.showMessageDialog(VentaLibre, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAadirALa.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnAadirALa.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnAadirALa.setBounds(488, 123, 183, 41);
		VentaLibre.add(btnAadirALa);

		JPanel panel_22 = new JPanel();
		panel_22.setBackground(UIManager.getColor("Button.background"));
		panel_22.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_22.setBounds(22, 488, 507, 126);
		VentaLibre.add(panel_22);
		panel_22.setLayout(null);

		JLabel lblTotalAPagar = new JLabel("Total a Pagar :");
		lblTotalAPagar.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblTotalAPagar.setBounds(12, 13, 133, 37);
		panel_22.add(lblTotalAPagar);

		JLabel lblEfectivo = new JLabel("Efectivo :");
		lblEfectivo.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblEfectivo.setBounds(12, 48, 122, 21);
		panel_22.add(lblEfectivo);

		JLabel lblCambio = new JLabel("Cambio :");
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblCambio.setBounds(12, 74, 97, 27);
		panel_22.add(lblCambio);

		txtTotal = new JTextField();
		txtTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTotal.setBackground(Color.WHITE);
		txtTotal.setEditable(false);
		txtTotal.setText("0.0");
		txtTotal.setBounds(130, 22, 148, 22);
		panel_22.add(txtTotal);
		txtTotal.setColumns(10);

		txtEfectivo = new JTextField();
		txtEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
		txtEfectivo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				String text = txtEfectivo.getText();

				// Permitir solo dígitos y un único punto
				if (!Character.isDigit(c) && c != '.') {
					e.consume(); // Consumir el evento si no es un número o punto
				}

				// Permitir solo un punto en el campo
				if (c == '.' && text.contains(".")) {
					e.consume(); // Consumir el evento si ya hay un punto
				}

				// Evitar que el punto sea el primer carácter
				if (c == '.' && text.isEmpty()) {
					e.consume(); // Consumir el evento si el punto está al inicio
				}
			}

		});
		txtEfectivo.setBorder(new LineBorder(Color.BLACK));
		txtEfectivo.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEfectivo.setBounds(130, 49, 148, 22);
		panel_22.add(txtEfectivo);
		txtEfectivo.setColumns(10);

		txtCambio = new JTextField();
		txtCambio.setHorizontalAlignment(SwingConstants.CENTER);
		txtCambio.setBorder(new LineBorder(Color.BLACK));
		txtCambio.setFont(new Font("Arial", Font.PLAIN, 16));
		txtCambio.setBackground(Color.WHITE);
		txtCambio.setEditable(false);
		txtCambio.setBounds(130, 78, 148, 22);
		panel_22.add(txtCambio);
		txtCambio.setColumns(10);

		JButton btnCalcularCambio = new JButton("Calcular Cambio");
		btnCalcularCambio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar una compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				try {
					double totalAPagar = Double.parseDouble(txtTotal.getText().trim());
					double efectivoRecibido = Double.parseDouble(txtEfectivo.getText().trim());

					if (efectivoRecibido < totalAPagar) {
						JOptionPane.showMessageDialog(VentaLibre, "El efectivo recibido no es suficiente.", "Error", JOptionPane.ERROR_MESSAGE);
						cambioCalculado = false;
						return;
					}

					double cambio = efectivoRecibido - totalAPagar;
					txtCambio.setText(String.format("%.2f", cambio));

					// Marcar que el cambio ha sido calculado
					cambioCalculado = true;

				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(VentaLibre, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
					cambioCalculado = false;
				}
			}
		});
		btnCalcularCambio.setBackground(UIManager.getColor("Button.light"));
		btnCalcularCambio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCalcularCambio.setBounds(318, 36, 164, 44);
		panel_22.add(btnCalcularCambio);

		JPanel btnRealizarCompra = new JPanel();
		btnRealizarCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!cambioCalculado) {
					JOptionPane.showMessageDialog(null, 
							"Debe calcular el cambio antes de realizar la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si el cambio no ha sido calculado
				}
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				try {
					// Obtener el nombre del medicamento seleccionado
					String nombreMedicamento = (String) comboBoxMedicamentos.getSelectedItem();

					// Validar selección del medicamento
					if ("<Seleccione un medicamento>".equals(nombreMedicamento)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un medicamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el nombre del paciente seleccionado
					String nombrePaciente = (String) comboBoxPacientes.getSelectedItem();

					// Validar selección del paciente
					if ("<Seleccione un paciente>".equals(nombrePaciente)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener la cantidad seleccionada
					int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCant.getSelectedItem()).trim());

					// Validar que la cantidad sea mayor que cero
					if (cantidadSeleccionada <= 0) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el precio del medicamento
					double precioMedicamento = Farmacia.obtenerInstancia().obtenerPrecioMedicamento(nombreMedicamento);

					// Calcular el importe total
					double importeTotal = precioMedicamento * cantidadSeleccionada;

					// Convertir LocalDate a Date
					Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

					// Crear una nueva instancia de VentaLibre
					Logica.VentaLibre venta = new Logica.VentaLibre(
							fechaDeCompra, // Fecha de compra
							importeTotal, // Importe total
							nombreMedicamento, // Nombre del medicamento
							"MC-001", // Código del medicamento (puedes ajustarlo según sea necesario)
							cantidadSeleccionada // Cantidad vendida
							);

					// Agregar la venta al historial de Farmacia
					Farmacia.obtenerInstancia().registrarVenta(venta);

					// Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// Reiniciar el total
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// Habilitar el combobox de pacientes
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Limpiar las selecciones actuales
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxCant.setSelectedItem("0");

					// Mostrar mensaje de éxito
					JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

				} catch (RuntimeException ex) {
					// Manejar errores, como medicamento no encontrado
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnRealizarCompra.setBackground(new Color(204, 255, 204));
		btnRealizarCompra.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(152, 251, 152), 2)));
		btnRealizarCompra.setBounds(559, 517, 208, 82);
		VentaLibre.add(btnRealizarCompra);
		btnRealizarCompra.setLayout(null);

		JLabel lblRealizarCompra = new JLabel("Realizar ");
		lblRealizarCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!cambioCalculado) {
					JOptionPane.showMessageDialog(null, 
							"Debe calcular el cambio antes de realizar la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si el cambio no ha sido calculado
				}
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				try {
					// Obtener el nombre del medicamento seleccionado
					String nombreMedicamento = (String) comboBoxMedicamentos.getSelectedItem();

					// Validar selección del medicamento
					if ("<Seleccione un medicamento>".equals(nombreMedicamento)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un medicamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el nombre del paciente seleccionado
					String nombrePaciente = (String) comboBoxPacientes.getSelectedItem();

					// Validar selección del paciente
					if ("<Seleccione un paciente>".equals(nombrePaciente)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener la cantidad seleccionada
					int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCant.getSelectedItem()).trim());

					// Validar que la cantidad sea mayor que cero
					if (cantidadSeleccionada <= 0) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el precio del medicamento
					double precioMedicamento = Farmacia.obtenerInstancia().obtenerPrecioMedicamento(nombreMedicamento);

					// Calcular el importe total
					double importeTotal = precioMedicamento * cantidadSeleccionada;

					// Convertir LocalDate a Date
					Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

					// Crear una nueva instancia de VentaLibre
					Logica.VentaLibre venta = new Logica.VentaLibre(
							fechaDeCompra, // Fecha de compra
							importeTotal, // Importe total
							nombreMedicamento, // Nombre del medicamento
							"MC-001", // Código del medicamento (puedes ajustarlo según sea necesario)
							cantidadSeleccionada // Cantidad vendida
							);

					// Agregar la venta al historial de Farmacia
					Farmacia.obtenerInstancia().registrarVenta(venta);

					// Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// Reiniciar el total
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// Habilitar el combobox de pacientes
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Limpiar las selecciones actuales
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxCant.setSelectedItem("0");

					// Mostrar mensaje de éxito
					JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

				} catch (RuntimeException ex) {
					// Manejar errores, como medicamento no encontrado
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblRealizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblRealizarCompra.setBounds(12, -11, 127, 80);
		btnRealizarCompra.add(lblRealizarCompra);

		JLabel label_72 = new JLabel("");
		label_72.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!cambioCalculado) {
					JOptionPane.showMessageDialog(null, 
							"Debe calcular el cambio antes de realizar la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si el cambio no ha sido calculado
				}
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				try {
					// Obtener el nombre del medicamento seleccionado
					String nombreMedicamento = (String) comboBoxMedicamentos.getSelectedItem();

					// Validar selección del medicamento
					if ("<Seleccione un medicamento>".equals(nombreMedicamento)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un medicamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el nombre del paciente seleccionado
					String nombrePaciente = (String) comboBoxPacientes.getSelectedItem();

					// Validar selección del paciente
					if ("<Seleccione un paciente>".equals(nombrePaciente)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener la cantidad seleccionada
					int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCant.getSelectedItem()).trim());

					// Validar que la cantidad sea mayor que cero
					if (cantidadSeleccionada <= 0) 
					{
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el precio del medicamento
					double precioMedicamento = Farmacia.obtenerInstancia().obtenerPrecioMedicamento(nombreMedicamento);

					// Calcular el importe total
					double importeTotal = precioMedicamento * cantidadSeleccionada;

					// Convertir LocalDate a Date
					Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

					// Crear una nueva instancia de VentaLibre
					Logica.VentaLibre venta = new Logica.VentaLibre(
							fechaDeCompra, // Fecha de compra
							importeTotal, // Importe total
							nombreMedicamento, // Nombre del medicamento
							"MC-001", // Código del medicamento (puedes ajustarlo según sea necesario)
							cantidadSeleccionada // Cantidad vendida
							);

					// Agregar la venta al historial de Farmacia
					Farmacia.obtenerInstancia().registrarVenta(venta);

					// Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// Reiniciar el total
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// Habilitar el combobox de pacientes
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Limpiar las selecciones actuales
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxCant.setSelectedItem("0");

					// Mostrar mensaje de éxito
					JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

				} catch (RuntimeException ex) {
					// Manejar errores, como medicamento no encontrado
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		label_72.setBounds(119, 13, 65, 53);
		btnRealizarCompra.add(label_72);
		UtilesInterfaz.ajustarImagen(label_72, "src/iconos/comprobacion-del-carrito-de-la-compra.png");

		JLabel lblCompra_1 = new JLabel("Compra");
		lblCompra_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!cambioCalculado) {
					JOptionPane.showMessageDialog(null, 
							"Debe calcular el cambio antes de realizar la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si el cambio no ha sido calculado
				}
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				try {
					// Obtener el nombre del medicamento seleccionado
					String nombreMedicamento = (String) comboBoxMedicamentos.getSelectedItem();

					// Validar selección del medicamento
					if ("<Seleccione un medicamento>".equals(nombreMedicamento)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un medicamento válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el nombre del paciente seleccionado
					String nombrePaciente = (String) comboBoxPacientes.getSelectedItem();

					// Validar selección del paciente
					if ("<Seleccione un paciente>".equals(nombrePaciente)) {
						JOptionPane.showMessageDialog(null, "Por favor, seleccione un paciente válido.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener la cantidad seleccionada
					int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCant.getSelectedItem()).trim());

					// Validar que la cantidad sea mayor que cero
					if (cantidadSeleccionada <= 0) {
						JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Obtener el precio del medicamento
					double precioMedicamento = Farmacia.obtenerInstancia().obtenerPrecioMedicamento(nombreMedicamento);

					// Calcular el importe total
					double importeTotal = precioMedicamento * cantidadSeleccionada;

					// Convertir LocalDate a Date
					Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

					// Crear una nueva instancia de VentaLibre
					Logica.VentaLibre venta = new Logica.VentaLibre(
							fechaDeCompra, // Fecha de compra
							importeTotal, // Importe total
							nombreMedicamento, // Nombre del medicamento
							"MC-001", // Código del medicamento (puedes ajustarlo según sea necesario)
							cantidadSeleccionada // Cantidad vendida
							);

					// Agregar la venta al historial de Farmacia
					Farmacia.obtenerInstancia().registrarVenta(venta);

					// Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// Reiniciar el total
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// Habilitar el combobox de pacientes
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Limpiar las selecciones actuales
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxCant.setSelectedItem("0");

					// Mostrar mensaje de éxito
					JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

				} catch (RuntimeException ex) {
					// Manejar errores, como medicamento no encontrado
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		lblCompra_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCompra_1.setBounds(12, 13, 127, 93);
		btnRealizarCompra.add(lblCompra_1);

		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_21.setBackground(UIManager.getColor("Button.background"));
		panel_21.setBounds(12, 175, 981, 279);
		VentaLibre.add(panel_21);
		panel_21.setLayout(null);

		JLabel lblCompra = new JLabel("Compra");
		lblCompra.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCompra.setBounds(30, 13, 153, 29);
		panel_21.add(lblCompra);

		JPanel btnCancelarCompra = new JPanel();
		btnCancelarCompra.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// 3. Restablecer las selecciones de los comboboxes
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxCant.setSelectedItem("0");

					// 4. Reiniciar el registro de cantidades acumuladas
					cantidadesAcumuladas.clear();

					// 5. Habilitar el combobox de pacientes (en caso de que esté deshabilitado)
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});
		btnCancelarCompra.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(255, 0, 0), 2)));
		btnCancelarCompra.setBackground(new Color(255, 105, 105));
		btnCancelarCompra.setBounds(779, 517, 208, 82);
		VentaLibre.add(btnCancelarCompra);
		btnCancelarCompra.setLayout(null);

		JLabel lblCancelar = new JLabel("Cancelar");
		lblCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// 3. Restablecer las selecciones de los comboboxes
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxCant.setSelectedItem("0");

					// 4. Reiniciar el registro de cantidades acumuladas
					cantidadesAcumuladas.clear();

					// 5. Habilitar el combobox de pacientes (en caso de que esté deshabilitado)
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});

		JLabel label_76 = new JLabel("");
		label_76.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// 3. Restablecer las selecciones de los comboboxes
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxCant.setSelectedItem("0");

					// 4. Reiniciar el registro de cantidades acumuladas
					cantidadesAcumuladas.clear();

					// 5. Habilitar el combobox de pacientes (en caso de que esté deshabilitado)
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});

		label_76.setBounds(127, 13, 69, 53);
		btnCancelarCompra.add(label_76);
		lblCancelar.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCancelar.setBounds(12, 13, 101, 33);
		btnCancelarCompra.add(lblCancelar);
		UtilesInterfaz.ajustarImagen(label_76, "src/iconos/circulo-cruzado.png");

		JLabel label_74 = new JLabel("Compra");
		label_74.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!hayComprasEnLaTabla()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar algún medicamento a la compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					comprasTableModel.getDataVector().clear();
					comprasTableModel.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					txtTotal.setText("0.0");
					txtEfectivo.setText("");
					txtCambio.setText("");

					// 3. Restablecer las selecciones de los comboboxes
					comboBoxMedicamentos.setSelectedItem("<Seleccione un medicamento>");
					comboBoxPacientes.setSelectedItem("<Seleccione un paciente>");
					comboBoxCant.setSelectedItem("0");

					// 4. Reiniciar el registro de cantidades acumuladas
					cantidadesAcumuladas.clear();

					// 5. Habilitar el combobox de pacientes (en caso de que esté deshabilitado)
					comboBoxPacientes.setEnabled(true);
					pacienteSeleccionado = false;

					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});
		label_74.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_74.setBounds(12, 13, 127, 93);
		btnCancelarCompra.add(label_74);

		final JPanel VentaAlmohadillas = new JPanel();
		VentaAlmohadillas.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, VentaAlmohadillas, null);
		VentaAlmohadillas.setLayout(null);

		JPanel panel_18 = new JPanel();
		panel_18.setLayout(null);
		panel_18.setBackground(new Color(75, 255, 112));
		panel_18.setBounds(0, 0, 1015, 57);
		VentaAlmohadillas.add(panel_18);

		JLabel label_71 = new JLabel("");
		label_71.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		label_71.setBounds(12, 0, 57, 57);
		panel_18.add(label_71);
		UtilesInterfaz.ajustarImagen(label_71, "src/iconos/deshacer.png");

		JLabel lblVentaAlmohadillasSanitarias_1 = new JLabel("VENTA ALMOHADILLAS SANITARIAS");
		lblVentaAlmohadillasSanitarias_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblVentaAlmohadillasSanitarias_1.setBounds(79, 0, 517, 57);
		panel_18.add(lblVentaAlmohadillasSanitarias_1);

		JPanel panel_24 = new JPanel();
		panel_24.setBounds(12, 228, 981, 230);
		VentaAlmohadillas.add(panel_24);
		panel_24.setLayout(null);

		JScrollPane jscroll_18 = new JScrollPane();
		jscroll_18.setBounds(12, 42, 957, 175);
		panel_24.add(jscroll_18);

		tableALmohadillas = new JTable();
		jscroll_18.setViewportView(tableALmohadillas);
		// Inicializar el modelo
		modeloAlmohadillas = new AlmohadillasTableModel();

		// Asignar el modelo a la tabla
		tableALmohadillas.setModel(modeloAlmohadillas);


		JLabel label_86 = new JLabel("Compra");
		label_86.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_86.setBounds(12, 0, 153, 29);
		panel_24.add(label_86);

		JPanel panel_25 = new JPanel();
		panel_25.setLayout(null);
		panel_25.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_25.setBackground(SystemColor.menu);
		panel_25.setBounds(12, 486, 507, 126);
		VentaAlmohadillas.add(panel_25);

		JLabel label_77 = new JLabel("Total a Pagar :");
		label_77.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_77.setBounds(12, 13, 133, 37);
		panel_25.add(label_77);

		JLabel label_78 = new JLabel("Efectivo :");
		label_78.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_78.setBounds(12, 48, 122, 21);
		panel_25.add(label_78);

		JLabel label_79 = new JLabel("Cambio :");
		label_79.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_79.setBounds(12, 74, 97, 27);
		panel_25.add(label_79);

		textTotal = new JTextField();
		textTotal.setHorizontalAlignment(SwingConstants.CENTER);
		textTotal.setFont(new Font("Arial", Font.PLAIN, 16));
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		textTotal.setBackground(Color.WHITE);
		textTotal.setBounds(130, 22, 148, 22);
		panel_25.add(textTotal);

		textEfectivo = new JTextField();
		textEfectivo.setHorizontalAlignment(SwingConstants.CENTER);
		textEfectivo.setFont(new Font("Arial", Font.PLAIN, 16));
		textEfectivo.setColumns(10);
		textEfectivo.setBorder(new LineBorder(Color.BLACK));
		textEfectivo.setBounds(130, 49, 148, 22);
		panel_25.add(textEfectivo);

		textCambio = new JTextField();
		textCambio.setHorizontalAlignment(SwingConstants.CENTER);
		textCambio.setFont(new Font("Arial", Font.PLAIN, 16));
		textCambio.setEditable(false);
		textCambio.setColumns(10);
		textCambio.setBorder(new LineBorder(Color.BLACK));
		textCambio.setBackground(Color.WHITE);
		textCambio.setBounds(130, 78, 148, 22);
		panel_25.add(textCambio);

		JButton buttonCambio = new JButton("Calcular Cambio");
		buttonCambio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					if (!comproNucleo()) {
						JOptionPane.showMessageDialog(null, 
								"Debe ingresar una compra.", 
								"Error", JOptionPane.ERROR_MESSAGE);
						return; // Salir si no hay compras
					}
					try {
						double totalAPagar = Double.parseDouble(textTotal.getText().trim());
						double efectivoRecibido = Double.parseDouble(textEfectivo.getText().trim());

						if (efectivoRecibido < totalAPagar) {
							JOptionPane.showMessageDialog(VentaLibre, "El efectivo recibido no es suficiente.", "Error", JOptionPane.ERROR_MESSAGE);
							cambioCalculado = false;
							return;
						}

						double cambio = efectivoRecibido - totalAPagar;
						textCambio.setText(String.format("%.2f", cambio));

						// Marcar que el cambio ha sido calculado
						cambioCalculado = true;

					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(VentaLibre, "Por favor, ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
						cambioCalculado = false;
					}

			}
		});
		buttonCambio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		buttonCambio.setBackground(SystemColor.controlHighlight);
		buttonCambio.setBounds(318, 36, 164, 44);
		panel_25.add(buttonCambio);

		JPanel btnComprar = new JPanel();
		btnComprar.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            // Validación: debe haber al menos una compra
		            if (!comproNucleo()) {
		                JOptionPane.showMessageDialog(null, "Debe ingresar al menos una compra.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Validación: debe calcular el cambio antes de continuar
		            if (!cambioCalculado) {
		                JOptionPane.showMessageDialog(null, "Debe calcular el cambio antes de realizar la compra.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            boolean todasLasFilasValidas = true;

		         // Registrar todas las compras en el sistema
		            for (int i = 0; i < modeloAlmohadillas.getRowCount(); i++) {
		                try {
		                    // Obtener los valores de la fila actual
		                    Object precioObj = modeloAlmohadillas.getValueAt(i, 2); // Precio Unitario (columna 2)
		                    Object cantidadObj = modeloAlmohadillas.getValueAt(i, 3); // Cantidad (columna 3)

		                    // Validar que los valores no sean null y sean del tipo correcto
		                    if (precioObj == null || !(precioObj instanceof Double)) {
		                        JOptionPane.showMessageDialog(null, "Fila " + (i + 1) + ": El precio es inválido.", "Error", JOptionPane.ERROR_MESSAGE);
		                        continue;
		                    }

		                    if (cantidadObj == null || !(cantidadObj instanceof Integer)) {
		                        JOptionPane.showMessageDialog(null, "Fila " + (i + 1) + ": La cantidad es inválida.", "Error", JOptionPane.ERROR_MESSAGE);
		                        continue;
		                    }

		                    double precioUnitario = (Double) precioObj;
		                    int cantidad = (Integer) cantidadObj;

		                    // Registrar venta en Farmacia
		                    Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(
		                        LocalDate.now(),
		                        precioUnitario,
		                        cantidad
		                    );
		                    

		                } catch (Exception ex) {
		                    ex.printStackTrace();
		                    JOptionPane.showMessageDialog(null, "Error al procesar la fila " + (i + 1) + ": " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		                }
		            }

		            // Limpiar datos después de registrar todas las ventas
		            modeloAlmohadillas.getDataVector().clear();
		            modeloAlmohadillas.fireTableDataChanged();
		            textTotal.setText("0.0");
		            textEfectivo.setText("");
		            textCambio.setText("");
		            cambioCalculado = false;
		            
		            
		            // Mostrar mensaje de éxito
		            JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "Error al realizar la compra: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		btnComprar.setLayout(null);
		btnComprar.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(152, 251, 152), 2)));
		btnComprar.setBackground(new Color(204, 255, 204));
		btnComprar.setBounds(542, 503, 208, 82);
		VentaAlmohadillas.add(btnComprar);

		JLabel label_80 = new JLabel("Realizar ");
		label_80.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_80.setBounds(12, -11, 127, 80);
		btnComprar.add(label_80);

		JLabel label_81 = new JLabel("");
		label_81.setBounds(119, 13, 65, 53);
		btnComprar.add(label_81);
		UtilesInterfaz.ajustarImagen(label_81, "src/iconos/compracarrito.png");

		JLabel label_82 = new JLabel("Compra");
		label_82.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_82.setBounds(12, 13, 127, 93);
		btnComprar.add(label_82);

		JPanel btnCancelar = new JPanel();
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!comproNucleo()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar una compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					
					// 1. Limpiar la tabla de compras
					modeloAlmohadillas.getDataVector().clear();
					modeloAlmohadillas.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					textTotal.setText("0.0");
					textEfectivo.setText("");
					textCambio.setText("");
					
					

					
					
					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});
		btnCancelar.setLayout(null);
		btnCancelar.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(255, 0, 0), 2)));
		btnCancelar.setBackground(new Color(255, 105, 105));
		btnCancelar.setBounds(762, 503, 208, 82);
		VentaAlmohadillas.add(btnCancelar);

		JLabel label_83 = new JLabel("");
		label_83.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!comproNucleo()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar una compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					modeloAlmohadillas.getDataVector().clear();
					modeloAlmohadillas.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					textTotal.setText("0.0");
					textEfectivo.setText("");
					textCambio.setText("");

					
					
					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});
		label_83.setBounds(127, 13, 69, 53);
		btnCancelar.add(label_83);
		UtilesInterfaz.ajustarImagen(label_83, "src/iconos/circulo-cruzado.png");

		JLabel label_84 = new JLabel("Cancelar");
		label_84.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!comproNucleo()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar una compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					modeloAlmohadillas.getDataVector().clear();
					modeloAlmohadillas.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					textTotal.setText("0.0");
					textEfectivo.setText("");
					textCambio.setText("");

					
					
					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});
		label_84.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_84.setBounds(12, 13, 101, 33);
		btnCancelar.add(label_84);

		JLabel label_85 = new JLabel("Compra");
		label_85.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (!comproNucleo()) {
					JOptionPane.showMessageDialog(null, 
							"Debe ingresar una compra.", 
							"Error", JOptionPane.ERROR_MESSAGE);
					return; // Salir si no hay compras
				}
				// Mostrar mensaje de confirmación
				int opcion = JOptionPane.showConfirmDialog(
						null,
						"¿Está seguro de que desea cancelar la compra?\nSe perderán todos los datos ingresados hasta el momento.",
						"Confirmar Cancelación",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE
						);

				// Si el usuario confirma, proceder con la cancelación
				if (opcion == JOptionPane.YES_OPTION) {
					// 1. Limpiar la tabla de compras
					modeloAlmohadillas.getDataVector().clear();
					modeloAlmohadillas.fireTableDataChanged();

					// 2. Reiniciar el total acumulado
					textTotal.setText("0.0");
					textEfectivo.setText("");
					textCambio.setText("");

					
					
					// Opcional: Mostrar un mensaje de confirmación
					JOptionPane.showMessageDialog(
							null,
							"La compra ha sido cancelada.",
							"Compra Cancelada",
							JOptionPane.INFORMATION_MESSAGE
							);
				}
			}
		});
		label_85.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_85.setBounds(12, 13, 127, 93);
		btnCancelar.add(label_85);

		JLabel lblNcleos = new JLabel("N\u00FAcleos");
		lblNcleos.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNcleos.setBounds(34, 72, 121, 39);
		VentaAlmohadillas.add(lblNcleos);

		 final JComboBox <String>comboBoxNucleos = new JComboBox<String>();
		comboBoxNucleos.setFont(new Font("Tahoma", Font.PLAIN, 20));

		comboBoxNucleos.setBounds(124, 77, 244, 31);
		NucleosComboBoxModel nucleosComboBoxModel = new NucleosComboBoxModel();
		comboBoxNucleos.setModel(nucleosComboBoxModel);
		nucleosComboBoxModel.cargarDatos();

		VentaAlmohadillas.add(comboBoxNucleos);

		JButton button = new JButton("A\u00F1adir a la Compra");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
			        // Validaciones previas
			        String nucleoSeleccionado = (String) comboBoxNucleos.getSelectedItem();
			        if (nucleoSeleccionado == null || nucleoSeleccionado.equals("<Seleccione un núcleo>")) {
			            JOptionPane.showMessageDialog(null, "Debe seleccionar un núcleo familiar válido.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }
			        
			        String idNucleo = nucleoSeleccionado.split(" - ")[0];
			        Logica.NucleoFamiliar nucleo = Farmacia.obtenerInstancia().buscarNucleoPorId(idNucleo);
			        
			        if (nucleo.getCompraron()) {
			            JOptionPane.showMessageDialog(null, "Este núcleo ya realizó una compra.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        int cantidadMujeres = nucleo.getMujeres() != null ? nucleo.getMujeres().size() : 0;
			        if (cantidadMujeres <= 0) {
			            JOptionPane.showMessageDialog(null, "El núcleo seleccionado no tiene mujeres registradas.", "Error", JOptionPane.ERROR_MESSAGE);
			            return;
			        }

			        // Crear nueva compra
			        double precioUnitario = 8.5;
			        Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
			        
			        Logica.AlmohadillasSanitarias venta = new Logica.AlmohadillasSanitarias(
			            precioUnitario,
			            cantidadMujeres,
			            fechaDeCompra
			        );
			        
			        modeloAlmohadillas.adicionar(venta);
			        actualizarTotal(textTotal, modeloAlmohadillas);
			        nucleo.setCompraron(true);
			
			    } catch (Exception ex) {
			        ex.printStackTrace();
			        JOptionPane.showMessageDialog(null, "Error al añadir la compra: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			    }	
			}
				});

		button.setFont(new Font("Tahoma", Font.PLAIN, 20));
		button.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		button.setBounds(30, 124, 224, 51);
		VentaAlmohadillas.add(button);

		final JPanel VentaControlada = new JPanel();
		VentaControlada.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, VentaControlada, null);
		VentaControlada.setLayout(null);

		JPanel panel_19 = new JPanel();
		panel_19.setBounds(0, 0, 1015, 57);
		panel_19.setLayout(null);
		panel_19.setBackground(new Color(75, 255, 112));
		VentaControlada.add(panel_19);

		JLabel label_73 = new JLabel("");
		label_73.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		label_73.setBounds(12, 0, 57, 57);
		panel_19.add(label_73);
		UtilesInterfaz.ajustarImagen(label_73, "src/iconos/deshacer.png");

		JLabel lblVentaControlada = new JLabel("VENTA MEDICAMENTO CONTROLADO");
		lblVentaControlada.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblVentaControlada.setBounds(79, 0, 447, 57);
		panel_19.add(lblVentaControlada);
		
		JLabel label_87 = new JLabel("Paciente :");
		label_87.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_87.setBounds(64, 70, 123, 32);
		VentaControlada.add(label_87);
		
		JLabel label_88 = new JLabel("Medicamento :");
		label_88.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_88.setBounds(32, 122, 180, 32);
		VentaControlada.add(label_88);
		
		final JComboBox<String> comboBoxPacientesControlados = new JComboBox<String>();
		List<String> pacientesControlados = Farmacia.obtenerInstancia().obtenerListaPacientesControlados();
		
		// Crear el modelo personalizado
		PacientesControladosComboBoxModel modeloPacientesControlados = new PacientesControladosComboBoxModel(pacientesControlados);
		comboBoxPacientesControlados.setModel(modeloPacientesControlados);
		comboBoxPacientesControlados.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxPacientesControlados.setBorder(new LineBorder(Color.BLACK));
		comboBoxPacientesControlados.setBackground(Color.WHITE);
		comboBoxPacientesControlados.setBounds(199, 75, 270, 32);
		VentaControlada.add(comboBoxPacientesControlados);
		
		final JComboBox<String> comboBoxMedControlado = new JComboBox<String>();
		
		// Obtener la lista de medicamentos controlados
		List<String> medicamentosControlados = Farmacia.obtenerInstancia().obtenerListaMedicamentosControlados();

		// Crear el modelo personalizado
		MedicamentosControladosComboBoxModel modeloMedControlados = new MedicamentosControladosComboBoxModel(medicamentosControlados);
		comboBoxMedControlado.setModel(modeloMedControlados);

		comboBoxMedControlado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBoxMedControlado.setBorder(new LineBorder(Color.BLACK));
		comboBoxMedControlado.setBackground(Color.WHITE);
		comboBoxMedControlado.setBounds(199, 120, 270, 32);
		VentaControlada.add(comboBoxMedControlado);
		
		JLabel label_89 = new JLabel("Cantidad :");
		label_89.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_89.setBounds(501, 70, 128, 32);
		VentaControlada.add(label_89);
		
		final JComboBox<Integer> comboBoxCantid = new JComboBox<Integer>();
		comboBoxCantid.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBoxCantid.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxCantid.setBorder(new LineBorder(Color.BLACK));
		comboBoxCantid.setBounds(619, 75, 79, 32);
		VentaControlada.add(comboBoxCantid);
		
		JButton button_1 = new JButton("A\u00F1adir a la Compra");
		button_1.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		           

		            // Obtener selección de cantidad
		            Object cantidadObj = comboBoxCantid.getSelectedItem();

		            if (cantidadObj == null) {
		                JOptionPane.showMessageDialog(VentaControlada, "Por favor, seleccione una cantidad válida.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            int cantidadSeleccionada;
		            if (cantidadObj instanceof Integer) {
		                cantidadSeleccionada = (Integer) cantidadObj;
		            } else if (cantidadObj instanceof String) {
		                try {
		                    cantidadSeleccionada = Integer.parseInt((String) cantidadObj);
		                } catch (NumberFormatException ex) {
		                    JOptionPane.showMessageDialog(VentaControlada, "La cantidad debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
		                    return;
		                }
		            } else {
		                JOptionPane.showMessageDialog(VentaControlada, "Tipo de cantidad no soportado.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            if (cantidadSeleccionada <= 0) {
		                JOptionPane.showMessageDialog(VentaControlada, "La cantidad debe ser mayor que cero.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Obtener selección de paciente
		            String nombrePaciente = (String) comboBoxPacientesControlados.getSelectedItem();

		            if ("<Seleccione un paciente>".equals(nombrePaciente)) {
		                JOptionPane.showMessageDialog(VentaControlada, "Por favor, seleccione un paciente válido.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Verificar límite de medicamento (ejemplo: 10 unidades)
		            int cantidadAcumulada = cantidadesAcumuladas.getOrDefault(medicamento.getNomComun(), 0);

		            if (cantidadAcumulada + cantidadSeleccionada > 10) {
		                JOptionPane.showMessageDialog(VentaControlada, "No puede comprar más de 10 unidades de este medicamento.", "Error", JOptionPane.ERROR_MESSAGE);
		                return;
		            }

		            // Crear nueva venta
		            Date fechaDeCompra = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		            Logica.VentaControlada venta = new Logica.VentaControlada(
		                fechaDeCompra,
		                medicamento.getPrecio() * cantidadSeleccionada,
		                medicamento.getNomComun(),
		                medicamento.getCodigo(),
		                cantidadSeleccionada
		            );

		            // Añadir al modelo de la tabla
		            compraControladaTableModel.adicionar(venta);

		            // Actualizar cantidad acumulada
		            cantidadesAcumuladas.put(medicamento.getNomComun(), cantidadAcumulada + cantidadSeleccionada);

		            // Deshabilitar combo de pacientes si es la primera vez
		            if (!pacienteSeleccionado) {
		                comboBoxPacientesControlados.setEnabled(false);
		                pacienteSeleccionado = true;
		            }

		            // Opcional: mostrar mensaje de éxito
		            JOptionPane.showMessageDialog(VentaControlada, "Medicamento añadido correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

		        } catch (Exception ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(VentaControlada, "Error al añadir la compra: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		button_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		button_1.setBounds(496, 113, 183, 41);
		VentaControlada.add(button_1);
		
		JPanel panel_23 = new JPanel();
		panel_23.setLayout(null);
		panel_23.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_23.setBackground(SystemColor.menu);
		panel_23.setBounds(12, 183, 981, 279);
		VentaControlada.add(panel_23);
		
		JLabel label_90 = new JLabel("Compra");
		label_90.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_90.setBounds(30, 13, 153, 29);
		panel_23.add(label_90);
		
		JScrollPane scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(12, 55, 957, 211);
		panel_23.add(scrollPane_7);
		
		tablaControlados = new JTable();
		scrollPane_7.setViewportView(tablaControlados);
		//Inicializar el modelo de la tabla
		compraControladaTableModel = new CompraControladaTableModel();
		tablaControlados.setModel(compraControladaTableModel);
		
		JPanel panel_26 = new JPanel();
		panel_26.setLayout(null);
		panel_26.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_26.setBackground(SystemColor.menu);
		panel_26.setBounds(22, 496, 507, 126);
		VentaControlada.add(panel_26);
		
		JLabel label_91 = new JLabel("Total a Pagar :");
		label_91.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_91.setBounds(12, 13, 133, 37);
		panel_26.add(label_91);
		
		JLabel label_92 = new JLabel("Efectivo :");
		label_92.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_92.setBounds(12, 48, 122, 21);
		panel_26.add(label_92);
		
		JLabel label_93 = new JLabel("Cambio :");
		label_93.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_93.setBounds(12, 74, 97, 27);
		panel_26.add(label_93);
		
		textField = new JTextField();
		textField.setText("0.0");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Arial", Font.PLAIN, 16));
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField.setBackground(Color.WHITE);
		textField.setBounds(130, 22, 148, 22);
		panel_26.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_1.setColumns(10);
		textField_1.setBorder(new LineBorder(Color.BLACK));
		textField_1.setBounds(130, 49, 148, 22);
		panel_26.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBorder(new LineBorder(Color.BLACK));
		textField_2.setBackground(Color.WHITE);
		textField_2.setBounds(130, 78, 148, 22);
		panel_26.add(textField_2);
		
		JButton button_2 = new JButton("Calcular Cambio");
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBackground(SystemColor.controlHighlight);
		button_2.setBounds(318, 36, 164, 44);
		panel_26.add(button_2);
		
		JPanel panel_27 = new JPanel();
		panel_27.setLayout(null);
		panel_27.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(152, 251, 152), 2)));
		panel_27.setBackground(new Color(204, 255, 204));
		panel_27.setBounds(559, 525, 208, 82);
		VentaControlada.add(panel_27);
		
		JLabel label_94 = new JLabel("Realizar ");
		label_94.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_94.setBounds(12, -11, 127, 80);
		panel_27.add(label_94);
		
		JLabel label_95 = new JLabel("");
		label_95.setBounds(119, 13, 65, 53);
		panel_27.add(label_95);
		
		JLabel label_96 = new JLabel("Compra");
		label_96.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_96.setBounds(12, 13, 127, 93);
		panel_27.add(label_96);
		
		JPanel panel_28 = new JPanel();
		panel_28.setLayout(null);
		panel_28.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(255, 0, 0), 2)));
		panel_28.setBackground(new Color(255, 105, 105));
		panel_28.setBounds(779, 525, 208, 82);
		VentaControlada.add(panel_28);
		
		JLabel label_97 = new JLabel("");
		label_97.setBounds(127, 13, 69, 53);
		panel_28.add(label_97);
		
		JLabel label_98 = new JLabel("Cancelar");
		label_98.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_98.setBounds(12, 13, 101, 33);
		panel_28.add(label_98);
		
		JLabel label_99 = new JLabel("Compra");
		label_99.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_99.setBounds(12, 13, 127, 93);
		panel_28.add(label_99);

		JPanel VentaPrescripcion = new JPanel();
		VentaPrescripcion.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, VentaPrescripcion, null);
		VentaPrescripcion.setLayout(null);

		JPanel panel_20 = new JPanel();
		panel_20.setBounds(0, 0, 1015, 57);
		panel_20.setLayout(null);
		panel_20.setBackground(new Color(75, 255, 112));
		VentaPrescripcion.add(panel_20);

		JLabel label_75 = new JLabel("");
		label_75.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(1);
			}
		});
		label_75.setBounds(12, 0, 57, 57);
		panel_20.add(label_75);
		UtilesInterfaz.ajustarImagen(label_75, "src/iconos/deshacer.png");

		JLabel lblVentaPrescripcion = new JLabel("VENTA PRESCRIPCION M\u00C9DICA");
		lblVentaPrescripcion.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblVentaPrescripcion.setBounds(79, 0, 510, 57);
		panel_20.add(lblVentaPrescripcion);
		
		JLabel label_100 = new JLabel("Paciente :");
		label_100.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_100.setBounds(62, 70, 123, 32);
		VentaPrescripcion.add(label_100);
		
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		comboBox_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_3.setBorder(new LineBorder(Color.BLACK));
		comboBox_3.setBackground(Color.WHITE);
		comboBox_3.setBounds(197, 75, 270, 32);
		VentaPrescripcion.add(comboBox_3);
		
		JComboBox<String> comboBox_4 = new JComboBox<String>();
		comboBox_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_4.setBorder(new LineBorder(Color.BLACK));
		comboBox_4.setBackground(Color.WHITE);
		comboBox_4.setBounds(197, 120, 270, 32);
		VentaPrescripcion.add(comboBox_4);
		
		JLabel label_101 = new JLabel("Medicamento :");
		label_101.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_101.setBounds(30, 122, 180, 32);
		VentaPrescripcion.add(label_101);
		
		JLabel label_102 = new JLabel("Cantidad :");
		label_102.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_102.setBounds(499, 70, 128, 32);
		VentaPrescripcion.add(label_102);
		
		JComboBox<Integer> comboBox_5 = new JComboBox<Integer>();
		comboBox_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox_5.setBorder(new LineBorder(Color.BLACK));
		comboBox_5.setBounds(617, 75, 79, 32);
		VentaPrescripcion.add(comboBox_5);
		
		JButton button_3 = new JButton("A\u00F1adir a la Compra");
		button_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		button_3.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		button_3.setBounds(494, 113, 183, 41);
		VentaPrescripcion.add(button_3);
		
		JPanel panel_29 = new JPanel();
		panel_29.setLayout(null);
		panel_29.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_29.setBackground(SystemColor.menu);
		panel_29.setBounds(10, 183, 981, 279);
		VentaPrescripcion.add(panel_29);
		
		JLabel label_103 = new JLabel("Compra");
		label_103.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_103.setBounds(30, 13, 153, 29);
		panel_29.add(label_103);
		
		JPanel panel_30 = new JPanel();
		panel_30.setLayout(null);
		panel_30.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		panel_30.setBackground(SystemColor.menu);
		panel_30.setBounds(20, 496, 507, 126);
		VentaPrescripcion.add(panel_30);
		
		JLabel label_104 = new JLabel("Total a Pagar :");
		label_104.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_104.setBounds(12, 13, 133, 37);
		panel_30.add(label_104);
		
		JLabel label_105 = new JLabel("Efectivo :");
		label_105.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_105.setBounds(12, 48, 122, 21);
		panel_30.add(label_105);
		
		JLabel label_106 = new JLabel("Cambio :");
		label_106.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_106.setBounds(12, 74, 97, 27);
		panel_30.add(label_106);
		
		textField_3 = new JTextField();
		textField_3.setText("0.0");
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		textField_3.setBackground(Color.WHITE);
		textField_3.setBounds(130, 22, 148, 22);
		panel_30.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_4.setColumns(10);
		textField_4.setBorder(new LineBorder(Color.BLACK));
		textField_4.setBounds(130, 49, 148, 22);
		panel_30.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Arial", Font.PLAIN, 16));
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		textField_5.setBorder(new LineBorder(Color.BLACK));
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(130, 78, 148, 22);
		panel_30.add(textField_5);
		
		JButton button_4 = new JButton("Calcular Cambio");
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_4.setBackground(SystemColor.controlHighlight);
		button_4.setBounds(318, 36, 164, 44);
		panel_30.add(button_4);
		
		JPanel panel_31 = new JPanel();
		panel_31.setLayout(null);
		panel_31.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(152, 251, 152), 2)));
		panel_31.setBackground(new Color(204, 255, 204));
		panel_31.setBounds(557, 525, 208, 82);
		VentaPrescripcion.add(panel_31);
		
		JLabel label_107 = new JLabel("Realizar ");
		label_107.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_107.setBounds(12, -11, 127, 80);
		panel_31.add(label_107);
		
		JLabel label_108 = new JLabel("");
		label_108.setBounds(119, 13, 65, 53);
		panel_31.add(label_108);
		
		JLabel label_109 = new JLabel("Compra");
		label_109.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_109.setBounds(12, 13, 127, 93);
		panel_31.add(label_109);
		
		JPanel panel_32 = new JPanel();
		panel_32.setLayout(null);
		panel_32.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(255, 0, 0), 2)));
		panel_32.setBackground(new Color(255, 105, 105));
		panel_32.setBounds(777, 525, 208, 82);
		VentaPrescripcion.add(panel_32);
		
		JLabel label_110 = new JLabel("");
		label_110.setBounds(127, 13, 69, 53);
		panel_32.add(label_110);
		
		JLabel label_111 = new JLabel("Cancelar");
		label_111.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_111.setBounds(12, 13, 101, 33);
		panel_32.add(label_111);
		
		JLabel label_112 = new JLabel("Compra");
		label_112.setFont(new Font("Tahoma", Font.PLAIN, 27));
		label_112.setBounds(12, 13, 127, 93);
		panel_32.add(label_112);


		JPanel usuarios = new JPanel();
		pestanas.addTab("New tab", null, usuarios, null);
		usuarios.setLayout(null);

		JPanel panel_16 = new JPanel();
		panel_16.setLayout(null);
		panel_16.setBackground(new Color(75, 255, 112));
		panel_16.setBounds(0, 0, 1016, 43);
		usuarios.add(panel_16);

		JLabel lblUsuarios_1 = new JLabel("USUARIOS");
		lblUsuarios_1.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblUsuarios_1.setBounds(12, 0, 205, 43);
		panel_16.add(lblUsuarios_1);

		// Inicialización del modelo
		TablaDePacientes modeloTabla = new TablaDePacientes();

		// Inicialización de los datos
		Farmacia.obtenerInstancia().inicializarDatosPrueba();
		modeloTabla.actualizar(Farmacia.obtenerInstancia().getPacientes());

		// Creacion de la tabla con el modelo
		JTable tablaPacientes = new JTable(modeloTabla);
		tablaPacientes.setRowHeight(28);

		// Agregar al JScrollPane
		JScrollPane scrollPane_6;
		scrollPane_6 = new JScrollPane(tablaPacientes);
		scrollPane_6.setBounds(0, 395, 1005, 255);
		usuarios.add(scrollPane_6);
	}
}