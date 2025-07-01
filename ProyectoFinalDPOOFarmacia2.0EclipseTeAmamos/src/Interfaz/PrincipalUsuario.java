package Interfaz;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;

import Logica.Farmacia;
import Utiles.Navegacion;
import Utiles.UtilesInterfaz;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Label;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import modelos.AlmohadillasNecesariasTableModel;
import modelos.ButtonRenderer;
import modelos.ComparacionVentasTableModel;
import modelos.ComprasTableModel;
import modelos.MedicamentoTableModel;
import modelos.MedicamentosComboBoxModel;
import modelos.MedicamentosMasVendidosTableModel;
import modelos.ModeloPrincipalTableModel;
import modelos.PacientesComboBoxModel;
import modelos.TarjetonesIncumplidosTableModel;

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
	private void actualizarTotal(JTextField txtTotal, ComprasTableModel comprasTableModel) {
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
		menu.setBackground(new Color(152, 251, 152));
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
				panel1.setBackground(new Color(75, 255, 112));
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel1.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(0);
			}
		});
		panel1.setBackground(new Color(152, 251, 152));
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
		panel2.setBackground(new Color(152, 251, 152));
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
		
		final JPanel panel_8 = new JPanel();
		panel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_8.setBackground(new Color(75, 255, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_8.setBackground(new Color(152, 251, 152));
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		panel_8.setBackground(new Color(152, 251, 152));
		panel_8.setBounds(0, 412, 281, 68);
		menu.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel lblRegistrarse = new JLabel("REGISTRARSE");
		lblRegistrarse.setFont(new Font("Times New Roman", Font.BOLD, 26));
		lblRegistrarse.setBounds(65, 13, 191, 42);
		panel_8.add(lblRegistrarse);
		
		JLabel iconoRegistrarse = new JLabel("");
		iconoRegistrarse.setBounds(12, 13, 41, 42);
		panel_8.add(iconoRegistrarse);
		UtilesInterfaz.ajustarImagen(iconoRegistrarse, "src/iconos/usuario(1).png");
		
		final JPanel panel_9 = new JPanel();
		panel_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_9.setBackground(new Color(75, 255, 112));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_9.setBackground(new Color(152, 251, 152));
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
		panel_9.setBackground(new Color(152, 251, 152));
		panel_9.setBounds(0, 484, 281, 68);
		menu.add(panel_9);
		panel_9.setLayout(null);
		
		
		JLabel label_15 = new JLabel("CERRAR SESI\u00D3N");
		label_15.addMouseListener(new MouseAdapter() {
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
		label_15.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_15.setBounds(63, 13, 218, 49);
		panel_9.add(label_15);
		
		JLabel iconoCerrarSesion = new JLabel("");
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
		iconoCerrarSesion.setBounds(12, 13, 41, 42);
		panel_9.add(iconoCerrarSesion);
		UtilesInterfaz.ajustarImagen(iconoCerrarSesion, "src/iconos/cierre-de-sesion-de-usuario.png");
		
		JPanel barraSuperior = new JPanel();
		barraSuperior.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		
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
		barraSuperior.setBackground(new Color(0, 255, 127));
		barraSuperior.setBounds(-14, -11, 1189, 67);
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
		cruz.setBounds(1086, 13, 78, 56);
		barraSuperior.add(cruz);
		UtilesInterfaz.ajustarImagen(cruz, "src/iconos/exit0.png");
		
		JLabel minimizar = new JLabel("");
		minimizar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setState(JFrame.ICONIFIED);
			}
		});
		minimizar.setBounds(1021, 13, 78, 56);
		barraSuperior.add(minimizar);
		UtilesInterfaz.ajustarImagen(minimizar, "src/iconos/minimize0.png");
		
		JLabel label_2 = new JLabel("Sistema de Gesti\u00F3n de Procesos en las Farmacia");
		label_2.setForeground(Color.BLACK);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		label_2.setBounds(27, 24, 681, 45);
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
		panel.setBackground(new Color(75, 255, 112));
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
		comprar.setBackground(Color.WHITE);
		pestanas.addTab("New tab", null, comprar, null);
		comprar.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(75, 255, 112));
		panel_1.setBounds(0, 0, 875, 43);
		comprar.add(panel_1);
		
		JLabel label_6 = new JLabel("COMPRAR");
		label_6.setFont(new Font("Times New Roman", Font.BOLD, 26));
		label_6.setBounds(12, 0, 144, 43);
		panel_1.add(label_6);
		
		JPanel panel_4 = new JPanel();
		panel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(3);
			}
		});
		panel_4.setLayout(null);
		panel_4.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		panel_4.setBackground(new Color(150, 255, 147));
		panel_4.setBounds(35, 159, 353, 200);
		comprar.add(panel_4);
		
		JLabel label_16 = new JLabel("_______________________");
		label_16.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(3);
			}
		});
		label_16.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_16.setBounds(0, 13, 381, 50);
		panel_4.add(label_16);
		
		JLabel label_17 = new JLabel("Venta Libre");
		label_17.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(3);
			}
		});
		label_17.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_17.setBounds(12, 13, 192, 43);
		panel_4.add(label_17);
		
		JLabel label_18 = new JLabel("Venta de medicamentos que no requieren");
		label_18.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pestanas.setSelectedIndex(3);
			}
		});
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_18.setBounds(10, 13, 402, 119);
		panel_4.add(label_18);
		
		JLabel label_19 = new JLabel("de tarjet\u00F3n o receta m\u00E9dica");
		label_19.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_19.setBounds(10, 82, 317, 22);
		panel_4.add(label_19);
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		panel_5.setBackground(new Color(150, 255, 147));
		panel_5.setBounds(35, 400, 353, 200);
		comprar.add(panel_5);
		
		JLabel label_20 = new JLabel("_______________________");
		label_20.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_20.setBounds(0, 13, 381, 50);
		panel_5.add(label_20);
		
		JLabel label_21 = new JLabel("Venta Medicamento Controlado");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_21.setBounds(12, 13, 341, 43);
		panel_5.add(label_21);
		
		JLabel label_22 = new JLabel("Venta de medicamentos que requieren");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_22.setBounds(10, 53, 331, 22);
		panel_5.add(label_22);
		
		JLabel label_23 = new JLabel("de un tarjet\u00F3n");
		label_23.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_23.setBounds(10, 76, 210, 22);
		panel_5.add(label_23);
		
		JPanel panel_6 = new JPanel();
		panel_6.setLayout(null);
		panel_6.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		panel_6.setBackground(new Color(150, 255, 147));
		panel_6.setBounds(483, 400, 353, 200);
		comprar.add(panel_6);
		
		JLabel label_24 = new JLabel("_______________________");
		label_24.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_24.setBounds(0, 13, 381, 50);
		panel_6.add(label_24);
		
		JLabel label_25 = new JLabel("Venta Prescripci\u00F3n M\u00E9dica");
		label_25.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_25.setBounds(12, 13, 329, 43);
		panel_6.add(label_25);
		
		JLabel label_26 = new JLabel("Venta de medicamentos que requieren");
		label_26.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_26.setBounds(10, 58, 331, 22);
		panel_6.add(label_26);
		
		JLabel label_27 = new JLabel("de una receta m\u00E9dica");
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_27.setBounds(10, 79, 314, 22);
		panel_6.add(label_27);
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBorder(new CompoundBorder(new LineBorder(new Color(0, 255, 0), 2), new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0))));
		panel_7.setBackground(new Color(150, 255, 147));
		panel_7.setBounds(483, 159, 353, 200);
		comprar.add(panel_7);
		
		JLabel label_28 = new JLabel("_______________________");
		label_28.setFont(new Font("Tahoma", Font.BOLD, 25));
		label_28.setBounds(0, 13, 381, 50);
		panel_7.add(label_28);
		
		JLabel label_29 = new JLabel("Venta Almohadillas Sanitarias");
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 24));
		label_29.setBounds(12, 13, 329, 43);
		panel_7.add(label_29);
		
		JLabel label_30 = new JLabel("Venta de almohadillas sanitarias para");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_30.setBounds(10, 57, 331, 29);
		panel_7.add(label_30);
		
		JLabel label_31 = new JLabel("las mujeres del n\u00FAcleo familiar");
		label_31.setFont(new Font("Tahoma", Font.PLAIN, 18));
		label_31.setBounds(10, 69, 316, 41);
		panel_7.add(label_31);
		
		JLabel label_32 = new JLabel("Elija como desea realizar su compra");
		label_32.setFont(new Font("Times New Roman", Font.BOLD, 28));
		label_32.setBounds(10, 45, 840, 43);
		comprar.add(label_32);
		
		Label label_33 = new Label("_______________________________________________________________________");
		label_33.setForeground(new Color(75, 255, 112));
		label_33.setFont(new Font("Arial Black", Font.BOLD, 50));
		label_33.setBounds(0, 33, 875, 79);
		comprar.add(label_33);
		
		JPanel medicamentos = new JPanel();
		pestanas.addTab("New tab", null, medicamentos, null);
		medicamentos.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(75, 255, 112));
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
				label_9.setForeground(new Color(75, 255, 112));
				label_9.setBounds(0, 0, 494, 165);
				medicamentos.add(label_9);
				
				JLabel label_10 = new JLabel("_________");
				label_10.setForeground(new Color(75, 255, 112));
				label_10.setFont(new Font("Tahoma", Font.PLAIN, 99));
				label_10.setBounds(398, 0, 494, 165);
				medicamentos.add(label_10);
				
				JLabel lblMedicamentosDisponibles = new JLabel("Medicamentos Disponibles");
				lblMedicamentosDisponibles.setFont(new Font("Tahoma", Font.BOLD, 25));
				lblMedicamentosDisponibles.setBounds(244, 70, 368, 43);
				medicamentos.add(lblMedicamentosDisponibles);
				
				final JPanel VentaLibre = new JPanel();
				VentaLibre.setBackground(Color.WHITE);
				pestanas.addTab("New tab", null, VentaLibre, null);
				VentaLibre.setLayout(null);

				JScrollPane scrollPane_5 = new JScrollPane();
				scrollPane_5.setFont(new Font("Arial", Font.PLAIN, 19));
				scrollPane_5.setBounds(38, 224, 810, 197);
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

				final JComboBox <Integer>comboBoxCantidad = new JComboBox<Integer>();
				comboBoxCantidad.setBorder(new LineBorder(Color.BLACK));
				comboBoxCantidad.setModel(new DefaultComboBoxModel(new String[] {"0", "1 ", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));
				comboBoxCantidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
				comboBoxCantidad.setBounds(611, 85, 79, 32);
				VentaLibre.add(comboBoxCantidad);

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
				        int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCantidad.getSelectedItem()).trim());

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
				                "Debe ingresar algún medicamento a la compra.", 
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
					        int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCantidad.getSelectedItem()).trim());

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
					        comboBoxCantidad.setSelectedItem("0");

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
				btnRealizarCompra.setBounds(555, 482, 153, 57);
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
					        int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCantidad.getSelectedItem()).trim());

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
					        comboBoxCantidad.setSelectedItem("0");

					        // Mostrar mensaje de éxito
					        JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

					    } catch (RuntimeException ex) {
					        // Manejar errores, como medicamento no encontrado
					        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					    }
					}
				});
				lblRealizarCompra.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblRealizarCompra.setBounds(12, -26, 127, 80);
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
					        int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCantidad.getSelectedItem()).trim());

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
					        comboBoxCantidad.setSelectedItem("0");

					        // Mostrar mensaje de éxito
					        JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

					    } catch (RuntimeException ex) {
					        // Manejar errores, como medicamento no encontrado
					        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					    }
					}
				});
				label_72.setBounds(91, 13, 48, 38);
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
					        int cantidadSeleccionada = Integer.parseInt(((String) comboBoxCantidad.getSelectedItem()).trim());

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
					        comboBoxCantidad.setSelectedItem("0");

					        // Mostrar mensaje de éxito
					        JOptionPane.showMessageDialog(null, "Compra realizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

					    } catch (RuntimeException ex) {
					        // Manejar errores, como medicamento no encontrado
					        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					    }
					}
				});
				lblCompra_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblCompra_1.setBounds(12, -11, 127, 93);
				btnRealizarCompra.add(lblCompra_1);

				JPanel panel_21 = new JPanel();
				panel_21.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
				panel_21.setBackground(UIManager.getColor("Button.background"));
				panel_21.setBounds(12, 175, 851, 279);
				VentaLibre.add(panel_21);
				panel_21.setLayout(null);

				JLabel lblCompra = new JLabel("Compra");
				lblCompra.setFont(new Font("Tahoma", Font.PLAIN, 24));
				lblCompra.setBounds(30, 13, 153, 29);
				panel_21.add(lblCompra);
				
				JPanel panel_23 = new JPanel();
				panel_23.addMouseListener(new MouseAdapter() {
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
					        comboBoxCantidad.setSelectedItem("0");

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
				panel_23.setBorder(new CompoundBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)), new LineBorder(new Color(255, 0, 0), 2)));
				panel_23.setBackground(new Color(255, 105, 105));
				panel_23.setBounds(555, 552, 153, 62);
				VentaLibre.add(panel_23);
				panel_23.setLayout(null);
				
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
					        comboBoxCantidad.setSelectedItem("0");

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
					        comboBoxCantidad.setSelectedItem("0");

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
				
				label_76.setBounds(99, 13, 40, 38);
				panel_23.add(label_76);
				lblCancelar.setFont(new Font("Tahoma", Font.PLAIN, 20));
				lblCancelar.setBounds(12, 0, 101, 33);
				panel_23.add(lblCancelar);
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
					        comboBoxCantidad.setSelectedItem("0");

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
				label_74.setFont(new Font("Tahoma", Font.PLAIN, 20));
				label_74.setBounds(12, -11, 127, 93);
				panel_23.add(label_74);
				
				JPanel panel_11 = new JPanel();
				pestanas.addTab("New tab", null, panel_11, null);
				
				JPanel panel_12 = new JPanel();
				pestanas.addTab("New tab", null, panel_12, null);
				
				JPanel panel_13 = new JPanel();
				pestanas.addTab("New tab", null, panel_13, null);
				
				JPanel panel_14 = new JPanel();
				pestanas.addTab("New tab", null, panel_14, null);
				

				// CARGAR DATOS DESDE LA FARMACIA
				Farmacia.obtenerInstancia().inicializarDatosPrueba();

				// LLENAR EL MODELO CON LOS DATOS
				medicamentoTableModel.actualizar(Farmacia.obtenerInstancia().getMedicamentos());
	}
}
