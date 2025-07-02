package Logica;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

import LogicaUtiles.Factura;
import LogicaUtiles.Porcentaje;

import LogicaUtiles.Factura;
import LogicaUtiles.Validaciones;

import Logica.Medicamento;
import LogicaUtiles.VentaDeMedicamentos;
import Interfaces_Enum.Facturar;
import Interfaces_Enum.GestionarStockAlmohadillasSanitarias;
import Interfaces_Enum.Reportes;
import Logica.NucleoFamiliar;

public class Farmacia implements Reportes,Facturar,GestionarStockAlmohadillasSanitarias 
{
	private static Farmacia instancia;
	private ArrayList<Medicamento> medicamentos;
	private ArrayList <Venta> historialVentas;
	private ArrayList <NucleoFamiliar> nucleos;
	private ArrayList<Tarjeton> tarjetones;
	private ArrayList<Factura> facturas;
	private ArrayList<Paciente> pacientes;
	private long cantidadDeAlmohadillasSanitarias;
	private ArrayList<MedicamentoControlado> medicamentoControlado;
	private ArrayList<String> carnets;

	// Constructor PRIVADO
	private Farmacia() 
	{
		// Inicializar todas las listas
		this.medicamentos = new ArrayList<Medicamento>();
		this.historialVentas = new ArrayList<Venta>();
		this.nucleos = new ArrayList<NucleoFamiliar>();
		this.tarjetones = new ArrayList<Tarjeton>();
		this.facturas = new ArrayList<Factura>();
		this.cantidadDeAlmohadillasSanitarias = 10;
		this.pacientes = new ArrayList<Paciente>();
		this.medicamentoControlado = new ArrayList<MedicamentoControlado>();
		this.carnets = new ArrayList<String>();
	}

	// Método para obtener la instancia
	public static Farmacia obtenerInstancia() 
	{
		if (instancia == null) 
		{
			instancia = new Farmacia();
		}
		return instancia;
	}

	public void inicializarDatosPrueba() 
	{
		inicializarPacientes();
		inicializarMedicamentos();
		inicializarMedicamentosControlados();
		inicializarTarjetones();
		inicializarVentaControlada();
		inicializarVentaConPrescripcion();
		inicializarAlmohadillas();
		inicializarVentaLibre();
		generarFacturasDesdeVentas();
		inicializarNucleosFamiliares();
		actualizarInventario();
		Validaciones.removerPacientesRepetidos();
		Validaciones.removerMedicamentosControladosRepetidos(); 
		Validaciones.removerMedicamentosRepetidos() ; 
		Validaciones.carnetsDeIdentidadRepetido();
		System.out.println("Total de medicamentos: "+Farmacia.obtenerInstancia().getMedicamentos().size());
		System.out.println("Total de medicamentos Controlados: "+Farmacia.obtenerInstancia().getMedicamentoControlado().size());
	}


	//-------------------------------------------------Reportes-----------------------------------------------------------
	// Primer reporte
	public ArrayList<VentaDeMedicamentos> medicamentosMasVendidos()
	{
		ArrayList<VentaDeMedicamentos> medVend = new ArrayList<VentaDeMedicamentos>();
		ArrayList<VentaDeMedicamentos> medMasVendidos = buscarOrdenDeAccion();

		for(int i = 0; i < 10; i++)
			medVend.add(medMasVendidos.get(i));

		return medVend;
	}

	// Segundo reporte
	public long cantDeAlmohadillasNecesarias()
	{
		long cantidad = calcularCantidad();
		//if(cantidad < 0)
		//cantidad = 0;
		return cantidad;

	}

	// Tercer Reporte

	public ArrayList<Porcentaje> comparacionDeVentasMensuales()
	{
		ArrayList<Porcentaje> resultado =  new ArrayList<Porcentaje>();
		resultado = calcularValor();
		return resultado;
	}

	// Cuarto Reporte
	public ArrayList<Tarjeton> registroDeIncumplimiento()
	{
		ArrayList<Tarjeton> tarjetonesNoActivos = tarjetonesDesactivados();
		return tarjetonesNoActivos;
	}


	//------------------------------------------------------MetodosDeLosReportes-----------------------------------------------------------------------------

	// Metodos del primer reporte 
	public ArrayList<VentaDeMedicamentos> buscarOrdenDeAccion()
	{
		ArrayList<VentaDeMedicamentos> ventas = new ArrayList<VentaDeMedicamentos>();
		ventas = listaDeMedDeVentas();
		Collections.sort(ventas, comparador);
		return ventas;
	}

	Comparator<VentaDeMedicamentos> comparador = new Comparator<VentaDeMedicamentos>() 
			{
		public int compare(VentaDeMedicamentos a, VentaDeMedicamentos b) 
		{
			int cantidadB = b.getCantidadVendida();
			int cantidadA = a.getCantidadVendida();
			return cantidadB - cantidadA ;
		}
			};


			public ArrayList<VentaDeMedicamentos> listaDeMedDeVentas() 
			{
				ArrayList<VentaDeMedicamentos> ventas = new ArrayList<VentaDeMedicamentos>();

				// Medicamento Controlado 
				for(MedicamentoControlado m: medicamentoControlado) 
				{
					int total = 0;

					if (m.getTipo().equals("Medicamento controlado")) 
					{
						for(Venta v: historialVentas) 
						{
							if(v instanceof VentaControlada) 
							{
								if (!seRepiteElMedicamento(m, ventas)) 
								{
									if (((VentaControlada) v).getNombreDelMed().equals(m.nomComun)) 
									{
										total += ((VentaControlada) v).getCantMedVendidos(); 

									}
								}
							}
						}
					}

					if(total > 0) 
					{
						VentaDeMedicamentos venta = new VentaDeMedicamentos();
						venta.setCantidadVendida(total);
						venta.setNombre(m.nomComun);
						ventas.add(venta); 

					}
				}

				// Medicamento Libre
				for(Medicamento m: medicamentos) 
				{
					int totalVendidoDelMedicamento = 0;

					if (m.getTipo().equals("Venta libre")) 
					{
						totalVendidoDelMedicamento = 0;
						for(Venta v: historialVentas) 
						{
							if(v instanceof VentaLibre) 
							{
								if (!seRepiteElMedicamento(m, ventas)) 
								{
									if (((VentaLibre) v).getNombreDelMed().equals(m.nomComun)) 
									{
										totalVendidoDelMedicamento += ((VentaLibre) v).getCantMedVendidos();
									}
								}
							}
						}

					}

					if(totalVendidoDelMedicamento > 0) 
					{
						VentaDeMedicamentos venta = new VentaDeMedicamentos();
						venta.setCantidadVendida(totalVendidoDelMedicamento);
						venta.setNombre(m.nomComun);
						ventas.add(venta); 

					}
				}

				// Medicamento Con Prescripción
				for(Medicamento m: medicamentos) 
				{
					int totalVendidoDelMedicamento = 0;

					if (m.getTipo().equals("Con prescripción")) 
					{

						for(Venta v: historialVentas) 
						{
							if(v instanceof VentaConPrescripcion) 
							{
								if (!seRepiteElMedicamento(m, ventas)) 
								{
									if (((VentaConPrescripcion) v).getNombreDelMed().equals(m.nomComun)) 
									{
										totalVendidoDelMedicamento += ((VentaConPrescripcion) v).getCantMedVendidos();
									}
								}
							}
						}

					}

					if(totalVendidoDelMedicamento > 0) 
					{
						VentaDeMedicamentos venta = new VentaDeMedicamentos();
						venta.setCantidadVendida(totalVendidoDelMedicamento);
						venta.setNombre(m.nomComun);
						ventas.add(venta); 

					}
				}

				return ventas;
			}



			public boolean seRepiteElMedicamento(Medicamento med, ArrayList<VentaDeMedicamentos> ventas)
			{
				boolean repite = false;

				for(VentaDeMedicamentos vent: ventas)
				{
					if(vent.getNombre().equals(med.getNomComun()))
						repite = true;
				}
				return repite;
			}


			// metodos del segundo reporte

			public long calcularCantidad()
			{
				long cantidad = 0;

				for(NucleoFamiliar n: nucleos)
				{
					if(n.getCompraron() == false)
					{
						cantidad += n.getMujeres().size();
					}
				}

				cantidadDeAlmohadillasSanitarias -= cantidadDeAlmohadillas();

				cantidad -= cantidadDeAlmohadillasSanitarias;

				return cantidad;
			}







