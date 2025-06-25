package Logica;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

import Interfaces_Enum.Presentacion;
import Logica.*;
import LogicaUtiles.Factura;
import LogicaUtiles.Porcentaje;
import LogicaUtiles.Factura;
import Logica.Medicamento;
import LogicaUtiles.VentaDeMedicamentos;
import Interfaces_Enum.Facturar;
import Interfaces_Enum.GestionarStockAlmohadillasSanitarias;
import Interfaces_Enum.Reportes;

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



	// Constructor PRIVADO
	private Farmacia() 
	{
		// Inicializar todas las listas
		this.medicamentos = new ArrayList<Medicamento>();
		this.historialVentas = new ArrayList<Venta>();
		this.nucleos = new ArrayList<NucleoFamiliar>();
		this.tarjetones = new ArrayList<Tarjeton>();
		this.facturas = new ArrayList<Factura>();
		this.cantidadDeAlmohadillasSanitarias = 0;
		this.pacientes = new ArrayList<Paciente>();
		this.medicamentoControlado = new ArrayList<MedicamentoControlado>();
	}

	// Método para obtener la instancia
	public static Farmacia obtenerInstancia() 
	{
		if (instancia == null) {
			instancia = new Farmacia();
		}
		return instancia;
	}

	public void registrarMedicamento()
	{
		//implementar
	}

	public void procesarVenta()
	{
		//implementar
	}

	public void generarReporte()
	{
		//implementar
	}

	// Primer reporte
	public ArrayList<VentaDeMedicamentos> medicamentosMasVendidos()
	{
		ArrayList<VentaDeMedicamentos> medMasVendidos = buscarOrdenDeAccion();
		return medMasVendidos;
	}

	// Segundo reporte
	public long cantDeAlmohadillasNecesarias()
	{
		long cantidad = calcularCantidad();
		return cantidad;

	}

	// Tercer Reporte

	public Porcentaje comparacionDeVentasMensuales()
	{
		Porcentaje resultado = calcularValor();
		return resultado;
	}

	// Cuarto Reporte
	public ArrayList<Tarjeton> registroDeIncumplimiento()
	{
		ArrayList<Tarjeton> tarjetonesNoActivos = tarjetonesDesactivados();
		return tarjetonesNoActivos;
	}

	public Factura generarFactura(String nombreDelMed, String codigoDelMed, int cantMedVendidos, Date fechaDeLaCompra) 
	{
		Factura facturacion = new Factura(nombreDelMed, codigoDelMed, cantMedVendidos, fechaDeLaCompra);
		return facturacion;
	}

	// Metodos del primer reporte 
	public ArrayList<VentaDeMedicamentos> buscarOrdenDeAccion()
	{
		VentaDeMedicamentos venta= new VentaDeMedicamentos();
		ArrayList<VentaDeMedicamentos> ventas = new ArrayList<VentaDeMedicamentos>();
		for(Medicamento m: medicamentos)
		{
			int totalVendidoDelMedicamento = 0;
			for(Factura f: facturas)
				if(m.nomComun.equals(f.getNombreDelMed()))
					totalVendidoDelMedicamento += f.getCantMedVendidos();

			if(totalVendidoDelMedicamento > 0)
			{
				venta.setCantidadVendida(totalVendidoDelMedicamento);
				venta.setNombre(m.nomComun);
				ventas.add(venta);
			}
		}

		//  es como tener un merge sort

		Collections.sort(ventas, comparador);
		return ventas;
	}

	Comparator<VentaDeMedicamentos> comparador = new Comparator<VentaDeMedicamentos>() 
			{
		public int compare(VentaDeMedicamentos a, VentaDeMedicamentos b) 
		{
			int cantidadB = b.getCantidadVendida();
			int cantidadA = a.getCantidadVendida();
			return cantidadB - cantidadA; 
		}
			};



			// metodos del segundo reporte

			public long calcularCantidad()
			{
				long cantidad = 0;

				for(NucleoFamiliar n: nucleos)
				{
					if(n.getCompraron() == false)
						cantidad += n.getMujeres().size();
				}

				cantidadDeAlmohadillasSanitarias -= cantidadDeAlmohadillas();

				// DANGER: realizar validacion de mayor que stock 
				cantidad -= cantidadDeAlmohadillasSanitarias;

				return cantidad;
			}







			// metodos del tercer reporte
			public Porcentaje calcularValor()
			{
				double importeTotalDeAlmohadillas = cantDeDineroObtenidoPorAlmohadillas();;
				double importeTotalDeMedPrescripcion = cantDeDineroObtenidoPorMedPrescripcion();
				double importeTotalDeMedControlados = cantDeDineroObtenidoPorMedControlados();
				double importeTotalDeMedVentaLibre = cantDeDineroObtenidoPorMedVentaLibre();
				double total = 0;
				Porcentaje resultado;
				total = calcularTotal();

				if(total != 0)
				{
					resultado = calcularPorcentaje(total, importeTotalDeAlmohadillas,
							importeTotalDeMedPrescripcion, importeTotalDeMedControlados, 
							importeTotalDeMedVentaLibre);
				}

				else
				{
					resultado = new Porcentaje(0, "No hubo ventas en la Farmacia");
				}

				return resultado;
			}




			public Porcentaje calcularPorcentaje(double total, double importeTotalDeAlmohadillas,
					double importeTotalDeMedPrescripcion, double importeTotalDeMedControlados, 
					double importeTotalDeMedVentaLibre)
			{
				Porcentaje resultado;
				double porcentajeAlmohadillas = (importeTotalDeAlmohadillas * 100) / total;
				double porcentajePrescripcion = (importeTotalDeMedPrescripcion * 100) / total;
				double porcentajeControlados = (importeTotalDeMedControlados * 100) / total;
				double porcentajeVentaLibre = (importeTotalDeMedVentaLibre * 100) / total;

				resultado = comparacion(porcentajeAlmohadillas, porcentajePrescripcion,
						porcentajeControlados, porcentajeVentaLibre);

				return resultado;
			}

			public Porcentaje comparacion(double porcentajeAlmohadillas, double porcentajePrescripcion,
					double porcentajeControlados, double porcentajeVentaLibre)
			{
				// busco el valor máximo los porcentajes

				double maximo = valorMaximo(porcentajeAlmohadillas, porcentajePrescripcion
						,porcentajeControlados, porcentajeVentaLibre);

				// busco cuantos valores tienen ese valor

				int contador = repeticiones(maximo, porcentajeAlmohadillas, porcentajePrescripcion,
						porcentajeControlados, porcentajeVentaLibre);

				// reviso si se repite ese contador entonces se revisa cuales
				// son los valores que hay que introducir al String

				String nombres = revisionDeRepeticiones(contador ,maximo, porcentajeAlmohadillas, porcentajePrescripcion,
						porcentajeControlados, porcentajeVentaLibre);

				Porcentaje valores = new Porcentaje(maximo,nombres);

				return valores;

			}

			public double valorMaximo(double almo, double pres, double cont, double libr)
			{
				return Math.max(Math.max(almo, pres), Math.max(cont, libr));
			}

			public int repeticiones(double max,double alm, double pres,
					double contro, double libr)
			{
				int contador = 0;

				if(max == alm)
					contador++;

				if(max == pres)
					contador++;

				if(max == contro)
					contador++;

				if(max == libr)
					contador++;

				return contador;
			}

			public String revisionDeRepeticiones(int cont,double max,double alm, double pres,
					double contro, double libr)
			{
				String nombres = null;
				switch(cont)
				{
				case 4:
				{
					nombres = "Almohadillas, Medicamento por Prescripción, Controlado y Libre";
					break;				
				}

				case 3:

				{
					if(max != alm)
						nombres = "Medicamento por Prescripción, Controlado y Libre";
					else if(max != pres)
						nombres = "Almohadillas, Medicamento Controlado y Libre";
					else if(max != contro)
						nombres = "Almohadillas, Medicamento por Prescripción y Libre";
					else if(max != libr)
						nombres = "Almohadillas, Medicamento por Prescripción y Controlado";
					break;
				}
				case 2:
				{
					if (alm == max && pres == max)  
						nombres = "Almohadillas y Prescripción";

					else if (alm == max && contro == max)  
						nombres = "Almohadillas y Controlados";

					else if (alm == max && libr == max)  
						nombres = "Almohadillas y Venta Libre";

					else if (pres == max && contro == max)  
						nombres = "Prescripción y Controlados ";

					else if (pres == max && libr == max)  
						nombres = "Prescripción y Venta Libre";
					break;
				}

				case 1:
				{
					if(max == alm)
						nombres = "Almohadillas";
					else if(max == pres)
						nombres = "Medicamento con Prescripción";
					else if(max == contro)
						nombres = "Medicamento Controlado";
					else if(max == libr)
						nombres = "Medicamento Libre";
					break;
				}

				default:
				{
					nombres = "Error encontrado en el contador, tercer reporte, segundo metodo.";
					break;
				}

				}
				return nombres;
			}



			// metodos del cuarto reporte

			public ArrayList<Tarjeton> tarjetonesDesactivados()
			{
				ArrayList<Tarjeton> tarjetonesInactivos = new ArrayList<Tarjeton>();
				for(Tarjeton t: tarjetones)
					if(!t.validacion(t.getFechaExpedicion(), t.getFechaVencimiento()))
						tarjetonesInactivos.add(t);

				return tarjetonesInactivos;
			}


			public ArrayList<Factura> getFacturas() 
			{
				return facturas;
			}

			public void setFacturas(ArrayList<Factura> facturas) 
			{
				this.facturas = facturas;
			}

			public ArrayList<Medicamento> getMedicamentos() {
				return medicamentos;
			}

			public void setMedicamentos(ArrayList<Medicamento> medicamentos) {
				this.medicamentos = medicamentos;
			}

			public long getCantidadDeAlmohadillasSanitarias() 
			{
				return cantidadDeAlmohadillasSanitarias;
			}

			public void setCantidadDeAlmohadillasSanitarias(long cantidadDeAlmohadillasSanitarias) 
			{
				this.cantidadDeAlmohadillasSanitarias = cantidadDeAlmohadillasSanitarias;
			}

			public long cantidadDeAlmohadillas()
			{
				long cantidad = 0;
				for(Venta v: historialVentas)
					if(v instanceof AlmohadillasSanitarias)
						cantidad += ((AlmohadillasSanitarias) v).getCant();

				return cantidad;

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
					}
				}
				return importeTotalDeMedVentaLibre;
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


			// Agregar Datos: 

			// Medicamentos Controlados
			public void agregarMedicamentosControlados(String nomComun, String nomCientifico, String presentacion,
					double precio, String tipo, String fortaleza, double tempAlmac,
					long cantExis, LocalDate fechaProd, LocalDate fechaVenc, String patologia, long cantAsigMensual, long cantDispensadaMensual)
			{
				Date utilFechaProd = Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date utilFechaVenc = Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				MedicamentoControlado m = new MedicamentoControlado(nomComun,nomCientifico, presentacion, precio, tipo, fortaleza, tempAlmac, cantExis, utilFechaProd , utilFechaVenc
						,patologia, cantAsigMensual, cantDispensadaMensual);
				registrarMedicamentoControlado(m);
			}




			// Inicializar MEdicamentos
			public void agregarMedicamento(String nomComun, String nomCientifico, String presentacion,
					double precio, String tipo, String fortaleza, double tempAlmac,
					long cantExis, LocalDate fechaProd, LocalDate fechaVenc)
			{
				Date utilFechaProd = Date.from(fechaProd.atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date utilFechaVenc = Date.from(fechaVenc.atStartOfDay(ZoneId.systemDefault()).toInstant());
				Medicamento m = new Medicamento(nomComun,nomCientifico, presentacion, precio, tipo, fortaleza, tempAlmac, cantExis, utilFechaProd , utilFechaVenc);
				medicamentos.add(m);
			}



			// inicializador de tarjetones
			public void inicializarTarjetones() 
			{
				for (int i = 0; i < 15; i++) 
				{
					Paciente p = pacientes.get(i);
					int numTarjetones = 1 + (i % 3);
					for (int j = 0; j < numTarjetones; j++) 
					{
						LocalDate fechaExp = LocalDate.of(2024, 1 + (i % 12), 1 + (j * 10));
						LocalDate fechaVenc = fechaExp.plusMonths(6);


						int numMedicamentos = 1 + (j % 3);
						ArrayList<MedicamentoControlado> medsControlados = new ArrayList<MedicamentoControlado>();

						for (int k = 0; k < numMedicamentos && k < obtenerMedicamentoControlado().size(); k++) 
						{
							medsControlados.add((MedicamentoControlado) obtenerMedicamentoControlado().get(k));
						}
						Tarjeton t = new Tarjeton
								(
										p.getNombre(),
										p.getDireccion(),
										java.sql.Date.valueOf(fechaExp), 
										java.sql.Date.valueOf(fechaVenc), 
										medsControlados
										);
						tarjetones.add(t);
						p.agregarTarjeton(t);
					}
				}
			}

			public void generarFacturasDesdeVentas() 
			{
				for (Venta venta : historialVentas) 
				{
					String nombreMedicamento = "";
					String codigoMedicamento = "";
					int cantidadVendida = 1;
					Date fechaCompra = venta.getFechaVenta();

					if (venta instanceof VentaLibre) 
					{
						nombreMedicamento = ((VentaLibre) venta).getNombreDelMed();
						codigoMedicamento = ((VentaLibre) venta).getCodigoDelMed();
						cantidadVendida = ((VentaLibre) venta).getCantMedVendidos(); 
						facturas.add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
					} 

					else if (venta instanceof VentaConPrescripcion) 
					{
						
						nombreMedicamento = ((VentaConPrescripcion) venta).getNombreDelMed(); 
						codigoMedicamento = ((VentaConPrescripcion) venta).getCodigoDelMed();
						cantidadVendida = ((VentaConPrescripcion) venta).getCantMedVendidos();
						facturas.add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
					} 

					else if (venta instanceof VentaControlada) 
					{
						nombreMedicamento = ((VentaControlada) venta).getNombreDelMed(); 
						codigoMedicamento = ((VentaControlada) venta).getCodigoDelMed();
						cantidadVendida = ((VentaControlada) venta).getCantMedVendidos();
						facturas.add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
					} 

					else if (venta instanceof AlmohadillasSanitarias) 
					{
						AlmohadillasSanitarias alm = (AlmohadillasSanitarias) venta;
						nombreMedicamento = "Almohadillas Sanitarias";
						codigoMedicamento = "ALM-001";
						cantidadVendida = alm.getCant();
						facturas.add(new Factura(nombreMedicamento, codigoMedicamento, cantidadVendida, fechaCompra));
					}
				}
				System.out.println("Se generaron " + facturas.size() + " facturas desde el historial de ventas.");
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


			public void inicializarVentasLibres() 
			{
				// Ventas con prescripción - 40 ejemplos
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 3, 1), 25.0, LocalDate.of(2025, 1, 1), "Omeprazol", "Inhibidor de bomba", 20 );
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 2), 30.0, LocalDate.of(2025, 1, 1),"Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 3), 28.5, LocalDate.of(2025, 1, 2),"Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 4), 32.75, LocalDate.of(2025, 1, 2),"Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 5), 40.0, LocalDate.of(2025, 1, 3), "Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 6), 22.0, LocalDate.of(2025, 1, 4), "Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 7), 35.0, LocalDate.of(2025, 1, 4), "Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 8), 45.0, LocalDate.of(2025, 1, 5), "Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 9), 30.0, LocalDate.of(2025, 1, 5), "Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 10), 28.0, LocalDate.of(2025, 1, 6), "Omeprazol", "Inhibidor de bomba", 20);

				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 11), 32.0, LocalDate.of(2025, 1, 6),"Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 12), 37.0, LocalDate.of(2025, 1, 7), "Omeprazol", "Inhibidor de bomba", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 13), 41.0, LocalDate.of(2025, 1, 7), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 14), 29.0, LocalDate.of(2025, 1, 8), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 15), 33.0, LocalDate.of(2025, 1, 8), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 16), 36.0, LocalDate.of(2025, 1, 9), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 17), 42.0, LocalDate.of(2025, 1, 9), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 18), 26.0, LocalDate.of(2025, 1, 10), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 19), 34.0, LocalDate.of(2025, 1, 10), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 20), 39.0, LocalDate.of(2025, 1, 11), "Metformina", "Glifage", 20);

				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 21), 27.0, LocalDate.of(2025, 1, 11), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 22), 31.0, LocalDate.of(2025, 1, 12), "Metformina", "Glifage", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 23), 38.0, LocalDate.of(2025, 1, 12), "Simvastatina", "Estatinas", 40);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 24), 43.0, LocalDate.of(2025, 1, 13), "Simvastatina", "Estatinas", 40);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 25), 33.0, LocalDate.of(2025, 1, 13), "Simvastatina", "Estatinas", 40);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 26), 29.0, LocalDate.of(2025, 1, 14), "Simvastatina", "Estatinas", 40);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 27), 35.0, LocalDate.of(2025, 1, 14), "Amoxilina", "Penicilina", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 28), 40.0, LocalDate.of(2025, 1, 15), "Amoxilina", "Penicilina", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 29), 31.0, LocalDate.of(2025, 1, 15), "Amoxilina", "Penicilina", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 30), 36.0, LocalDate.of(2025, 1, 16), "Amoxilina", "Penicilina", 20);

				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 1, 31), 44.0, LocalDate.of(2025, 1, 16), "Amoxilina", "Penicilina", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 1), 25.0, LocalDate.of(2025, 1, 17), "Atorvastatina", "Lipitor", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 2), 30.0, LocalDate.of(2025, 1, 17), "Atorvastatina", "Lipitor", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 3), 28.0, LocalDate.of(2025, 1, 18), "Atorvastatina", "Lipitor", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 4), 32.0, LocalDate.of(2025, 1, 18), "Atorvastatina", "Lipitor", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 5), 37.0, LocalDate.of(2025, 1, 19), "Dipirona", "Metamizol", 40);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 6), 42.0, LocalDate.of(2025, 1, 19), "Naproxeno", "Antiinflamatorio", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 7), 34.0, LocalDate.of(2025, 1, 20), "Dipirona", "Metamizol", 40);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 8), 39.0, LocalDate.of(2025, 1, 20), "Naproxeno", "Antiinflamatorio", 20);
				Farmacia.obtenerInstancia().agregarVentaConPrescripcion(LocalDate.of(2025, 2, 9), 27.0, LocalDate.of(2025, 1, 21), "Naproxeno", "Antiinflamatorio", 20);
			}

			public void agregarVentaConPrescripcion(LocalDate date, double importe, LocalDate dateDos, String nombreDelMed,String codigoDelMed, int cantMedVendidos)
			{
				Date fechaVenta = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
				Date fechaDeCompra = Date.from(dateDos.atStartOfDay(ZoneId.systemDefault()).toInstant());

				VentaConPrescripcion m = new VentaConPrescripcion(fechaVenta, importe, fechaDeCompra, nombreDelMed, codigoDelMed, cantMedVendidos);

				historialVentas.add(m);
			}


			public void inicializarVentaLibre()
			{

				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 3, 1), 20.0, "Paracetamol", "Acetaminofén", 15);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 4, 2), 15.0, "Paracetamol", "Acetaminofén", 30 );
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 3), 18.5, "Paracetamol", "Acetaminofén", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 4), 22.0, "Paracetamol", "Acetaminofén", 15);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 5), 10.0, "Paracetamol", "Acetaminofén", 10);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 6), 17.0, "Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 7), 25.0, "Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 8), 12.0, "Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 9), 14.5, "Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 10), 19.0, "Ibuprofeno", "Ácido Ibuprofénico", 20);

				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 11), 21.0, "Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 12), 16.0,"Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 13), 23.0,"Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 14), 11.0, "Ibuprofeno", "Ácido Ibuprofénico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 15), 13.5, "Aspirina", "Ácido Acetilsalicílico", 30);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 16), 18.0, "Aspirina", "Ácido Acetilsalicílico", 30);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 17), 24.0, "Aspirina", "Ácido Acetilsalicílico", 30);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 18), 15.0, "Aspirina", "Ácido Acetilsalicílico", 15);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 19), 19.0, "Aspirina", "Ácido Acetilsalicílico", 25);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 20), 20.0, "Loratadina", "Antihistamínico", 20);

				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 21), 22.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 22), 17.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 23), 14.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 24), 18.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 25), 23.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 26), 20.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 27), 15.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 28), 19.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 29), 16.0, "Loratadina", "Antihistamínico", 20);
				Farmacia.obtenerInstancia().agregarVentaLibre(LocalDate.of(2025, 1, 30), 21.0, "Loratadina", "Antihistamínico", 20);

			}


			public void agregarVentaLibre(LocalDate fecha, double importe,String nombreDelMed,String codigoDelMed, int cantMedVendidos)
			{
				Date fechaDeCompra = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
				VentaLibre vl = new VentaLibre(fechaDeCompra, importe, nombreDelMed, codigoDelMed, cantMedVendidos);
				historialVentas.add(vl);
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

			public void agregarVentaAlmohadillasSanitarias(LocalDate fecha, double precio, int cant)
			{
				//AlmohadillasSanitarias(double precioUnit, int cant,Date fechaVenta)
				Date fechaDeCompra = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
				AlmohadillasSanitarias a = new AlmohadillasSanitarias(precio, cant, fechaDeCompra);
				historialVentas.add(a);
			}

			public void inicializarVentaControlada()
			{
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 6, 1), 50.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 2), 60.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 3), 55.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 4), 65.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 5), 70.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 6), 48.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 7), 52.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 8), 58.0, "Tramadol","Tramadol Clorhidrato", 30);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 9), 63.0, "Alprazolam", "Alprazolam", 20);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 10), 54.0, "Alprazolam", "Alprazolam", 20) ;

				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 11), 56.0, "Alprazolam", "Alprazolam", 20);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 12), 59.0, "Alprazolam", "Alprazolam", 20);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 13), 62.0, "Alprazolam", "Alprazolam", 20);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 14), 67.0, "Alprazolam", "Alprazolam", 20);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 15), 61.0, "Alprazolam", "Alprazolam", 20);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 16), 53.0, "Clonazepam", "Clonazepam", 40);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 17), 57.0,  "Clonazepam", "Clonazepam", 40);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 18), 64.0, "Clonazepam", "Clonazepam", 40);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 19), 68.0, "Clonazepam", "Clonazepam", 40);
				Farmacia.obtenerInstancia().agregarVentaControlada(LocalDate.of(2025, 1, 20), 69.0, "Ritalina", "Metilfenidato", 30);	
			}

			public void agregarVentaControlada(LocalDate fecha, double importe,String nombreDelMed,String codigoDelMed, int cantMedVendidos)
			{
				Date fechaDeCompra = Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
				VentaControlada vc = new VentaControlada(fechaDeCompra, importe, nombreDelMed, codigoDelMed, cantMedVendidos);
				historialVentas.add(vc);
			}


			// Métodos de acceso
			public ArrayList<Paciente> obtenerPacientes() 
			{
				return pacientes;
			}


			public ArrayList obtenerMedicamentoControlado()
			{
				return medicamentoControlado;
			}
			public ArrayList<Medicamento> obtenerMedicamentos() 
			{
				return medicamentos;
			}

			public ArrayList<Venta> obtenerVentas()
			{
				return new ArrayList<Venta>(historialVentas);
			}

			public ArrayList<Factura> obtenerFacturas() 
			{
				return new ArrayList<Factura>(facturas);
			}

			public List<Tarjeton> obtenerTarjetones() 
			{
				return new ArrayList<Tarjeton>(tarjetones);
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


			//Inicializacion De Datos
			public void inicializarPacientes() 
			{
				// Hombres (25)
				Farmacia.obtenerInstancia().agregarPaciente("Alejandro Bermúdez Cervantes", "88041568485", 'M', LocalDate.of(1988, 4, 15), "Calle 23, #45");
				Farmacia.obtenerInstancia().agregarPaciente("Benjamín Delgado Espinoza", "90062012345", 'M', LocalDate.of(1990, 6, 20), "Calle 12, #34");
				Farmacia.obtenerInstancia().agregarPaciente("Carlos Fuentes Gutiérrez", "78031049284", 'M', LocalDate.of(1978, 3, 10), "Calle 34, #56");
				Farmacia.obtenerInstancia().agregarPaciente("Daniel Hernández López", "85071234567", 'M', LocalDate.of(1985, 7, 12), "Calle 45, #78");
				Farmacia.obtenerInstancia().agregarPaciente("Emilio Juárez Méndez", "92081567890", 'M', LocalDate.of(1992, 8, 15), "Calle 56, #90");
				Farmacia.obtenerInstancia().agregarPaciente("Fernando Katz Núñez", "83092123456", 'M', LocalDate.of(1983, 9, 21), "Calle 67, #12");
				Farmacia.obtenerInstancia().agregarPaciente("Gabriel López Ortiz", "89103078901", 'M', LocalDate.of(1989, 10, 30), "Calle 78, #34");
				Farmacia.obtenerInstancia().agregarPaciente("Héctor Méndez Pérez", "77020545678", 'M', LocalDate.of(1977, 2, 5), "Calle 89, #56");
				Farmacia.obtenerInstancia().agregarPaciente("Ignacio Núñez Quintero", "94040890123", 'M', LocalDate.of(1994, 4, 8), "Calle 90, #78");
				Farmacia.obtenerInstancia().agregarPaciente("Javier Ortiz Rojas", "81061156789", 'M', LocalDate.of(1981, 6, 11), "Calle 11, #90");
				Farmacia.obtenerInstancia().agregarPaciente("Kevin Pérez Sánchez", "99121401234", 'M', LocalDate.of(1999, 12, 14), "Calle 22, #13");
				Farmacia.obtenerInstancia().agregarPaciente("Luis Quintero Torres", "87101767890", 'M', LocalDate.of(1987, 10, 17), "Calle 33, #24");
				Farmacia.obtenerInstancia().agregarPaciente("Manuel Rojas Aguilar", "93022012345", 'M', LocalDate.of(1993, 2, 20), "Calle 44, #35");
				Farmacia.obtenerInstancia().agregarPaciente("Nicolás Sánchez Bermúdez", "80032378901", 'M', LocalDate.of(1980, 3, 23), "Calle 55, #46");
				Farmacia.obtenerInstancia().agregarPaciente("Oscar Torres Cervantes", "95062623456", 'M', LocalDate.of(1995, 6, 26), "Calle 66, #57");
				Farmacia.obtenerInstancia().agregarPaciente("Pablo Aguilar Delgado", "85012989012", 'M', LocalDate.of(1985, 1, 29), "Calle 77, #68");
				Farmacia.obtenerInstancia().agregarPaciente("Raúl Bermúdez Espinoza", "74040234567", 'M', LocalDate.of(1974, 4, 2), "Calle 88, #79");
				Farmacia.obtenerInstancia().agregarPaciente("Sergio Cervantes Fuentes", "98070590123", 'M', LocalDate.of(1998, 7, 5), "Calle 99, #80");
				Farmacia.obtenerInstancia().agregarPaciente("Tomás Delgado Gutiérrez", "82080845678", 'M', LocalDate.of(1982, 8, 8), "Calle 10, #91");
				Farmacia.obtenerInstancia().agregarPaciente("Víctor Espinoza Hernández", "91091101234", 'M', LocalDate.of(1991, 9, 11), "Calle 21, #02");
				Farmacia.obtenerInstancia().agregarPaciente("Adrián Fuentes Ibarra", "75011456789", 'M', LocalDate.of(1975, 1, 14), "Calle 32, #13");
				Farmacia.obtenerInstancia().agregarPaciente("Bruno Gutiérrez Juárez", "97021712345", 'M', LocalDate.of(1997, 2, 17), "Calle 43, #24");
				Farmacia.obtenerInstancia().agregarPaciente("Diego Hernández Katz", "87032067890", 'M', LocalDate.of(1987, 3, 20), "Calle 54, #35");
				Farmacia.obtenerInstancia().agregarPaciente("Eduardo Ibarra López", "92042323456", 'M', LocalDate.of(1992, 4, 23), "Calle 65, #46");
				Farmacia.obtenerInstancia().agregarPaciente("Felipe Juárez Méndez", "81052678901", 'M', LocalDate.of(1981, 5, 26), "Calle 76, #57");

				// Mujeres (20)
				Farmacia.obtenerInstancia().agregarPaciente("Adriana Méndez Núñez", "89062934567", 'F', LocalDate.of(1989, 6, 29), "Calle 87, #68");
				Farmacia.obtenerInstancia().agregarPaciente("Beatriz Núñez Ortiz", "94080190123", 'F', LocalDate.of(1994, 8, 1), "Calle 98, #79");
				Farmacia.obtenerInstancia().agregarPaciente("Camila Ortiz Pérez", "85090445678", 'F', LocalDate.of(1985, 9, 4), "Calle 09, #80");
				Farmacia.obtenerInstancia().agregarPaciente("Diana Pérez Quintero", "99100701234", 'F', LocalDate.of(1999, 10, 7), "Calle 20, #91");
				Farmacia.obtenerInstancia().agregarPaciente("Elena Quintero Rojas", "77111056789", 'F', LocalDate.of(1977, 11, 10), "Calle 31, #02");
				Farmacia.obtenerInstancia().agregarPaciente("Fernanda Rojas Sánchez", "93021312345", 'F', LocalDate.of(1993, 2, 13), "Calle 42, #13");
				Farmacia.obtenerInstancia().agregarPaciente("Gabriela Sánchez Torres", "83031667890", 'F', LocalDate.of(1983, 3, 16), "Calle 53, #24");
				Farmacia.obtenerInstancia().agregarPaciente("Helena Torres Aguilar", "97041923456", 'F', LocalDate.of(1997, 4, 19), "Calle 64, #35");
				Farmacia.obtenerInstancia().agregarPaciente("Isabel Aguilar Bermúdez", "87052278901", 'F', LocalDate.of(1987, 5, 22), "Calle 75, #46");
				Farmacia.obtenerInstancia().agregarPaciente("Jimena Bermúdez Cervantes", "92062534567", 'F', LocalDate.of(1992, 6, 25), "Calle 86, #57");
				Farmacia.obtenerInstancia().agregarPaciente("Karina Cervantes Delgado", "82072890123", 'F', LocalDate.of(1982, 7, 28), "Calle 97, #68");
				Farmacia.obtenerInstancia().agregarPaciente("Laura Delgado Espinoza", "98083145678", 'F', LocalDate.of(1998, 8, 31), "Calle 08, #79");
				Farmacia.obtenerInstancia().agregarPaciente("Mariana Espinoza Fuentes", "74090301234", 'F', LocalDate.of(1974, 9, 3), "Calle 19, #80");
				Farmacia.obtenerInstancia().agregarPaciente("Natalia Fuentes Gutiérrez", "95100656789", 'F', LocalDate.of(1995, 10, 6), "Calle 30, #91");
				Farmacia.obtenerInstancia().agregarPaciente("Olivia Gutiérrez Hernández", "85110912345", 'F', LocalDate.of(1985, 11, 9), "Calle 41, #02");
				Farmacia.obtenerInstancia().agregarPaciente("Patricia Hernández Ibarra", "99121267890", 'F', LocalDate.of(1999, 12, 12), "Calle 52, #13");
				Farmacia.obtenerInstancia().agregarPaciente("Raquel Ibarra Juárez", "81031523456", 'F', LocalDate.of(1981, 3, 15), "Calle 63, #24");
				Farmacia.obtenerInstancia().agregarPaciente("Sofía Juárez Katz", "97041878901", 'F', LocalDate.of(1997, 4, 18), "Calle 74, #35");
				Farmacia.obtenerInstancia().agregarPaciente("Tatiana Katz López", "83052134567", 'F', LocalDate.of(1983, 5, 21), "Calle 85, #46");
				Farmacia.obtenerInstancia().agregarPaciente("Valeria López Méndez", "93062490123", 'F', LocalDate.of(1993, 6, 24), "Calle 96, #57");
			}



			public void inicializarMedicamentos() 
			{
				// Medicamento 1
				Farmacia.obtenerInstancia().agregarMedicamento("Paracetamol", "Acetaminofén", "Tabletas", 15.0, "Venta libre", "500 mg", 25.0, 100,
						LocalDate.of(2025, 2, 07), LocalDate.of(2027, 2, 07));

				// Medicamento 2
				Farmacia.obtenerInstancia().agregarMedicamento("Ibuprofeno", "Ácido Ibuprofénico", "Cápsulas", 20.0, "Venta libre", "400 mg", 25.0, 200,
						LocalDate.of(2025, 2, 15), LocalDate.of(2027, 2 ,15));

				// Medicamento 3
				Farmacia.obtenerInstancia().agregarMedicamento("Aspirina", "Ácido Acetilsalicílico", "Tabletas", 10.0, "Venta libre", "100 mg", 25.0, 150,
						LocalDate.of(2025, 3, 10), LocalDate.of(2027, 3, 10));

				// Medicamento 4
				Farmacia.obtenerInstancia().agregarMedicamento("Loratadina", "Antihistamínico", "Jarabe", 12.5, "Venta libre", "10 mg/ml", 20.0, 300,
						LocalDate.of(2025,4,20), LocalDate.of(2027, 4,20));

				// Medicamento 5
				Farmacia.obtenerInstancia().agregarMedicamento("Omeprazol", "Inhibidor de bomba", "Cápsulas", 18.99, "Con prescripción", "20 mg", 20.0, 250,
						LocalDate.of(2025,2,05), LocalDate.of(2027, 5,05));

				// Medicamento 6
				Farmacia.obtenerInstancia().agregarMedicamento("Metformina", "Glifage", "Tabletas", 25.50, "Con prescripción", "500 mg", 20.0, 200,
						LocalDate.of(2025,1,10), LocalDate.of(2027,6,10));

				// Medicamento 7
				Farmacia.obtenerInstancia().agregarMedicamento("Simvastatina", "Estatinas", "Tabletas", 30.0, "Con prescripción", "20 mg", 20.0, 180,
						LocalDate.of(2025, 2,15), LocalDate.of(2027, 7,15));

				// Medicamento 8
				Farmacia.obtenerInstancia().agregarMedicamento("Amoxicilina", "Penicilina", "Cápsulas", 22.75, "Con prescripción", "250 mg", 20.0, 120,
						LocalDate.of(2025, 2,20), LocalDate.of(2027, 8, 20));

				// Medicamento 9
				Farmacia.obtenerInstancia().agregarMedicamento("Atorvastatina", "Lipitor", "Cápsulas", 35.0, "Con prescripción", "40 mg", 20.0, 100,
						LocalDate.of(2025, 1,25), LocalDate.of(2027,9, 25));

				// Medicamento 10
				Farmacia.obtenerInstancia().agregarMedicamento("Dipirona", "Metamizol", "Tabletas", 13.0, "Venta libre", "500 mg", 25.0, 400,
						LocalDate.of(2025, 3,01), LocalDate.of(2027,10,01));

				// Medicamento 11
				Farmacia.obtenerInstancia().agregarMedicamento("Levotiroxina", "T", "Tabletas", 28.99, "Con prescripción", "50 mcg", 20.0, 300,
						LocalDate.of(2025, 2, 05), LocalDate.of(2027, 11, 05 ));

				// Medicamento 12
				Farmacia.obtenerInstancia().agregarMedicamento("Diclofenaco", "Antiinflamatorio", "Gel", 15.0, "Con prescripción", "100mg", 25.0, 200,
						LocalDate.of(2025, 2, 10), LocalDate.of(2027,12,10));

				// Medicamento 13
				Farmacia.obtenerInstancia().agregarMedicamento("Naproxeno", "Antiinflamatorio", "Tabletas", 10.5, "Con prescripción", "250 mg", 25.0, 180,
						LocalDate.of(2025, 1,15), LocalDate.of(2027, 4,15)); 

				// Medicamento 14
				Farmacia.obtenerInstancia().agregarMedicamento("Salbutamol", "Ventolín", "Inhalador", 18.99, "Con prescripción", "100 mcg", 15.0, 150,
						LocalDate.of(2025, 2, 20), LocalDate.of(2027, 5, 20));
			}

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
						"Convulsiones y sedación",
						130,
						120
						);
			} 

			public void inicializarDatosPrueba() 
			{
				inicializarPacientes();
				inicializarMedicamentos();
				inicializarMedicamentosControlados();
				inicializarTarjetones();
				inicializarVentaControlada();
				inicializarVentasLibres();
				inicializarAlmohadillas();
				inicializarVentasLibres();
				generarFacturasDesdeVentas();
			}


			// mas adelante quitar esto
			public Factura generarFactura(String nombreDelMed, String codigoDelMed,
					int cantMedVendidos, java.sql.Date fechaDeLaCompra) 
			{
				// TODO Auto-generated method stub
				return null;
			}
}