			// metodos del tercer reporte
			public ArrayList<Porcentaje> calcularValor()
			{
				double importeTotalDeAlmohadillas = cantDeDineroObtenidoPorAlmohadillas();
				System.out.println("Almohadillas "+importeTotalDeAlmohadillas);
				double importeTotalDeMedPrescripcion = cantDeDineroObtenidoPorMedPrescripcion();
				System.out.println("Pre "+importeTotalDeMedPrescripcion);
				double importeTotalDeMedControlados = cantDeDineroObtenidoPorMedControlados();
				System.out.println("control "+importeTotalDeMedControlados);
				double importeTotalDeMedVentaLibre = cantDeDineroObtenidoPorMedVentaLibre();
				System.out.println("Libre "+importeTotalDeMedVentaLibre);
				double total = 0;
				ArrayList<Porcentaje> resultado =  new ArrayList<Porcentaje>();
				total = calcularTotal();

				if(total != 0)
				{
					resultado = calcularPorcentaje(total, importeTotalDeAlmohadillas,
							importeTotalDeMedPrescripcion, importeTotalDeMedControlados, 
							importeTotalDeMedVentaLibre);
				}

				else
				{
					Porcentaje nulo = new Porcentaje(0, "No hubo ventas en la Farmacia");
					resultado.add(nulo);
				}

				return resultado;
			}




			public ArrayList<Porcentaje> calcularPorcentaje(double total, double importeTotalDeAlmohadillas,
					double importeTotalDeMedPrescripcion, double importeTotalDeMedControlados, 
					double importeTotalDeMedVentaLibre)
					{
				ArrayList<Porcentaje> resultado = new ArrayList<Porcentaje>();

				Porcentaje porcentajeAlmohadillas = new Porcentaje((importeTotalDeAlmohadillas * 100) / total, "Almohadillas");
				resultado.add(porcentajeAlmohadillas);

				Porcentaje porcentajePrescripcion = new Porcentaje((importeTotalDeMedPrescripcion  * 100) / total, "Medicamento por Prescripción");
				resultado.add(porcentajePrescripcion);

				Porcentaje porcentajeControlados = new Porcentaje((importeTotalDeMedControlados * 100) / total, "Medicamento Controlado");
				resultado.add(porcentajeControlados);

				Porcentaje porcentajeVentaLibre = new Porcentaje((importeTotalDeMedVentaLibre * 100) / total, "Medicamento Libre" );
				resultado.add(porcentajeVentaLibre);

				Collections.sort(resultado, ordenado);

				return resultado;
					}


			Comparator<Porcentaje> ordenado = new Comparator<Porcentaje>() 
					{
				@Override
				public int compare(Porcentaje a, Porcentaje b)
				{
					return Double.compare(b.getPorcentaje(), a.getPorcentaje());
				}
					};


					// metodos del cuarto reporte

					public ArrayList<Tarjeton> tarjetonesDesactivados()
					{
						ArrayList<Tarjeton> tarjetonesInactivos = new ArrayList<Tarjeton>();
						for(Tarjeton t: tarjetones)
							if(Validaciones.estaVencido(t.getFechaVencimiento()))
								tarjetonesInactivos.add(t);

						System.out.println("Hay "+tarjetonesInactivos.size()+" inactivos");

						return tarjetonesInactivos;
					}

					//=====================================================================================================================




					public ArrayList<Factura> getFacturas() 
					{
						return facturas;
					}

					public void setFacturas(ArrayList<Factura> facturas) 
					{
						this.facturas = facturas;
					}

					public ArrayList<Medicamento> getMedicamentos() 
					{
						return medicamentos;
					}

					public void setMedicamentos(ArrayList<Medicamento> medicamentos) 
					{
						this.medicamentos = medicamentos;
					}


					public double calcularTotal() 
					{
						double total = 0;
						total += cantDeDineroObtenidoPorAlmohadillas();
						total += cantDeDineroObtenidoPorMedPrescripcion();
						total += cantDeDineroObtenidoPorMedControlados();
						total +=cantDeDineroObtenidoPorMedVentaLibre();
						return total;
					}

					public double cantDeDineroObtenidoPorAlmohadillas()
					{
						double importeTotalDeAlmohadillas = 0;
						for(Venta h: historialVentas)
						{
							if(h instanceof AlmohadillasSanitarias)
							{
								AlmohadillasSanitarias almohadilla = (AlmohadillasSanitarias)h;
								importeTotalDeAlmohadillas += almohadilla.getImporteTotal();
							}
						}
						return importeTotalDeAlmohadillas;
					}

					public double cantDeDineroObtenidoPorMedPrescripcion()
					{
						double importeTotalDeMedPrescripcion = 0;
						for(Venta h: historialVentas)
						{
							if(h instanceof VentaConPrescripcion)
							{
								VentaConPrescripcion prescripcion = (VentaConPrescripcion)h;
								importeTotalDeMedPrescripcion += prescripcion.getImporteTotal();
							}
						}
						return importeTotalDeMedPrescripcion ;
					}

					public double cantDeDineroObtenidoPorMedControlados()
					{
						double importeTotalDeMedControlados = 0;
						for(Venta h: historialVentas)
						{

							if(h instanceof VentaControlada)
							{
								VentaControlada control = (VentaControlada)h;
								importeTotalDeMedControlados += control.getImporteTotal();
							}
						}
						return importeTotalDeMedControlados;
					}

					public double cantDeDineroObtenidoPorMedVentaLibre()
					{
						double importeTotalDeMedVentaLibre = 0;
						for(Venta h: historialVentas)
						{
							if(h instanceof VentaLibre)
							{
								VentaLibre ventLib = (VentaLibre)h;
								importeTotalDeMedVentaLibre += ventLib.getImporteTotal();
								System.out.println("El total fue de " +importeTotalDeMedVentaLibre);
							}
						}
						return importeTotalDeMedVentaLibre;
					}

					public long getCantidadDeAlmohadillasSanitarias()
					{
						return cantidadDeAlmohadillasSanitarias;
					}

					public void setCantidadDeAlmohadillasSanitarias(long cantidadDeAlmohadillasSanitarias)
					{
						this.cantidadDeAlmohadillasSanitarias = cantidadDeAlmohadillasSanitarias;
					}

					public ArrayList<Venta> getHistorialVentas() 
					{
						return historialVentas;
					}

					public void setHistorialVentas(ArrayList<Venta> historialVentas) 
					{
						this.historialVentas = historialVentas;
					}

					public ArrayList<NucleoFamiliar> getNucleos() 
					{
						return nucleos;
					}

					public void setNucleos(ArrayList<NucleoFamiliar> nucleos) 
					{
						this.nucleos = nucleos;
					}

					public ArrayList<Tarjeton> getTarjetones() 
					{
						return tarjetones;
					}

					public void setTarjetones(ArrayList<Tarjeton> tarjetones) 
					{
						this.tarjetones = tarjetones;
					}

					public Comparator<VentaDeMedicamentos> getComparador() 
					{
						return comparador;
					}

					public void setComparador(Comparator<VentaDeMedicamentos> comparador) 
					{
						this.comparador = comparador;
					}


					// Medicamentos Controlados
					public void agregarMedicamentosControlados(String nomComun, String nomCientifico, String presentacion,
							double precio, String tipo, String fortaleza, double tempAlmac,
							long cantExis, LocalDate fechaProd, LocalDate fechaVenc, String codigo 
							, String patologia, long cantAsigMensual, long cantDispensadaMensual)
					{
						Date utilFechaProd = Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant());
						Date utilFechaVenc = Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant());
						MedicamentoControlado m = new MedicamentoControlado(nomComun,nomCientifico, presentacion, precio, tipo, fortaleza, tempAlmac, cantExis, utilFechaProd , utilFechaVenc
								,codigo ,patologia, cantAsigMensual, cantDispensadaMensual);
						registrarMedicamentoControlado(m);
					}




					// Inicializar Medicamentos
					public void agregarMedicamento(String nomComun, String nomCientifico, String presentacion,
							double precio, String tipo, String fortaleza, double tempAlmac,
							long cantExis, LocalDate fechaProd, LocalDate fechaVenc, String codigo)
					{
						Date utilFechaProd = Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant());
						Date utilFechaVenc = Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant());
						Medicamento m = new Medicamento(nomComun,nomCientifico, presentacion, precio, tipo, fortaleza,
								tempAlmac, cantExis, utilFechaProd , utilFechaVenc, codigo);
						medicamentos.add(m);
					}

					public void agregarPaciente(String nombre, String ci, char genero, LocalDate fechaNac, String direccion) 
					{
						Paciente p = new Paciente();
						p.setNombre(nombre);
						p.setCi(ci);
						p.setGenero(genero);
						p.setFechaNacimiento(java.sql.Date.valueOf(fechaNac)); 
						p.setDireccion(direccion);

						registrarPaciente(p);
					}



					public void agregarVentaConPrescripcion(LocalDate date, double importe, LocalDate dateDos, String nombreDelMed,String codigoDelMed, int cantMedVendidos)
					{
						Date fechaVenta = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
						Date fechaDeCompra = Date.from(dateDos.atStartOfDay(ZoneId.systemDefault()).toInstant());
						VentaConPrescripcion m = new VentaConPrescripcion(fechaVenta, importe, fechaDeCompra, nombreDelMed, codigoDelMed, cantMedVendidos);
						historialVentas.add(m);
					}




					public Factura generarFactura(String nombreDelMed, String codigoDelMed, int cantMedVendidos, Date fechaDeLaCompra) 
					{
						Factura facturacion = new Factura(nombreDelMed, codigoDelMed, cantMedVendidos, fechaDeLaCompra);
						return facturacion;
					}

					public void agregarVentaLibre(LocalDate fecha, double importe,String nombreDelMed,String codigoDelMed, int cantMedVendidos)
					{
						Date fechaDeCompra = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
						VentaLibre vl = new VentaLibre(fechaDeCompra, importe, nombreDelMed, codigoDelMed, cantMedVendidos);
						historialVentas.add(vl);
					}



					public void agregarVentaAlmohadillasSanitarias(LocalDate fecha, double precio, int cant)
					{
						Date fechaDeCompra = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
						AlmohadillasSanitarias a = new AlmohadillasSanitarias(precio, cant, fechaDeCompra);
						historialVentas.add(a);
					}



					public void agregarVentaControlada(LocalDate fecha, double importe,String nombreDelMed,String codigoDelMed, int cantMedVendidos)
					{
						Date fechaDeCompra = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
						VentaControlada vc = new VentaControlada(fechaDeCompra, importe, nombreDelMed, codigoDelMed, cantMedVendidos);
						historialVentas.add(vc);
					}


					// Métodos de acceso
					public ArrayList<Paciente> getPacientes() 
					{
						return pacientes;
					}


					public ArrayList<String> getCarnets() 
					{
						return carnets;
					}

					public ArrayList<String> agregarCarnet(String ci)
					{
						carnets.add(ci);
						return carnets;
					}

					public ArrayList<MedicamentoControlado> getMedicamentoControlado()
					{
						return medicamentoControlado;
					}

					public ArrayList<Venta> getVentas()
					{
						return new ArrayList<Venta>(historialVentas);
					}

					// Métodos de registro
					public void registrarVenta(Venta venta) 
					{
						historialVentas.add(venta);
					}

					public void registrarMedicamentoControlado(MedicamentoControlado m)
					{
						medicamentoControlado.add(m);
					}

					public void registrarPaciente(Paciente p)
					{
						pacientes.add(p);
					}

					public void registrarFactura(Factura factura) 
					{
						facturas.add(factura);
					}

					public void registrarTarjeton(Tarjeton tarjeton) 
					{
						tarjetones.add(tarjeton);
					}

					// ====================================================Buscadores============================================================================================================================

					public Paciente obtenerPacientePorNombre(String nombre) 
					{
						for (Paciente p : Farmacia.obtenerInstancia().getPacientes()) 
						{
							if (p.getNombre().equals(nombre)) 
							{
								return p;
							}
						}
						throw new RuntimeException("Paciente no encontrado: " + nombre);
					}

					//================================================================================================================================================================================

					public Medicamento agregarMed(String nomComun, String nomCientifico, String presentacion,
							double precio, String tipo, String fortaleza, double tempAlmac,
							long cantExis, LocalDate fechaProd, LocalDate fechaVenc, String codigo)
					{
						Date utilFechaProd = Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant());
						Date utilFechaVenc = Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant());
						Medicamento m = new Medicamento(nomComun,nomCientifico, presentacion, precio, tipo, fortaleza, tempAlmac, cantExis, utilFechaProd , utilFechaVenc, codigo);
						return m;
					}









					public void agregarNucleoFamiliar(String id, String direccion, ArrayList <Paciente> hombres, 
							ArrayList <Paciente> mujeres, Paciente jefe, boolean compraron)
					{
						NucleoFamiliar m = new NucleoFamiliar( id, direccion, hombres, mujeres, jefe, compraron);
						nucleos.add(m);
					}

					@Override
					public long cantidadDeAlmohadillas() 
					{
						return getCantidadDeAlmohadillasSanitarias();
					}

					public void actualizarInventario()
					{
						for(Medicamento m: medicamentos)
							for(Venta v: historialVentas)
								if(v instanceof VentaConPrescripcion)
									if(m.getNomComun().equals(((VentaConPrescripcion) v).getNombreDelMed()))
									{
										long valorFact = ((VentaConPrescripcion) v).getCantMedVendidos();
										long valorMed = m.getCantExis();
										long valor = valorMed - valorFact;
										m.setCantExis(valor);  
									}

						for(Medicamento m: medicamentos)
							for(Venta v: historialVentas)
								if(v instanceof VentaLibre)
									if(m.getNomComun().equals(((VentaLibre) v).getNombreDelMed()))
									{
										long valorFact = ((VentaLibre) v).getCantMedVendidos();
										long valorMed = m.getCantExis();
										long valor = valorMed - valorFact;
										m.setCantExis(valor);  
									}


						for(MedicamentoControlado mc: medicamentoControlado)
							for(Venta v: historialVentas)
							{
								if(v instanceof VentaControlada)

									if(mc.getNomComun().equals(((VentaControlada) v).getNombreDelMed()))
									{
										long valorFact = ((VentaControlada) v).getCantMedVendidos();
										long valorMed = mc.getCantExis();
										long valor = valorMed - valorFact;
										mc.setCantExis(valor);  
									}

							}
					}

					// Funcionalidad: Actualiza la cantidad de medicamentos despues de una compra, sino se puede realizar la compra saldra un mensaje
					public void actCantMed(Medicamento med, int cant)
					{
						for(Medicamento m: medicamentos)
							if(m.getNomComun().equals(med.getNomComun()))
							{
								long valorMed = m.getCantExis();				
								m.setCantExis(valorMed - cant);  
							}		
					}	 

					//=========================================================Pacientes==================================================================================================================



					public void inicializarPacientes() 
					{
						// Hombres (25)
						Farmacia.obtenerInstancia().agregarPaciente("Alejandro Bermúdez Cervantes", "88041558485", 'M', LocalDate.of(1988, 4, 15), "Calle 23, #45");
						Farmacia.obtenerInstancia().agregarPaciente("Benjamín Delgado Espinoza", "90062012345", 'M', LocalDate.of(1990, 6, 20), "Calle 12, #34");
						Farmacia.obtenerInstancia().agregarPaciente("Carlos Fuentes Gutiérrez", "78031049284", 'M', LocalDate.of(1978, 3, 10), "Calle 34, #56");
						Farmacia.obtenerInstancia().agregarPaciente("Daniel Hernández López", "85071234567", 'M', LocalDate.of(1985, 7, 12), "Calle 45, #78");
						Farmacia.obtenerInstancia().agregarPaciente("Emilio Juárez Méndez", "92081537880", 'M', LocalDate.of(1992, 8, 15), "Calle 56, #90");
						Farmacia.obtenerInstancia().agregarPaciente("Fernando Katz Núñez", "83092123426", 'M', LocalDate.of(1983, 9, 21), "Calle 67, #12");
						Farmacia.obtenerInstancia().agregarPaciente("Gabriel López Ortiz", "81103028921", 'M', LocalDate.of(1989, 10, 30), "Calle 78, #34");
						Farmacia.obtenerInstancia().agregarPaciente("Héctor Méndez Pérez", "77020545628", 'M', LocalDate.of(1977, 2, 5), "Calle 89, #56");
						Farmacia.obtenerInstancia().agregarPaciente("Ignacio Núñez Quintero", "94040820123", 'M', LocalDate.of(1994, 4, 8), "Calle 90, #78");
						Farmacia.obtenerInstancia().agregarPaciente("Javier Ortiz Rojas", "81061156789", 'M', LocalDate.of(1981, 6, 11), "Calle 11, #90");
						Farmacia.obtenerInstancia().agregarPaciente("Kevin Pérez Sánchez", "99121401224", 'M', LocalDate.of(1999, 12, 14), "Calle 22, #13");
						Farmacia.obtenerInstancia().agregarPaciente("Luis Quintero Torres", "75091927840", 'M', LocalDate.of(1987, 10, 17), "Calle 33, #24");
						Farmacia.obtenerInstancia().agregarPaciente("Manuel Rojas Aguilar", "93022012325", 'M', LocalDate.of(1993, 2, 20), "Calle 44, #35");
						Farmacia.obtenerInstancia().agregarPaciente("Nicolás Sánchez Bermúdez", "80032318901", 'M', LocalDate.of(1980, 3, 23), "Calle 55, #46");
						Farmacia.obtenerInstancia().agregarPaciente("Oscar Torres Cervantes", "95062623446", 'M', LocalDate.of(1995, 6, 26), "Calle 66, #57");
						Farmacia.obtenerInstancia().agregarPaciente("Pablo Aguilar Delgado", "85012919062", 'M', LocalDate.of(1985, 1, 29), "Calle 77, #68");
						Farmacia.obtenerInstancia().agregarPaciente("Raúl Bermúdez Espinoza", "74040234567", 'M', LocalDate.of(1974, 4, 2), "Calle 88, #79");
						Farmacia.obtenerInstancia().agregarPaciente("Sergio Cervantes Fuentes", "98070510163", 'M', LocalDate.of(1998, 7, 5), "Calle 99, #80");
						Farmacia.obtenerInstancia().agregarPaciente("Tomás Delgado Gutiérrez", "82080845688", 'M', LocalDate.of(1982, 8, 8), "Calle 10, #91");
						Farmacia.obtenerInstancia().agregarPaciente("Víctor Espinoza Hernández", "91091101224", 'M', LocalDate.of(1991, 9, 11), "Calle 21, #02");
						Farmacia.obtenerInstancia().agregarPaciente("Adrián Fuentes Ibarra", "75011456789", 'M', LocalDate.of(1975, 1, 14), "Calle 32, #13");
						Farmacia.obtenerInstancia().agregarPaciente("Bruno Gutiérrez Juárez", "97021712345", 'M', LocalDate.of(1997, 2, 17), "Calle 43, #24");
						Farmacia.obtenerInstancia().agregarPaciente("Diego Hernández Katz", "87032017820", 'M', LocalDate.of(1987, 3, 20), "Calle 54, #35");
						Farmacia.obtenerInstancia().agregarPaciente("Eduardo Ibarra López", "92042323426", 'M', LocalDate.of(1992, 4, 23), "Calle 65, #46");
						Farmacia.obtenerInstancia().agregarPaciente("Felipe Juárez Méndez", "05020968485", 'M', LocalDate.of(1981, 5, 26), "Calle 76, #57");

						// Mujeres (20)
						Farmacia.obtenerInstancia().agregarPaciente("Adriana Méndez Núñez", "89062934537", 'F', LocalDate.of(1989, 6, 29), "Calle 87, #68");
						Farmacia.obtenerInstancia().agregarPaciente("Beatriz Núñez Ortiz", "94080110153", 'F', LocalDate.of(1994, 8, 1), "Calle 98, #79");
						Farmacia.obtenerInstancia().agregarPaciente("Camila Ortiz Pérez", "85090445678", 'F', LocalDate.of(1985, 9, 4), "Calle 09, #80");
						Farmacia.obtenerInstancia().agregarPaciente("Diana Pérez Quintero", "99100701234", 'F', LocalDate.of(1999, 10, 7), "Calle 20, #91");
						Farmacia.obtenerInstancia().agregarPaciente("Elena Quintero Rojas", "77111056739", 'F', LocalDate.of(1977, 11, 10), "Calle 31, #02");
						Farmacia.obtenerInstancia().agregarPaciente("Fernanda Rojas Sánchez", "93021312335", 'F', LocalDate.of(1993, 2, 13), "Calle 42, #13");
						Farmacia.obtenerInstancia().agregarPaciente("Gabriela Sánchez Torres", "83031617890", 'F', LocalDate.of(1983, 3, 16), "Calle 53, #24");
						Farmacia.obtenerInstancia().agregarPaciente("Helena Torres Aguilar", "97041923456", 'F', LocalDate.of(1997, 4, 19), "Calle 64, #35");
						Farmacia.obtenerInstancia().agregarPaciente("Isabel Aguilar Bermúdez", "87052218911", 'F', LocalDate.of(1987, 5, 22), "Calle 75, #46");
						Farmacia.obtenerInstancia().agregarPaciente("Jimena Bermúdez Cervantes", "92062534517", 'F', LocalDate.of(1992, 6, 25), "Calle 86, #57");
						Farmacia.obtenerInstancia().agregarPaciente("Karina Cervantes Delgado", "82072810113", 'F', LocalDate.of(1982, 7, 28), "Calle 97, #68");
						Farmacia.obtenerInstancia().agregarPaciente("Laura Delgado Espinoza", "98083145678", 'F', LocalDate.of(1998, 8, 31), "Calle 08, #79");
						Farmacia.obtenerInstancia().agregarPaciente("Mariana Espinoza Fuentes", "74090301234", 'F', LocalDate.of(1974, 9, 3), "Calle 19, #80");
						Farmacia.obtenerInstancia().agregarPaciente("Natalia Fuentes Gutiérrez", "95100656719", 'F', LocalDate.of(1995, 10, 6), "Calle 30, #91");
						Farmacia.obtenerInstancia().agregarPaciente("Olivia Gutiérrez Hernández", "85110912315", 'F', LocalDate.of(1985, 11, 9), "Calle 41, #02");
						Farmacia.obtenerInstancia().agregarPaciente("Patricia Hernández Ibarra", "99121217890", 'F', LocalDate.of(1999, 12, 12), "Calle 52, #13");
						Farmacia.obtenerInstancia().agregarPaciente("Raquel Ibarra Juárez", "81031523456", 'F', LocalDate.of(1981, 3, 15), "Calle 63, #24");
						Farmacia.obtenerInstancia().agregarPaciente("Sofía Juárez Katz", "97041818911", 'F', LocalDate.of(1997, 4, 18), "Calle 74, #35");
						Farmacia.obtenerInstancia().agregarPaciente("Tatiana Katz López", "82062114537", 'F', LocalDate.of(1983, 5, 21), "Calle 85, #46");
						Farmacia.obtenerInstancia().agregarPaciente("Valeria López Méndez", "93062410113", 'F', LocalDate.of(1993, 6, 24), "Calle 96, #57");

						System.out.println("Se insertaron " +Farmacia.obtenerInstancia().getPacientes().size()+" pacientes");

					}


					//================================================================================================================================================================================

					//===================================================Medicamentos===============================================================================================================




					public void inicializarMedicamentos() 
					{
						// Medicamento 1
						Farmacia.obtenerInstancia().agregarMedicamento("Paracetamol", "Acetaminofén", "Tabletas", 15.0, "Venta libre", "500 mg", 25.0, 400,
								LocalDate.of(2025, 2, 07), LocalDate.of(2027, 2, 07), "MC-1");

						// Medicamento 2
						Farmacia.obtenerInstancia().agregarMedicamento("Ibuprofeno", "Ácido Ibuprofénico", "Cápsulas", 20.0, "Venta libre", "400 mg", 25.0, 500,
								LocalDate.of(2025, 2, 15), LocalDate.of(2027, 2 ,15),"MC-2");

						// Medicamento 3
						Farmacia.obtenerInstancia().agregarMedicamento("Aspirina", "Ácido Acetilsalicílico", "Tabletas", 10.0, "Venta libre", "100 mg", 25.0, 550,
								LocalDate.of(2025, 3, 10), LocalDate.of(2027, 3, 10),"MC-3");

						// Medicamento 4
						Farmacia.obtenerInstancia().agregarMedicamento("Loratadina", "Antihistamínico", "Jarabe", 12.5, "Venta libre", "10 mg/ml", 20.0, 500,
								LocalDate.of(2025,4,20), LocalDate.of(2027, 4,20),"MC-4");

						// Medicamento 5
						Farmacia.obtenerInstancia().agregarMedicamento("Omeprazol", "Inhibidor de bomba", "Cápsulas", 18.99, "Con prescripción", "20 mg", 20.0, 500,
								LocalDate.of(2025,2,05), LocalDate.of(2027, 5,05),"MC-5");

						// Medicamento 6
						Farmacia.obtenerInstancia().agregarMedicamento("Metformina", "Glifage", "Tabletas", 25.50, "Con prescripción", "500 mg", 20.0, 500,
								LocalDate.of(2025,1,10), LocalDate.of(2027,6,10),"MC-6");

						// Medicamento 7
						Farmacia.obtenerInstancia().agregarMedicamento("Simvastatina", "Estatinas", "Tabletas", 30.0, "Con prescripción", "20 mg", 20.0, 500,
								LocalDate.of(2025, 2,15), LocalDate.of(2027, 7,15),"MC-7");

						// Medicamento 8
						Farmacia.obtenerInstancia().agregarMedicamento("Amoxicilina", "Penicilina", "Cápsulas", 22.75, "Con prescripción", "250 mg", 20.0, 500,
								LocalDate.of(2025, 2,20), LocalDate.of(2027, 8, 20),"MC-8");

						// Medicamento 9
						Farmacia.obtenerInstancia().agregarMedicamento("Atorvastatina", "Lipitor", "Cápsulas", 35.0, "Con prescripción", "40 mg", 20.0, 500,
								LocalDate.of(2025, 1,25), LocalDate.of(2027,9, 25),"MC-9");

						// Medicamento 10
						Farmacia.obtenerInstancia().agregarMedicamento("Dipirona", "Metamizol", "Tabletas", 13.0, "Venta libre", "500 mg", 25.0, 500,
								LocalDate.of(2025, 3,01), LocalDate.of(2027,10,01),"MC-10");

						// Medicamento 11
						Farmacia.obtenerInstancia().agregarMedicamento("Levotiroxina", "T", "Tabletas", 28.99, "Con prescripción", "50 mcg", 20.0, 300,
								LocalDate.of(2025, 2, 05), LocalDate.of(2027, 11, 05 ),"MC-11");

						// Medicamento 12
						Farmacia.obtenerInstancia().agregarMedicamento("Diclofenaco", "Antiinflamatorio", "Gel", 15.0, "Con prescripción", "100mg", 25.0, 300,
								LocalDate.of(2025, 2, 10), LocalDate.of(2027,12,10),"MC-12");

						// Medicamento 13
						Farmacia.obtenerInstancia().agregarMedicamento("Naproxeno", "Antiinflamatorio", "Tabletas", 10.5, "Con prescripción", "250 mg", 25.0, 500,
								LocalDate.of(2025, 1,15), LocalDate.of(2027, 4,15),"MC-13"); 

						// Medicamento 14
						Farmacia.obtenerInstancia().agregarMedicamento("Salbutamol", "Ventolín", "Inhalador", 18.99, "Con prescripción", "100 mcg", 15.0, 500,
								LocalDate.of(2025, 2, 20), LocalDate.of(2027, 5, 20),"MC-14");

						System.out.println("Medicamentos: " + Farmacia.obtenerInstancia().getMedicamentos().size());
					}

					// ==================================================Medicamentos Controlados==========================================================================================================

					public void inicializarMedicamentosControlados()
					{
						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Tramadol",
								"Tramadol Clorhidrato",
								"Cápsula",
								45.0,
								"Medicamento controlado",
								"50 mg",
								25.0,
								300,
								LocalDate.of(2025, 2, 1),
								LocalDate.of(2027, 2, 1),
								"MC-15",
								"Dolor moderado a severo",
								120,
								110
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Buprenorfina",
								"Buprenorfina",
								"Tableta sublingual",
								90.0,
								"Medicamento controlado",
								"2 mg",
								20.0,
								150,
								LocalDate.of(2025, 3, 10),
								LocalDate.of(2027, 3, 10),
								"MC-16",
								"Tratamiento de adicción a opioides",
								80,
								75
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Alprazolam",
								"Alprazolam",
								"Tableta",
								35.0,
								"Medicamento controlado",
								"1 mg",
								25.0,
								200,
								LocalDate.of(2025, 4, 15),
								LocalDate.of(2027, 4, 15),
								"MC-16",
								"Trastorno de ansiedad generalizada",
								90,
								85
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Clonazepam",
								"Clonazepam",
								"Tableta",
								40.0,
								"Medicamento controlado",
								"2 mg",
								25.0,
								180,
								LocalDate.of(2025, 3, 20),
								LocalDate.of(2027, 3, 20),
								"MC-17",
								"Epilepsia y trastornos de pánico",
								70,
								65
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Ritalina",
								"Metilfenidato",
								"Tableta",
								85.0,
								"Medicamento controlado",
								"10 mg",
								25.0,
								250,
								LocalDate.of(2025, 2, 25),
								LocalDate.of(2027, 2, 25),
								"MC-18",
								"TDAH",
								100,
								95
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Midazolam",
								"Benzodiacepina",
								"Inyección",
								110.0,
								"Medicamento controlado",
								"1 mg/ml",
								15.0,
								100,
								LocalDate.of(2025, 1, 30),
								LocalDate.of(2027, 1, 30),
								"MC-19",
								"Sedación preoperatoria",
								40,
								35
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Meperidina",
								"Pethidina",
								"Ampolla inyectable",
								60.0,
								"Medicamento controlado",
								"50 mg/ml",
								15.0,
								80,
								LocalDate.of(2025, 2, 10),
								LocalDate.of(2027, 2, 10),
								"MC-20",
								"Dolor intenso agudo",
								30,
								28
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Propoxifeno",
								"Propoxifeno Napsilato",
								"Cápsula",
								30.0,
								"Medicamento controlado",
								"100 mg",
								25.0,
								350,
								LocalDate.of(2025, 4, 5),
								LocalDate.of(2027, 4, 5),
								"MC-21",
								"Dolor leve a moderado crónico",
								110,
								105
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Ketamina",
								"Cyclohexanone",
								"Inyección",
								95.0,
								"Medicamento controlado",
								"50 mg/ml",
								10.0,
								60,
								LocalDate.of(2025, 3, 1),
								LocalDate.of(2027, 3, 1),
								"MC-22",
								"Anestésico y dolor refractario",
								25,
								20
								);

						Farmacia.obtenerInstancia().agregarMedicamentosControlados(
								"Barbitona",
								"Fenobarbital",
								"Tableta",
								20.0,
								"Medicamento controlado",
								"30 mg",
								25.0,
								400,
								LocalDate.of(2025, 3, 12),
								LocalDate.of(2027, 3, 12),
								"MC-23",
								"Convulsiones y sedación",
								130,
								120
								);
						System.out.println("Medicamentos Controlados: " +Farmacia.obtenerInstancia().getMedicamentoControlado().size());
					} 


					//===================================================================================================================================================================================

					//===========================================================Ventas==================================================================================================================



					public void inicializarVentaConPrescripcion() 
					{
						// Ventas con prescripción - 40 ejemplos
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 3, 1), 25.0, LocalDate.of(2025, 1, 1), "Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 2), 30.0, LocalDate.of(2025, 1, 1),"Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 3), 28.5, LocalDate.of(2025, 1, 2),"Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 4), 32.75, LocalDate.of(2025, 1, 2),"Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 5), 40.0, LocalDate.of(2025, 1, 3), "Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 6), 22.0, LocalDate.of(2025, 1, 4), "Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 7), 35.0, LocalDate.of(2025, 1, 4), "Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 8), 45.0, LocalDate.of(2025, 1, 5), "Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 9), 30.0, LocalDate.of(2025, 1, 5), "Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 10), 28.0, LocalDate.of(2025, 1, 6), "Omeprazol", "Inhibidor de bomba", 2);

						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 11), 32.0, LocalDate.of(2025, 1, 6),"Omeprazol", "Inhibidor de bomba", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 12), 37.0, LocalDate.of(2025, 1, 7), "Omeprazol", "Inhibidor de bomba", 3);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 13), 41.0, LocalDate.of(2025, 1, 7), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 14), 29.0, LocalDate.of(2025, 1, 8), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 15), 33.0, LocalDate.of(2025, 1, 8), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 16), 36.0, LocalDate.of(2025, 1, 9), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 17), 42.0, LocalDate.of(2025, 1, 9), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 18), 26.0, LocalDate.of(2025, 1, 10), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 19), 34.0, LocalDate.of(2025, 1, 10), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 20), 39.0, LocalDate.of(2025, 1, 11), "Metformina", "Glifage", 2);

						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 21), 27.0, LocalDate.of(2025, 1, 11), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 22), 31.0, LocalDate.of(2025, 1, 12), "Metformina", "Glifage", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 23), 38.0, LocalDate.of(2025, 1, 12), "Simvastatina", "Estatinas", 4);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 24), 43.0, LocalDate.of(2025, 1, 13), "Simvastatina", "Estatinas", 4);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 25), 33.0, LocalDate.of(2025, 1, 13), "Simvastatina", "Estatinas", 4);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 26), 29.0, LocalDate.of(2025, 1, 14), "Simvastatina", "Estatinas", 4);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 27), 35.0, LocalDate.of(2025, 1, 14), "Amoxilina", "Penicilina", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 28), 40.0, LocalDate.of(2025, 1, 15), "Amoxilina", "Penicilina", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 29), 31.0, LocalDate.of(2025, 1, 15), "Amoxilina", "Penicilina", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 30), 36.0, LocalDate.of(2025, 1, 16), "Amoxilina", "Penicilina", 2);

						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 31), 44.0, LocalDate.of(2025, 1, 16), "Amoxilina", "Penicilina", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 1), 25.0, LocalDate.of(2025, 1, 17), "Atorvastatina", "Lipitor", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 2), 30.0, LocalDate.of(2025, 1, 17), "Atorvastatina", "Lipitor", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 3), 28.0, LocalDate.of(2025, 1, 18), "Atorvastatina", "Lipitor", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 4), 32.0, LocalDate.of(2025, 1, 18), "Atorvastatina", "Lipitor", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 5), 37.0, LocalDate.of(2025, 1, 19), "Dipirona", "Metamizol", 4);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 6), 42.0, LocalDate.of(2025, 1, 19), "Naproxeno", "Antiinflamatorio", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 7), 34.0, LocalDate.of(2025, 1, 20), "Dipirona", "Metamizol", 4);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 8), 39.0, LocalDate.of(2025, 1, 20), "Naproxeno", "Antiinflamatorio", 2);
						Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 9), 27.0, LocalDate.of(2025, 1, 21), "Naproxeno", "Antiinflamatorio", 2);
					}


					public void inicializarVentaLibre()
					{
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 3, 1), 20.0, "Paracetamol", "Acetaminofén", 1);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 4, 2), 15.0, "Paracetamol", "Acetaminofén", 8 );
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 3), 18.5, "Paracetamol", "Acetaminofén", 7);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 4), 22.0, "Paracetamol", "Acetaminofén", 6);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 5), 10.0, "Paracetamol", "Acetaminofén", 5);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 6), 17.0, "Ibuprofeno", "Ácido Ibuprofénico", 1);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 7), 25.0, "Ibuprofeno", "Ácido Ibuprofénico", 4);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 8), 12.0, "Ibuprofeno", "Ácido Ibuprofénico", 2);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 9), 14.5, "Ibuprofeno", "Ácido Ibuprofénico", 5);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 10), 19.0, "Ibuprofeno", "Ácido Ibuprofénico", 10);

						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 11), 21.0, "Ibuprofeno", "Ácido Ibuprofénico", 2);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 12), 16.0,"Ibuprofeno", "Ácido Ibuprofénico", 2);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 13), 23.0,"Ibuprofeno", "Ácido Ibuprofénico", 2);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 14), 11.0, "Ibuprofeno", "Ácido Ibuprofénico", 2);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 15), 13.5, "Aspirina", "Ácido Acetilsalicílico", 3);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 16), 18.0, "Aspirina", "Ácido Acetilsalicílico", 3);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 17), 24.0, "Aspirina", "Ácido Acetilsalicílico", 3);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 18), 15.0, "Aspirina", "Ácido Acetilsalicílico", 5);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 19), 19.0, "Aspirina", "Ácido Acetilsalicílico", 5);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 20), 20.0, "Loratadina", "Antihistamínico", 10);

						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 21), 22.0, "Loratadina", "Antihistamínico", 10);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 22), 17.0, "Loratadina", "Antihistamínico", 10);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 23), 14.0, "Loratadina", "Antihistamínico", 10);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 24), 18.0, "Loratadina", "Antihistamínico", 10);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 25), 23.0, "Loratadina", "Antihistamínico", 10);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 26), 20.0, "Loratadina", "Antihistamínico", 7);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 27), 15.0, "Loratadina", "Antihistamínico", 9);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 28), 19.0, "Loratadina", "Antihistamínico", 8);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 29), 16.0, "Loratadina", "Antihistamínico", 7);
						Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 30), 21.0, "Loratadina", "Antihistamínico", 5);

					}


					public void inicializarVentaControlada()
					{
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 6, 1), 50.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 2), 60.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 3), 55.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 4), 65.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 5), 70.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 6), 48.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 7), 52.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 8), 58.0, "Tramadol","Tramadol Clorhidrato", 3);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 9), 63.0, "Alprazolam", "Alprazolam", 2);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 10), 54.0, "Alprazolam", "Alprazolam", 2) ;

						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 11), 56.0, "Alprazolam", "Alprazolam", 2);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 12), 59.0, "Alprazolam", "Alprazolam", 2);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 13), 62.0, "Alprazolam", "Alprazolam", 2);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 14), 67.0, "Alprazolam", "Alprazolam", 2);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 15), 61.0, "Alprazolam", "Alprazolam", 2);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 16), 53.0, "Clonazepam", "Clonazepam", 4);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 17), 57.0,  "Clonazepam", "Clonazepam", 4);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 18), 64.0, "Clonazepam", "Clonazepam", 4);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 19), 68.0, "Clonazepam", "Clonazepam", 4);
						Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 20), 69.0, "Ritalina", "Metilfenidato", 3);	
					}


					public void inicializarAlmohadillas() 
					{
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 5, 1), 8.5, 5);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 2), 8.5, 6);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 3), 8.5, 4);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 4), 8.5, 5);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 5), 8.5, 3);

						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 6), 8.5, 6);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 7), 8.5, 5);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 8), 8.5, 4);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 9), 8.5, 5);
						Farmacia.obtenerInstancia().agregarVentaAlmohadillasSanitarias(LocalDate.of(2025, 1, 10), 8.5, 6);
					}


					//==========================================================================================================================================================


					//===========================================Tarjetones====================================================================================================

					public void inicializarTarjetones() 
					{
						// Simulamos una fecha fija de hoy para pruebas: 1 de Julio de 2025
						LocalDate hoy = LocalDate.of(2025, 7, 1);

						for (int i = 0; i < 15; i++) {
							Paciente p = Farmacia.obtenerInstancia().getPacientes().get(i);
							int numTarjetones = 1 + (i % 3);

							for (int j = 0; j < numTarjetones && j < 3; j++) {

								// Decidimos si será vencido (1 de cada 5)
								boolean estaVencido = (j % 5 == 0); // 1 de cada 5

								LocalDate fechaExp = null;
								LocalDate fechaVenc = null;

								if (estaVencido) {
									// Tarjetón vencido: expiró antes de hoy
									// Generamos un vencimiento entre 1 y 6 meses antes de hoy
									int mesesAtras = 1 + (int)(Math.random() * 6); // entre 1 y 6 meses atrás
									fechaVenc = hoy.minusMonths(mesesAtras);

									// Expedición debe ser antes del vencimiento (ej: 1 o 2 meses antes)
									int mesesAntesDeVencer = 1 + (int)(Math.random() * 2);
									fechaExp = fechaVenc.minusMonths(mesesAntesDeVencer);

								} else {
									// Tarjetón vigente: vence después de hoy
									int mesesFuturo = 3 + (int)(Math.random() * 10); // entre 3 y 12 meses
									fechaVenc = hoy.plusMonths(mesesFuturo);

									// Expedición 2 meses antes del vencimiento
									fechaExp = fechaVenc.minusMonths(2);
								}

								// Asegurarse de que la fecha de expedición no esté en el futuro
								if (fechaExp.isAfter(hoy)) {
									fechaExp = hoy; // Si por error se genera en el futuro, ajustamos a hoy
								}

								// Seleccionamos algunos medicamentos controlados
								int numMedicamentos = 1 + (j % 3);
								ArrayList<MedicamentoControlado> medsControlados = new ArrayList<>();

								for (int k = 0; k < numMedicamentos && k < Farmacia.obtenerInstancia().getMedicamentoControlado().size(); k++) {
									medsControlados.add((MedicamentoControlado) Farmacia.obtenerInstancia().getMedicamentoControlado().get(k));
								}

								// Añadimos el tarjetón solo si el paciente tiene espacio
								if (p.obtenerTarjetones().size() < 3) {
									Tarjeton t = new Tarjeton(
											p.getNombre(),
											p.getDireccion(),
											java.sql.Date.valueOf(fechaExp),
											java.sql.Date.valueOf(fechaVenc),
											medsControlados
											);
									Farmacia.obtenerInstancia().getTarjetones().add(t);
									p.agregarTarjeton(t);
								}
							}
						}


						Tarjeton tVencido = new Tarjeton(
								"Beatriz Nuñez Ortiz",
								"23A / 244 Y 250",
								java.sql.Date.valueOf(LocalDate.of(2024, 1, 1)),
								java.sql.Date.valueOf(LocalDate.of(2024, 6, 30)),
								new ArrayList<>(Farmacia.obtenerInstancia().getMedicamentoControlado())
								);
						Farmacia.obtenerInstancia().getTarjetones().add(tVencido);

						Tarjeton tVencidoDos = new Tarjeton(
								"Gabriela Sánchez Torres",
								"Calle Victoria 244",
								java.sql.Date.valueOf(LocalDate.of(2024, 1, 1)),
								java.sql.Date.valueOf(LocalDate.of(2024, 6, 30)),
								new ArrayList<>(Farmacia.obtenerInstancia().getMedicamentoControlado())
								);
						Farmacia.obtenerInstancia().getTarjetones().add(tVencidoDos);

						Tarjeton tVencidoTres = new Tarjeton(
								"Helena Torres Aguilar",
								"23B/244254",
								java.sql.Date.valueOf(LocalDate.of(2024, 1, 1)),
								java.sql.Date.valueOf(LocalDate.of(2024, 6, 30)),
								new ArrayList<>(Farmacia.obtenerInstancia().getMedicamentoControlado())
								);
						Farmacia.obtenerInstancia().getTarjetones().add(tVencidoTres);

						System.out.println("Se agregaron los tarjetones: " + Farmacia.obtenerInstancia().getTarjetones().size());
					}




					public void generarFacturasDesdeVentas() 
					{
						for (Venta venta : Farmacia.obtenerInstancia().getHistorialVentas()) 
						{
							String nombreMedicamento = "";
							String codigoMedicamento = "";
							int cantidadVendida = 0;
							Date fechaCompra = venta.getFechaVenta();

							if (venta instanceof VentaLibre) 
							{
								nombreMedicamento = ((VentaLibre) venta).getNombreDelMed();
								codigoMedicamento = ((VentaLibre) venta).getCodigoDelMed();
								cantidadVendida = ((VentaLibre) venta).getCantMedVendidos(); 
								Farmacia.obtenerInstancia().getFacturas().add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
							} 

							else if (venta instanceof VentaConPrescripcion) 
							{

								nombreMedicamento = ((VentaConPrescripcion) venta).getNombreDelMed(); 
								codigoMedicamento = ((VentaConPrescripcion) venta).getCodigoDelMed();
								cantidadVendida = ((VentaConPrescripcion) venta).getCantMedVendidos();
								Farmacia.obtenerInstancia().getFacturas().add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
							} 

							else if (venta instanceof VentaControlada) 
							{
								nombreMedicamento = ((VentaControlada) venta).getNombreDelMed(); 
								codigoMedicamento = ((VentaControlada) venta).getCodigoDelMed();
								cantidadVendida = ((VentaControlada) venta).getCantMedVendidos();
								Farmacia.obtenerInstancia().getFacturas().add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
							} 

							else if (venta instanceof AlmohadillasSanitarias) 
							{
								AlmohadillasSanitarias alm = (AlmohadillasSanitarias) venta;
								nombreMedicamento = "Almohadillas Sanitarias";
								codigoMedicamento = "ALM-001";
								cantidadVendida = alm.getCant();
								Farmacia.obtenerInstancia().getFacturas().add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
							}
						}
						System.out.println("Se generaron " + Farmacia.obtenerInstancia().getFacturas().size() + " facturas desde el historial de ventas.");
					}

					// ====================================================Nucleos==================================================================================================
					public void inicializarNucleosFamiliares() 
					{

						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-001", 
								"Calle 23, #45",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Daniel Hernández López"),
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Luis Quintero Torres")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Adriana Méndez Núñez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Luis Quintero Torres"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-002", 
								"Calle 12, #34",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Héctor Méndez Pérez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Beatriz Núñez Ortiz"),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Elena Quintero Rojas")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Héctor Méndez Pérez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-003", 
								"Calle 34, #56",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Raúl Bermúdez Espinoza")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Camila Ortiz Pérez"),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Isabel Aguilar Bermúdez")
												)),

												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Raúl Bermúdez Espinoza"),
												false
								);



						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-004", 
								"Calle 45, #78",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Bruno Gutiérrez Juárez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diana Pérez Quintero")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diana Pérez Quintero"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-005", 
								"Calle 56, #90",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diego Hernández Katz")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Gabriela Sánchez Torres")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diego Hernández Katz"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-006", 
								"Calle 67, #12",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Felipe Juárez Méndez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Karina Cervantes Delgado")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Felipe Juárez Méndez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-007", 
								"Calle 78, #34",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Javier Ortiz Rojas")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Laura Delgado Espinoza")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Javier Ortiz Rojas"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-008", 
								"Calle 89, #56",
								new ArrayList<>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Kevin Pérez Sánchez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Mariana Espinoza Fuentes")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Mariana Espinoza Fuentes"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(
								"NUC-009", 
								"Calle 90, #78",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Manuel Rojas Aguilar")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Natalia Fuentes Gutiérrez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Manuel Rojas Aguilar"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-010", 
								"Calle 11, #90",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Javier Ortiz Rojas")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Olivia Gutiérrez Hernández")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Olivia Gutiérrez Hernández"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-011", 
								"Calle 22, #13",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Oscar Torres Cervantes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Patricia Hernández Ibarra")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Patricia Hernández Ibarra"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-012", 
								"Calle 33, #24",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Pablo Aguilar Delgado")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Raquel Ibarra Juárez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Pablo Aguilar Delgado"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-013", 
								"Calle 44, #35",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Raúl Bermúdez Espinoza")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sofía Juárez Katz")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Raúl Bermúdez Espinoza"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-014", 
								"Calle 55, #46",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sergio Cervantes Fuentes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Tatiana Katz López")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sergio Cervantes Fuentes"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-015", 
								"Calle 66, #57",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Tomás Delgado Gutiérrez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Valeria López Méndez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Tomás Delgado Gutiérrez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-016", 
								"Calle 77, #68",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Víctor Espinoza Hernández")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Jimena Bermúdez Cervantes")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Víctor Espinoza Hernández"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-017", 
								"Calle 88, #79",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Adrián Fuentes Ibarra")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Helena Torres Aguilar")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Adrián Fuentes Ibarra"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-018", 
								"Calle 99, #80",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Bruno Gutiérrez Juárez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sofía Juárez Katz")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Bruno Gutiérrez Juárez"),
												false
								);

						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-019", 
								"Calle 10, #91",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Eduardo Ibarra López")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Beatriz Núñez Ortiz")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Eduardo Ibarra López"),
												false
								);

						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-020", 
								"Calle 21, #02",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Fernando Katz Núñez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Tatiana Katz López")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Fernando Katz Núñez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-021", 
								"Calle 32, #13",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diego Hernández Katz")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Elena Quintero Rojas")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diego Hernández Katz"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-022", 
								"Calle 43, #24",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sergio Cervantes Fuentes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Fernanda Rojas Sánchez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sergio Cervantes Fuentes"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-023", 
								"Calle 54, #35",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Luis Quintero Torres")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Helena Torres Aguilar")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Luis Quintero Torres"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-024", 
								"Calle 65, #46",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Carlos Fuentes Gutiérrez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sofía Juárez Katz")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Carlos Fuentes Gutiérrez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-025", 
								"Calle 76, #57",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Alejandro Bermúdez Cervantes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Mariana Espinoza Fuentes")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Alejandro Bermúdez Cervantes"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-026", 
								"Calle 87, #68",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Gabriel López Ortiz")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Laura Delgado Espinoza")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Gabriel López Ortiz"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-027", 
								"Calle 98, #79",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Javier Ortiz Rojas")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Camila Ortiz Pérez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Javier Ortiz Rojas"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-028", 
								"Calle 09, #80",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Benjamín Delgado Espinoza")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Isabel Aguilar Bermúdez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Isabel Aguilar Bermúdez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-029", 
								"Calle 20, #91",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Daniel Hernández López")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diana Pérez Quintero")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Diana Pérez Quintero"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-030", 
								"Calle 31, #02",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Héctor Méndez Pérez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Elena Quintero Rojas")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Elena Quintero Rojas"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-031", 
								"Calle 42, #13",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Kevin Pérez Sánchez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Fernanda Rojas Sánchez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Fernanda Rojas Sánchez"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-032", 
								"Calle 53, #24",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Oscar Torres Cervantes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Gabriela Sánchez Torres")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Gabriela Sánchez Torres"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-033", 
								"Calle 64, #35",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Manuel Rojas Aguilar")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Helena Torres Aguilar")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Helena Torres Aguilar"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-034", 
								"Calle 75, #46",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Sergio Cervantes Fuentes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Isabel Aguilar Bermúdez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Isabel Aguilar Bermúdez"),
												false
								);

						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-035", 
								"Calle 86, #57",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Ignacio Núñez Quintero")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Jimena Bermúdez Cervantes")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Jimena Bermúdez Cervantes"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-036", 
								"Calle 97, #68",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Alejandro Bermúdez Cervantes")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Karina Cervantes Delgado")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Karina Cervantes Delgado"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-037", 
								"Calle 08, #79",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Pablo Aguilar Delgado")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Laura Delgado Espinoza")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Laura Delgado Espinoza"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-038", 
								"Calle 19, #80",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Carlos Fuentes Gutiérrez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Mariana Espinoza Fuentes")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Mariana Espinoza Fuentes"),
												false
								);


						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-039", 
								"Calle 30, #91",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Javier Ortiz Rojas")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Natalia Fuentes Gutiérrez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Natalia Fuentes Gutiérrez"),
												false
								);

						Farmacia.obtenerInstancia().agregarNucleoFamiliar(

								"NUC-040", 
								"Calle 96, #57",
								new ArrayList<Paciente>(Arrays.asList(
										Farmacia.obtenerInstancia().obtenerPacientePorNombre("Tomás Delgado Gutiérrez")
										)),
										new ArrayList<Paciente>(Arrays.asList(
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Valeria López Méndez")
												)),
												Farmacia.obtenerInstancia().obtenerPacientePorNombre("Valeria López Méndez"),
												false
								);
						System.out.println("Total de Núcleos: " + Farmacia.obtenerInstancia().getNucleos().size());
					}

					//===================================================================================================================================================================================			


					//-------------------------------------------Listados para ComboBox-------------------------------------------------------------------

					// Método para obtener la lista de pacientes
					public List<String> obtenerListaPacientes() {
						List<String> listaPacientes = new ArrayList<>();
						for (Paciente paciente : pacientes) {
							listaPacientes.add(paciente.getNombre());
						}
						return listaPacientes;
					}

					public List<String> obtenerListaPacientesControlados() {
					    List<String> listaControlados = new ArrayList<>();
					    for (Paciente paciente : pacientes) {
					        if (paciente.esControlado()) { // Ajusta este método según tu clase Paciente
					            listaControlados.add(paciente.getNombre());
					        }
					    }
					    return listaControlados;
					}
					
					// Método para obtener la lista de medicamentos
					public List<String> obtenerListaMedicamentos() {
						List<String> listaMedicamentos = new ArrayList<>();
						for (Medicamento medicamento : medicamentos) {
							listaMedicamentos.add(medicamento.getNomComun());
						}
						return listaMedicamentos;
					}

					//Método para obtener la lista de medicamentos de venta libre 
					public List<Medicamento> obtenerMedicamentosVentaLibre() {
						List<Medicamento> medicamentosVentaLibre = new ArrayList<>();
						for (Medicamento medicamento : medicamentos) {
							if ("Venta libre".equalsIgnoreCase(medicamento.getTipo())) {
								medicamentosVentaLibre.add(medicamento);
							}
						}
						return medicamentosVentaLibre;
					}

					// Método para obtener la lista de medicamentos controlados
					public List<String> obtenerListaMedicamentosControlados() {
						List<String> listaMedControlados = new ArrayList<>();
						for (MedicamentoControlado medicamento : medicamentoControlado) {
							listaMedControlados.add(medicamento.getNomComun());
						}
						return listaMedControlados;
					}

					// Método para obtener la lista de tarjetones
					public List<String> obtenerListaTarjetones() {
						List<String> listaTarjetones = new ArrayList<>();
						for (Tarjeton tarjeton : tarjetones) {
							listaTarjetones.add(tarjeton.getNombre());
						}
						return listaTarjetones;
					}

					// Método para obtener la lista de recetas
					public List<String> obtenerListaRecetas() {
						List<String> listaRecetas = new ArrayList<>();
						for (Venta venta : historialVentas) {
							if (venta instanceof VentaConPrescripcion) {
								VentaConPrescripcion receta = (VentaConPrescripcion) venta;
								listaRecetas.add(receta.getNombreDelMed());
							}
						}
						return listaRecetas;
					}
					
					// Método para obtener la lista de mujeres
					public List<String> obtenerListaMujeres() {
						List<String> listaMujeres = new ArrayList<>();
						for (Paciente paciente : pacientes) {
							if (paciente.getGenero() == 'F') { // Filtrar por género femenino
								listaMujeres.add(paciente.getNombre());
							}
						}
						return listaMujeres;
					}
<<<<<<< HEAD

					public double obtenerPrecioMedicamento(String nombreMedicamento) {
					    // Validación básica
					    if (nombreMedicamento == null || nombreMedicamento.trim().isEmpty()) {
					        throw new IllegalArgumentException("El nombre del medicamento no puede ser nulo o vacío.");
					    }

					    String nombreBuscado = nombreMedicamento.trim();

					    for (Medicamento medicamento : medicamentos) {
					        if (medicamento.getNomComun() != null 
					                && medicamento.getNomComun().equalsIgnoreCase(nombreBuscado)) {

					            // Verificar si es controlado
					            if (medicamento.esControlado()) {
					                return medicamento.getPrecio(); // Es válido: es controlado
					            } else {
					                throw new IllegalArgumentException("El medicamento '" + nombreBuscado + "' NO es un medicamento controlado.");
					            }
					        }
					    }

					    // Si no se encontró el medicamento
					    throw new IllegalArgumentException("Medicamento no encontrado: " + nombreBuscado);
=======
					
					public double obtenerPrecioMedicamento(String nombreMedicamento) 
					{
						for (Medicamento medicamento : medicamentos) {
							if (medicamento.getNomComun().equalsIgnoreCase(nombreMedicamento)) {
								return medicamento.getPrecio();
							}
						}
						throw new RuntimeException("Medicamento no encontrado: " + nombreMedicamento);
>>>>>>> 7a363c56d1196bc1e01ba03a9e9ebeec93838d38
					}

					public ArrayList<Medicamento> filtroLetrasParaMed()
					{
						ArrayList<Medicamento> med = new ArrayList<Medicamento>();

						for(Medicamento m: medicamentos)
							med.add(m);
						for(MedicamentoControlado mc: medicamentoControlado)
							med.add(mc);

						Collections.sort(med, new Comparator<Medicamento>() 
								{
							@Override
							public int compare(Medicamento m1, Medicamento m2) 
							{
								return m1.getNomComun().compareTo(m2.getNomComun());
							}
								});

						return med;	
					}

					public ArrayList<Medicamento> mostrarTodosLosMedicamentos()
					{
						ArrayList<Medicamento> med = new ArrayList<Medicamento>();

						for(Medicamento m: medicamentos)
							med.add(m);
						for(MedicamentoControlado mc: medicamentoControlado)
							med.add(mc);
						return med;
					}

					public ArrayList<Medicamento> filtroPrecioParaMed()
					{
						ArrayList<Medicamento> med = new ArrayList<Medicamento>();

						for(Medicamento m: medicamentos)
							med.add(m);
						for(MedicamentoControlado mc: medicamentoControlado)
							med.add(mc);

						Collections.sort(med, new Comparator<Medicamento>() 
								{
							@Override
							public int compare(Medicamento m1, Medicamento m2) 
							{
								Double primerValor = m1.getPrecio();
								Double segundoValor = m2.getPrecio();
								return primerValor.compareTo(segundoValor);
								
							}
								});

						return med;	
					}
					
					public ArrayList<Medicamento> desorganizarPorLimites() 
					{
					    ArrayList<Medicamento> med = new ArrayList<Medicamento>();
					    
					    // Guardo todos los datos en la lista
					    
						for(Medicamento m: medicamentos)
							med.add(m);
						for(MedicamentoControlado mc: medicamentoControlado)
							med.add(mc);
						
					    int n = med.size();
					    
					    for (int i = 0; i < n / 2; i++) 
					    {
					        int j = n - 1 - i;

					        // Se Intercambia la posición de i con la posición j
					        
					        Medicamento temp = med.get(i);
					        med.set(i, med.get(j));
					        med.set(j, temp);
					    }

					    return med;
					}

			
//==========================================================================================================================================
				    // Método auxiliar para buscar un núcleo familiar por su ID
				    public NucleoFamiliar buscarNucleoPorId(String idNucleo) 
				    {
				        for (NucleoFamiliar nucleo : nucleos) 
				        {
				            if (nucleo.getId().equals(idNucleo)) 
				            {
				                return nucleo;
				            }
				        }
				        return null;
				    }
}




